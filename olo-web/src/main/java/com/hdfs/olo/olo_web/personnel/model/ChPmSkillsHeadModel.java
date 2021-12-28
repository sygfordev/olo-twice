package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [技能等级(技术工种)信息头表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmSkillsHeadModel implements Serializable {

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
     * 现技能等级
     */
	@Dict(key = "skills")
	private Integer skills4now;
	/**
     * 现技能等级-名称
     */
	private String skills4nowCn;
	/**
     * 现技能等级取得时间
     */
	private java.util.Date skills4nowGotTime;
	/**
     * 现技能等级序列（技能分类）
     */
	@Dict(key = "skillsClass")
	private Integer skillsClass4now;
	/**
     * 现技能等级序列-名称
     */
	private String skillsClass4nowCn;
	/**
     * 现技能等级级别(五级|四级|三级|二级|一级)
     */
	@Dict(key = "skillsLevel")
	private Integer skillsLevel4now;
	/**
     * 现技能等级级别-名称
     */
	private String skillsLevel4nowCn;
	/**
     * 现技能等级待遇级别(五级|四级|三级|二级|一级)
     */
	@Dict(key = "skillsLevel")
	private Integer treatLevel4now;
	/**
     * 现技能等级待遇级别-名称
     */
	private String treatLevel4nowCn;
	/**
     * 现技能初聘开始时间
     */
	private java.util.Date skills4nowHStartTime;
	/**
     * 现技能聘任终止时间
     */
	private java.util.Date skills4nowHEndTime;
	/**
     * 是否现最高技能等级  0：否  1：是
     */
	@Dict(key = "YN")
	private Integer skillsMax4now;
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
	* <p>Discription:[现技能等级]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkills4now(){
		return skills4now;
	}
	/**
	* <p>Discription:[现技能等级]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills4now(Integer skills4now){
		this.skills4now = skills4now;
	}
	/**
	* <p>Discription:[现技能等级-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkills4nowCn(){
		return skills4nowCn;
	}
	/**
	* <p>Discription:[现技能等级-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills4nowCn(String skills4nowCn){
		this.skills4nowCn = skills4nowCn;
	}
	/**
	* <p>Discription:[现技能等级取得时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkills4nowGotTime(){
		return skills4nowGotTime;
	}
	/**
	* <p>Discription:[现技能等级取得时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills4nowGotTime(java.util.Date skills4nowGotTime){
		this.skills4nowGotTime = skills4nowGotTime;
	}
	/**
	* <p>Discription:[现技能等级序列（技能分类）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsClass4now(){
		return skillsClass4now;
	}
	/**
	* <p>Discription:[现技能等级序列（技能分类）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsClass4now(Integer skillsClass4now){
		this.skillsClass4now = skillsClass4now;
	}
	/**
	* <p>Discription:[现技能等级序列-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsClass4nowCn(){
		return skillsClass4nowCn;
	}
	/**
	* <p>Discription:[现技能等级序列-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsClass4nowCn(String skillsClass4nowCn){
		this.skillsClass4nowCn = skillsClass4nowCn;
	}
	/**
	* <p>Discription:[现技能等级级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsLevel4now(){
		return skillsLevel4now;
	}
	/**
	* <p>Discription:[现技能等级级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsLevel4now(Integer skillsLevel4now){
		this.skillsLevel4now = skillsLevel4now;
	}
	/**
	* <p>Discription:[现技能等级级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsLevel4nowCn(){
		return skillsLevel4nowCn;
	}
	/**
	* <p>Discription:[现技能等级级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsLevel4nowCn(String skillsLevel4nowCn){
		this.skillsLevel4nowCn = skillsLevel4nowCn;
	}
	/**
	* <p>Discription:[现技能等级待遇级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getTreatLevel4now(){
		return treatLevel4now;
	}
	/**
	* <p>Discription:[现技能等级待遇级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4now(Integer treatLevel4now){
		this.treatLevel4now = treatLevel4now;
	}
	/**
	* <p>Discription:[现技能等级待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTreatLevel4nowCn(){
		return treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现技能等级待遇级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTreatLevel4nowCn(String treatLevel4nowCn){
		this.treatLevel4nowCn = treatLevel4nowCn;
	}
	/**
	* <p>Discription:[现技能初聘开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkills4nowHStartTime(){
		return skills4nowHStartTime;
	}
	/**
	* <p>Discription:[现技能初聘开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills4nowHStartTime(java.util.Date skills4nowHStartTime){
		this.skills4nowHStartTime = skills4nowHStartTime;
	}
	/**
	* <p>Discription:[现技能聘任终止时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkills4nowHEndTime(){
		return skills4nowHEndTime;
	}
	/**
	* <p>Discription:[现技能聘任终止时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills4nowHEndTime(java.util.Date skills4nowHEndTime){
		this.skills4nowHEndTime = skills4nowHEndTime;
	}
	/**
	* <p>Discription:[是否现最高技能等级  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsMax4now(){
		return skillsMax4now;
	}
	/**
	* <p>Discription:[是否现最高技能等级  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsMax4now(Integer skillsMax4now){
		this.skillsMax4now = skillsMax4now;
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
