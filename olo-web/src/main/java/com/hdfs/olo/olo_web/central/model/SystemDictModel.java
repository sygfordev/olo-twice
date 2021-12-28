package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [系统字典model]</p>
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class SystemDictModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
	private Long id;
	/**
     * 字典名称
     */
	private String name;
	/**
     * 字典编码
     */
	private String code;
	/**
     * 字典类型0为string,1为number
     */
	private Integer type;
	/**
     * 状态  0:正常  1：删除
     */
	private Integer status;
	/**
     * 备注
     */
	private String remark;
	/**
     * 创建人员
     */
	private String creator;
	/**
     * 创建时间
     */
	private java.util.Date createTime;
	/**
     * 更新人员
     */
	private String updator;
	/**
     * 更新时间
     */
	private java.util.Date updateTime;
	
	// setter and getter
	/**
	* <p>Discription:[主键]</p>
	* Created on 2021年03月04日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[主键]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[字典名称]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[字典名称]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[字典编码]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getCode(){
		return code;
	}
	/**
	* <p>Discription:[字典编码]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setCode(String code){
		this.code = code;
	}
	/**
	* <p>Discription:[字典类型0为string,1为number]</p>
	* Created on 2021年03月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getType(){
		return type;
	}
	/**
	* <p>Discription:[字典类型0为string,1为number]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setType(Integer type){
		this.type = type;
	}
	/**
	* <p>Discription:[状态  0:正常  1：删除]</p>
	* Created on 2021年03月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态  0:正常  1：删除]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建人员]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getCreator(){
		return creator;
	}
	/**
	* <p>Discription:[创建人员]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setCreator(String creator){
		this.creator = creator;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新人员]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getUpdator(){
		return updator;
	}
	/**
	* <p>Discription:[更新人员]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setUpdator(String updator){
		this.updator = updator;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
