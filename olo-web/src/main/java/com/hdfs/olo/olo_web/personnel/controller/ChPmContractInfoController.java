package com.hdfs.olo.olo_web.personnel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.hdfs.olo.olo_web.central.model.SystemDictItemModel;
import com.hdfs.olo.olo_web.central.model.SystemDictModel;
import com.hdfs.olo.olo_web.personnel.biz.IChPmContractInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
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
 * Description: [合同信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmContractInfo/*")
public class ChPmContractInfoController extends BaseController {
	
	public final static String Module_Name = "合同信息";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmContractInfoBiz chPmContractInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_contClass = "contClass";
	private	final static String PARA_contClassCn = "contClassCn";
	private	final static String PARA_contCycle = "contCycle";
	private	final static String PARA_contCycleCn = "contCycleCn";
	private	final static String PARA_contNo = "contNo";
	private	final static String PARA_contStartTime = "contStartTime";
	private	final static String PARA_contEndTime = "contEndTime";
	private	final static String PARA_contExpireWarnTime = "contExpireWarnTime";
	private	final static String PARA_contOrder = "contOrder";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,contClass,contCycle";
	
	
	/**
	 * <p>Discription:[合同信息请求首页]</p>
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
		this.fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[合同信息首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmContractInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmContractInfoModel> retBody = new Result4Page<ChPmContractInfoModel>();
		//查询参数
		Page.Builder<ChPmContractInfoModel> builder = new Page.Builder<ChPmContractInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmContractInfoModel> page = builder.build();
		try {
			//分页
			chPmContractInfoBiz.queryPage(page);
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
	 * <p>Discription:[合同信息请求首页]<//**
	 * <p>Discription:[合同信息设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmContractInfoModel> builder) {
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
		String contClassStr = request.getParameter(PARA_contClass);
		Integer contClass = !StringHelper.isNullOrEmpty(contClassStr)?Integer.parseInt(contClassStr):null;
		String contClassCn = request.getParameter(PARA_contClassCn);
		String contCycleStr = request.getParameter(PARA_contCycle);
		Integer contCycle = !StringHelper.isNullOrEmpty(contCycleStr)?Integer.parseInt(contCycleStr):null;
		String contCycleCn = request.getParameter(PARA_contCycleCn);
		String contNo = request.getParameter(PARA_contNo);
		String contStartTimeStr = request.getParameter(PARA_contStartTime);
		Date contStartTime = !StringHelper.isNullOrEmpty(contStartTimeStr)?DateTimeHelper.strToDate(contStartTimeStr, "yyyy-MM-dd"):null;
		String contEndTimeStr = request.getParameter(PARA_contEndTime);
		Date contEndTime = !StringHelper.isNullOrEmpty(contEndTimeStr)?DateTimeHelper.strToDate(contEndTimeStr, "yyyy-MM-dd"):null;
		String contExpireWarnTimeStr = request.getParameter(PARA_contExpireWarnTime);
		Date contExpireWarnTime = !StringHelper.isNullOrEmpty(contExpireWarnTimeStr)?DateTimeHelper.strToDate(contExpireWarnTimeStr, "yyyy-MM-dd"):null;
		String contOrderStr = request.getParameter(PARA_contOrder);
		Integer contOrder = !StringHelper.isNullOrEmpty(contOrderStr)?Integer.parseInt(contOrderStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmContractInfoModel model = new ChPmContractInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setContClass(null!=contClass?contClass:null);
		model.setContClassCn(null!=contClassCn && !"".equals(contClassCn.trim())?contClassCn.trim():null);
		model.setContCycle(null!=contCycle?contCycle:null);
		model.setContCycleCn(null!=contCycleCn && !"".equals(contCycleCn.trim())?contCycleCn.trim():null);
		model.setContNo(null!=contNo && !"".equals(contNo.trim())?contNo.trim():null);
		model.setContStartTime(null!=contStartTime?contStartTime:null);
		model.setContEndTime(null!=contEndTime?contEndTime:null);
		model.setContExpireWarnTime(null!=contExpireWarnTime?contExpireWarnTime:null);
		model.setContOrder(null!=contOrder?contOrder:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[合同信息校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmContractInfoModel model) {
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
		String contClassStr = request.getParameter(PARA_contClass);
		Integer contClass = !StringHelper.isNullOrEmpty(contClassStr)?Integer.parseInt(contClassStr):null;
		if(null == contClass)
		{
			stringBuffer.append("输入合同类别 !");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String contClassCn = request.getParameter(PARA_contClassCn);
		String contCycleStr = request.getParameter(PARA_contCycle);
		Integer contCycle = !StringHelper.isNullOrEmpty(contCycleStr)?Integer.parseInt(contCycleStr):null;
		if(null == contCycle)
		{
			stringBuffer.append("输入合同期限!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String contCycleCn = request.getParameter(PARA_contCycleCn);
		String contNo = request.getParameter(PARA_contNo);
		if(StringHelper.isNullOrEmpty(contNo))
		{
			stringBuffer.append("输入合同编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String contStartTimeStr = request.getParameter(PARA_contStartTime);
		Date contStartTime = !StringHelper.isNullOrEmpty(contStartTimeStr)?DateTimeHelper.strToDate(contStartTimeStr, "yyyy-MM-dd"):null;
		String contEndTimeStr = request.getParameter(PARA_contEndTime);
		Date contEndTime = !StringHelper.isNullOrEmpty(contEndTimeStr)?DateTimeHelper.strToDate(contEndTimeStr, "yyyy-MM-dd"):null;
		String contExpireWarnTimeStr = request.getParameter(PARA_contExpireWarnTime);
		Date contExpireWarnTime = !StringHelper.isNullOrEmpty(contExpireWarnTimeStr)?DateTimeHelper.strToDate(contExpireWarnTimeStr, "yyyy-MM-dd"):null;
		String contOrderStr = request.getParameter(PARA_contOrder);
		Integer contOrder = !StringHelper.isNullOrEmpty(contOrderStr)?Integer.parseInt(contOrderStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setContClass(null!=contClass?contClass:null);
		model.setContClassCn(null!=contClassCn && !"".equals(contClassCn.trim())?contClassCn.trim():null);
		model.setContCycle(null!=contCycle?contCycle:null);
		model.setContCycleCn(null!=contCycleCn && !"".equals(contCycleCn.trim())?contCycleCn.trim():null);
		model.setContNo(null!=contNo && !"".equals(contNo.trim())?contNo.trim():null);
		model.setContStartTime(null!=contStartTime?contStartTime:null);
		model.setContEndTime(null!=contEndTime?contEndTime:null);
		model.setContExpireWarnTime(null!=contExpireWarnTime?contExpireWarnTime:null);
		model.setContOrder(null!=contOrder?contOrder:null);
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
			ChPmContractInfoModel model = new ChPmContractInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chPmContractInfoBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
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
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) return;
		//字典数据加载
		fillCommonDict(modelMap);
		
		ChPmContractInfoModel model = chPmContractInfoBiz.queryById(id);
		modelMap.addAttribute("model", model);
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
			ChPmContractInfoModel model = chPmContractInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmContractInfoBiz.edit(model);
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
			ChPmContractInfoModel model = chPmContractInfoBiz.queryById(id);
			if(model != null)
			{
				chPmContractInfoBiz.delById4Logic(id);
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
			chPmContractInfoBiz.delByIds4Logic(ids);
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
		ChPmContractInfoModel model = null;
		try {
			model = chPmContractInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载合同信息失败！error:",e);
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
	 * 通过合同类别加载合同期限
	 * @param request
	 * @param itemKey  合同类别字典码
	 * @return
	 */
	@RequestMapping(value = "loadCycleByClass.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadCycleByClass(HttpServletRequest request,String itemKey) {
		Map<String, Object> map = null;
		try {
			SystemDictModel dict = dictBiz.queryByCode("contClass");
			if(null == dict) throw new Exception("合同类别字典不存在！");
			SystemDictItemModel item = new SystemDictItemModel();
			item.setDictId(dict.getId());
			item.setItemKey(itemKey);
			List<SystemDictItemModel> clazz = dictItemBiz.queryList(item);
			if(clazz.size()<=0)throw new Exception("该字典项不存在！");
			SystemDictItemModel clazzItem = clazz.get(0);
			
			Map<String,List<Map<String,Object>>> itemMap = dictBiz.queryItemListByCodes(Arrays.asList("contCycle".split(",")));
			if(null == itemMap || itemMap.isEmpty())
				throw new Exception("合同类别字典项不存在！");
			List<Map<String,Object>> cycleList = itemMap.get("contCycle");
			Iterator<Map<String,Object>> it = cycleList.iterator();
			//合同类别字典项Key    0：固定期限 1：无固定期限
			String classKey = clazzItem.getItemKey();
			Map<String,Object> oneMap = null;
			while(it.hasNext())
			{
				oneMap = it.next();
				String curKey = oneMap.get("item_key")+"";
				if("0".equals(classKey))
				{
					if("0".equals(curKey)) it.remove();
				}else
				{
					if(!"0".equals(curKey)) it.remove();
				}
			}
			
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"合同类别字典项key:"+itemKey);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,cycleList);
		} catch (Exception ex) {
			log2Error(request, Module_Name, "合同类别加载期限",ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("通过合同类别加载合同期限失败!error:",ex);
		}
		return map;
	}
}
