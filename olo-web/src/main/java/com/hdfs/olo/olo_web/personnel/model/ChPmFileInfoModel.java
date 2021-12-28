package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
/** 
 * <p>Description: [文件信息model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmFileInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 职工编号
     */
	private Long workerId;
	/**
     * 模块编码
     */
	private Integer moduleCode;
	/**
     * 模块名称
     */
	private String moduleName;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
     * 文件内容
     */
	private byte[] fileContent;
	/**
	 * 文件回显路径
	 */
	private String fileUrl;
	/**
     * 文件类型（文件后缀）
     */
	private String fileType;
	/**
     * 文件大小
     */
	private Long fileSize;
	/**
     * 状态 0：正常  1：异常
     */
	private Integer status;
	/**
     * 备注
     */
	private String remark;
	/**
     * 创建时间
     */
	private java.util.Date createTime;
	/**
     * 更新时间
     */
	private java.util.Date updateTime;
	
	// setter and getter
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年03月29日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年03月29日
	* @return Long
    * @author:huadf
    */
	public Long getWorkerId(){
		return workerId;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWorkerId(Long workerId){
		this.workerId = workerId;
	}
	/**
	* <p>Discription:[模块编码]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getModuleCode(){
		return moduleCode;
	}
	/**
	* <p>Discription:[模块编码]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setModuleCode(Integer moduleCode){
		this.moduleCode = moduleCode;
	}
	/**
	* <p>Discription:[模块名称]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getModuleName(){
		return moduleName;
	}
	/**
	* <p>Discription:[模块名称]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setModuleName(String moduleName){
		this.moduleName = moduleName;
	}
	/**
	* <p>Discription:[文件内容]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getFileName(){
		return fileName;
	}
	/**
	* <p>Discription:[文件内容]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	/**
	* <p>Discription:[文件内容]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public byte[] getFileContent(){
		return fileContent;
	}
	/**
	* <p>Discription:[文件内容]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setFileContent(byte[] fileContent){
		this.fileContent = fileContent;
	}
	/**
	* <p>Discription:[文件类型（文件后缀）]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getFileUrl(){
		return fileUrl;
	}
	/**
	* <p>Discription:[文件类型（文件后缀）]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setFileUrl(String fileUrl){
		this.fileUrl = fileUrl;
	}
	/**
	* <p>Discription:[文件类型（文件后缀）]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getFileType(){
		return fileType;
	}
	/**
	* <p>Discription:[文件类型（文件后缀）]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setFileType(String fileType){
		this.fileType = fileType;
	}
	/**
	* <p>Discription:[文件大小]</p>
	* Created on 2021年03月29日
	* @return Long
    * @author:huadf
    */
	public Long getFileSize(){
		return fileSize;
	}
	/**
	* <p>Discription:[文件大小]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setFileSize(Long fileSize){
		this.fileSize = fileSize;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
