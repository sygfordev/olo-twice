package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [调资-公式表model]</p>
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaAdjustFormulaModel implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 目标：职称/职务/学历
     */
	private String target;
	/**
     * 目标等级
     */
	@MustExist
	private String targetLevelCn;
	/**
     * 2年及以下
     */
	@MustExist
	private BigDecimal formula2down;
	/**
     * 3-4年
     */
	@MustExist
	private BigDecimal formula3to4;
	/**
     * 5-6年
     */
	@MustExist
	private BigDecimal formula5to6;
	/**
     * 7-8年
     */
	@MustExist
	private BigDecimal formula7to8;
	/**
     * 9-10年
     */
	@MustExist
	private BigDecimal formula9to10;
	/**
     * 11-12年
     */
	@MustExist
	private BigDecimal formula11to12;
	/**
     * 13年以上
     */
	@MustExist
	private BigDecimal formula13up;
	/**
     * 级差
     */
	@MustExist
	private BigDecimal gratdations;
	
	/**
	 * 用于计算-不做入库
	 */
	private BigDecimal targetVal;
	/**
     * 公式分类
     */
	private String formulaType;
	/**
	 * 唯一批次号
	 */
	private String uniqueKey;
	/**
     * 状态 0：正常  1：已过期
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
	* <p>Discription:[目标：职称/职务/学历]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getTarget(){
		return target;
	}
	/**
	* <p>Discription:[目标：职称/职务/学历]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTarget(String target){
		this.target = target;
	}
	/**
	* <p>Discription:[目标等级]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getTargetLevelCn(){
		return targetLevelCn;
	}
	/**
	* <p>Discription:[目标等级]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTargetLevelCn(String targetLevelCn){
		this.targetLevelCn = targetLevelCn;
	}
	/**
	* <p>Discription:[2年及以下]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula2down(){
		return formula2down;
	}
	/**
	* <p>Discription:[2年及以下]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula2down(BigDecimal formula2down){
		this.formula2down = formula2down;
	}
	/**
	* <p>Discription:[3-4年]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula3to4(){
		return formula3to4;
	}
	/**
	* <p>Discription:[3-4年]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula3to4(BigDecimal formula3to4){
		this.formula3to4 = formula3to4;
	}
	/**
	* <p>Discription:[5-6年]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula5to6(){
		return formula5to6;
	}
	/**
	* <p>Discription:[5-6年]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula5to6(BigDecimal formula5to6){
		this.formula5to6 = formula5to6;
	}
	/**
	* <p>Discription:[7-8年]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula7to8(){
		return formula7to8;
	}
	/**
	* <p>Discription:[7-8年]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula7to8(BigDecimal formula7to8){
		this.formula7to8 = formula7to8;
	}
	/**
	* <p>Discription:[9-10年]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula9to10(){
		return formula9to10;
	}
	/**
	* <p>Discription:[9-10年]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula9to10(BigDecimal formula9to10){
		this.formula9to10 = formula9to10;
	}
	/**
	* <p>Discription:[11-12年]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula11to12(){
		return formula11to12;
	}
	/**
	* <p>Discription:[11-12年]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula11to12(BigDecimal formula11to12){
		this.formula11to12 = formula11to12;
	}
	/**
	* <p>Discription:[13年以上]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getFormula13up(){
		return formula13up;
	}
	/**
	* <p>Discription:[13年以上]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormula13up(BigDecimal formula13up){
		this.formula13up = formula13up;
	}
	/**
	* <p>Discription:[级差]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getGratdations(){
		return gratdations;
	}
	/**
	* <p>Discription:[级差]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setGratdations(BigDecimal gratdations){
		this.gratdations = gratdations;
	}
	/**
	* <p>Discription:[公式分类]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getFormulaType(){
		return formulaType;
	}
	/**
	* <p>Discription:[公式分类]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormulaType(String formulaType){
		this.formulaType = formulaType;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已过期]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：已过期]</p>
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
	@Override
	public ChSaAdjustFormulaModel clone() throws CloneNotSupportedException
	{
		Object object = super.clone();
		return (ChSaAdjustFormulaModel)object;
	}
	public BigDecimal getTargetVal() {
		return targetVal;
	}
	public void setTargetVal(BigDecimal targetVal) {
		this.targetVal = targetVal;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
