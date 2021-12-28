package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [其他信息model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmOtherInfoModel implements Serializable {

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
     * 是否疫情防控一线医护人员  0:否 1:是
     */
	private Integer epidsPrecFlHcStaff;
	/**
     * 荣誉金取得时间
     */
	private java.util.Date honMoneyGotTime;
	/**
     * 核减工龄年限
     */
	private Integer caeWkYears;
	/**
	 * 是否残疾人
	 */
	private Integer isDisability;
	/**
	 * 残疾鉴定级别
	 */
	private String disabilityLev;
	/**
	 * 是否工伤
	 */
	private Integer isInjryOnJob;
	/**
	 * 工伤鉴定级别
	 */
	private String injryLev;
	/**
     * 状态 0：正常  1：异常
     */
	private Integer status;
	/**
     * 备注1
     */
	private String remark;
	/**
     * 备注2
     */
	private String remark2;
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
	* <p>Discription:[是否疫情防控一线医护人员  0:否 1:是]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getEpidsPrecFlHcStaff(){
		return epidsPrecFlHcStaff;
	}
	/**
	* <p>Discription:[是否疫情防控一线医护人员  0:否 1:是]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setEpidsPrecFlHcStaff(Integer epidsPrecFlHcStaff){
		this.epidsPrecFlHcStaff = epidsPrecFlHcStaff;
	}
	/**
	* <p>Discription:[荣誉金取得时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getHonMoneyGotTime(){
		return honMoneyGotTime;
	}
	/**
	* <p>Discription:[荣誉金取得时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setHonMoneyGotTime(java.util.Date honMoneyGotTime){
		this.honMoneyGotTime = honMoneyGotTime;
	}
	/**
	* <p>Discription:[核减工龄年限]</p>
	* Created on 2021年03月29日
	* @return Integer
    * @author:huadf
    */
	public Integer getCaeWkYears(){
		return caeWkYears;
	}
	/**
	* <p>Discription:[核减工龄年限]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setCaeWkYears(Integer caeWkYears){
		this.caeWkYears = caeWkYears;
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
	* <p>Discription:[备注1]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注1]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[备注2]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getRemark2(){
		return remark2;
	}
	/**
	* <p>Discription:[备注2]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setRemark2(String remark2){
		this.remark2 = remark2;
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
	public Integer getIsDisability() {
		return isDisability;
	}
	public void setIsDisability(Integer isDisability) {
		this.isDisability = isDisability;
	}
	public String getDisabilityLev() {
		return disabilityLev;
	}
	public void setDisabilityLev(String disabilityLev) {
		this.disabilityLev = disabilityLev;
	}
	public Integer getIsInjryOnJob() {
		return isInjryOnJob;
	}
	public void setIsInjryOnJob(Integer isInjryOnJob) {
		this.isInjryOnJob = isInjryOnJob;
	}
	public String getInjryLev() {
		return injryLev;
	}
	public void setInjryLev(String injryLev) {
		this.injryLev = injryLev;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
