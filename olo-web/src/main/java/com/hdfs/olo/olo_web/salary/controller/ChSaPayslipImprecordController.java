package com.hdfs.olo.olo_web.salary.controller;

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

import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
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
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipImprecordBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipImprecordModel;

/** 
 * Description: [薪资-工资条导入记录服务实现]
 * Created on 2021年05月16日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/central/chSaPayslipImprecord/*")
public class ChSaPayslipImprecordController extends BaseController {
	
	public final static String Module_Name = "薪资-工资条导入记录";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChSaPayslipImprecordBiz chSaPayslipImprecordBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_batchNo = "batchNo";
	private	final static String PARA_batchType = "batchType";
	private	final static String PARA_batchUser = "batchUser";
	private	final static String PARA_sucNum = "sucNum";
	private	final static String PARA_faiNum = "faiNum";
	private	final static String PARA_exeNum = "exeNum";
	private	final static String PARA_existNum = "existNum";
	private	final static String PARA_batchMsg = "batchMsg";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "status,YN";
	
	/**
	 * <p>Discription:[薪资-工资条导入记录请求首页]</p>
	 * Created on 2021年05月16日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[薪资-工资条导入记录首页数据加载-分页]</p>
	 * Created on 2021年05月16日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaPayslipImprecordModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSaPayslipImprecordModel> retBody = new Result4Page<ChSaPayslipImprecordModel>();
		//查询参数
		Page.Builder<ChSaPayslipImprecordModel> builder = new Page.Builder<ChSaPayslipImprecordModel>();
		setSearchParameters(request, builder);
		Page<ChSaPayslipImprecordModel> page = builder.build();
		try {
			//分页
			chSaPayslipImprecordBiz.queryPage(page);
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
	 * <p>Discription:[薪资-工资条导入记录设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年05月16日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaPayslipImprecordModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String batchNo = request.getParameter(PARA_batchNo);
		String batchType = request.getParameter(PARA_batchType);
		String batchUser = request.getParameter(PARA_batchUser);
		String sucNumStr = request.getParameter(PARA_sucNum);
		Long sucNum = !StringHelper.isNullOrEmpty(sucNumStr)?Long.parseLong(sucNumStr):null;
		String faiNumStr = request.getParameter(PARA_faiNum);
		Long faiNum = !StringHelper.isNullOrEmpty(faiNumStr)?Long.parseLong(faiNumStr):null;
		String exeNumStr = request.getParameter(PARA_exeNum);
		Long exeNum = !StringHelper.isNullOrEmpty(exeNumStr)?Long.parseLong(exeNumStr):null;
		String existNumStr = request.getParameter(PARA_existNum);
		Long existNum = !StringHelper.isNullOrEmpty(existNumStr)?Long.parseLong(existNumStr):null;
		String batchMsg = request.getParameter(PARA_batchMsg);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChSaPayslipImprecordModel model = new ChSaPayslipImprecordModel();
		model.setId(null!=id?id:null);
		model.setBatchNo(null!=batchNo && !"".equals(batchNo.trim())?batchNo.trim():null);
		model.setBatchType(null!=batchType && !"".equals(batchType.trim())?batchType.trim():null);
		model.setBatchUser(null!=batchUser && !"".equals(batchUser.trim())?batchUser.trim():null);
		model.setSucNum(null!=sucNum?sucNum:null);
		model.setFaiNum(null!=faiNum?faiNum:null);
		model.setExeNum(null!=exeNum?exeNum:null);
		model.setExistNum(null!=existNum?existNum:null);
		model.setBatchMsg(null!=batchMsg && !"".equals(batchMsg.trim())?batchMsg.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[薪资-工资条导入记录校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年05月16日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChSaPayslipImprecordModel model) {
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
		String batchNo = request.getParameter(PARA_batchNo);
		if(StringHelper.isNullOrEmpty(batchNo))
		{
			stringBuffer.append("输入批次号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String batchType = request.getParameter(PARA_batchType);
		String batchUser = request.getParameter(PARA_batchUser);
		String sucNumStr = request.getParameter(PARA_sucNum);
		Long sucNum = !StringHelper.isNullOrEmpty(sucNumStr)?Long.parseLong(sucNumStr):null;
		String faiNumStr = request.getParameter(PARA_faiNum);
		Long faiNum = !StringHelper.isNullOrEmpty(faiNumStr)?Long.parseLong(faiNumStr):null;
		String exeNumStr = request.getParameter(PARA_exeNum);
		Long exeNum = !StringHelper.isNullOrEmpty(exeNumStr)?Long.parseLong(exeNumStr):null;
		String existNumStr = request.getParameter(PARA_existNum);
		Long existNum = !StringHelper.isNullOrEmpty(existNumStr)?Long.parseLong(existNumStr):null;
		String batchMsg = request.getParameter(PARA_batchMsg);
		if(StringHelper.isNullOrEmpty(batchMsg))
		{
			stringBuffer.append("输入批次消息!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		if(null == status)
		{
			stringBuffer.append("输入状态 0：校验失败  1：导入成功!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setBatchNo(null!=batchNo && !"".equals(batchNo.trim())?batchNo.trim():null);
		model.setBatchType(null!=batchType && !"".equals(batchType.trim())?batchType.trim():null);
		model.setBatchUser(null!=batchUser && !"".equals(batchUser.trim())?batchUser.trim():null);
		model.setSucNum(null!=sucNum?sucNum:null);
		model.setFaiNum(null!=faiNum?faiNum:null);
		model.setExeNum(null!=exeNum?exeNum:null);
		model.setExistNum(null!=existNum?existNum:null);
		model.setBatchMsg(null!=batchMsg && !"".equals(batchMsg.trim())?batchMsg.trim():null);
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
			ChSaPayslipImprecordModel model = new ChSaPayslipImprecordModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chSaPayslipImprecordBiz.save(model);
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
			
			ChSaPayslipImprecordModel model = chSaPayslipImprecordBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载薪资-工资条导入记录失败！error:",e);
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
			ChSaPayslipImprecordModel model = chSaPayslipImprecordBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chSaPayslipImprecordBiz.edit(model);
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
			ChSaPayslipImprecordModel model = chSaPayslipImprecordBiz.queryById(id);
			if(model != null)
			{
				chSaPayslipImprecordBiz.delById4Logic(id);
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
			chSaPayslipImprecordBiz.delByIds4Logic(ids);
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
		ChSaPayslipImprecordModel model = null;
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			//加载详情对象
			model = chSaPayslipImprecordBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载薪资-工资条导入记录失败！error:",e);
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
}
