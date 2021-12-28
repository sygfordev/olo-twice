package com.hdfs.olo.olo_web.personnel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.hdfs.olo.olo_web.personnel.biz.IChPmFamilyMemberInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmFamilyMemberInfoModel;
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
 * Description: [家庭成员服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmFamilyMemberInfo/*")
public class ChPmFamilyMemberInfoController extends BaseController {
	
	public final static String Module_Name = "家庭成员";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChPmFamilyMemberInfoBiz chPmFamilyMemberInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_memRelation = "memRelation";
	private	final static String PARA_memRelationCn = "memRelationCn";
	private	final static String PARA_memName = "memName";
	private	final static String PARA_memWkCom = "memWkCom";
	private	final static String PARA_memTelno = "memTelno";
	private	final static String PARA_memAddr = "memAddr";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,homeMemRel";
	/**
	 * <p>Discription:[家庭成员请求首页]</p>
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
	}
	 
	/**
	 * <p>Discription:[家庭成员首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmFamilyMemberInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmFamilyMemberInfoModel> retBody = new Result4Page<ChPmFamilyMemberInfoModel>();
		//查询参数
		Page.Builder<ChPmFamilyMemberInfoModel> builder = new Page.Builder<ChPmFamilyMemberInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmFamilyMemberInfoModel> page = builder.build();
		try {
			//分页
			chPmFamilyMemberInfoBiz.queryPage(page);
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
	 * <p>Discription:[家庭成员设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmFamilyMemberInfoModel> builder) {
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
		String memRelation = request.getParameter(PARA_memRelation);
		String memRelationCn = request.getParameter(PARA_memRelationCn);
		String memName = request.getParameter(PARA_memName);
		String memWkCom = request.getParameter(PARA_memWkCom);
		String memTelno = request.getParameter(PARA_memTelno);
		String memAddr = request.getParameter(PARA_memAddr);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		ChPmFamilyMemberInfoModel model = new ChPmFamilyMemberInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setMemRelation(null!=memRelation && !"".equals(memRelation.trim())?memRelation.trim():null);
		model.setMemRelationCn(null!=memRelationCn && !"".equals(memRelationCn.trim())?memRelationCn.trim():null);
		model.setMemName(null!=memName && !"".equals(memName.trim())?memName.trim():null);
		model.setMemWkCom(null!=memWkCom && !"".equals(memWkCom.trim())?memWkCom.trim():null);
		model.setMemTelno(null!=memTelno && !"".equals(memTelno.trim())?memTelno.trim():null);
		model.setMemAddr(null!=memAddr && !"".equals(memAddr.trim())?memAddr.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[家庭成员校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmFamilyMemberInfoModel model) {
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
		String memRelation = request.getParameter(PARA_memRelation);
		if(StringHelper.isNullOrEmpty(memRelation))
		{
			stringBuffer.append("输入家庭成员关系!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String memRelationCn = request.getParameter(PARA_memRelationCn);
		String memName = request.getParameter(PARA_memName);
		if(StringHelper.isNullOrEmpty(memName))
		{
			stringBuffer.append("输入家庭成员姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String memWkCom = request.getParameter(PARA_memWkCom);
		String memTelno = request.getParameter(PARA_memTelno);
		String memAddr = request.getParameter(PARA_memAddr);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setMemRelation(null!=memRelation && !"".equals(memRelation.trim())?memRelation.trim():null);
		model.setMemRelationCn(null!=memRelationCn && !"".equals(memRelationCn.trim())?memRelationCn.trim():null);
		model.setMemName(null!=memName && !"".equals(memName.trim())?memName.trim():null);
		model.setMemWkCom(null!=memWkCom && !"".equals(memWkCom.trim())?memWkCom.trim():null);
		model.setMemTelno(null!=memTelno && !"".equals(memTelno.trim())?memTelno.trim():null);
		model.setMemAddr(null!=memAddr && !"".equals(memAddr.trim())?memAddr.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap,Long wkId)throws Exception{
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
			ChPmFamilyMemberInfoModel model = new ChPmFamilyMemberInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chPmFamilyMemberInfoBiz.save(model);
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
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			//字典数据加载
			fillCommonDict(modelMap);
			
			ChPmFamilyMemberInfoModel model = chPmFamilyMemberInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载家庭成员失败！error:",e);
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
			ChPmFamilyMemberInfoModel model = chPmFamilyMemberInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmFamilyMemberInfoBiz.edit(model);
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
			ChPmFamilyMemberInfoModel model = chPmFamilyMemberInfoBiz.queryById(id);
			if(model != null)
			{
				chPmFamilyMemberInfoBiz.delById4Logic(id);
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
			chPmFamilyMemberInfoBiz.delByIds4Logic(ids);
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
		ChPmFamilyMemberInfoModel model = null;
		try {
			model = chPmFamilyMemberInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载家庭成员失败！error:",e);
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
