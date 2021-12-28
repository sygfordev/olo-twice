package com.hdfs.olo.olo_web.central.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.treexts.TreeNodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/**
 * 主界面控制
 * @author huadf
 *
 */
@Controller
public class MainController extends BaseController{
	
	public final static String Module_Name = "首页";
	
	@Autowired
	private IAuthorityBiz authorityBiz;
	@Autowired
	private IUserInfoBiz userInfoBiz;
	
	
	private	final static String PARA_userRealName = "userRealName";
	private	final static String PARA_userSex = "userSex";
	private	final static String PARA_userJob = "userJob";
	private	final static String PARA_userPhone = "userPhone";
	private	final static String PARA_userFax = "userFax";
	private	final static String PARA_userMobile = "userMobile";
	private	final static String PARA_userAddress = "userAddress";
	private	final static String PARA_userZip = "userZip";
	private	final static String PARA_userHomeAddress = "userHomeAddress";
	private	final static String PARA_userHomeZip = "userHomeZip";
	private	final static String PARA_userRemark = "userRemark";
	private final static String PARA_userEmail = "userEmail";
	
	/**
	 * 首页
	 * @param request
	 * @return
	 */
	@RequestMapping("/index.jhtm")
	public String index(HttpServletRequest request)throws Exception
	{
		//通过请求信息获取用户基本信息和用户角色列表
		UserInfoModel userInfo = (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
		List<SystemRoleModel> list = authorityBiz.getSystemRoleByUserId(userInfo.getUserId());
		if(null == list || list.size() == 0)
		{
			return "redirect:/main/noauthority.jhtm";//重定向
		};
		//通过该用户角色列表加载其下所有菜单
		List<Long> roleIds = new ArrayList<Long>();
		for(SystemRoleModel item:list)
		{
			roleIds.add(item.getRoleId());
		}
		JSONObject menusTree = this.loadMenuTree(userInfo.getUserId(), roleIds);
		WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_ROLE_LIST, list);
		if(null != menusTree) WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_CURR_MENUS, menusTree.getJSONArray("list"));
		return "/central/main/index";
	}
	
	
	/**
	 * 修改当前角色
	 * @param id
	 */
	@RequestMapping(value="/main/changeRole.jhtm",method = RequestMethod.POST)
	@ResponseBody
	public void changeRole(HttpServletRequest request,Long id)
	{
		if(null == id)
			return;
		SystemRoleModel systemRole = (SystemRoleModel)WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_CURR_ROLE);
		if(systemRole ==  null)
			return;
		if(!systemRole.getRoleId().equals(id))
		{
			List<SystemRoleModel> list = (List<SystemRoleModel>)WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_ROLE_LIST);
			for (SystemRoleModel item : list) {
				if(item.getRoleId().equals(id))
				{
					WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_CURR_ROLE, item);
					break;
				}
			}
		}
	}
	
	

	/**
	 * 修改当前用户信息
	 * @return
	 */
	@RequestMapping("/main/editUserInfo.jhtm")
	public String editUserInfo(HttpServletRequest request,ModelMap modelMap)
	{
		UserInfoModel model = (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
		modelMap.addAttribute("model", model);
		List<ComboxItem> sexList = getSexList();
		modelMap.addAttribute("sexList", sexList);
		return "/central/main/editUserInfo";
	}
	
	/**
	 * 修改当前用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/main/editUserInfo/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEditUserInfo(HttpServletRequest request) {
		
		Map<String, Object> map = null;
		try {
			
			UserInfoModel model = (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
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
			String userRemark = request.getParameter(PARA_userRemark);
			String userEmail = request.getParameter(PARA_userEmail);
			model.setUserRealName(userRealName.trim());
			model.setUserSex(userSex.trim());
			model.setUserJob(userJob.trim());
			model.setUserTelPhone(userPhone.trim());
			model.setUserFax(userFax.trim());
			model.setUserMobile(userMobile.trim());
			model.setUserAddress(userAddress.trim());
			model.setUserZip(userZip.trim());
			model.setUserHomeAddress(userHomeAddress.trim());
			model.setUserHomeZip(userHomeZip.trim());
			model.setRemark(userRemark.trim());
			model.setUserEmail(userEmail.trim());
			userInfoBiz.editUserInfo(model);
			WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER, model);
			log2Info(request, Module_Name, "用户信息编辑","用户编号:"+model.getUserId()+",真实姓名:"+model.getUserRealName());
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, "用户信息编辑",ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
		
	}
	
	/**
	 * 获取权限状态为正常且类型为菜单
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JSONObject loadMenuTree(Long userId,List<Long> roleIds)throws Exception{
		List<SystemPrivModel> list = authorityBiz.getUserPrivByUserAndRoleIds(userId, roleIds);
		if(null == list || 0 == list.size()) return null;
		
		List<SystemPrivModel> available = new ArrayList<SystemPrivModel>();
		//过滤
		int index = 0;
		SystemPrivModel item = null;
		for (int i=0;i<list.size();i++) {
			item = list.get(i);
			//当权限状态为非正常或非菜单时，直接跳过
			if(DictionaryUtil.KEY_NORMAL != item.getStatus() || 
					DictionaryUtil.KEY_PRIV_TYPE_1 != item.getPrivType())
				continue;
			available.add(index, item);
			index++;
		}
		
		TreeNodeUtil.CvtConfig builder = new TreeNodeUtil.CvtConfig();
		TreeNodeUtil util = builder.idKey("privId").pidKey("privSuper").rootId(available.get(0).getPrivSuper())
				.subKey("list").build();
		JSONObject tmp = util.merge(available);
		return tmp;
	}
	
	/**
	 * 退出
	 * @param request
	 * @return
	 */
	@RequestMapping("/main/loginOut.jhtm")
	public String doLoginOut(HttpServletRequest request)
	{
		try
		{
			 log2Info(request, Module_Name, LogDictionary.Module_Oper_loginOut);
			 //shiro注销
			 Subject subject = SecurityUtils.getSubject();
			 if (subject != null) {
		            subject.logout();
		     }
		}catch(Exception ex){
			 log2Error(request, Module_Name, LogDictionary.Module_Oper_loginOut,ex.getMessage());
		}finally
		{
			//session失效
			request.getSession().invalidate();
		}
		return "redirect:/login.jhtm";//重定向
	}
	
	/**
	 * to setting userInfo
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/main/set4user.jhtm")
	public String set4User(HttpServletRequest request, Long userId,ModelMap modelMap) throws Exception{
		UserInfoModel userInfo = userInfoBiz.queryUserInfoById(userId);
		modelMap.put("model", userInfo);
		
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("sexList", sexList);
		modelMap.put("statusList", statusList);
		
		List<SystemRoleModel> roles = authorityBiz.getSystemRoleByUserId(userId);
		modelMap.put("roles", roles);
		return "/central/sets/set4user";
	}
	/**
	 * do setting userInfo
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/main/set4user/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doSet4User(HttpServletRequest request,@RequestBody UserInfoModel user) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			//必须输入参数
			Long userId = user.getUserId();
			if (null == userId) {
				stringBuffer.append("用户编号不存在!");
				stringBuffer.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			UserInfoModel model = userInfoBiz.queryUserInfoById(userId);
			if(model == null)
			{
				stringBuffer.append("用户编号不存在!");
				stringBuffer.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			userInfoBiz.editUserInfo(user);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"用户编号:"+model.getUserId()+",真实姓名:"+model.getUserRealName());
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
	 * to setting userPwd
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/main/set4pwd.jhtm")
	public String set4Pwd()
	{
		return "/central/sets/set4pwd";
	}
	/**
	 * 修改用户基本信息-for top setting
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/main/set4pwd/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doSet4Pwd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("password");
			String renewPassword = request.getParameter("repassword");
			String[] valiArray = {oldPassword,newPassword,renewPassword};
			if (StringHelper.isNullOrEmpty(valiArray)) {
				return LayAjaxHelper.fail("参数有误，请重新输入!");
			}
			if (!newPassword.equals(renewPassword)) {
				return LayAjaxHelper.fail("密码不一致！");
			}
			UserInfoModel userInfo = (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
			EncryptHelper encryptHelper = EncryptHelper.getInstance();
			//判断旧密码是否正确
			oldPassword = encryptHelper.encryptString(oldPassword.trim());
			if(!encryptHelper.compareString(userInfo.getUserPassword(), oldPassword))
			{
				return LayAjaxHelper.fail("当前密码错误!");
			}
			String userPassword = encryptHelper.encryptString(newPassword.trim());
			userInfoBiz.edit4Pwd(userInfo.getUserId(), userPassword);
			userInfo.setUserPassword(userPassword);
			WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER, userInfo);
			
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_editPwd);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_editPwd,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/main/404.jhtm")
	public String notFound()
	{
		return "/error/404";
	}
	/**
	 * 获取越权页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/main/noauthority.jhtm")
	public String noauthority(HttpServletRequest request)
	{
		log2Info(request, LogDictionary.Module_Oper_noauthority, LogDictionary.Module_Oper_noauthority,"成功拦截");
		return "/error/noauthority";
	}
}
