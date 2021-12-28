package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [操作日志Model]</p>
 * Created on 2020年08月24日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public class ComTabLogModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long logId;
	/**
     * 
     */
	private Long branchId;
	/**
     * 
     */
	private String branchName;
	/**
     * 
     */
	private Long userId;
	/**
     * 
     */
	private String userName;
	/**
     * 
     */
	private String logModuleId;
	/**
     * 
     */
	private String logModuleName;
	/**
     * 
     */
	private String logIp;
	/**
     * 
     */
	private String logMac;
	/**
     * 
     */
	private Integer logLevel;
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
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return Long
    * @author:huadf
    */
	public Long getLogId(){
		return logId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogId(Long logId){
		this.logId = logId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return Long
    * @author:huadf
    */
	public Long getBranchId(){
		return branchId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setBranchId(Long branchId){
		this.branchId = branchId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getBranchName(){
		return branchName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setBranchName(String branchName){
		this.branchName = branchName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return Long
    * @author:huadf
    */
	public Long getUserId(){
		return userId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getUserName(){
		return userName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setUserName(String userName){
		this.userName = userName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getLogModuleId(){
		return logModuleId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogModuleId(String logModuleId){
		this.logModuleId = logModuleId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getLogModuleName(){
		return logModuleName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogModuleName(String logModuleName){
		this.logModuleName = logModuleName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getLogIp(){
		return logIp;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogIp(String logIp){
		this.logIp = logIp;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getLogMac(){
		return logMac;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogMac(String logMac){
		this.logMac = logMac;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public Integer getLogLevel(){
		return logLevel;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setLogLevel(Integer logLevel){
		this.logLevel = logLevel;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getStatus(){
		return status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setStatus(String status){
		this.status = status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年08月24日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
