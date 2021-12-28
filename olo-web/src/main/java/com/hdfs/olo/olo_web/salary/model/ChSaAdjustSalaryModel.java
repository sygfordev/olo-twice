package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [调资表model]</p>
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaAdjustSalaryModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
     * 自增主键
     */
	private Long id;
	/**
     * 职工姓名
     */
	@MustExist
	private String name;
	/**
     * 卡号（身份）
     */
	@MustExist
	private String cardNo;
	/**
     * 人员编号（工资出账）
     */
	private String wagesId;
	/**
     * 工资账序号
     */
	private String wagesSeq;
	/**
     * 工资账姓名
     */
	private String wagesName;
	/**
     * 工资账用工形式
     */
	private String wagesModalityCn;
	/**
     * 工资账科室
     */
	private String wagesDepart;
	/**
     * 原岗位工资标准
     */
	private BigDecimal wagesStandardBef;
	/**
     * 调资类别
     */
	@MustExist
	private String salaryAdjustTypeCn;
	/**
     * 医院一级科室
     */
	private String hosDepart1levelCn;
	/**
     * 医院二级科室
     */
	private String hosDepart2levelCn;
	/**
     * 最高学历
     */
	@MustExist
	private String edu4max;
	/**
     * 第一学历
     */
	private String edu4first;
	/**
     * 工资学历
     */
	private String edu4wage;
	/**
     * 首次参加工作时间
     */
	private java.util.Date firstWorkTime;
	/**
     * 工龄
     */
	private Double workedYear;
	/**
     * 现行政职务
     */
	@MustExist
	private String posit4nowCn;
	/**
     * 任职开始时间
     */
	private java.util.Date posit4nowStartTime;
	/**
     * 现行政级别年限
     */
	@MustExist
	private Double posit4nowYears;
	/**
     * 行政级别
     */
	private String positLevelCn;
	/**
     * 行政级别代码
     */
	private String positLevelCode;
	/**
     * 任正职时间
     */
	private java.util.Date onPrinPositStartTime;
	/**
     * 任正职年限
     */
	private Double onPrinPositYears;
	/**
     * 任副职时间
     */
	private java.util.Date onDeptPositStartTime;
	/**
     * 任副职年限
     */
	private Double onDeptPositYears;
	/**
     * 最高职称
     */
	@MustExist
	private String title4max;
	/**
     * 最高职称取得时间
     */
	private java.util.Date title4maxGotTime;
	/**
     * 最高职称取得年限
     */
	@MustExist
	private Double title4maxGotYears;
	/**
     * 职称序列
     */
	private String title4maxClassCn;
	/**
     * 职称级别
     */
	private String title4maxLevelCn;
	/**
     * 职称调资
     */
	private BigDecimal adjust4title;
	/**
     * 职务调资
     */
	private BigDecimal adjust4posit;
	/**
     * 学历调资
     */
	private BigDecimal adjust4edu;
	/**
     * 调整后工资标准
     */
	private BigDecimal wagesStandardAft;
	/**
     * 调资依据
     */
	private String adjustProof;
	/**
     * 调资差额
     */
	private BigDecimal adjustDiffe;
	/**
     * 公式ID
     */
	private Long formulaId;
	/**
     * 调资记录ID
     */
	private Long recordId;
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
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[卡号（身份）]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[卡号（身份）]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getWagesId(){
		return wagesId;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesId(String wagesId){
		this.wagesId = wagesId;
	}
	/**
	* <p>Discription:[工资账序号]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getWagesSeq(){
		return wagesSeq;
	}
	/**
	* <p>Discription:[工资账序号]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesSeq(String wagesSeq){
		this.wagesSeq = wagesSeq;
	}
	/**
	* <p>Discription:[工资账姓名]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getWagesName(){
		return wagesName;
	}
	/**
	* <p>Discription:[工资账姓名]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesName(String wagesName){
		this.wagesName = wagesName;
	}
	/**
	* <p>Discription:[工资账用工形式]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getWagesModalityCn(){
		return wagesModalityCn;
	}
	/**
	* <p>Discription:[工资账用工形式]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesModalityCn(String wagesModalityCn){
		this.wagesModalityCn = wagesModalityCn;
	}
	/**
	* <p>Discription:[工资账科室]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getWagesDepart(){
		return wagesDepart;
	}
	/**
	* <p>Discription:[工资账科室]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesDepart(String wagesDepart){
		this.wagesDepart = wagesDepart;
	}
	/**
	* <p>Discription:[原岗位工资标准]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWagesStandardBef(){
		return wagesStandardBef;
	}
	/**
	* <p>Discription:[原岗位工资标准]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesStandardBef(BigDecimal wagesStandardBef){
		this.wagesStandardBef = wagesStandardBef;
	}
	/**
	* <p>Discription:[调资类别]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getSalaryAdjustTypeCn(){
		return salaryAdjustTypeCn;
	}
	/**
	* <p>Discription:[调资类别]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setSalaryAdjustTypeCn(String salaryAdjustTypeCn){
		this.salaryAdjustTypeCn = salaryAdjustTypeCn;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getHosDepart1levelCn(){
		return hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setHosDepart1levelCn(String hosDepart1levelCn){
		this.hosDepart1levelCn = hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getHosDepart2levelCn(){
		return hosDepart2levelCn;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setHosDepart2levelCn(String hosDepart2levelCn){
		this.hosDepart2levelCn = hosDepart2levelCn;
	}
	/**
	* <p>Discription:[最高学历]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getEdu4max(){
		return edu4max;
	}
	/**
	* <p>Discription:[最高学历]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setEdu4max(String edu4max){
		this.edu4max = edu4max;
	}
	/**
	* <p>Discription:[第一学历]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getEdu4first(){
		return edu4first;
	}
	/**
	* <p>Discription:[第一学历]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setEdu4first(String edu4first){
		this.edu4first = edu4first;
	}
	/**
	* <p>Discription:[工资学历]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getEdu4wage(){
		return edu4wage;
	}
	/**
	* <p>Discription:[工资学历]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setEdu4wage(String edu4wage){
		this.edu4wage = edu4wage;
	}
	/**
	* <p>Discription:[首次参加工作时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getFirstWorkTime(){
		return firstWorkTime;
	}
	/**
	* <p>Discription:[首次参加工作时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFirstWorkTime(java.util.Date firstWorkTime){
		this.firstWorkTime = firstWorkTime;
	}
	/**
	* <p>Discription:[工龄]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Double getWorkedYear(){
		return workedYear;
	}
	/**
	* <p>Discription:[工龄]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWorkedYear(Double workedYear){
		this.workedYear = workedYear;
	}
	/**
	* <p>Discription:[现行政职务]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getPosit4nowCn(){
		return posit4nowCn;
	}
	/**
	* <p>Discription:[现行政职务]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setPosit4nowCn(String posit4nowCn){
		this.posit4nowCn = posit4nowCn;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getPosit4nowStartTime(){
		return posit4nowStartTime;
	}
	/**
	* <p>Discription:[任职开始时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setPosit4nowStartTime(java.util.Date posit4nowStartTime){
		this.posit4nowStartTime = posit4nowStartTime;
	}
	/**
	* <p>Discription:[现行政级别年限]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Double getPosit4nowYears(){
		return posit4nowYears;
	}
	/**
	* <p>Discription:[现行政级别年限]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setPosit4nowYears(Double posit4nowYears){
		this.posit4nowYears = posit4nowYears;
	}
	/**
	* <p>Discription:[行政级别]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getPositLevelCn(){
		return positLevelCn;
	}
	/**
	* <p>Discription:[行政级别]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setPositLevelCn(String positLevelCn){
		this.positLevelCn = positLevelCn;
	}
	/**
	* <p>Discription:[行政级别代码]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getPositLevelCode(){
		return positLevelCode;
	}
	/**
	* <p>Discription:[行政级别代码]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setPositLevelCode(String positLevelCode){
		this.positLevelCode = positLevelCode;
	}
	/**
	* <p>Discription:[任正职时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnPrinPositStartTime(){
		return onPrinPositStartTime;
	}
	/**
	* <p>Discription:[任正职时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setOnPrinPositStartTime(java.util.Date onPrinPositStartTime){
		this.onPrinPositStartTime = onPrinPositStartTime;
	}
	/**
	* <p>Discription:[任正职年限]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public Double getOnPrinPositYears(){
		return onPrinPositYears;
	}
	/**
	* <p>Discription:[任正职年限]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setOnPrinPositYears(Double onPrinPositYears){
		this.onPrinPositYears = onPrinPositYears;
	}
	/**
	* <p>Discription:[任副职时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getOnDeptPositStartTime(){
		return onDeptPositStartTime;
	}
	/**
	* <p>Discription:[任副职时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setOnDeptPositStartTime(java.util.Date onDeptPositStartTime){
		this.onDeptPositStartTime = onDeptPositStartTime;
	}
	/**
	* <p>Discription:[任副职年限]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Double getOnDeptPositYears(){
		return onDeptPositYears;
	}
	/**
	* <p>Discription:[任副职年限]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setOnDeptPositYears(Double onDeptPositYears){
		this.onDeptPositYears = onDeptPositYears;
	}
	/**
	* <p>Discription:[最高职称]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getTitle4max(){
		return title4max;
	}
	/**
	* <p>Discription:[最高职称]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTitle4max(String title4max){
		this.title4max = title4max;
	}
	/**
	* <p>Discription:[最高职称取得时间]</p>
	* Created on 2021年05月25日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getTitle4maxGotTime(){
		return title4maxGotTime;
	}
	/**
	* <p>Discription:[最高职称取得时间]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTitle4maxGotTime(java.util.Date title4maxGotTime){
		this.title4maxGotTime = title4maxGotTime;
	}
	/**
	* <p>Discription:[最高职称取得年限]</p>
	* Created on 2021年05月25日
	* @return Integer
    * @author:huadf
    */
	public Double getTitle4maxGotYears(){
		return title4maxGotYears;
	}
	/**
	* <p>Discription:[最高职称取得年限]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTitle4maxGotYears(Double title4maxGotYears){
		this.title4maxGotYears = title4maxGotYears;
	}
	/**
	* <p>Discription:[职称序列]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getTitle4maxClassCn(){
		return title4maxClassCn;
	}
	/**
	* <p>Discription:[职称序列]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTitle4maxClassCn(String title4maxClassCn){
		this.title4maxClassCn = title4maxClassCn;
	}
	/**
	* <p>Discription:[职称级别]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getTitle4maxLevelCn(){
		return title4maxLevelCn;
	}
	/**
	* <p>Discription:[职称级别]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setTitle4maxLevelCn(String title4maxLevelCn){
		this.title4maxLevelCn = title4maxLevelCn;
	}
	/**
	* <p>Discription:[职称调资]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getAdjust4title(){
		return adjust4title;
	}
	/**
	* <p>Discription:[职称调资]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjust4title(BigDecimal adjust4title){
		this.adjust4title = adjust4title;
	}
	/**
	* <p>Discription:[职务调资]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getAdjust4posit(){
		return adjust4posit;
	}
	/**
	* <p>Discription:[职务调资]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjust4posit(BigDecimal adjust4posit){
		this.adjust4posit = adjust4posit;
	}
	/**
	* <p>Discription:[学历调资]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getAdjust4edu(){
		return adjust4edu;
	}
	/**
	* <p>Discription:[学历调资]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjust4edu(BigDecimal adjust4edu){
		this.adjust4edu = adjust4edu;
	}
	/**
	* <p>Discription:[调整后工资标准]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWagesStandardAft(){
		return wagesStandardAft;
	}
	/**
	* <p>Discription:[调整后工资标准]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setWagesStandardAft(BigDecimal wagesStandardAft){
		this.wagesStandardAft = wagesStandardAft;
	}
	/**
	* <p>Discription:[调资依据]</p>
	* Created on 2021年05月25日
	* @return String
    * @author:huadf
    */
	public String getAdjustProof(){
		return adjustProof;
	}
	/**
	* <p>Discription:[调资依据]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjustProof(String adjustProof){
		this.adjustProof = adjustProof;
	}
	/**
	* <p>Discription:[调资差额]</p>
	* Created on 2021年05月25日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getAdjustDiffe(){
		return adjustDiffe;
	}
	/**
	* <p>Discription:[调资差额]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setAdjustDiffe(BigDecimal adjustDiffe){
		this.adjustDiffe = adjustDiffe;
	}
	/**
	* <p>Discription:[公式ID]</p>
	* Created on 2021年05月25日
	* @return Long
    * @author:huadf
    */
	public Long getFormulaId(){
		return formulaId;
	}
	/**
	* <p>Discription:[公式ID]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setFormulaId(Long formulaId){
		this.formulaId = formulaId;
	}
	/**
	* <p>Discription:[调资记录ID]</p>
	* Created on 2021年05月25日
	* @return Long
    * @author:huadf
    */
	public Long getRecordId(){
		return recordId;
	}
	/**
	* <p>Discription:[调资记录ID]</p>
	* Created on 2021年05月25日
	* @author:huadf
	*/
	public void setRecordId(Long recordId){
		this.recordId = recordId;
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
}
