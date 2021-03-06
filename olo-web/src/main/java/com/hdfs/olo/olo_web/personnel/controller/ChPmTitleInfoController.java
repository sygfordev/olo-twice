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
import com.hdfs.olo.olo_web.personnel.biz.IChPmTitleHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmTitleInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleInfoModel;
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
 * Description: [???????????????????????????]
 * Created on 2021???03???29???
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021??? xxxx
 */
@Controller
@RequestMapping("/personnel/chPmTitleInfo/*")
public class ChPmTitleInfoController extends BaseController {
	
	public final static String Module_Name = "???????????????";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmTitleHeadBiz chPmTitleHeadBiz;
	@Autowired
	private IChPmTitleInfoBiz chPmTitleInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_title = "title";
	private	final static String PARA_titleCn = "titleCn";
	private	final static String PARA_titleLevel = "titleLevel";
	private	final static String PARA_titleLevelCn = "titleLevelCn";
	private	final static String PARA_titleClass = "titleClass";
	private	final static String PARA_titleClassCn = "titleClassCn";
	private	final static String PARA_titleMax = "titleMax";
	private	final static String PARA_titleOrder = "titleOrder";
	private	final static String PARA_titleCertNo = "titleCertNo";
	private	final static String PARA_titleOnitNo = "titleOnitNo";
	private	final static String PARA_titleHireNo = "titleHireNo";
	private	final static String PARA_titleHStartTime = "titleHStartTime";
	private	final static String PARA_titleHEndTime = "titleHEndTime";
	private	final static String PARA_titleHCycle = "titleHCycle";
	private	final static String PARA_titleOth = "titleOth";
	private	final static String PARA_titleOthCn = "titleOthCn";
	private	final static String PARA_titleOthGotTime = "titleOthGotTime";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,YN,title,titleLevel,titleClass";
	
	/**
	 * <p>Discription:[???????????????????????????]</p>
	 * Created on 2021???03???29???												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//?????????????????????????????????
		String wkIdStr = request.getParameter("wkId");
		Long wkId = !StringHelper.isNullOrEmpty(wkIdStr)?Long.parseLong(wkIdStr):null;
		if(null == wkId) throw new Exception("??????????????????");
		modelMap.addAttribute("wkId", wkId);
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("oper", oper);
		
		//?????????????????????
		ChPmTitleHeadModel head = chPmTitleHeadBiz.queryByWkId(wkId);
		modelMap.addAttribute("model", head);
		
		//??????????????????
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[?????????????????????????????????-??????]</p>
	 * Created on 2021???03???29???								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmTitleInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmTitleInfoModel> retBody = new Result4Page<ChPmTitleInfoModel>();
		//????????????
		Page.Builder<ChPmTitleInfoModel> builder = new Page.Builder<ChPmTitleInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmTitleInfoModel> page = builder.build();
		try {
			//??????
			chPmTitleInfoBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("???????????????");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("???????????????");
			logger.error("???????????????error:",e);
		}
		return retBody;
	}

	/**
	 * <p>Discription:[?????????????????????????????????]</p>
	 * @param request????????????
	 * @param builder???????????????
	 * Created on 2021???04???10???								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmTitleInfoModel> builder) {
		//????????????????????????
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//??????????????????
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		String titleStr = request.getParameter(PARA_title);
		Integer title = !StringHelper.isNullOrEmpty(titleStr)?Integer.parseInt(titleStr):null;
		String titleCn = request.getParameter(PARA_titleCn);
		String titleLevel = request.getParameter(PARA_titleLevel);
		String titleLevelCn = request.getParameter(PARA_titleLevelCn);
		String titleClassStr = request.getParameter(PARA_titleClass);
		Integer titleClass = !StringHelper.isNullOrEmpty(titleClassStr)?Integer.parseInt(titleClassStr):null;
		String titleClassCn = request.getParameter(PARA_titleClassCn);
		String titleMaxStr = request.getParameter(PARA_titleMax);
		Integer titleMax = !StringHelper.isNullOrEmpty(titleMaxStr)?Integer.parseInt(titleMaxStr):null;
		String titleOrderStr = request.getParameter(PARA_titleOrder);
		Integer titleOrder = !StringHelper.isNullOrEmpty(titleOrderStr)?Integer.parseInt(titleOrderStr):null;
		String titleCertNo = request.getParameter(PARA_titleCertNo);
		String titleOnitNo = request.getParameter(PARA_titleOnitNo);
		String titleHireNo = request.getParameter(PARA_titleHireNo);
		String titleHStartTimeStr = request.getParameter(PARA_titleHStartTime);
		Date titleHStartTime = !StringHelper.isNullOrEmpty(titleHStartTimeStr)?DateTimeHelper.strToDate(titleHStartTimeStr, "yyyy-MM-dd"):null;
		String titleHEndTimeStr = request.getParameter(PARA_titleHEndTime);
		Date titleHEndTime = !StringHelper.isNullOrEmpty(titleHEndTimeStr)?DateTimeHelper.strToDate(titleHEndTimeStr, "yyyy-MM-dd"):null;
		String titleHCycleStr = request.getParameter(PARA_titleHCycle);
		Integer titleHCycle = !StringHelper.isNullOrEmpty(titleHCycleStr)?Integer.parseInt(titleHCycleStr):null;
		String titleOthStr = request.getParameter(PARA_titleOth);
		Integer titleOth = !StringHelper.isNullOrEmpty(titleOthStr)?Integer.parseInt(titleOthStr):null;
		String titleOthCn = request.getParameter(PARA_titleOthCn);
		String titleOthGotTimeStr = request.getParameter(PARA_titleOthGotTime);
		Date titleOthGotTime = !StringHelper.isNullOrEmpty(titleOthGotTimeStr)?DateTimeHelper.strToDate(titleOthGotTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmTitleInfoModel model = new ChPmTitleInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setTitle(null!=title?title:null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setTitleLevel(null!=titleLevel && !"".equals(titleLevel.trim())?titleLevel.trim():null);
		model.setTitleLevelCn(null!=titleLevelCn && !"".equals(titleLevelCn.trim())?titleLevelCn.trim():null);
		model.setTitleClass(null!=titleClass?titleClass:null);
		model.setTitleClassCn(null!=titleClassCn && !"".equals(titleClassCn.trim())?titleClassCn.trim():null);
		model.setTitleMax(null!=titleMax?titleMax:null);
		model.setTitleOrder(null!=titleOrder?titleOrder:null);
		model.setTitleCertNo(null!=titleCertNo && !"".equals(titleCertNo.trim())?titleCertNo.trim():null);
		model.setTitleOnitNo(null!=titleOnitNo && !"".equals(titleOnitNo.trim())?titleOnitNo.trim():null);
		model.setTitleHireNo(null!=titleHireNo && !"".equals(titleHireNo.trim())?titleHireNo.trim():null);
		model.setTitleHStartTime(null!=titleHStartTime?titleHStartTime:null);
		model.setTitleHEndTime(null!=titleHEndTime?titleHEndTime:null);
		model.setTitleHCycle(null!=titleHCycle?titleHCycle:null);
		model.setTitleOth(null!=titleOth?titleOth:null);
		model.setTitleOthCn(null!=titleOthCn && !"".equals(titleOthCn.trim())?titleOthCn.trim():null);
		model.setTitleOthGotTime(null!=titleOthGotTime?titleOthGotTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //???????????????
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[?????????????????????]</p>
	 * @param request????????????
	 * @param type  0:?????? 1?????????
	 * @return Map key???true/false  val???
	 * Created on 2021???04???10???								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmTitleInfoModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//??????????????????
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		if(null == workerId)
		{
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String titleStr = request.getParameter(PARA_title);
		Integer title = !StringHelper.isNullOrEmpty(titleStr)?Integer.parseInt(titleStr):null;
		if(null == title)
		{
			stringBuffer.append("????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String titleCn = request.getParameter(PARA_titleCn);
		String titleLevel = request.getParameter(PARA_titleLevel);
		if(StringHelper.isNullOrEmpty(titleLevel))
		{
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String titleLevelCn = request.getParameter(PARA_titleLevelCn);
		String titleClassStr = request.getParameter(PARA_titleClass);
		Integer titleClass = !StringHelper.isNullOrEmpty(titleClassStr)?Integer.parseInt(titleClassStr):null;
//		if(null == titleClass)
//		{
//			stringBuffer.append("????????????????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleClassCn = request.getParameter(PARA_titleClassCn);
		String titleMaxStr = request.getParameter(PARA_titleMax);
		Integer titleMax = !StringHelper.isNullOrEmpty(titleMaxStr)?Integer.parseInt(titleMaxStr):null;
//		if(null == titleMax)
//		{
//			stringBuffer.append("????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleOrderStr = request.getParameter(PARA_titleOrder);
		Integer titleOrder = !StringHelper.isNullOrEmpty(titleOrderStr)?Integer.parseInt(titleOrderStr):null;
		String titleCertNo = request.getParameter(PARA_titleCertNo);
//		if(StringHelper.isNullOrEmpty(titleCertNo))
//		{
//			stringBuffer.append("????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleOnitNo = request.getParameter(PARA_titleOnitNo);
//		if(StringHelper.isNullOrEmpty(titleOnitNo))
//		{
//			stringBuffer.append("????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleHireNo = request.getParameter(PARA_titleHireNo);
//		if(StringHelper.isNullOrEmpty(titleHireNo))
//		{
//			stringBuffer.append("????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleHStartTimeStr = request.getParameter(PARA_titleHStartTime);
		Date titleHStartTime = !StringHelper.isNullOrEmpty(titleHStartTimeStr)?DateTimeHelper.strToDate(titleHStartTimeStr, "yyyy-MM-dd"):null;
		String titleHEndTimeStr = request.getParameter(PARA_titleHEndTime);
		Date titleHEndTime = !StringHelper.isNullOrEmpty(titleHEndTimeStr)?DateTimeHelper.strToDate(titleHEndTimeStr, "yyyy-MM-dd"):null;
		String titleHCycleStr = request.getParameter(PARA_titleHCycle);
		Integer titleHCycle = !StringHelper.isNullOrEmpty(titleHCycleStr)?Integer.parseInt(titleHCycleStr):null;
//		if(null == titleHCycle)
//		{
//			stringBuffer.append("????????????????????????!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String titleOthStr = request.getParameter(PARA_titleOth);
		Integer titleOth = !StringHelper.isNullOrEmpty(titleOthStr)?Integer.parseInt(titleOthStr):null;
		String titleOthCn = request.getParameter(PARA_titleOthCn);
		String titleOthGotTimeStr = request.getParameter(PARA_titleOthGotTime);
		Date titleOthGotTime = !StringHelper.isNullOrEmpty(titleOthGotTimeStr)?DateTimeHelper.strToDate(titleOthGotTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setTitle(null!=title?title:null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setTitleLevel(null!=titleLevel && !"".equals(titleLevel.trim())?titleLevel.trim():null);
		model.setTitleLevelCn(null!=titleLevelCn && !"".equals(titleLevelCn.trim())?titleLevelCn.trim():null);
		model.setTitleClass(null!=titleClass?titleClass:null);
		model.setTitleClassCn(null!=titleClassCn && !"".equals(titleClassCn.trim())?titleClassCn.trim():null);
		model.setTitleMax(null!=titleMax?titleMax:null);
		model.setTitleOrder(null!=titleOrder?titleOrder:null);
		model.setTitleCertNo(null!=titleCertNo && !"".equals(titleCertNo.trim())?titleCertNo.trim():null);
		model.setTitleOnitNo(null!=titleOnitNo && !"".equals(titleOnitNo.trim())?titleOnitNo.trim():null);
		model.setTitleHireNo(null!=titleHireNo && !"".equals(titleHireNo.trim())?titleHireNo.trim():null);
		model.setTitleHStartTime(null!=titleHStartTime?titleHStartTime:null);
		model.setTitleHEndTime(null!=titleHEndTime?titleHEndTime:null);
		model.setTitleHCycle(null!=titleHCycle?titleHCycle:null);
		model.setTitleOth(null!=titleOth?titleOth:null);
		model.setTitleOthCn(null!=titleOthCn && !"".equals(titleOthCn.trim())?titleOthCn.trim():null);
		model.setTitleOthGotTime(null!=titleOthGotTime?titleOthGotTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * ??????????????????
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap,Long wkId) throws Exception{
		if(null == wkId) throw new Exception("????????????????????????");
		modelMap.addAttribute("wkId", wkId);
		//??????????????????
		fillCommonDict(modelMap);
	}
	
	/**
	 * ????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmTitleInfoModel model = new ChPmTitleInfoModel();
			//????????????
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//??????????????????????????????
			if(DictionaryUtil.KEY_NOT_NORMAL == model.getTitleMax())
			{
				ChPmTitleInfoModel tmp = new ChPmTitleInfoModel();
				tmp.setStatus(DictionaryUtil.KEY_NORMAL);
				tmp.setTitleMax(DictionaryUtil.KEY_NOT_NORMAL);
				tmp.setWorkerId(model.getWorkerId());
				Long count = chPmTitleInfoBiz.queryCount(tmp);
				if(count>0) return LayAjaxHelper.fail("????????????????????????");
			}
			//??????
			chPmTitleInfoBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"??????:" + model+",??????:"+model);
			//??????????????????
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("???????????????error:",ex);
		}
		return map;
	}
	
	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			//??????????????????
			fillCommonDict(modelMap);
			ChPmTitleInfoModel model = chPmTitleInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("??????????????????????????????error:",e);
		}
	}
	
	/**
	 *  ????????????
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
			ChPmTitleInfoModel model = chPmTitleInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmTitleInfoBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"??????:" + model.getId()+",??????:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//????????????
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("???????????????error:",ex);
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("id")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("????????????????????????!");
			ChPmTitleInfoModel model = chPmTitleInfoBiz.queryById(id);
			if(model != null)
			{
				chPmTitleInfoBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"??????:" + model+",??????:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("???????????????error:",ex);
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("???????????????!");
			chPmTitleInfoBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"??????:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("?????????????????????error:",ex);
		}
		return map;
	}
	
	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		ChPmTitleInfoModel model = null;
		try {
			model = chPmTitleInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"??????:" + model+",??????:" + model);
		}catch(Exception e)
		{
			logger.error("??????????????????????????????error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"??????:" + model+",??????:" + model);
		}
		
	}
	//********************?????????????????????***********************
	/**
	 * ???????????????????????????Map
	 * @param id
	 * @param modelMap
	 */
	private void fillCommonDict(ModelMap modelMap) throws Exception
	{
		//??????????????????
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
