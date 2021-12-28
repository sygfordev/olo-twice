package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 
 * <p>Description: [医院支部表model]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmHosBranchModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer hbhNo;
	/**
     * 
     */
	private String hbhName;
	/**
     * 
     */
	private Integer sorts;
	/**
     * 
     */
	private Integer status;
	
	private List<ChPmHosDepartModel> subList = new ArrayList<ChPmHosDepartModel>();
	
	// setter and getter
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
	public String getHbhName(){
		return hbhName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHbhName(String hbhName){
		this.hbhName = hbhName;
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
	public List<ChPmHosDepartModel> getSubList() {
		return subList;
	}
	public void setSubList(List<ChPmHosDepartModel> subList) {
		this.subList = subList;
	}
}
