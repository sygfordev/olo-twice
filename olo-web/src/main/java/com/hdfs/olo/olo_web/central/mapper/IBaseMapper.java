package com.hdfs.olo.olo_web.central.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.common.page.Page;


public interface IBaseMapper<T,PK extends Serializable> {
	/**
	* 分页查询固定参数
	*/
	List<T> queryPage(@Param("page")Page<T> page, @Param("entity")T entity, @Param("queryFields")List<String> queryFields);

	/**
	* 查询固定参数
	*/
	List<T> queryList(@Param("entity")T entity, @Param("queryFields")List<String> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")T entity);
	
	/**
	* 查询单个实体
	*/
	T queryById(@Param("id")Long id,  @Param("queryFields")List<String> queryFields);
	
	/**
	* 新增
	*/
	int insert(T entity);
	
	/**
	* 新增-批量
	*/
	int insertBatch(List<T> list);
	
	/**
	* 修改
	*/
	int update(T entity);
	
	/**
	* 物理删除
	*/
	int delById(@Param("id")Long id);
	
	/**
	* 逻辑删除
	*/
	int delById4Logic(@Param("id")Long id);
	
	/**
	* 批量删除-物理
	*/
	int delBatchByIds(List<Long> ids);
	
	/**
	* 批量删除-逻辑
	*/
	int delBatchByIds4Logic(List<Long> ids);
}
