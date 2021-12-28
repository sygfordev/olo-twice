package com.hdfs.olo.olo_web.personnel.controller;

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

import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosDepartBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
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
 * Description: [医院一级科室表服务实现]
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmHosDepart/*")
public class ChPmHosDepartController extends BaseController {
	
	public final static String Module_Name = "医院一级科室表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmHosDepartBiz chPmHosDepartBiz;
	
	private	final static String PARA_hdpNo = "hdpNo";
	private	final static String PARA_hdpName = "hdpName";
	private	final static String PARA_hbhNo = "hbhNo";
	private	final static String PARA_sorts = "sorts";
	private	final static String PARA_status = "status";
	
	/**
	 * <p>Discription:[医院一级科室表请求首页]</p>
	 * Created on 2021年04月02日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[医院一级科室表首页数据加载-分页]</p>
	 * Created on 2021年04月02日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmHosDepartModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmHosDepartModel> retBody = new Result4Page<ChPmHosDepartModel>();
		//查询参数
		Page.Builder<ChPmHosDepartModel> builder = new Page.Builder<ChPmHosDepartModel>();
		setSearchParameters(request, builder);
		Page<ChPmHosDepartModel> page = builder.build();
		try {
			//分页
			chPmHosDepartBiz.queryPage(page);
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
	 * <p>Discription:[医院一级科室表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年04月02日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmHosDepartModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String hdpNoStr = request.getParameter(PARA_hdpNo);
		Integer hdpNo = !StringHelper.isNullOrEmpty(hdpNoStr)?Integer.parseInt(hdpNoStr):null;
		String hdpName = request.getParameter(PARA_hdpName);
		String hbhNoStr = request.getParameter(PARA_hbhNo);
		Integer hbhNo = !StringHelper.isNullOrEmpty(hbhNoStr)?Integer.parseInt(hbhNoStr):null;
		String sortStr = request.getParameter(PARA_sorts);
		Integer sorts = !StringHelper.isNullOrEmpty(sortStr)?Integer.parseInt(sortStr):null;
		String statuStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statuStr)?Integer.parseInt(statuStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmHosDepartModel model = new ChPmHosDepartModel();
		model.setHdpNo(null!=hdpNo?hdpNo:null);
		model.setHdpName(null!=hdpName && !"".equals(hdpName.trim())?hdpName.trim():null);
		model.setHbhNo(null!=hbhNo?hbhNo:null);
		model.setSorts(null!=sorts?sorts:null);
		model.setStatus(null!=status?status:null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[医院一级科室表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月02日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmHosDepartModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String hdpNoStr = request.getParameter(PARA_hdpNo);
		Integer hdpNo = !StringHelper.isNullOrEmpty(hdpNoStr)?Integer.parseInt(hdpNoStr):null;
		if(null == hdpNo)
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String hdpName = request.getParameter(PARA_hdpName);
		if(StringHelper.isNullOrEmpty(hdpName))
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String hbhNoStr = request.getParameter(PARA_hbhNo);
		Integer hbhNo = !StringHelper.isNullOrEmpty(hbhNoStr)?Integer.parseInt(hbhNoStr):null;
		if(null == hbhNo)
		{
			stringBuffer.append("输入!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String sortStr = request.getParameter(PARA_sorts);
		Integer sorts = !StringHelper.isNullOrEmpty(sortStr)?Integer.parseInt(sortStr):null;
		String statuStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statuStr)?Integer.parseInt(statuStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		
		if (stringBuffer.length() > 0) return;
		model.setHdpNo(null!=hdpNo?hdpNo:null);
		model.setHdpName(null!=hdpName && !"".equals(hdpName.trim())?hdpName.trim():null);
		model.setHbhNo(null!=hbhNo?hbhNo:null);
		model.setSorts(null!=sorts?sorts:null);
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
			ChPmHosDepartModel model = new ChPmHosDepartModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chPmHosDepartBiz.save(model);
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
	public void edit(@RequestParam("hdp_no")Long id, ModelMap modelMap) {
		if(null == id) return;
		try {
			List<ComboxItem> statusList = getStatusList();
			modelMap.addAttribute("statusList", statusList);
			
			ChPmHosDepartModel model = chPmHosDepartBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载医院一级科室表失败！error:",e);
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
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("hdp_no") Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmHosDepartModel model = chPmHosDepartBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmHosDepartBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getHdpNo()+",名称:"+model);
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
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("hdp_no")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("未检测到编号存在!");
			ChPmHosDepartModel model = chPmHosDepartBiz.queryById(id);
			if(model != null)
			{
				chPmHosDepartBiz.delById4Logic(id);
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
			chPmHosDepartBiz.delByIds4Logic(ids);
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
		ChPmHosDepartModel model = null;
		try {
			model = chPmHosDepartBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载医院一级科室表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
}
