package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.HosBranchDepart.Target;
/** 
 * <p>Description: [用工及岗位表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmStationModel implements Serializable {

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
     * 人员编号（工资出账）
     */
	private String wagesId;
	/**
     * 个人身份  1:干部 2:工人
     */
	@Dict(key = "identity")
	private Integer identity;
	/**
     * 个人身份-名称
     */
	private String identityCn;
	/**
     * 用工形式
     */
	@Dict(key = "wkModality")
	private Integer wkModality;
	/**
     * 用工形式-名称
     */
	private String wkModalityCn;
	/**
     * 用工类型
     */
	@Dict(key = "wkType")
	private Integer wkType;
	/**
     * 用工类型-名称
     */
	private String wkTypeCn;
	/**
     * 劳动关系编号
     */
    @Dict(key = "laborRel",cnField = "laborRelCn")
	private Integer laborRelNo;
	/**
     * 劳动关系名称
     */
	private String laborRelCn;
	/**
     * 在岗情况
     */
	@Dict(key = "stationSitu")
	private Integer stationSitu;
	/**
     * 在岗情况-名称
     */
	private String stationSituCn;
	/**
     * 岗位类型
     */
	@Dict(key = "stationType")
	private Integer stationType;
	/**
     * 岗位类型-名称
     */
	private String stationTypeCn;
	/**
     * 岗位状态
     */
	@Dict(key = "stationStatus")
	private Integer stationStatus;
	/**
     * 岗位状态-名称
     */
	private String stationStatusCn;
	/**
     * 岗位序列编号
     */
	@Dict(key = "stationSeq",cnField = "stationSeqCn")
	private Integer stationSeqNo;
	/**
     * 岗位序列名称
     */
	private String stationSeqCn;
	/**
     * 是否专业人员
     */
	@Dict(key = "YN")
	private Integer isMajorPerson;
	/**
     * 现从事专业
     */
	private Integer inMajorNow;
	/**
     * 现从事专业
     */
	private String inMajorNowCn;
	/**
     * 工作地域
     */
	@Dict(key = "workArea")
	private Integer workArea;
	/**
     * 工作地域-名称
     */
	private String workAreaCn;
	/**
     * 医院支部
     */
	@HosBranchDepart(target = Target.BRANCH,cnField = "hosBranchCn")
	private Integer hosBranch;
	/**
     * 医院支部-名称
     */
	private String hosBranchCn;
	/**
     * 医院一级科室
     */
	@HosBranchDepart(target = Target.DEPART1,superBKey = "hosBranch",cnField = "hosDepart1levelCn")
	private Integer hosDepart1level;
	/**
     * 医院一级科室-名称
     */
	private String hosDepart1levelCn;
	/**
     * 医院二级科室
     */
	@HosBranchDepart(target = Target.DEPART2,superBKey = "hosBranch",superD1Key = "hosDepart1level",cnField = "hosDepart2levelCn")
	private Integer hosDepart2level;
	/**
     * 医院二级科室-名称
     */
	private String hosDepart2levelCn;
	/**
     * 现家庭详细住址
     */
	private String homeAddress;
	/**
     * 联系电话
     */
	private String telphoneNo;
	/**
     * 邮箱
     */
	private String mailBox;
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
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getWagesId(){
		return wagesId;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWagesId(String wagesId){
		this.wagesId = wagesId;
	}
	/**
	* <p>Discription:[个人身份  1:干部 2:工人]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getIdentity(){
		return identity;
	}
	/**
	* <p>Discription:[个人身份  1:干部 2:工人]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setIdentity(Integer identity){
		this.identity = identity;
	}
	/**
	* <p>Discription:[个人身份-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getIdentityCn(){
		return identityCn;
	}
	/**
	* <p>Discription:[个人身份-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setIdentityCn(String identityCn){
		this.identityCn = identityCn;
	}
	/**
	* <p>Discription:[用工形式]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getWkModality(){
		return wkModality;
	}
	/**
	* <p>Discription:[用工形式]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWkModality(Integer wkModality){
		this.wkModality = wkModality;
	}
	/**
	* <p>Discription:[用工形式-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getWkModalityCn(){
		return wkModalityCn;
	}
	/**
	* <p>Discription:[用工形式-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWkModalityCn(String wkModalityCn){
		this.wkModalityCn = wkModalityCn;
	}
	/**
	* <p>Discription:[用工类型]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getWkType(){
		return wkType;
	}
	/**
	* <p>Discription:[用工类型]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWkType(Integer wkType){
		this.wkType = wkType;
	}
	/**
	* <p>Discription:[用工类型-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getWkTypeCn(){
		return wkTypeCn;
	}
	/**
	* <p>Discription:[用工类型-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWkTypeCn(String wkTypeCn){
		this.wkTypeCn = wkTypeCn;
	}
	/**
	* <p>Discription:[劳动关系编号]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getLaborRelNo(){
		return laborRelNo;
	}
	/**
	* <p>Discription:[劳动关系编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setLaborRelNo(Integer laborRelNo){
		this.laborRelNo = laborRelNo;
	}
	/**
	* <p>Discription:[劳动关系名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getLaborRelCn(){
		return laborRelCn;
	}
	/**
	* <p>Discription:[劳动关系名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setLaborRelCn(String laborRelCn){
		this.laborRelCn = laborRelCn;
	}
	/**
	* <p>Discription:[在岗情况]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getStationSitu(){
		return stationSitu;
	}
	/**
	* <p>Discription:[在岗情况]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationSitu(Integer stationSitu){
		this.stationSitu = stationSitu;
	}
	/**
	* <p>Discription:[在岗情况-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getStationSituCn(){
		return stationSituCn;
	}
	/**
	* <p>Discription:[在岗情况-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationSituCn(String stationSituCn){
		this.stationSituCn = stationSituCn;
	}
	/**
	* <p>Discription:[岗位类型]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getStationType(){
		return stationType;
	}
	/**
	* <p>Discription:[岗位类型]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationType(Integer stationType){
		this.stationType = stationType;
	}
	/**
	* <p>Discription:[岗位类型-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getStationTypeCn(){
		return stationTypeCn;
	}
	/**
	* <p>Discription:[岗位类型-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationTypeCn(String stationTypeCn){
		this.stationTypeCn = stationTypeCn;
	}
	/**
	* <p>Discription:[岗位状态]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getStationStatus(){
		return stationStatus;
	}
	/**
	* <p>Discription:[岗位状态]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationStatus(Integer stationStatus){
		this.stationStatus = stationStatus;
	}
	/**
	* <p>Discription:[岗位状态-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getStationStatusCn(){
		return stationStatusCn;
	}
	/**
	* <p>Discription:[岗位状态-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationStatusCn(String stationStatusCn){
		this.stationStatusCn = stationStatusCn;
	}
	/**
	* <p>Discription:[岗位序列编号]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getStationSeqNo(){
		return stationSeqNo;
	}
	/**
	* <p>Discription:[岗位序列编号]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationSeqNo(Integer stationSeqNo){
		this.stationSeqNo = stationSeqNo;
	}
	/**
	* <p>Discription:[岗位序列名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getStationSeqCn(){
		return stationSeqCn;
	}
	/**
	* <p>Discription:[岗位序列名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setStationSeqCn(String stationSeqCn){
		this.stationSeqCn = stationSeqCn;
	}
	/**
	* <p>Discription:[是否专业人员]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getIsMajorPerson(){
		return isMajorPerson;
	}
	/**
	* <p>Discription:[是否专业人员]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setIsMajorPerson(Integer isMajorPerson){
		this.isMajorPerson = isMajorPerson;
	}
	/**
	* <p>Discription:[现从事专业]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public Integer getInMajorNow(){
		return inMajorNow;
	}
	/**
	* <p>Discription:[现从事专业]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setInMajorNow(Integer inMajorNow){
		this.inMajorNow = inMajorNow;
	}
	/**
	* <p>Discription:[现从事专业]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getInMajorNowCn(){
		return inMajorNowCn;
	}
	/**
	* <p>Discription:[现从事专业]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setInMajorNowCn(String inMajorNowCn){
		this.inMajorNowCn = inMajorNowCn;
	}
	/**
	* <p>Discription:[工作地域]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getWorkArea(){
		return workArea;
	}
	/**
	* <p>Discription:[工作地域]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWorkArea(Integer workArea){
		this.workArea = workArea;
	}
	/**
	* <p>Discription:[工作地域-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getWorkAreaCn(){
		return workAreaCn;
	}
	/**
	* <p>Discription:[工作地域-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setWorkAreaCn(String workAreaCn){
		this.workAreaCn = workAreaCn;
	}
	/**
	* <p>Discription:[医院支部]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosBranch(){
		return hosBranch;
	}
	/**
	* <p>Discription:[医院支部]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosBranch(Integer hosBranch){
		this.hosBranch = hosBranch;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getHosBranchCn(){
		return hosBranchCn;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosBranchCn(String hosBranchCn){
		this.hosBranchCn = hosBranchCn;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosDepart1level(){
		return hosDepart1level;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosDepart1level(Integer hosDepart1level){
		this.hosDepart1level = hosDepart1level;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getHosDepart1levelCn(){
		return hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosDepart1levelCn(String hosDepart1levelCn){
		this.hosDepart1levelCn = hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosDepart2level(){
		return hosDepart2level;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosDepart2level(Integer hosDepart2level){
		this.hosDepart2level = hosDepart2level;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getHosDepart2levelCn(){
		return hosDepart2levelCn;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHosDepart2levelCn(String hosDepart2levelCn){
		this.hosDepart2levelCn = hosDepart2levelCn;
	}
	/**
	* <p>Discription:[现家庭详细住址]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getHomeAddress(){
		return homeAddress;
	}
	/**
	* <p>Discription:[现家庭详细住址]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setHomeAddress(String homeAddress){
		this.homeAddress = homeAddress;
	}
	/**
	* <p>Discription:[联系电话]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getTelphoneNo(){
		return telphoneNo;
	}
	/**
	* <p>Discription:[联系电话]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setTelphoneNo(String telphoneNo){
		this.telphoneNo = telphoneNo;
	}
	/**
	* <p>Discription:[邮箱]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getMailBox(){
		return mailBox;
	}
	/**
	* <p>Discription:[邮箱]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setMailBox(String mailBox){
		this.mailBox = mailBox;
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
