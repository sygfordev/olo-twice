package com.hdfs.olo.olo_web.personnel.controller;

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
import com.hdfs.olo.olo_web.personnel.biz.IChPmPositHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmPositInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositInfoModel;
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

/** 
 * Description: [职务信息表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmPositInfo/*")
public class ChPmPositInfoController extends BaseController {
	
	public final static String Module_Name = "职务信息表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmPositHeadBiz chPmPositHeadBiz;
	@Autowired
	private IChPmPositInfoBiz chPmPositInfoBiz;
	
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_posit = "posit";
	private	final static String PARA_positCn = "positCn";
	private	final static String PARA_positDepart = "positDepart";
	private	final static String PARA_positLevel = "positLevel";
	private	final static String PARA_positLevelCn = "positLevelCn";
	private	final static String PARA_positMax = "positMax";
	private	final static String PARA_onPositOrder = "onPositOrder";
	private	final static String PARA_onPositStartTime = "onPositStartTime";
	private	final static String PARA_onPositEndTime = "onPositEndTime";
	private	final static String PARA_onPositDocno = "onPositDocno";
	private	final static String PARA_conPosit = "conPosit";
	private	final static String PARA_conPositCn = "conPositCn";
	private	final static String PARA_conPositDepart = "conPositDepart";
	private	final static String PARA_conPositStartTime = "conPositStartTime";
	private	final static String PARA_conPositEndTime = "conPositEndTime";
	private	final static String PARA_treatLevel = "treatLevel";
	private	final static String PARA_treatLevelCn = "treatLevelCn";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,YN,posit,positLevel";
	
	/**
	 * <p>Discription:[职务信息表请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//加载请求参数并返回页面
		String wkIdStr = request.getParameter("wkId");
		Long wkId = !StringHelper.isNullOrEmpty(wkIdStr)?Long.parseLong(wkIdStr):null;
		if(null == wkId) throw new Exception("职工编号为空");
		modelMap.addAttribute("wkId", wkId);
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("oper", oper);
		
		//加载学历现信息
		ChPmPositHeadModel head = chPmPositHeadBiz.queryByWkId(wkId);
		modelMap.addAttribute("model", head);
				
		//字典数据加载
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[职务信息表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmPositInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmPositInfoModel> retBody = new Result4Page<ChPmPositInfoModel>();
		//查询参数
		Page.Builder<ChPmPositInfoModel> builder = new Page.Builder<ChPmPositInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmPositInfoModel> page = builder.build();
		try {
			//分页
			chPmPositInfoBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			logger.error("查询失败！error:",e);
		}
		return retBody;
	}

	/**
	 * <p>Discription:[职务信息表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmPositInfoModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		String positStr = request.getParameter(PARA_posit);
		Integer posit = !StringHelper.isNullOrEmpty(positStr)?Integer.parseInt(positStr):null;
		String positCn = request.getParameter(PARA_positCn);
		String positDepart = request.getParameter(PARA_positDepart);
		String positLevelStr = request.getParameter(PARA_positLevel);
		Integer positLevel = !StringHelper.isNullOrEmpty(positLevelStr)?Integer.parseInt(positLevelStr):null;
		String positLevelCn = request.getParameter(PARA_positLevelCn);
		String positMaxStr = request.getParameter(PARA_positMax);
		Integer positMax = !StringHelper.isNullOrEmpty(positMaxStr)?Integer.parseInt(positMaxStr):null;
		String onPositOrderStr = request.getParameter(PARA_onPositOrder);
		Integer onPositOrder = !StringHelper.isNullOrEmpty(onPositOrderStr)?Integer.parseInt(onPositOrderStr):null;
		String onPositStartTimeStr = request.getParameter(PARA_onPositStartTime);
		Date onPositStartTime = !StringHelper.isNullOrEmpty(onPositStartTimeStr)?DateTimeHelper.strToDate(onPositStartTimeStr, "yyyy-MM-dd"):null;
		String onPositEndTimeStr = request.getParameter(PARA_onPositEndTime);
		Date onPositEndTime = !StringHelper.isNullOrEmpty(onPositEndTimeStr)?DateTimeHelper.strToDate(onPositEndTimeStr, "yyyy-MM-dd"):null;
		String onPositDocno = request.getParameter(PARA_onPositDocno);
		String conPositStr = request.getParameter(PARA_conPosit);
		Integer conPosit = !StringHelper.isNullOrEmpty(conPositStr)?Integer.parseInt(conPositStr):null;
		String conPositCn = request.getParameter(PARA_conPositCn);
		String conPositDepart = request.getParameter(PARA_conPositDepart);
		String conPositStartTimeStr = request.getParameter(PARA_conPositStartTime);
		Date conPositStartTime = !StringHelper.isNullOrEmpty(conPositStartTimeStr)?DateTimeHelper.strToDate(conPositStartTimeStr, "yyyy-MM-dd"):null;
		String conPositEndTimeStr = request.getParameter(PARA_conPositEndTime);
		Date conPositEndTime = !StringHelper.isNullOrEmpty(conPositEndTimeStr)?DateTimeHelper.strToDate(conPositEndTimeStr, "yyyy-MM-dd"):null;
		String treatLevelStr = request.getParameter(PARA_treatLevel);
		Integer treatLevel = !StringHelper.isNullOrEmpty(treatLevelStr)?Integer.parseInt(treatLevelStr):null;
		String treatLevelCn = request.getParameter(PARA_treatLevelCn);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmPositInfoModel model = new ChPmPositInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setPosit(null!=posit?posit:null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setPositDepart(null!=positDepart && !"".equals(positDepart.trim())?positDepart.trim():null);
		model.setPositLevel(null!=positLevel?positLevel:null);
		model.setPositLevelCn(null!=positLevelCn && !"".equals(positLevelCn.trim())?positLevelCn.trim():null);
		model.setPositMax(null!=positMax?positMax:null);
		model.setOnPositOrder(null!=onPositOrder?onPositOrder:null);
		model.setOnPositStartTime(null!=onPositStartTime?onPositStartTime:null);
		model.setOnPositEndTime(null!=onPositEndTime?onPositEndTime:null);
		model.setOnPositDocno(null!=onPositDocno && !"".equals(onPositDocno.trim())?onPositDocno.trim():null);
		model.setConPosit(null!=conPosit?conPosit:null);
		model.setConPositCn(null!=conPositCn && !"".equals(conPositCn.trim())?conPositCn.trim():null);
		model.setConPositDepart(null!=conPositDepart && !"".equals(conPositDepart.trim())?conPositDepart.trim():null);
		model.setConPositStartTime(null!=conPositStartTime?conPositStartTime:null);
		model.setConPositEndTime(null!=conPositEndTime?conPositEndTime:null);
		model.setTreatLevel(null!=treatLevel?treatLevel:null);
		model.setTreatLevelCn(null!=treatLevelCn && !"".equals(treatLevelCn.trim())?treatLevelCn.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[职务信息表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmPositInfoModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		if(null == workerId)
		{
			stringBuffer.append("输入职工编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String positStr = request.getParameter(PARA_posit);
		Integer posit = !StringHelper.isNullOrEmpty(positStr)?Integer.parseInt(positStr):null;
		if(null == posit)
		{
			stringBuffer.append("输入职务!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String positCn = request.getParameter(PARA_positCn);
		String positDepart = request.getParameter(PARA_positDepart);
//		if(StringHelper.isNullOrEmpty(positDepart))
//		{
//			stringBuffer.append("输入任职部门!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String positLevelStr = request.getParameter(PARA_positLevel);
		Integer positLevel = !StringHelper.isNullOrEmpty(positLevelStr)?Integer.parseInt(positLevelStr):null;
//		if(null == positLevel)
//		{
//			stringBuffer.append("输入行政级别!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String positLevelCn = request.getParameter(PARA_positLevelCn);
		String positMaxStr = request.getParameter(PARA_positMax);
		Integer positMax = !StringHelper.isNullOrEmpty(positMaxStr)?Integer.parseInt(positMaxStr):null;
		if(null == positMax)
		{
			stringBuffer.append("输入是否最高职务!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String onPositOrderStr = request.getParameter(PARA_onPositOrder);
		Integer onPositOrder = !StringHelper.isNullOrEmpty(onPositOrderStr)?Integer.parseInt(onPositOrderStr):null;
		String onPositStartTimeStr = request.getParameter(PARA_onPositStartTime);
		Date onPositStartTime = !StringHelper.isNullOrEmpty(onPositStartTimeStr)?DateTimeHelper.strToDate(onPositStartTimeStr, "yyyy-MM-dd"):null;
		String onPositEndTimeStr = request.getParameter(PARA_onPositEndTime);
		Date onPositEndTime = !StringHelper.isNullOrEmpty(onPositEndTimeStr)?DateTimeHelper.strToDate(onPositEndTimeStr, "yyyy-MM-dd"):null;
		String onPositDocno = request.getParameter(PARA_onPositDocno);
//		if(StringHelper.isNullOrEmpty(onPositDocno))
//		{
//			stringBuffer.append("输入任职文号!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String conPositStr = request.getParameter(PARA_conPosit);
		Integer conPosit = !StringHelper.isNullOrEmpty(conPositStr)?Integer.parseInt(conPositStr):null;
		String conPositCn = request.getParameter(PARA_conPositCn);
		String conPositDepart = request.getParameter(PARA_conPositDepart);
		String conPositStartTimeStr = request.getParameter(PARA_conPositStartTime);
		Date conPositStartTime = !StringHelper.isNullOrEmpty(conPositStartTimeStr)?DateTimeHelper.strToDate(conPositStartTimeStr, "yyyy-MM-dd"):null;
		String conPositEndTimeStr = request.getParameter(PARA_conPositEndTime);
		Date conPositEndTime = !StringHelper.isNullOrEmpty(conPositEndTimeStr)?DateTimeHelper.strToDate(conPositEndTimeStr, "yyyy-MM-dd"):null;
		String treatLevelStr = request.getParameter(PARA_treatLevel);
		Integer treatLevel = !StringHelper.isNullOrEmpty(treatLevelStr)?Integer.parseInt(treatLevelStr):null;
//		if(null == treatLevel)
//		{
//			stringBuffer.append("输入待遇级别!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String treatLevelCn = request.getParameter(PARA_treatLevelCn);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setPosit(null!=posit?posit:null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setPositDepart(null!=positDepart && !"".equals(positDepart.trim())?positDepart.trim():null);
		model.setPositLevel(null!=positLevel?positLevel:null);
		model.setPositLevelCn(null!=positLevelCn && !"".equals(positLevelCn.trim())?positLevelCn.trim():null);
		model.setPositMax(null!=positMax?positMax:null);
		model.setOnPositOrder(null!=onPositOrder?onPositOrder:null);
		model.setOnPositStartTime(null!=onPositStartTime?onPositStartTime:null);
		model.setOnPositEndTime(null!=onPositEndTime?onPositEndTime:null);
		model.setOnPositDocno(null!=onPositDocno && !"".equals(onPositDocno.trim())?onPositDocno.trim():null);
		model.setConPosit(null!=conPosit?conPosit:null);
		model.setConPositCn(null!=conPositCn && !"".equals(conPositCn.trim())?conPositCn.trim():null);
		model.setConPositDepart(null!=conPositDepart && !"".equals(conPositDepart.trim())?conPositDepart.trim():null);
		model.setConPositStartTime(null!=conPositStartTime?conPositStartTime:null);
		model.setConPositEndTime(null!=conPositEndTime?conPositEndTime:null);
		model.setTreatLevel(null!=treatLevel?treatLevel:null);
		model.setTreatLevelCn(null!=treatLevelCn && !"".equals(treatLevelCn.trim())?treatLevelCn.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap,Long wkId) throws Exception{
		if(null == wkId) throw new Exception("职工编号不存在！");
		modelMap.addAttribute("wkId", wkId);
		//字典数据加载
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
			ChPmPositInfoModel model = new ChPmPositInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			
			//校验是否存在最高职务
			if(DictionaryUtil.KEY_NOT_NORMAL == model.getPositMax())
			{
				ChPmPositInfoModel tmp = new ChPmPositInfoModel();
				tmp.setStatus(DictionaryUtil.KEY_NORMAL);
				tmp.setPositMax(DictionaryUtil.KEY_NOT_NORMAL);
				tmp.setWorkerId(model.getWorkerId());
				Long count = chPmPositInfoBiz.queryCount(tmp);
				if(count>0) return LayAjaxHelper.fail("已存在最高职务！");
			}
			
			//增加
			chPmPositInfoBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("保存失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			//字典数据加载
			fillCommonDict(modelMap);
			
			ChPmPositInfoModel model = chPmPositInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载职务信息表失败！error:",e);
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
			ChPmPositInfoModel model = chPmPositInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmPositInfoBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("编辑失败！error:",ex);
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
			ChPmPositInfoModel model = chPmPositInfoBiz.queryById(id);
			if(model != null)
			{
				chPmPositInfoBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model+",名称:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("删除失败！error:",ex);
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
			chPmPositInfoBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("批量删除失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 查看详细页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		ChPmPositInfoModel model = null;
		try {
			model = chPmPositInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载职务信息表失败！error:",e);
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
