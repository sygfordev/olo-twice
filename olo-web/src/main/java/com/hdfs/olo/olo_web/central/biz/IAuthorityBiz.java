package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.central.model.UserPrivModel;
import com.hdfs.olo.olo_web.central.model.UserRoleModel;
/**
 * 用户、角色、权限关系管理
 * @author huadf
 *
 */
public interface IAuthorityBiz {
	
	/**
	 * 获取所有的权限列表
	 * @return
	 */
	public List<SystemPrivModel> getAllSystemPriv();
	
	/**
	 * 通过角色编号获取其所有权限功能
	 * @param roleId
	 * @return
	 */
	public List<SystemPrivModel> getAllSystemPrivByRoleId(Long roleId);
	
	/**
	 * 更新角色下面的权限，更新用户-权限信息
	 * @param systemRole
	 * @param addList 增加的权限集合
	 * @param deleteList	删除的权限集合
	 * @param isAppend	是否往用户-权限追加新的权限
	 */
	public void updateSystemPriv4Role(SystemRoleModel systemRole,List<SystemPrivModel> addList,List<SystemPrivModel> deleteList,boolean isAppend);
	
	/**
	 * 通过角色编号获取，其已选用户列表和可选用户列表
	 * @param roleId
	 * @return
	 * 			数组，0代表已选用户列表
     *				 1代表可选用户列表
	 */
	public Object[] getUserInfoListByRoleId(Long roleId);
	
	/**
	 * 更新用户-角色，更新用户-权限
	 * @param systemRole
	 * @param addList	增加的用户集合
	 * @param deleteList	删除的用户集合
	 */
	public void updateUserRole(SystemRoleModel systemRole,List<UserInfoModel> addList,List<UserInfoModel> deleteList);

	/**
	 * 通过用户编号获取角色列表
	 * @param userId
	 * @return
	 */
	public List<SystemRoleModel> getSystemRoleByUserId(Long userId);
	/**
	 * 增加 用户-角色信息,增加用户-权限信息
	 * @param userRole
	 */
	public void addUserRole(UserRoleModel userRole);
	/**
	 * 删除 用户-角色信息,增加用户-权限信息
	 * @param userRole
	 */
	public void deleteUserRole(UserRoleModel userRole);
	
	/**
	 * 通过 通过用户、角色编号获取用户-权限表中权限信息
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public  List<SystemPrivModel> getUserPrivByUserIdAndRoleId(Long userId,Long roleId);
	
	/**
	 * 通过用户编编号和角色编号列表获取用户-权限表中的权限信息
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	public List<SystemPrivModel> getUserPrivByUserAndRoleIds(Long userId,List<Long> roleIds);
	
	/**
	 *  更新 用户-权限
	 *  	1、通过用户编号和角色编号删除用户-权限信息
	 *  	2、增加用户-权限信息
	 * @param userId
	 * @param roleId
	 * @param list
	 */
	public void updateUserPriv(Long userId,Long roleId,List<UserPrivModel> list);
	/**
	 * 删除 权限、用户-权限、角色-权限
	 * 	
	 * @param privId
	 */
	public void deleteSystemPriv(Long privId);
	
	/**
	 * 删除 角色、用户-权限、角色-权限
	 * @param roleId
	 */
	public void deleteSystemRole(Long roleId);
	
	/**
	 * 批量删除 角色、用户-权限、角色-权限
	 * @param List<String> roleIds
	 */
	public void deleteBatchSystemRole(List<Long> roleIds);
	
	/**
	 * 删除 用户、用户-权限、用户-角色
	 * @param userId
	 */
	public void deleteUserInfo(Long userId);
	
	/**
	 * 批量删除 用户、用户-权限、用户-角色
	 * @param userId
	 */
	public void batchDelUserInfo(List<Long> userIds);
	
	/**
	 * 通过用户编号获取去重的用户-权限信息
	 * @param userId
	 * @return
	 */
	public List<SystemPrivModel> getUserPrivDistinctByUserId(Long userId);
} 
