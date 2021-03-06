package com.hdfs.olo.olo_web.central.controller.authority;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.biz.IBranchOfficeBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemRoleBiz;
import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.central.model.UserPrivModel;
import com.hdfs.olo.olo_web.central.model.UserRoleModel;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.ResponseMsg;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.treexts.TreeNodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/**
 * 
 * @author huadf
 *
 */
@Controller
@RequestMapping("/central/userInfo/*")
public class UserInfoController extends BaseController{
	Log logger = LogFactory.getLog(this.getClass());
	public final static String Module_Name = "??????";
	
	private	final static String PARA_userId = "userId";
	private	final static String PARA_account = "account";
	private	final static String PARA_userPassword = "userPassword";
	private	final static String PARA_userRealName = "userRealName";
	private	final static String PARA_userSex = "userSex";
	private	final static String PARA_branch = "branch";
	private	final static String PARA_userJob = "userJob";
	private	final static String PARA_userPhone = "userPhone";
	private	final static String PARA_userFax = "userFax";
	private	final static String PARA_userMobile = "userMobile";
	private	final static String PARA_userAddress = "userAddress";
	private	final static String PARA_userZip = "userZip";
	private	final static String PARA_userHomeAddress = "userHomeAddress";
	private	final static String PARA_userHomeZip = "userHomeZip";
	private	final static String PARA_userStatus = "status";
	private	final static String PARA_userExpireDate = "userExpireDate";
	private	final static String PARA_userRemark = "remark";
	private final static String PARA_userEmail = "userEmail";
	
	@Autowired
	private IUserInfoBiz userInfoBiz;
	@Autowired
	private IBranchOfficeBiz branchOfficeBiz;
	@Autowired
	private IAuthorityBiz authorityBiz;
	@Autowired
	private ISystemRoleBiz systemRoleBiz;

	/**
	 * ????????????
	 * @param request
	 * @param model
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("sexList", sexList);
		modelMap.put("statusList", statusList);
	}
	
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<UserInfoModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<UserInfoModel> retBody = new Result4Page<UserInfoModel>();
		//????????????
		Page.Builder<UserInfoModel> builder = new Page.Builder<UserInfoModel>();
		setSearchParameters(request, builder);
		Page<UserInfoModel> pageInfo = builder.build();
		try {
			//??????
			userInfoBiz.queryPageUserInfo(pageInfo);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("???????????????");
			retBody.setData(pageInfo.getDatas());
			retBody.setCount(pageInfo.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("???????????????");
		}
		return retBody;
	}
	
	/**
	 * ??????????????????
	 * @param request
	 * @param pageInfo
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<UserInfoModel> builder) {
		String pageStr  = request.getParameter("page");
		String limitStr  = request.getParameter("limit");
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		String account = request.getParameter(PARA_account);
        String realName =  request.getParameter(PARA_userRealName);
        String userSex =  request.getParameter(PARA_userSex);
        String userStatus =  request.getParameter(PARA_userStatus);
        String mobile =  request.getParameter(PARA_userMobile);
        String email = request.getParameter(PARA_userEmail);
        
        String orderBy  = "UPDATE_TIME DESC";
        
        UserInfoModel dto = new UserInfoModel();
        if(!StringHelper.isNullOrEmpty(account))
        	dto.setAccount(account);
        if(!StringHelper.isNullOrEmpty(realName))
        	dto.setUserRealName(realName);
        if(!StringHelper.isNullOrEmpty(userSex))
        	dto.setUserSex(userSex);
        if(!StringHelper.isNullOrEmpty(userStatus))
        	dto.setStatus(userStatus);
        if(!StringHelper.isNullOrEmpty(mobile))
        	dto.setUserMobile(mobile);
        if(!StringHelper.isNullOrEmpty(email))
        	dto.setUserEmail(email);
		builder.curPageIndex(curPageIndex).pageSize(pageSize).orderBy(orderBy).model(dto);
	}

	/**
	 * ??????
	 * @param request
	 * @param stringBuffer  ????????????
	 * @param model	
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, UserInfoModel model) {

		String branch = request.getParameter(PARA_branch);
		if (StringHelper.isNullOrEmpty(branch)) {
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		
		String userRealName = request.getParameter(PARA_userRealName);
		String userSex = request.getParameter(PARA_userSex);
		String userJob = request.getParameter(PARA_userJob);
		String userPhone = request.getParameter(PARA_userPhone);
		String userFax = request.getParameter(PARA_userFax);
		String userMobile = request.getParameter(PARA_userMobile);
		String userAddress = request.getParameter(PARA_userAddress);
		String userZip = request.getParameter(PARA_userZip);
		String userHomeAddress = request.getParameter(PARA_userHomeAddress);
		String userHomeZip = request.getParameter(PARA_userHomeZip);
		String userStatus = request.getParameter(PARA_userStatus);
		String userExpireDate = request.getParameter(PARA_userExpireDate);
		String userRemark = request.getParameter(PARA_userRemark);
		String userEmail = request.getParameter(PARA_userEmail);
		
		if (stringBuffer.length() == 0) {
			model.setUserRealName(userRealName.trim());
			model.setUserSex(userSex.trim());
			
			Long branchId = StringHelper.isNullOrEmpty(branch)?null:Long.parseLong(branch);
			BranchOfficeModel branchOffice = new BranchOfficeModel();
			branchOffice.setId(null != branch?branchId:null);
			model.setBranch(branchOffice);
			model.setUserJob(null!=userJob?userJob.trim():null);
			model.setUserTelPhone(null!=userPhone?userPhone.trim():null);
			model.setUserFax(null!=userFax?userFax.trim():null);
			model.setUserMobile(null!=userMobile?userMobile.trim():null);
			model.setUserAddress(null!=userAddress?userAddress.trim():null);
			model.setUserZip(null!=userZip?userZip.trim():null);
			model.setUserHomeAddress(null!=userHomeAddress?userHomeAddress.trim():null);
			model.setUserHomeZip(null!=userHomeZip?userHomeZip.trim():null);
			model.setStatus(null!=userStatus?userStatus.trim():null);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date expire = null;
			try {
				expire = sdf.parse(userExpireDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			model.setUserExpireDate(null!=userExpireDate?expire:null);
			model.setRemark(null!=userRemark?userRemark.trim():null);
			model.setUserEmail(null!=userEmail?userEmail.trim():null);
		}
	}

	/**
	 * ??????????????????
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
			String account = request.getParameter(PARA_account);
			if (StringHelper.isNullOrEmpty(account)) {
				stringBuffer.append("????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			String userPassword = request.getParameter(PARA_userPassword);
			if (StringHelper.isNullOrEmpty(userPassword)) {
				stringBuffer.append("????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			UserInfoModel model = new UserInfoModel();
			//????????????
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0)//????????????
			{
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			model.setAccount(account);
			//????????????
			userPassword = EncryptHelper.getInstance().encryptString(userPassword.trim());
			model.setUserPassword(userPassword.trim());
			
			//??????
			userInfoBiz.saveUserInfo(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"????????????:"+model.getUserId()+",????????????:"+model.getUserRealName());
			
			//??????????????????
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}

	/**
	 *  ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(Long userId, ModelMap modelMap) throws Exception{
		if(null == userId) return;
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
		
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
	}

	/**
	 *  ????????????
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("userId") Long userId) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			//??????????????????
			if (null == userId) {
				stringBuffer.append("??????????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
			if(model == null)
			{
				stringBuffer.append("??????????????????????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			userInfoBiz.editUserInfo(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"????????????:"+model.getUserId()+",????????????:"+model.getUserRealName());
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//????????????
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
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
	public Map<String, Object> delete(HttpServletRequest request,Long userId) {
		Map<String, Object> map = null;
		try {
			if(null == userId) return LayAjaxHelper.fail("???????????????!");
			UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
			if(model != null)
			{
				authorityBiz.deleteUserInfo(userId);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"????????????:"+model.getUserId()+",????????????:"+model.getUserRealName());
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("???????????????!");
			authorityBiz.batchDelUserInfo(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"????????????:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}

	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value = "details.jhtm")
	public void details(HttpServletRequest request,String id, ModelMap modelMap) throws Exception{
		Long userId = (null!=id)?Long.parseLong(id):null;
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
		
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"????????????:"+model.getUserId()+",????????????:"+model.getUserRealName());
	}
	
	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value = "editPwd.jhtm")
	public void editPwd(String id, ModelMap modelMap) throws Exception{
		Long userId = (null!=id)?Long.parseLong(id):null;
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
		
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
	}
	
	
	/**
	 * ??????????????????
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "editPwd/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEditPwd(HttpServletRequest request,@RequestParam("userId")Long userId) {
		Map<String, Object> map = null;
		StringBuffer stringBuffer = new StringBuffer();
		//??????????????????
		if (null == userId) {
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
			return LayAjaxHelper.fail(stringBuffer.toString());
		}
		UserInfoModel model = null;
		try {
			model = userInfoBiz.queryUserInfoById(userId);
			if(model == null)
			{
				stringBuffer.append("??????????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			
			String pwd = request.getParameter(PARA_userPassword);
			if (StringHelper.isNullOrEmpty(pwd)) {
				stringBuffer.append("????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			//????????????
			pwd = EncryptHelper.getInstance().encryptString(pwd.trim());
			userInfoBiz.edit4Pwd(userId, pwd);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_editPwd,"????????????:"+model.getUserId()+",????????????:"+model.getUserRealName());
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			String txt = "????????????:"+ userId;
			if(model != null) txt = txt + ",????????????:"+model.getUserRealName();
			log2Error(request, Module_Name, LogDictionary.Module_Oper_editPwd,txt);
			//????????????
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	
	/**
	 * ????????????-?????????????????? 
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value = "userRolePrivCfg.jhtm")
	public void userRolePrivCfg(HttpServletRequest request,Long userId, ModelMap modelMap) throws Exception{
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg);
	}
	
	/**
	 * ????????????-?????????????????? 
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value = "userRolePrivCfg/loadList.jhtm")
	@ResponseBody
	public Result4Page<SystemRoleModel> userRolePrivCfgList(HttpServletRequest request,Long userId, ModelMap modelMap) throws Exception{
		Result4Page<SystemRoleModel> retBody = new Result4Page<SystemRoleModel>();
		if(null == userId)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("?????????????????????");
			return retBody;
		}
		try {
			List<SystemRoleModel> list = authorityBiz.getSystemRoleByUserId(userId);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("???????????????");
			retBody.setData(list);
			retBody.setCount(Long.valueOf(list.size()));
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("???????????????");
		}
		log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg);
		return retBody;
	}
	
	
	/**
	 * ?????? ??????-?????? ????????????
	 * @param modelMap
	 */
	@RequestMapping(value="userRoleAdd.jhtm")
	public void userRoleAdd(Long userId, ModelMap modelMap) throws Exception{
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
		
		//????????????????????????????????????--?????????????????????????????????
		List<SystemRoleModel> list = systemRoleBiz.queryList(new SystemRoleModel());
		List<SystemRoleModel> setedList = authorityBiz.getSystemRoleByUserId(userId);
		if(setedList.size()<=0)
		{
			modelMap.addAttribute("list", list);
			modelMap.addAttribute("msg", list.size()==0?"???????????????????????????????????????????????????":"");
			return;
		}
		Iterator<SystemRoleModel> it = list.iterator();
		SystemRoleModel item = null;
		while(it.hasNext())
		{
			item = it.next();
			for(SystemRoleModel seted:setedList)
			{
				if(!item.getRoleId().equals(seted.getRoleId()))
					continue;
				it.remove();
			}
		}
		modelMap.addAttribute("list", list);
		modelMap.addAttribute("msg", list.size()==0?"???????????????????????????????????????????????????":"");
	}
	
	/**
	 * ??????
	 * @param id
	 * @param roleId
	 * @param stringBuffer
	 */
	private void baseValidate(Long userId,Long roleId,StringBuffer stringBuffer) throws Exception{
		//??????????????????
		if (null == userId) {
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if (null == roleId) {
			stringBuffer.append("??????????????????!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if(stringBuffer.length() == 0)
		{
			UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
			if(model == null)
			{
				stringBuffer.append("??????????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			SystemRoleModel systemRole = systemRoleBiz.queryById(roleId);
			if(systemRole == null)
			{
				stringBuffer.append("??????????????????!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
		}
	}
	
	
	/**
	 * ??????-?????? ????????????
	 * @param request
	 * @param id
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="userRoleAdd/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> doUserRoleAdd(HttpServletRequest request,Long userId,Long roleId, ModelMap modelMap) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			baseValidate(userId, roleId, stringBuffer);
			
			if(stringBuffer.length()>0)
			{
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			List<SystemRoleModel> currSystemRoleList = authorityBiz.getSystemRoleByUserId(userId);
			boolean isok = false;
			for (SystemRoleModel item : currSystemRoleList) {
				if(item.getRoleId().equals(roleId))
				{
					isok = true;
					break;
				}
			}
			if(isok == true)
			{
				return LayAjaxHelper.fail("??????????????????,???????????????!");
			}
			
			UserRoleModel userRole = new UserRoleModel();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			authorityBiz.addUserRole(userRole);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg+"-"+LogDictionary.Module_Oper_add,"????????????:"+userId+",????????????:"+roleId);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg+"-"+LogDictionary.Module_Oper_add,"????????????:"+userId+",????????????:"+roleId);
			//????????????
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ??????-?????? ????????????
	 * @param request
	 * @param id
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "userRoleDelete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doUserRoleDelete(HttpServletRequest request,Long userId,Long roleId) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			baseValidate(userId, roleId, stringBuffer);
			if(stringBuffer.length()>0)
			{
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			
			UserRoleModel userRole = new UserRoleModel();
			userRole.setRoleId(roleId);
			userRole.setUserId(userId);
			authorityBiz.deleteUserRole(userRole);
			
			log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg+"-"+LogDictionary.Module_Oper_delete,"????????????:"+userId+",????????????:"+roleId);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg+"-"+LogDictionary.Module_Oper_delete,"????????????:"+userId+",????????????:"+roleId);
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	
	/**
	 * ??????-?????? ??????
	 * @param request
	 * @param id
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "userRolePrivEdit.jhtm")
	public void userRolePrivEdit(HttpServletRequest request,Long userId,Long roleId,ModelMap modelMap) throws Exception{
		UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
		modelMap.addAttribute("model", model);
		
		SystemRoleModel systemRole = systemRoleBiz.queryById(roleId);
		modelMap.addAttribute("systemRole", systemRole);
		
		//?????? ???????????????????????????????????????-????????????????????????
		List<SystemPrivModel> systemPrivList = authorityBiz.getUserPrivByUserIdAndRoleId(userId, roleId);
		String str = "";
        if(systemPrivList.size() > 0)
        {
        	StringBuffer buffer = new StringBuffer();
            for (SystemPrivModel item : systemPrivList)
            {
            	buffer.append(item.getPrivId());
            	buffer.append(",");
            }
            str =  buffer.substring(0, buffer.length()-1);
        }
        modelMap.addAttribute("systemPrivList", str);
        log2Info(request, Module_Name, LogDictionary.Module_Oper_userRolePrivEdit,"????????????:"+userId+",????????????:"+roleId);
	}
	
	
	
	/**
	 * ajax??????????????????????????????
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "userRolePrivEdit/getPriv.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> userRolePrivEditGetSystemPriv(Long userId,Long roleId)
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		//??????????????????
		if(null == roleId || null == userId)
		{
			retMap.put("code", 1);
			retMap.put("msg", "????????????????????????????????????");
			return retMap;
		}
		try {
			//???????????????????????????????????????????????????????????????????????????
			List<SystemPrivModel> list = authorityBiz.getAllSystemPrivByRoleId(roleId);
			List<SystemPrivModel> seted = authorityBiz.getUserPrivByUserIdAndRoleId(userId, roleId);
			List<Long> checked = new ArrayList<Long>();
			for(SystemPrivModel item:seted)
			{
				checked.add(item.getPrivId());
			}
			
			//????????????????????????
			TreeNodeUtil.CvtConfig builder = new TreeNodeUtil.CvtConfig();
			Map<String,String> keyMaps = new HashMap<String,String>();
			keyMaps.put("privCnName", "name");
			keyMaps.put("privId", "value");
			TreeNodeUtil util = builder.idKey("privId").pidKey("privSuper").rootId(-1l).subKey("list").checkedIds(checked).keyMaps(keyMaps).build();
			JSONObject tmp = util.merge(list);
			JSONObject data = new JSONObject();
			JSONArray array = new JSONArray();
			array.add(tmp);
			data.put("trees", array);
			retMap.put("code", 0);
			retMap.put("msg", "????????????");
			retMap.put("data", data);
		}catch(Exception e)
		{
			retMap.put("code", 1);
			retMap.put("msg", "?????????????????????");
			logger.error("????????????????????????????????????!error:",e);
		}
		return retMap;
	}
	
	/**
	 * ??????-?????? ????????????
	 * @param request
	 * @param id
	 * @param roleId
	 * @param systemPrivList
	 * @return
	 */
	@RequestMapping(value = "userRolePrivEdit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doUserRolePrivEdit(HttpServletRequest request,Long userId,Long roleId,@RequestParam("systemPrivList[]")String[] systemPrivList) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			baseValidate(userId, roleId, stringBuffer);
			if(stringBuffer.length()>0)
			{
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			if(StringHelper.isNullOrEmpty(systemPrivList))
			{
				return LayAjaxHelper.fail("????????????");
			}
			
			List<UserPrivModel> list = new ArrayList<UserPrivModel>();
			//?????????????????????,???????????????
			UserPrivModel userPriv = null;
			for (String item : systemPrivList) {
				userPriv = new UserPrivModel();
				userPriv.setPrivId(Long.parseLong(item));
				userPriv.setRoleId(roleId);
				userPriv.setUserId(userId);
				list.add(userPriv);
			}
			authorityBiz.updateUserPriv(userId, roleId, list);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_userRolePrivEdit+"-"+LogDictionary.Module_Oper_edit,"????????????:"+userId+",????????????:"+roleId);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_userRolePrivEdit+"-"+LogDictionary.Module_Oper_edit,"????????????:"+userId+",????????????:"+roleId);
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 */
	@RequestMapping("lookup.jhtm")
	public void lookup(HttpServletRequest request,ModelMap modelMap) {
		String curBranch = request.getParameter("branchId");
		String curBranchName = null;
		try {
			curBranchName = new String(request.getParameter("branchName").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		modelMap.put("curBranchId", curBranch);
		modelMap.put("curBranchName", curBranchName);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_lookup);
	}
	
	@RequestMapping(value="lookup/do.jhtm",method=RequestMethod.POST)
	@ResponseBody
	public ResponseMsg<Object> lookupGetAll(HttpServletRequest request)
	{
		ResponseMsg<Object> retObj = new ResponseMsg<Object>();
		try {
			//??????????????????????????????
			List<BranchOfficeModel> list = branchOfficeBiz.queryList(new BranchOfficeModel());
			//????????????
			TreeNodeUtil.CvtConfig builder = new TreeNodeUtil.CvtConfig();
			Map<String,String> keyMaps = new HashMap<String,String>();
			keyMaps.put("branchName", "title");
			keyMaps.put("parentId", "pid");
			TreeNodeUtil util = builder.rootId(-1l).idKey("id").pidKey("parentId")
					.subKey("children").keyMaps(keyMaps).build();
			JSONObject tmp = util.merge(list);
			JSONArray data = new JSONArray();
			data.add(tmp);
			
			WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_BRANCH_LIST, data);
			//???????????????????????????
			retObj.setRetCode("200");
			retObj.setErrorDesc("????????????");
			retObj.setResponseBody(data);
		}catch(Exception e)
		{
			retObj.setRetCode("201");
			retObj.setErrorDesc("????????????");
			logger.error("??????????????????! error???",e);
		}
		return retObj;
	}
}
