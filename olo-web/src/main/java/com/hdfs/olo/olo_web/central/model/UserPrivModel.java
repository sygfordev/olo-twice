package com.hdfs.olo.olo_web.central.model;

/**
 * 
 * @author huadf
 *
 */
public class UserPrivModel implements java.io.Serializable {
	private static final long serialVersionUID = 5541530169140781082L;
	
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 角色代码
	 */
	private Long roleId;

	/**
	 * 权限代码
	 */
	private Long privId;
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
	public Long getPrivId() {
		return this.privId;
	}
	public void setPrivId(Long privId) {
		this.privId = privId;
	}
}
