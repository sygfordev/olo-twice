package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [薪资-工资单model]</p>
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSaPayslipModel implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号，导出时使用
	 */
	private Long serialNo;
	/**
     * 自增主键
     */
	private Long id;
	/**
     * 职工编号
     */
	private Long workerId;
	/**
     * 职工姓名
     */
	@MustExist
	private String name;
	/**
     * 身份证号
     */
	@MustExist
	private String cardNo;
	/**
     * 银行卡号
     */
	private String bankCardNo;
	/**
     * 手机号
     */
	private String mobileNo;
	/**
     * 用工形式(人员类别)
     */
	private Integer wkModality;
	/**
     * 用工形式(人员类别)名称
     */
	private String wkModalityCn;
	/**
     * 人员编号（工资出账）
     */
	private String wagesId;
	/**
     * 职务
     */
	private Integer posit;
	/**
     * 职务-名称
     */
	private String positCn;
	/**
     * 职称
     */
	private Integer title;
	/**
     * 职称-名称
     */
	private String titleCn;
	/**
     * 医院支部
     */
	private Integer hosBranch;
	/**
     * 医院支部-名称
     */
	private String hosBranchCn;
	/**
     * 医院一级科室
     */
	private Integer hosDepart1level;
	/**
     * 医院一级科室-名称
     */
	private String hosDepart1levelCn;
	/**
     * 医院二级科室
     */
	private Integer hosDepart2level;
	/**
     * 医院二级科室-名称
     */
	private String hosDepart2levelCn;
	/**
     * 部门类别
     */
	private String departClassCn;
	/**
     * 部门类别属性
     */
	private String departClassPop;
	/**
     * 岗位
     */
	private String stationCn;
	/**
     * 岗位类型
     */
	private String stationTypeCn;
	/**
     * 岗位状态
     */
	private String stationStatusCn;
	/**
     * 岗位序列
     */
	private String stationSeqCn;
	/**
     * 职称序列（职称分类）
     */
	private String titleClassCn;
	/**
     * 技能等级级别
     */
	private String skillsLevelCn;
	/**
     * 现学历
     */
	private String eduLev4nowCn;
	/**
     * 年薪制人员
     */
	private String yearlySalaryMan;
	/**
     * 工资汇总表项目
     */
	private String saSumProject;
	/**
     * 报工系统部门分类
     */
	private String rptWkDepClass;
	/**
     * 标准工数
     */
	private BigDecimal manwkStandard;
	/**
     * 出勤工数
     */
	private BigDecimal manwkAttend;
	/**
     * 旷工工数
     */
	private BigDecimal manwkMiner;
	/**
     * 离职工数
     */
	private BigDecimal manwkQuit;
	/**
     * 病假工数
     */
	private BigDecimal manwkSick;
	/**
     * 产假工数
     */
	private BigDecimal manwkMaternity;
	/**
     * 事假工数
     */
	private BigDecimal manwkPrivpassion;
	/**
     * 加班工数
     */
	private BigDecimal manwkOvertime;
	/**
     * 岗位工资标准
     */
	private BigDecimal wageStandard4posit;
	/**
     * 岗位日工资
     */
	private BigDecimal wageDay4posit;
	/**
     * 出勤工资
     */
	private BigDecimal wageAttend;
	/**
     * 病假工资
     */
	private BigDecimal wageSick;
	/**
     * 岗位工资合计
     */
	private BigDecimal wagePositTotal;
	/**
     * 夜班费用
     */
	private BigDecimal wageNightShift;
	/**
     * 加班工资
     */
	private BigDecimal wageOvertime;
	/**
     * 年功工资
     */
	private BigDecimal wageYearg;
	/**
     * 卫生津贴
     */
	private BigDecimal allowanceHygiene;
	/**
     * 纠错工资
     */
	private BigDecimal wageErrorCorrent;
	/**
     * 电补
     */
	private BigDecimal supp4tel;
	/**
     * 交通补贴
     */
	private BigDecimal supp4traffic;
	/**
     * 下矿（井、乡）补贴
     */
	private BigDecimal supp4mining;
	/**
     * 其他补贴
     */
	private BigDecimal supp4other;
	/**
     * 大学生生活补贴
     */
	private BigDecimal supp4univeStuLife;
	/**
     * 其他
     */
	private BigDecimal supp4oth;
	/**
     * 津补贴合计
     */
	private BigDecimal suppTotal;
	/**
     * 工资合计
     */
	private BigDecimal wageTotal;
	/**
     * 经合办绩效
     */
	private BigDecimal wageJxComb;
	/**
     * 经合办绩效1
     */
	private BigDecimal wageJxComb1;
	/**
     * 经合办绩效2
     */
	private BigDecimal wageJxComb2;
	/**
     * 经合办绩效3
     */
	private BigDecimal wageJxComb3;
	/**
     * 经合办绩效4
     */
	private BigDecimal wageJxComb4;
	/**
     * 基层分院绩效
     */
	private BigDecimal wageJxSubstrate;
	/**
     * 空调班绩效
     */
	private BigDecimal wageJxAircon;
	/**
     * 放射科介入
     */
	private BigDecimal wageJxRadiology;
	/**
     * 手术室介入
     */
	private BigDecimal wageJxOperatroom;
	/**
     * 年薪制人员绩效
     */
	private BigDecimal wageJxYearlySalary;
	/**
     * 分级诊疗办公室下乡补助
     */
	private BigDecimal supp4toCountryside;
	/**
     * 奖励1
     */
	private BigDecimal reward1;
	/**
     * 奖励2
     */
	private BigDecimal reward2;
	/**
     * 奖励3
     */
	private BigDecimal reward3;
	/**
     * 奖励4
     */
	private BigDecimal reward4;
	/**
     * 奖励5
     */
	private BigDecimal reward5;
	/**
     * 奖励6
     */
	private BigDecimal reward6;
	/**
     * 奖励7
     */
	private BigDecimal reward7;
	/**
     * 奖励8
     */
	private BigDecimal reward8;
	/**
     * 奖励9
     */
	private BigDecimal reward9;
	/**
     * 奖励10
     */
	private BigDecimal reward10;
	/**
     * 奖励11
     */
	private BigDecimal reward11;
	/**
     * 奖励12
     */
	private BigDecimal reward12;
	/**
     * 奖励13
     */
	private BigDecimal reward13;
	/**
     * 奖励14
     */
	private BigDecimal reward14;
	/**
     * 奖励15
     */
	private BigDecimal reward15;
	/**
     * 疗养院绩效
     */
	private BigDecimal wageJxSanatorium;
	/**
     * 绩效工资合计
     */
	private BigDecimal wageJxTotal;
	/**
     * 应发工资合计
     */
	private BigDecimal wagePayableTotal;
	/**
     * 养老保险
     */
	private BigDecimal bxPension;
	/**
     * 医疗保险
     */
	private BigDecimal bxMedical;
	/**
     * 失业保险
     */
	private BigDecimal bxUnemploy;
	/**
     * 大病保险
     */
	private BigDecimal bxSeriousIllness;
	/**
     * 住房公积金
     */
	private BigDecimal bxHousfund;
	/**
     * 年企业金
     */
	private BigDecimal bxAnnualCorpGold;
	/**
     * 保险合计
     */
	private BigDecimal bxTotal;
	/**
     * 水电费
     */
	private BigDecimal cutWater2elect;
	/**
     * 卫生费
     */
	private BigDecimal cutHygiene;

	/**
     * 其他扣款
     */
	private BigDecimal cutOther;
	/**
     * 职工欠款
     */
	private BigDecimal cutArrears;
	/**
     * 扣款合计
     */
	private BigDecimal cutTotal;
	/**
     * 计税工资
     */
	private BigDecimal taxableWage;
	/**
     * 个税
     */
	private BigDecimal taxIncomePersonal;
	/**
     * 实发工资
     */
	private BigDecimal netSalary;
	/**
     * 工资年月
     */
	private String netTargetYearmonth;
	/**
     * 状态 0：正常  1：异常
     */
	private Integer status;
	/**
     * 导入编号
     */
	private String btimpNo;
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
	
	private Map<String,Object> rewardMap = new HashMap<String,Object>();
	// setter and getter
	
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年05月20日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年05月20日
	* @return Long
    * @author:huadf
    */
	public Long getWorkerId(){
		return workerId;
	}
	/**
	* <p>Discription:[职工编号]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWorkerId(Long workerId){
		this.workerId = workerId;
	}
	/**
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[银行卡号]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getBankCardNo(){
		return bankCardNo;
	}
	/**
	* <p>Discription:[银行卡号]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBankCardNo(String bankCardNo){
		this.bankCardNo = bankCardNo;
	}
	/**
	* <p>Discription:[手机号]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getMobileNo(){
		return mobileNo;
	}
	/**
	* <p>Discription:[手机号]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setMobileNo(String mobileNo){
		this.mobileNo = mobileNo;
	}
	/**
	* <p>Discription:[用工形式(人员类别)]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getWkModality(){
		return wkModality;
	}
	/**
	* <p>Discription:[用工形式(人员类别)]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWkModality(Integer wkModality){
		this.wkModality = wkModality;
	}
	/**
	* <p>Discription:[用工形式(人员类别)名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getWkModalityCn(){
		return wkModalityCn;
	}
	/**
	* <p>Discription:[用工形式(人员类别)名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWkModalityCn(String wkModalityCn){
		this.wkModalityCn = wkModalityCn;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getWagesId(){
		return wagesId;
	}
	/**
	* <p>Discription:[人员编号（工资出账）]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWagesId(String wagesId){
		this.wagesId = wagesId;
	}
	/**
	* <p>Discription:[职务]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getPosit(){
		return posit;
	}
	/**
	* <p>Discription:[职务]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setPosit(Integer posit){
		this.posit = posit;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getPositCn(){
		return positCn;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setPositCn(String positCn){
		this.positCn = positCn;
	}
	/**
	* <p>Discription:[职称]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getTitle(){
		return title;
	}
	/**
	* <p>Discription:[职称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setTitle(Integer title){
		this.title = title;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getTitleCn(){
		return titleCn;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setTitleCn(String titleCn){
		this.titleCn = titleCn;
	}
	/**
	* <p>Discription:[医院支部]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosBranch(){
		return hosBranch;
	}
	/**
	* <p>Discription:[医院支部]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosBranch(Integer hosBranch){
		this.hosBranch = hosBranch;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getHosBranchCn(){
		return hosBranchCn;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosBranchCn(String hosBranchCn){
		this.hosBranchCn = hosBranchCn;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosDepart1level(){
		return hosDepart1level;
	}
	/**
	* <p>Discription:[医院一级科室]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosDepart1level(Integer hosDepart1level){
		this.hosDepart1level = hosDepart1level;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getHosDepart1levelCn(){
		return hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosDepart1levelCn(String hosDepart1levelCn){
		this.hosDepart1levelCn = hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getHosDepart2level(){
		return hosDepart2level;
	}
	/**
	* <p>Discription:[医院二级科室]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosDepart2level(Integer hosDepart2level){
		this.hosDepart2level = hosDepart2level;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getHosDepart2levelCn(){
		return hosDepart2levelCn;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setHosDepart2levelCn(String hosDepart2levelCn){
		this.hosDepart2levelCn = hosDepart2levelCn;
	}
	/**
	* <p>Discription:[部门类别]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getDepartClassCn(){
		return departClassCn;
	}
	/**
	* <p>Discription:[部门类别]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setDepartClassCn(String departClassCn){
		this.departClassCn = departClassCn;
	}
	/**
	* <p>Discription:[部门类别属性]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getDepartClassPop(){
		return departClassPop;
	}
	/**
	* <p>Discription:[部门类别属性]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setDepartClassPop(String departClassPop){
		this.departClassPop = departClassPop;
	}
	/**
	* <p>Discription:[岗位]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getStationCn(){
		return stationCn;
	}
	/**
	* <p>Discription:[岗位]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setStationCn(String stationCn){
		this.stationCn = stationCn;
	}
	/**
	* <p>Discription:[岗位类型]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getStationTypeCn(){
		return stationTypeCn;
	}
	/**
	* <p>Discription:[岗位类型]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setStationTypeCn(String stationTypeCn){
		this.stationTypeCn = stationTypeCn;
	}
	/**
	* <p>Discription:[岗位状态]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getStationStatusCn(){
		return stationStatusCn;
	}
	/**
	* <p>Discription:[岗位状态]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setStationStatusCn(String stationStatusCn){
		this.stationStatusCn = stationStatusCn;
	}
	/**
	* <p>Discription:[岗位序列]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getStationSeqCn(){
		return stationSeqCn;
	}
	/**
	* <p>Discription:[岗位序列]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setStationSeqCn(String stationSeqCn){
		this.stationSeqCn = stationSeqCn;
	}
	/**
	* <p>Discription:[职称序列（职称分类）]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getTitleClassCn(){
		return titleClassCn;
	}
	/**
	* <p>Discription:[职称序列（职称分类）]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setTitleClassCn(String titleClassCn){
		this.titleClassCn = titleClassCn;
	}
	/**
	* <p>Discription:[技能等级级别]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getSkillsLevelCn(){
		return skillsLevelCn;
	}
	/**
	* <p>Discription:[技能等级级别]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setSkillsLevelCn(String skillsLevelCn){
		this.skillsLevelCn = skillsLevelCn;
	}
	/**
	* <p>Discription:[现学历]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getEduLev4nowCn(){
		return eduLev4nowCn;
	}
	/**
	* <p>Discription:[现学历]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setEduLev4nowCn(String eduLev4nowCn){
		this.eduLev4nowCn = eduLev4nowCn;
	}
	/**
	* <p>Discription:[年薪制人员]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getYearlySalaryMan(){
		return yearlySalaryMan;
	}
	/**
	* <p>Discription:[年薪制人员]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setYearlySalaryMan(String yearlySalaryMan){
		this.yearlySalaryMan = yearlySalaryMan;
	}
	/**
	* <p>Discription:[工资汇总表项目]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getSaSumProject(){
		return saSumProject;
	}
	/**
	* <p>Discription:[工资汇总表项目]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setSaSumProject(String saSumProject){
		this.saSumProject = saSumProject;
	}
	/**
	* <p>Discription:[报工系统部门分类]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getRptWkDepClass(){
		return rptWkDepClass;
	}
	/**
	* <p>Discription:[报工系统部门分类]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setRptWkDepClass(String rptWkDepClass){
		this.rptWkDepClass = rptWkDepClass;
	}
	/**
	* <p>Discription:[标准工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkStandard(){
		return manwkStandard;
	}
	/**
	* <p>Discription:[标准工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkStandard(BigDecimal manwkStandard){
		this.manwkStandard = manwkStandard;
	}
	/**
	* <p>Discription:[出勤工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkAttend(){
		return manwkAttend;
	}
	/**
	* <p>Discription:[出勤工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkAttend(BigDecimal manwkAttend){
		this.manwkAttend = manwkAttend;
	}
	/**
	* <p>Discription:[矿工工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkMiner(){
		return manwkMiner;
	}
	/**
	* <p>Discription:[矿工工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkMiner(BigDecimal manwkMiner){
		this.manwkMiner = manwkMiner;
	}
	/**
	* <p>Discription:[病假工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkSick(){
		return manwkSick;
	}
	/**
	* <p>Discription:[病假工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkSick(BigDecimal manwkSick){
		this.manwkSick = manwkSick;
	}
	/**
	* <p>Discription:[产假工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkMaternity(){
		return manwkMaternity;
	}
	/**
	* <p>Discription:[产假工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkMaternity(BigDecimal manwkMaternity){
		this.manwkMaternity = manwkMaternity;
	}
	/**
	* <p>Discription:[事假工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkPrivpassion(){
		return manwkPrivpassion;
	}
	/**
	* <p>Discription:[事假工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkPrivpassion(BigDecimal manwkPrivpassion){
		this.manwkPrivpassion = manwkPrivpassion;
	}
	/**
	* <p>Discription:[加班工数]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getManwkOvertime(){
		return manwkOvertime;
	}
	/**
	* <p>Discription:[加班工数]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setManwkOvertime(BigDecimal manwkOvertime){
		this.manwkOvertime = manwkOvertime;
	}
	/**
	* <p>Discription:[岗位工资标准]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageStandard4posit(){
		return wageStandard4posit;
	}
	/**
	* <p>Discription:[岗位工资标准]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageStandard4posit(BigDecimal wageStandard4posit){
		this.wageStandard4posit = wageStandard4posit;
	}
	/**
	* <p>Discription:[岗位日工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageDay4posit(){
		return wageDay4posit;
	}
	/**
	* <p>Discription:[岗位日工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageDay4posit(BigDecimal wageDay4posit){
		this.wageDay4posit = wageDay4posit;
	}
	/**
	* <p>Discription:[出勤工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageAttend(){
		return wageAttend;
	}
	/**
	* <p>Discription:[出勤工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageAttend(BigDecimal wageAttend){
		this.wageAttend = wageAttend;
	}
	/**
	* <p>Discription:[病假工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageSick(){
		return wageSick;
	}
	/**
	* <p>Discription:[病假工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageSick(BigDecimal wageSick){
		this.wageSick = wageSick;
	}
	/**
	* <p>Discription:[岗位工资合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWagePositTotal(){
		return wagePositTotal;
	}
	/**
	* <p>Discription:[岗位工资合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWagePositTotal(BigDecimal wagePositTotal){
		this.wagePositTotal = wagePositTotal;
	}
	/**
	* <p>Discription:[夜班费用]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageNightShift(){
		return wageNightShift;
	}
	/**
	* <p>Discription:[夜班费用]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageNightShift(BigDecimal wageNightShift){
		this.wageNightShift = wageNightShift;
	}
	/**
	* <p>Discription:[加班工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageOvertime(){
		return wageOvertime;
	}
	/**
	* <p>Discription:[加班工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageOvertime(BigDecimal wageOvertime){
		this.wageOvertime = wageOvertime;
	}
	/**
	* <p>Discription:[年功工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageYearg(){
		return wageYearg;
	}
	/**
	* <p>Discription:[年功工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageYearg(BigDecimal wageYearg){
		this.wageYearg = wageYearg;
	}
	/**
	* <p>Discription:[卫生津贴]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getAllowanceHygiene(){
		return allowanceHygiene;
	}
	/**
	* <p>Discription:[卫生津贴]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setAllowanceHygiene(BigDecimal allowanceHygiene){
		this.allowanceHygiene = allowanceHygiene;
	}
	/**
	* <p>Discription:[纠错工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageErrorCorrent(){
		return wageErrorCorrent;
	}
	/**
	* <p>Discription:[纠错工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageErrorCorrent(BigDecimal wageErrorCorrent){
		this.wageErrorCorrent = wageErrorCorrent;
	}
	/**
	* <p>Discription:[电补]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4tel(){
		return supp4tel;
	}
	/**
	* <p>Discription:[电补]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setSupp4tel(BigDecimal supp4tel){
		this.supp4tel = supp4tel;
	}
	/**
	* <p>Discription:[交通补贴]</p>
	* Created on 2021年05月24日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4traffic(){
		return supp4traffic;
	}
	/**
	* <p>Discription:[交通补贴]</p>
	* Created on 2021年05月24日
	* @author:huadf
	*/
	public void setSupp4traffic(BigDecimal supp4traffic){
		this.supp4traffic = supp4traffic;
	}
	/**
	* <p>Discription:[下矿（井、乡）补贴]</p>
	* Created on 2021年05月24日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4mining(){
		return supp4mining;
	}
	/**
	* <p>Discription:[下矿（井、乡）补贴]</p>
	* Created on 2021年05月24日
	* @author:huadf
	*/
	public void setSupp4mining(BigDecimal supp4mining){
		this.supp4mining = supp4mining;
	}
	/**
	* <p>Discription:[其他补贴]</p>
	* Created on 2021年05月24日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4other(){
		return supp4other;
	}
	/**
	* <p>Discription:[其他补贴]</p>
	* Created on 2021年05月24日
	* @author:huadf
	*/
	public void setSupp4other(BigDecimal supp4other){
		this.supp4other = supp4other;
	}
	/**
	* <p>Discription:[大学生生活补贴]</p>
	* Created on 2021年05月24日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4univeStuLife(){
		return supp4univeStuLife;
	}
	/**
	* <p>Discription:[大学生生活补贴]</p>
	* Created on 2021年05月24日
	* @author:huadf
	*/
	public void setSupp4univeStuLife(BigDecimal supp4univeStuLife){
		this.supp4univeStuLife = supp4univeStuLife;
	}
	/**
	* <p>Discription:[其他]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4oth(){
		return supp4oth;
	}
	/**
	* <p>Discription:[其他]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setSupp4oth(BigDecimal supp4oth){
		this.supp4oth = supp4oth;
	}
	/**
	* <p>Discription:[津补贴合计]</p>
	* Created on 2021年05月26日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSuppTotal(){
		return suppTotal;
	}
	/**
	* <p>Discription:[津补贴合计]</p>
	* Created on 2021年05月26日
	* @author:huadf
	*/
	public void setSuppTotal(BigDecimal suppTotal){
		this.suppTotal = suppTotal;
	}
	/**
	* <p>Discription:[工资合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageTotal(){
		return wageTotal;
	}
	/**
	* <p>Discription:[工资合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageTotal(BigDecimal wageTotal){
		this.wageTotal = wageTotal;
	}
	/**
	* <p>Discription:[经合办绩效]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxComb(){
		return wageJxComb;
	}
	/**
	* <p>Discription:[经合办绩效]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxComb(BigDecimal wageJxComb){
		this.wageJxComb = wageJxComb;
	}
	/**
	* <p>Discription:[经合办绩效1]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxComb1(){
		return wageJxComb1;
	}
	/**
	* <p>Discription:[经合办绩效1]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxComb1(BigDecimal wageJxComb1){
		this.wageJxComb1 = wageJxComb1;
	}
	/**
	* <p>Discription:[经合办绩效2]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxComb2(){
		return wageJxComb2;
	}
	/**
	* <p>Discription:[经合办绩效2]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxComb2(BigDecimal wageJxComb2){
		this.wageJxComb2 = wageJxComb2;
	}
	/**
	* <p>Discription:[经合办绩效3]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxComb3(){
		return wageJxComb3;
	}
	/**
	* <p>Discription:[经合办绩效3]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxComb3(BigDecimal wageJxComb3){
		this.wageJxComb3 = wageJxComb3;
	}
	/**
	* <p>Discription:[经合办绩效4]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxComb4(){
		return wageJxComb4;
	}
	/**
	* <p>Discription:[经合办绩效4]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxComb4(BigDecimal wageJxComb4){
		this.wageJxComb4 = wageJxComb4;
	}
	/**
	* <p>Discription:[基层分院绩效]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxSubstrate(){
		return wageJxSubstrate;
	}
	/**
	* <p>Discription:[基层分院绩效]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxSubstrate(BigDecimal wageJxSubstrate){
		this.wageJxSubstrate = wageJxSubstrate;
	}
	/**
	* <p>Discription:[空调班绩效]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxAircon(){
		return wageJxAircon;
	}
	/**
	* <p>Discription:[空调班绩效]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxAircon(BigDecimal wageJxAircon){
		this.wageJxAircon = wageJxAircon;
	}
	/**
	* <p>Discription:[放射科介入]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxRadiology(){
		return wageJxRadiology;
	}
	/**
	* <p>Discription:[放射科介入]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxRadiology(BigDecimal wageJxRadiology){
		this.wageJxRadiology = wageJxRadiology;
	}
	/**
	* <p>Discription:[手术室介入]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxOperatroom(){
		return wageJxOperatroom;
	}
	/**
	* <p>Discription:[手术室介入]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxOperatroom(BigDecimal wageJxOperatroom){
		this.wageJxOperatroom = wageJxOperatroom;
	}
	/**
	* <p>Discription:[年薪制人员绩效]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxYearlySalary(){
		return wageJxYearlySalary;
	}
	/**
	* <p>Discription:[年薪制人员绩效]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxYearlySalary(BigDecimal wageJxYearlySalary){
		this.wageJxYearlySalary = wageJxYearlySalary;
	}
	/**
	* <p>Discription:[分级诊疗办公室下乡补助]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSupp4toCountryside(){
		return supp4toCountryside;
	}
	/**
	* <p>Discription:[分级诊疗办公室下乡补助]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setSupp4toCountryside(BigDecimal supp4toCountryside){
		this.supp4toCountryside = supp4toCountryside;
	}
	/**
	* <p>Discription:[奖励1]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward1(){
		return reward1;
	}
	/**
	* <p>Discription:[奖励1]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward1(BigDecimal reward1){
		this.reward1 = reward1;
	}
	/**
	* <p>Discription:[奖励2]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward2(){
		return reward2;
	}
	/**
	* <p>Discription:[奖励2]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward2(BigDecimal reward2){
		this.reward2 = reward2;
	}
	/**
	* <p>Discription:[奖励3]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward3(){
		return reward3;
	}
	/**
	* <p>Discription:[奖励3]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward3(BigDecimal reward3){
		this.reward3 = reward3;
	}
	/**
	* <p>Discription:[奖励4]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward4(){
		return reward4;
	}
	/**
	* <p>Discription:[奖励4]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward4(BigDecimal reward4){
		this.reward4 = reward4;
	}
	/**
	* <p>Discription:[奖励5]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward5(){
		return reward5;
	}
	/**
	* <p>Discription:[奖励5]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward5(BigDecimal reward5){
		this.reward5 = reward5;
	}
	/**
	* <p>Discription:[奖励6]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward6(){
		return reward6;
	}
	/**
	* <p>Discription:[奖励6]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward6(BigDecimal reward6){
		this.reward6 = reward6;
	}
	/**
	* <p>Discription:[奖励7]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward7(){
		return reward7;
	}
	/**
	* <p>Discription:[奖励7]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward7(BigDecimal reward7){
		this.reward7 = reward7;
	}
	/**
	* <p>Discription:[奖励8]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward8(){
		return reward8;
	}
	/**
	* <p>Discription:[奖励8]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward8(BigDecimal reward8){
		this.reward8 = reward8;
	}
	/**
	* <p>Discription:[奖励9]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward9(){
		return reward9;
	}
	/**
	* <p>Discription:[奖励9]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward9(BigDecimal reward9){
		this.reward9 = reward9;
	}
	/**
	* <p>Discription:[奖励10]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward10(){
		return reward10;
	}
	/**
	* <p>Discription:[奖励10]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward10(BigDecimal reward10){
		this.reward10 = reward10;
	}
	/**
	* <p>Discription:[奖励11]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward11(){
		return reward11;
	}
	/**
	* <p>Discription:[奖励11]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward11(BigDecimal reward11){
		this.reward11 = reward11;
	}
	/**
	* <p>Discription:[奖励12]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward12(){
		return reward12;
	}
	/**
	* <p>Discription:[奖励12]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward12(BigDecimal reward12){
		this.reward12 = reward12;
	}
	/**
	* <p>Discription:[奖励13]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward13(){
		return reward13;
	}
	/**
	* <p>Discription:[奖励13]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward13(BigDecimal reward13){
		this.reward13 = reward13;
	}
	/**
	* <p>Discription:[奖励14]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward14(){
		return reward14;
	}
	/**
	* <p>Discription:[奖励14]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward14(BigDecimal reward14){
		this.reward14 = reward14;
	}
	/**
	* <p>Discription:[奖励15]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getReward15(){
		return reward15;
	}
	/**
	* <p>Discription:[奖励15]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setReward15(BigDecimal reward15){
		this.reward15 = reward15;
	}
	/**
	* <p>Discription:[疗养院绩效]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxSanatorium(){
		return wageJxSanatorium;
	}
	/**
	* <p>Discription:[疗养院绩效]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxSanatorium(BigDecimal wageJxSanatorium){
		this.wageJxSanatorium = wageJxSanatorium;
	}
	/**
	* <p>Discription:[绩效工资合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWageJxTotal(){
		return wageJxTotal;
	}
	/**
	* <p>Discription:[绩效工资合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWageJxTotal(BigDecimal wageJxTotal){
		this.wageJxTotal = wageJxTotal;
	}
	/**
	* <p>Discription:[应发工资合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getWagePayableTotal(){
		return wagePayableTotal;
	}
	/**
	* <p>Discription:[应发工资合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setWagePayableTotal(BigDecimal wagePayableTotal){
		this.wagePayableTotal = wagePayableTotal;
	}
	/**
	* <p>Discription:[养老保险]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxPension(){
		return bxPension;
	}
	/**
	* <p>Discription:[养老保险]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxPension(BigDecimal bxPension){
		this.bxPension = bxPension;
	}
	/**
	* <p>Discription:[医疗保险]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxMedical(){
		return bxMedical;
	}
	/**
	* <p>Discription:[医疗保险]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxMedical(BigDecimal bxMedical){
		this.bxMedical = bxMedical;
	}
	/**
	* <p>Discription:[失业保险]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxUnemploy(){
		return bxUnemploy;
	}
	/**
	* <p>Discription:[失业保险]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxUnemploy(BigDecimal bxUnemploy){
		this.bxUnemploy = bxUnemploy;
	}
	/**
	* <p>Discription:[大病保险]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxSeriousIllness(){
		return bxSeriousIllness;
	}
	/**
	* <p>Discription:[大病保险]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxSeriousIllness(BigDecimal bxSeriousIllness){
		this.bxSeriousIllness = bxSeriousIllness;
	}
	/**
	* <p>Discription:[住房公积金]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxHousfund(){
		return bxHousfund;
	}
	/**
	* <p>Discription:[住房公积金]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxHousfund(BigDecimal bxHousfund){
		this.bxHousfund = bxHousfund;
	}
	/**
	* <p>Discription:[年企业金]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxAnnualCorpGold(){
		return bxAnnualCorpGold;
	}
	/**
	* <p>Discription:[年企业金]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxAnnualCorpGold(BigDecimal bxAnnualCorpGold){
		this.bxAnnualCorpGold = bxAnnualCorpGold;
	}
	/**
	* <p>Discription:[保险合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getBxTotal(){
		return bxTotal;
	}
	/**
	* <p>Discription:[保险合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBxTotal(BigDecimal bxTotal){
		this.bxTotal = bxTotal;
	}
	/**
	* <p>Discription:[水电费]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getCutWater2elect(){
		return cutWater2elect;
	}
	/**
	* <p>Discription:[水电费]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCutWater2elect(BigDecimal cutWater2elect){
		this.cutWater2elect = cutWater2elect;
	}
	/**
	* <p>Discription:[卫生费]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getCutHygiene(){
		return cutHygiene;
	}
	/**
	* <p>Discription:[卫生费]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCutHygiene(BigDecimal cutHygiene){
		this.cutHygiene = cutHygiene;
	}
	/**
	* <p>Discription:[职工欠款]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getCutArrears(){
		return cutArrears;
	}
	/**
	* <p>Discription:[职工欠款]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCutArrears(BigDecimal cutArrears){
		this.cutArrears = cutArrears;
	}
	/**
	* <p>Discription:[扣款合计]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getCutTotal(){
		return cutTotal;
	}
	/**
	* <p>Discription:[扣款合计]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCutTotal(BigDecimal cutTotal){
		this.cutTotal = cutTotal;
	}
	/**
	* <p>Discription:[计税工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getTaxableWage(){
		return taxableWage;
	}
	/**
	* <p>Discription:[计税工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setTaxableWage(BigDecimal taxableWage){
		this.taxableWage = taxableWage;
	}
	/**
	* <p>Discription:[个税]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getTaxIncomePersonal(){
		return taxIncomePersonal;
	}
	/**
	* <p>Discription:[个税]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setTaxIncomePersonal(BigDecimal taxIncomePersonal){
		this.taxIncomePersonal = taxIncomePersonal;
	}
	/**
	* <p>Discription:[实发工资]</p>
	* Created on 2021年05月20日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getNetSalary(){
		return netSalary;
	}
	/**
	* <p>Discription:[实发工资]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setNetSalary(BigDecimal netSalary){
		this.netSalary = netSalary;
	}
	/**
	* <p>Discription:[工资年月]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getNetTargetYearmonth(){
		return netTargetYearmonth;
	}
	/**
	* <p>Discription:[工资年月]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setNetTargetYearmonth(String netTargetYearmonth){
		this.netTargetYearmonth = netTargetYearmonth;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年05月20日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[导入编号]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getBtimpNo(){
		return btimpNo;
	}
	/**
	* <p>Discription:[导入编号]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setBtimpNo(String btimpNo){
		this.btimpNo = btimpNo;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月20日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月20日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月20日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年05月20日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public Map<String, Object> getRewardMap() {
		return rewardMap;
	}
	public void setRewardMap(Map<String, Object> rewardMap) {
		this.rewardMap = rewardMap;
	}
	public BigDecimal getManwkQuit() {
		return manwkQuit;
	}
	public void setManwkQuit(BigDecimal manwkQuit) {
		this.manwkQuit = manwkQuit;
	}
	public BigDecimal getCutOther() {
		return cutOther;
	}
	public void setCutOther(BigDecimal cutOther) {
		this.cutOther = cutOther;
	}
}
