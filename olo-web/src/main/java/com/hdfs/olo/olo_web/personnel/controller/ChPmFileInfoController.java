package com.hdfs.olo.olo_web.personnel.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.alibaba.fastjson.JSON;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmFileInfoBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmFileInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.FilesHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.PropertiesHolder;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [文件信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmFileInfo/*")
public class ChPmFileInfoController extends BaseController {
	
	public final static String Module_Name = "文件信息";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChPmFileInfoBiz chPmFileInfoBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_moduleCode = "moduleCode";
	private	final static String PARA_moduleName = "moduleName";
	private	final static String PARA_fileContent = "fileContent";
	private	final static String PARA_fileType = "fileType";
	private	final static String PARA_fileSize = "fileSize";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private static final String OUTER_BASE;
	private static final String OUTER_ACCE;
	static {
		OUTER_ACCE = PropertiesHolder.readByKey("file_outer_acce_url");
		OUTER_BASE = PropertiesHolder.readByKey("file_outer_root_url");
	}
	
	private final static String DICT_COMMON_CODES = "status,uploadModule";
	
	/**
	 * <p>Discription:[文件信息请求首页]</p>
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
	
	
	private static final String FIELDS = "ID,WORKER_ID,MODULE_CODE,MODULE_NAME,FILE_TYPE,FILE_SIZE,STATUS,CREATE_TIME,UPDATE_TIME";
	/**
	 * <p>Discription:[文件信息首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmFileInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmFileInfoModel> retBody = new Result4Page<ChPmFileInfoModel>();
		//查询参数
		Page.Builder<ChPmFileInfoModel> builder = new Page.Builder<ChPmFileInfoModel>();
		setSearchParameters(request, builder);
		Page<ChPmFileInfoModel> page = builder.build();
		page.setQueryFields(FIELDS);
		try {
			//分页
			chPmFileInfoBiz.queryPage(page);
			parseList(page);//字节数组转文件
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
	 * <p>Discription:[文件信息设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmFileInfoModel> builder) {
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
		String moduleCodeStr = request.getParameter(PARA_moduleCode);
		Integer moduleCode = !StringHelper.isNullOrEmpty(moduleCodeStr)?Integer.parseInt(moduleCodeStr):null;
		String moduleName = request.getParameter(PARA_moduleName);
		//String fileContent = request.getParameter(PARA_fileContent);
		//String fileType = request.getParameter(PARA_fileType);
		//String fileSizeStr = request.getParameter(PARA_fileSize);
		//Long fileSize = !StringHelper.isNullOrEmpty(fileSizeStr)?Long.parseLong(fileSizeStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		ChPmFileInfoModel model = new ChPmFileInfoModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setModuleCode(null!=moduleCode?moduleCode:null);
		model.setModuleName(null!=moduleName && !"".equals(moduleName.trim())?moduleName.trim():null);
		//model.setFileContent(null!=fileContent && !"".equals(fileContent.trim())?fileContent.trim():null);
		//model.setFileType(null!=fileType && !"".equals(fileType.trim())?fileType.trim():null);
		//model.setFileSize(null!=fileSize?fileSize:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[文件信息校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmFileInfoModel model) {
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
		String moduleCodeStr = request.getParameter(PARA_moduleCode);
		Integer moduleCode = !StringHelper.isNullOrEmpty(moduleCodeStr)?Integer.parseInt(moduleCodeStr):null;
		if(null == moduleCode)
		{
			stringBuffer.append("输入模块编码!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String moduleName = request.getParameter(PARA_moduleName);
		if(StringHelper.isNullOrEmpty(moduleName))
		{
			stringBuffer.append("输入模块名称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
//		String fileContent = request.getParameter(PARA_fileContent);
//		if(StringHelper.isNullOrEmpty(fileContent))
//		{
//			stringBuffer.append("输入文件内容!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
//		String fileType = request.getParameter(PARA_fileType);
//		if(StringHelper.isNullOrEmpty(fileType))
//		{
//			stringBuffer.append("输入文件类型（文件后缀）!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
//		String fileSizeStr = request.getParameter(PARA_fileSize);
//		Long fileSize = !StringHelper.isNullOrEmpty(fileSizeStr)?Long.parseLong(fileSizeStr):null;
//		if(null == fileSize)
//		{
//			stringBuffer.append("输入文件大小!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setModuleCode(null!=moduleCode?moduleCode:null);
		model.setModuleName(null!=moduleName && !"".equals(moduleName.trim())?moduleName.trim():null);
		//model.setFileContent(null!=fileContent && !"".equals(fileContent.trim())?fileContent.trim():null);
		//model.setFileType(null!=fileType && !"".equals(fileType.trim())?fileType.trim():null);
		//model.setFileSize(null!=fileSize?fileSize:null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向首页新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add1.jhtm")
	public void add1(ModelMap modelMap,Long wkId) throws Exception{
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
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap,Long wkId) throws Exception{
		if(null == wkId) throw new Exception("职工编号不存在！");
		modelMap.addAttribute("wkId", wkId);
	}
	
	/**
	 * 增加操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request,@RequestParam MultipartFile[] file) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmFileInfoModel model = new ChPmFileInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
        	for (MultipartFile item : file) {
        		model.setFileType(item.getContentType());
        		model.setFileSize(item.getSize());
        		model.setFileName(item.getOriginalFilename());
        		model.setFileContent(item.getBytes());
        		chPmFileInfoBiz.save(model);
        	}
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
	 * 上传文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "upload/do.jhtm")
	@ResponseBody//@PathVariable(value = "mCode") 
	public Map<String,Object> upload(HttpServletRequest request,@RequestParam MultipartFile[] file) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmFileInfoModel model = new ChPmFileInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			StringBuffer names = new StringBuffer();
			//增加
        	for (MultipartFile item : file) {
        		model.setFileType(item.getContentType());
        		model.setFileSize(item.getSize());
        		model.setFileName(item.getOriginalFilename());
        		names.append(names.length()>0?","+item.getOriginalFilename():item.getOriginalFilename());
        		model.setFileContent(item.getBytes());
        		chPmFileInfoBiz.save(model);
        	}
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,names);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("上传失败!error:",ex);
		}
		return map;
	}
	
	/**
	 * 上传文件
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadFileInfo.jhtm")
	@ResponseBody
	public Map<String,Object> loadFileInfo(HttpServletRequest request,@RequestParam Long wkId) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			if(null == wkId) throw new Exception("职工编号为空!");
			ChPmFileInfoModel model = new ChPmFileInfoModel();
			model.setWorkerId(wkId);
			List<ChPmFileInfoModel> list = chPmFileInfoBiz.queryList(model);
			if(null == list) 
				return LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,new ArrayList<String>());
			
			String fileType = null;
			String fileName = null;
			String targetPath = null;
			String accessPath = null;
			Integer moduleCode = null;
			List<ChPmFileInfoModel> moduleList = null;
			Map<Integer,List<ChPmFileInfoModel>> retData = new HashMap<Integer,List<ChPmFileInfoModel>>();
			for(ChPmFileInfoModel item:list)
			{
				fileType = item.getFileType();
				moduleCode = item.getModuleCode();
				if(StringHelper.isNullOrEmpty(fileType))continue;
				String[] type = fileType.split("/");
				targetPath = OUTER_BASE+item.getWorkerId()+"//"+type[0]+"//";
				accessPath = OUTER_ACCE+item.getWorkerId()+"//"+type[0]+"//";
				fileName = item.getWorkerId()+moduleCode+item.getId()+"."+type[1];
				FilesHelper.writeFile(item.getFileContent(), targetPath, fileName);
				if(retData.containsKey(moduleCode))
				{
					item.setFileUrl(accessPath+fileName);
					item.setFileContent(null);
					retData.get(moduleCode).add(item);
				}else
				{
					moduleList = new ArrayList<ChPmFileInfoModel>();
					item.setFileUrl(accessPath+fileName);
					item.setFileContent(null);
					moduleList.add(item);
					retData.put(moduleCode, moduleList);
				}
			}
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model.getWorkerId());
			//logger.info("加载职工文件结束："+JSON.toJSONString(retData));
			//返回成功信息
			retMap =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,JSON.parse(JSON.toJSONString(retData)));
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			retMap = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("加载文件信息失败!error:",ex);
		}
		return retMap;
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("wkId")Long wkId, ModelMap modelMap) throws Exception{
		if(null == wkId) throw new Exception("职工编号为空!");
		modelMap.addAttribute("wkId", wkId);
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
			ChPmFileInfoModel model = chPmFileInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmFileInfoBiz.edit(model);
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
			ChPmFileInfoModel model = chPmFileInfoBiz.queryById(id);
			if(model != null)
			{
				chPmFileInfoBiz.delById4Logic(id);
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
			chPmFileInfoBiz.delByIds4Logic(ids);
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
		ChPmFileInfoModel model = null;
		try {
			model = chPmFileInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载文件信息失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
	
	/**
	 * 将首页搜索到的文件列表转为可访问的文件
	 * @param page
	 */
	private void parseList(Page page)
	{
		List<ChPmFileInfoModel> list = page.getDatas();
		if(null == list || list.size()<=0)return;
		
		String fileType = null;
		String fileName = null;
		String targetPath = null;
		String accessPath = null;
		Integer moduleCode = null;
		for(ChPmFileInfoModel item:list)
		{
			fileType = item.getFileType();
			moduleCode = item.getModuleCode();
			if(StringHelper.isNullOrEmpty(fileType))continue;
			String[] type = fileType.split("/");
			targetPath = OUTER_BASE+item.getWorkerId()+"//"+type[0]+"//";
			accessPath = OUTER_ACCE+item.getWorkerId()+"//"+type[0]+"//";
			fileName = item.getWorkerId()+moduleCode+item.getId()+"."+type[1];
			FilesHelper.writeFile(item.getFileContent(), targetPath, fileName);
			item.setFileUrl(accessPath+fileName);
			item.setFileContent(null);
		}
	}
	
	/**
	 * 文件下载
	 * @param request
	 * @param ids
	 * @return
	 */
//	@RequestMapping(value = "download.jhtm")
//	public ResponseEntity<byte[]> download(HttpServletRequest request,HttpServletResponse response) {
//		Long id = null;
//		ResponseEntity<byte[]> entity = null;
//		try {
//			String idStr = request.getParameter(PARA_id);
//			id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
//			if(null == id) throw new Exception("职工编号为空！");
//			ChPmFileInfoModel item = chPmFileInfoBiz.queryById(id);
//			
//			HttpHeaders header = new HttpHeaders();
//			header.setContentDispositionFormData("attachment", item.getFileName());
//			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//			byte[] bytes = item.getFileContent();
//			
//			entity = new ResponseEntity<byte[]>(bytes,header,HttpStatus.OK);
//			log2Info(request, Module_Name, "文件下载","编号:"+id);
//		} catch (Exception ex) {
//			logger.error("人事档案-文件下载异常！ error："+ex.getMessage());
//			log2Error(request, Module_Name, "文件下载","编号:"+id+" ERROR:"+ex.getMessage());
//		}
//		return entity;
//	}
	
	@RequestMapping(value = "download", method = RequestMethod.GET)
	@ResponseBody
	public void testDownload(HttpServletRequest request,HttpServletResponse res) {
		Long id = null;
		
		byte[] buff = new byte[1024];
		ByteArrayInputStream bais = null;
		OutputStream os = null;
		
		try {
			String idStr = request.getParameter(PARA_id);
			id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
			if(null == id) throw new Exception("职工编号为空！");
			ChPmFileInfoModel item = chPmFileInfoBiz.queryById(id);
			
			//res.setHeader("content-type", item.getFileType());
			res.setHeader("content-type", "application/x-msdownload;");
			res.setContentType(item.getFileType()+"; charset=utf-8");
			res.setHeader("Content-Disposition", "attachment; filename=" + item.getFileName());
			
			byte[] bytes = item.getFileContent();
			bais = new ByteArrayInputStream(bytes);
	        
			os = res.getOutputStream();
			int i = bais.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bais.read(buff);
			}
			log2Info(request, Module_Name, "文件下载","编号:"+id);
		} catch (Exception ex) {
			logger.error("人事档案-文件下载异常！ error："+ex.getMessage());
			log2Error(request, Module_Name, "文件下载","编号:"+id+" ERROR:"+ex.getMessage());
		}finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		logger.info("export file finish");
	}
}
