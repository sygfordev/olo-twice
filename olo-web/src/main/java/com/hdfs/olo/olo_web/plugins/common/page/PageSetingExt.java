package com.hdfs.olo.olo_web.plugins.common.page;

/**
 * 分页模块定制化扩展[仅用于树形列表]
 * @author huadf
 *
 */
public class PageSetingExt {

	/**
	 * 权限模块-页面分页-定制化偏移量
	 */
	private Integer privOffset;
	/**
	 * 机构模块-页面分页-定制化偏移量
	 */
	private Integer branchOffset;
	
	public PageSetingExt(){}
	public PageSetingExt(Builder builder)
	{
		this.privOffset = builder.privOffset;
		this.branchOffset = builder.branchOffset;
	}
	
	public Integer getPrivOffset()
	{
		return this.privOffset;
	}
	public void setPrivOffset(Integer privOffset)
	{
		this.privOffset = privOffset;
	}
	public Integer getBranchOffset()
	{
		return this.branchOffset;
	}
	public void setBranchOffset(Integer branchOffset)
	{
		this.branchOffset = branchOffset;
	}
	
	public static class Builder {
		private Integer privOffset = 1;
		private Integer branchOffset = 10;
		
		public Builder privOffset(Integer privOffset) {
			this.privOffset = privOffset;
			return this;
		}
		
		public PageSetingExt build()
		{
			return new PageSetingExt(this);
		}
	}
}
