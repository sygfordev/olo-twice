package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/** 
 * <p>Description: [医院一级科室表model]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChPmHosDepartModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Integer hdpNo;
	/**
     * 
     */
	private String hdpName;
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
	
	/**
	 * 二级科室列表
	 */
	private List<ChPmHosSecdepModel> subList = new ArrayList<ChPmHosSecdepModel>();;
	
	// setter and getter
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
	public String getHdpName(){
		return hdpName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年04月02日
	* @author:huadf
	*/
	public void setHdpName(String hdpName){
		this.hdpName = hdpName;
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
	public List<ChPmHosSecdepModel> getSubList() {
		return subList;
	}
	public void setSubList(List<ChPmHosSecdepModel> subList) {
		this.subList = subList;
	}
}
