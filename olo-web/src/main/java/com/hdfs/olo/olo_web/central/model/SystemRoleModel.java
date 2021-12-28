package com.hdfs.olo.olo_web.central.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 
 * <p>Description: [系统角色表Model]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public class SystemRoleModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long roleId;
	/**
     * 
     */
	private String roleName;
	/**
     * 
     */
	private String roleLevel;
	/**
     * 
     */
	private String status;
	/**
     * 
     */
	private String remark;
	/**
     * 
     */
	private java.util.Date createTime;
	/**
     * 
     */
	private java.util.Date updateTime;
	
	private SystemPrivModel priv = new SystemPrivModel();
	private List<SystemPrivModel> menuList = new ArrayList<SystemPrivModel>();
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getRoleId(){
		return roleId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getRoleName(){
		return roleName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setRoleName(String roleName){
		this.roleName = roleName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getRoleLevel(){
		return roleLevel;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setRoleLevel(String roleLevel){
		this.roleLevel = roleLevel;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getStatus(){
		return status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setStatus(String status){
		this.status = status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	@Transient
	public SystemPrivModel getPriv()
	{
		return priv;
	}
	public void setPriv(SystemPrivModel priv)
	{
		this.priv = priv;
	}
	@Transient
	public List<SystemPrivModel> getMenuList()
	{
		return menuList;
	}
	public void setMenuList(List<SystemPrivModel> menuList)
	{
		this.menuList = menuList;
	}
}
