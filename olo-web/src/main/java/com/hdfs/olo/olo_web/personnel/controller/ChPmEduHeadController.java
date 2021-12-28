package com.hdfs.olo.olo_web.personnel.controller;

import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmEduHeadBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmDossierInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [教育Head表服务实现]
 * Created on 2021年04月05日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmEduHead/*")
public class ChPmEduHeadController extends BaseController {
	
	public final static String Module_Name = "教育Head表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmEduHeadBiz chPmEduHeadBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_eduDeg4now = "eduDeg4now";
	private	final static String PARA_eduDeg4nowCn = "eduDeg4nowCn";
	private	final static String PARA_eduLev4now = "eduLev4now";
	private	final static String PARA_eduLev4nowCn = "eduLev4nowCn";
	private	final static String PARA_eduLev4sal = "eduLev4sal";
	private	final static String PARA_eduLev4salCn = "eduLev4salCn";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	/**
	 * <p>Discription:[教育Head表请求首页]</p>
	 * Created on 2021年04月05日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[教育Head表首页数据加载-分页]</p>
	 * Created on 2021年04月05日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmEduHeadModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmEduHeadModel> retBody = new Result4Page<ChPmEduHeadModel>();
		//查询参数
		Page.Builder<ChPmEduHeadModel> builder = new Page.Builder<ChPmEduHeadModel>();
		setSearchParameters(request, builder);
		Page<ChPmEduHeadModel> page = builder.build();
		try {
			//分页
			chPmEduHeadBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			logger.error("查询失败!error:",e);
		}
		return retBody;
	}

	/**
	 * <p>Discription:[教育Head表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月05日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmEduHeadModel> builder) {
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
		String eduDeg4nowStr = request.getParameter(PARA_eduDeg4now);
		Integer eduDeg4now = !StringHelper.isNullOrEmpty(eduDeg4nowStr)?Integer.parseInt(eduDeg4nowStr):null;
		String eduDeg4nowCn = request.getParameter(PARA_eduDeg4nowCn);
		String eduLev4nowStr = request.getParameter(PARA_eduLev4now);
		Integer eduLev4now = !StringHelper.isNullOrEmpty(eduLev4nowStr)?Integer.parseInt(eduLev4nowStr):null;
		String eduLev4nowCn = request.getParameter(PARA_eduLev4nowCn);
		String eduLev4salStr = request.getParameter(PARA_eduLev4sal);
		Integer eduLev4sal = !StringHelper.isNullOrEmpty(eduLev4salStr)?Integer.parseInt(eduLev4salStr):null;
		String eduLev4salCn = request.getParameter(PARA_eduLev4salCn);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmEduHeadModel model = new ChPmEduHeadModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setEduDeg4now(null!=eduDeg4now?eduDeg4now:null);
		model.setEduDeg4nowCn(null!=eduDeg4nowCn && !"".equals(eduDeg4nowCn.trim())?eduDeg4nowCn.trim():null);
		model.setEduLev4now(null!=eduLev4now?eduLev4now:null);
		model.setEduLev4nowCn(null!=eduLev4nowCn && !"".equals(eduLev4nowCn.trim())?eduLev4nowCn.trim():null);
		model.setEduLev4sal(null!=eduLev4sal?eduLev4sal:null);
		model.setEduLev4salCn(null!=eduLev4salCn && !"".equals(eduLev4salCn.trim())?eduLev4salCn.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[教育Head表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月05日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmEduHeadModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
//		if(null == id)
//		{
//			stringBuffer.append("输入自增主键!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		if(null == workerId)
		{
			stringBuffer.append("输入职工编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String eduDeg4nowStr = request.getParameter(PARA_eduDeg4now);
		Integer eduDeg4now = !StringHelper.isNullOrEmpty(eduDeg4nowStr)?Integer.parseInt(eduDeg4nowStr):null;
		if(null == eduDeg4now)
		{
			stringBuffer.append("输入受教学位!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String eduDeg4nowCn = request.getParameter(PARA_eduDeg4nowCn);
		String eduLev4nowStr = request.getParameter(PARA_eduLev4now);
		Integer eduLev4now = !StringHelper.isNullOrEmpty(eduLev4nowStr)?Integer.parseInt(eduLev4nowStr):null;
		if(null == eduLev4now)
		{
			stringBuffer.append("输入学历（高中|大专|本科|硕士|博士等）!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String eduLev4nowCn = request.getParameter(PARA_eduLev4nowCn);
		String eduLev4salStr = request.getParameter(PARA_eduLev4sal);
		Integer eduLev4sal = !StringHelper.isNullOrEmpty(eduLev4salStr)?Integer.parseInt(eduLev4salStr):null;
		if(null == eduLev4sal)
		{
			stringBuffer.append("输入工资学历!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String eduLev4salCn = request.getParameter(PARA_eduLev4salCn);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setEduDeg4now(null!=eduDeg4now?eduDeg4now:null);
		model.setEduDeg4nowCn(null!=eduDeg4nowCn && !"".equals(eduDeg4nowCn.trim())?eduDeg4nowCn.trim():null);
		model.setEduLev4now(null!=eduLev4now?eduLev4now:null);
		model.setEduLev4nowCn(null!=eduLev4nowCn && !"".equals(eduLev4nowCn.trim())?eduLev4nowCn.trim():null);
		model.setEduLev4sal(null!=eduLev4sal?eduLev4sal:null);
		model.setEduLev4salCn(null!=eduLev4salCn && !"".equals(eduLev4salCn.trim())?eduLev4salCn.trim():null);
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
	//@CrossOrigin
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmEduHeadModel model = new ChPmEduHeadModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//判断该职工的学历Head信息是否存在
			ChPmEduHeadModel head = chPmEduHeadBiz.queryByWkId(model.getWorkerId());
			if(null != head) return LayAjaxHelper.fail("该职工的现学历信息已存在！");
			//增加
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			chPmEduHeadBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("保存失败!error:",ex);
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
			
			ChPmEduHeadModel model = chPmEduHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载教育Head表失败！error:",e);
		}
	}
	
	/**
	 *  编辑操作
	 * @param request
	 * @param wkId
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("workerId") Long wkId) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmEduHeadModel model = null;
			ChPmEduHeadModel model1 = chPmEduHeadBiz.queryByWkId(wkId);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmEduHeadModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(null != model1)
				chPmEduHeadBiz.edit(model);
			else
				chPmEduHeadBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("编辑失败!error:",ex);
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
			ChPmEduHeadModel model = chPmEduHeadBiz.queryById(id);
			if(model != null)
			{
				chPmEduHeadBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model+",名称:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("删除失败!error:",ex);
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
			chPmEduHeadBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("批量删除失败!error:",ex);
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
		ChPmEduHeadModel model = null;
		try {
			model = chPmEduHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载教育Head表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
