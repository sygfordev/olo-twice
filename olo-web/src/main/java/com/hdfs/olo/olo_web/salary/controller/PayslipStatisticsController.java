package com.hdfs.olo.olo_web.salary.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipRewardBiz;
import com.hdfs.olo.olo_web.salary.biz.IPayslipStatisticsBiz;
import com.hdfs.olo.olo_web.salary.biz.impl.SaPayslipHeadUtil;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;

@Controller
@RequestMapping("/salary/statistics/*")
public class PayslipStatisticsController {
	public final static String Module_Name = "工资条统计";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChSaPayslipBiz chSaPayslipBiz;
	@Autowired
	private IPayslipStatisticsBiz payslipStatisticsBiz;
	@Autowired
	private IChSaPayslipRewardBiz chSaPayslipRewardBiz;
	@Autowired
	private IHuTCommonService huTCommonService; 
	
	//工资条字典缓存时间
	Long cachedTime = null;
	Map<String,List<String>> cachedMap = new HashMap<String,List<String>>();
	private static final Map<String,Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String,Object>();
		cachedColumnsMap.put("DEPART_CLASS_CN", "departClass");
		cachedColumnsMap.put("WK_MODALITY_CN", "modality");
	}
	
	/**
	 * <p>Discription:[薪资报表首页]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		
	}
	/**
	 * 薪资统计>   定向页面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "{type}/tostatis.jhtm")
	public String toIndex(HttpServletRequest request,ModelMap modelMap,@PathVariable("type")int type) {
		return "salary/statistics/"+type;
	}
	
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaPayslipExtendModel> builder) throws Exception{
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):1;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):1;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String name = request.getParameter("name");
		//name=!StringHelper.isNullOrEmpty(name)?new String(name.getBytes("iso8859-1"),"UTF-8"):null;
		String cardNo = request.getParameter("cardNo");
		String wkModalityCn = request.getParameter("wkModalityCn");
		String departClassCn = request.getParameter("departClassCn");
		
		//扩展属性
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		
		ChSaPayslipExtendModel model = new ChSaPayslipExtendModel();
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setDepartClassCn(null!=departClassCn && !"".equals(departClassCn.trim())?departClassCn.trim():null);
		model.setStatus(0);
		model.setStartMonth(StringHelper.isNullOrEmpty(startMonth)?null:startMonth);
		model.setEndMonth(StringHelper.isNullOrEmpty(endMonth)?null:endMonth);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * 若超过设置的缓存时间，则重新缓存搜索区域条件
	 */
	private synchronized void loadCachedDict(ModelMap modelMap)
	{
		if(null == modelMap) return;
		//第一步：判断是否超过缓存时常，若超过，则重新加载数据
		Long curTime = System.currentTimeMillis();
		if(null == cachedTime || curTime - cachedTime > (24*60*60*1000) || cachedMap.isEmpty())
		{
			cachedTime = curTime;//将当前时间设置为缓存时间
			cachedMap.clear();//清空当前的缓存数据列表
			//String fValues = null;
			List<String> fvallist = null;
			for(String column:cachedColumnsMap.keySet()) {
				fvallist = chSaPayslipBiz.loadSelectFields(column);
//				if(null!=fValues)
//					fvallist = Arrays.asList(fValues.split(","));
//				else
//					fvallist = new ArrayList<String>();
				if(null == fvallist) fvallist = new ArrayList<String>();
				cachedMap.put(cachedColumnsMap.get(column)+"List", fvallist);
			}
		}
		//第二步：重新返回缓存数据
		List<ComboxItem> comboxs = null;
		for(String key:cachedMap.keySet())
		{
			List<String> list = cachedMap.get(key);
			comboxs = new ArrayList<ComboxItem>();
			for(String item:list)
			{
				comboxs.add(new ComboxItem(item,item));
			}
			modelMap.addAttribute(key, comboxs);
		}
	}
	
	//=======================薪资报表==============================
	/**
	 * <p>Discription:[统计工资条信息]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "100/tostatis.jhtm")
	public String toSt100(HttpServletRequest request,ModelMap modelMap) {
		loadCachedDict(modelMap);
		return "salary/statistics/100";
	}
	
	/**
	 * <p>Discription:[薪资-工资单首页数据加载-分页]</p>
	 * Created on 2021年05月14日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "100/dostatis.jhtm")
	@ResponseBody
	public Result4Page<ChSaPayslipExtendModel> st100(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Result4Page<ChSaPayslipExtendModel> retBody = new Result4Page<ChSaPayslipExtendModel>();
		//查询参数
		Page.Builder<ChSaPayslipExtendModel> builder = new Page.Builder<ChSaPayslipExtendModel>();
		setSearchParameters(request, builder);
		Page<ChSaPayslipExtendModel> page = builder.build();
		String startMonth = page.getModel().getStartMonth();
		String endMonth = page.getModel().getEndMonth();
		if(StringHelper.isNullOrEmpty(startMonth) && 
				StringHelper.isNullOrEmpty(endMonth)) {
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("请选择时间段！");
			return retBody;
		}
		try {
			//分页
			List<String> months = payslipStatisticsBiz.queryMonthList(page);
			payslipStatisticsBiz.queryPage(page,months);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			e.printStackTrace();
		}
		return retBody;
	}
	
	@RequestMapping(value = "100/loadHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadSt100Head(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		if(StringHelper.isNullOrEmpty(startMonth) && 
				StringHelper.isNullOrEmpty(endMonth))
		{
			return LayAjaxHelper.fail("请选择时间段！");
		}
		//查询参数
		Page.Builder<ChSaPayslipExtendModel> builder = new Page.Builder<ChSaPayslipExtendModel>();
		setSearchParameters(request, builder);
		Page<ChSaPayslipExtendModel> page = builder.build();
		List<String> months = payslipStatisticsBiz.queryMonthList(page);
		JSONArray heads = SaPayslipHeadUtil.loadHead4_100(months);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("heads", heads);
		retMap.put("startMonth", startMonth);
		retMap.put("endMonth", endMonth);
		return retMap;
	}
	
	@RequestMapping(value = "export100.jhtm")
	@ResponseBody
	public void export100(HttpServletRequest request,HttpServletResponse response) {
		//查询参数
		Page.Builder<ChSaPayslipExtendModel> builder = new Page.Builder<ChSaPayslipExtendModel>();
		try {
			setSearchParameters(request, builder);
			Page<ChSaPayslipExtendModel> page = builder.build();
			//根据搜检区间查询奖励项汇总个数
			List<String> months = payslipStatisticsBiz.queryMonthList(page);
			//根据奖励项个数生成excel的header
			JSONObject headJson = PayslipExportHeadUtil.createStatisticsHead(months);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> data = payslipStatisticsBiz.queryListWithSerial(page.getModel(),months);
			Map<String,Object> item = null;
			for(int i=0;i<data.size();i++)
			{
				item = data.get(i);
				item.put("SERIAL_NO", (i+1));
			}
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资单统计", data, headInfo);
		}catch(Exception e)
		{
			logger.error("工资单导出！error:",e);
		}
	}
	
	//全年工资明细汇总===============开始
	@RequestMapping(value = "101/loadHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadSt101Head(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		String year  = request.getParameter("year");
		if(StringHelper.isNullOrEmpty(year))
		{
			return LayAjaxHelper.fail("请选择年份！");
		}
		boolean success = false;
		try {
			List<String> rewards = chSaPayslipRewardBiz.queryRewardList(year+"-01", year+"-12");
			JSONArray heads = SaPayslipHeadUtil.loadHead4_101(year,rewards);
			retMap.put("heads", heads);
			retMap.put("year", year);
			success = true;
		}catch(Exception e) {
			logger.error("加载全年工资明细汇总表头异常！error:",e);
		}
		retMap.put("success", success);
		return retMap;
	}
	/**
	 * （全年工资明细汇总）通过年份、用工形式和薪酬奖励项列表统计
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "101/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_101(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String year  = request.getParameter("year");
		if(StringHelper.isNullOrEmpty(year))
		{
			return LayAjaxHelper.fail("请选择年份！");
		}
		
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4year(year);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export101.jhtm")
	@ResponseBody
	public void export101(HttpServletRequest request,HttpServletResponse response) {
		try {
			String year  = request.getParameter("year");
			if(StringHelper.isNullOrEmpty(year))
			{
				logger.error("全年工资明细汇总表导出时缺失统计年份字段！");
				return;
			}
			//根据奖励项个数生成excel的header
			List<String> rewards = chSaPayslipRewardBiz.queryRewardList(year+"-01", year+"-12");
			JSONObject headJson = PayslipExportHeadUtil.createStatis101Head(year,rewards);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4year(year);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, year+"全年工资明细汇总表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("全年工资明细汇总表导出异常！error:",e);
		}
	}
	
	//用工类型月工资汇总
	@RequestMapping(value = "200/loadHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadSt200Head(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		String yMonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(yMonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		boolean success = false;
		try {
			List<String> rewards = chSaPayslipRewardBiz.queryRewardList(yMonth, yMonth);
			JSONArray heads = SaPayslipHeadUtil.loadHead4_200(yMonth, rewards);
			success = true;
			retMap.put("heads", heads);
			retMap.put("ymonth", yMonth);
		}catch(Exception e) {
			logger.error("加载信息统计>用户类型月工资汇总表头信息异常!",e);
			e.printStackTrace();
		}
		retMap.put("success", success);
		return retMap;
	}
	/**
	 * 用工类型月工资汇总表  
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "200/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_200(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String yMonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(yMonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4modality(yMonth);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export200.jhtm")
	@ResponseBody
	public void export200(HttpServletRequest request,HttpServletResponse response) {
		try {
			String yMonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(yMonth))
			{
				logger.error("用工类型月工资汇总表  导出时薪资年月为空!");
				return;
			}
			
			List<String> rewards = chSaPayslipRewardBiz.queryRewardList(yMonth, yMonth);
			JSONObject headJson = PayslipExportHeadUtil.createStatis200Head(rewards);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4modality(yMonth);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "用工类型"+yMonth+"月工资汇总表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("用工类型月工资汇总表！error:",e);
		}
	}
	
	private static final List<String> MODALITYS_202;
	private static final List<String> SUMPROJECTS_202;
	private static final List<String> MODALITYS_203;
	private static final List<String> MODALITYS_204;
	private static final List<String> MODALITYS_205;
	private static final List<String> MODALITYS_206;
	private static final List<String> MODALITYS_207;
	static {
		MODALITYS_202 = new ArrayList<String>();
		MODALITYS_202.add("总院正式");
		MODALITYS_202.add("总院正式1");
		SUMPROJECTS_202 = new ArrayList<String>();
		SUMPROJECTS_202.add("工勤");
		SUMPROJECTS_202.add("临床");
		SUMPROJECTS_202.add("行管");
		
		MODALITYS_203 = new ArrayList<String>();
		MODALITYS_203.add("总院众鑫");
		MODALITYS_203.add("总院启日");
		MODALITYS_203.add("总院启日1");
		MODALITYS_203.add("基层启日1");
		
		MODALITYS_204 = new ArrayList<String>();
		MODALITYS_204.add("总院返聘");
		
		MODALITYS_205 = new ArrayList<String>();
		MODALITYS_205.add("基层正式");
		MODALITYS_205.add("基层正式1");
		
		MODALITYS_206 = new ArrayList<String>();
		MODALITYS_206.add("基层返聘");
		
		MODALITYS_207 = new ArrayList<String>();
		MODALITYS_207.add("基层合同");
		MODALITYS_207.add("基层启日1");
	}
	/**
	 * 工资汇总-全院
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "201/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_201(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4class(ymonth);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export201.jhtm")
	@ResponseBody
	public void export201(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<全院>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4class(ymonth);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"全院表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-全院表  导出异常！error:",e);
		}
	}
	
	/**
	 * 工资汇总-总院正式
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "202/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_202(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_202, SUMPROJECTS_202);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export202.jhtm")
	@ResponseBody
	public void export202(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<总院正式>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_202, SUMPROJECTS_202);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"总院正式表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-总院正式表  导出异常！error:",e);
		}
	}
	/**
	 * 工资汇总-总院劳务
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "203/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_203(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_203, SUMPROJECTS_202);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export203.jhtm")
	@ResponseBody
	public void export203(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<总院劳务>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_203, SUMPROJECTS_202);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"总院劳务表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-总院劳务表  导出异常！error:",e);
		}
	}
	
	/**
	 * 工资汇总-总院返聘
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "204/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_204(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_204, SUMPROJECTS_202);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export204.jhtm")
	@ResponseBody
	public void export204(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<总院返聘>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_204, SUMPROJECTS_202);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"总院返聘表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-总院返聘表  导出异常！error:",e);
		}
	}
	
	/**
	 * 工资汇总-基层正式
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "205/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_205(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_205, null);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export205.jhtm")
	@ResponseBody
	public void export205(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<基层正式>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_205, null);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"基层正式表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-基层正式表  导出异常！error:",e);
		}
	}
	
	/**
	 * 工资汇总-基层返聘
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "206/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_206(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_206, null);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export206.jhtm")
	@ResponseBody
	public void export206(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<基层返聘>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_206, null);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"基层返聘表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-基层返聘表  导出异常！error:",e);
		}
	}
	
	/**
	 * 工资汇总-基层劳务
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "207/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st4_207(HttpServletRequest request,ModelMap modelMap)throws Exception
	{
		String ymonth  = request.getParameter("ymonth");
		if(StringHelper.isNullOrEmpty(ymonth))
		{
			return LayAjaxHelper.fail("请选择年月！");
		}
		List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_207, null);
		return LayAjaxHelper.success("加载成功!", datas);
	}
	
	@RequestMapping(value = "export207.jhtm")
	@ResponseBody
	public void export207(HttpServletRequest request,HttpServletResponse response) {
		try {
			String ymonth  = request.getParameter("ymonth");
			if(StringHelper.isNullOrEmpty(ymonth))
			{
				logger.error("工资汇总<基层劳务>  导出时薪资年月为空!");
				return;
			}
			
			JSONObject headJson = PayslipExportHeadUtil.createStatis202Head(ymonth);
			if(null == headJson || headJson.isEmpty()) throw new Exception("检索条件有误！");
			List<Map<String,Object>> datas = payslipStatisticsBiz.st4common(ymonth, MODALITYS_207, null);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "工资汇总-"+ymonth+"基层劳务表", datas, headInfo);
		}catch(Exception e)
		{
			logger.error("工资汇总-基层劳务表  导出异常！error:",e);
		}
	}
}
