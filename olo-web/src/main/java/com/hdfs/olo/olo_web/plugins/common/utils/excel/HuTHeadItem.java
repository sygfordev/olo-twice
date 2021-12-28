package com.hdfs.olo.olo_web.plugins.common.utils.excel;

import java.util.ArrayList;
import java.util.List;

public class HuTHeadItem{
	private String title;
	private String field;
	private Integer rowS;
	private Integer rowE;
	private Integer colS;
	private Integer colE;
	private List<HuTHeadItem> subs = new ArrayList<HuTHeadItem>();
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Integer getRowS() {
		return rowS;
	}
	public void setRowS(Integer rowS) {
		this.rowS = rowS;
	}
	public Integer getRowE() {
		return rowE;
	}
	public void setRowE(Integer rowE) {
		this.rowE = rowE;
	}
	public Integer getColS() {
		return colS;
	}
	public void setColS(Integer colS) {
		this.colS = colS;
	}
	public Integer getColE() {
		return colE;
	}
	public void setColE(Integer colE) {
		this.colE = colE;
	}
	public List<HuTHeadItem> getSubs() {
		return subs;
	}
	public void setSubs(List<HuTHeadItem> subs) {
		this.subs = subs;
	}
}
