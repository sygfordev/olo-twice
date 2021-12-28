package com.hdfs.olo.olo_web.central.mapper;

import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [区县表Mapper]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface SysAreaMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SysAreaModel> queryPage(@Param("page")Page page, @Param("entity")SysAreaModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<SysAreaModel> queryList(@Param("entity")SysAreaModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SysAreaModel entity);
	
	/**
	* 查询单个实体
	*/
	SysAreaModel queryById(@Param("id")Long areaNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(SysAreaModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<SysAreaModel> list);
	
	/**
	* 修改
	*/
	int update(SysAreaModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long areaNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long areaNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> areaNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> areaNos);

	//*****************************以下为扩展方法****************************
}
