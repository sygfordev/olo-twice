package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [技能等级（技术工种）表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmSkillsInfoModel implements Serializable {

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
     * 技能等级
     */
	@Dict(key = "skills")
	private Integer skills;
	/**
     * 技能等级-名称
     */
	private String skillsCn;
	/**
     * 技能等级取得时间
     */
	private java.util.Date skillsGotTime;
	/**
     * 技能等级序列（技能分类）
     */
	@Dict(key = "skillsClass")
	private Integer skillsClass;
	/**
     * 技能等级序列-名称
     */
	private String skillsClassCn;
	/**
     * 技能等级级别(五级|四级|三级|二级|一级)
     */
	@Dict(key = "skillsLevel")
	private Integer skillsLevel;
	/**
     * 技能等级级别-名称
     */
	private String skillsLevelCn;
	/**
     * 是否最高职称 0：否  1：是
     */
	@Dict(key = "YN")
	private Integer skillsMax;
	/**
     * 技能顺序
     */
	private Integer skillsOrder;
	/**
     * 技能证书编号
     */
	private String skillsCertNo;
	/**
     * 技能任职文号
     */
	private String skillsOnitNo;
	/**
     * 技能初聘文号
     */
	private String skillsHireNo;
	/**
     * 技能初聘开始时间
     */
	private java.util.Date skillsHStartTime;
	/**
     * 技能初聘结束时间
     */
	private java.util.Date skillsHEndTime;
	/**
     * 技能聘任周期
     */
	private Integer skillsHCycle;
	/**
     * 其他技能
     */
	@Dict(key = "skills")
	private Integer skillsOth;
	/**
     * 其他技能-名称
     */
	private String skillsOthCn;
	/**
     * 其他技能取得时间
     */
	private java.util.Date skillsOthGotTime;
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
	* <p>Discription:[技能等级]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkills(){
		return skills;
	}
	/**
	* <p>Discription:[技能等级]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkills(Integer skills){
		this.skills = skills;
	}
	/**
	* <p>Discription:[技能等级-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsCn(){
		return skillsCn;
	}
	/**
	* <p>Discription:[技能等级-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsCn(String skillsCn){
		this.skillsCn = skillsCn;
	}
	/**
	* <p>Discription:[技能等级取得时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkillsGotTime(){
		return skillsGotTime;
	}
	/**
	* <p>Discription:[技能等级取得时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsGotTime(java.util.Date skillsGotTime){
		this.skillsGotTime = skillsGotTime;
	}
	/**
	* <p>Discription:[技能等级序列（技能分类）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsClass(){
		return skillsClass;
	}
	/**
	* <p>Discription:[技能等级序列（技能分类）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsClass(Integer skillsClass){
		this.skillsClass = skillsClass;
	}
	/**
	* <p>Discription:[技能等级序列-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsClassCn(){
		return skillsClassCn;
	}
	/**
	* <p>Discription:[技能等级序列-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsClassCn(String skillsClassCn){
		this.skillsClassCn = skillsClassCn;
	}
	/**
	* <p>Discription:[技能等级级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsLevel(){
		return skillsLevel;
	}
	/**
	* <p>Discription:[技能等级级别(五级|四级|三级|二级|一级)]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsLevel(Integer skillsLevel){
		this.skillsLevel = skillsLevel;
	}
	/**
	* <p>Discription:[技能等级级别-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsLevelCn(){
		return skillsLevelCn;
	}
	/**
	* <p>Discription:[技能等级级别-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsLevelCn(String skillsLevelCn){
		this.skillsLevelCn = skillsLevelCn;
	}
	/**
	* <p>Discription:[是否最高职称 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsMax(){
		return skillsMax;
	}
	/**
	* <p>Discription:[是否最高职称 0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsMax(Integer skillsMax){
		this.skillsMax = skillsMax;
	}
	/**
	* <p>Discription:[技能顺序]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsOrder(){
		return skillsOrder;
	}
	/**
	* <p>Discription:[技能顺序]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsOrder(Integer skillsOrder){
		this.skillsOrder = skillsOrder;
	}
	/**
	* <p>Discription:[技能证书编号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsCertNo(){
		return skillsCertNo;
	}
	/**
	* <p>Discription:[技能证书编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsCertNo(String skillsCertNo){
		this.skillsCertNo = skillsCertNo;
	}
	/**
	* <p>Discription:[技能任职文号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsOnitNo(){
		return skillsOnitNo;
	}
	/**
	* <p>Discription:[技能任职文号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsOnitNo(String skillsOnitNo){
		this.skillsOnitNo = skillsOnitNo;
	}
	/**
	* <p>Discription:[技能初聘文号]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsHireNo(){
		return skillsHireNo;
	}
	/**
	* <p>Discription:[技能初聘文号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsHireNo(String skillsHireNo){
		this.skillsHireNo = skillsHireNo;
	}
	/**
	* <p>Discription:[技能初聘开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkillsHStartTime(){
		return skillsHStartTime;
	}
	/**
	* <p>Discription:[技能初聘开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsHStartTime(java.util.Date skillsHStartTime){
		this.skillsHStartTime = skillsHStartTime;
	}
	/**
	* <p>Discription:[技能初聘结束时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkillsHEndTime(){
		return skillsHEndTime;
	}
	/**
	* <p>Discription:[技能初聘结束时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsHEndTime(java.util.Date skillsHEndTime){
		this.skillsHEndTime = skillsHEndTime;
	}
	/**
	* <p>Discription:[技能聘任周期]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsHCycle(){
		return skillsHCycle;
	}
	/**
	* <p>Discription:[技能聘任周期]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsHCycle(Integer skillsHCycle){
		this.skillsHCycle = skillsHCycle;
	}
	/**
	* <p>Discription:[其他技能]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getSkillsOth(){
		return skillsOth;
	}
	/**
	* <p>Discription:[其他技能]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsOth(Integer skillsOth){
		this.skillsOth = skillsOth;
	}
	/**
	* <p>Discription:[其他技能-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getSkillsOthCn(){
		return skillsOthCn;
	}
	/**
	* <p>Discription:[其他技能-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsOthCn(String skillsOthCn){
		this.skillsOthCn = skillsOthCn;
	}
	/**
	* <p>Discription:[其他技能取得时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getSkillsOthGotTime(){
		return skillsOthGotTime;
	}
	/**
	* <p>Discription:[其他技能取得时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setSkillsOthGotTime(java.util.Date skillsOthGotTime){
		this.skillsOthGotTime = skillsOthGotTime;
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
