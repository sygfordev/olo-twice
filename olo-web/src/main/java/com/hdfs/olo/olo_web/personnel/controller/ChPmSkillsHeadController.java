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
import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsHeadBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
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
 * Description: [技能等级(技术工种)信息头表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmSkillsHead/*")
public class ChPmSkillsHeadController extends BaseController {
	
	public final static String Module_Name = "技能等级(技术工种)信息头表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmSkillsHeadBiz chPmSkillsHeadBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_skills4now = "skills4now";
	private	final static String PARA_skills4nowCn = "skills4nowCn";
	private	final static String PARA_skills4nowGotTime = "skills4nowGotTime";
	private	final static String PARA_skillsClass4now = "skillsClass4now";
	private	final static String PARA_skillsClass4nowCn = "skillsClass4nowCn";
	private	final static String PARA_skillsLevel4now = "skillsLevel4now";
	private	final static String PARA_skillsLevel4nowCn = "skillsLevel4nowCn";
	private	final static String PARA_treatLevel4now = "treatLevel4now";
	private	final static String PARA_treatLevel4nowCn = "treatLevel4nowCn";
	private	final static String PARA_skills4nowHStartTime = "skills4nowHStartTime";
	private	final static String PARA_skills4nowHEndTime = "skills4nowHEndTime";
	private	final static String PARA_skillsMax4now = "skillsMax4now";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,YN,skills,skillsClass,skillsLevel";
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,Long wkId,ModelMap modelMap) throws Exception{
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
	 * <p>Discription:[技能等级(技术工种)信息头表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmSkillsHeadModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmSkillsHeadModel> retBody = new Result4Page<ChPmSkillsHeadModel>();
		//查询参数
		Page.Builder<ChPmSkillsHeadModel> builder = new Page.Builder<ChPmSkillsHeadModel>();
		setSearchParameters(request, builder);
		Page<ChPmSkillsHeadModel> page = builder.build();
		try {
			//分页
			chPmSkillsHeadBiz.queryPage(page);
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
	 * <p>Discription:[技能等级(技术工种)信息头表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmSkillsHeadModel> builder) {
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
		String skills4nowStr = request.getParameter(PARA_skills4now);
		Integer skills4now = !StringHelper.isNullOrEmpty(skills4nowStr)?Integer.parseInt(skills4nowStr):null;
		String skills4nowCn = request.getParameter(PARA_skills4nowCn);
		String skills4nowGotTimeStr = request.getParameter(PARA_skills4nowGotTime);
		Date skills4nowGotTime = !StringHelper.isNullOrEmpty(skills4nowGotTimeStr)?DateTimeHelper.strToDate(skills4nowGotTimeStr, "yyyy-MM-dd"):null;
		String skillsClass4nowStr = request.getParameter(PARA_skillsClass4now);
		Integer skillsClass4now = !StringHelper.isNullOrEmpty(skillsClass4nowStr)?Integer.parseInt(skillsClass4nowStr):null;
		String skillsClass4nowCn = request.getParameter(PARA_skillsClass4nowCn);
		String skillsLevel4nowStr = request.getParameter(PARA_skillsLevel4now);
		Integer skillsLevel4now = !StringHelper.isNullOrEmpty(skillsLevel4nowStr)?Integer.parseInt(skillsLevel4nowStr):null;
		String skillsLevel4nowCn = request.getParameter(PARA_skillsLevel4nowCn);
		String treatLevel4nowStr = request.getParameter(PARA_treatLevel4now);
		Integer treatLevel4now = !StringHelper.isNullOrEmpty(treatLevel4nowStr)?Integer.parseInt(treatLevel4nowStr):null;
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String skills4nowHStartTimeStr = request.getParameter(PARA_skills4nowHStartTime);
		Date skills4nowHStartTime = !StringHelper.isNullOrEmpty(skills4nowHStartTimeStr)?DateTimeHelper.strToDate(skills4nowHStartTimeStr, "yyyy-MM-dd"):null;
		String skills4nowHEndTimeStr = request.getParameter(PARA_skills4nowHEndTime);
		Date skills4nowHEndTime = !StringHelper.isNullOrEmpty(skills4nowHEndTimeStr)?DateTimeHelper.strToDate(skills4nowHEndTimeStr, "yyyy-MM-dd"):null;
		String skillsMax4nowStr = request.getParameter(PARA_skillsMax4now);
		Integer skillsMax4now = !StringHelper.isNullOrEmpty(skillsMax4nowStr)?Integer.parseInt(skillsMax4nowStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmSkillsHeadModel model = new ChPmSkillsHeadModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setSkills4now(null!=skills4now?skills4now:null);
		model.setSkills4nowCn(null!=skills4nowCn && !"".equals(skills4nowCn.trim())?skills4nowCn.trim():null);
		model.setSkills4nowGotTime(null!=skills4nowGotTime?skills4nowGotTime:null);
		model.setSkillsClass4now(null!=skillsClass4now?skillsClass4now:null);
		model.setSkillsClass4nowCn(null!=skillsClass4nowCn && !"".equals(skillsClass4nowCn.trim())?skillsClass4nowCn.trim():null);
		model.setSkillsLevel4now(null!=skillsLevel4now?skillsLevel4now:null);
		model.setSkillsLevel4nowCn(null!=skillsLevel4nowCn && !"".equals(skillsLevel4nowCn.trim())?skillsLevel4nowCn.trim():null);
		model.setTreatLevel4now(null!=treatLevel4now?treatLevel4now:null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setSkills4nowHStartTime(null!=skills4nowHStartTime?skills4nowHStartTime:null);
		model.setSkills4nowHEndTime(null!=skills4nowHEndTime?skills4nowHEndTime:null);
		model.setSkillsMax4now(null!=skillsMax4now?skillsMax4now:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmSkillsHeadModel model) {
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
		String skills4nowStr = request.getParameter(PARA_skills4now);
		Integer skills4now = !StringHelper.isNullOrEmpty(skills4nowStr)?Integer.parseInt(skills4nowStr):null;
		if(null == skills4now)
		{
			stringBuffer.append("输入现技能等级!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skills4nowCn = request.getParameter(PARA_skills4nowCn);
		String skills4nowGotTimeStr = request.getParameter(PARA_skills4nowGotTime);
		Date skills4nowGotTime = !StringHelper.isNullOrEmpty(skills4nowGotTimeStr)?DateTimeHelper.strToDate(skills4nowGotTimeStr, "yyyy-MM-dd"):null;
		String skillsClass4nowStr = request.getParameter(PARA_skillsClass4now);
		Integer skillsClass4now = !StringHelper.isNullOrEmpty(skillsClass4nowStr)?Integer.parseInt(skillsClass4nowStr):null;
		if(null == skillsClass4now)
		{
			stringBuffer.append("输入现技能等级序列（技能分类）!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsClass4nowCn = request.getParameter(PARA_skillsClass4nowCn);
		String skillsLevel4nowStr = request.getParameter(PARA_skillsLevel4now);
		Integer skillsLevel4now = !StringHelper.isNullOrEmpty(skillsLevel4nowStr)?Integer.parseInt(skillsLevel4nowStr):null;
		if(null == skillsLevel4now)
		{
			stringBuffer.append("输入现技能等级级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsLevel4nowCn = request.getParameter(PARA_skillsLevel4nowCn);
		String treatLevel4nowStr = request.getParameter(PARA_treatLevel4now);
		Integer treatLevel4now = !StringHelper.isNullOrEmpty(treatLevel4nowStr)?Integer.parseInt(treatLevel4nowStr):null;
		if(null == treatLevel4now)
		{
			stringBuffer.append("输入现技能等级待遇级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String treatLevel4nowCn = request.getParameter(PARA_treatLevel4nowCn);
		String skills4nowHStartTimeStr = request.getParameter(PARA_skills4nowHStartTime);
		Date skills4nowHStartTime = !StringHelper.isNullOrEmpty(skills4nowHStartTimeStr)?DateTimeHelper.strToDate(skills4nowHStartTimeStr, "yyyy-MM-dd"):null;
		String skills4nowHEndTimeStr = request.getParameter(PARA_skills4nowHEndTime);
		Date skills4nowHEndTime = !StringHelper.isNullOrEmpty(skills4nowHEndTimeStr)?DateTimeHelper.strToDate(skills4nowHEndTimeStr, "yyyy-MM-dd"):null;
		String skillsMax4nowStr = request.getParameter(PARA_skillsMax4now);
		Integer skillsMax4now = !StringHelper.isNullOrEmpty(skillsMax4nowStr)?Integer.parseInt(skillsMax4nowStr):null;
		if(null == skillsMax4now)
		{
			stringBuffer.append("输入是否现最高技能等级  0：否  1：是!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setSkills4now(null!=skills4now?skills4now:null);
		model.setSkills4nowCn(null!=skills4nowCn && !"".equals(skills4nowCn.trim())?skills4nowCn.trim():null);
		model.setSkills4nowGotTime(null!=skills4nowGotTime?skills4nowGotTime:null);
		model.setSkillsClass4now(null!=skillsClass4now?skillsClass4now:null);
		model.setSkillsClass4nowCn(null!=skillsClass4nowCn && !"".equals(skillsClass4nowCn.trim())?skillsClass4nowCn.trim():null);
		model.setSkillsLevel4now(null!=skillsLevel4now?skillsLevel4now:null);
		model.setSkillsLevel4nowCn(null!=skillsLevel4nowCn && !"".equals(skillsLevel4nowCn.trim())?skillsLevel4nowCn.trim():null);
		model.setTreatLevel4now(null!=treatLevel4now?treatLevel4now:null);
		model.setTreatLevel4nowCn(null!=treatLevel4nowCn && !"".equals(treatLevel4nowCn.trim())?treatLevel4nowCn.trim():null);
		model.setSkills4nowHStartTime(null!=skills4nowHStartTime?skills4nowHStartTime:null);
		model.setSkills4nowHEndTime(null!=skills4nowHEndTime?skills4nowHEndTime:null);
		model.setSkillsMax4now(null!=skillsMax4now?skillsMax4now:null);
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
			ChPmSkillsHeadModel model = new ChPmSkillsHeadModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//判断该职工的技能等级Head信息是否存在
			ChPmSkillsHeadModel head = chPmSkillsHeadBiz.queryByWkId(model.getWorkerId());
			if(null != head) return LayAjaxHelper.fail("该职工的现技能信息已存在！");
			//增加
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			chPmSkillsHeadBiz.save(model);
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
			
			ChPmSkillsHeadModel model = chPmSkillsHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载技能等级(技术工种)信息头表失败！error:",e);
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
			ChPmSkillsHeadModel model = null;
			ChPmSkillsHeadModel model1 = chPmSkillsHeadBiz.queryByWkId(wkId);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmSkillsHeadModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(null != model1)
				chPmSkillsHeadBiz.edit(model);
			else
				chPmSkillsHeadBiz.save(model);
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
			ChPmSkillsHeadModel model = chPmSkillsHeadBiz.queryById(id);
			if(model != null)
			{
				chPmSkillsHeadBiz.delById4Logic(id);
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
			chPmSkillsHeadBiz.delByIds4Logic(ids);
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
		ChPmSkillsHeadModel model = null;
		try {
			model = chPmSkillsHeadBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载技能等级(技术工种)信息头表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
