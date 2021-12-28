package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.ComTabLogModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [操作日志Mapper]</p>
 * Created on 2020年08月24日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@MyBatisRepository
public interface ComTabLogMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ComTabLogModel> queryPage(@Param("page")Page<ComTabLogModel> page, @Param("entity")ComTabLogModel entity, @Param("queryFields")List<String> queryFields);

	/**
	* 查询固定参数
	*/
	List<ComTabLogModel> queryList(@Param("entity")ComTabLogModel entity, @Param("queryFields")List<String> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ComTabLogModel entity);
	
	/**
	* 查询单个实体
	*/
	ComTabLogModel queryById(@Param("id")Long id,  @Param("queryFields")List<String> queryFields);
	
	/**
	* 新增
	*/
	int insert(ComTabLogModel entity);
	
	/**
	* 新增-批量
	*/
	int insertBatch(List<ComTabLogModel> list);
	
	/**
	* 修改
	*/
	int update(ComTabLogModel entity);
	
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
	int delBatchByIds(List ids);
	
	/**
	* 批量删除-逻辑
	*/
	int delBatchByIds4Logic(List ids);
}
