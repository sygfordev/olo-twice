package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [学历信息表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmEduInfoModel implements Serializable {

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
     * 受教院校
     */
	private String eduSch;
	/**
     * 受教专业
     */
	private String eduMaj;
	/**
     * 受教学位
     */
	@Dict(key = "eduDeg")
	private Integer eduDeg;
	/**
     * 受教学位-名称
     */
	private String eduDegCn;
	/**
     * 教育类型（全日制|函授|自考|电大|网络教育）
     */
	@Dict(key = "eduType")
	private Integer eduType;
	/**
     * 教育类型-名称
     */
	private String eduTypeCn;
	/**
     * 学历（高中|大专|本科|硕士|博士等）
     */
	@Dict(key = "eduLev")
	private Integer eduLev;
	/**
     * 学历-名称
     */
	private String eduLevCn;
	/**
     * 学历顺序
     */
	private Integer eduOrder;
	/**
     * 受教开始时间
     */
	private java.util.Date eduStartTime;
	/**
     * 受教结束时间（毕业）
     */
	private java.util.Date eduEndTime;
	/**
     * 是否最高学历  0：否  1：是
     */
	@Dict(key = "YN")
	private Integer eduMax;
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
	* <p>Discription:[受教院校]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduSch(){
		return eduSch;
	}
	/**
	* <p>Discription:[受教院校]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduSch(String eduSch){
		this.eduSch = eduSch;
	}
	/**
	* <p>Discription:[受教专业]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduMaj(){
		return eduMaj;
	}
	/**
	* <p>Discription:[受教专业]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduMaj(String eduMaj){
		this.eduMaj = eduMaj;
	}
	/**
	* <p>Discription:[受教学位]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduDeg(){
		return eduDeg;
	}
	/**
	* <p>Discription:[受教学位]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduDeg(Integer eduDeg){
		this.eduDeg = eduDeg;
	}
	/**
	* <p>Discription:[受教学位-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduDegCn(){
		return eduDegCn;
	}
	/**
	* <p>Discription:[受教学位-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduDegCn(String eduDegCn){
		this.eduDegCn = eduDegCn;
	}
	/**
	* <p>Discription:[教育类型（全日制|函授|自考|电大|网络教育）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduType(){
		return eduType;
	}
	/**
	* <p>Discription:[教育类型（全日制|函授|自考|电大|网络教育）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduType(Integer eduType){
		this.eduType = eduType;
	}
	/**
	* <p>Discription:[教育类型-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduTypeCn(){
		return eduTypeCn;
	}
	/**
	* <p>Discription:[教育类型-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduTypeCn(String eduTypeCn){
		this.eduTypeCn = eduTypeCn;
	}
	/**
	* <p>Discription:[学历（高中|大专|本科|硕士|博士等）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduLev(){
		return eduLev;
	}
	/**
	* <p>Discription:[学历（高中|大专|本科|硕士|博士等）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLev(Integer eduLev){
		this.eduLev = eduLev;
	}
	/**
	* <p>Discription:[学历-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduLevCn(){
		return eduLevCn;
	}
	/**
	* <p>Discription:[学历-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLevCn(String eduLevCn){
		this.eduLevCn = eduLevCn;
	}
	/**
	* <p>Discription:[学历顺序]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduOrder(){
		return eduOrder;
	}
	/**
	* <p>Discription:[学历顺序]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduOrder(Integer eduOrder){
		this.eduOrder = eduOrder;
	}
	/**
	* <p>Discription:[受教开始时间]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getEduStartTime(){
		return eduStartTime;
	}
	/**
	* <p>Discription:[受教开始时间]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduStartTime(java.util.Date eduStartTime){
		this.eduStartTime = eduStartTime;
	}
	/**
	* <p>Discription:[受教结束时间（毕业）]</p>
	* Created on 2021年04月10日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getEduEndTime(){
		return eduEndTime;
	}
	/**
	* <p>Discription:[受教结束时间（毕业）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduEndTime(java.util.Date eduEndTime){
		this.eduEndTime = eduEndTime;
	}
	/**
	* <p>Discription:[是否最高学历  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduMax(){
		return eduMax;
	}
	/**
	* <p>Discription:[是否最高学历  0：否  1：是]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduMax(Integer eduMax){
		this.eduMax = eduMax;
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