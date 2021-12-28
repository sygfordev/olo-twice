package com.hdfs.olo.olo_web.central.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.hdfs.olo.olo_web.central.model.SystemDictItemModel;
import com.hdfs.olo.olo_web.central.model.SystemDictModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [系统字典项服务实现]
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/central/systemDictItem/*")
public class SystemDictItemController extends BaseController {
	
	public final static String Module_Name = "系统字典项";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz systemDictBiz;
	@Autowired
	private ISystemDictItemBiz systemDictItemBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_dictId = "dictId";
	private	final static String PARA_itemKey = "itemKey";
	private	final static String PARA_itemVal = "itemVal";
	private	final static String PARA_existSuper = "existSuper";
	private	final static String PARA_superId = "superId";
	private	final static String PARA_sortVal = "sortVal";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_creator = "creator";
	private	final static String PARA_updator = "updator";
	
	/**
	 * <p>Discription:[系统字典项请求首页]</p>
	 * Created on 2021年03月04日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		String dictIdStr = request.getParameter("dictId");
		Long dictId = !StringHelper.isNullOrEmpty(dictIdStr)?Long.parseLong(dictIdStr):null;
		if(null == dictId)
		{
			logger.error("字典信息未带入字典项管理页面!");
			throw new Exception("字典信息未带入字典项管理页面!");
		}
		SystemDictModel dict = systemDictBiz.queryById(dictId);
		modelMap.put("dictId", dictId);
		modelMap.put("dictName", dict.getName());
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
		List<ComboxItem> yesNoList = getYesNoList();
		modelMap.put("yesNoList", yesNoList);
		//为父级字典项做数据准备
		List<ComboxItem> superList = loadComBox4Super(dictId);
		modelMap.addAttribute("superList", superList);
	}
	 
	/**
	 * <p>Discription:[系统字典项首页数据加载-分页]</p>
	 * Created on 2021年03月04日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<SystemDictItemModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<SystemDictItemModel> retBody = new Result4Page<SystemDictItemModel>();
		//查询参数
		Page.Builder<SystemDictItemModel> builder = new Page.Builder<SystemDictItemModel>();
		setSearchParameters(request, builder);
		Page<SystemDictItemModel> page = builder.build();
		try {
			//分页
			systemDictItemBiz.queryPage(page);
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
	 * <p>Discription:[系统字典项设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月04日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<SystemDictItemModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String dictIdStr = request.getParameter(PARA_dictId);
		Long dictId = !StringHelper.isNullOrEmpty(dictIdStr)?Long.parseLong(dictIdStr):null;
		String itemKey = request.getParameter(PARA_itemKey);
		String itemVal = request.getParameter(PARA_itemVal);
		String existSuperStr = request.getParameter(PARA_existSuper);
		Integer existSuper = !StringHelper.isNullOrEmpty(existSuperStr)?Integer.parseInt(existSuperStr):null;
		String superIdStr = request.getParameter(PARA_superId);
		Long superId = !StringHelper.isNullOrEmpty(superIdStr)?Long.parseLong(superIdStr):null;
		if(null == existSuper || 0 == existSuper) superId = null;
		String sortValStr = request.getParameter(PARA_sortVal);
		Integer sortVal = !StringHelper.isNullOrEmpty(sortValStr)?Integer.parseInt(sortValStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		
		UserInfoModel loginUser = this.getLoginUser(request);
		
		SystemDictItemModel model = new SystemDictItemModel();
		model.setId(null!=id?id:null);
		model.setDictId(null!=dictId?dictId:null);
		model.setItemKey(null!=itemKey && !"".equals(itemKey.trim())?itemKey.trim():null);
		model.setItemVal(null!=itemVal && !"".equals(itemVal.trim())?itemVal.trim():null);
		model.setExistSuper(null!=existSuper?existSuper:null);
		model.setSuperId(null!=superId?superId:null);
		model.setSortVal(null!=sortVal?sortVal:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && "".equals(remark.trim())?remark.trim():null);
		model.setCreator(null != loginUser?loginUser.getAccount():null);
		model.setUpdator(null != loginUser?loginUser.getAccount():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[系统字典项校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月04日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, SystemDictItemModel model) {
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		
		String dictIdStr = request.getParameter(PARA_dictId);
		Long dictId = !StringHelper.isNullOrEmpty(dictIdStr)?Long.parseLong(dictIdStr):null;
		if(null == dictId)
		{
			stringBuffer.append("输入字典编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String itemKey = request.getParameter(PARA_itemKey);
		if(StringHelper.isNullOrEmpty(itemKey))
		{
			stringBuffer.append("输入字典项Key!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String itemVal = request.getParameter(PARA_itemVal);
		if(StringHelper.isNullOrEmpty(itemVal))
		{
			stringBuffer.append("输入字典项Val!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String existSuperStr = request.getParameter(PARA_existSuper);
		Integer existSuper = !StringHelper.isNullOrEmpty(existSuperStr)?Integer.parseInt(existSuperStr):null;
		String superIdStr = request.getParameter(PARA_superId);
		Long superId = !StringHelper.isNullOrEmpty(superIdStr)?Long.parseLong(superIdStr):null;
		if(null == existSuper || 0 == existSuper) superId = null;
		if(null == existSuper)
		{
			stringBuffer.append("输入是否存在父项!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if(1==existSuper && null == superId)
		{
			stringBuffer.append("输入父项编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String sortValStr = request.getParameter(PARA_sortVal);
		Integer sortVal = !StringHelper.isNullOrEmpty(sortValStr)?Integer.parseInt(sortValStr):null;
		if(null == sortVal)
		{
			stringBuffer.append("输入排序!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		if(null == status)
		{
			stringBuffer.append("输入状态!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		UserInfoModel loginUser = this.getLoginUser(request);
		model.setId(null!=id?id:null);
		model.setDictId(null!=dictId?dictId:null);
		model.setItemKey(null!=itemKey && !"".equals(itemKey.trim())?itemKey.trim():null);
		model.setItemVal(null!=itemVal && !"".equals(itemVal.trim())?itemVal.trim():null);
		model.setExistSuper(null!=existSuper?existSuper:null);
		model.setSuperId(null!=superId?superId:null);
		model.setSortVal(null!=sortVal?sortVal:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreator(null!=loginUser?loginUser.getAccount():null);
		model.setUpdator(null!=loginUser?loginUser.getAccount():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(Long dictId,String dictName,ModelMap modelMap) throws Exception{
		if(null == dictId) {
			logger.error("字典信息未带入字典项管理页面!");
			throw new Exception("字典信息未带入字典项管理页面!");
		}
		List<ComboxItem> statusList = getStatusList();
		List<ComboxItem> yesNoList = getYesNoList();
		
		modelMap.addAttribute("dictId", dictId);
		modelMap.addAttribute("statusList", statusList);
		modelMap.addAttribute("yesNoList", yesNoList);
		
		//为父级字典项做数据准备
		List<ComboxItem> superList = loadComBox4Super(dictId);
		modelMap.addAttribute("superList", superList);
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
			SystemDictItemModel model = new SystemDictItemModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//校验该字典下是否已存在该字典项key
			SystemDictItemModel tmp = new SystemDictItemModel();
			tmp.setStatus(DictionaryUtil.KEY_NORMAL);
			tmp.setDictId(model.getDictId());
			tmp.setItemKey(model.getItemKey());
			Long count = systemDictItemBiz.queryCount(tmp);
			if(count>0) return LayAjaxHelper.fail("该字典项Key已存在!");
			//增加
			systemDictItemBiz.save(model);
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
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			List<ComboxItem> statusList = getStatusList();
			modelMap.addAttribute("statusList", statusList);
			
			SystemDictItemModel model = systemDictItemBiz.queryById(id);
			modelMap.addAttribute("model", model);
			List<ComboxItem> yesNoList = getYesNoList();
			modelMap.addAttribute("yesNoList", yesNoList);
			
			//为父级字典项做数据准备
			List<ComboxItem> superList = loadComBox4Super(model.getDictId());
			modelMap.addAttribute("superList", superList);
		}catch(Exception e)
		{
			logger.error("加载系统字典项失败！error:",e);
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
			SystemDictItemModel model = systemDictItemBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			systemDictItemBiz.edit(model);
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
			SystemDictItemModel model = systemDictItemBiz.queryById(id);
			if(model != null)
			{
				systemDictItemBiz.delById4Logic(id);
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
			systemDictItemBiz.delByIds4Logic(ids);
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
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		SystemDictItemModel model = null;
		try {
			model = systemDictItemBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model.getId()+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载系统字典项失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model.getId()+",名称:" + model);
		}
	}
	//********************以下为扩展方法***********************
	/**
	 * 根据字典主键查询顶级字典项列表
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	private List<ComboxItem> loadComBox4Super(Long dictId) throws Exception
	{
		if(null == dictId) throw new Exception("加载字典项父级时未检测到字典编号存在！");
		List<ComboxItem> superList = null;
		try {
			//为父级字典项做数据准备
			SystemDictItemModel itemDto = new SystemDictItemModel();
			itemDto.setDictId(dictId);
			itemDto.setStatus(DictionaryUtil.KEY_NORMAL);
			List<SystemDictItemModel> list = systemDictItemBiz.queryList(itemDto);
			ComboxItem combox = null;
			superList = new ArrayList<ComboxItem>();
			for(SystemDictItemModel item:list)
			{
				if(1 == item.getExistSuper())continue;
				combox = new ComboxItem();
				combox.setKey(String.valueOf(item.getId()));
				combox.setValue(item.getItemVal());
				superList.add(combox);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("加载字典项父级时异常！");
		}
		return superList;
	}
}
