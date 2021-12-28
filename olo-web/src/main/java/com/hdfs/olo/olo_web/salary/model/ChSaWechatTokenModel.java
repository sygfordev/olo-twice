package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
/** 
 * <p>Description: [微信访问Tokenmodel]</p>
 * Created on 2021年05月26日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaWechatTokenModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * token
     */
	private String token;
	/**
     * token类型
     */
	private Integer type;
	/**
     * 身份证号
     */
	private String cardNo;
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
	* Created on 2021年05月26日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[token]</p>
	* Created on 2021年05月26日
	* @return String
    * @author:huadf
    */
	public String getToken(){
		return token;
	}
	/**
	* <p>Discription:[token]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setToken(String token){
		this.token = token;
	}
	/**
	* <p>Discription:[token类型]</p>
	* Created on 2021年05月26日
	* @return Integer
    * @author:huadf
    */
	public Integer getType(){
		return type;
	}
	/**
	* <p>Discription:[token类型]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setType(Integer type){
		this.type = type;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年05月26日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月26日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月26日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
}
