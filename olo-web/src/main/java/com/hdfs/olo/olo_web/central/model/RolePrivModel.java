package com.hdfs.olo.olo_web.central.model;

/**
 * 
 * @author huadf
 *
 */
public class RolePrivModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -500702178194219781L;

	/**
	 * 
	 */
	public RolePrivModel() {
	}
	
	
	/**
	 * 权限id
	 */
	private Long privId;
	/**
	 * 角色代码
	 */
	private Long roleId;
	public Long getPrivId() {
		return this.privId;
	}
	public void setPrivId(Long privId) {
		this.privId = privId;
	}
	public Long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
