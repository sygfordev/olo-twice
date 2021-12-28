package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [增减情况model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmIncdecInfoModel implements Serializable {

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
	 * 职工身份证号-用于导入时使用
	 */
	@MustExist
	private String cardNo;
	/**
     * 进入时间
     */
	private java.util.Date entryTime;
	/**
     * 进入渠道
     */
	private Integer entryChan;
	/**
     * 离职时间
     */
	private java.util.Date quitTime;
	/**
     * 离职原因 退休|病退|辞职|辞退|工作调动等
     */
	private Integer quitReason;
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
	* <p>Discription:[进入时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getEntryTime(){
		return entryTime;
	}
	/**
	* <p>Discription:[进入时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setEntryTime(java.util.Date entryTime){
		this.entryTime = entryTime;
	}
	/**
	* <p>Discription:[进入渠道]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getEntryChan(){
		return entryChan;
	}
	/**
	* <p>Discription:[进入渠道]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setEntryChan(Integer entryChan){
		this.entryChan = entryChan;
	}
	/**
	* <p>Discription:[离职时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getQuitTime(){
		return quitTime;
	}
	/**
	* <p>Discription:[离职时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setQuitTime(java.util.Date quitTime){
		this.quitTime = quitTime;
	}
	/**
	* <p>Discription:[离职原因 退休|病退|辞职|辞退|工作调动等]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getQuitReason(){
		return quitReason;
	}
	/**
	* <p>Discription:[离职原因 退休|病退|辞职|辞退|工作调动等]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setQuitReason(Integer quitReason){
		this.quitReason = quitReason;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
