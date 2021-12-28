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
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosDepartBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmTransInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTransInfoModel;
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
 * Description: [调动信息表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmTransInfo/*")
public class ChPmTransInfoController extends BaseController {
	
	public final static String Module_Name = "调动信息";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmTransInfoBiz chPmTransInfoBiz;
	@Autowired
	private IChPmHosDepartBiz chPmHosDepartBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_name = "name";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_departBefore = "departBefore";
	private	final static String PARA_departBeforeCn = "departBeforeCn";
	private	final static String PARA_positBefore = "positBefore";
	private	final static String PARA_positBeforeCn = "positBeforeCn";
	private	final static String PARA_departAfter = "departAfter";
	private	final static String PARA_departAfterCn = "departAfterCn";
	private	final static String PARA_positAfter = "positAfter";
	private	final static String PARA_positAfterCn = "positAfterCn";
	private	final static String PARA_transDocno = "transDocno";
	private	final static String PARA_transTime = "transTime";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "YN,stationType";
	
	/**
	 * <p>Discription:[调动信息请求首页]</p>
	 * Created on 2021年04月15日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[调动信息首页数据加载-分页]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmTransInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmTransInfoModel> retBody = new Result4Page<ChPmTransInfoModel>();
		//查询参数
		Page.Builder<ChPmTransInfoModel> builder = new Page.Builder<ChPmTransInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmTransInfoModel> page = builder.build();
		try {
			//分页
			chPmTransInfoBiz.queryPage(page);
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
	 * <p>Discription:[调动信息设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmTransInfoModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String name = request.getParameter(PARA_name);
		String cardNo = request.getParameter(PARA_cardNo);
		String departBeforeStr = request.getParameter(PARA_departBefore);
		Integer departBefore = !StringHelper.isNullOrEmpty(departBeforeStr)?Integer.parseInt(departBeforeStr):null;
		String departBeforeCn = request.getParameter(PARA_departBeforeCn);
		String positBeforeStr = request.getParameter(PARA_positBefore);
		Integer positBefore = !StringHelper.isNullOrEmpty(positBeforeStr)?Integer.parseInt(positBeforeStr):null;
		String positBeforeCn = request.getParameter(PARA_positBeforeCn);
		String departAfterStr = request.getParameter(PARA_departAfter);
		Integer departAfter = !StringHelper.isNullOrEmpty(departAfterStr)?Integer.parseInt(departAfterStr):null;
		String departAfterCn = request.getParameter(PARA_departAfterCn);
		String positAfterStr = request.getParameter(PARA_positAfter);
		Integer positAfter = !StringHelper.isNullOrEmpty(positAfterStr)?Integer.parseInt(positAfterStr):null;
		String positAfterCn = request.getParameter(PARA_positAfterCn);
		String transDocno = request.getParameter(PARA_transDocno);
		String transTimeStr = request.getParameter(PARA_transTime);
		Date transTime = !StringHelper.isNullOrEmpty(transTimeStr)?DateTimeHelper.strToDate(transTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		String tranStartStr = request.getParameter("trans-start");
		String transEndStr = request.getParameter("trans-end");
		//Date tranStart = null;
		if(!StringHelper.isNullOrEmpty(tranStartStr))
		{
			//tranStart = DateTimeHelper.strToDate(tranStartStr, DateTimeHelper.DEFAULT_DATETIME_FORMAT);
			tranStartStr+=" 00:00:00";
		}
		//Date transEnd = null;
		if(!StringHelper.isNullOrEmpty(transEndStr))
		{
			//transEnd = DateTimeHelper.strToDate(transEndStr, DateTimeHelper.DEFAULT_DATETIME_FORMAT);
			transEndStr+=" 00:00:00";
		}
		
		ChPmTransInfoModel model = new ChPmTransInfoModel();
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setDepartBefore(null!=departBefore?departBefore:null);
		model.setDepartBeforeCn(null!=departBeforeCn && !"".equals(departBeforeCn.trim())?departBeforeCn.trim():null);
		model.setPositBefore(null!=positBefore?positBefore:null);
		model.setPositBeforeCn(null!=positBeforeCn && !"".equals(positBeforeCn.trim())?positBeforeCn.trim():null);
		model.setDepartAfter(null!=departAfter?departAfter:null);
		model.setDepartAfterCn(null!=departAfterCn && !"".equals(departAfterCn.trim())?departAfterCn.trim():null);
		model.setPositAfter(null!=positAfter?positAfter:null);
		model.setPositAfterCn(null!=positAfterCn && !"".equals(positAfterCn.trim())?positAfterCn.trim():null);
		model.setTransDocno(null!=transDocno && !"".equals(transDocno.trim())?transDocno.trim():null);
		model.setTransTime(null!=transTime?transTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setTranStart(StringHelper.isNullOrEmpty(tranStartStr)?null:tranStartStr.trim());
		model.setTranSend(StringHelper.isNullOrEmpty(transEndStr)?null:transEndStr.trim());
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[调动信息校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmTransInfoModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String name = request.getParameter(PARA_name);
		if(StringHelper.isNullOrEmpty(name))
		{
			stringBuffer.append("输入姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String cardNo = request.getParameter(PARA_cardNo);
		if(StringHelper.isNullOrEmpty(cardNo))
		{
			stringBuffer.append("输入身份证号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String departBeforeStr = request.getParameter(PARA_departBefore);
		Integer departBefore = !StringHelper.isNullOrEmpty(departBeforeStr)?Integer.parseInt(departBeforeStr):null;
		String departBeforeCn = request.getParameter(PARA_departBeforeCn);
		if(null == departBefore)
		{
			stringBuffer.append("输入原部门!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String positBeforeStr = request.getParameter(PARA_positBefore);
		Integer positBefore = !StringHelper.isNullOrEmpty(positBeforeStr)?Integer.parseInt(positBeforeStr):null;
		String positBeforeCn = request.getParameter(PARA_positBeforeCn);
		if(null == positBefore)
		{
			stringBuffer.append("输入原岗位!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String departAfterStr = request.getParameter(PARA_departAfter);
		Integer departAfter = !StringHelper.isNullOrEmpty(departAfterStr)?Integer.parseInt(departAfterStr):null;
		if(null == departAfter)
		{
			stringBuffer.append("输入新部门!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String departAfterCn = request.getParameter(PARA_departAfterCn);
		
		String positAfterStr = request.getParameter(PARA_positAfter);
		Integer positAfter = !StringHelper.isNullOrEmpty(positAfterStr)?Integer.parseInt(positAfterStr):null;
		String positAfterCn = request.getParameter(PARA_positAfterCn);
		if(null == positAfter)
		{
			stringBuffer.append("输入新岗位!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String transDocno = request.getParameter(PARA_transDocno);
		if(StringHelper.isNullOrEmpty(transDocno))
		{
			stringBuffer.append("输入调动文号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String transTimeStr = request.getParameter(PARA_transTime);
		Date transTime = !StringHelper.isNullOrEmpty(transTimeStr)?DateTimeHelper.strToDate(transTimeStr, "yyyy-MM-dd"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setDepartBefore(null!=departBefore?departBefore:null);
		model.setDepartBeforeCn(null!=departBeforeCn && !"".equals(departBeforeCn.trim())?departBeforeCn.trim():null);
		model.setPositBefore(null!=positBefore?positBefore:null);
		model.setPositBeforeCn(null!=positBeforeCn && !"".equals(positBeforeCn.trim())?positBeforeCn.trim():null);
		model.setDepartAfter(null!=departAfter?departAfter:null);
		model.setDepartAfterCn(null!=departAfterCn && !"".equals(departAfterCn.trim())?departAfterCn.trim():null);
		model.setPositAfter(null!=positAfter?positAfter:null);
		model.setPositAfterCn(null!=positAfterCn && !"".equals(positAfterCn.trim())?positAfterCn.trim():null);
		model.setTransDocno(null!=transDocno && !"".equals(transDocno.trim())?transDocno.trim():null);
		model.setTransTime(null!=transTime?transTime:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
		
		//填充一级科室列表
		List<ChPmHosDepartModel> list = chPmHosDepartBiz.queryList(new ChPmHosDepartModel(), "hdp_no,hdp_name");
		modelMap.addAttribute("hdlist", list);
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
			ChPmTransInfoModel model = new ChPmTransInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chPmTransInfoBiz.save(model);
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
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) throw new Exception("编辑数据时，主键为空!");
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			
			ChPmTransInfoModel model = chPmTransInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			
			//填充一级科室列表
			List<ChPmHosDepartModel> list = chPmHosDepartBiz.queryList(new ChPmHosDepartModel(), "hdp_no,hdp_name");
			modelMap.addAttribute("hdlist", list);
		}catch(Exception e)
		{
			logger.error("加载调动信息失败！error:",e);
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
			ChPmTransInfoModel model = chPmTransInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmTransInfoBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("修改失败！error:",ex);
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
			ChPmTransInfoModel model = chPmTransInfoBiz.queryById(id);
			if(model != null)
			{
				chPmTransInfoBiz.delById4Logic(id);
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
			chPmTransInfoBiz.delByIds4Logic(ids);
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
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) throws Exception{
		ChPmTransInfoModel model = null;
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			//加载详情对象
			model = chPmTransInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			
			//填充一级科室列表
			List<ChPmHosDepartModel> list = chPmHosDepartBiz.queryList(new ChPmHosDepartModel(), "hdp_no,hdp_name");
			modelMap.addAttribute("hdlist", list);
			
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载调动信息失败！error:",e);
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