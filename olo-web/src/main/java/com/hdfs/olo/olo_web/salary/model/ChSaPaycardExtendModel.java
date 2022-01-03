package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;

/**
 * 工资卡扩展实体-用于查询等外围操作
 * 
 * @author huadf
 *
 */
public class ChSaPaycardExtendModel extends ChSaPaycardModel implements Serializable {

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

}
