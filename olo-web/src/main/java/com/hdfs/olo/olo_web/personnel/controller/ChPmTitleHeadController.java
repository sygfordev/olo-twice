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
import com.hdfs.olo.olo_web.personnel.biz.IChPmTitleHeadBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleHeadModel;
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
 * Description: [职称信息头表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmTitleHead/*")
public class ChPmTitleHeadController extends BaseController {
	
	public final static String Module_Name = "职称信息头表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmTitleHeadBiz chPmTitleHeadBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_title4now = "title4now";
	private	final static String PARA_title4nowCn = "title4nowCn";
	private	final static String PARA_title4nowGotTime = "title4nowGotTime";
	private	final static String PARA_title4nowGotYears = "title4nowGotYears";
	private	final static String PARA_titleClass4now = "titleClass4now";
	private	final static String PARA_titleClass4nowCn = "titleClass4nowCn";
	private	final static String PARA_titleLevel4now = "titleLevel4now";
	private	final static String PARA_titleLevel4nowCn = "titleLevel4nowCn";
	private	final static String PARA_treatLevel4now = "treatLevel4now";
	private	final static String PARA_treatLevel4nowCn = "treatLevel4nowCn";
	private	final static String PARA_title4nowHStartTime = "title4nowHStartTime";
	private	final static String PARA_title4nowHEndTime = "title4nowHEndTime";
	private	final static String PARA_titleMax4now = "titleMax4now";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	/**
	 * <p>Discription:[职称信息头表请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[职称信息头表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmTitleHeadModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmTitleHeadModel> retBody = new Result4Page<ChPmTitleHeadModel>();
		//查询参数
		Page.Builder<ChPmTitleHeadModel> builder = new Page.Builder<ChPmTitleHeadModel>();
		setSearchParameters(request, builder);
		Page<ChPmTitleHeadModel> page = builder.build();
		try {
			//分页
			chPmTitleHeadBiz.queryPage(page);
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
	 * <p>Discription:[职称信息头表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmTitleHeadModel> builder) {
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
		String title4nowStr = request.getParameter(PARA_title4now);
		Integer title4now = !StringHelper.isNullOrEmpty(title4nowStr)?Integer.parseInt(title4nowStr):null;
		String title4nowCn = request.getParameter(PARA_title4nowCn);
		String title4nowGotTimeStr = request.getParameter(PARA_title4nowGotTime);
		Date title4nowGotTime = !StringHelper.isNullOrEmpty(title4nowGotTimeStr)?DateTimeHelper.strToDate(title4nowGotTimeStr, "yyyy-MM-dd"):null;
		String title4nowGotYearsStr = request.getParameter(PARA_title4nowGotYears);
		Integer title4nowGotYears = !StringHelper.isNullOrEmpty(title4nowGotYearsStr)?Integer.parseInt(title4nowGotYearsStr):null;
		String titleClass4nowStr = request.getParameter(PARA_titleClass4now);
		Integer titleClass4now = !StringHelper.isNullOrEmpty(titleClass4nowStr)?Integer.parseInt(titleClass4nowStr):null;
		String titleClass4nowCn = request.getParameter(PARA_titleClass4nowCn);
		String titleLevel4now = request.getParameter(PARA_titleLevel4now);
		String titleLevel4nowCn = request.getParameter(PARA_titleLevel4nowCn);
		String treatLevel4now = request.getParameter(PARA_treatLevel4now);
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String title4nowHStartTimeStr = request.getParameter(PARA_title4nowHStartTime);
		Date title4nowHStartTime = !StringHelper.isNullOrEmpty(title4nowHStartTimeStr)?DateTimeHelper.strToDate(title4nowHStartTimeStr, "yyyy-MM-dd"):null;
		String title4nowHEndTimeStr = request.getParameter(PARA_title4nowHEndTime);
		Date title4nowHEndTime = !StringHelper.isNullOrEmpty(title4nowHEndTimeStr)?DateTimeHelper.strToDate(title4nowHEndTimeStr, "yyyy-MM-dd"):null;
		String titleMax4nowStr = request.getParameter(PARA_titleMax4now);
		Integer titleMax4now = !StringHelper.isNullOrEmpty(titleMax4nowStr)?Integer.parseInt(titleMax4nowStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmTitleHeadModel model = new ChPmTitleHeadModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setTitle4now(null!=title4now?title4now:null);
		model.setTitle4nowCn(null!=title4nowCn && !"".equals(title4nowCn.trim())?title4nowCn.trim():null);
		model.setTitle4nowGotTime(null!=title4nowGotTime?title4nowGotTime:null);
		model.setTitle4nowGotYears(null!=title4nowGotYears?title4nowGotYears:null);
		model.setTitleClass4now(null!=titleClass4now?titleClass4now:null);
		model.setTitleClass4nowCn(null!=titleClass4nowCn && !"".equals(titleClass4nowCn.trim())?titleClass4nowCn.trim():null);
		model.setTitleLevel4now(null!=titleLevel4now && !"".equals(titleLevel4now.trim())?titleLevel4now.trim():null);
		model.setTitleLevel4nowCn(null!=titleLevel4nowCn && !"".equals(titleLevel4nowCn.trim())?titleLevel4nowCn.trim():null);
		model.setTreatLevel4now(null!=treatLevel4now && !"".equals(treatLevel4now.trim())?treatLevel4now.trim():null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setTitle4nowHStartTime(null!=title4nowHStartTime?title4nowHStartTime:null);
		model.setTitle4nowHEndTime(null!=title4nowHEndTime?title4nowHEndTime:null);
		model.setTitleMax4now(null!=titleMax4now?titleMax4now:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[职称信息头表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmTitleHeadModel model) {
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
		String title4nowStr = request.getParameter(PARA_title4now);
		Integer title4now = !StringHelper.isNullOrEmpty(title4nowStr)?Integer.parseInt(title4nowStr):null;
		if(null == title4now)
		{
			stringBuffer.append("输入现职称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String title4nowCn = request.getParameter(PARA_title4nowCn);
		String title4nowGotTimeStr = request.getParameter(PARA_title4nowGotTime);
		Date title4nowGotTime = !StringHelper.isNullOrEmpty(title4nowGotTimeStr)?DateTimeHelper.strToDate(title4nowGotTimeStr, "yyyy-MM-dd"):null;
		Integer title4nowGotYears = null;
		if(null != title4nowGotTime)
		{
			DayCompare c = CalendarUtil.dayComparePrecise(title4nowGotTime, new Date());
			title4nowGotYears = c.getYear()+1;
		}
		String titleClass4nowStr = request.getParameter(PARA_titleClass4now);
		Integer titleClass4now = !StringHelper.isNullOrEmpty(titleClass4nowStr)?Integer.parseInt(titleClass4nowStr):null;
		if(null == titleClass4now)
		{
			stringBuffer.append("输入现职称序列!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String titleClass4nowCn = request.getParameter(PARA_titleClass4nowCn);
		String titleLevel4now = request.getParameter(PARA_titleLevel4now);
		if(StringHelper.isNullOrEmpty(titleLevel4now))
		{
			stringBuffer.append("输入现职称级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String titleLevel4nowCn = request.getParameter(PARA_titleLevel4nowCn);
		String treatLevel4now = request.getParameter(PARA_treatLevel4now);
		if(StringHelper.isNullOrEmpty(treatLevel4now))
		{
			stringBuffer.append("输入现职称待遇级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String title4nowHStartTimeStr = request.getParameter(PARA_title4nowHStartTime);
		Date title4nowHStartTime = !StringHelper.isNullOrEmpty(title4nowHStartTimeStr)?DateTimeHelper.strToDate(title4nowHStartTimeStr, "yyyy-MM-dd"):null;
		String title4nowHEndTimeStr = request.getParameter(PARA_title4nowHEndTime);
		Date title4nowHEndTime = !StringHelper.isNullOrEmpty(title4nowHEndTimeStr)?DateTimeHelper.strToDate(title4nowHEndTimeStr, "yyyy-MM-dd"):null;
		String titleMax4nowStr = request.getParameter(PARA_titleMax4now);
		Integer titleMax4now = !StringHelper.isNullOrEmpty(titleMax4nowStr)?Integer.parseInt(titleMax4nowStr):null;
		if(null == titleMax4now)
		{
			stringBuffer.append("输入是否现最高职称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setTitle4now(null!=title4now?title4now:null);
		model.setTitle4nowCn(null!=title4nowCn && !"".equals(title4nowCn.trim())?title4nowCn.trim():null);
		model.setTitle4nowGotTime(null!=title4nowGotTime?title4nowGotTime:null);
		model.setTitle4nowGotYears(null!=title4nowGotYears?title4nowGotYears:null);
		model.setTitleClass4now(null!=titleClass4now?titleClass4now:null);
		model.setTitleClass4nowCn(null!=titleClass4nowCn && !"".equals(titleClass4nowCn.trim())?titleClass4nowCn.trim():null);
		model.setTitleLevel4now(null!=titleLevel4now && !"".equals(titleLevel4now.trim())?titleLevel4now.trim():null);
		model.setTitleLevel4nowCn(null!=titleLevel4nowCn && !"".equals(titleLevel4nowCn.trim())?titleLevel4nowCn.trim():null);
		model.setTreatLevel4now(null!=treatLevel4now && !"".equals(treatLevel4now.trim())?treatLevel4now.trim():null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setTitle4nowHStartTime(null!=title4nowHStartTime?title4nowHStartTime:null);
		model.setTitle4nowHEndTime(null!=title4nowHEndTime?title4nowHEndTime:null);
		model.setTitleMax4now(null!=titleMax4now?titleMax4now:null);
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
			ChPmTitleHeadModel model = new ChPmTitleHeadModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//判断该职工的职务Head信息是否存在
			ChPmTitleHeadModel head = chPmTitleHeadBiz.queryByWkId(model.getWorkerId());
			if(null != head) return LayAjaxHelper.fail("该职工的现职务信息已存在！");
			//增加
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			chPmTitleHeadBiz.save(model);
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
			
			ChPmTitleHeadModel model = chPmTitleHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载职称信息头表失败！error:",e);
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
			ChPmTitleHeadModel model = null;
			ChPmTitleHeadModel model1 = chPmTitleHeadBiz.queryByWkId(wkId);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmTitleHeadModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(null != model1)
				chPmTitleHeadBiz.edit(model);
			else
				chPmTitleHeadBiz.save(model);
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
			ChPmTitleHeadModel model = chPmTitleHeadBiz.queryById(id);
			if(model != null)
			{
				chPmTitleHeadBiz.delById4Logic(id);
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
			chPmTitleHeadBiz.delByIds4Logic(ids);
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
		ChPmTitleHeadModel model = null;
		try {
			model = chPmTitleHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载职称信息头表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
