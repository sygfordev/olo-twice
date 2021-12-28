package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [区县表model]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class SysAreaModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer areaNo;
	/**
     * 
     */
	private String areaName;
	/**
     * 
     */
	private String areaEn;
	/**
     * 
     */
	private Integer cityNo;
	/**
     * 
     */
	private Integer provNo;
	/**
     * 
     */
	private Integer sorts;
	/**
     * 
     */
	private String lat;
	/**
     * 
     */
	private Integer status;
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return Integer
    * @author:huadf
    */
	public Integer getAreaNo(){
		return areaNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setAreaNo(Integer areaNo){
		this.areaNo = areaNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return String
    * @author:huadf
    */
	public String getAreaName(){
		return areaName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setAreaName(String areaName){
		this.areaName = areaName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return String
    * @author:huadf
    */
	public String getAreaEn(){
		return areaEn;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setAreaEn(String areaEn){
		this.areaEn = areaEn;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return Integer
    * @author:huadf
    */
	public Integer getCityNo(){
		return cityNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setCityNo(Integer cityNo){
		this.cityNo = cityNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return Integer
    * @author:huadf
    */
	public Integer getProvNo(){
		return provNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setProvNo(Integer provNo){
		this.provNo = provNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return Integer
    * @author:huadf
    */
	public Integer getSorts(){
		return sorts;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setSorts(Integer sorts){
		this.sorts = sorts;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return String
    * @author:huadf
    */
	public String getLat(){
		return lat;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setLat(String lat){
		this.lat = lat;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
}
