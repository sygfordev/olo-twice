package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [系统角色表Mapper]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@MyBatisRepository
public interface SystemRoleMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SystemRoleModel> queryPage(@Param("page")Page page, @Param("entity")SystemRoleModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<SystemRoleModel> queryList(@Param("entity")SystemRoleModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SystemRoleModel entity);
	
	/**
	* 查询单个实体
	*/
	SystemRoleModel queryById(@Param("id")Long id,  @Param("queryFields")List queryFields);
	
	/**
	* 新增
	*/
	int insert(SystemRoleModel entity);
	
	/**
	* 新增-批量
	*/
	int insertBatch(List<SystemRoleModel> list);
	
	/**
	* 修改
	*/
	int update(SystemRoleModel entity);
	
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
