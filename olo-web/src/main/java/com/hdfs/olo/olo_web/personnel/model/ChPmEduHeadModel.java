package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [学历信息头表model]</p>
 * Created on 2021年04月10日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmEduHeadModel implements Serializable {

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
     * 学习学位
     */
	@Dict(key = "eduDeg")
	private Integer eduDeg4now;
	/**
     * 学习学位-名称
     */
	private String eduDeg4nowCn;
	/**
     * 学历（高中|大专|本科|硕士|博士等）
     */
	@Dict(key = "eduLev")
	private Integer eduLev4now;
	/**
     * 学历名称
     */
	private String eduLev4nowCn;
	/**
     * 工资学历
     */
	@Dict(key = "eduLev")
	private Integer eduLev4sal;
	/**
     * 工资学历-名称
     */
	private String eduLev4salCn;
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
	* <p>Discription:[学习学位]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduDeg4now(){
		return eduDeg4now;
	}
	/**
	* <p>Discription:[学习学位]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduDeg4now(Integer eduDeg4now){
		this.eduDeg4now = eduDeg4now;
	}
	/**
	* <p>Discription:[学习学位-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduDeg4nowCn(){
		return eduDeg4nowCn;
	}
	/**
	* <p>Discription:[学习学位-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduDeg4nowCn(String eduDeg4nowCn){
		this.eduDeg4nowCn = eduDeg4nowCn;
	}
	/**
	* <p>Discription:[学历（高中|大专|本科|硕士|博士等）]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduLev4now(){
		return eduLev4now;
	}
	/**
	* <p>Discription:[学历（高中|大专|本科|硕士|博士等）]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLev4now(Integer eduLev4now){
		this.eduLev4now = eduLev4now;
	}
	/**
	* <p>Discription:[学历名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduLev4nowCn(){
		return eduLev4nowCn;
	}
	/**
	* <p>Discription:[学历名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLev4nowCn(String eduLev4nowCn){
		this.eduLev4nowCn = eduLev4nowCn;
	}
	/**
	* <p>Discription:[工资学历]</p>
	* Created on 2021年04月10日
	* @return Integer
    * @author:huadf
    */
	public Integer getEduLev4sal(){
		return eduLev4sal;
	}
	/**
	* <p>Discription:[工资学历]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLev4sal(Integer eduLev4sal){
		this.eduLev4sal = eduLev4sal;
	}
	/**
	* <p>Discription:[工资学历-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getEduLev4salCn(){
		return eduLev4salCn;
	}
	/**
	* <p>Discription:[工资学历-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setEduLev4salCn(String eduLev4salCn){
		this.eduLev4salCn = eduLev4salCn;
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
