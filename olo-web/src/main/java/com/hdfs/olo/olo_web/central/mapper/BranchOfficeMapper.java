package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/** 
 * <p>Description: [组织机构表Mapper]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@MyBatisRepository
public interface BranchOfficeMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<BranchOfficeModel> queryPage(@Param("page")Page page, @Param("entity")BranchOfficeModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<BranchOfficeModel> queryList(@Param("entity")BranchOfficeModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")BranchOfficeModel entity);
	
	/**
	* 查询单个实体
	*/
	BranchOfficeModel queryByBranchId(@Param("id")Long id);
	
	/**
	* 根据parentIds查询组织机构
	*/
	List<BranchOfficeModel> queryListByParentIds(@Param("parentIds")List<?> parentIds,@Param("queryFields")List queryFields);
	
	/**
	*根据组织机构ID向上查询
	*/
	List<BranchOfficeModel> queryListUpById(@Param("id")Long id);
	/**
	* 根据组织机构ID向下查询
	*/
	List<BranchOfficeModel> queryListDownById(@Param("id")Long id);
	
	/**
	* 查询单个实体
	*/
	BranchOfficeModel queryById(@Param("id")Long id,@Param("queryFields")List queryFields);
	
	/**
	* 新增
	*/
	int insert(BranchOfficeModel entity);
	
	/**
	* 新增-批量
	*/
	int insertBatch(List<BranchOfficeModel> list);
	
	/**
	* 修改
	*/
	int update(BranchOfficeModel entity);
	
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
