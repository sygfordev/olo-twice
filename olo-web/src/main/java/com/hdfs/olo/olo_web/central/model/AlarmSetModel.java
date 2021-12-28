package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [警报设置model]</p>
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class AlarmSetModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 警报类型
     */
	private Integer alarmType;
	/**
     * 提前天/小时/分钟....数量
     */
	private Integer alarmAdSize;
	/**
     * 提前单位
     */
	private String alarmAdUnit;
	/**
     * 报警样板
     */
	private String alarmTeInfo;
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
	* Created on 2021年09月04日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[警报类型]</p>
	* Created on 2021年09月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getAlarmType(){
		return alarmType;
	}
	/**
	* <p>Discription:[警报类型]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setAlarmType(Integer alarmType){
		this.alarmType = alarmType;
	}
	/**
	* <p>Discription:[提前天/小时/分钟....数量]</p>
	* Created on 2021年09月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getAlarmAdSize(){
		return alarmAdSize;
	}
	/**
	* <p>Discription:[提前天/小时/分钟....数量]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setAlarmAdSize(Integer alarmAdSize){
		this.alarmAdSize = alarmAdSize;
	}
	/**
	* <p>Discription:[提前单位]</p>
	* Created on 2021年09月04日
	* @return String
    * @author:huadf
    */
	public String getAlarmAdUnit(){
		return alarmAdUnit;
	}
	/**
	* <p>Discription:[提前单位]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setAlarmAdUnit(String alarmAdUnit){
		this.alarmAdUnit = alarmAdUnit;
	}
	/**
	* <p>Discription:[报警样板]</p>
	* Created on 2021年09月04日
	* @return String
    * @author:huadf
    */
	public String getAlarmTeInfo(){
		return alarmTeInfo;
	}
	/**
	* <p>Discription:[报警样板]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setAlarmTeInfo(String alarmTeInfo){
		this.alarmTeInfo = alarmTeInfo;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年09月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年09月04日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年09月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年09月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年09月04日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
