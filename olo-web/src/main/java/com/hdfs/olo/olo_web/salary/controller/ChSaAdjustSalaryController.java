package com.hdfs.olo.olo_web.salary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustSalaryBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustRecordModel;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustSalaryModel;

/** 
 * Description: [调资表服务实现]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/salary/chSaAdjustSalary/*")
public class ChSaAdjustSalaryController extends BaseController {
	
	public final static String Module_Name = "调资表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChSaAdjustSalaryBiz chSaAdjustSalaryBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_name = "name";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_wagesId = "wagesId";
	private	final static String PARA_wagesSeq = "wagesSeq";
	private	final static String PARA_wagesName = "wagesName";
	private	final static String PARA_wagesModalityCn = "wagesModalityCn";
	private	final static String PARA_wagesDepart = "wagesDepart";
	private	final static String PARA_wagesStandardBef = "wagesStandardBef";
	private	final static String PARA_salaryAdjustTypeCn = "salaryAdjustTypeCn";
	private	final static String PARA_hosDepart1levelCn = "hosDepart1levelCn";
	private	final static String PARA_hosDepart2levelCn = "hosDepart2levelCn";
	private	final static String PARA_edu4max = "edu4max";
	private	final static String PARA_edu4first = "edu4first";
	private	final static String PARA_edu4wage = "edu4wage";
	private	final static String PARA_firstWorkTime = "firstWorkTime";
	private	final static String PARA_workedYear = "workedYear";
	private	final static String PARA_posit4nowCn = "posit4nowCn";
	private	final static String PARA_posit4nowStartTime = "posit4nowStartTime";
	private	final static String PARA_posit4nowYears = "posit4nowYears";
	private	final static String PARA_positLevelCn = "positLevelCn";
	private	final static String PARA_positLevelCode = "positLevelCode";
	private	final static String PARA_onPrinPositStartTime = "onPrinPositStartTime";
	private	final static String PARA_onPrinPositYears = "onPrinPositYears";
	private	final static String PARA_onDeptPositStartTime = "onDeptPositStartTime";
	private	final static String PARA_onDeptPositYears = "onDeptPositYears";
	private	final static String PARA_title4max = "title4max";
	private	final static String PARA_title4maxGotTime = "title4maxGotTime";
	private	final static String PARA_title4maxGotYears = "title4maxGotYears";
	private	final static String PARA_title4maxClassCn = "title4maxClassCn";
	private	final static String PARA_title4maxLevelCn = "title4maxLevelCn";
	private	final static String PARA_adjust4title = "adjust4title";
	private	final static String PARA_adjust4posit = "adjust4posit";
	private	final static String PARA_adjust4edu = "adjust4edu";
	private	final static String PARA_wagesStandardAft = "wagesStandardAft";
	private	final static String PARA_adjustProof = "adjustProof";
	private	final static String PARA_adjustDiffe = "adjustDiffe";
	private	final static String PARA_formulaId = "formulaId";
	private	final static String PARA_recordId = "recordId";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private	final static String PARA_adjustMonth = "adjustMonth";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "status,YN";
	
	//工资条字典缓存时间
	Long cachedTime = null;
	Map<String,List<String>> cachedMap = new HashMap<String,List<String>>();
	private static final Map<String,Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String,Object>();
		cachedColumnsMap.put("HOS_DEPART_1LEVEL_CN", "depart1");
		cachedColumnsMap.put("POSIT_LEVEL_CN", "positLevel");
		cachedColumnsMap.put("TITLE_4MAX_LEVEL_CN", "titleLevel");
	}
		
	/**
	 * <p>Discription:[调资表请求首页]</p>
	 * Created on 2021年05月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		String recId = request.getParameter(PARA_recordId);
		Long recordId = !StringHelper.isNullOrEmpty(recId)?Long.parseLong(recId):null;
		modelMap.put(PARA_recordId, recordId);
		
		loadCachedDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[调资表首页数据加载-分页]</p>
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaAdjustSalaryModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSaAdjustSalaryModel> retBody = new Result4Page<ChSaAdjustSalaryModel>();
		//查询参数
		Page.Builder<ChSaAdjustSalaryModel> builder = new Page.Builder<ChSaAdjustSalaryModel>();
		setSearchParameters(request, builder);
		Page<ChSaAdjustSalaryModel> page = builder.build();
		ChSaAdjustSalaryModel model = page.getModel();
		Long recId = model.getRecordId();
		if(null == recId)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("请求参数中缺失调资记录编号!");
			return retBody;
		}
		try {
			//分页
			chSaAdjustSalaryBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		return retBody;
	}

	/**
	 * <p>Discription:[调资表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaAdjustSalaryModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String name = request.getParameter(PARA_name);
		String cardNo = request.getParameter(PARA_cardNo);
		String wagesId = request.getParameter(PARA_wagesId);
		String wagesSeq = request.getParameter(PARA_wagesSeq);
		String wagesName = request.getParameter(PARA_wagesName);
		String wagesModalityCn = request.getParameter(PARA_wagesModalityCn);
		String wagesDepart = request.getParameter(PARA_wagesDepart);
		String wagesStandardBefStr = request.getParameter(PARA_wagesStandardBef);
		BigDecimal wagesStandardBef = !StringHelper.isNullOrEmpty(wagesStandardBefStr)?new BigDecimal(wagesStandardBefStr):null;
		String salaryAdjustTypeCn = request.getParameter(PARA_salaryAdjustTypeCn);
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String edu4max = request.getParameter(PARA_edu4max);
		String edu4first = request.getParameter(PARA_edu4first);
		String edu4wage = request.getParameter(PARA_edu4wage);
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.strToDate(firstWorkTimeStr, "yyyy-MM-dd"):null;
		String workedYearStr = request.getParameter(PARA_workedYear);
		Double workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Double.parseDouble(workedYearStr):null;
		String posit4nowCn = request.getParameter(PARA_posit4nowCn);
		String posit4nowStartTimeStr = request.getParameter(PARA_posit4nowStartTime);
		Date posit4nowStartTime = !StringHelper.isNullOrEmpty(posit4nowStartTimeStr)?DateTimeHelper.strToDate(posit4nowStartTimeStr, "yyyy-MM-dd"):null;
		String posit4nowYearsStr = request.getParameter(PARA_posit4nowYears);
		Double posit4nowYears = !StringHelper.isNullOrEmpty(posit4nowYearsStr)?Double.parseDouble(posit4nowYearsStr):null;
		String positLevelCn = request.getParameter(PARA_positLevelCn);
		String positLevelCode = request.getParameter(PARA_positLevelCode);
		String onPrinPositStartTimeStr = request.getParameter(PARA_onPrinPositStartTime);
		Date onPrinPositStartTime = !StringHelper.isNullOrEmpty(onPrinPositStartTimeStr)?DateTimeHelper.strToDate(onPrinPositStartTimeStr, "yyyy-MM-dd"):null;
		String onPrinPositYearsStr = request.getParameter(PARA_onPrinPositYears);
		Double onPrinPositYears = !StringHelper.isNullOrEmpty(onPrinPositYearsStr)?Double.parseDouble(onPrinPositYearsStr):null;
		String onDeptPositStartTimeStr = request.getParameter(PARA_onDeptPositStartTime);
		Date onDeptPositStartTime = !StringHelper.isNullOrEmpty(onDeptPositStartTimeStr)?DateTimeHelper.strToDate(onDeptPositStartTimeStr, "yyyy-MM-dd"):null;
		String onDeptPositYearsStr = request.getParameter(PARA_onDeptPositYears);
		Double onDeptPositYears = !StringHelper.isNullOrEmpty(onDeptPositYearsStr)?Double.parseDouble(onDeptPositYearsStr):null;
		String title4max = request.getParameter(PARA_title4max);
		String title4maxGotTimeStr = request.getParameter(PARA_title4maxGotTime);
		Date title4maxGotTime = !StringHelper.isNullOrEmpty(title4maxGotTimeStr)?DateTimeHelper.strToDate(title4maxGotTimeStr, "yyyy-MM-dd"):null;
		String title4maxGotYearsStr = request.getParameter(PARA_title4maxGotYears);
		Double title4maxGotYears = !StringHelper.isNullOrEmpty(title4maxGotYearsStr)?Double.parseDouble(title4maxGotYearsStr):null;
		String title4maxClassCn = request.getParameter(PARA_title4maxClassCn);
		String title4maxLevelCn = request.getParameter(PARA_title4maxLevelCn);
		String adjust4titleStr = request.getParameter(PARA_adjust4title);
		BigDecimal adjust4title = !StringHelper.isNullOrEmpty(adjust4titleStr)?new BigDecimal(adjust4titleStr):null;
		String adjust4positStr = request.getParameter(PARA_adjust4posit);
		BigDecimal adjust4posit = !StringHelper.isNullOrEmpty(adjust4positStr)?new BigDecimal(adjust4positStr):null;
		String adjust4eduStr = request.getParameter(PARA_adjust4edu);
		BigDecimal adjust4edu = !StringHelper.isNullOrEmpty(adjust4eduStr)?new BigDecimal(adjust4eduStr):null;
		String wagesStandardAftStr = request.getParameter(PARA_wagesStandardAft);
		BigDecimal wagesStandardAft = !StringHelper.isNullOrEmpty(wagesStandardAftStr)?new BigDecimal(wagesStandardAftStr):null;
		String adjustProof = request.getParameter(PARA_adjustProof);
		String adjustDiffeStr = request.getParameter(PARA_adjustDiffe);
		BigDecimal adjustDiffe = !StringHelper.isNullOrEmpty(adjustDiffeStr)?new BigDecimal(adjustDiffeStr):null;
		String formulaIdStr = request.getParameter(PARA_formulaId);
		Long formulaId = !StringHelper.isNullOrEmpty(formulaIdStr)?Long.parseLong(formulaIdStr):null;
		String recordIdStr = request.getParameter(PARA_recordId);
		Long recordId = !StringHelper.isNullOrEmpty(recordIdStr)?Long.parseLong(recordIdStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChSaAdjustSalaryModel model = new ChSaAdjustSalaryModel();
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setWagesSeq(null!=wagesSeq && !"".equals(wagesSeq.trim())?wagesSeq.trim():null);
		model.setWagesName(null!=wagesName && !"".equals(wagesName.trim())?wagesName.trim():null);
		model.setWagesModalityCn(null!=wagesModalityCn && !"".equals(wagesModalityCn.trim())?wagesModalityCn.trim():null);
		model.setWagesDepart(null!=wagesDepart && !"".equals(wagesDepart.trim())?wagesDepart.trim():null);
		model.setWagesStandardBef(null!=wagesStandardBef?wagesStandardBef:null);
		model.setSalaryAdjustTypeCn(null!=salaryAdjustTypeCn && !"".equals(salaryAdjustTypeCn.trim())?salaryAdjustTypeCn.trim():null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setEdu4max(null!=edu4max && !"".equals(edu4max.trim())?edu4max.trim():null);
		model.setEdu4first(null!=edu4first && !"".equals(edu4first.trim())?edu4first.trim():null);
		model.setEdu4wage(null!=edu4wage && !"".equals(edu4wage.trim())?edu4wage.trim():null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setPosit4nowCn(null!=posit4nowCn && !"".equals(posit4nowCn.trim())?posit4nowCn.trim():null);
		model.setPosit4nowStartTime(null!=posit4nowStartTime?posit4nowStartTime:null);
		model.setPosit4nowYears(null!=posit4nowYears?posit4nowYears:null);
		model.setPositLevelCn(null!=positLevelCn && !"".equals(positLevelCn.trim())?positLevelCn.trim():null);
		model.setPositLevelCode(null!=positLevelCode && !"".equals(positLevelCode.trim())?positLevelCode.trim():null);
		model.setOnPrinPositStartTime(null!=onPrinPositStartTime?onPrinPositStartTime:null);
		model.setOnPrinPositYears(null!=onPrinPositYears?onPrinPositYears:null);
		model.setOnDeptPositStartTime(null!=onDeptPositStartTime?onDeptPositStartTime:null);
		model.setOnDeptPositYears(null!=onDeptPositYears?onDeptPositYears:null);
		model.setTitle4max(null!=title4max && !"".equals(title4max.trim())?title4max.trim():null);
		model.setTitle4maxGotTime(null!=title4maxGotTime?title4maxGotTime:null);
		model.setTitle4maxGotYears(null!=title4maxGotYears?title4maxGotYears:null);
		model.setTitle4maxClassCn(null!=title4maxClassCn && !"".equals(title4maxClassCn.trim())?title4maxClassCn.trim():null);
		model.setTitle4maxLevelCn(null!=title4maxLevelCn && !"".equals(title4maxLevelCn.trim())?title4maxLevelCn.trim():null);
		model.setAdjust4title(null!=adjust4title?adjust4title:null);
		model.setAdjust4posit(null!=adjust4posit?adjust4posit:null);
		model.setAdjust4edu(null!=adjust4edu?adjust4edu:null);
		model.setWagesStandardAft(null!=wagesStandardAft?wagesStandardAft:null);
		model.setAdjustProof(null!=adjustProof && !"".equals(adjustProof.trim())?adjustProof.trim():null);
		model.setAdjustDiffe(null!=adjustDiffe?adjustDiffe:null);
		model.setFormulaId(null!=formulaId?formulaId:null);
		model.setRecordId(null!=recordId?recordId:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[调资表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChSaAdjustSalaryModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		if(null == id)
		{
			stringBuffer.append("输入自增主键!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String name = request.getParameter(PARA_name);
		if(StringHelper.isNullOrEmpty(name))
		{
			stringBuffer.append("输入职工姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String cardNo = request.getParameter(PARA_cardNo);
		String wagesId = request.getParameter(PARA_wagesId);
		if(StringHelper.isNullOrEmpty(wagesId))
		{
			stringBuffer.append("输入人员编号（工资出账）!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wagesSeq = request.getParameter(PARA_wagesSeq);
		if(StringHelper.isNullOrEmpty(wagesSeq))
		{
			stringBuffer.append("输入工资账序号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wagesName = request.getParameter(PARA_wagesName);
		if(StringHelper.isNullOrEmpty(wagesName))
		{
			stringBuffer.append("输入工资账姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wagesModalityCn = request.getParameter(PARA_wagesModalityCn);
		if(StringHelper.isNullOrEmpty(wagesModalityCn))
		{
			stringBuffer.append("输入工资账用工形式!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wagesDepart = request.getParameter(PARA_wagesDepart);
		String wagesStandardBefStr = request.getParameter(PARA_wagesStandardBef);
		BigDecimal wagesStandardBef = !StringHelper.isNullOrEmpty(wagesStandardBefStr)?new BigDecimal(wagesStandardBefStr):null;
		String salaryAdjustTypeCn = request.getParameter(PARA_salaryAdjustTypeCn);
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String edu4max = request.getParameter(PARA_edu4max);
		String edu4first = request.getParameter(PARA_edu4first);
		String edu4wage = request.getParameter(PARA_edu4wage);
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.strToDate(firstWorkTimeStr, "yyyy-MM-dd"):null;
		String workedYearStr = request.getParameter(PARA_workedYear);
		Double workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Double.parseDouble(workedYearStr):null;
		String posit4nowCn = request.getParameter(PARA_posit4nowCn);
		String posit4nowStartTimeStr = request.getParameter(PARA_posit4nowStartTime);
		Date posit4nowStartTime = !StringHelper.isNullOrEmpty(posit4nowStartTimeStr)?DateTimeHelper.strToDate(posit4nowStartTimeStr, "yyyy-MM-dd"):null;
		String posit4nowYearsStr = request.getParameter(PARA_posit4nowYears);
		Double posit4nowYears = !StringHelper.isNullOrEmpty(posit4nowYearsStr)?Double.parseDouble(posit4nowYearsStr):null;
		String positLevelCn = request.getParameter(PARA_positLevelCn);
		String positLevelCode = request.getParameter(PARA_positLevelCode);
		String onPrinPositStartTimeStr = request.getParameter(PARA_onPrinPositStartTime);
		Date onPrinPositStartTime = !StringHelper.isNullOrEmpty(onPrinPositStartTimeStr)?DateTimeHelper.strToDate(onPrinPositStartTimeStr, "yyyy-MM-dd"):null;
		String onPrinPositYearsStr = request.getParameter(PARA_onPrinPositYears);
		Double onPrinPositYears = !StringHelper.isNullOrEmpty(onPrinPositYearsStr)?Double.parseDouble(onPrinPositYearsStr):null;
		String onDeptPositStartTimeStr = request.getParameter(PARA_onDeptPositStartTime);
		Date onDeptPositStartTime = !StringHelper.isNullOrEmpty(onDeptPositStartTimeStr)?DateTimeHelper.strToDate(onDeptPositStartTimeStr, "yyyy-MM-dd"):null;
		String onDeptPositYearsStr = request.getParameter(PARA_onDeptPositYears);
		Double onDeptPositYears = !StringHelper.isNullOrEmpty(onDeptPositYearsStr)?Double.parseDouble(onDeptPositYearsStr):null;
		String title4max = request.getParameter(PARA_title4max);
		String title4maxGotTimeStr = request.getParameter(PARA_title4maxGotTime);
		Date title4maxGotTime = !StringHelper.isNullOrEmpty(title4maxGotTimeStr)?DateTimeHelper.strToDate(title4maxGotTimeStr, "yyyy-MM-dd"):null;
		String title4maxGotYearsStr = request.getParameter(PARA_title4maxGotYears);
		Double title4maxGotYears = !StringHelper.isNullOrEmpty(title4maxGotYearsStr)?Double.parseDouble(title4maxGotYearsStr):null;
		String title4maxClassCn = request.getParameter(PARA_title4maxClassCn);
		String title4maxLevelCn = request.getParameter(PARA_title4maxLevelCn);
		String adjust4titleStr = request.getParameter(PARA_adjust4title);
		BigDecimal adjust4title = !StringHelper.isNullOrEmpty(adjust4titleStr)?new BigDecimal(adjust4titleStr):null;
		String adjust4positStr = request.getParameter(PARA_adjust4posit);
		BigDecimal adjust4posit = !StringHelper.isNullOrEmpty(adjust4positStr)?new BigDecimal(adjust4positStr):null;
		String adjust4eduStr = request.getParameter(PARA_adjust4edu);
		BigDecimal adjust4edu = !StringHelper.isNullOrEmpty(adjust4eduStr)?new BigDecimal(adjust4eduStr):null;
		String wagesStandardAftStr = request.getParameter(PARA_wagesStandardAft);
		BigDecimal wagesStandardAft = !StringHelper.isNullOrEmpty(wagesStandardAftStr)?new BigDecimal(wagesStandardAftStr):null;
		String adjustProof = request.getParameter(PARA_adjustProof);
		String adjustDiffeStr = request.getParameter(PARA_adjustDiffe);
		BigDecimal adjustDiffe = !StringHelper.isNullOrEmpty(adjustDiffeStr)?new BigDecimal(adjustDiffeStr):null;
		String formulaIdStr = request.getParameter(PARA_formulaId);
		Long formulaId = !StringHelper.isNullOrEmpty(formulaIdStr)?Long.parseLong(formulaIdStr):null;
		if(null == formulaId)
		{
			stringBuffer.append("输入公式ID!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String recordIdStr = request.getParameter(PARA_recordId);
		Long recordId = !StringHelper.isNullOrEmpty(recordIdStr)?Long.parseLong(recordIdStr):null;
		if(null == recordId)
		{
			stringBuffer.append("输入调资记录ID!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		if(null == status)
		{
			stringBuffer.append("输入状态 0：正常  1：已删除!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setWagesSeq(null!=wagesSeq && !"".equals(wagesSeq.trim())?wagesSeq.trim():null);
		model.setWagesName(null!=wagesName && !"".equals(wagesName.trim())?wagesName.trim():null);
		model.setWagesModalityCn(null!=wagesModalityCn && !"".equals(wagesModalityCn.trim())?wagesModalityCn.trim():null);
		model.setWagesDepart(null!=wagesDepart && !"".equals(wagesDepart.trim())?wagesDepart.trim():null);
		model.setWagesStandardBef(null!=wagesStandardBef?wagesStandardBef:null);
		model.setSalaryAdjustTypeCn(null!=salaryAdjustTypeCn && !"".equals(salaryAdjustTypeCn.trim())?salaryAdjustTypeCn.trim():null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setEdu4max(null!=edu4max && !"".equals(edu4max.trim())?edu4max.trim():null);
		model.setEdu4first(null!=edu4first && !"".equals(edu4first.trim())?edu4first.trim():null);
		model.setEdu4wage(null!=edu4wage && !"".equals(edu4wage.trim())?edu4wage.trim():null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setPosit4nowCn(null!=posit4nowCn && !"".equals(posit4nowCn.trim())?posit4nowCn.trim():null);
		model.setPosit4nowStartTime(null!=posit4nowStartTime?posit4nowStartTime:null);
		model.setPosit4nowYears(null!=posit4nowYears?posit4nowYears:null);
		model.setPositLevelCn(null!=positLevelCn && !"".equals(positLevelCn.trim())?positLevelCn.trim():null);
		model.setPositLevelCode(null!=positLevelCode && !"".equals(positLevelCode.trim())?positLevelCode.trim():null);
		model.setOnPrinPositStartTime(null!=onPrinPositStartTime?onPrinPositStartTime:null);
		model.setOnPrinPositYears(null!=onPrinPositYears?onPrinPositYears:null);
		model.setOnDeptPositStartTime(null!=onDeptPositStartTime?onDeptPositStartTime:null);
		model.setOnDeptPositYears(null!=onDeptPositYears?onDeptPositYears:null);
		model.setTitle4max(null!=title4max && !"".equals(title4max.trim())?title4max.trim():null);
		model.setTitle4maxGotTime(null!=title4maxGotTime?title4maxGotTime:null);
		model.setTitle4maxGotYears(null!=title4maxGotYears?title4maxGotYears:null);
		model.setTitle4maxClassCn(null!=title4maxClassCn && !"".equals(title4maxClassCn.trim())?title4maxClassCn.trim():null);
		model.setTitle4maxLevelCn(null!=title4maxLevelCn && !"".equals(title4maxLevelCn.trim())?title4maxLevelCn.trim():null);
		model.setAdjust4title(null!=adjust4title?adjust4title:null);
		model.setAdjust4posit(null!=adjust4posit?adjust4posit:null);
		model.setAdjust4edu(null!=adjust4edu?adjust4edu:null);
		model.setWagesStandardAft(null!=wagesStandardAft?wagesStandardAft:null);
		model.setAdjustProof(null!=adjustProof && !"".equals(adjustProof.trim())?adjustProof.trim():null);
		model.setAdjustDiffe(null!=adjustDiffe?adjustDiffe:null);
		model.setFormulaId(null!=formulaId?formulaId:null);
		model.setRecordId(null!=recordId?recordId:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
	}
	
	/**
	 * 增加操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChSaAdjustSalaryModel model = new ChSaAdjustSalaryModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chSaAdjustSalaryBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 导入文件
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "doImport.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doImport(HttpServletRequest request,@RequestParam MultipartFile[] file) {
		Map<String, Object> map = null;
		InputStream inputs = null;
		try {
			//获取当前登录人
			UserInfoModel loginUser = this.getLoginUser(request);
			if(null == loginUser) return LayAjaxHelper.fail("当前未处于登录状态，请重新登录!");
			String adjustMonth = request.getParameter(PARA_adjustMonth);
			if(StringHelper.isNullOrEmpty(adjustMonth))
				return LayAjaxHelper.fail("请选择调资年月!");
			
			//封装调资记录
			ChSaAdjustRecordModel recModel = new ChSaAdjustRecordModel();
			recModel.setAdjustMonth(adjustMonth);
			recModel.setAdjustAccount(loginUser.getAccount());
			recModel.setAdjustUser(loginUser.getUserRealName());
			
			String type = null;
			String fileName = null;
			//导入操作返回的结果数据
			Map<Boolean,Object> data = null;
        	for (MultipartFile item : file) {
        		fileName = item.getOriginalFilename();
        		type = null != fileName?fileName.substring(fileName.indexOf("."), fileName.length()):null;
        		if(!type.contains("xls") && !type.contains("xlsx"))
        			break;
        		inputs = item.getInputStream();
        		data = chSaAdjustSalaryBiz.doImport(inputs,recModel);
        	}
			log2Info(request, Module_Name, LogDictionary.Module_Oper_import,"编号:" +",名称:薪级分类");
			map =  data.containsKey(true)?LayAjaxHelper.success("调资成功！"):LayAjaxHelper.fail(String.valueOf(data.get(false)));
		} catch (Exception ex) {
			ex.printStackTrace();
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("导入失败！error:",ex);
		}finally {
			if(null != inputs)
				try {
					inputs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
		return map;
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) throw new Exception("编辑数据时，主键为空!");
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			
			ChSaAdjustSalaryModel model = chSaAdjustSalaryBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载调资表失败！error:",e);
		}
	}
	
	/**
	 *  编辑操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("id") Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChSaAdjustSalaryModel model = chSaAdjustSalaryBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chSaAdjustSalaryBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 删除操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("id")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("未检测到编号存在!");
			ChSaAdjustSalaryModel model = chSaAdjustSalaryBiz.queryById(id);
			if(model != null)
			{
				chSaAdjustSalaryBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model+",名称:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 删除操作
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			chSaAdjustSalaryBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 查看详细页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) throws Exception{
		ChSaAdjustSalaryModel model = null;
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			//加载详情对象
			model = chSaAdjustSalaryBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载调资表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
	
	/**
	 * 填充字典数据到回显Map
	 * @param id
	 * @param modelMap
	 */
	private void fillCommonDict(ModelMap modelMap) throws Exception
	{
		//字典数据加载
		List<String> codes = Arrays.asList(DICT_COMMON_CODES.split(","));
		Map<String,List<Map<String,Object>>> dictMap = dictBiz.queryItemListByCodes(codes);
		List<ComboxItem> comboxs = null;
		for(String dictCode:dictMap.keySet())
		{
			List<Map<String,Object>> list = dictMap.get(dictCode);
			comboxs = new ArrayList<ComboxItem>();
			for(Map<String,Object> item:list)
			{
				comboxs.add(new ComboxItem(item.get("item_key")+"",item.get("item_val")+""));
			}
			modelMap.addAttribute(dictCode+"List", comboxs);
		}
	}
	
	/**
	 * <p>Discription:[调资请求首页]</p>
	 * Created on 2021年05月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("showList.jhtm")
	public void showList(HttpServletRequest request,ModelMap modelMap) throws Exception{
		String recId = request.getParameter(PARA_recordId);
		Long recordId = !StringHelper.isNullOrEmpty(recId)?Long.parseLong(recId):null;
		modelMap.put(PARA_recordId, recordId);
	}
	 
	/**
	 * <p>Discription:[调资表首页数据加载-分页]</p>
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "showList/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaAdjustSalaryModel> doShowList(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSaAdjustSalaryModel> retBody = new Result4Page<ChSaAdjustSalaryModel>();
		//查询参数
		Page.Builder<ChSaAdjustSalaryModel> builder = new Page.Builder<ChSaAdjustSalaryModel>();
		setSearchParameters(request, builder);
		Page<ChSaAdjustSalaryModel> page = builder.build();
		ChSaAdjustSalaryModel model = page.getModel();
		Long recId = model.getRecordId();
		if(null == recId)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("请求参数中缺失调资记录编号!");
			return retBody;
		}
		try {
			//分页
			chSaAdjustSalaryBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		return retBody;
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
				fvallist = chSaAdjustSalaryBiz.loadSelectFields(column);
//				if(null!=fValues)
//					fvallist = Arrays.asList(fValues.split(","));
//				else
//					fvallist = new ArrayList<String>();
				if(null == fvallist)fvallist = new ArrayList<String>();
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
}
