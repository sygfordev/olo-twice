package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [工作经历信息model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmWorkExpeInfoModel implements Serializable {

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
     * 工作单位名称
     */
	private String wkComName;
	/**
     * 工作部门名称
     */
	private String wkDepName;
	/**
     * 工作岗位
     */
	private String wkStation;
	/**
     * 担任职务
     */
	private String wkPosit;
	/**
     * 职称
     */
	private String wkTitle;
	/**
     * 调动文号
     */
	private String transDocno;
	/**
     * 工作开始时间
     */
	private java.util.Date wkStartTime;
	/**
     * 工作结束时间
     */
	private java.util.Date wkEndTime;
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
	* <p>Discription:[工作单位名称]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getWkComName(){
		return wkComName;
	}
	/**
	* <p>Discription:[工作单位名称]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkComName(String wkComName){
		this.wkComName = wkComName;
	}
	/**
	* <p>Discription:[工作部门名称]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getWkDepName(){
		return wkDepName;
	}
	/**
	* <p>Discription:[工作部门名称]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkDepName(String wkDepName){
		this.wkDepName = wkDepName;
	}
	/**
	* <p>Discription:[工作岗位]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getWkStation(){
		return wkStation;
	}
	/**
	* <p>Discription:[工作岗位]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkStation(String wkStation){
		this.wkStation = wkStation;
	}
	/**
	* <p>Discription:[担任职务]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getWkPosit(){
		return wkPosit;
	}
	/**
	* <p>Discription:[担任职务]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkPosit(String wkPosit){
		this.wkPosit = wkPosit;
	}
	/**
	* <p>Discription:[职称]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getWkTitle(){
		return wkTitle;
	}
	/**
	* <p>Discription:[职称]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkTitle(String wkTitle){
		this.wkTitle = wkTitle;
	}
	/**
	* <p>Discription:[调动文号]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getTransDocno(){
		return transDocno;
	}
	/**
	* <p>Discription:[调动文号]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setTransDocno(String transDocno){
		this.transDocno = transDocno;
	}
	/**
	* <p>Discription:[工作开始时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getWkStartTime(){
		return wkStartTime;
	}
	/**
	* <p>Discription:[工作开始时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkStartTime(java.util.Date wkStartTime){
		this.wkStartTime = wkStartTime;
	}
	/**
	* <p>Discription:[工作结束时间]</p>
	* Created on 2021年03月29日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getWkEndTime(){
		return wkEndTime;
	}
	/**
	* <p>Discription:[工作结束时间]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setWkEndTime(java.util.Date wkEndTime){
		this.wkEndTime = wkEndTime;
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
	* <p>Discription:[备注]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
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
}
