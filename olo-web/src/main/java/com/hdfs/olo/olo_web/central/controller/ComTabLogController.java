package com.hdfs.olo.olo_web.central.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdfs.olo.olo_web.central.biz.IComTabLogBiz;
import com.hdfs.olo.olo_web.central.model.ComTabLogModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * <p>Description: [操作日志 控制层]</p>
 * Created on 2020年08月24日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年
 */
@Controller
@RequestMapping("/central/comTabLog/*")
public class ComTabLogController extends BaseController{
	public final static String Module_Name = "操作日志";
	
	private	final static String PARA_logId = "logId";
	private	final static String PARA_branchId = "branchId";
	private	final static String PARA_branchName = "branchName";
	private	final static String PARA_userId = "userId";
	private	final static String PARA_userName = "userName";
	private	final static String PARA_logModuleId = "logModuleId";
	private	final static String PARA_logModuleName = "logModuleName";
	private	final static String PARA_logIp = "logIp";
	private	final static String PARA_logMac = "logMac";
	private	final static String PARA_logLevel = "logLevel";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	@Autowired
	private IComTabLogBiz comTabLogBiz;
	
	/**
	 * <p>Discription:[操作日志请求首页]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	
	/**
	 * <p>Discription:[操作日志首页数据加载-分页]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ComTabLogModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ComTabLogModel> retBody = new Result4Page<ComTabLogModel>();
		//查询参数
		Page.Builder<ComTabLogModel> builder = new Page.Builder<ComTabLogModel>();
		setSearchParameters(request, builder);
		Page<ComTabLogModel> pageInfo = builder.build();
		try {
			//分页
			comTabLogBiz.queryPage(pageInfo);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(pageInfo.getDatas());
			retBody.setCount(pageInfo.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		return retBody;
	}
	
	/**
	 * <p>Discription:[操作日志设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ComTabLogModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		//请求参数置入
		String logIdStr = request.getParameter(PARA_logId);
		Long logId = !StringHelper.isNullOrEmpty(logIdStr)?Long.parseLong(logIdStr):null;
		String branchIdStr = request.getParameter(PARA_branchId);
		Long branchId = !StringHelper.isNullOrEmpty(branchIdStr)?Long.parseLong(branchIdStr):null;
		String branchName = request.getParameter(PARA_branchName);
		String userIdStr = request.getParameter(PARA_userId);
		Long userId = !StringHelper.isNullOrEmpty(userIdStr)?Long.parseLong(userIdStr):null;
		String userName = request.getParameter(PARA_userName);
		String logModuleId = request.getParameter(PARA_logModuleId);
		String logModuleName = request.getParameter(PARA_logModuleName);
		String logIp = request.getParameter(PARA_logIp);
		String logMac = request.getParameter(PARA_logMac);
		String logLevelStr = request.getParameter(PARA_logLevel);
		Integer logLevel = !StringHelper.isNullOrEmpty(logLevelStr)?Integer.parseInt(logLevelStr):null;
		String status = request.getParameter(PARA_status);
		String remark = request.getParameter(PARA_remark);
        
        ComTabLogModel model = new ComTabLogModel();
		if(null != logId)
        	model.setLogId(logId);
		if(null != branchId)
        	model.setBranchId(branchId);
		if(!StringHelper.isNullOrEmpty(branchName))
        	model.setBranchName(branchName);
		if(null != userId)
        	model.setUserId(userId);
		if(!StringHelper.isNullOrEmpty(userName))
        	model.setUserName(userName);
		if(!StringHelper.isNullOrEmpty(logModuleId))
        	model.setLogModuleId(logModuleId);
		if(!StringHelper.isNullOrEmpty(logModuleName))
        	model.setLogModuleName(logModuleName);
		if(!StringHelper.isNullOrEmpty(logIp))
        	model.setLogIp(logIp);
		if(!StringHelper.isNullOrEmpty(logMac))
        	model.setLogMac(logMac);
		if(null != logLevel)
        	model.setLogLevel(logLevel);
		if(!StringHelper.isNullOrEmpty(status))
        	model.setStatus(status);
		if(!StringHelper.isNullOrEmpty(remark))
        	model.setRemark(remark);
		//置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[操作日志 校验]</p>
	 * @param request请求对象
	 * @param errMsg错误信息
	 * @param model实体对象
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer errMsg, ComTabLogModel model) {
		String logIdStr = request.getParameter(PARA_logId);
		Long logId = !StringHelper.isNullOrEmpty(logIdStr)?Long.parseLong(logIdStr):null;
		String branchIdStr = request.getParameter(PARA_branchId);
		Long branchId = !StringHelper.isNullOrEmpty(branchIdStr)?Long.parseLong(branchIdStr):null;
		String branchName = request.getParameter(PARA_branchName);
		String userIdStr = request.getParameter(PARA_userId);
		Long userId = !StringHelper.isNullOrEmpty(userIdStr)?Long.parseLong(userIdStr):null;
		String userName = request.getParameter(PARA_userName);
		String logModuleId = request.getParameter(PARA_logModuleId);
		String logModuleName = request.getParameter(PARA_logModuleName);
		String logIp = request.getParameter(PARA_logIp);
		String logMac = request.getParameter(PARA_logMac);
		String logLevelStr = request.getParameter(PARA_logLevel);
		Integer logLevel = !StringHelper.isNullOrEmpty(logLevelStr)?Integer.parseInt(logLevelStr):null;
		String status = request.getParameter(PARA_status);
		String remark = request.getParameter(PARA_remark);
		
		if (errMsg.length() == 0) {
			model.setLogId(null!=logId?logId:null);
			model.setBranchId(null!=branchId?branchId:null);
			model.setBranchName(null!=branchName?branchName.trim():null);
			model.setUserId(null!=userId?userId:null);
			model.setUserName(null!=userName?userName.trim():null);
			model.setLogModuleId(null!=logModuleId?logModuleId.trim():null);
			model.setLogModuleName(null!=logModuleName?logModuleName.trim():null);
			model.setLogIp(null!=logIp?logIp.trim():null);
			model.setLogMac(null!=logMac?logMac.trim():null);
			model.setLogLevel(logLevel);
			model.setStatus(null!=status?status.trim():null);
			model.setRemark(null!=remark?remark.trim():null);
		}
	}
	
	/**
	 * <p>Discription:[操作日志 添加转向]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) {
		//List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		//modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
	}
	
	/**
	 * <p>Discription:[操作日志 添加执行]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			ComTabLogModel model = new ComTabLogModel();
			//基本验证
			baseValidate(request, errMsg, model);
			//若验证失败,则返回
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			//执行添加
			comTabLogBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:"+model+",other:"+model);
			
			//返回成功信息
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[操作日志 编辑转向]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(Long logId, ModelMap modelMap) {
		if(null == logId) return;
		//List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		//modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
		
		ComTabLogModel model = comTabLogBiz.queryById(logId);
		modelMap.addAttribute("model", model);
	}
	
	/**
	 * <p>Discription:[操作日志 编辑执行]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, Long logId) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			//必须输入参数
			if (null == logId) {
				errMsg.append("请输入编号!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			ComTabLogModel model = comTabLogBiz.queryById(logId);
			if(null == model)
			{
				errMsg.append("该记录在系统中不存在!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			baseValidate(request, errMsg, model);
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			comTabLogBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:"+model+",other:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[操作日志 单项删除执行]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,Long logId) {
		Map<String, Object> map = null;
		try {
			if(null == logId) return LayAjaxHelper.fail("请选择编号!");
			ComTabLogModel model = comTabLogBiz.queryById(logId);
			if(null != model)
			{
				comTabLogBiz.delById(logId);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+model+",other:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[操作日志 批量删除执行]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			comTabLogBiz.delBatchByIds(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"用户编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[操作日志 详情转向/执行]</p>
	 * Created on 2020年08月24日
	 * @author:huadf
	 */
	@RequestMapping(value = "details.jhtm")
	public void details(HttpServletRequest request,Long logId, ModelMap modelMap) {
		ComTabLogModel model = comTabLogBiz.queryById(logId);
		modelMap.addAttribute("model", model);
		
		//List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		//modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:"+model+",other:"+model);
	}
}
