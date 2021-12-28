package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;
import java.util.List;

/**
 * 工资条扩展实体-用于查询等外围操作
 * @author huadf
 *
 */
public class ChSaPayslipExtendModel extends ChSaPayslipModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6454583387278808520L;
	/**
	 * 起始年月
	 */
	private String startMonth;
	/**
	 * 结束年月
	 */
	private String endMonth;
	
	private List<String> positCns;
	private List<String> titleCns;
	private List<String> hosBranchCns;
	private List<String> hosDepart1levelCns;
	private List<String> hosDepart2levelCns;
	private List<String> wkModalityCns;
	
	private List<String> departClassCns;
	private List<String> departClassPops;
	private List<String> stationCns;
	private List<String> stationTypeCns;
	private List<String> stationStatusCns;
	private List<String> stationSeqCns;
	private List<String> titleClassCns;
	private List<String> skillsLevelCns;
	private List<String> eduLev4nowCns;
	private List<String> saSumProjects;
	private List<String> rptWkDepClasss;
	
	
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	public List<String> getHosDepart1levelCns() {
		return hosDepart1levelCns;
	}
	public void setHosDepart1levelCns(List<String> hosDepart1levelCns) {
		this.hosDepart1levelCns = hosDepart1levelCns;
	}
	public List<String> getWkModalityCns() {
		return wkModalityCns;
	}
	public void setWkModalityCns(List<String> wkModalityCns) {
		this.wkModalityCns = wkModalityCns;
	}
	public List<String> getHosBranchCns() {
		return hosBranchCns;
	}
	public void setHosBranchCns(List<String> hosBranchCns) {
		this.hosBranchCns = hosBranchCns;
	}
	public List<String> getHosDepart2levelCns() {
		return hosDepart2levelCns;
	}
	public void setHosDepart2levelCns(List<String> hosDepart2levelCns) {
		this.hosDepart2levelCns = hosDepart2levelCns;
	}
	public List<String> getDepartClassCns() {
		return departClassCns;
	}
	public void setDepartClassCns(List<String> departClassCns) {
		this.departClassCns = departClassCns;
	}
	public List<String> getDepartClassPops() {
		return departClassPops;
	}
	public void setDepartClassPops(List<String> departClassPops) {
		this.departClassPops = departClassPops;
	}
	public List<String> getStationCns() {
		return stationCns;
	}
	public void setStationCns(List<String> stationCns) {
		this.stationCns = stationCns;
	}
	public List<String> getStationTypeCns() {
		return stationTypeCns;
	}
	public void setStationTypeCns(List<String> stationTypeCns) {
		this.stationTypeCns = stationTypeCns;
	}
	public List<String> getStationStatusCns() {
		return stationStatusCns;
	}
	public void setStationStatusCns(List<String> stationStatusCns) {
		this.stationStatusCns = stationStatusCns;
	}
	public List<String> getStationSeqCns() {
		return stationSeqCns;
	}
	public void setStationSeqCns(List<String> stationSeqCns) {
		this.stationSeqCns = stationSeqCns;
	}
	public List<String> getTitleClassCns() {
		return titleClassCns;
	}
	public void setTitleClassCns(List<String> titleClassCns) {
		this.titleClassCns = titleClassCns;
	}
	public List<String> getSkillsLevelCns() {
		return skillsLevelCns;
	}
	public void setSkillsLevelCns(List<String> skillsLevelCns) {
		this.skillsLevelCns = skillsLevelCns;
	}
	public List<String> getEduLev4nowCns() {
		return eduLev4nowCns;
	}
	public void setEduLev4nowCns(List<String> eduLev4nowCns) {
		this.eduLev4nowCns = eduLev4nowCns;
	}
	public List<String> getSaSumProjects() {
		return saSumProjects;
	}
	public void setSaSumProjects(List<String> saSumProjects) {
		this.saSumProjects = saSumProjects;
	}
	public List<String> getRptWkDepClasss() {
		return rptWkDepClasss;
	}
	public void setRptWkDepClasss(List<String> rptWkDepClasss) {
		this.rptWkDepClasss = rptWkDepClasss;
	}
	public List<String> getPositCns() {
		return positCns;
	}
	public void setPositCns(List<String> positCns) {
		this.positCns = positCns;
	}
	public List<String> getTitleCns() {
		return titleCns;
	}
	public void setTitleCns(List<String> titleCns) {
		this.titleCns = titleCns;
	}
}
