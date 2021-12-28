package com.hdfs.olo.olo_web.plugins.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;


/**
 *	 数据库Realm.
 *  Realm是一个执行者，负责真正的认证和鉴权
 * @author huadf
 *
 */
@Component
public class ShiroDataBaseRealm extends AuthorizingRealm{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAuthorityBiz authorityBiz;
	@Autowired
	private IUserInfoBiz userInfoBiz;
	/**
	 * 认证回调函数,登录时调用.
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserInfoModel userInfo = null;
		try
		{
			userInfo = userInfoBiz.queryByAccount(token.getUsername());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		SimpleAuthenticationInfo info = null;
		if (userInfo != null) {
			info =  new SimpleAuthenticationInfo(userInfo.getUserId(), userInfo.getUserPassword(), getName());
		} 
		return info;
	}
	
	
	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 * @param principals
	 * @return
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Long userId = (Long) principals.fromRealm(getName()).iterator().next();
		UserInfoModel userInfo = null;
		try
		{
			userInfo = userInfoBiz.queryUserInfoById(userId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		SimpleAuthorizationInfo info = null;
		if (userInfo != null) {
			info = new SimpleAuthorizationInfo();
			List<SystemPrivModel> list = authorityBiz.getUserPrivDistinctByUserId(userInfo.getUserId());
			
			String method = null;
			String permissioin = null;
			for (SystemPrivModel item : list) {
				method = item.getPrivMethod();
				permissioin = item.getPrivPermissioin();
				if(!StringHelper.isNullOrEmpty(method))
				{
					if(!StringHelper.isNullOrEmpty(permissioin))
					{
						permissioin = permissioin.trim();
						
						if(permissioin.indexOf(":") > 0)
						{
							info.addStringPermission(permissioin);
						}
					}
				}
			}
		}
		return info;
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
	
}
