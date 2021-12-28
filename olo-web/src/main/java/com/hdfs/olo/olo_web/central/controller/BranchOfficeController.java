package com.hdfs.olo.olo_web.central.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.biz.IBranchOfficeBiz;
import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.treexts.TreeNodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * <p>Description: [组织机构 控制层]</p>
 * Created on 2020年03月18日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年
 */
@Controller
@RequestMapping("/central/branchOffice/*")
public class BranchOfficeController extends BaseController{
	public final static String Module_Name = "组织机构";
	
	private	final static String PARA_id = "id";
	private	final static String PARA_branchName = "branchName";
	private	final static String PARA_cityNo = "cityNo";
	private	final static String PARA_parentId = "parentId";
	private	final static String PARA_contact = "contact";
	private	final static String PARA_phone = "phone";
	private	final static String PARA_fax = "fax";
	private	final static String PARA_email = "email";
	private	final static String PARA_address = "address";
	private	final static String PARA_zip = "zip";
	private	final static String PARA_status = "status";
	private final static String PARA_remark = "remark";
	
	@Autowired
	private IUserInfoBiz userInfoBiz;
	@Autowired
	private IBranchOfficeBiz branchOfficeBiz;
	
	/**
	 * <p>Discription:[组织机构请求首页]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	
	/**
	 * <p>Discription:[组织机构首页数据加载-分页]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Map<String,Object> loadAll(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			//分页
			List<BranchOfficeModel> list = branchOfficeBiz.queryList(new BranchOfficeModel());
			TreeNodeUtil.CvtConfig builder = new TreeNodeUtil.CvtConfig();
			Map<String,String> keyMaps = new HashMap<String,String>();
			keyMaps.put("branchName", "title");
			keyMaps.put("parentId", "pid");
			TreeNodeUtil util = builder.rootId(-1l).idKey("id").pidKey("parentId")
					.subKey("children").keyMaps(keyMaps).build();
			JSONObject tmp = util.merge(list);
			JSONArray data = new JSONArray();
			data.add(tmp);
			retMap.put("retCode", 200);
			retMap.put("retData", data);
			retMap.put("retMsg", "加载成功！");
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "加载失败！");
			e.printStackTrace();
		}
		return retMap;
	}
	
	/**
	 * <p>Discription:[组织机构设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@SuppressWarnings("unused")
	private void setSearchParameters(HttpServletRequest request, Builder<BranchOfficeModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter("page");
		String limitStr  = request.getParameter("limit");
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		//请求参数置入
		String id = request.getParameter(PARA_id);
		Long branchId = StringHelper.isNullOrEmpty(id)?null:Long.parseLong(id);
		String branchName = request.getParameter(PARA_branchName);
		String cityNo = request.getParameter(PARA_cityNo);
		String parentIdStr = request.getParameter(PARA_parentId);
		Long parentId = StringHelper.isNullOrEmpty(parentIdStr)?null:Long.parseLong(parentIdStr);
		String contact = request.getParameter(PARA_contact);
		String phone = request.getParameter(PARA_phone);
		String fax = request.getParameter(PARA_fax);
		String email = request.getParameter(PARA_email);
		String address = request.getParameter(PARA_address);
		String zip = request.getParameter(PARA_zip);
		String status = request.getParameter(PARA_status);
        
        BranchOfficeModel model = new BranchOfficeModel();
		if(null != branchId)
        	model.setId(branchId);
		if(!StringHelper.isNullOrEmpty(branchName))
        	model.setBranchName(branchName);
		if(!StringHelper.isNullOrEmpty(cityNo))
        	model.setCityNo(cityNo);
		if(null != parentId)
        	model.setParentId(parentId);
		if(!StringHelper.isNullOrEmpty(contact))
        	model.setContact(contact);
		if(!StringHelper.isNullOrEmpty(phone))
        	model.setPhone(phone);
		if(!StringHelper.isNullOrEmpty(fax))
        	model.setFax(fax);
		if(!StringHelper.isNullOrEmpty(email))
        	model.setEmail(email);
		if(!StringHelper.isNullOrEmpty(address))
        	model.setAddress(address);
		if(!StringHelper.isNullOrEmpty(zip))
        	model.setZip(zip);
		if(!StringHelper.isNullOrEmpty(status))
        	model.setStatus(status);
		//置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[组织机构 校验]</p>
	 * @param request请求对象
	 * @param errMsg错误信息
	 * @param model实体对象
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer errMsg, BranchOfficeModel model) {
		String id = request.getParameter(PARA_id);
		Long branchId = StringHelper.isNullOrEmpty(id)?null:Long.parseLong(id);
		String branchName = request.getParameter(PARA_branchName);
		if (StringHelper.isNullOrEmpty(branchName)) {
			errMsg.append("输入机构名称!");
			errMsg.append(LayAjaxHelper.CR);
		}
		String parentId = request.getParameter(PARA_parentId);
		if(null != model && "-1".equals(model.getParentId()+""))
			parentId = String.valueOf(model.getParentId());
		Long pId = StringHelper.isNullOrEmpty(parentId)?null:Long.parseLong(parentId);
		if (null == pId) {
			errMsg.append("请选择父级机构!");
			errMsg.append(LayAjaxHelper.CR);
		}
		String cityNo = request.getParameter(PARA_cityNo);
		String contact = request.getParameter(PARA_contact);
		String phone = request.getParameter(PARA_phone);
		String fax = request.getParameter(PARA_fax);
		String email = request.getParameter(PARA_email);
		String address = request.getParameter(PARA_address);
		String zip = request.getParameter(PARA_zip);
		String status = request.getParameter(PARA_status);
		String remark = request.getParameter(PARA_remark);
		
		if (errMsg.length() == 0) {
			model.setId(branchId);
			model.setBranchName(null!=branchName?branchName.trim():null);
			model.setCityNo(null!=cityNo?cityNo.trim():null);
			model.setContact(null!=contact?contact.trim():null);
			model.setPhone(null!=phone?phone.trim():null);
			model.setParentId(null!=pId?pId:null);
			model.setFax(null!=fax?fax.trim():null);
			model.setEmail(null!=email?email.trim():null);
			model.setAddress(null!=address?address.trim():null);
			model.setZip(null!=zip?zip.trim():null);
			model.setStatus(null!=status?status.trim():null);
			model.setRemark(null!=remark?remark.trim():null);
		}
	}
	
	/**
	 * <p>Discription:[组织机构 添加转向]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value="add.jhtm")
	public void add(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.addAttribute("statusList", statusList);
		
		String parentId = request.getParameter(PARA_parentId);
		modelMap.addAttribute("parentId", parentId);
	}
	
	/**
	 * <p>Discription:[组织机构 添加执行]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			BranchOfficeModel model = new BranchOfficeModel();
			//基本验证
			baseValidate(request, errMsg, model);
			//若验证失败,则返回
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			//执行添加
			branchOfficeBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:"+model+",other:"+model);
			
			//返回成功信息
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[组织机构 编辑转向]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(Long id, ModelMap modelMap) {
		if(null == id) return;
		List<ComboxItem> statusList = getStatusList();
		modelMap.addAttribute("statusList", statusList);
		
		BranchOfficeModel model = branchOfficeBiz.queryById(id);
		modelMap.addAttribute("model", model);
		if(null != model) {
			List<BranchOfficeModel> parents = new ArrayList<BranchOfficeModel>();
			BranchOfficeModel parent = branchOfficeBiz.queryById(model.getParentId());
			parents.add(parent);
			modelMap.addAttribute("parents", parents);
		}
	}
	
	/**
	 * <p>Discription:[组织机构 编辑执行]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			//必须输入参数
			if (null == id) {
				errMsg.append("请输入编号!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			BranchOfficeModel model = branchOfficeBiz.queryById(id);
			if(null == model)
			{
				errMsg.append("该记录在系统中不存在!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			baseValidate(request, errMsg, model);
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			branchOfficeBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:"+model+",other:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[组织机构 单项删除执行]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("请选择编号!");
			BranchOfficeModel model = branchOfficeBiz.queryById(id);
			if(null != model)
			{
				branchOfficeBiz.delById(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+model+",other:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[组织机构 批量删除执行]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			branchOfficeBiz.delBatchByIds(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"用户编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[组织机构 删除校验操作]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "check4Del.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> check4Del(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("机构编号为空!");
			Map<String,Object> datas = new HashMap<String,Object>();
			//第一步：判断其下是否有子机构存在
			boolean isContatinSuBranch = false;
			List<BranchOfficeModel> branchs = null;
			//搜集其下所有组织机构编号
			List<Long> branchIds = new ArrayList<Long>();
			for(Long id:ids)
			{
				branchs = branchOfficeBiz.queryListUpOrDowById(id, 1);
				for(BranchOfficeModel item:branchs)
				{
					if(branchIds.contains(item.getId()))
						continue;
					isContatinSuBranch = true;
					branchIds.add(item.getId());
				}
				if(!branchIds.contains(id))
					branchIds.add(id);
			}
			datas.put("isExist4Sub", isContatinSuBranch);
			//第二步：判断其下所有子机构中是否包含用户
			long size = userInfoBiz.queryCountByBranch(branchIds);
			datas.put("isExist4User", size>0?true:false);
			retMap = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
			retMap.putAll(datas);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"机构编号:"+ids);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			retMap = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return retMap;
	}
	
	/**
	 * <p>Discription:[组织机构 详情转向/执行]</p>
	 * Created on 2020年03月18日
	 * @author:huadf
	 */
	@RequestMapping(value = "details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		BranchOfficeModel model = branchOfficeBiz.queryById(id);
		modelMap.addAttribute("model", model);
		
		//List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		//modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:"+model+",other:"+model);
	}
}
