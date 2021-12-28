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
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkExpeInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkExpeInfoModel;
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
 * Description: [工作经历信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmWorkExpeInfo/*")
public class ChPmWorkExpeInfoController extends BaseController {
	
	public final static String Module_Name = "工作经历信息";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChPmWorkExpeInfoBiz chPmWorkExpeInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_wkComName = "wkComName";
	private	final static String PARA_wkDepName = "wkDepName";
	private	final static String PARA_wkStation = "wkStation";
	private	final static String PARA_wkPosit = "wkPosit";
	private	final static String PARA_wkTitle = "wkTitle";
	private	final static String PARA_transDocno = "transDocno";
	private	final static String PARA_wkStartTime = "wkStartTime";
	private	final static String PARA_wkEndTime = "wkEndTime";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,YN";
	
	/**
	 * <p>Discription:[工作经历信息请求首页]</p>
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
	 * <p>Discription:[工作经历信息首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmWorkExpeInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmWorkExpeInfoModel> retBody = new Result4Page<ChPmWorkExpeInfoModel>();
		//查询参数
		Page.Builder<ChPmWorkExpeInfoModel> builder = new Page.Builder<ChPmWorkExpeInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmWorkExpeInfoModel> page = builder.build();
		try {
			//分页
			chPmWorkExpeInfoBiz.queryPage(page);
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
	 * <p>Discription:[工作经历信息设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmWorkExpeInfoModel> builder) {
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
		String wkComName = request.getParameter(PARA_wkComName);
		String wkDepName = request.getParameter(PARA_wkDepName);
		String wkStation = request.getParameter(PARA_wkStation);
		String wkPosit = request.getParameter(PARA_wkPosit);
		String wkTitle = request.getParameter(PARA_wkTitle);
		String transDocno = request.getParameter(PARA_transDocno);
		String wkStartTimeStr = request.getParameter(PARA_wkStartTime);
		Date wkStartTime = !StringHelper.isNullOrEmpty(wkStartTimeStr)?DateTimeHelper.strToDate(wkStartTimeStr, "yyyy-MM-dd"):null;
		String wkEndTimeStr = request.getParameter(PARA_wkEndTime);
		Date wkEndTime = !StringHelper.isNullOrEmpty(wkEndTimeStr)?DateTimeHelper.strToDate(wkEndTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		ChPmWorkExpeInfoModel model = new ChPmWorkExpeInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setWkComName(null!=wkComName && !"".equals(wkComName.trim())?wkComName.trim():null);
		model.setWkDepName(null!=wkDepName && !"".equals(wkDepName.trim())?wkDepName.trim():null);
		model.setWkStation(null!=wkStation && !"".equals(wkStation.trim())?wkStation.trim():null);
		model.setWkPosit(null!=wkPosit && !"".equals(wkPosit.trim())?wkPosit.trim():null);
		model.setWkTitle(null!=wkTitle && !"".equals(wkTitle.trim())?wkTitle.trim():null);
		model.setTransDocno(null!=transDocno && !"".equals(transDocno.trim())?transDocno.trim():null);
		model.setWkStartTime(null!=wkStartTime?wkStartTime:null);
		model.setWkEndTime(null!=wkEndTime?wkEndTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[工作经历信息校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmWorkExpeInfoModel model) {
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
		String wkComName = request.getParameter(PARA_wkComName);
		if(StringHelper.isNullOrEmpty(wkComName))
		{
			stringBuffer.append("输入工作单位名称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wkDepName = request.getParameter(PARA_wkDepName);
		if(StringHelper.isNullOrEmpty(wkDepName))
		{
			stringBuffer.append("输入工作部门名称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wkStation = request.getParameter(PARA_wkStation);
		if(StringHelper.isNullOrEmpty(wkStation))
		{
			stringBuffer.append("输入工作岗位!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wkPosit = request.getParameter(PARA_wkPosit);
		String wkTitle = request.getParameter(PARA_wkTitle);
		String transDocno = request.getParameter(PARA_transDocno);
		String wkStartTimeStr = request.getParameter(PARA_wkStartTime);
		Date wkStartTime = !StringHelper.isNullOrEmpty(wkStartTimeStr)?DateTimeHelper.strToDate(wkStartTimeStr, "yyyy-MM-dd"):null;
		String wkEndTimeStr = request.getParameter(PARA_wkEndTime);
		Date wkEndTime = !StringHelper.isNullOrEmpty(wkEndTimeStr)?DateTimeHelper.strToDate(wkEndTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setWkComName(null!=wkComName && !"".equals(wkComName.trim())?wkComName.trim():null);
		model.setWkDepName(null!=wkDepName && !"".equals(wkDepName.trim())?wkDepName.trim():null);
		model.setWkStation(null!=wkStation && !"".equals(wkStation.trim())?wkStation.trim():null);
		model.setWkPosit(null!=wkPosit && !"".equals(wkPosit.trim())?wkPosit.trim():null);
		model.setWkTitle(null!=wkTitle && !"".equals(wkTitle.trim())?wkTitle.trim():null);
		model.setTransDocno(null!=transDocno && !"".equals(transDocno.trim())?transDocno.trim():null);
		model.setWkStartTime(null!=wkStartTime?wkStartTime:null);
		model.setWkEndTime(null!=wkEndTime?wkEndTime:null);
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
			ChPmWorkExpeInfoModel model = new ChPmWorkExpeInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chPmWorkExpeInfoBiz.save(model);
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
			
			ChPmWorkExpeInfoModel model = chPmWorkExpeInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载工作经历信息失败！error:",e);
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
			ChPmWorkExpeInfoModel model = chPmWorkExpeInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmWorkExpeInfoBiz.edit(model);
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
			ChPmWorkExpeInfoModel model = chPmWorkExpeInfoBiz.queryById(id);
			if(model != null)
			{
				chPmWorkExpeInfoBiz.delById4Logic(id);
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
			chPmWorkExpeInfoBiz.delByIds4Logic(ids);
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
		ChPmWorkExpeInfoModel model = null;
		try {
			model = chPmWorkExpeInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载工作经历信息失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
