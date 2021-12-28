package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
/** 
 * <p>Description: [微信访问账户model]</p>
 * Created on 2021年06月01日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaWechatAccountModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 身份证号
     */
	private String cardNo;
	/**
     * 账户类型 0微信公众号 1:其他
     */
	private Integer type;
	/**
     * 密码
     */
	private String passwd;
	/**
	 * 当天密码输错次数
	 */
	private Integer errTimes;
	/**
     * 状态 0：正常  1：已删除
     */
	private Integer status;
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
	* Created on 2021年06月01日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年06月01日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[账户类型 0微信公众号 1:其他]</p>
	* Created on 2021年06月01日
	* @return Integer
    * @author:huadf
    */
	public Integer getType(){
		return type;
	}
	/**
	* <p>Discription:[账户类型 0微信公众号 1:其他]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setType(Integer type){
		this.type = type;
	}
	/**
	* <p>Discription:[密码]</p>
	* Created on 2021年06月01日
	* @return String
    * @author:huadf
    */
	public String getPasswd(){
		return passwd;
	}
	/**
	* <p>Discription:[密码]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setPasswd(String passwd){
		this.passwd = passwd;
	}
	public Integer getErrTimes() {
		return errTimes;
	}
	public void setErrTimes(Integer errTimes) {
		this.errTimes = errTimes;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已删除]</p>
	* Created on 2021年06月01日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已删除]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年06月01日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年06月01日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年06月01日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
