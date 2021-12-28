package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [职务信息表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmPositInfoModel implements Serializable {

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
     * 职务
     */
	@Dict(key = "posit")
	private Integer posit;
	/**
     * 职务-名称
     */
	private String positCn;
	/**
     * 任职部门
     */
	private String positDepart;
	/**
     * 行政级别-编号（正处级|副处级等）
     */
	@Dict(key = "positLevel")
	private Integer positLevel;
	/**
     * 行政级别-名称
     */
	private String positLevelCn;
	/**
     * 是否最高职务 0：否  1：是
     */
	@Dict(key = "YN")
	private Integer positMax;
	/**
     * 任职顺序
     */
	private Integer onPositOrder;
	/**
     * 任职开始时间
     */
	private java.util.Date onPositStartTime;
	/**
     * 任职开始时间
     */
	private java.util.Date onPositEndTime;
	/**
     * 任职文号
     */
	private String onPositDocno;
	/**
     * 兼任职务
     */
	@Dict(key = "posit")
	private Integer conPosit;
	/**
     * 兼任职务-名称
     */
	private String conPositCn;
	/**
     * 兼职部门
     */
	private String conPositDepart;
	/**
     * 兼职开始时间
     */
	private java.util.Date conPositStartTime;
	/**
     * 兼职结束时间
     */
	private java.util.Date conPositEndTime;
	/**
     * 待遇级别（正科级|副科级等的编号）
     */
	@Dict(key = "treatLevel")
	private Integer treatLevel;
	/**
     * 待遇级别-名称
     */
	private String treatLevelCn;
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
	* <p>Discription:[职务]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getPosit(){
		return posit;
	}
	/**
	* <p>Discription:[职务]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPosit(Integer posit){
		this.posit = posit;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPositCn(){
		return positCn;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositCn(String positCn){
		this.positCn = positCn;
	}
	/**
	* <p>Discription:[任职部门]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPositDepart(){
		return positDepart;
	}
	/**
	* <p>Discription:[任职部门]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositDepart(String positDepart){
		this.positDepart = positDepart;
	}
	/**
	* <p>Discription:[行政级别-编号（正处级|副处级等）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getPositLevel(){
		return positLevel;
	}
	/**
	* <p>Discription:[行政级别-编号（正处级|副处级等）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositLevel(Integer positLevel){
		this.positLevel = positLevel;
	}
	/**
	* <p>Discription:[行政级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getPositLevelCn(){
		return positLevelCn;
	}
	/**
	* <p>Discription:[行政级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositLevelCn(String positLevelCn){
		this.positLevelCn = positLevelCn;
	}
	/**
	* <p>Discription:[是否最高职务 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getPositMax(){
		return positMax;
	}
	/**
	* <p>Discription:[是否最高职务 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setPositMax(Integer positMax){
		this.positMax = positMax;
	}
	/**
	* <p>Discription:[任职顺序]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getOnPositOrder(){
		return onPositOrder;
	}
	/**
	* <p>Discription:[任职顺序]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnPositOrder(Integer onPositOrder){
		this.onPositOrder = onPositOrder;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnPositStartTime(){
		return onPositStartTime;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnPositStartTime(java.util.Date onPositStartTime){
		this.onPositStartTime = onPositStartTime;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnPositEndTime(){
		return onPositEndTime;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnPositEndTime(java.util.Date onPositEndTime){
		this.onPositEndTime = onPositEndTime;
	}
	/**
	* <p>Discription:[任职文号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getOnPositDocno(){
		return onPositDocno;
	}
	/**
	* <p>Discription:[任职文号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setOnPositDocno(String onPositDocno){
		this.onPositDocno = onPositDocno;
	}
	/**
	* <p>Discription:[兼任职务]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getConPosit(){
		return conPosit;
	}
	/**
	* <p>Discription:[兼任职务]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setConPosit(Integer conPosit){
		this.conPosit = conPosit;
	}
	/**
	* <p>Discription:[兼任职务-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getConPositCn(){
		return conPositCn;
	}
	/**
	* <p>Discription:[兼任职务-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setConPositCn(String conPositCn){
		this.conPositCn = conPositCn;
	}
	/**
	* <p>Discription:[兼职部门]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getConPositDepart(){
		return conPositDepart;
	}
	/**
	* <p>Discription:[兼职部门]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setConPositDepart(String conPositDepart){
		this.conPositDepart = conPositDepart;
	}
	/**
	* <p>Discription:[兼职开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getConPositStartTime(){
		return conPositStartTime;
	}
	/**
	* <p>Discription:[兼职开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setConPositStartTime(java.util.Date conPositStartTime){
		this.conPositStartTime = conPositStartTime;
	}
	/**
	* <p>Discription:[兼职结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getConPositEndTime(){
		return conPositEndTime;
	}
	/**
	* <p>Discription:[兼职结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setConPositEndTime(java.util.Date conPositEndTime){
		this.conPositEndTime = conPositEndTime;
	}
	/**
	* <p>Discription:[待遇级别（正科级|副科级等的编号）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTreatLevel(){
		return treatLevel;
	}
	/**
	* <p>Discription:[待遇级别（正科级|副科级等的编号）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel(Integer treatLevel){
		this.treatLevel = treatLevel;
	}
	/**
	* <p>Discription:[待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTreatLevelCn(){
		return treatLevelCn;
	}
	/**
	* <p>Discription:[待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevelCn(String treatLevelCn){
		this.treatLevelCn = treatLevelCn;
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
