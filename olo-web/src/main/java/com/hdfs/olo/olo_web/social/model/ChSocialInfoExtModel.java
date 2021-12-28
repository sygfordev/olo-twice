package com.hdfs.olo.olo_web.social.model;

import java.util.List;

public class ChSocialInfoExtModel extends ChSocialInfoModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String startMonth;
	private String endMonth;
	private String startYear;
	private List<String> wkModalityCns;
	private List<String> wkAreasCns;
	private List<String> compNames;
	private List<String> hosDepart1levelCns;
	private List<String> hosDepart2levelCns;
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
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
	public List<String> getWkModalityCns() {
		return wkModalityCns;
	}
	public void setWkModalityCns(List<String> wkModalityCns) {
		this.wkModalityCns = wkModalityCns;
	}
	public List<String> getWkAreasCns() {
		return wkAreasCns;
	}
	public void setWkAreasCns(List<String> wkAreasCns) {
		this.wkAreasCns = wkAreasCns;
	}
	public List<String> getHosDepart1levelCns() {
		return hosDepart1levelCns;
	}
	public void setHosDepart1levelCns(List<String> hosDepart1levelCns) {
		this.hosDepart1levelCns = hosDepart1levelCns;
	}
	public List<String> getHosDepart2levelCns() {
		return hosDepart2levelCns;
	}
	public void setHosDepart2levelCns(List<String> hosDepart2levelCns) {
		this.hosDepart2levelCns = hosDepart2levelCns;
	}
	public List<String> getCompNames() {
		return compNames;
	}
	public void setCompNames(List<String> compNames) {
		this.compNames = compNames;
	}
}
