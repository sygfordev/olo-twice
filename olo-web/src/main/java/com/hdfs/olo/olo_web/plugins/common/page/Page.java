package com.hdfs.olo.olo_web.plugins.common.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author huadf
 *
 */
public class Page<T> implements java.io.Serializable {

	private static final long serialVersionUID = -7142026735728407523L;
	private Integer curPageIndex = 1;
	private Long pageOffset = 0l;
	private Integer pageSize = 10;
	private Long pageCount = 0l;
	private Long recordTotal = 0l;
	private String queryFields = null;
	private String orderBy = null;
	private T model;
	private List<T> datas = new ArrayList<T>();
	/**
	 * 	仅用于树形列表-页面定制化扩展
	 */
	private PageSetingExt setingExt = new PageSetingExt();
	
	public Page(Builder<T> builder)
    {
        this.curPageIndex=builder.curPageIndex;
        this.curPageIndex = curPageIndex<1?1:curPageIndex;
        this.pageOffset = ((curPageIndex-1)*pageSize)*1l;
        this.queryFields = builder.queryFields;
        this.orderBy = builder.orderBy;
        this.pageSize=builder.pageSize;
        this.model=builder.model;
        this.setingExt = builder.setingExt;
    }
	
	public Integer getCurPageIndex() {
		return null == curPageIndex ? 1 : curPageIndex;
	}

	public void setCurPageIndex(Integer curPageIndex) {
		this.curPageIndex = curPageIndex;
	}
	
	public Long getPageOffset() {
		this.curPageIndex = curPageIndex<1?1:curPageIndex;
        this.pageOffset = ((curPageIndex-1)*pageSize)*1l;
		return pageOffset;
	}

	public void setPageOffset(Long pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageSize() {
		return null == pageSize ? 10 : pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Long getPageCount() {
		return pageCount;
	}
	
	public Long getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(Long recordTotal) {
		this.recordTotal = recordTotal;
		this.pageCount = (recordTotal/pageSize)+(0 == (recordTotal%pageSize)? 0 : 1);
	}
	
	public String getQueryFields()
	{
		return queryFields;
	}
	public void setQueryFields(String queryFields)
	{
		this.queryFields = queryFields;
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}
	
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	public static class Builder<T> {
		private Integer curPageIndex = 1;
		private Integer pageSize = 10;
		private String queryFields = null;
		private String orderBy = null;
		private T model;
		private PageSetingExt setingExt;
		
		public Builder(){}
		public Builder(Page<T> page){
			this.curPageIndex = page.curPageIndex;
			this.pageSize = page.pageSize;
			this.queryFields = page.queryFields;
			this.orderBy = page.orderBy;
			this.model = page.model;
			this.setingExt = page.setingExt;
		}
		
		public Builder<T> curPageIndex(Integer curPageIndex) {
			this.curPageIndex = curPageIndex;
			return this;
		}
		public Builder<T> pageSize(Integer pageSize) {
			this.pageSize = pageSize;
			return this;
		}
		public Builder<T> queryFields(String queryFields)
		{
			this.queryFields = queryFields;
			return this;
		}
		public Builder<T> orderBy(String orderBy) {
			this.orderBy = orderBy;
			return this;
		}
		public Builder<T> model(T model) {
			this.model = model;
			return this;
		}
		public Builder<T> setingExt(PageSetingExt setingExt) {
			this.setingExt = setingExt;
			return this;
		}
		public Page<T> build()
		{
			return new Page<T>(this);
		}
	}
}
