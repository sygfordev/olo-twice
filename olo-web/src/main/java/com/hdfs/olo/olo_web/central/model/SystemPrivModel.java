package com.hdfs.olo.olo_web.central.model;

import java.io.Serializable;
/** 
 * <p>Description: [系统权限表Model]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public class SystemPrivModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 
     */
	private Long privId;
	/**
     * 
     */
	private String privCnName;
	/**
     * 
     */
	private String privEnName;
	/**
     * 
     */
	private String privTarget;
	/**
     * 
     */
	private String remark;
	/**
     * 
     */
	private Integer privType;
	/**
     * 菜单级别（当privType=1菜单时,privLevel才有会值）
     */
	private Integer menuLevel;
	/**
	 * 排序值（值越小，排序也靠前）
	 */
	private Integer sortVal;
	/**
     * 
     */
	private String privMethod;
	/**
     * 
     */
	private String privPermissioin;
	/**
     * 
     */
	private String privAction;
	/**
     * 
     */
	private String privIcon;
	/**
     * 
     */
	private Long privSuper;
	/**
     * 
     */
	private Integer status;
	/**
     * 
     */
	private java.util.Date createTime;
	/**
     * 
     */
	private java.util.Date updateTime;
	
	// setter and getter
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getPrivId(){
		return privId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivId(Long privId){
		this.privId = privId;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivCnName(){
		return privCnName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivCnName(String privCnName){
		this.privCnName = privCnName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivEnName(){
		return privEnName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivEnName(String privEnName){
		this.privEnName = privEnName;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivTarget(){
		return privTarget;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivTarget(String privTarget){
		this.privTarget = privTarget;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public Integer getPrivType(){
		return privType;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivType(Integer privType){
		this.privType = privType;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivMethod(){
		return privMethod;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivMethod(String privMethod){
		this.privMethod = privMethod;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivPermissioin(){
		return privPermissioin;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivPermissioin(String privPermissioin){
		this.privPermissioin = privPermissioin;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivAction(){
		return privAction;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivAction(String privAction){
		this.privAction = privAction;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public String getPrivIcon(){
		return privIcon;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivIcon(String privIcon){
		this.privIcon = privIcon;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return Long
    * @author:huadf
    */
	public Long getPrivSuper(){
		return privSuper;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setPrivSuper(Long privSuper){
		this.privSuper = privSuper;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return String
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[]</p>
	* Created on 2020年03月17日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	
	public Integer getMenuLevel(){
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel){
		this.menuLevel = menuLevel;
	}
	public Integer getSortVal(){
		return sortVal;
	}
	public void setSortVal(Integer sortVal){
		this.sortVal = sortVal;
	}
}
