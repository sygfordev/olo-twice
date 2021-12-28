package com.hdfs.olo.olo_web.personnel.controller;

import java.text.SimpleDateFormat;
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

import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmPositHeadBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.CalendarUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.DayCompare;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [职务信息头表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmPositHead/*")
public class ChPmPositHeadController extends BaseController {
	
	public final static String Module_Name = "职务信息头表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmPositHeadBiz chPmPositHeadBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_posit4now = "posit4now";
	private	final static String PARA_posit4nowCn = "posit4nowCn";
	private	final static String PARA_positDepart4now = "positDepart4now";
	private	final static String PARA_positLevel4now = "positLevel4now";
	private	final static String PARA_positLevel4nowCn = "positLevel4nowCn";
	private	final static String PARA_posit4nowStartTime = "posit4nowStartTime";
	private	final static String PARA_posit4nowEndTime = "posit4nowEndTime";
	private	final static String PARA_treatLevel4now = "treatLevel4now";
	private	final static String PARA_treatLevel4nowCn = "treatLevel4nowCn";
	private	final static String PARA_treat4nowStartTime = "treat4nowStartTime";
	private	final static String PARA_treat4nowEndTime = "treat4nowEndTime";
	private	final static String PARA_treat4nowYears = "treat4nowYears";
	private	final static String PARA_onChuStartTime = "onChuStartTime";
	private	final static String PARA_onChuEndTime = "onChuEndTime";
	private	final static String PARA_onChuDetupyStartTime = "onChuDetupyStartTime";
	private	final static String PARA_onChuDetupyEndTime = "onChuDetupyEndTime";
	private	final static String PARA_onKeStartTime = "onKeStartTime";
	private	final static String PARA_onKeEndTime = "onKeEndTime";
	private	final static String PARA_onKeDetupyStartTime = "onKeDetupyStartTime";
	private	final static String PARA_onKeDetupyEndTime = "onKeDetupyEndTime";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	/**
	 * <p>Discription:[职务信息头表请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[职务信息头表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmPositHeadModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmPositHeadModel> retBody = new Result4Page<ChPmPositHeadModel>();
		//查询参数
		Page.Builder<ChPmPositHeadModel> builder = new Page.Builder<ChPmPositHeadModel>();
		setSearchParameters(request, builder);
		Page<ChPmPositHeadModel> page = builder.build();
		try {
			//分页
			chPmPositHeadBiz.queryPage(page);
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
	 * <p>Discription:[职务信息头表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmPositHeadModel> builder) {
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
		String posit4nowStr = request.getParameter(PARA_posit4now);
		Integer posit4now = !StringHelper.isNullOrEmpty(posit4nowStr)?Integer.parseInt(posit4nowStr):null;
		String posit4nowCn = request.getParameter(PARA_posit4nowCn);
		String positDepart4now = request.getParameter(PARA_positDepart4now);
		String positLevel4nowStr = request.getParameter(PARA_positLevel4now);
		Integer positLevel4now = !StringHelper.isNullOrEmpty(positLevel4nowStr)?Integer.parseInt(positLevel4nowStr):null;
		String positLevel4nowCn = request.getParameter(PARA_positLevel4nowCn);
		String posit4nowStartTimeStr = request.getParameter(PARA_posit4nowStartTime);
		Date posit4nowStartTime = !StringHelper.isNullOrEmpty(posit4nowStartTimeStr)?DateTimeHelper.strToDate(posit4nowStartTimeStr, "yyyy-MM-dd"):null;
		String posit4nowEndTimeStr = request.getParameter(PARA_posit4nowEndTime);
		Date posit4nowEndTime = !StringHelper.isNullOrEmpty(posit4nowEndTimeStr)?DateTimeHelper.strToDate(posit4nowEndTimeStr, "yyyy-MM-dd"):null;
		String treatLevel4nowStr = request.getParameter(PARA_treatLevel4now);
		Integer treatLevel4now = !StringHelper.isNullOrEmpty(treatLevel4nowStr)?Integer.parseInt(treatLevel4nowStr):null;
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String treat4nowStartTimeStr = request.getParameter(PARA_treat4nowStartTime);
		Date treat4nowStartTime = !StringHelper.isNullOrEmpty(treat4nowStartTimeStr)?DateTimeHelper.strToDate(treat4nowStartTimeStr, "yyyy-MM-dd"):null;
		String treat4nowEndTimeStr = request.getParameter(PARA_treat4nowEndTime);
		Date treat4nowEndTime = !StringHelper.isNullOrEmpty(treat4nowEndTimeStr)?DateTimeHelper.strToDate(treat4nowEndTimeStr, "yyyy-MM-dd"):null;
		String treat4nowYearsStr = request.getParameter(PARA_treat4nowYears);
		Integer treat4nowYears = !StringHelper.isNullOrEmpty(treat4nowYearsStr)?Integer.parseInt(treat4nowYearsStr):null;
		String onChuStartTimeStr = request.getParameter(PARA_onChuStartTime);
		Date onChuStartTime = !StringHelper.isNullOrEmpty(onChuStartTimeStr)?DateTimeHelper.strToDate(onChuStartTimeStr, "yyyy-MM-dd"):null;
		String onChuEndTimeStr = request.getParameter(PARA_onChuEndTime);
		Date onChuEndTime = !StringHelper.isNullOrEmpty(onChuEndTimeStr)?DateTimeHelper.strToDate(onChuEndTimeStr, "yyyy-MM-dd"):null;
		String onChuDetupyStartTimeStr = request.getParameter(PARA_onChuDetupyStartTime);
		Date onChuDetupyStartTime = !StringHelper.isNullOrEmpty(onChuDetupyStartTimeStr)?DateTimeHelper.strToDate(onChuDetupyStartTimeStr, "yyyy-MM-dd"):null;
		String onChuDetupyEndTimeStr = request.getParameter(PARA_onChuDetupyEndTime);
		Date onChuDetupyEndTime = !StringHelper.isNullOrEmpty(onChuDetupyEndTimeStr)?DateTimeHelper.strToDate(onChuDetupyEndTimeStr, "yyyy-MM-dd"):null;
		String onKeStartTimeStr = request.getParameter(PARA_onKeStartTime);
		Date onKeStartTime = !StringHelper.isNullOrEmpty(onKeStartTimeStr)?DateTimeHelper.strToDate(onKeStartTimeStr, "yyyy-MM-dd"):null;
		String onKeEndTimeStr = request.getParameter(PARA_onKeEndTime);
		Date onKeEndTime = !StringHelper.isNullOrEmpty(onKeEndTimeStr)?DateTimeHelper.strToDate(onKeEndTimeStr, "yyyy-MM-dd"):null;
		String onKeDetupyStartTimeStr = request.getParameter(PARA_onKeDetupyStartTime);
		Date onKeDetupyStartTime = !StringHelper.isNullOrEmpty(onKeDetupyStartTimeStr)?DateTimeHelper.strToDate(onKeDetupyStartTimeStr, "yyyy-MM-dd"):null;
		String onKeDetupyEndTimeStr = request.getParameter(PARA_onKeDetupyEndTime);
		Date onKeDetupyEndTime = !StringHelper.isNullOrEmpty(onKeDetupyEndTimeStr)?DateTimeHelper.strToDate(onKeDetupyEndTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmPositHeadModel model = new ChPmPositHeadModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setPosit4now(null!=posit4now?posit4now:null);
		model.setPosit4nowCn(null!=posit4nowCn && !"".equals(posit4nowCn.trim())?posit4nowCn.trim():null);
		model.setPositDepart4now(null!=positDepart4now && !"".equals(positDepart4now.trim())?positDepart4now.trim():null);
		model.setPositLevel4now(null!=positLevel4now?positLevel4now:null);
		model.setPositLevel4nowCn(null!=positLevel4nowCn && !"".equals(positLevel4nowCn.trim())?positLevel4nowCn.trim():null);
		model.setPosit4nowStartTime(null!=posit4nowStartTime?posit4nowStartTime:null);
		model.setPosit4nowEndTime(null!=posit4nowEndTime?posit4nowEndTime:null);
		model.setTreatLevel4now(null!=treatLevel4now?treatLevel4now:null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setTreat4nowStartTime(null!=treat4nowStartTime?treat4nowStartTime:null);
		model.setTreat4nowEndTime(null!=treat4nowEndTime?treat4nowEndTime:null);
		model.setTreat4nowYears(null!=treat4nowYears?treat4nowYears:null);
		model.setOnChuStartTime(null!=onChuStartTime?onChuStartTime:null);
		model.setOnChuEndTime(null!=onChuEndTime?onChuEndTime:null);
		model.setOnChuDetupyStartTime(null!=onChuDetupyStartTime?onChuDetupyStartTime:null);
		model.setOnChuDetupyEndTime(null!=onChuDetupyEndTime?onChuDetupyEndTime:null);
		model.setOnKeStartTime(null!=onKeStartTime?onKeStartTime:null);
		model.setOnKeEndTime(null!=onKeEndTime?onKeEndTime:null);
		model.setOnKeDetupyStartTime(null!=onKeDetupyStartTime?onKeDetupyStartTime:null);
		model.setOnKeDetupyEndTime(null!=onKeDetupyEndTime?onKeDetupyEndTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[职务信息头表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmPositHeadModel model) {
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
		String posit4nowStr = request.getParameter(PARA_posit4now);
		Integer posit4now = !StringHelper.isNullOrEmpty(posit4nowStr)?Integer.parseInt(posit4nowStr):null;
		if(null == posit4now)
		{
			stringBuffer.append("输入现任职务!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String posit4nowCn = request.getParameter(PARA_posit4nowCn);
		String positDepart4now = request.getParameter(PARA_positDepart4now);
		if(StringHelper.isNullOrEmpty(positDepart4now))
		{
			stringBuffer.append("输入现任职部门!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String positLevel4nowStr = request.getParameter(PARA_positLevel4now);
		Integer positLevel4now = !StringHelper.isNullOrEmpty(positLevel4nowStr)?Integer.parseInt(positLevel4nowStr):null;
		if(null == positLevel4now)
		{
			stringBuffer.append("输入现级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String positLevel4nowCn = request.getParameter(PARA_positLevel4nowCn);
		String posit4nowStartTimeStr = request.getParameter(PARA_posit4nowStartTime);
		Date posit4nowStartTime = !StringHelper.isNullOrEmpty(posit4nowStartTimeStr)?DateTimeHelper.strToDate(posit4nowStartTimeStr, "yyyy-MM-dd"):null;
		String posit4nowEndTimeStr = request.getParameter(PARA_posit4nowEndTime);
		Date posit4nowEndTime = !StringHelper.isNullOrEmpty(posit4nowEndTimeStr)?DateTimeHelper.strToDate(posit4nowEndTimeStr, "yyyy-MM-dd"):null;
		String treatLevel4nowStr = request.getParameter(PARA_treatLevel4now);
		Integer treatLevel4now = !StringHelper.isNullOrEmpty(treatLevel4nowStr)?Integer.parseInt(treatLevel4nowStr):null;
		if(null == treatLevel4now)
		{
			stringBuffer.append("输入现待遇级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String treat4nowStartTimeStr = request.getParameter(PARA_treat4nowStartTime);
		Date treat4nowStartTime = !StringHelper.isNullOrEmpty(treat4nowStartTimeStr)?DateTimeHelper.strToDate(treat4nowStartTimeStr, "yyyy-MM-dd"):null;
		String treat4nowEndTimeStr = request.getParameter(PARA_treat4nowEndTime);
		Date treat4nowEndTime = !StringHelper.isNullOrEmpty(treat4nowEndTimeStr)?DateTimeHelper.strToDate(treat4nowEndTimeStr, "yyyy-MM-dd"):null;
		Integer treat4nowYears = null;
		if(null != treat4nowStartTime)
		{
			DayCompare c = CalendarUtil.dayComparePrecise(treat4nowStartTime, new Date());
			treat4nowYears = c.getYear()+1;
		}
		String onChuStartTimeStr = request.getParameter(PARA_onChuStartTime);
		Date onChuStartTime = !StringHelper.isNullOrEmpty(onChuStartTimeStr)?DateTimeHelper.strToDate(onChuStartTimeStr, "yyyy-MM-dd"):null;
		String onChuEndTimeStr = request.getParameter(PARA_onChuEndTime);
		Date onChuEndTime = !StringHelper.isNullOrEmpty(onChuEndTimeStr)?DateTimeHelper.strToDate(onChuEndTimeStr, "yyyy-MM-dd"):null;
		String onChuDetupyStartTimeStr = request.getParameter(PARA_onChuDetupyStartTime);
		Date onChuDetupyStartTime = !StringHelper.isNullOrEmpty(onChuDetupyStartTimeStr)?DateTimeHelper.strToDate(onChuDetupyStartTimeStr, "yyyy-MM-dd"):null;
		String onChuDetupyEndTimeStr = request.getParameter(PARA_onChuDetupyEndTime);
		Date onChuDetupyEndTime = !StringHelper.isNullOrEmpty(onChuDetupyEndTimeStr)?DateTimeHelper.strToDate(onChuDetupyEndTimeStr, "yyyy-MM-dd"):null;
		String onKeStartTimeStr = request.getParameter(PARA_onKeStartTime);
		Date onKeStartTime = !StringHelper.isNullOrEmpty(onKeStartTimeStr)?DateTimeHelper.strToDate(onKeStartTimeStr, "yyyy-MM-dd"):null;
		String onKeEndTimeStr = request.getParameter(PARA_onKeEndTime);
		Date onKeEndTime = !StringHelper.isNullOrEmpty(onKeEndTimeStr)?DateTimeHelper.strToDate(onKeEndTimeStr, "yyyy-MM-dd"):null;
		String onKeDetupyStartTimeStr = request.getParameter(PARA_onKeDetupyStartTime);
		Date onKeDetupyStartTime = !StringHelper.isNullOrEmpty(onKeDetupyStartTimeStr)?DateTimeHelper.strToDate(onKeDetupyStartTimeStr, "yyyy-MM-dd"):null;
		String onKeDetupyEndTimeStr = request.getParameter(PARA_onKeDetupyEndTime);
		Date onKeDetupyEndTime = !StringHelper.isNullOrEmpty(onKeDetupyEndTimeStr)?DateTimeHelper.strToDate(onKeDetupyEndTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setPosit4now(null!=posit4now?posit4now:null);
		model.setPosit4nowCn(null!=posit4nowCn && !"".equals(posit4nowCn.trim())?posit4nowCn.trim():null);
		model.setPositDepart4now(null!=positDepart4now && !"".equals(positDepart4now.trim())?positDepart4now.trim():null);
		model.setPositLevel4now(null!=positLevel4now?positLevel4now:null);
		model.setPositLevel4nowCn(null!=positLevel4nowCn && !"".equals(positLevel4nowCn.trim())?positLevel4nowCn.trim():null);
		model.setPosit4nowStartTime(null!=posit4nowStartTime?posit4nowStartTime:null);
		model.setPosit4nowEndTime(null!=posit4nowEndTime?posit4nowEndTime:null);
		model.setTreatLevel4now(null!=treatLevel4now?treatLevel4now:null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setTreat4nowStartTime(null!=treat4nowStartTime?treat4nowStartTime:null);
		model.setTreat4nowEndTime(null!=treat4nowEndTime?treat4nowEndTime:null);
		model.setTreat4nowYears(null!=treat4nowYears?treat4nowYears:null);
		model.setOnChuStartTime(null!=onChuStartTime?onChuStartTime:null);
		model.setOnChuEndTime(null!=onChuEndTime?onChuEndTime:null);
		model.setOnChuDetupyStartTime(null!=onChuDetupyStartTime?onChuDetupyStartTime:null);
		model.setOnChuDetupyEndTime(null!=onChuDetupyEndTime?onChuDetupyEndTime:null);
		model.setOnKeStartTime(null!=onKeStartTime?onKeStartTime:null);
		model.setOnKeEndTime(null!=onKeEndTime?onKeEndTime:null);
		model.setOnKeDetupyStartTime(null!=onKeDetupyStartTime?onKeDetupyStartTime:null);
		model.setOnKeDetupyEndTime(null!=onKeDetupyEndTime?onKeDetupyEndTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) {
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
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
			ChPmPositHeadModel model = new ChPmPositHeadModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//判断该职工的职务Head信息是否存在
			ChPmPositHeadModel head = chPmPositHeadBiz.queryByWkId(model.getWorkerId());
			if(null != head) return LayAjaxHelper.fail("该职工的现职务信息已存在！");
			//增加
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			chPmPositHeadBiz.save(model);
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
			List<ComboxItem> statusList = getStatusList();
			modelMap.addAttribute("statusList", statusList);
			
			ChPmPositHeadModel model = chPmPositHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载职务信息头表失败！error:",e);
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
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("workerId") Long wkId) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmPositHeadModel model = null;
			ChPmPositHeadModel model1 = chPmPositHeadBiz.queryByWkId(wkId);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmPositHeadModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(null != model1)
				chPmPositHeadBiz.edit(model);
			else
				chPmPositHeadBiz.save(model);
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
			ChPmPositHeadModel model = chPmPositHeadBiz.queryById(id);
			if(model != null)
			{
				chPmPositHeadBiz.delById4Logic(id);
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
			chPmPositHeadBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("批量失败！error:",ex);
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
		ChPmPositHeadModel model = null;
		try {
			model = chPmPositHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载职务信息头表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
