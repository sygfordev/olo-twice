package com.hdfs.olo.olo_web.personnel.model;

import java.io.Serializable;
import java.util.Date;

import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.Dict;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;

public class ChPmOthInfo4ImpModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 职工编号
     */
	private Long workerId;
	/**
	 * 职工身份证号-用于导入时使用
	 */
	@MustExist
	private String cardNo;
	/**
     * 是否疫情防控一线医护人员  0:否 1:是
     */
	@Dict(key = "YN")
	private Integer epidsPrecFlHcStaff;
	/**
     * 荣誉金取得时间
     */
	private java.util.Date honMoneyGotTime;
	/**
     * 核减工龄年限
     */
	private Integer caeWkYears;
	/**
	 * 是否残疾人
	 */
	@Dict(key = "YN")
	private Integer isDisability;
	/**
	 * 残疾鉴定级别
	 */
	private String disabilityLev;
	/**
	 * 是否工伤
	 */
	@Dict(key = "YN")
	private Integer isInjryOnJob;
	/**
	 * 工伤鉴定级别
	 */
	private String injryLev;
	
	/**
     * 人事档案编号
     */
	private String dossierNo;
	/**
     * 人事档案存放地
     */
	@Dict(key = "dosStorage")
	private Integer dossierStorage;
	
	/**
     * 进入时间
     */
	private java.util.Date entryTime;
	/**
     * 进入渠道
     */
	@Dict(key = "entryChan")
	private Integer entryChan;
	/**
     * 离职时间
     */
	private java.util.Date quitTime;
	/**
     * 离职原因 退休|病退|辞职|辞退|工作调动等
     */
	@Dict(key = "quitReason")
	private Integer quitReason;
	
	public Long getWorkerId() {
		return workerId;
	}
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	public Integer getEpidsPrecFlHcStaff() {
		return epidsPrecFlHcStaff;
	}
	public void setEpidsPrecFlHcStaff(Integer epidsPrecFlHcStaff) {
		this.epidsPrecFlHcStaff = epidsPrecFlHcStaff;
	}
	public java.util.Date getHonMoneyGotTime() {
		return honMoneyGotTime;
	}
	public void setHonMoneyGotTime(java.util.Date honMoneyGotTime) {
		this.honMoneyGotTime = honMoneyGotTime;
	}
	public Integer getCaeWkYears() {
		return caeWkYears;
	}
	public void setCaeWkYears(Integer caeWkYears) {
		this.caeWkYears = caeWkYears;
	}
	public Integer getIsDisability() {
		return isDisability;
	}
	public void setIsDisability(Integer isDisability) {
		this.isDisability = isDisability;
	}
	public String getDisabilityLev() {
		return disabilityLev;
	}
	public void setDisabilityLev(String disabilityLev) {
		this.disabilityLev = disabilityLev;
	}
	public Integer getIsInjryOnJob() {
		return isInjryOnJob;
	}
	public void setIsInjryOnJob(Integer isInjryOnJob) {
		this.isInjryOnJob = isInjryOnJob;
	}
	public String getInjryLev() {
		return injryLev;
	}
	public void setInjryLev(String injryLev) {
		this.injryLev = injryLev;
	}
	public String getDossierNo() {
		return dossierNo;
	}
	public void setDossierNo(String dossierNo) {
		this.dossierNo = dossierNo;
	}
	public Integer getDossierStorage() {
		return dossierStorage;
	}
	public void setDossierStorage(Integer dossierStorage) {
		this.dossierStorage = dossierStorage;
	}
	public java.util.Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(java.util.Date entryTime) {
		this.entryTime = entryTime;
	}
	public Integer getEntryChan() {
		return entryChan;
	}
	public void setEntryChan(Integer entryChan) {
		this.entryChan = entryChan;
	}
	public java.util.Date getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(java.util.Date quitTime) {
		this.quitTime = quitTime;
	}
	public Integer getQuitReason() {
		return quitReason;
	}
	public void setQuitReason(Integer quitReason) {
		this.quitReason = quitReason;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public static class Builder {
		private Long workerId;
		private String cardNo;
		private Integer epidsPrecFlHcStaff;
		private java.util.Date honMoneyGotTime;
		private Integer caeWkYears;
		private Integer isDisability;
		private String disabilityLev;
		private Integer isInjryOnJob;
		private String injryLev;
		private String dossierNo;
		private Integer dossierStorage;
		private java.util.Date entryTime;
		private Integer entryChan;
		private java.util.Date quitTime;
		private Integer quitReason;
		
		public Builder(){}
		public Builder(ChPmOthInfo4ImpModel model){
			this.workerId = model.workerId;
			this.cardNo = model.cardNo;
			this.epidsPrecFlHcStaff = model.epidsPrecFlHcStaff;
			this.honMoneyGotTime = model.honMoneyGotTime;
			this.caeWkYears = model.caeWkYears;
			this.isDisability = model.isDisability;
			this.disabilityLev = model.disabilityLev;
			this.isInjryOnJob = model.isInjryOnJob;
			this.injryLev = model.injryLev;
			this.dossierNo = model.dossierNo;
			this.dossierStorage = model.dossierStorage;
			this.entryTime = model.entryTime;
			this.entryChan = model.entryChan;
			this.quitTime = model.quitTime;
			this.quitReason = model.quitReason;
		}
		public Builder workerId(Long workerId) {
			this.workerId = workerId;
			return this;
		}
		public Builder epidsPrecFlHcStaff(Integer epidsPrecFlHcStaff) {
			this.epidsPrecFlHcStaff = epidsPrecFlHcStaff;
			return this;
		}
		public Builder honMoneyGotTime(Date honMoneyGotTime)
		{
			this.honMoneyGotTime = honMoneyGotTime;
			return this;
		}
		public Builder caeWkYears(Integer caeWkYears) {
			this.caeWkYears = caeWkYears;
			return this;
		}
		public Builder isDisability(Integer isDisability) {
			this.isDisability = isDisability;
			return this;
		}
		public Builder disabilityLev(String disabilityLev) {
			this.disabilityLev = disabilityLev;
			return this;
		}
		public Builder isInjryOnJob(Integer isInjryOnJob) {
			this.isInjryOnJob = isInjryOnJob;
			return this;
		}
		public Builder injryLev(String injryLev) {
			this.injryLev = injryLev;
			return this;
		}
		public Builder dossierNo(String dossierNo) {
			this.dossierNo = dossierNo;
			return this;
		}
		public Builder dossierStorage(Integer dossierStorage) {
			this.dossierStorage = dossierStorage;
			return this;
		}
		public Builder entryTime(Date entryTime) {
			this.entryTime = entryTime;
			return this;
		}
		public Builder entryChan(Integer entryChan) {
			this.entryChan = entryChan;
			return this;
		}
		public Builder quitTime(Date quitTime) {
			this.quitTime = quitTime;
			return this;
		}
		public Builder quitReason(Integer quitReason) {
			this.quitReason = quitReason;
			return this;
		}
		public ChPmOtherInfoModel buildOth()
		{
			ChPmOtherInfoModel model = new ChPmOtherInfoModel();
			model.setWorkerId(workerId);
			model.setCardNo(cardNo);
			model.setEpidsPrecFlHcStaff(epidsPrecFlHcStaff);
			model.setHonMoneyGotTime(honMoneyGotTime);
			model.setCaeWkYears(caeWkYears);
			model.setIsDisability(isDisability);
			model.setDisabilityLev(disabilityLev);
			model.setIsInjryOnJob(isInjryOnJob);
			model.setInjryLev(injryLev);
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			return model;
		}
		public ChPmDossierInfoModel buildDos()
		{
			ChPmDossierInfoModel model = new ChPmDossierInfoModel();
			model.setWorkerId(workerId);
			model.setCardNo(cardNo);
			model.setDossierNo(dossierNo);
			model.setDossierStorage(dossierStorage);
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			return model;
		}
		public ChPmIncdecInfoModel buildIncD()
		{
			ChPmIncdecInfoModel model = new ChPmIncdecInfoModel();
			model.setWorkerId(workerId);
			model.setCardNo(cardNo);
			model.setEntryTime(entryTime);
			model.setEntryChan(entryChan);
			model.setQuitTime(quitTime);
			model.setQuitReason(quitReason);
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			return model;
		}
	}
}
