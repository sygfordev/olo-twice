package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/** 
 * <p>Description: [调资记录表model]</p>
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaAdjustRecordModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 调资年月
     */
	private String adjustMonth;
	/**
     * 操作账户
     */
	private String adjustAccount;
	/**
     * 操作人
     */
	private String adjustUser;
	
	/**
	 * 本次调资使用的调资类别及对应的版本号
	 */
	private List<Map<String,Object>> usedAdjustMap = new ArrayList<Map<String,Object>>();
	/**
     * 状态 0：正常  1：已删除
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
	* Created on 2021年05月25日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[调资年月]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getAdjustMonth(){
		return adjustMonth;
	}
	/**
	* <p>Discription:[调资年月]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjustMonth(String adjustMonth){
		this.adjustMonth = adjustMonth;
	}
	/**
	* <p>Discription:[操作账户]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getAdjustAccount(){
		return adjustAccount;
	}
	/**
	* <p>Discription:[操作账户]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjustAccount(String adjustAccount){
		this.adjustAccount = adjustAccount;
	}
	/**
	* <p>Discription:[操作人]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getAdjustUser(){
		return adjustUser;
	}
	/**
	* <p>Discription:[操作人]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjustUser(String adjustUser){
		this.adjustUser = adjustUser;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已删除]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已删除]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public List<Map<String, Object>> getUsedAdjustMap() {
		return usedAdjustMap;
	}
	public void setUsedAdjustMap(List<Map<String, Object>> usedAdjustMap) {
		this.usedAdjustMap = usedAdjustMap;
	}
}
