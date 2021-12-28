package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [合同信息model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmContractInfoModel implements Serializable {

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
     * 合同类别  固定期限|无固定期限
     */
	@Dict(key = "contClass")
	private Integer contClass;
	/**
     * 合同类别-名称
     */
	private String contClassCn;
	/**
     * 合同期限（合同周期）  无固定期限|1年|2年等
     */
	@Dict(key = "contCycle")
	private Integer contCycle;
	/**
     * 合同期限-名称
     */
	private String contCycleCn;
	/**
     * 合同编号
     */
	private String contNo;
	/**
     * 合同开始时间
     */
	private java.util.Date contStartTime;
	/**
     * 合同结束时间
     */
	private java.util.Date contEndTime;
	/**
     * 合同到期预警时间
     */
	private java.util.Date contExpireWarnTime;
	/**
     * 合同顺序
     */
	private Integer contOrder;
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
	* Created on 2021年04月10日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年04月10日
	* @return Long
    * @author:huadf
    */
	public Long getWorkerId(){
		return workerId;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWorkerId(Long workerId){
		this.workerId = workerId;
	}
	/**
	* <p>Discription:[合同类别  固定期限|无固定期限]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getContClass(){
		return contClass;
	}
	/**
	* <p>Discription:[合同类别  固定期限|无固定期限]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContClass(Integer contClass){
		this.contClass = contClass;
	}
	/**
	* <p>Discription:[合同类别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getContClassCn(){
		return contClassCn;
	}
	/**
	* <p>Discription:[合同类别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContClassCn(String contClassCn){
		this.contClassCn = contClassCn;
	}
	/**
	* <p>Discription:[合同期限（合同周期）  无固定期限|1年|2年等]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getContCycle(){
		return contCycle;
	}
	/**
	* <p>Discription:[合同期限（合同周期）  无固定期限|1年|2年等]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContCycle(Integer contCycle){
		this.contCycle = contCycle;
	}
	/**
	* <p>Discription:[合同期限-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getContCycleCn(){
		return contCycleCn;
	}
	/**
	* <p>Discription:[合同期限-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContCycleCn(String contCycleCn){
		this.contCycleCn = contCycleCn;
	}
	/**
	* <p>Discription:[合同编号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getContNo(){
		return contNo;
	}
	/**
	* <p>Discription:[合同编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContNo(String contNo){
		this.contNo = contNo;
	}
	/**
	* <p>Discription:[合同开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getContStartTime(){
		return contStartTime;
	}
	/**
	* <p>Discription:[合同开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContStartTime(java.util.Date contStartTime){
		this.contStartTime = contStartTime;
	}
	/**
	* <p>Discription:[合同结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getContEndTime(){
		return contEndTime;
	}
	/**
	* <p>Discription:[合同结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContEndTime(java.util.Date contEndTime){
		this.contEndTime = contEndTime;
	}
	/**
	* <p>Discription:[合同到期预警时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getContExpireWarnTime(){
		return contExpireWarnTime;
	}
	/**
	* <p>Discription:[合同到期预警时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContExpireWarnTime(java.util.Date contExpireWarnTime){
		this.contExpireWarnTime = contExpireWarnTime;
	}
	/**
	* <p>Discription:[合同顺序]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getContOrder(){
		return contOrder;
	}
	/**
	* <p>Discription:[合同顺序]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setContOrder(Integer contOrder){
		this.contOrder = contOrder;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年04月10日
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
