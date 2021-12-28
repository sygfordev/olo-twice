package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
/** 
 * <p>Description: [薪资-工资条导入记录model]</p>
 * Created on 2021年05月16日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaPayslipImprecordModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 批次号
     */
	private String batchNo;
	/**
     * 批次类型名称
     */
	private String batchType;
	
	private String batchUserAccount;
	/**
     * 操作人
     */
	private String batchUser;
	/**
     * 成功数量
     */
	private Long sucNum;
	/**
     * 失败数量
     */
	private Long faiNum;
	/**
     * 异常数量
     */
	private Long exeNum;
	/**
     * 已存在数量
     */
	private Long existNum;
	/**
     * 批次消息
     */
	private String batchMsg;
	/**
     * 状态 0：校验失败  1：导入成功
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
	* Created on 2021年05月16日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[批次号]</p>
	* Created on 2021年05月16日
	* @return String
    * @author:huadf
    */
	public String getBatchNo(){
		return batchNo;
	}
	/**
	* <p>Discription:[批次号]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}
	/**
	* <p>Discription:[批次类型名称]</p>
	* Created on 2021年05月16日
	* @return String
    * @author:huadf
    */
	public String getBatchType(){
		return batchType;
	}
	/**
	* <p>Discription:[批次类型名称]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setBatchType(String batchType){
		this.batchType = batchType;
	}
	/**
	* <p>Discription:[操作人]</p>
	* Created on 2021年05月16日
	* @return String
    * @author:huadf
    */
	public String getBatchUser(){
		return batchUser;
	}
	/**
	* <p>Discription:[操作人]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setBatchUser(String batchUser){
		this.batchUser = batchUser;
	}
	/**
	* <p>Discription:[成功数量]</p>
	* Created on 2021年05月16日
	* @return Long
    * @author:huadf
    */
	public Long getSucNum(){
		return sucNum;
	}
	/**
	* <p>Discription:[成功数量]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setSucNum(Long sucNum){
		this.sucNum = sucNum;
	}
	/**
	* <p>Discription:[失败数量]</p>
	* Created on 2021年05月16日
	* @return Long
    * @author:huadf
    */
	public Long getFaiNum(){
		return faiNum;
	}
	/**
	* <p>Discription:[失败数量]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setFaiNum(Long faiNum){
		this.faiNum = faiNum;
	}
	/**
	* <p>Discription:[异常数量]</p>
	* Created on 2021年05月16日
	* @return Long
    * @author:huadf
    */
	public Long getExeNum(){
		return exeNum;
	}
	/**
	* <p>Discription:[异常数量]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setExeNum(Long exeNum){
		this.exeNum = exeNum;
	}
	/**
	* <p>Discription:[已存在数量]</p>
	* Created on 2021年05月16日
	* @return Long
    * @author:huadf
    */
	public Long getExistNum(){
		return existNum;
	}
	/**
	* <p>Discription:[已存在数量]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setExistNum(Long existNum){
		this.existNum = existNum;
	}
	/**
	* <p>Discription:[批次消息]</p>
	* Created on 2021年05月16日
	* @return String
    * @author:huadf
    */
	public String getBatchMsg(){
		return batchMsg;
	}
	/**
	* <p>Discription:[批次消息]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setBatchMsg(String batchMsg){
		this.batchMsg = batchMsg;
	}
	/**
	* <p>Discription:[状态 0：校验失败  1：导入成功]</p>
	* Created on 2021年05月16日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：校验失败  1：导入成功]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月16日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月16日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月16日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月16日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public String getBatchUserAccount() {
		return batchUserAccount;
	}
	public void setBatchUserAccount(String batchUserAccount) {
		this.batchUserAccount = batchUserAccount;
	}
}
