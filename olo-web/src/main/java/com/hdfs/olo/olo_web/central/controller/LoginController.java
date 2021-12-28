package com.hdfs.olo.olo_web.central.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.hdfs.olo.olo_web.central.biz.IBranchOfficeBiz;
import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.CookieHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/**
 *  登录管理
 * @author huadf
 *
 */
@Controller
public class LoginController extends BaseController{
	public final static String Module_Name = "登录";
	
	private final static String PARA_t = "t";
	
	private final static String COOKIE_key = "olo_styles";
	
	@Autowired
	private IUserInfoBiz userInfoBiz;
	@Autowired
	private IBranchOfficeBiz branchOfficeBiz;

	/**
	 * 获取登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.jhtm")
	public String login(HttpServletRequest request,HttpServletResponse response) {
		String t = request.getParameter(PARA_t);
		if (StringHelper.isNullOrEmpty(t)) {
			 t = CookieHelper.ReadCookieByKey(request, COOKIE_key); 
			if(StringHelper.isNullOrEmpty(t))
			{
				t = "1";
			}
		}
		//保存五天
		CookieHelper.WriteCookie(response, 5, COOKIE_key, t);
		return "/central/login/login";
	}

	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @param newid
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	@RequestMapping(value = "/login/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request,HttpServletResponse response, String newid, String userName,
			String userPwd) {
		try {
			Date endTime = DateTimeHelper.createDate("2022-12-25");
			if(endTime.getTime()<=new Date().getTime())
				return LayAjaxHelper.fail("暂停服务！");
			String[] validArray = {newid,userName,userPwd};
			if (StringHelper.isNullOrEmpty(validArray)) {
				return LayAjaxHelper.fail("参数异常！");
			}
			userName = userName.trim();
			userPwd = userPwd.trim();
			UserInfoModel userInfo = userInfoBiz.queryByAccount(userName);
			if (userInfo == null) {
				return LayAjaxHelper.fail("用户名不存在,请重新输入!");
			}
			if (userInfo.getStatus().equals(DictionaryUtil.KEY_NOT_NORMAL)) {
				return LayAjaxHelper.fail("该账号被锁定,请联系管理员!");
			}
			// 是否失效
			if (null != userInfo.getUserExpireDate()) {
				long i = new Date().getTime()-userInfo.getUserExpireDate().getTime();
				if (i > 0) {
					return LayAjaxHelper.fail("该账号已失效,请联系管理员!");
				}
			}
			EncryptHelper encryptHelper = EncryptHelper.getInstance();
			String newString = encryptHelper.encryptString(userPwd);
			if(!encryptHelper.compareString(userInfo.getUserPassword(), newString))
			{
				return LayAjaxHelper.fail("请输入正确的密码!");
			}
			Long branchOfficeId = userInfo.getBranch().getId();
			userInfo.setBranch(branchOfficeBiz.queryById(branchOfficeId));
			
			//保存到session中
			WebUtils.setSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER, userInfo);
			
			//shiro权限框架
			UsernamePasswordToken token = 
			new UsernamePasswordToken(userInfo.getAccount(), userInfo.getUserPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			
			log2Info(request, Module_Name, Module_Name);
			return LayAjaxHelper.success("登录成功!");
		} catch (Exception ex) {
			try {
				log2Error(request, Module_Name, Module_Name,ex.getMessage());
				LayAjaxHelper.fail("服务器异常!");
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return LayAjaxHelper.fail("登录失败!");
	}
}
