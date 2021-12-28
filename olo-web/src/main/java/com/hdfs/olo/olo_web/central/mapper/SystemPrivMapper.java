package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/** 
 * <p>Description: [系统权限表Mapper]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@MyBatisRepository
public interface SystemPrivMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SystemPrivModel> queryPage(@Param("page")Page page, @Param("entity")SystemPrivModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<SystemPrivModel> queryList(@Param("entity")SystemPrivModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SystemPrivModel entity);
	
	/**
	* 查询单个实体
	*/
	SystemPrivModel queryById(@Param("id")Long id,  @Param("queryFields")List<String> queryFields);
	
	/**
	* 根据上级编号查找系统权限
	*/
	List<SystemPrivModel> queryBySuperId(@Param("superId")Long superId);
	
	/**
	* 新增
	*/
	int insert(SystemPrivModel entity);
	
	/**
	* 新增-批量
	*/
	int insertBatch(List<SystemPrivModel> list);
	
	/**
	* 修改
	*/
	int update(SystemPrivModel entity);
	
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
