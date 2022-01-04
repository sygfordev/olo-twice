package com.hdfs.olo.olo_web.salary.model;

import java.io.Serializable;

/**
 * <p>
 * Description: [薪资-工资卡model]
 * </p>
 * Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
public class ChSaPaycardModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private String cardNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
