package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
/** 
 * <p>Description: [调动信息表model]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmTransInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 姓名
     */
	private String name;
	/**
     * 身份证号
     */
	private String cardNo;
	/**
     * 原部门
     */
	private Integer departBefore;
	/**
     * 原部门-名称
     */
	private String departBeforeCn;
	/**
     * 原岗位
     */
	private Integer positBefore;
	/**
     * 原岗位
     */
	private String positBeforeCn;
	/**
     * 新部门
     */
	private Integer departAfter;
	/**
     * 新部门-名称
     */
	private String departAfterCn;
	/**
     * 新岗位
     */
	private Integer positAfter;
	/**
     * 新岗位
     */
	private String positAfterCn;
	/**
     * 调动文号
     */
	private String transDocno;
	/**
     * 调动时间
     */
	private java.util.Date transTime;
	
	/**
	 * 调动开始和结束时间，用于列表检索
	 */
	private String tranStart;
	private String tranSend;
	
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
	* Created on 2021年04月15日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[姓名]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[姓名]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[原部门]</p>
	* Created on 2021年04月15日
	* @return Integer
    * @author:huadf
    */
	public Integer getDepartBefore(){
		return departBefore;
	}
	/**
	* <p>Discription:[原部门]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setDepartBefore(Integer departBefore){
		this.departBefore = departBefore;
	}
	/**
	* <p>Discription:[原部门-名称]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getDepartBeforeCn(){
		return departBeforeCn;
	}
	/**
	* <p>Discription:[原部门-名称]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setDepartBeforeCn(String departBeforeCn){
		this.departBeforeCn = departBeforeCn;
	}
	/**
	* <p>Discription:[原岗位]</p>
	* Created on 2021年04月15日
	* @return Integer
    * @author:huadf
    */
	public Integer getPositBefore(){
		return positBefore;
	}
	/**
	* <p>Discription:[原岗位]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setPositBefore(Integer positBefore){
		this.positBefore = positBefore;
	}
	/**
	* <p>Discription:[原岗位-名称]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getPositBeforeCn(){
		return positBeforeCn;
	}
	/**
	* <p>Discription:[原岗位-名称]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setPositBeforeCn(String positBeforeCn){
		this.positBeforeCn = positBeforeCn;
	}
	/**
	* <p>Discription:[新部门]</p>
	* Created on 2021年04月15日
	* @return Integer
    * @author:huadf
    */
	public Integer getDepartAfter(){
		return departAfter;
	}
	/**
	* <p>Discription:[新部门]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setDepartAfter(Integer departAfter){
		this.departAfter = departAfter;
	}
	/**
	* <p>Discription:[新部门-名称]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getDepartAfterCn(){
		return departAfterCn;
	}
	/**
	* <p>Discription:[新部门-名称]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setDepartAfterCn(String departAfterCn){
		this.departAfterCn = departAfterCn;
	}
	/**
	* <p>Discription:[新岗位]</p>
	* Created on 2021年04月15日
	* @return Integer
    * @author:huadf
    */
	public Integer getPositAfter(){
		return positAfter;
	}
	/**
	* <p>Discription:[新岗位]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setPositAfter(Integer positAfter){
		this.positAfter = positAfter;
	}
	/**
	* <p>Discription:[新岗位-名称]</p>
	* Created on 2021年04月15日
	* @return Srtring
    * @author:huadf
    */
	public String getPositAfterCn(){
		return positAfterCn;
	}
	/**
	* <p>Discription:[新岗位-名称]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setPositAfterCn(String positAfterCn){
		this.positAfterCn = positAfterCn;
	}
	/**
	* <p>Discription:[调动文号]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getTransDocno(){
		return transDocno;
	}
	/**
	* <p>Discription:[调动文号]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setTransDocno(String transDocno){
		this.transDocno = transDocno;
	}
	/**
	* <p>Discription:[调动时间]</p>
	* Created on 2021年04月15日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTransTime(){
		return transTime;
	}
	/**
	* <p>Discription:[调动时间]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setTransTime(java.util.Date transTime){
		this.transTime = transTime;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年04月15日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年04月15日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年04月15日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年04月15日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年04月15日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public String getTranStart() {
		return tranStart;
	}
	public void setTranStart(String tranStart) {
		this.tranStart = tranStart;
	}
	public String getTranSend() {
		return tranSend;
	}
	public void setTranSend(String tranSend) {
		this.tranSend = tranSend;
	}
}
