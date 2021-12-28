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
import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
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
 * Description: [技能等级（技术工种）表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmSkillsInfo/*")
public class ChPmSkillsInfoController extends BaseController {
	
	public final static String Module_Name = "技能等级（技术工种）表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmSkillsHeadBiz chPmSkillsHeadBiz;
	@Autowired
	private IChPmSkillsInfoBiz chPmSkillsInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_skills = "skills";
	private	final static String PARA_skillsCn = "skillsCn";
	private	final static String PARA_skillsGotTime = "skillsGotTime";
	private	final static String PARA_skillsClass = "skillsClass";
	private	final static String PARA_skillsClassCn = "skillsClassCn";
	private	final static String PARA_skillsLevel = "skillsLevel";
	private	final static String PARA_skillsLevelCn = "skillsLevelCn";
	private	final static String PARA_skillsMax = "skillsMax";
	private	final static String PARA_skillsOrder = "skillsOrder";
	private	final static String PARA_skillsCertNo = "skillsCertNo";
	private	final static String PARA_skillsOnitNo = "skillsOnitNo";
	private	final static String PARA_skillsHireNo = "skillsHireNo";
	private	final static String PARA_skillsHStartTime = "skillsHStartTime";
	private	final static String PARA_skillsHEndTime = "skillsHEndTime";
	private	final static String PARA_skillsHCycle = "skillsHCycle";
	private	final static String PARA_skillsOth = "skillsOth";
	private	final static String PARA_skillsOthCn = "skillsOthCn";
	private	final static String PARA_skillsOthGotTime = "skillsOthGotTime";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,YN,skills,skillsClass,skillsLevel";
	
	/**
	 * <p>Discription:[技能等级（技术工种）表请求首页]</p>
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
		
		//加载技能等级现信息
		ChPmSkillsHeadModel head = chPmSkillsHeadBiz.queryByWkId(wkId);
		modelMap.addAttribute("model", head);
				
		//字典数据加载
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[技能等级（技术工种）表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmSkillsInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmSkillsInfoModel> retBody = new Result4Page<ChPmSkillsInfoModel>();
		//查询参数
		Page.Builder<ChPmSkillsInfoModel> builder = new Page.Builder<ChPmSkillsInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmSkillsInfoModel> page = builder.build();
		try {
			//分页
			chPmSkillsInfoBiz.queryPage(page);
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
	 * <p>Discription:[技能等级（技术工种）表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmSkillsInfoModel> builder) {
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
		String skillsStr = request.getParameter(PARA_skills);
		Integer skills = !StringHelper.isNullOrEmpty(skillsStr)?Integer.parseInt(skillsStr):null;
		String skillsCn = request.getParameter(PARA_skillsCn);
		String skillsGotTimeStr = request.getParameter(PARA_skillsGotTime);
		Date skillsGotTime = !StringHelper.isNullOrEmpty(skillsGotTimeStr)?DateTimeHelper.strToDate(skillsGotTimeStr, "yyyy-MM-dd"):null;
		String skillsClassStr = request.getParameter(PARA_skillsClass);
		Integer skillsClass = !StringHelper.isNullOrEmpty(skillsClassStr)?Integer.parseInt(skillsClassStr):null;
		String skillsClassCn = request.getParameter(PARA_skillsClassCn);
		String skillsLevelStr = request.getParameter(PARA_skillsLevel);
		Integer skillsLevel = !StringHelper.isNullOrEmpty(skillsLevelStr)?Integer.parseInt(skillsLevelStr):null;
		String skillsLevelCn = request.getParameter(PARA_skillsLevelCn);
		String skillsMaxStr = request.getParameter(PARA_skillsMax);
		Integer skillsMax = !StringHelper.isNullOrEmpty(skillsMaxStr)?Integer.parseInt(skillsMaxStr):null;
		String skillsOrderStr = request.getParameter(PARA_skillsOrder);
		Integer skillsOrder = !StringHelper.isNullOrEmpty(skillsOrderStr)?Integer.parseInt(skillsOrderStr):null;
		String skillsCertNo = request.getParameter(PARA_skillsCertNo);
		String skillsOnitNo = request.getParameter(PARA_skillsOnitNo);
		String skillsHireNo = request.getParameter(PARA_skillsHireNo);
		String skillsHStartTimeStr = request.getParameter(PARA_skillsHStartTime);
		Date skillsHStartTime = !StringHelper.isNullOrEmpty(skillsHStartTimeStr)?DateTimeHelper.strToDate(skillsHStartTimeStr, "yyyy-MM-dd"):null;
		String skillsHEndTimeStr = request.getParameter(PARA_skillsHEndTime);
		Date skillsHEndTime = !StringHelper.isNullOrEmpty(skillsHEndTimeStr)?DateTimeHelper.strToDate(skillsHEndTimeStr, "yyyy-MM-dd"):null;
		String skillsHCycleStr = request.getParameter(PARA_skillsHCycle);
		Integer skillsHCycle = !StringHelper.isNullOrEmpty(skillsHCycleStr)?Integer.parseInt(skillsHCycleStr):null;
		String skillsOthStr = request.getParameter(PARA_skillsOth);
		Integer skillsOth = !StringHelper.isNullOrEmpty(skillsOthStr)?Integer.parseInt(skillsOthStr):null;
		String skillsOthCn = request.getParameter(PARA_skillsOthCn);
		String skillsOthGotTimeStr = request.getParameter(PARA_skillsOthGotTime);
		Date skillsOthGotTime = !StringHelper.isNullOrEmpty(skillsOthGotTimeStr)?DateTimeHelper.strToDate(skillsOthGotTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmSkillsInfoModel model = new ChPmSkillsInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setSkills(null!=skills?skills:null);
		model.setSkillsCn(null!=skillsCn && !"".equals(skillsCn.trim())?skillsCn.trim():null);
		model.setSkillsGotTime(null!=skillsGotTime?skillsGotTime:null);
		model.setSkillsClass(null!=skillsClass?skillsClass:null);
		model.setSkillsClassCn(null!=skillsClassCn && !"".equals(skillsClassCn.trim())?skillsClassCn.trim():null);
		model.setSkillsLevel(null!=skillsLevel?skillsLevel:null);
		model.setSkillsLevelCn(null!=skillsLevelCn && !"".equals(skillsLevelCn.trim())?skillsLevelCn.trim():null);
		model.setSkillsMax(null!=skillsMax?skillsMax:null);
		model.setSkillsOrder(null!=skillsOrder?skillsOrder:null);
		model.setSkillsCertNo(null!=skillsCertNo && !"".equals(skillsCertNo.trim())?skillsCertNo.trim():null);
		model.setSkillsOnitNo(null!=skillsOnitNo && !"".equals(skillsOnitNo.trim())?skillsOnitNo.trim():null);
		model.setSkillsHireNo(null!=skillsHireNo && !"".equals(skillsHireNo.trim())?skillsHireNo.trim():null);
		model.setSkillsHStartTime(null!=skillsHStartTime?skillsHStartTime:null);
		model.setSkillsHEndTime(null!=skillsHEndTime?skillsHEndTime:null);
		model.setSkillsHCycle(null!=skillsHCycle?skillsHCycle:null);
		model.setSkillsOth(null!=skillsOth?skillsOth:null);
		model.setSkillsOthCn(null!=skillsOthCn && !"".equals(skillsOthCn.trim())?skillsOthCn.trim():null);
		model.setSkillsOthGotTime(null!=skillsOthGotTime?skillsOthGotTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[技能等级（技术工种）表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmSkillsInfoModel model) {
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
		String skillsStr = request.getParameter(PARA_skills);
		Integer skills = !StringHelper.isNullOrEmpty(skillsStr)?Integer.parseInt(skillsStr):null;
		if(null == skills)
		{
			stringBuffer.append("输入技能等级!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsCn = request.getParameter(PARA_skillsCn);
		String skillsGotTimeStr = request.getParameter(PARA_skillsGotTime);
		Date skillsGotTime = !StringHelper.isNullOrEmpty(skillsGotTimeStr)?DateTimeHelper.strToDate(skillsGotTimeStr, "yyyy-MM-dd"):null;
		String skillsClassStr = request.getParameter(PARA_skillsClass);
		Integer skillsClass = !StringHelper.isNullOrEmpty(skillsClassStr)?Integer.parseInt(skillsClassStr):null;
		if(null == skillsClass)
		{
			stringBuffer.append("输入技能等级序列!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsClassCn = request.getParameter(PARA_skillsClassCn);
		String skillsLevelStr = request.getParameter(PARA_skillsLevel);
		Integer skillsLevel = !StringHelper.isNullOrEmpty(skillsLevelStr)?Integer.parseInt(skillsLevelStr):null;
		if(null == skillsLevel)
		{
			stringBuffer.append("输入技能等级级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsLevelCn = request.getParameter(PARA_skillsLevelCn);
		String skillsMaxStr = request.getParameter(PARA_skillsMax);
		Integer skillsMax = !StringHelper.isNullOrEmpty(skillsMaxStr)?Integer.parseInt(skillsMaxStr):null;
		if(null == skillsMax)
		{
			stringBuffer.append("输入是否最高职称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsOrderStr = request.getParameter(PARA_skillsOrder);
		Integer skillsOrder = !StringHelper.isNullOrEmpty(skillsOrderStr)?Integer.parseInt(skillsOrderStr):null;
		String skillsCertNo = request.getParameter(PARA_skillsCertNo);
		if(StringHelper.isNullOrEmpty(skillsCertNo))
		{
			stringBuffer.append("输入技能证书编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsOnitNo = request.getParameter(PARA_skillsOnitNo);
		if(StringHelper.isNullOrEmpty(skillsOnitNo))
		{
			stringBuffer.append("输入技能任职文号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsHireNo = request.getParameter(PARA_skillsHireNo);
		if(StringHelper.isNullOrEmpty(skillsHireNo))
		{
			stringBuffer.append("输入技能初聘文号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsHStartTimeStr = request.getParameter(PARA_skillsHStartTime);
		Date skillsHStartTime = !StringHelper.isNullOrEmpty(skillsHStartTimeStr)?DateTimeHelper.strToDate(skillsHStartTimeStr, "yyyy-MM-dd"):null;
		String skillsHEndTimeStr = request.getParameter(PARA_skillsHEndTime);
		Date skillsHEndTime = !StringHelper.isNullOrEmpty(skillsHEndTimeStr)?DateTimeHelper.strToDate(skillsHEndTimeStr, "yyyy-MM-dd"):null;
		String skillsHCycleStr = request.getParameter(PARA_skillsHCycle);
		Integer skillsHCycle = !StringHelper.isNullOrEmpty(skillsHCycleStr)?Integer.parseInt(skillsHCycleStr):null;
		if(null == skillsHCycle)
		{
			stringBuffer.append("输入技能聘任周期!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String skillsOthStr = request.getParameter(PARA_skillsOth);
		Integer skillsOth = !StringHelper.isNullOrEmpty(skillsOthStr)?Integer.parseInt(skillsOthStr):null;
		String skillsOthCn = request.getParameter(PARA_skillsOthCn);
		String skillsOthGotTimeStr = request.getParameter(PARA_skillsOthGotTime);
		Date skillsOthGotTime = !StringHelper.isNullOrEmpty(skillsOthGotTimeStr)?DateTimeHelper.strToDate(skillsOthGotTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setSkills(null!=skills?skills:null);
		model.setSkillsCn(null!=skillsCn && !"".equals(skillsCn.trim())?skillsCn.trim():null);
		model.setSkillsGotTime(null!=skillsGotTime?skillsGotTime:null);
		model.setSkillsClass(null!=skillsClass?skillsClass:null);
		model.setSkillsClassCn(null!=skillsClassCn && !"".equals(skillsClassCn.trim())?skillsClassCn.trim():null);
		model.setSkillsLevel(null!=skillsLevel?skillsLevel:null);
		model.setSkillsLevelCn(null!=skillsLevelCn && !"".equals(skillsLevelCn.trim())?skillsLevelCn.trim():null);
		model.setSkillsMax(null!=skillsMax?skillsMax:null);
		model.setSkillsOrder(null!=skillsOrder?skillsOrder:null);
		model.setSkillsCertNo(null!=skillsCertNo && !"".equals(skillsCertNo.trim())?skillsCertNo.trim():null);
		model.setSkillsOnitNo(null!=skillsOnitNo && !"".equals(skillsOnitNo.trim())?skillsOnitNo.trim():null);
		model.setSkillsHireNo(null!=skillsHireNo && !"".equals(skillsHireNo.trim())?skillsHireNo.trim():null);
		model.setSkillsHStartTime(null!=skillsHStartTime?skillsHStartTime:null);
		model.setSkillsHEndTime(null!=skillsHEndTime?skillsHEndTime:null);
		model.setSkillsHCycle(null!=skillsHCycle?skillsHCycle:null);
		model.setSkillsOth(null!=skillsOth?skillsOth:null);
		model.setSkillsOthCn(null!=skillsOthCn && !"".equals(skillsOthCn.trim())?skillsOthCn.trim():null);
		model.setSkillsOthGotTime(null!=skillsOthGotTime?skillsOthGotTime:null);
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
			ChPmSkillsInfoModel model = new ChPmSkillsInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			
			//校验是否存在最高技能等级
			if(DictionaryUtil.KEY_NOT_NORMAL == model.getSkillsMax())
			{
				ChPmSkillsInfoModel tmp = new ChPmSkillsInfoModel();
				tmp.setStatus(DictionaryUtil.KEY_NORMAL);
				tmp.setSkillsMax(DictionaryUtil.KEY_NOT_NORMAL);
				tmp.setWorkerId(model.getWorkerId());
				Long count = chPmSkillsInfoBiz.queryCount(tmp);
				if(count>0) return LayAjaxHelper.fail("已存在最高技能等级！");
			}
			//增加
			chPmSkillsInfoBiz.save(model);
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
			fillCommonDict(modelMap);
			
			ChPmSkillsInfoModel model = chPmSkillsInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载技能等级（技术工种）表失败！error:",e);
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
			ChPmSkillsInfoModel model = chPmSkillsInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmSkillsInfoBiz.edit(model);
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
			ChPmSkillsInfoModel model = chPmSkillsInfoBiz.queryById(id);
			if(model != null)
			{
				chPmSkillsInfoBiz.delById4Logic(id);
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
			chPmSkillsInfoBiz.delByIds4Logic(ids);
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
		ChPmSkillsInfoModel model = null;
		try {
			model = chPmSkillsInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载技能等级（技术工种）表失败！error:",e);
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
