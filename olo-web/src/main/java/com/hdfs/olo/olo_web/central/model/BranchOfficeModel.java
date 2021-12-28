package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [组织机构表Model]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public class BranchOfficeModel  implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long id;
	/**
     * 
     */
	private String branchName;
	/**
     * 
     */
	private String cityNo;
	/**
     * 
     */
	private Long parentId;
	/**
     * 
     */
	private String contact;
	/**
     * 
     */
	private String phone;
	/**
     * 
     */
	private String fax;
	/**
     * 
     */
	private String email;
	/**
     * 
     */
	private String address;
	/**
     * 
     */
	private String zip;
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
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getBranchName(){
		return branchName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setBranchName(String branchName){
		this.branchName = branchName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getCityNo(){
		return cityNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setCityNo(String cityNo){
		this.cityNo = cityNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getParentId(){
		return parentId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setParentId(Long parentId){
		this.parentId = parentId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getContact(){
		return contact;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setContact(String contact){
		this.contact = contact;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPhone(){
		return phone;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPhone(String phone){
		this.phone = phone;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getFax(){
		return fax;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setFax(String fax){
		this.fax = fax;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getEmail(){
		return email;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setEmail(String email){
		this.email = email;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getAddress(){
		return address;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setAddress(String address){
		this.address = address;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getZip(){
		return zip;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setZip(String zip){
		this.zip = zip;
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
}
