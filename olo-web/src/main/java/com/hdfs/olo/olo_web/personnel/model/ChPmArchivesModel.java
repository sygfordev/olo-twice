package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
/** 
 * <p>Description: [人事档案model]</p>
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmArchivesModel extends ChPmWorkerModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 人员编号（工资出账）
     */
	private String wagesId;
	
	
	//================岗位字段===================
	/**
     * 个人身份  1:干部 2:工人
     */
	private Integer identity;
	/**
     * 用工形式
     */
	private Integer wkModality;
	/**
     * 在岗情况
     */
	private Integer stationSitu;
	/**
     * 工作地域
     */
	private Integer workArea;
	/**
     * 医院支部
     */
	private Integer hosBranch;
	/**
     * 医院一级科室
     */
	private Integer hosDepart1level;
	
	
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年03月25日
	* @return String
    * @author:huadf
    */
	public String getWagesId(){
		return wagesId;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年03月25日
	* @author:huadf
	*/
	public void setWagesId(String wagesId){
		this.wagesId = wagesId;
	}
	public Integer getIdentity() {
		return identity;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public Integer getWkModality() {
		return wkModality;
	}
	public void setWkModality(Integer wkModality) {
		this.wkModality = wkModality;
	}
	public Integer getStationSitu() {
		return stationSitu;
	}
	public void setStationSitu(Integer stationSitu) {
		this.stationSitu = stationSitu;
	}
	public Integer getWorkArea() {
		return workArea;
	}
	public void setWorkArea(Integer workArea) {
		this.workArea = workArea;
	}
	public Integer getHosBranch() {
		return hosBranch;
	}
	public void setHosBranch(Integer hosBranch) {
		this.hosBranch = hosBranch;
	}
	public Integer getHosDepart1level() {
		return hosDepart1level;
	}
	public void setHosDepart1level(Integer hosDepart1level) {
		this.hosDepart1level = hosDepart1level;
	}
}
