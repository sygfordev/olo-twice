package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.RolePrivModel;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.central.model.UserPrivModel;
import com.hdfs.olo.olo_web.central.model.UserRoleModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;



@MyBatisRepository
public interface AuthorityMapper {
	/**
	 * 通过角色编号获取其所有权限功能
	 * @param roleId
	 * @return
	 */
	public List<SystemPrivModel> findAllSystemPrivByRoleId(Long roleId);
	
	/**
	 * 通过角色编号和权限字符串删除 角色-权限 数据
	 * @param roleId
	 * @param list
	 */
	public void deleteRolePrivByRoleIdAndPrivList(Long roleId,List<Long> list);
	
	/**
	 * 通过角色编号和权限字符串删除 用户-权限 数据
	 * @param roleId
	 * @param list
	 */
	public void deleteUserPrivByRoleIdAndPrivList(Long roleId,List<Long> list);
	
	/**
	 * 通过角色编号和用户字符串删除 用户-角色信息
	 * @param roleId
	 * @param list
	 */
	public void deleteUserRoleByRoleIdAndUserList(Long roleId,List<Long> list);
	
	/**
	 * 通过角色编号和用户字符串删除 用户-权限信息
	 * @param roleId
	 * @param list
	 */
	public void deleteUserPrivByRoleIdAndUserList(Long roleId,List<Long> list);
	
	
	/**
	 * 增加 角色 -权限 数据集合
	 * @param list
	 */
	public void saveRolePrivList(List<RolePrivModel> list);
	/**
	 * 通过 角色编号 获取 用户-权限 数据集合
	 * @param roleId
	 * @return
	 */
	public List<UserRoleModel> findUserRoleByRoleId(Long roleId);
	
	/**
	 * 增加 用户 -权限 数据集合
	 * @param list
	 */
	public void saveUserPrivList(List<UserPrivModel> list);
	
	
	/**
	 * 通过角色编号获取，其已选用户列表和可选用户列表
	 * @param roleId
	 * @return
	 * 			数组，0代表已选用户列表
     *				 1代表可选用户列表
	 */
	public List<UserInfoModel> findUserInfoListByRoleId(Long roleId);
	public List<UserInfoModel> findUserInfoListByRoleIdNotIn(Long roleId);
	
	/**
	 * 增加 用户-角色 数据集合
	 * @param list
	 */
	public void saveUserRoleList(List<UserRoleModel> list);
	
	/**
	 * 通过用户编号获取角色列表
	 * @param userId
	 * @return
	 */
	public List<SystemRoleModel> findSystemRoleByUserId(Long userId);
	
	/**
	 * 保存用户-角色信息
	 * @param model
	 */
	public void saveUserRole(UserRoleModel model);
	
	/**
	 * 通过用户编号和角色编号删除 用户-权限信息
	 * @param userId
	 * @param roleId
	 */
	public void deleteUserPrivByUserIdAndRoleId(Long userId,Long roleId);

	/**
	 * 删除 用户-角色信息
	 * @param model
	 */
	public void deleteUserRole(UserRoleModel model);
	
	
	/**
	 * 通过 通过用户、角色编号获取用户-权限表中权限信息
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public List<SystemPrivModel> findUserPrivByUserIdAndRoleId(Long userId,Long roleId);
	
	/**
	 * 通过用户编号和角色编号列表获取用户-权限表中权限信息
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	public List<SystemPrivModel> findUserPrivByUserAndRoleIds(Long userId,List<Long> roleIds);
	
	/**
	 * 通过权限编号删除 用户-权限信息
	 * @param privId
	 */
	public void deleteUserPrivByPrivId(Long privId);
	
	/**
	 * 通过权限编号列表  批量删除 用户-权限信息
	 * @param privId
	 */
	public void delBatchUserPrivByPrivIds(List<Long> privIds);
	
	/**
	 * 通过权限编号删除 角色-权限信息
	 * @param privId
	 */
	public void deleteRolePrivByPrivId(Long privId);
	
	/**
	 * 通过角色编号删除 用户-权限信息
	 * @param roleId
	 */
	public void deleteUserPrivByRoleId(Long roleId);
	/**
	 * 通过角色编号 删除 角色-权限信息
	 * @param roleId
	 */
	public void deleteRolePrivByRoleId(Long roleId);
	
	/**
	 * 通过用户编号 删除 用户-权限信息
	 * @param userId
	 */
	public void deleteUserPrivByUserId(Long userId);
	/**
	 * 通过用户编号 删除 用户-角色信息
	 * @param userId
	 */
	public void deleteUserRoleByUserId(Long userId);
	
	/**
	 * 通过用户编号获取去重的用户-权限信息
	 * @param userId
	 * @return
	 */
	public List<SystemPrivModel> findUserPrivDistinctByUserId(Long userId);
}
