package com.hdfs.olo.olo_web.personnel.model;

import com.hdfs.olo.olo_web.plugins.excel.annotaion.ProvCityArea.Target;

public class ExcelHead {
	private String headKey;
	private String headCn;
	private String dictCvt;
	private Target pcaCvt;
	
	
	public String getHeadKey()
	{
		return headKey;
	}
	public void setHeadKey(String headKey)
	{
		this.headKey = headKey;
	}
	public String getDictCvt()
	{
		return dictCvt;
	}
	public void setDictCvt(String dictCvt)
	{
		this.dictCvt = dictCvt;
	}
	public String getHeadCn() {
		return headCn;
	}
	public void setHeadCn(String headCn) {
		this.headCn = headCn;
	}
	public Target getPcaCvt() {
		return pcaCvt;
	}
	public void setPcaCvt(Target pcaCvt) {
		this.pcaCvt = pcaCvt;
	}
}
