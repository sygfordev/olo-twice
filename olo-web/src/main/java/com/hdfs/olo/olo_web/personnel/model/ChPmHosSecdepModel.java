package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
/** 
 * <p>Description: [医院二级科室表model]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmHosSecdepModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer hsdNo;
	/**
     * 
     */
	private String hsdName;
	/**
     * 
     */
	private Integer hdpNo;
	/**
     * 
     */
	private Integer hbhNo;
	/**
     * 
     */
	private Integer sorts;
	/**
     * 
     */
	private Integer status;
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public Integer getHsdNo(){
		return hsdNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHsdNo(Integer hsdNo){
		this.hsdNo = hsdNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public String getHsdName(){
		return hsdName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHsdName(String hsdName){
		this.hsdName = hsdName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public Integer getHdpNo(){
		return hdpNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHdpNo(Integer hdpNo){
		this.hdpNo = hdpNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public Integer getHbhNo(){
		return hbhNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHbhNo(Integer hbhNo){
		this.hbhNo = hbhNo;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public Integer getSorts(){
		return sorts;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setSorts(Integer sorts){
		this.sorts = sorts;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @return String
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
}
