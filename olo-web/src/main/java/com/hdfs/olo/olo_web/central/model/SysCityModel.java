package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 
 * <p>Description: [城市表model]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class SysCityModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer cityNo;
	/**
     * 
     */
	private String cityName;
	/**
     * 
     */
	private String cityEn;
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
	
	private List<SysAreaModel> areaList = new ArrayList<SysAreaModel>();
	// setter and getter
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
	* @return String
    * @author:huadf
    */
	public String getCityName(){
		return cityName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return String
    * @author:huadf
    */
	public String getCityEn(){
		return cityEn;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setCityEn(String cityEn){
		this.cityEn = cityEn;
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
	public List<SysAreaModel> getAreaList() {
		return areaList;
	}
	public void setAreaList(List<SysAreaModel> areaList) {
		this.areaList = areaList;
	}
}
