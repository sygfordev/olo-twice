package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmHosSecdepModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [医院二级科室表Mapper]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmHosSecdepMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmHosSecdepModel> queryPage(@Param("page")Page page, @Param("entity")ChPmHosSecdepModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmHosSecdepModel> queryList(@Param("entity")ChPmHosSecdepModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmHosSecdepModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmHosSecdepModel queryById(@Param("id")Long hsdNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmHosSecdepModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmHosSecdepModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmHosSecdepModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long hsdNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long hsdNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> hsdNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> hsdNos);

	//*****************************以下为扩展方法****************************
}
