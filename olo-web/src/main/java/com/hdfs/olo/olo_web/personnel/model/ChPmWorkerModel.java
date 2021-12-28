package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.Excel;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target;
/** 
 * <p>Description: [职工信息表model]</p>
 * Created on 2021年03月30日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Excel(isSupport = true)
public class ChPmWorkerModel implements Serializable {

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
     * 职工性别  0：女  1：男  -1：未知
     */
	@Dict(key = "sex")
	private Integer sex;
	/**
     * 职工年龄
     */
	private Integer age;
	/**
     * 卡类型（身份）
     */
	private Integer cardType;
	/**
     * 卡号（身份）
     */
	@MustExist
	private String cardNo;
	/**
     * 出生日期
     */
	private java.util.Date birthDay;
	/**
     * 民族
     */
	@Dict(key = "nation",cnField = "nationCn")
	private Integer nation;
	/**
     * 民族-名称
     */
	private String nationCn;
	/**
     * 政治面貌
     */
	@Dict(key = "politics",cnField = "politicsCn")
	private Integer politics;
	/**
     * 政治面貌-名称
     */
	private String politicsCn;
	/**
     * 政治面貌加入时间
     */
	private java.util.Date politicsInTime;
	/**
     * 首次参加工作时间
     */
	private java.util.Date firstWorkTime;
	/**
     * 工龄
     */
	private Integer workedYear;
	/**
     * 进入本单位时间
     */
	private java.util.Date intoLocalCompTime;
	/**
     * 籍贯-省份
     */
	@ProvCityArea(target = Target.PROVINCE,cnField = "nativePlaceProvCn")
	private Integer nativePlaceProv;
	/**
     * 籍贯-省份-名称
     */
	private String nativePlaceProvCn;
	/**
     * 籍贯-城市
     */
	@ProvCityArea(target = Target.CITY,superPKey = "nativePlaceProv",cnField = "nativePlaceCityCn")
	private Integer nativePlaceCity;
	/**
     * 籍贯-城市-名称
     */
	private String nativePlaceCityCn;
	/**
     * 籍贯-区县
     */
	@ProvCityArea(target = Target.AREA,superPKey="nativePlaceProv",superCKey="nativePlaceCity",cnField = "nativePlaceAreaCn")
	private Integer nativePlaceArea;
	/**
     * 籍贯-区县-名称
     */
	private String nativePlaceAreaCn;
	/**
     * 户口性质
     */
	@Dict(key = "residence",cnField = "residenceTypeCn")
	private Integer residenceType;
	/**
     * 户口性质-名称
     */
	private String residenceTypeCn;
	/**
     * 户口所在地-省份
     */
	@ProvCityArea(target = Target.PROVINCE,cnField = "residenceBirthlandProvCn")
	private Integer residenceBirthlandProv;
	/**
     * 户口所在地-省份-名称
     */
	private String residenceBirthlandProvCn;
	/**
     * 户口所在地-城市
     */
	@ProvCityArea(target = Target.CITY,superPKey = "residenceBirthlandProv",cnField = "residenceBirthlandCityCn")
	private Integer residenceBirthlandCity;
	/**
     * 户口所在地-城市-名称
     */
	private String residenceBirthlandCityCn;
	/**
     * 户口所在地-区县
     */
	@ProvCityArea(target = Target.AREA,superPKey="residenceBirthlandProv",superCKey = "residenceBirthlandCity",cnField = "residenceBirthlandAreaCn")
	private Integer residenceBirthlandArea;
	/**
     * 户口所在地-区县-名称
     */
	private String residenceBirthlandAreaCn;
	/**
	 * 户口所在地-所属派出所
	 */
	private String residencePoliceStation;
	/**
     * 现家庭详细住址-省份
     */
	@ProvCityArea(target = Target.PROVINCE,cnField = "homeProvCn")
	private Integer homeProv;
	/**
     * 现家庭详细住址-省份-名称
     */
	private String homeProvCn;
	/**
     * 现家庭详细住址-城市
     */
	@ProvCityArea(target = Target.CITY,superPKey="homeProv",cnField = "homeCityCn")
	private Integer homeCity;
	/**
     * 现家庭详细住址-城市-名称
     */
	private String homeCityCn;
	/**
     * 现家庭详细住址-详细地址
     */
	private String homeAddr;
	/**
     * 联系电话
     */
	private String telphoneNo;
	/**
     * 邮箱
     */
	private String mailBox;
	/**
     * 调资类别
     */
	@Dict(key = "adjustType")
	private Integer salaryAdjustType;
	/**
     * 调资类别-名称
     */
	private String salaryAdjustTypeCn;
	/**
     * 状态 0：正常  1：异常
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
	* Created on 2021年03月30日
	* @return Long
    * @author:huadf
    */
	public Long getId(){
		return id;
	}
	/**
	* <p>Discription:[自增主键]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setId(Long id){
		this.id = id;
	}
	/**
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getName(){
		return name;
	}
	/**
	* <p>Discription:[职工姓名]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setName(String name){
		this.name = name;
	}
	/**
	* <p>Discription:[职工性别  0：女  1：男  -1：未知]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getSex(){
		return sex;
	}
	/**
	* <p>Discription:[职工性别  0：女  1：男  -1：未知]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setSex(Integer sex){
		this.sex = sex;
	}
	/**
	* <p>Discription:[职工年龄]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getAge(){
		return age;
	}
	/**
	* <p>Discription:[职工年龄]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setAge(Integer age){
		this.age = age;
	}
	/**
	* <p>Discription:[卡类型（身份）]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getCardType(){
		return cardType;
	}
	/**
	* <p>Discription:[卡类型（身份）]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setCardType(Integer cardType){
		this.cardType = cardType;
	}
	/**
	* <p>Discription:[卡号（身份）]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getCardNo(){
		return cardNo;
	}
	/**
	* <p>Discription:[卡号（身份）]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setCardNo(String cardNo){
		this.cardNo = cardNo;
	}
	/**
	* <p>Discription:[出生日期]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getBirthDay(){
		return birthDay;
	}
	/**
	* <p>Discription:[出生日期]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setBirthDay(java.util.Date birthDay){
		this.birthDay = birthDay;
	}
	/**
	* <p>Discription:[民族]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getNation(){
		return nation;
	}
	/**
	* <p>Discription:[民族]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setNation(Integer nation){
		this.nation = nation;
	}
	/**
	* <p>Discription:[政治面貌]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getPolitics(){
		return politics;
	}
	/**
	* <p>Discription:[政治面貌]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setPolitics(Integer politics){
		this.politics = politics;
	}
	/**
	* <p>Discription:[政治面貌加入时间]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getPoliticsInTime(){
		return politicsInTime;
	}
	/**
	* <p>Discription:[政治面貌加入时间]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setPoliticsInTime(java.util.Date politicsInTime){
		this.politicsInTime = politicsInTime;
	}
	/**
	* <p>Discription:[首次参加工作时间]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getFirstWorkTime(){
		return firstWorkTime;
	}
	/**
	* <p>Discription:[首次参加工作时间]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setFirstWorkTime(java.util.Date firstWorkTime){
		this.firstWorkTime = firstWorkTime;
	}
	/**
	* <p>Discription:[工龄]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getWorkedYear(){
		return workedYear;
	}
	/**
	* <p>Discription:[工龄]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setWorkedYear(Integer workedYear){
		this.workedYear = workedYear;
	}
	/**
	* <p>Discription:[进入本单位时间]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getIntoLocalCompTime(){
		return intoLocalCompTime;
	}
	/**
	* <p>Discription:[进入本单位时间]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setIntoLocalCompTime(java.util.Date intoLocalCompTime){
		this.intoLocalCompTime = intoLocalCompTime;
	}
	/**
	* <p>Discription:[籍贯-省份]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getNativePlaceProv(){
		return nativePlaceProv;
	}
	/**
	* <p>Discription:[籍贯-省份]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setNativePlaceProv(Integer nativePlaceProv){
		this.nativePlaceProv = nativePlaceProv;
	}
	/**
	* <p>Discription:[籍贯-城市]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getNativePlaceCity(){
		return nativePlaceCity;
	}
	/**
	* <p>Discription:[籍贯-城市]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setNativePlaceCity(Integer nativePlaceCity){
		this.nativePlaceCity = nativePlaceCity;
	}
	/**
	* <p>Discription:[籍贯-区县]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getNativePlaceArea(){
		return nativePlaceArea;
	}
	/**
	* <p>Discription:[籍贯-区县]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setNativePlaceArea(Integer nativePlaceArea){
		this.nativePlaceArea = nativePlaceArea;
	}
	/**
	* <p>Discription:[户口性质]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getResidenceType(){
		return residenceType;
	}
	/**
	* <p>Discription:[户口性质]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setResidenceType(Integer residenceType){
		this.residenceType = residenceType;
	}
	/**
	* <p>Discription:[户口所在地-省份]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getResidenceBirthlandProv(){
		return residenceBirthlandProv;
	}
	/**
	* <p>Discription:[户口所在地-省份]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setResidenceBirthlandProv(Integer residenceBirthlandProv){
		this.residenceBirthlandProv = residenceBirthlandProv;
	}
	/**
	* <p>Discription:[户口所在地-省份]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getResidenceBirthlandCity(){
		return residenceBirthlandCity;
	}
	/**
	* <p>Discription:[户口所在地-省份]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setResidenceBirthlandCity(Integer residenceBirthlandCity){
		this.residenceBirthlandCity = residenceBirthlandCity;
	}
	/**
	* <p>Discription:[户口所在地-区县]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getResidenceBirthlandArea(){
		return residenceBirthlandArea;
	}
	/**
	* <p>Discription:[户口所在地-区县]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setResidenceBirthlandArea(Integer residenceBirthlandArea){
		this.residenceBirthlandArea = residenceBirthlandArea;
	}
	/**
	* <p>Discription:[现家庭详细住址-省份]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getHomeProv(){
		return homeProv;
	}
	/**
	* <p>Discription:[现家庭详细住址-省份]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setHomeProv(Integer homeProv){
		this.homeProv = homeProv;
	}
	/**
	* <p>Discription:[现家庭详细住址-城市]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getHomeCity(){
		return homeCity;
	}
	/**
	* <p>Discription:[现家庭详细住址-城市]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setHomeCity(Integer homeCity){
		this.homeCity = homeCity;
	}
	/**
	* <p>Discription:[现家庭详细住址-详细地址]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getHomeAddr(){
		return homeAddr;
	}
	/**
	* <p>Discription:[现家庭详细住址-详细地址]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setHomeAddr(String homeAddr){
		this.homeAddr = homeAddr;
	}
	/**
	* <p>Discription:[联系电话]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getTelphoneNo(){
		return telphoneNo;
	}
	/**
	* <p>Discription:[联系电话]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setTelphoneNo(String telphoneNo){
		this.telphoneNo = telphoneNo;
	}
	/**
	* <p>Discription:[邮箱]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getMailBox(){
		return mailBox;
	}
	/**
	* <p>Discription:[邮箱]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setMailBox(String mailBox){
		this.mailBox = mailBox;
	}
	/**
	* <p>Discription:[调资类别]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getSalaryAdjustType(){
		return salaryAdjustType;
	}
	/**
	* <p>Discription:[调资类别]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setSalaryAdjustType(Integer salaryAdjustType){
		this.salaryAdjustType = salaryAdjustType;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年03月30日
	* @return Integer
    * @author:huadf
    */
	public Integer getStatus(){
		return status;
	}
	/**
	* <p>Discription:[状态 0：正常  1：异常]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月30日
	* @return String
    * @author:huadf
    */
	public String getRemark(){
		return remark;
	}
	/**
	* <p>Discription:[备注]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setRemark(String remark){
		this.remark = remark;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	* <p>Discription:[创建时间]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月30日
	* @return java.util.Date
    * @author:huadf
    */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	* <p>Discription:[更新时间]</p>
	* Created on 2021年03月30日
	* @author:huadf
	*/
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public String getNationCn() {
		return nationCn;
	}
	public void setNationCn(String nationCn) {
		this.nationCn = nationCn;
	}
	public String getPoliticsCn() {
		return politicsCn;
	}
	public void setPoliticsCn(String politicsCn) {
		this.politicsCn = politicsCn;
	}
	public String getNativePlaceProvCn() {
		return nativePlaceProvCn;
	}
	public void setNativePlaceProvCn(String nativePlaceProvCn) {
		this.nativePlaceProvCn = nativePlaceProvCn;
	}
	public String getNativePlaceCityCn() {
		return nativePlaceCityCn;
	}
	public void setNativePlaceCityCn(String nativePlaceCityCn) {
		this.nativePlaceCityCn = nativePlaceCityCn;
	}
	public String getNativePlaceAreaCn() {
		return nativePlaceAreaCn;
	}
	public void setNativePlaceAreaCn(String nativePlaceAreaCn) {
		this.nativePlaceAreaCn = nativePlaceAreaCn;
	}
	public String getResidenceTypeCn() {
		return residenceTypeCn;
	}
	public void setResidenceTypeCn(String residenceTypeCn) {
		this.residenceTypeCn = residenceTypeCn;
	}
	public String getResidenceBirthlandProvCn() {
		return residenceBirthlandProvCn;
	}
	public void setResidenceBirthlandProvCn(String residenceBirthlandProvCn) {
		this.residenceBirthlandProvCn = residenceBirthlandProvCn;
	}
	public String getResidenceBirthlandCityCn() {
		return residenceBirthlandCityCn;
	}
	public void setResidenceBirthlandCityCn(String residenceBirthlandCityCn) {
		this.residenceBirthlandCityCn = residenceBirthlandCityCn;
	}
	public String getResidenceBirthlandAreaCn() {
		return residenceBirthlandAreaCn;
	}
	public void setResidenceBirthlandAreaCn(String residenceBirthlandAreaCn) {
		this.residenceBirthlandAreaCn = residenceBirthlandAreaCn;
	}
	public String getResidencePoliceStation() {
		return residencePoliceStation;
	}
	public void setResidencePoliceStation(String residencePoliceStation) {
		this.residencePoliceStation = residencePoliceStation;
	}
	public String getHomeProvCn() {
		return homeProvCn;
	}
	public void setHomeProvCn(String homeProvCn) {
		this.homeProvCn = homeProvCn;
	}
	public String getHomeCityCn() {
		return homeCityCn;
	}
	public void setHomeCityCn(String homeCityCn) {
		this.homeCityCn = homeCityCn;
	}
	public String getSalaryAdjustTypeCn() {
		return salaryAdjustTypeCn;
	}
	public void setSalaryAdjustTypeCn(String salaryAdjustTypeCn) {
		this.salaryAdjustTypeCn = salaryAdjustTypeCn;
	}
}
