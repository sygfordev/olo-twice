package com.hdfs.olo.olo_web.social.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
/** 
 * <p>Description: [社保信息model]</p>
 * Created on 2021年06月07日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public class ChSocialInfoModel implements Serializable {

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
     * 姓名
     */
	@MustExist
	private String name;
	/**
     * 身份证号
     */
	@MustExist
	private String cardNo;
	/**
     * 单位名称
     */
	private String compName;
	/**
     * 用工形式(人员类别)名称
     */
	private String wkModalityCn;
	/**
     * 工作地域-名称
     */
	private String workAreaCn;
	/**
     * 职务-名称
     */
	private String positCn;
	/**
     * 职称-名称
     */
	private String titleCn;
	/**
     * 医院支部-名称
     */
	private String hosBranchCn;
	/**
     * 医院一级科室-名称
     */
	private String hosDepart1levelCn;
	/**
     * 医院二级科室-名称
     */
	private String hosDepart2levelCn;
	/**
     * 社保年月
     */
	private String socialYmonth;
	/**
     * 变动时间
     */
	private String changeTime;
	/**
	 * 是否存在补缴
	 */
	private Integer isExistSupple;
	/**
     * 基数
     */
	private BigDecimal sPenBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sPenCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sPenCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sPenCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sPenCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sPenCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sPenPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sPenPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sPenPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sPenPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sPenPersComple;
	/**
     * 基数
     */
	private BigDecimal sMediBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sMediCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sMediCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sMediCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sMediCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sMediCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sMediPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sMediPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sMediPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sMediPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sMediPersComple;
	/**
     * 基数
     */
	private BigDecimal sUnempBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sUnempCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sUnempCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sUnempCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sUnempCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sUnempCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sUnempPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sUnempPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sUnempPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sUnempPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sUnempPersComple;
	/**
     * 基数
     */
	private BigDecimal sInjuryBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sInjuryCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sInjuryCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sInjuryCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sInjuryCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sInjuryCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sInjuryPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sInjuryPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sInjuryPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sInjuryPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sInjuryPersComple;
	/**
     * 基数
     */
	private BigDecimal sBirthBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sBirthCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sBirthCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sBirthCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sBirthCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sBirthCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sBirthPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sBirthPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sBirthPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sBirthPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sBirthPersComple;
	/**
     * 基数
     */
	private BigDecimal sAnnuityBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sAnnuityCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sAnnuityCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sAnnuityCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sAnnuityCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sAnnuityCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sAnnuityPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sAnnuityPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sAnnuityPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sAnnuityPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sAnnuityPersComple;
	/**
     * 基数
     */
	private BigDecimal sOvpBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sOvpCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sOvpCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sOvpCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sOvpCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sOvpCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sOvpPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sOvpPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sOvpPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sOvpPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sOvpPersComple;
	/**
     * 基数
     */
	private BigDecimal sSpMediBase;
	/**
     * 单位应缴比例
     */
	private BigDecimal sSpMediCompRatio;
	/**
     * 单位应缴金额
     */
	private BigDecimal sSpMediCompAmount;
	/**
     * 单位实缴金额
     */
	private BigDecimal sSpMediCompRecapAmount;
	/**
     * 单位实缴差额
     */
	private BigDecimal sSpMediCompRecapDiffe;
	/**
     * 单位多缴
     */
	private BigDecimal sSpMediCompOverpaid;
	/**
     * 个人应缴比例
     */
	private BigDecimal sSpMediPersRatio;
	/**
     * 个人应缴金额
     */
	private BigDecimal sSpMediPersAmount;
	/**
     * 个人实缴金额
     */
	private BigDecimal sSpMediPersRecapAmount;
	/**
     * 个人实缴差额
     */
	private BigDecimal sSpMediPersRecapDiffe;
	/**
     * 个人补缴
     */
	private BigDecimal sSpMediPersComple;
	/**
     * 月备注
     */
	private String remark4month;
	/**
     * 年备注
     */
	private String remark4year;
	/**
     * 本年个人实缴累计差额
     */
	private BigDecimal persRecapDiffe4addup;
	/**
     * 本年个人补缴累计差额
     */
	private BigDecimal persCompleDiffe4addup;
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
	
	// setter and getter
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年06月07日
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
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[姓名]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[姓名]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[身份证号]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[单位名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getCompName(){
		return compName;
	}
	/**
	* <p>Discription:[单位名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setCompName(String compName){
		this.compName = compName;
	}
	/**
	* <p>Discription:[用工形式(人员类别)名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getWkModalityCn(){
		return wkModalityCn;
	}
	/**
	* <p>Discription:[用工形式(人员类别)名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setWkModalityCn(String wkModalityCn){
		this.wkModalityCn = wkModalityCn;
	}
	/**
	* <p>Discription:[工作地域-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getWorkAreaCn(){
		return workAreaCn;
	}
	/**
	* <p>Discription:[工作地域-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setWorkAreaCn(String workAreaCn){
		this.workAreaCn = workAreaCn;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getPositCn(){
		return positCn;
	}
	/**
	* <p>Discription:[职务-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setPositCn(String positCn){
		this.positCn = positCn;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getTitleCn(){
		return titleCn;
	}
	/**
	* <p>Discription:[职称-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setTitleCn(String titleCn){
		this.titleCn = titleCn;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getHosBranchCn(){
		return hosBranchCn;
	}
	/**
	* <p>Discription:[医院支部-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setHosBranchCn(String hosBranchCn){
		this.hosBranchCn = hosBranchCn;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getHosDepart1levelCn(){
		return hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院一级科室-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setHosDepart1levelCn(String hosDepart1levelCn){
		this.hosDepart1levelCn = hosDepart1levelCn;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getHosDepart2levelCn(){
		return hosDepart2levelCn;
	}
	/**
	* <p>Discription:[医院二级科室-名称]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setHosDepart2levelCn(String hosDepart2levelCn){
		this.hosDepart2levelCn = hosDepart2levelCn;
	}
	/**
	* <p>Discription:[社保年月]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getSocialYmonth(){
		return socialYmonth;
	}
	/**
	* <p>Discription:[社保年月]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSocialYmonth(String socialYmonth){
		this.socialYmonth = socialYmonth;
	}
	public String getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenBase(){
		return sPenBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenBase(BigDecimal sPenBase){
		this.sPenBase = sPenBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenCompRatio(){
		return sPenCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenCompRatio(BigDecimal sPenCompRatio){
		this.sPenCompRatio = sPenCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenCompAmount(){
		return sPenCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenCompAmount(BigDecimal sPenCompAmount){
		this.sPenCompAmount = sPenCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenCompRecapAmount(){
		return sPenCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenCompRecapAmount(BigDecimal sPenCompRecapAmount){
		this.sPenCompRecapAmount = sPenCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenCompRecapDiffe(){
		return sPenCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenCompRecapDiffe(BigDecimal sPenCompRecapDiffe){
		this.sPenCompRecapDiffe = sPenCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenCompOverpaid(){
		return sPenCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenCompOverpaid(BigDecimal sPenCompOverpaid){
		this.sPenCompOverpaid = sPenCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenPersRatio(){
		return sPenPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenPersRatio(BigDecimal sPenPersRatio){
		this.sPenPersRatio = sPenPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenPersAmount(){
		return sPenPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenPersAmount(BigDecimal sPenPersAmount){
		this.sPenPersAmount = sPenPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenPersRecapAmount(){
		return sPenPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenPersRecapAmount(BigDecimal sPenPersRecapAmount){
		this.sPenPersRecapAmount = sPenPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenPersRecapDiffe(){
		return sPenPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenPersRecapDiffe(BigDecimal sPenPersRecapDiffe){
		this.sPenPersRecapDiffe = sPenPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSPenPersComple(){
		return sPenPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSPenPersComple(BigDecimal sPenPersComple){
		this.sPenPersComple = sPenPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediBase(){
		return sMediBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediBase(BigDecimal sMediBase){
		this.sMediBase = sMediBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediCompRatio(){
		return sMediCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediCompRatio(BigDecimal sMediCompRatio){
		this.sMediCompRatio = sMediCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediCompAmount(){
		return sMediCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediCompAmount(BigDecimal sMediCompAmount){
		this.sMediCompAmount = sMediCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediCompRecapAmount(){
		return sMediCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediCompRecapAmount(BigDecimal sMediCompRecapAmount){
		this.sMediCompRecapAmount = sMediCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediCompRecapDiffe(){
		return sMediCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediCompRecapDiffe(BigDecimal sMediCompRecapDiffe){
		this.sMediCompRecapDiffe = sMediCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediCompOverpaid(){
		return sMediCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediCompOverpaid(BigDecimal sMediCompOverpaid){
		this.sMediCompOverpaid = sMediCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediPersRatio(){
		return sMediPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediPersRatio(BigDecimal sMediPersRatio){
		this.sMediPersRatio = sMediPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediPersAmount(){
		return sMediPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediPersAmount(BigDecimal sMediPersAmount){
		this.sMediPersAmount = sMediPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediPersRecapAmount(){
		return sMediPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediPersRecapAmount(BigDecimal sMediPersRecapAmount){
		this.sMediPersRecapAmount = sMediPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediPersRecapDiffe(){
		return sMediPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediPersRecapDiffe(BigDecimal sMediPersRecapDiffe){
		this.sMediPersRecapDiffe = sMediPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSMediPersComple(){
		return sMediPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSMediPersComple(BigDecimal sMediPersComple){
		this.sMediPersComple = sMediPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempBase(){
		return sUnempBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempBase(BigDecimal sUnempBase){
		this.sUnempBase = sUnempBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempCompRatio(){
		return sUnempCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempCompRatio(BigDecimal sUnempCompRatio){
		this.sUnempCompRatio = sUnempCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempCompAmount(){
		return sUnempCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempCompAmount(BigDecimal sUnempCompAmount){
		this.sUnempCompAmount = sUnempCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempCompRecapAmount(){
		return sUnempCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempCompRecapAmount(BigDecimal sUnempCompRecapAmount){
		this.sUnempCompRecapAmount = sUnempCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempCompRecapDiffe(){
		return sUnempCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempCompRecapDiffe(BigDecimal sUnempCompRecapDiffe){
		this.sUnempCompRecapDiffe = sUnempCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempCompOverpaid(){
		return sUnempCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempCompOverpaid(BigDecimal sUnempCompOverpaid){
		this.sUnempCompOverpaid = sUnempCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempPersRatio(){
		return sUnempPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempPersRatio(BigDecimal sUnempPersRatio){
		this.sUnempPersRatio = sUnempPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempPersAmount(){
		return sUnempPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempPersAmount(BigDecimal sUnempPersAmount){
		this.sUnempPersAmount = sUnempPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempPersRecapAmount(){
		return sUnempPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempPersRecapAmount(BigDecimal sUnempPersRecapAmount){
		this.sUnempPersRecapAmount = sUnempPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempPersRecapDiffe(){
		return sUnempPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempPersRecapDiffe(BigDecimal sUnempPersRecapDiffe){
		this.sUnempPersRecapDiffe = sUnempPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSUnempPersComple(){
		return sUnempPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSUnempPersComple(BigDecimal sUnempPersComple){
		this.sUnempPersComple = sUnempPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryBase(){
		return sInjuryBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryBase(BigDecimal sInjuryBase){
		this.sInjuryBase = sInjuryBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryCompRatio(){
		return sInjuryCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryCompRatio(BigDecimal sInjuryCompRatio){
		this.sInjuryCompRatio = sInjuryCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryCompAmount(){
		return sInjuryCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryCompAmount(BigDecimal sInjuryCompAmount){
		this.sInjuryCompAmount = sInjuryCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryCompRecapAmount(){
		return sInjuryCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryCompRecapAmount(BigDecimal sInjuryCompRecapAmount){
		this.sInjuryCompRecapAmount = sInjuryCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryCompRecapDiffe(){
		return sInjuryCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryCompRecapDiffe(BigDecimal sInjuryCompRecapDiffe){
		this.sInjuryCompRecapDiffe = sInjuryCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryCompOverpaid(){
		return sInjuryCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryCompOverpaid(BigDecimal sInjuryCompOverpaid){
		this.sInjuryCompOverpaid = sInjuryCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryPersRatio(){
		return sInjuryPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryPersRatio(BigDecimal sInjuryPersRatio){
		this.sInjuryPersRatio = sInjuryPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryPersAmount(){
		return sInjuryPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryPersAmount(BigDecimal sInjuryPersAmount){
		this.sInjuryPersAmount = sInjuryPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryPersRecapAmount(){
		return sInjuryPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryPersRecapAmount(BigDecimal sInjuryPersRecapAmount){
		this.sInjuryPersRecapAmount = sInjuryPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryPersRecapDiffe(){
		return sInjuryPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryPersRecapDiffe(BigDecimal sInjuryPersRecapDiffe){
		this.sInjuryPersRecapDiffe = sInjuryPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSInjuryPersComple(){
		return sInjuryPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSInjuryPersComple(BigDecimal sInjuryPersComple){
		this.sInjuryPersComple = sInjuryPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthBase(){
		return sBirthBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthBase(BigDecimal sBirthBase){
		this.sBirthBase = sBirthBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthCompRatio(){
		return sBirthCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthCompRatio(BigDecimal sBirthCompRatio){
		this.sBirthCompRatio = sBirthCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthCompAmount(){
		return sBirthCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthCompAmount(BigDecimal sBirthCompAmount){
		this.sBirthCompAmount = sBirthCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthCompRecapAmount(){
		return sBirthCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthCompRecapAmount(BigDecimal sBirthCompRecapAmount){
		this.sBirthCompRecapAmount = sBirthCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthCompRecapDiffe(){
		return sBirthCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthCompRecapDiffe(BigDecimal sBirthCompRecapDiffe){
		this.sBirthCompRecapDiffe = sBirthCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthCompOverpaid(){
		return sBirthCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthCompOverpaid(BigDecimal sBirthCompOverpaid){
		this.sBirthCompOverpaid = sBirthCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthPersRatio(){
		return sBirthPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthPersRatio(BigDecimal sBirthPersRatio){
		this.sBirthPersRatio = sBirthPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthPersAmount(){
		return sBirthPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthPersAmount(BigDecimal sBirthPersAmount){
		this.sBirthPersAmount = sBirthPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthPersRecapAmount(){
		return sBirthPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthPersRecapAmount(BigDecimal sBirthPersRecapAmount){
		this.sBirthPersRecapAmount = sBirthPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthPersRecapDiffe(){
		return sBirthPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthPersRecapDiffe(BigDecimal sBirthPersRecapDiffe){
		this.sBirthPersRecapDiffe = sBirthPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSBirthPersComple(){
		return sBirthPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSBirthPersComple(BigDecimal sBirthPersComple){
		this.sBirthPersComple = sBirthPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityBase(){
		return sAnnuityBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityBase(BigDecimal sAnnuityBase){
		this.sAnnuityBase = sAnnuityBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityCompRatio(){
		return sAnnuityCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityCompRatio(BigDecimal sAnnuityCompRatio){
		this.sAnnuityCompRatio = sAnnuityCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityCompAmount(){
		return sAnnuityCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityCompAmount(BigDecimal sAnnuityCompAmount){
		this.sAnnuityCompAmount = sAnnuityCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityCompRecapAmount(){
		return sAnnuityCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityCompRecapAmount(BigDecimal sAnnuityCompRecapAmount){
		this.sAnnuityCompRecapAmount = sAnnuityCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityCompRecapDiffe(){
		return sAnnuityCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityCompRecapDiffe(BigDecimal sAnnuityCompRecapDiffe){
		this.sAnnuityCompRecapDiffe = sAnnuityCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityCompOverpaid(){
		return sAnnuityCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityCompOverpaid(BigDecimal sAnnuityCompOverpaid){
		this.sAnnuityCompOverpaid = sAnnuityCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityPersRatio(){
		return sAnnuityPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityPersRatio(BigDecimal sAnnuityPersRatio){
		this.sAnnuityPersRatio = sAnnuityPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityPersAmount(){
		return sAnnuityPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityPersAmount(BigDecimal sAnnuityPersAmount){
		this.sAnnuityPersAmount = sAnnuityPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityPersRecapAmount(){
		return sAnnuityPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityPersRecapAmount(BigDecimal sAnnuityPersRecapAmount){
		this.sAnnuityPersRecapAmount = sAnnuityPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityPersRecapDiffe(){
		return sAnnuityPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityPersRecapDiffe(BigDecimal sAnnuityPersRecapDiffe){
		this.sAnnuityPersRecapDiffe = sAnnuityPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSAnnuityPersComple(){
		return sAnnuityPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSAnnuityPersComple(BigDecimal sAnnuityPersComple){
		this.sAnnuityPersComple = sAnnuityPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpBase(){
		return sOvpBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpBase(BigDecimal sOvpBase){
		this.sOvpBase = sOvpBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpCompRatio(){
		return sOvpCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpCompRatio(BigDecimal sOvpCompRatio){
		this.sOvpCompRatio = sOvpCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpCompAmount(){
		return sOvpCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpCompAmount(BigDecimal sOvpCompAmount){
		this.sOvpCompAmount = sOvpCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpCompRecapAmount(){
		return sOvpCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpCompRecapAmount(BigDecimal sOvpCompRecapAmount){
		this.sOvpCompRecapAmount = sOvpCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpCompRecapDiffe(){
		return sOvpCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpCompRecapDiffe(BigDecimal sOvpCompRecapDiffe){
		this.sOvpCompRecapDiffe = sOvpCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpCompOverpaid(){
		return sOvpCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpCompOverpaid(BigDecimal sOvpCompOverpaid){
		this.sOvpCompOverpaid = sOvpCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpPersRatio(){
		return sOvpPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpPersRatio(BigDecimal sOvpPersRatio){
		this.sOvpPersRatio = sOvpPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpPersAmount(){
		return sOvpPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpPersAmount(BigDecimal sOvpPersAmount){
		this.sOvpPersAmount = sOvpPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpPersRecapAmount(){
		return sOvpPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpPersRecapAmount(BigDecimal sOvpPersRecapAmount){
		this.sOvpPersRecapAmount = sOvpPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpPersRecapDiffe(){
		return sOvpPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpPersRecapDiffe(BigDecimal sOvpPersRecapDiffe){
		this.sOvpPersRecapDiffe = sOvpPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSOvpPersComple(){
		return sOvpPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSOvpPersComple(BigDecimal sOvpPersComple){
		this.sOvpPersComple = sOvpPersComple;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediBase(){
		return sSpMediBase;
	}
	/**
	* <p>Discription:[基数]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediBase(BigDecimal sSpMediBase){
		this.sSpMediBase = sSpMediBase;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediCompRatio(){
		return sSpMediCompRatio;
	}
	/**
	* <p>Discription:[单位应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediCompRatio(BigDecimal sSpMediCompRatio){
		this.sSpMediCompRatio = sSpMediCompRatio;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediCompAmount(){
		return sSpMediCompAmount;
	}
	/**
	* <p>Discription:[单位应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediCompAmount(BigDecimal sSpMediCompAmount){
		this.sSpMediCompAmount = sSpMediCompAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediCompRecapAmount(){
		return sSpMediCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediCompRecapAmount(BigDecimal sSpMediCompRecapAmount){
		this.sSpMediCompRecapAmount = sSpMediCompRecapAmount;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediCompRecapDiffe(){
		return sSpMediCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediCompRecapDiffe(BigDecimal sSpMediCompRecapDiffe){
		this.sSpMediCompRecapDiffe = sSpMediCompRecapDiffe;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediCompOverpaid(){
		return sSpMediCompOverpaid;
	}
	/**
	* <p>Discription:[单位多缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediCompOverpaid(BigDecimal sSpMediCompOverpaid){
		this.sSpMediCompOverpaid = sSpMediCompOverpaid;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediPersRatio(){
		return sSpMediPersRatio;
	}
	/**
	* <p>Discription:[个人应缴比例]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediPersRatio(BigDecimal sSpMediPersRatio){
		this.sSpMediPersRatio = sSpMediPersRatio;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediPersAmount(){
		return sSpMediPersAmount;
	}
	/**
	* <p>Discription:[个人应缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediPersAmount(BigDecimal sSpMediPersAmount){
		this.sSpMediPersAmount = sSpMediPersAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediPersRecapAmount(){
		return sSpMediPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴金额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediPersRecapAmount(BigDecimal sSpMediPersRecapAmount){
		this.sSpMediPersRecapAmount = sSpMediPersRecapAmount;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediPersRecapDiffe(){
		return sSpMediPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人实缴差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediPersRecapDiffe(BigDecimal sSpMediPersRecapDiffe){
		this.sSpMediPersRecapDiffe = sSpMediPersRecapDiffe;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getSSpMediPersComple(){
		return sSpMediPersComple;
	}
	/**
	* <p>Discription:[个人补缴]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setSSpMediPersComple(BigDecimal sSpMediPersComple){
		this.sSpMediPersComple = sSpMediPersComple;
	}
	/**
	* <p>Discription:[月备注]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getRemark4month(){
		return remark4month;
	}
	/**
	* <p>Discription:[月备注]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setRemark4month(String remark4month){
		this.remark4month = remark4month;
	}
	/**
	* <p>Discription:[年备注]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getRemark4year(){
		return remark4year;
	}
	/**
	* <p>Discription:[年备注]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setRemark4year(String remark4year){
		this.remark4year = remark4year;
	}
	/**
	* <p>Discription:[本年个人实缴累计差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getPersRecapDiffe4addup(){
		return persRecapDiffe4addup;
	}
	/**
	* <p>Discription:[本年个人实缴累计差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setPersRecapDiffe4addup(BigDecimal persRecapDiffe4addup){
		this.persRecapDiffe4addup = persRecapDiffe4addup;
	}
	/**
	* <p>Discription:[本年个人实缴累计差额]</p>
	* Created on 2021年06月07日
	* @return BigDecimal
    * @author:huadf
    */
	public BigDecimal getPersCompleDiffe4addup(){
		return persCompleDiffe4addup;
	}
	/**
	* <p>Discription:[本年个人实缴累计差额]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setPersCompleDiffe4addup(BigDecimal persCompleDiffe4addup){
		this.persCompleDiffe4addup = persCompleDiffe4addup;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年06月07日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[导入编号]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getBtimpNo(){
		return btimpNo;
	}
	/**
	* <p>Discription:[导入编号]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setBtimpNo(String btimpNo){
		this.btimpNo = btimpNo;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年06月07日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年06月07日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年06月07日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年06月07日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public Integer getIsExistSupple() {
		return isExistSupple;
	}
	public void setIsExistSupple(Integer isExistSupple) {
		this.isExistSupple = isExistSupple;
	}
}
