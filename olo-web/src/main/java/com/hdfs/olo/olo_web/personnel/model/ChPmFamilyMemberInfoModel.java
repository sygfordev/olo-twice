package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [家庭成员model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmFamilyMemberInfoModel implements Serializable {

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
     * 家庭成员关系
     */
	@Dict(key = "homeMemRel")
	private String memRelation;
	/**
     * 家庭成员关系-名称
     */
	private String memRelationCn;
	/**
     * 家庭成员姓名
     */
	private String memName;
	/**
     * 家庭成员工作单位
     */
	private String memWkCom;
	/**
     * 家庭成员联系电话
     */
	private String memTelno;
	/**
     * 现详细住址
     */
	private String memAddr;
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
	* <p>Discription:[家庭成员关系]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getMemRelation(){
		return memRelation;
	}
	/**
	* <p>Discription:[家庭成员关系]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setMemRelation(String memRelation){
		this.memRelation = memRelation;
	}
	/**
	* <p>Discription:[家庭成员关系-名称]</p>
	* Created on 2021年04月10日
	* @return String
    * @author:huadf
    */
	public String getMemRelationCn(){
		return memRelationCn;
	}
	/**
	* <p>Discription:[家庭成员关系-名称]</p>
	* Created on 2021年04月10日
	* @author:huadf
	*/
	public void setMemRelationCn(String memRelationCn){
		this.memRelationCn = memRelationCn;
	}
	/**
	* <p>Discription:[家庭成员姓名]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getMemName(){
		return memName;
	}
	/**
	* <p>Discription:[家庭成员姓名]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setMemName(String memName){
		this.memName = memName;
	}
	/**
	* <p>Discription:[家庭成员工作单位]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getMemWkCom(){
		return memWkCom;
	}
	/**
	* <p>Discription:[家庭成员工作单位]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setMemWkCom(String memWkCom){
		this.memWkCom = memWkCom;
	}
	/**
	* <p>Discription:[家庭成员联系电话]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getMemTelno(){
		return memTelno;
	}
	/**
	* <p>Discription:[家庭成员联系电话]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setMemTelno(String memTelno){
		this.memTelno = memTelno;
	}
	/**
	* <p>Discription:[现详细住址]</p>
	* Created on 2021年03月29日
	* @return String
    * @author:huadf
    */
	public String getMemAddr(){
		return memAddr;
	}
	/**
	* <p>Discription:[现详细住址]</p>
	* Created on 2021年03月29日
	* @author:huadf
	*/
	public void setMemAddr(String memAddr){
		this.memAddr = memAddr;
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
