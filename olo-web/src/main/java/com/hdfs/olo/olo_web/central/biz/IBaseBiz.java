package com.hdfs.olo.olo_web.central.biz;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;



public interface IBaseBiz<T,PK extends Serializable> {
	/**
	 * 增加
	 * @param entity
	 */
	public void add(T entity);
	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity);
	/**
	 * 通过主键删除
	 * @param id
	 */
	public void delete(PK id);
	
	/**
	 * 实体删除
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 分页
	 * @param page
	 */
	public PageInfo<T> getByPage(Map<String,Object> params,Integer pageNum,Integer pageSize);
	//public void getByPage(Page page);
	
	/**
	 * 通过主键获取对象
	 * @param id
	 * @return
	 */
	public T getById(PK id);
	
	/**
	 * 获取所有实体
	 * @return
	 */
	public List<T> getAll();
	
}
