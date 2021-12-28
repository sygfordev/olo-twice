package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
import java.util.List;
/** 
 * <p>Description: [系统字典项model]</p>
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class SystemDictItemModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
	private Long id;
	/**
     * 字典id
     */
	private Long dictId;
	/**
     * 字典名称
     */
	private String dictName;
	/**
     * 字典项Key
     */
	private String itemKey;
	/**
     * 字典项Val
     */
	private String itemVal;
	/**
     * 	字典项是否存在父级
     */
	private Integer existSuper;
	/**
     * 	字典项父级编号
     */
	private Long superId;
	/**
     * 	字典项父级名称
     */
	private String superName;
	/**
     * 排序
     */
	private Integer sortVal;
	/**
     * 状态（0启用 1不启用）
     */
	private Integer status;
	/**
     * 描述
     */
	private String remark;
	/**
     * 
     */
	private String creator;
	/**
     * 
     */
	private java.util.Date createTime;
	/**
     * 
     */
	private String updator;
	/**
     * 
     */
	private java.util.Date updateTime;
	
	/**
	 * 子字典项列表
	 */
	private List<SystemDictItemModel> subList;
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
	* <p>Discription:[字典id]</p>
	* Created on 2021年03月04日
	* @return Long
    * @author:huadf
    */
	public Long getDictId(){
		return dictId;
	}
	/**
	* <p>Discription:[字典id]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setDictId(Long dictId){
		this.dictId = dictId;
	}
	/**
	* <p>Discription:[字典名称]</p>
	* Created on 2021年03月04日
	* @return Long
    * @author:huadf
    */
	public String getDictName(){
		return dictName;
	}
	/**
	* <p>Discription:[字典名称]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setDictName(String dictName){
		this.dictName = dictName;
	}
	/**
	* <p>Discription:[字典项Key]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getItemKey(){
		return itemKey;
	}
	/**
	* <p>Discription:[字典项Key]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setItemKey(String itemKey){
		this.itemKey = itemKey;
	}
	/**
	* <p>Discription:[字典项Val]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getItemVal(){
		return itemVal;
	}
	/**
	* <p>Discription:[字典项Val]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setItemVal(String itemVal){
		this.itemVal = itemVal;
	}
	/**
	* <p>Discription:[字典项是否存在父级]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public Integer getExistSuper(){
		return existSuper;
	}
	/**
	* <p>Discription:[字典项是否存在父级]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setExistSuper(Integer existSuper){
		this.existSuper = existSuper;
	}
	/**
	* <p>Discription:[字典项父级编号]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public Long getSuperId(){
		return superId;
	}
	/**
	* <p>Discription:[字典项父级编号]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setSuperId(Long superId){
		this.superId = superId;
	}
	/**
	* <p>Discription:[字典项父级名称]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getSuperName(){
		return superName;
	}
	/**
	* <p>Discription:[字典项父级名称]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setSuperName(String superName){
		this.superName = superName;
	}
	/**
	* <p>Discription:[排序]</p>
	* Created on 2021年03月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getSortVal(){
		return sortVal;
	}
	/**
	* <p>Discription:[排序]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setSortVal(Integer sortVal){
		this.sortVal = sortVal;
	}
	/**
	* <p>Discription:[状态（0启用 1不启用）]</p>
	* Created on 2021年03月04日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态（0启用 1不启用）]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[描述]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[描述]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getCreator(){
		return creator;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setCreator(String creator){
		this.creator = creator;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @return String
    * @author:huadf
    */
	public String getUpdator(){
		return updator;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setUpdator(String updator){
		this.updator = updator;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @return List
    * @author:huadf
    */
	public List<SystemDictItemModel> getSubList(){
		return subList;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2021年03月04日
	* @author:huadf
	*/
	public void setSubList(List<SystemDictItemModel> subList){
		this.subList = subList;
	}
}
