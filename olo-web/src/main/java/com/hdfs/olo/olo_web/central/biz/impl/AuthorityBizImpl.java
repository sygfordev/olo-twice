package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.mapper.AuthorityMapper;
import com.hdfs.olo.olo_web.central.mapper.SystemPrivMapper;
import com.hdfs.olo.olo_web.central.mapper.SystemRoleMapper;
import com.hdfs.olo.olo_web.central.mapper.UserInfoMapper;
import com.hdfs.olo.olo_web.central.model.RolePrivModel;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.central.model.UserPrivModel;
import com.hdfs.olo.olo_web.central.model.UserRoleModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;

@DataSource(value="smart")
@Service("authorityBiz")
public class AuthorityBizImpl implements IAuthorityBiz {
	
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private SystemPrivMapper systemPrivMapper;
	@Autowired
	private SystemRoleMapper systemRoleMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * 获取所有的权限列表
	 * @return
	 */
	public List<SystemPrivModel> getAllSystemPriv()
	{
		List<SystemPrivModel> list = systemPrivMapper.queryList(new SystemPrivModel(), null);
		SystemPrivModel item = null;
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if(DictionaryUtil.KEY_NOT_NORMAL == item.getStatus())
			{
				//如果该权限为禁用，则追加“禁用”字符
				item.setPrivCnName(item.getPrivCnName() + "("+DictionaryUtil.CN_KEY_DISABLE+")");
			}
		}
		return list;
	}
	
	/**
	 * 通过角色编号获取其所有权限功能
	 * @param roleId
	 * @return
	 */
	public List<SystemPrivModel> getAllSystemPrivByRoleId(Long roleId) {
		List<SystemPrivModel> list = authorityMapper.findAllSystemPrivByRoleId(roleId);
		SystemPrivModel item = null;
		for (int i = 0; i < list.size(); i++) {
			item = list.get(i);
			if(DictionaryUtil.KEY_NOT_NORMAL == item.getStatus())
			{
				//如果该权限为禁用，则追加“禁用”字符
				item.setPrivCnName(item.getPrivCnName() + "("+DictionaryUtil.CN_KEY_DISABLE+")");
			}
		}
		return list;
	}
	
	
	/**
	 * 更新角色下面的权限，更新用户-权限信息
	 * @param systemRole
	 * @param addList 增加的权限集合
	 * @param deleteList	删除的权限集合
	 * @param isAppend true是往用户-权限追加新的权限,false不追加
	 */
	public void updateSystemPriv4Role(SystemRoleModel systemRole,List<SystemPrivModel> addList,List<SystemPrivModel> deleteList,boolean isAppend)
	{
		/**删除 角色-权限、用户-权限*******/
		if(deleteList.size() >0)
		{
			List<Long> list = new ArrayList<Long>();
			for (SystemPrivModel item : deleteList)
	         {
				list.add(item.getPrivId());
	         }
		 
	     	//删除 角色-权限
		    authorityMapper.deleteRolePrivByRoleIdAndPrivList(systemRole.getRoleId(), list);
	 		//删除 用户-权限
	 		authorityMapper.deleteUserPrivByRoleIdAndPrivList(systemRole.getRoleId(), list);
		}
		//增加 角色-权限
		if(addList.size() > 0)
		{
			List<RolePrivModel> list = new ArrayList<RolePrivModel>();
			RolePrivModel model = null;
			for (SystemPrivModel item : addList) {
				model = new RolePrivModel();
				model.setPrivId(item.getPrivId());
				model.setRoleId(systemRole.getRoleId());
				list.add(model);
			}
			//增加
			authorityMapper.saveRolePrivList(list);
			if(isAppend)//追加
			{
				//增加 用户-权限
				
				//获取当前角色所有用户
				List<UserRoleModel> userRoleList = authorityMapper.findUserRoleByRoleId(systemRole.getRoleId());
				if(userRoleList.size()>0)
				{
					List<UserPrivModel> userPrivList = new ArrayList<UserPrivModel>();
					UserPrivModel userPriv = null;
					for (UserRoleModel userRole : userRoleList) {
						for (SystemPrivModel item : addList) {
							userPriv = new UserPrivModel();
							userPriv.setPrivId(item.getPrivId());
							userPriv.setRoleId(systemRole.getRoleId());
							userPriv.setUserId(userRole.getUserId());
							userPrivList.add(userPriv);
						}
					}
					authorityMapper.saveUserPrivList(userPrivList);
					
				}
			}
		}
	}
	
	/**
	 * 通过角色编号获取，其已选用户列表和可选用户列表
	 * @param roleId
	 * @return
	 * 			数组，0代表已选用户列表
     *				 1代表可选用户列表
	 */
	public Object[] getUserInfoListByRoleId(Long roleId)
	{
		List<UserInfoModel> list1 = authorityMapper.findUserInfoListByRoleId(roleId);
		List<UserInfoModel> list2 = authorityMapper.findUserInfoListByRoleIdNotIn(roleId);
		Object[] obj = {list1,list2};
		return obj;
	}
	
	/**
	 * 更新用户-角色，更新用户-权限
	 * @param systemRole
	 * @param addList	增加的用户集合
	 * @param deleteList	删除的用户集合
	 */
	public void updateUserRole(SystemRoleModel systemRole,List<UserInfoModel> addList,List<UserInfoModel> deleteList)
	{
		if(addList.size()>0)
		{
			List<UserRoleModel> list = new ArrayList<UserRoleModel>();
			UserRoleModel userRole = null;
			for (UserInfoModel item : addList) {
				userRole = new UserRoleModel();
				userRole.setRoleId(systemRole.getRoleId());
				userRole.setUserId(item.getUserId());
				list.add(userRole);
			}
			//增加用户-角色
			authorityMapper.saveUserRoleList(list);
			
			//角色下面所有权限
			List<SystemPrivModel> systemPrivList = authorityMapper.findAllSystemPrivByRoleId(systemRole.getRoleId());
			
			List<UserPrivModel> userPrivList = new ArrayList<UserPrivModel>();
			UserPrivModel userPriv = null;
			for (UserInfoModel item : addList) {
				for (SystemPrivModel systemPriv : systemPrivList) {
					userPriv = new UserPrivModel();
					userPriv.setPrivId(systemPriv.getPrivId());
					userPriv.setRoleId(systemRole.getRoleId());
					userPriv.setUserId(item.getUserId());
					userPrivList.add(userPriv);
				}
			}
			//增加用户-权限
			authorityMapper.saveUserPrivList(userPrivList);
		}
		
		if(deleteList.size() > 0)
		{
			List<Long> list = new ArrayList<Long>();
			for (UserInfoModel item : deleteList)
	         {
				list.add(item.getUserId());
	         }
		    //删除 用户-角色
		    authorityMapper.deleteUserRoleByRoleIdAndUserList(systemRole.getRoleId(), list);
		    //删除 用户-权限
		    authorityMapper.deleteUserPrivByRoleIdAndUserList(systemRole.getRoleId(), list);
		}
	}
	
	
	/**
	 * 通过用户编号获取角色列表
	 * @param userId
	 * @return
	 */
	public List<SystemRoleModel> getSystemRoleByUserId(Long userId)
	{
		return authorityMapper.findSystemRoleByUserId(userId);
	}
	
	
	/**
	 * 增加 用户-角色信息,增加用户-权限信息
	 * @param userRole
	 */
	public void addUserRole(UserRoleModel userRole)
	{

		authorityMapper.saveUserRole(userRole);
		
		//角色下面所有权限
		List<SystemPrivModel> systemPrivList = authorityMapper.findAllSystemPrivByRoleId(userRole.getRoleId());
		
		List<UserPrivModel> userPrivList = new ArrayList<UserPrivModel>();
		UserPrivModel userPriv = null;
		for (SystemPrivModel systemPriv : systemPrivList) {
			userPriv = new UserPrivModel();
			userPriv.setPrivId(systemPriv.getPrivId());
			userPriv.setRoleId(userRole.getRoleId());
			userPriv.setUserId(userRole.getUserId());
			userPrivList.add(userPriv);
		}
		//增加用户-权限
		authorityMapper.saveUserPrivList(userPrivList);
	}
	
	/**
	 * 删除 用户-角色信息,增加用户-权限信息
	 * @param userRole
	 */
	public void deleteUserRole(UserRoleModel userRole)
	{
		authorityMapper.deleteUserRole(userRole);
		authorityMapper.deleteUserPrivByUserIdAndRoleId(userRole.getUserId(), userRole.getRoleId());
	}
	
	/**
	 * 通过 通过用户、角色编号获取用户-权限表中权限信息
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public  List<SystemPrivModel> getUserPrivByUserIdAndRoleId(Long userId,Long roleId)
	{
		return authorityMapper.findUserPrivByUserIdAndRoleId(userId, roleId);
	}
	
	public List<SystemPrivModel> getUserPrivByUserAndRoleIds(Long userId,List<Long> roleIds)
	{
		return authorityMapper.findUserPrivByUserAndRoleIds(userId, roleIds);
	}
	/**
	 *  更新 用户-权限
	 *  	1、通过用户编号和角色编号删除用户-权限信息
	 *  	2、增加用户-权限信息
	 * @param userId
	 * @param roleId
	 * @param list
	 */
	public void updateUserPriv(Long userId,Long roleId,List<UserPrivModel> list)
	{
		List<Long> strs = new ArrayList<Long>();
		strs.add(userId);
		authorityMapper.deleteUserPrivByRoleIdAndUserList(roleId, strs);
		authorityMapper.saveUserPrivList(list);
	}
	
	/**
	 * 删除 权限、用户-权限、角色-权限
	 * 	
	 * @param privId
	 */
	public void deleteSystemPriv(Long privId)
	{
		systemPrivMapper.delById(privId);
		authorityMapper.deleteUserPrivByPrivId(privId);
		authorityMapper.deleteRolePrivByPrivId(privId);
	}
	
	/**
	 * 删除 角色、用户-权限、角色-权限
	 * @param roleId
	 */
	public void deleteSystemRole(Long roleId)
	{
		systemRoleMapper.delById(roleId);
		authorityMapper.deleteUserPrivByRoleId(roleId);
		authorityMapper.deleteRolePrivByRoleId(roleId);
	}
	
	/**
	 * 批量删除 角色、用户-权限、角色-权限
	 * @param roleId
	 */
	public void deleteBatchSystemRole(List<Long> roleIds)
	{
		for(Long roleId:roleIds)
		{
			systemRoleMapper.delById(roleId);
			authorityMapper.deleteUserPrivByRoleId(roleId);
			authorityMapper.deleteRolePrivByRoleId(roleId);
		}
	}
	
	/**
	 * 删除 用户、用户-权限、用户-角色
	 * @param userId
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void deleteUserInfo(Long userId)
	{
		userInfoMapper.delUserInfoById(userId);
		authorityMapper.deleteUserPrivByUserId(userId);
		authorityMapper.deleteUserRoleByUserId(userId);
	}
	/**
	 * 批量删除 用户、用户-权限、用户-角色
	 * @param userId
	 */
	public void batchDelUserInfo(List<Long> userIds)
	{
		for(Long item:userIds)
		{
			deleteUserInfo(item);
		}
	}
	/**
	 * 通过用户编号获取去重的用户-权限信息
	 * @param userId
	 * @return
	 */
	public List<SystemPrivModel> getUserPrivDistinctByUserId(Long userId)
	{
		return authorityMapper.findUserPrivDistinctByUserId(userId);
	}
}