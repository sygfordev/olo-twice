package com.hdfs.olo.olo_web.central.controller;

import java.text.SimpleDateFormat;
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

import com.hdfs.olo.olo_web.central.biz.ISysCityBiz;
import com.hdfs.olo.olo_web.central.biz.ISysProvinceBiz;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.central.model.SysProvinceModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [省份表服务实现]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/central/sysProvince/*")
public class SysProvinceController extends BaseController {
	
	public final static String Module_Name = "省份表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISysProvinceBiz sysProvinceBiz;
	@Autowired
	private ISysCityBiz sysCityBiz;
	
	private	final static String PARA_provNo = "provNo";
	private	final static String PARA_provName = "provName";
	private	final static String PARA_provEn = "provEn";
	private	final static String PARA_sorts = "sorts";
	private	final static String PARA_lat = "lat";
	private	final static String PARA_status = "status";
	
	/**
	 * <p>Discription:[省份表请求首页]</p>
	 * Created on 2021年03月31日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[省份表首页数据加载-分页]</p>
	 * Created on 2021年03月31日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<SysProvinceModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<SysProvinceModel> retBody = new Result4Page<SysProvinceModel>();
		//查询参数
		Page.Builder<SysProvinceModel> builder = new Page.Builder<SysProvinceModel>();
		setSearchParameters(request, builder);
		Page<SysProvinceModel> page = builder.build();
		try {
			//分页
			sysProvinceBiz.queryPage(page);
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
	 * <p>Discription:[省份表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月31日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<SysProvinceModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String provNoStr = request.getParameter(PARA_provNo);
		Integer provNo = !StringHelper.isNullOrEmpty(provNoStr)?Integer.parseInt(provNoStr):null;
		String provName = request.getParameter(PARA_provName);
		String provEn = request.getParameter(PARA_provEn);
		String sortsStr = request.getParameter(PARA_sorts);
		Integer sorts = !StringHelper.isNullOrEmpty(sortsStr)?Integer.parseInt(sortsStr):null;
		String lat = request.getParameter(PARA_lat);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		
		SysProvinceModel model = new SysProvinceModel();
		model.setProvNo(null!=provNo?provNo:null);
		model.setProvName(null!=provName && !"".equals(provName.trim())?provName.trim():null);
		model.setProvEn(null!=provEn && !"".equals(provEn.trim())?provEn.trim():null);
		model.setSorts(null!=sorts?sorts:null);
		model.setLat(null!=lat && !"".equals(lat.trim())?lat.trim():null);
		model.setStatus(null!=status?status:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[省份表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月31日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, SysProvinceModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String provNoStr = request.getParameter(PARA_provNo);
		Integer provNo = !StringHelper.isNullOrEmpty(provNoStr)?Integer.parseInt(provNoStr):null;
		if(null == provNo)
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String provName = request.getParameter(PARA_provName);
		if(StringHelper.isNullOrEmpty(provName))
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String provEn = request.getParameter(PARA_provEn);
		if(StringHelper.isNullOrEmpty(provEn))
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String sortsStr = request.getParameter(PARA_sorts);
		Integer sorts = !StringHelper.isNullOrEmpty(sortsStr)?Integer.parseInt(sortsStr):null;
		String lat = request.getParameter(PARA_lat);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		
		if (stringBuffer.length() > 0) return;
		model.setProvNo(null!=provNo?provNo:null);
		model.setProvName(null!=provName && !"".equals(provName.trim())?provName.trim():null);
		model.setProvEn(null!=provEn && !"".equals(provEn.trim())?provEn.trim():null);
		model.setSorts(null!=sorts?sorts:null);
		model.setLat(null!=lat && !"".equals(lat.trim())?lat.trim():null);
		model.setStatus(null!=status?status:null);
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
			SysProvinceModel model = new SysProvinceModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			sysProvinceBiz.save(model);
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
	public void edit(@RequestParam("prov_no")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			List<ComboxItem> statusList = getStatusList();
			modelMap.addAttribute("statusList", statusList);
			
			SysProvinceModel model = sysProvinceBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载省份表失败！error:",e);
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
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("prov_no") Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			SysProvinceModel model = sysProvinceBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			sysProvinceBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getProvNo()+",名称:"+model);
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
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("prov_no")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("未检测到编号存在!");
			SysProvinceModel model = sysProvinceBiz.queryById(id);
			if(model != null)
			{
				sysProvinceBiz.delById4Logic(id);
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
			sysProvinceBiz.delByIds4Logic(ids);
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
		SysProvinceModel model = null;
		try {
			model = sysProvinceBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载省份表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
	
	/**
	 *  通过省份编号获取城市列表接口
	 * @param request
	 * @param provNo
	 * @return
	 */
	@RequestMapping(value = "loadCityListByProvNo.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadCityListByProvNo(HttpServletRequest request,@RequestParam("provNo") Integer provNo) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			if(null == provNo) return LayAjaxHelper.fail("请选择省份!");
			List<SysCityModel> list = sysProvinceBiz.loadCityListByProvNo(provNo);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "加载成功!");
			retMap.put("retData", list);
		} catch (Exception ex) {
			ex.printStackTrace();
			retMap.put("retCode", 201);
			retMap.put("retMsg", "加载失败!");
		}
		return retMap;
	}
	
	/**
	 *  获取所有城市列表接口
	 * @param request
	 * @param provNo
	 * @return
	 */
	@RequestMapping(value = "loadProvList.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadProvList(HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			List<SysProvinceModel> list = sysProvinceBiz.queryList(new SysProvinceModel());
			retMap.put("retCode", 200);
			retMap.put("retMsg", "加载成功!");
			retMap.put("retData", list);
		} catch (Exception ex) {
			ex.printStackTrace();
			retMap.put("retCode", 201);
			retMap.put("retMsg", "加载失败!");
		}
		return retMap;
	}
	
	/**
	 *  通过城市编号获取其下所有区县列表
	 * @param request
	 * @param provNo
	 * @return
	 */
	@RequestMapping(value = "loadAreaListByCityNo.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadAreaListByCityNo(HttpServletRequest request,@RequestParam("cityNo") Integer cityNo) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			if(null == cityNo) return LayAjaxHelper.fail("请选择城市!");
			List<SysAreaModel> list = sysCityBiz.loadAreaListByCityNo(cityNo);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "加载成功!");
			retMap.put("retData", list);
		} catch (Exception ex) {
			ex.printStackTrace();
			retMap.put("retCode", 201);
			retMap.put("retMsg", "加载失败!");
		}
		return retMap;
	}
}
