package com.hdfs.olo.olo_web.central.controller;

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

import com.hdfs.olo.olo_web.central.biz.IAlarmSetBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.model.AlarmSetModel;
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
 * Description: [警报设置服务实现]
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/central/alarmSet/*")
public class AlarmSetController extends BaseController {
	
	public final static String Module_Name = "警报设置";
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IAlarmSetBiz alarmSetBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_alarmType = "alarmType";
	private	final static String PARA_alarmAdSize = "alarmAdSize";
	private	final static String PARA_alarmAdUnit = "alarmAdUnit";
	private	final static String PARA_alarmTeInfo = "alarmTeInfo";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "status,alarmType";
	
	/**
	 * <p>Discription:[警报设置请求首页]</p>
	 * Created on 2021年09月04日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[警报设置首页数据加载-分页]</p>
	 * Created on 2021年09月04日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<AlarmSetModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<AlarmSetModel> retBody = new Result4Page<AlarmSetModel>();
		//查询参数
		Page.Builder<AlarmSetModel> builder = new Page.Builder<AlarmSetModel>();
		setSearchParameters(request, builder);
		Page<AlarmSetModel> page = builder.build();
		try {
			//分页
			alarmSetBiz.queryPage(page);
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
	 * <p>Discription:[警报设置设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年09月04日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<AlarmSetModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String alarmTypeStr = request.getParameter(PARA_alarmType);
		Integer alarmType = !StringHelper.isNullOrEmpty(alarmTypeStr)?Integer.parseInt(alarmTypeStr):null;
		String alarmAdSizeStr = request.getParameter(PARA_alarmAdSize);
		Integer alarmAdSize = !StringHelper.isNullOrEmpty(alarmAdSizeStr)?Integer.parseInt(alarmAdSizeStr):null;
		String alarmAdUnit = request.getParameter(PARA_alarmAdUnit);
		String alarmTeInfo = request.getParameter(PARA_alarmTeInfo);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		AlarmSetModel model = new AlarmSetModel();
		model.setId(null!=id?id:null);
		model.setAlarmType(null!=alarmType?alarmType:null);
		model.setAlarmAdSize(null!=alarmAdSize?alarmAdSize:null);
		model.setAlarmAdUnit(null!=alarmAdUnit && !"".equals(alarmAdUnit.trim())?alarmAdUnit.trim():null);
		model.setAlarmTeInfo(null!=alarmTeInfo && !"".equals(alarmTeInfo.trim())?alarmTeInfo.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[警报设置校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年09月04日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, AlarmSetModel model) {
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
		String alarmTypeStr = request.getParameter(PARA_alarmType);
		Integer alarmType = !StringHelper.isNullOrEmpty(alarmTypeStr)?Integer.parseInt(alarmTypeStr):null;
		if(null == alarmType)
		{
			stringBuffer.append("输入警报类型!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String alarmAdSizeStr = request.getParameter(PARA_alarmAdSize);
		Integer alarmAdSize = !StringHelper.isNullOrEmpty(alarmAdSizeStr)?Integer.parseInt(alarmAdSizeStr):null;
		if(null == alarmAdSize)
		{
			stringBuffer.append("输入提前量!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String alarmAdUnit = request.getParameter(PARA_alarmAdUnit);
		if(StringHelper.isNullOrEmpty(alarmAdUnit))
		{
			stringBuffer.append("输入提前单位!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String alarmTeInfo = request.getParameter(PARA_alarmTeInfo);
		if(StringHelper.isNullOrEmpty(alarmTeInfo))
		{
			stringBuffer.append("输入警报样板!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?0:status;
//		if(null == status)
//		{
//			stringBuffer.append("输入状态!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setAlarmType(null!=alarmType?alarmType:null);
		model.setAlarmAdSize(null!=alarmAdSize?alarmAdSize:null);
		model.setAlarmAdUnit(null!=alarmAdUnit && !"".equals(alarmAdUnit.trim())?alarmAdUnit.trim():null);
		model.setAlarmTeInfo(null!=alarmTeInfo && !"".equals(alarmTeInfo.trim())?alarmTeInfo.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//填充字典数据
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
			AlarmSetModel model = new AlarmSetModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//校验
			AlarmSetModel va = new AlarmSetModel();
			va.setAlarmType(model.getAlarmType());
			va.setStatus(DictionaryUtil.KEY_NORMAL);
			List<AlarmSetModel> valist = alarmSetBiz.queryList(va);
			if(null != valist && valist.size()>0) {
				return LayAjaxHelper.fail("该警报类型的配置已存在！");
			}
			
			//增加
			alarmSetBiz.save(model);
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
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) throw new Exception("编辑数据时，主键为空!");
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			
			AlarmSetModel model = alarmSetBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载警报设置失败！error:",e);
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
			if(null == id) return LayAjaxHelper.fail("主键编号为空!");
			StringBuffer stringBuffer = new StringBuffer();
			AlarmSetModel model = alarmSetBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			
			//校验
			AlarmSetModel va = new AlarmSetModel();
			va.setAlarmType(model.getAlarmType());
			va.setStatus(DictionaryUtil.KEY_NORMAL);
			List<AlarmSetModel> valist = alarmSetBiz.queryList(va);
			if(null != valist && valist.size()>0 && valist.get(0).getId() != model.getId()) {
				return LayAjaxHelper.fail("该警报类型的配置已存在！");
			}
			
			
			alarmSetBiz.edit(model);
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
			AlarmSetModel model = alarmSetBiz.queryById(id);
			if(model != null)
			{
				alarmSetBiz.delById4Logic(id);
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
			alarmSetBiz.delByIds4Logic(ids);
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
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) throws Exception{
		AlarmSetModel model = null;
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			//加载详情对象
			model = alarmSetBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载警报设置失败！error:",e);
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
		List<ComboxItem> timeboxs = new ArrayList<ComboxItem>();
		timeboxs.add(new ComboxItem(0,"天"));
		modelMap.addAttribute("timeUnitList",timeboxs);
	}
}
