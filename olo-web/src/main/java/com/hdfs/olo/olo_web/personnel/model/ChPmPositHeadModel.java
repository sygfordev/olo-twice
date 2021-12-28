package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [职务信息头表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmPositHeadModel implements Serializable {

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
     * 现任职务
     */
	@Dict(key = "posit")
	private Integer posit4now;
	/**
     * 现任职务-名称
     */
	private String posit4nowCn;
	/**
     * 现任职部门
     */
	private String positDepart4now;
	/**
     * 现级别（正科|副科等的编号）
     */
	@Dict(key = "positLevel")
	private Integer positLevel4now;
	/**
     * 现级别-名称
     */
	private String positLevel4nowCn;
	/**
     * 现任职务开始时间
     */
	private java.util.Date posit4nowStartTime;
	/**
     * 现任职务结束时间
     */
	private java.util.Date posit4nowEndTime;
	/**
     * 现待遇级别（正科级|副科级等的编号）
     */
	@Dict(key = "treatLevel")
	private Integer treatLevel4now;
	/**
     * 现待遇级别-名称
     */
	private String treatLevel4nowCn;
	/**
     * 现待遇级别开始时间
     */
	private java.util.Date treat4nowStartTime;
	/**
     * 现待遇级别结束时间
     */
	private java.util.Date treat4nowEndTime;
	/**
     * 现待遇级别年限
     */
	private Integer treat4nowYears;
	/**
     * 任正处开始时间
     */
	private java.util.Date onChuStartTime;
	/**
     * 任正处结束时间
     */
	private java.util.Date onChuEndTime;
	/**
     * 任副处开始时间
     */
	private java.util.Date onChuDetupyStartTime;
	/**
     * 任副处结束时间
     */
	private java.util.Date onChuDetupyEndTime;
	/**
     * 任正科开始时间
     */
	private java.util.Date onKeStartTime;
	/**
     * 任正科结束时间
     */
	private java.util.Date onKeEndTime;
	/**
     * 任副科开始时间
     */
	private java.util.Date onKeDetupyStartTime;
	/**
     * 任副科结束时间
     */
	private java.util.Date onKeDetupyEndTime;
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
	* <p>Discription:[职工身份证号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[职工身份证号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[现任职务]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getPosit4now(){
		return posit4now;
	}
	/**
	* <p>Discription:[现任职务]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPosit4now(Integer posit4now){
		this.posit4now = posit4now;
	}
	/**
	* <p>Discription:[现任职务-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPosit4nowCn(){
		return posit4nowCn;
	}
	/**
	* <p>Discription:[现任职务-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPosit4nowCn(String posit4nowCn){
		this.posit4nowCn = posit4nowCn;
	}
	/**
	* <p>Discription:[现任职部门]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPositDepart4now(){
		return positDepart4now;
	}
	/**
	* <p>Discription:[现任职部门]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositDepart4now(String positDepart4now){
		this.positDepart4now = positDepart4now;
	}
	/**
	* <p>Discription:[现级别（正科|副科等的编号）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getPositLevel4now(){
		return positLevel4now;
	}
	/**
	* <p>Discription:[现级别（正科|副科等的编号）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositLevel4now(Integer positLevel4now){
		this.positLevel4now = positLevel4now;
	}
	/**
	* <p>Discription:[现级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPositLevel4nowCn(){
		return positLevel4nowCn;
	}
	/**
	* <p>Discription:[现级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositLevel4nowCn(String positLevel4nowCn){
		this.positLevel4nowCn = positLevel4nowCn;
	}
	/**
	* <p>Discription:[现任职务开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getPosit4nowStartTime(){
		return posit4nowStartTime;
	}
	/**
	* <p>Discription:[现任职务开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPosit4nowStartTime(java.util.Date posit4nowStartTime){
		this.posit4nowStartTime = posit4nowStartTime;
	}
	/**
	* <p>Discription:[现任职务结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getPosit4nowEndTime(){
		return posit4nowEndTime;
	}
	/**
	* <p>Discription:[现任职务结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPosit4nowEndTime(java.util.Date posit4nowEndTime){
		this.posit4nowEndTime = posit4nowEndTime;
	}
	/**
	* <p>Discription:[现待遇级别（正科级|副科级等的编号）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTreatLevel4now(){
		return treatLevel4now;
	}
	/**
	* <p>Discription:[现待遇级别（正科级|副科级等的编号）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4now(Integer treatLevel4now){
		this.treatLevel4now = treatLevel4now;
	}
	/**
	* <p>Discription:[现待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTreatLevel4nowCn(){
		return treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4nowCn(String treatLevel4nowCn){
		this.treatLevel4nowCn = treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现待遇级别开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTreat4nowStartTime(){
		return treat4nowStartTime;
	}
	/**
	* <p>Discription:[现待遇级别开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreat4nowStartTime(java.util.Date treat4nowStartTime){
		this.treat4nowStartTime = treat4nowStartTime;
	}
	/**
	* <p>Discription:[现待遇级别结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTreat4nowEndTime(){
		return treat4nowEndTime;
	}
	/**
	* <p>Discription:[现待遇级别结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreat4nowEndTime(java.util.Date treat4nowEndTime){
		this.treat4nowEndTime = treat4nowEndTime;
	}
	/**
	* <p>Discription:[现待遇级别年限]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTreat4nowYears(){
		return treat4nowYears;
	}
	/**
	* <p>Discription:[现待遇级别年限]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreat4nowYears(Integer treat4nowYears){
		this.treat4nowYears = treat4nowYears;
	}
	/**
	* <p>Discription:[任正处开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnChuStartTime(){
		return onChuStartTime;
	}
	/**
	* <p>Discription:[任正处开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnChuStartTime(java.util.Date onChuStartTime){
		this.onChuStartTime = onChuStartTime;
	}
	/**
	* <p>Discription:[任正处结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnChuEndTime(){
		return onChuEndTime;
	}
	/**
	* <p>Discription:[任正处结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnChuEndTime(java.util.Date onChuEndTime){
		this.onChuEndTime = onChuEndTime;
	}
	/**
	* <p>Discription:[任副处开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnChuDetupyStartTime(){
		return onChuDetupyStartTime;
	}
	/**
	* <p>Discription:[任副处开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnChuDetupyStartTime(java.util.Date onChuDetupyStartTime){
		this.onChuDetupyStartTime = onChuDetupyStartTime;
	}
	/**
	* <p>Discription:[任副处结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnChuDetupyEndTime(){
		return onChuDetupyEndTime;
	}
	/**
	* <p>Discription:[任副处结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnChuDetupyEndTime(java.util.Date onChuDetupyEndTime){
		this.onChuDetupyEndTime = onChuDetupyEndTime;
	}
	/**
	* <p>Discription:[任正科开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnKeStartTime(){
		return onKeStartTime;
	}
	/**
	* <p>Discription:[任正科开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnKeStartTime(java.util.Date onKeStartTime){
		this.onKeStartTime = onKeStartTime;
	}
	/**
	* <p>Discription:[任正科结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnKeEndTime(){
		return onKeEndTime;
	}
	/**
	* <p>Discription:[任正科结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnKeEndTime(java.util.Date onKeEndTime){
		this.onKeEndTime = onKeEndTime;
	}
	/**
	* <p>Discription:[任副科开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnKeDetupyStartTime(){
		return onKeDetupyStartTime;
	}
	/**
	* <p>Discription:[任副科开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnKeDetupyStartTime(java.util.Date onKeDetupyStartTime){
		this.onKeDetupyStartTime = onKeDetupyStartTime;
	}
	/**
	* <p>Discription:[任副科结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnKeDetupyEndTime(){
		return onKeDetupyEndTime;
	}
	/**
	* <p>Discription:[任副科结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnKeDetupyEndTime(java.util.Date onKeDetupyEndTime){
		this.onKeDetupyEndTime = onKeDetupyEndTime;
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
}

