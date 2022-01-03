package com.hdfs.olo.olo_web.salary.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 工资条-奖励
 * @author huadf
 *
 */
public class ChSaPaycardRewardModel {

	private String cardNo;
	private String targetYearMonth;
	private String rewardItem;
	private BigDecimal rewardAmount;
	/**
     * 导入编号
     */
	private String btimpNo;
	private Integer status;
	private Date createTime;
	private Date updateTime;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getTargetYearMonth() {
		return targetYearMonth;
	}
	public void setTargetYearMonth(String targetYearMonth) {
		this.targetYearMonth = targetYearMonth;
	}
	public String getRewardItem() {
		return rewardItem;
	}
	public void setRewardItem(String rewardItem) {
		this.rewardItem = rewardItem;
	}
	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getBtimpNo() {
		return btimpNo;
	}
	public void setBtimpNo(String btimpNo) {
		this.btimpNo = btimpNo;
	}
}
