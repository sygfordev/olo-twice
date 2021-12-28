package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 
 * <p>Description: [省份表model]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class SysProvinceModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer provNo;
	/**
     * 
     */
	private String provName;
	/**
     * 
     */
	private String provEn;
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
	
	List<SysCityModel> cityList = new ArrayList<SysCityModel>();
	
	// setter and getter
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
	* @return String
    * @author:huadf
    */
	public String getProvName(){
		return provName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setProvName(String provName){
		this.provName = provName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @return String
    * @author:huadf
    */
	public String getProvEn(){
		return provEn;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月31日
	* @author:huadf
	*/
	public void setProvEn(String provEn){
		this.provEn = provEn;
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
	public List<SysCityModel> getCityList() {
		return cityList;
	}
	public void setCityList(List<SysCityModel> cityList) {
		this.cityList = cityList;
	}
}
