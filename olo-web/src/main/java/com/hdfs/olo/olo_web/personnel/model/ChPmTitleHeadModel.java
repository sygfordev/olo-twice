package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [职称信息头表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmTitleHeadModel implements Serializable {

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
     * 现职称
     */
	@Dict(key = "title")
	private Integer title4now;
	/**
     * 现职称-名称
     */
	private String title4nowCn;
	/**
     * 现职称取得时间
     */
	private java.util.Date title4nowGotTime;
	/**
     * 现职称取得年限
     */
	private Integer title4nowGotYears;
	/**
     * 现职称序列（职称分类）
     */
	@Dict(key = "titleClass")
	private Integer titleClass4now;
	/**
     * 现职称序列-名称
     */
	private String titleClass4nowCn;
	/**
     * 现职称级别(士级|初级|中级|副高级|正高级)
     */
	@Dict(key = "titleLevel")
	private String titleLevel4now;
	/**
     * 现职称级别-名称
     */
	private String titleLevel4nowCn;
	/**
     * 现职称待遇级别(士级|初级|中级|副高级|正高级)
     */
	@Dict(key = "titleLevel")
	private String treatLevel4now;
	/**
     * 现职称待遇级别-名称
     */
	private String treatLevel4nowCn;
	/**
     * 现职称初聘开始时间
     */
	private java.util.Date title4nowHStartTime;
	/**
     * 现职称聘任终止时间
     */
	private java.util.Date title4nowHEndTime;
	/**
     * 是否现最高职称  0：否  1：是
     */
	@Dict(key = "YN")
	private Integer titleMax4now;
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
	* <p>Discription:[现职称]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitle4now(){
		return title4now;
	}
	/**
	* <p>Discription:[现职称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4now(Integer title4now){
		this.title4now = title4now;
	}
	/**
	* <p>Discription:[现职称-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitle4nowCn(){
		return title4nowCn;
	}
	/**
	* <p>Discription:[现职称-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4nowCn(String title4nowCn){
		this.title4nowCn = title4nowCn;
	}
	/**
	* <p>Discription:[现职称取得时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitle4nowGotTime(){
		return title4nowGotTime;
	}
	/**
	* <p>Discription:[现职称取得时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4nowGotTime(java.util.Date title4nowGotTime){
		this.title4nowGotTime = title4nowGotTime;
	}
	/**
	* <p>Discription:[现职称取得年限]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitle4nowGotYears(){
		return title4nowGotYears;
	}
	/**
	* <p>Discription:[现职称取得年限]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4nowGotYears(Integer title4nowGotYears){
		this.title4nowGotYears = title4nowGotYears;
	}
	/**
	* <p>Discription:[现职称序列（职称分类）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleClass4now(){
		return titleClass4now;
	}
	/**
	* <p>Discription:[现职称序列（职称分类）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleClass4now(Integer titleClass4now){
		this.titleClass4now = titleClass4now;
	}
	/**
	* <p>Discription:[现职称序列-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleClass4nowCn(){
		return titleClass4nowCn;
	}
	/**
	* <p>Discription:[现职称序列-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleClass4nowCn(String titleClass4nowCn){
		this.titleClass4nowCn = titleClass4nowCn;
	}
	/**
	* <p>Discription:[现职称级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleLevel4now(){
		return titleLevel4now;
	}
	/**
	* <p>Discription:[现职称级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleLevel4now(String titleLevel4now){
		this.titleLevel4now = titleLevel4now;
	}
	/**
	* <p>Discription:[现职称级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleLevel4nowCn(){
		return titleLevel4nowCn;
	}
	/**
	* <p>Discription:[现职称级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleLevel4nowCn(String titleLevel4nowCn){
		this.titleLevel4nowCn = titleLevel4nowCn;
	}
	/**
	* <p>Discription:[现职称待遇级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTreatLevel4now(){
		return treatLevel4now;
	}
	/**
	* <p>Discription:[现职称待遇级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4now(String treatLevel4now){
		this.treatLevel4now = treatLevel4now;
	}
	/**
	* <p>Discription:[现职称待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTreatLevel4nowCn(){
		return treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现职称待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4nowCn(String treatLevel4nowCn){
		this.treatLevel4nowCn = treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现职称初聘开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitle4nowHStartTime(){
		return title4nowHStartTime;
	}
	/**
	* <p>Discription:[现职称初聘开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4nowHStartTime(java.util.Date title4nowHStartTime){
		this.title4nowHStartTime = title4nowHStartTime;
	}
	/**
	* <p>Discription:[现职称聘任终止时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitle4nowHEndTime(){
		return title4nowHEndTime;
	}
	/**
	* <p>Discription:[现职称聘任终止时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle4nowHEndTime(java.util.Date title4nowHEndTime){
		this.title4nowHEndTime = title4nowHEndTime;
	}
	/**
	* <p>Discription:[是否现最高职称  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleMax4now(){
		return titleMax4now;
	}
	/**
	* <p>Discription:[是否现最高职称  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleMax4now(Integer titleMax4now){
		this.titleMax4now = titleMax4now;
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
