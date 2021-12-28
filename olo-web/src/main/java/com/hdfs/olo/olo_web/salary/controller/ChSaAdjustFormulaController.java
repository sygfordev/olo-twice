package com.hdfs.olo.olo_web.salary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartFile;

import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
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
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustFormulaBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustFormulaModel;

/** 
 * Description: [调资-公式表服务实现]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/salary/chSaAdjustFormula/*")
public class ChSaAdjustFormulaController extends BaseController {
	
	public final static String Module_Name = "调资-公式表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChSaAdjustFormulaBiz chSaAdjustFormulaBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_target = "target";
	private	final static String PARA_targetLevelCn = "targetLevelCn";
	private	final static String PARA_formula2down = "formula2down";
	private	final static String PARA_formula3to4 = "formula3to4";
	private	final static String PARA_formula5to6 = "formula5to6";
	private	final static String PARA_formula7to8 = "formula7to8";
	private	final static String PARA_formula9to10 = "formula9to10";
	private	final static String PARA_formula11to12 = "formula11to12";
	private	final static String PARA_formula13up = "formula13up";
	private	final static String PARA_gratdations = "gratdations";
	private	final static String PARA_formulaType = "formulaType";
	private final static String PARA_unique_key = "uniqueKey";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "status,YN";
	
	/**
	 * <p>Discription:[调资-公式表请求首页]</p>
	 * Created on 2021年05月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//填充字典数据
		//fillCommonDict(modelMap);
	}
	 
	/**
	 * <p>Discription:[调资-公式表首页数据加载-分页]</p>
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaAdjustFormulaModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSaAdjustFormulaModel> retBody = new Result4Page<ChSaAdjustFormulaModel>();
		//查询参数
		Page.Builder<ChSaAdjustFormulaModel> builder = new Page.Builder<ChSaAdjustFormulaModel>();
		setSearchParameters(request, builder);
		Page<ChSaAdjustFormulaModel> page = builder.build();
		try {
			//分页
			chSaAdjustFormulaBiz.queryPage(page);
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
	 * <p>Discription:[调资-公式表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaAdjustFormulaModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String target = request.getParameter(PARA_target);
		String targetLevelCn = request.getParameter(PARA_targetLevelCn);
		String formula2downStr = request.getParameter(PARA_formula2down);
		BigDecimal formula2down = !StringHelper.isNullOrEmpty(formula2downStr)?new BigDecimal(formula2downStr):null;
		String formula3to4Str = request.getParameter(PARA_formula3to4);
		BigDecimal formula3to4 = !StringHelper.isNullOrEmpty(formula3to4Str)?new BigDecimal(formula3to4Str):null;
		String formula5to6Str = request.getParameter(PARA_formula5to6);
		BigDecimal formula5to6 = !StringHelper.isNullOrEmpty(formula5to6Str)?new BigDecimal(formula5to6Str):null;
		String formula7to8Str = request.getParameter(PARA_formula7to8);
		BigDecimal formula7to8 = !StringHelper.isNullOrEmpty(formula7to8Str)?new BigDecimal(formula7to8Str):null;
		String formula9to10Str = request.getParameter(PARA_formula9to10);
		BigDecimal formula9to10 = !StringHelper.isNullOrEmpty(formula9to10Str)?new BigDecimal(formula9to10Str):null;
		String formula11to12Str = request.getParameter(PARA_formula11to12);
		BigDecimal formula11to12 = !StringHelper.isNullOrEmpty(formula11to12Str)?new BigDecimal(formula11to12Str):null;
		String formula13upStr = request.getParameter(PARA_formula13up);
		BigDecimal formula13up = !StringHelper.isNullOrEmpty(formula13upStr)?new BigDecimal(formula13upStr):null;
		String gratdationsStr = request.getParameter(PARA_gratdations);
		BigDecimal gratdations = !StringHelper.isNullOrEmpty(gratdationsStr)?new BigDecimal(gratdationsStr):null;
		String formulaType = request.getParameter(PARA_formulaType);
		String uniqueKey = request.getParameter(PARA_unique_key);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		//status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChSaAdjustFormulaModel model = new ChSaAdjustFormulaModel();
		model.setId(null!=id?id:null);
		model.setTarget(null!=target && !"".equals(target.trim())?target.trim():null);
		model.setTargetLevelCn(null!=targetLevelCn && !"".equals(targetLevelCn.trim())?targetLevelCn.trim():null);
		model.setFormula2down(null!=formula2down?formula2down:null);
		model.setFormula3to4(null!=formula3to4?formula3to4:null);
		model.setFormula5to6(null!=formula5to6?formula5to6:null);
		model.setFormula7to8(null!=formula7to8?formula7to8:null);
		model.setFormula9to10(null!=formula9to10?formula9to10:null);
		model.setFormula11to12(null!=formula11to12?formula11to12:null);
		model.setFormula13up(null!=formula13up?formula13up:null);
		model.setGratdations(null!=gratdations?gratdations:null);
		model.setFormulaType(null!=formulaType && !"".equals(formulaType.trim())?formulaType.trim():null);
		model.setUniqueKey(null!=uniqueKey && !"".equals(uniqueKey.trim())?uniqueKey.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[调资-公式表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChSaAdjustFormulaModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String target = request.getParameter(PARA_target);
		String targetLevelCn = request.getParameter(PARA_targetLevelCn);
		String formula2downStr = request.getParameter(PARA_formula2down);
		BigDecimal formula2down = !StringHelper.isNullOrEmpty(formula2downStr)?new BigDecimal(formula2downStr):null;
		String formula3to4Str = request.getParameter(PARA_formula3to4);
		BigDecimal formula3to4 = !StringHelper.isNullOrEmpty(formula3to4Str)?new BigDecimal(formula3to4Str):null;
		String formula5to6Str = request.getParameter(PARA_formula5to6);
		BigDecimal formula5to6 = !StringHelper.isNullOrEmpty(formula5to6Str)?new BigDecimal(formula5to6Str):null;
		String formula7to8Str = request.getParameter(PARA_formula7to8);
		BigDecimal formula7to8 = !StringHelper.isNullOrEmpty(formula7to8Str)?new BigDecimal(formula7to8Str):null;
		String formula9to10Str = request.getParameter(PARA_formula9to10);
		BigDecimal formula9to10 = !StringHelper.isNullOrEmpty(formula9to10Str)?new BigDecimal(formula9to10Str):null;
		String formula11to12Str = request.getParameter(PARA_formula11to12);
		BigDecimal formula11to12 = !StringHelper.isNullOrEmpty(formula11to12Str)?new BigDecimal(formula11to12Str):null;
		String formula13upStr = request.getParameter(PARA_formula13up);
		BigDecimal formula13up = !StringHelper.isNullOrEmpty(formula13upStr)?new BigDecimal(formula13upStr):null;
		String gratdationsStr = request.getParameter(PARA_gratdations);
		BigDecimal gratdations = !StringHelper.isNullOrEmpty(gratdationsStr)?new BigDecimal(gratdationsStr):null;
		String formulaType = request.getParameter(PARA_formulaType);
		if(StringHelper.isNullOrEmpty(formulaType))
		{
			if(stringBuffer.length()>0) stringBuffer.append("</br>");
			stringBuffer.append("缺失调资类别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String uniqueKey = request.getParameter(PARA_unique_key);
		if(StringHelper.isNullOrEmpty(uniqueKey))
		{
			if(stringBuffer.length()>0) stringBuffer.append("</br>");
			stringBuffer.append("缺失唯一编码!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setTarget(null!=target && !"".equals(target.trim())?target.trim():null);
		model.setTargetLevelCn(null!=targetLevelCn && !"".equals(targetLevelCn.trim())?targetLevelCn.trim():null);
		model.setFormula2down(null!=formula2down?formula2down:null);
		model.setFormula3to4(null!=formula3to4?formula3to4:null);
		model.setFormula5to6(null!=formula5to6?formula5to6:null);
		model.setFormula7to8(null!=formula7to8?formula7to8:null);
		model.setFormula9to10(null!=formula9to10?formula9to10:null);
		model.setFormula11to12(null!=formula11to12?formula11to12:null);
		model.setFormula13up(null!=formula13up?formula13up:null);
		model.setGratdations(null!=gratdations?gratdations:null);
		model.setFormulaType(null!=formulaType && !"".equals(formulaType.trim())?formulaType.trim():null);
		model.setUniqueKey(null!=uniqueKey && !"".equals(uniqueKey.trim())?uniqueKey.trim():null);
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
		//fillCommonDict(modelMap);
	}
	/**
	 * 转向上传页面
	 * @param modelMap
	 */
	@RequestMapping(value="upload.jhtm")
	public void upload(ModelMap modelMap) throws Exception{
		
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
			ChSaAdjustFormulaModel model = new ChSaAdjustFormulaModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chSaAdjustFormulaBiz.save(model);
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
	
	private static List<String> FORMULAS;
	static {
		FORMULAS = new ArrayList<String>();
		FORMULAS.add("A1");
		FORMULAS.add("A2");
		FORMULAS.add("B1");
		FORMULAS.add("B2");
		FORMULAS.add("三类");
	}
	/**
	 * 导入文件
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "doImport.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doImport(HttpServletRequest request,@RequestParam MultipartFile[] file) {
		Map<String, Object> map = null;
		InputStream inputs = null;
		try {
			//获取当前登录人
			UserInfoModel loginUser = this.getLoginUser(request);
			if(null == loginUser) return LayAjaxHelper.fail("当前未处于登录状态，请重新登录!");
			String formulaType = request.getParameter(PARA_formulaType);
			if(StringHelper.isNullOrEmpty(formulaType) || !FORMULAS.contains(formulaType))
				return LayAjaxHelper.fail("请选择薪级类别!");
			String type = null;
			String fileName = null;
			
			//导入操作返回的结果数据
			Map<Boolean,Object> data = null;
        	for (MultipartFile item : file) {
        		fileName = item.getOriginalFilename();
        		type = null != fileName?fileName.substring(fileName.indexOf("."), fileName.length()):null;
        		if(!type.contains("xls") && !type.contains("xlsx"))
        			break;
        		inputs = item.getInputStream();
        		data = chSaAdjustFormulaBiz.doImport(inputs,formulaType);
        	}
			log2Info(request, Module_Name, LogDictionary.Module_Oper_import,"编号:" +",名称:薪级分类");
			map =  data.containsKey(true)?LayAjaxHelper.success("上传成功！"):
				LayAjaxHelper.fail(String.valueOf(data.get(false)));
		} catch (Exception ex) {
			ex.printStackTrace();
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("导入失败！error:",ex);
		}finally {
			if(null != inputs)
				try {
					inputs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
			//fillCommonDict(modelMap);
			
			ChSaAdjustFormulaModel model = chSaAdjustFormulaBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载调资-公式表失败！error:",e);
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
			ChSaAdjustFormulaModel model = chSaAdjustFormulaBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chSaAdjustFormulaBiz.edit(model);
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
			ChSaAdjustFormulaModel model = chSaAdjustFormulaBiz.queryById(id);
			if(model != null)
			{
				chSaAdjustFormulaBiz.delById4Logic(id);
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
			chSaAdjustFormulaBiz.delByIds4Logic(ids);
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
		ChSaAdjustFormulaModel model = null;
		try {
			//填充字典数据
			//fillCommonDict(modelMap);
			//加载详情对象
			model = chSaAdjustFormulaBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载调资-公式表失败！error:",e);
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
	 * <p>Discription:[调资-公式表请求首页]</p>
	 * Created on 2021年05月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("showList.jhtm")
	public void showList(HttpServletRequest request,ModelMap modelMap) throws Exception{
		String formulaType = request.getParameter(PARA_formulaType);
		String uniqueKey = request.getParameter(PARA_unique_key);
		modelMap.put(PARA_formulaType, formulaType);
		modelMap.put(PARA_unique_key, uniqueKey);
	}
	 
	/**
	 * <p>Discription:[调资-公式表首页数据加载-分页]</p>
	 * Created on 2021年05月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "showList/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaAdjustFormulaModel> doShowList(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSaAdjustFormulaModel> retBody = new Result4Page<ChSaAdjustFormulaModel>();
		//查询参数
		Page.Builder<ChSaAdjustFormulaModel> builder = new Page.Builder<ChSaAdjustFormulaModel>();
		setSearchParameters(request, builder);
		Page<ChSaAdjustFormulaModel> page = builder.build();
		ChSaAdjustFormulaModel model = page.getModel();
		String type = model.getFormulaType();
		String uniqueKey = model.getUniqueKey();
		if(StringHelper.isNullOrEmpty(type) || StringHelper.isNullOrEmpty(uniqueKey))
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("请求参数中缺失必填参数!");
			return retBody;
		}
		try {
			//分页
			chSaAdjustFormulaBiz.queryPage(page);
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
}
