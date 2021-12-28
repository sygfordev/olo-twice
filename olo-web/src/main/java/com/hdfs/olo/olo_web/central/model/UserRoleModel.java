package com.hdfs.olo.olo_web.central.model;
/**
 * 
 * @author huadf
 *
 */
public class UserRoleModel implements java.io.Serializable {
	private static final long serialVersionUID = -424425330981992549L;
	
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 角色代码
	 */
	private Long roleId;
	
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
