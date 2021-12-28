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
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmIncdecInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkerBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmIncdecInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
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
 * Description: [增减情况服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmIncdecInfo/*")
public class ChPmIncdecInfoController extends BaseController {
	
	public final static String Module_Name = "增减情况";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChPmIncdecInfoBiz chPmIncdecInfoBiz;
	@Autowired
	private IChPmWorkerBiz chPmWorkerBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_entryTime = "entryTime";
	private	final static String PARA_entryChan = "entryChan";
	private	final static String PARA_quitTime = "quitTime";
	private	final static String PARA_quitReason = "quitReason";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,entryChan,quitReason";
	
	/**
	 * <p>Discription:[增减情况请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[增减情况首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmIncdecInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmIncdecInfoModel> retBody = new Result4Page<ChPmIncdecInfoModel>();
		//查询参数
		Page.Builder<ChPmIncdecInfoModel> builder = new Page.Builder<ChPmIncdecInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmIncdecInfoModel> page = builder.build();
		try {
			//分页
			chPmIncdecInfoBiz.queryPage(page);
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
	 * <p>Discription:[增减情况设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmIncdecInfoModel> builder) {
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
		String entryTimeStr = request.getParameter(PARA_entryTime);
		Date entryTime = !StringHelper.isNullOrEmpty(entryTimeStr)?DateTimeHelper.strToDate(entryTimeStr, "yyyy-MM-dd"):null;
		String entryChanStr = request.getParameter(PARA_entryChan);
		Integer entryChan = !StringHelper.isNullOrEmpty(entryChanStr)?Integer.parseInt(entryChanStr):null;
		String quitTimeStr = request.getParameter(PARA_quitTime);
		Date quitTime = !StringHelper.isNullOrEmpty(quitTimeStr)?DateTimeHelper.strToDate(quitTimeStr, "yyyy-MM-dd"):null;
		String quitReasonStr = request.getParameter(PARA_quitReason);
		Integer quitReason = !StringHelper.isNullOrEmpty(quitReasonStr)?Integer.parseInt(quitReasonStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		ChPmIncdecInfoModel model = new ChPmIncdecInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setEntryTime(null!=entryTime?entryTime:null);
		model.setEntryChan(null!=entryChan?entryChan:null);
		model.setQuitTime(null!=quitTime?quitTime:null);
		model.setQuitReason(null!=quitReason?quitReason:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[增减情况校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmIncdecInfoModel model) {
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
		String entryTimeStr = request.getParameter(PARA_entryTime);
		Date entryTime = !StringHelper.isNullOrEmpty(entryTimeStr)?DateTimeHelper.strToDate(entryTimeStr, "yyyy-MM-dd"):null;
		String entryChanStr = request.getParameter(PARA_entryChan);
		Integer entryChan = !StringHelper.isNullOrEmpty(entryChanStr)?Integer.parseInt(entryChanStr):null;
		if(null == entryChan)
		{
			stringBuffer.append("输入进入渠道!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String quitTimeStr = request.getParameter(PARA_quitTime);
		Date quitTime = !StringHelper.isNullOrEmpty(quitTimeStr)?DateTimeHelper.strToDate(quitTimeStr, "yyyy-MM-dd"):null;
		String quitReasonStr = request.getParameter(PARA_quitReason);
		Integer quitReason = !StringHelper.isNullOrEmpty(quitReasonStr)?Integer.parseInt(quitReasonStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setEntryTime(null!=entryTime?entryTime:null);
		model.setEntryChan(null!=entryChan?entryChan:null);
		model.setQuitTime(null!=quitTime?quitTime:null);
		model.setQuitReason(null!=quitReason?quitReason:null);
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
		
		//根据职工编号查询职工基本信息，用于本单位时间的填充
		ChPmWorkerModel model = chPmWorkerBiz.queryById(wkId);
		if(null != model)
			modelMap.addAttribute("intoLocalCompTime", model.getIntoLocalCompTime());
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
			ChPmIncdecInfoModel model = new ChPmIncdecInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			if(null != model.getId()) 
				return LayAjaxHelper.fail("当前职工的增减情况已录入，请勿重复录入！");
			//增加
			Long id = chPmIncdecInfoBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,id);
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
	public void edit(HttpServletRequest request, ModelMap modelMap) throws Exception{
		//加载请求参数并返回页面
		String wkIdStr = request.getParameter("wkId");
		Long wkId = !StringHelper.isNullOrEmpty(wkIdStr)?Long.parseLong(wkIdStr):null;
		if(null == wkId) throw new Exception("职工编号为空");
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("wkId", wkId);
		modelMap.addAttribute("oper", oper);
		
		//通过职工编号加载增减情况
		ChPmIncdecInfoModel model = chPmIncdecInfoBiz.queryByWkId(wkId);
		
		//若存在增减情况，且增减情况中的进入时间不为空，则无需加载职工基本信息中的进入本单位时间，若否，则需加载
		if(null == model || null == model.getEntryTime())
		{
			if(null == model) model = new ChPmIncdecInfoModel();
			//根据职工编号查询职工基本信息，用于本单位时间的填充
			ChPmWorkerModel wk = chPmWorkerBiz.queryById(wkId);
			if(null != wk)
				model.setEntryTime(wk.getIntoLocalCompTime());
		}
		modelMap.addAttribute("model",model);
		
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
			ChPmIncdecInfoModel model = null;
			ChPmIncdecInfoModel model1 = chPmIncdecInfoBiz.queryById(id);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmIncdecInfoModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0 && null != model1) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(null != model1)
				chPmIncdecInfoBiz.edit(model);
			else
				chPmIncdecInfoBiz.save(model);
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
			ChPmIncdecInfoModel model = chPmIncdecInfoBiz.queryById(id);
			if(model != null)
			{
				chPmIncdecInfoBiz.delById4Logic(id);
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
			chPmIncdecInfoBiz.delByIds4Logic(ids);
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
		ChPmIncdecInfoModel model = null;
		try {
			model = chPmIncdecInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载增减情况失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
