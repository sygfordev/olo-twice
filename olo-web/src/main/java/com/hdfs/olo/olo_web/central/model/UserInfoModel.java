package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [用户信息表Model]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public class UserInfoModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long userId;
	/**
     * 
     */
	private String account;
	/**
     * 
     */
	private String userPassword;
	/**
     * 
     */
	private String userRealName;
	/**
     * 
     */
	private String userSex;
	/**
     * 
     */
	private BranchOfficeModel branch;
	/**
     * 
     */
	private String userJob;
	/**
     * 
     */
	private String userTelPhone;
	/**
     * 
     */
	private String userFax;
	/**
     * 
     */
	private String userMobile;
	/**
     * 
     */
	private String userAddress;
	/**
     * 
     */
	private String userZip;
	/**
     * 
     */
	private String userHomeAddress;
	/**
     * 
     */
	private String userHomeZip;
	/**
     * 
     */
	private String userEmail;
	/**
     * 
     */
	private String status;
	/**
     * 
     */
	private java.util.Date userExpireDate;
	/**
     * 
     */
	private java.util.Date createTime;
	/**
     * 
     */
	private java.util.Date updateTime;
	/**
     * 
     */
	private String remark;
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getUserId(){
		return userId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getAccount(){
		return account;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setAccount(String account){
		this.account = account;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserPassword(){
		return userPassword;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserRealName(){
		return userRealName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserRealName(String userRealName){
		this.userRealName = userRealName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserSex(){
		return userSex;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserSex(String userSex){
		this.userSex = userSex;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public BranchOfficeModel getBranch(){
		return branch;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setBranch(BranchOfficeModel branch){
		this.branch = branch;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserJob(){
		return userJob;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserJob(String userJob){
		this.userJob = userJob;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserTelPhone(){
		return userTelPhone;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserTelPhone(String userTelPhone){
		this.userTelPhone = userTelPhone;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserFax(){
		return userFax;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserFax(String userFax){
		this.userFax = userFax;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserMobile(){
		return userMobile;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserMobile(String userMobile){
		this.userMobile = userMobile;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserAddress(){
		return userAddress;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserAddress(String userAddress){
		this.userAddress = userAddress;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserZip(){
		return userZip;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserZip(String userZip){
		this.userZip = userZip;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserHomeAddress(){
		return userHomeAddress;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserHomeAddress(String userHomeAddress){
		this.userHomeAddress = userHomeAddress;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserHomeZip(){
		return userHomeZip;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserHomeZip(String userHomeZip){
		this.userHomeZip = userHomeZip;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getUserEmail(){
		return userEmail;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
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
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUserExpireDate(){
		return userExpireDate;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUserExpireDate(java.util.Date userExpireDate){
		this.userExpireDate = userExpireDate;
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
}
