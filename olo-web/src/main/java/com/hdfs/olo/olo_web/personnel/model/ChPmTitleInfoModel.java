package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [职称信息表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmTitleInfoModel implements Serializable {

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
     * 职称
     */
	@Dict(key = "title")
	private Integer title;
	/**
     * 职称-名称
     */
	private String titleCn;
	/**
     * 职称级别(士级|初级|中级|副高级|正高级)
     */
	@Dict(key = "titleLevel")
	private String titleLevel;
	/**
     * 职称级别-名称
     */
	private String titleLevelCn;
	/**
     * 职称序列（职称分类）
     */
	@Dict(key = "titleClass")
	private Integer titleClass;
	/**
     * 职称序列-名称
     */
	private String titleClassCn;
	/**
     * 是否最高职称 0：否  1：是
     */
	@Dict(key = "YN")
	private Integer titleMax;
	/**
     * 职称顺序
     */
	private Integer titleOrder;
	/**
     * 职称证书编号
     */
	private String titleCertNo;
	/**
     * 职称任职文号
     */
	private String titleOnitNo;
	/**
     * 职称初聘文号
     */
	private String titleHireNo;
	/**
     * 职称初聘开始时间
     */
	private java.util.Date titleHStartTime;
	/**
     * 职称初聘结束时间
     */
	private java.util.Date titleHEndTime;
	/**
     * 职称聘任周期
     */
	private Integer titleHCycle;
	/**
     * 其他职称
     */
	@Dict(key = "title")
	private Integer titleOth;
	/**
     * 其他职称-名称
     */
	private String titleOthCn;
	/**
     * 其他职称取得时间
     */
	private java.util.Date titleOthGotTime;
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
	* <p>Discription:[职称]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitle(){
		return title;
	}
	/**
	* <p>Discription:[职称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitle(Integer title){
		this.title = title;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleCn(){
		return titleCn;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleCn(String titleCn){
		this.titleCn = titleCn;
	}
	/**
	* <p>Discription:[职称级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleLevel(){
		return titleLevel;
	}
	/**
	* <p>Discription:[职称级别(士级|初级|中级|副高级|正高级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleLevel(String titleLevel){
		this.titleLevel = titleLevel;
	}
	/**
	* <p>Discription:[职称级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleLevelCn(){
		return titleLevelCn;
	}
	/**
	* <p>Discription:[职称级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleLevelCn(String titleLevelCn){
		this.titleLevelCn = titleLevelCn;
	}
	/**
	* <p>Discription:[职称序列（职称分类）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleClass(){
		return titleClass;
	}
	/**
	* <p>Discription:[职称序列（职称分类）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleClass(Integer titleClass){
		this.titleClass = titleClass;
	}
	/**
	* <p>Discription:[职称序列-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleClassCn(){
		return titleClassCn;
	}
	/**
	* <p>Discription:[职称序列-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleClassCn(String titleClassCn){
		this.titleClassCn = titleClassCn;
	}
	/**
	* <p>Discription:[是否最高职称 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleMax(){
		return titleMax;
	}
	/**
	* <p>Discription:[是否最高职称 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleMax(Integer titleMax){
		this.titleMax = titleMax;
	}
	/**
	* <p>Discription:[职称顺序]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleOrder(){
		return titleOrder;
	}
	/**
	* <p>Discription:[职称顺序]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleOrder(Integer titleOrder){
		this.titleOrder = titleOrder;
	}
	/**
	* <p>Discription:[职称证书编号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleCertNo(){
		return titleCertNo;
	}
	/**
	* <p>Discription:[职称证书编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleCertNo(String titleCertNo){
		this.titleCertNo = titleCertNo;
	}
	/**
	* <p>Discription:[职称任职文号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleOnitNo(){
		return titleOnitNo;
	}
	/**
	* <p>Discription:[职称任职文号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleOnitNo(String titleOnitNo){
		this.titleOnitNo = titleOnitNo;
	}
	/**
	* <p>Discription:[职称初聘文号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleHireNo(){
		return titleHireNo;
	}
	/**
	* <p>Discription:[职称初聘文号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleHireNo(String titleHireNo){
		this.titleHireNo = titleHireNo;
	}
	/**
	* <p>Discription:[职称初聘开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitleHStartTime(){
		return titleHStartTime;
	}
	/**
	* <p>Discription:[职称初聘开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleHStartTime(java.util.Date titleHStartTime){
		this.titleHStartTime = titleHStartTime;
	}
	/**
	* <p>Discription:[职称初聘结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitleHEndTime(){
		return titleHEndTime;
	}
	/**
	* <p>Discription:[职称初聘结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleHEndTime(java.util.Date titleHEndTime){
		this.titleHEndTime = titleHEndTime;
	}
	/**
	* <p>Discription:[职称聘任周期]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleHCycle(){
		return titleHCycle;
	}
	/**
	* <p>Discription:[职称聘任周期]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleHCycle(Integer titleHCycle){
		this.titleHCycle = titleHCycle;
	}
	/**
	* <p>Discription:[其他职称]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitleOth(){
		return titleOth;
	}
	/**
	* <p>Discription:[其他职称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleOth(Integer titleOth){
		this.titleOth = titleOth;
	}
	/**
	* <p>Discription:[其他职称-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTitleOthCn(){
		return titleOthCn;
	}
	/**
	* <p>Discription:[其他职称-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleOthCn(String titleOthCn){
		this.titleOthCn = titleOthCn;
	}
	/**
	* <p>Discription:[其他职称取得时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitleOthGotTime(){
		return titleOthGotTime;
	}
	/**
	* <p>Discription:[其他职称取得时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTitleOthGotTime(java.util.Date titleOthGotTime){
		this.titleOthGotTime = titleOthGotTime;
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
