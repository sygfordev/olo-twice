package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmStationModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [用工及岗位表Mapper]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmStationMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmStationModel> queryPage(@Param("page")Page page, @Param("entity")ChPmStationModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmStationModel> queryList(@Param("entity")ChPmStationModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmStationModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmStationModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 查询单个实体
	*/
	ChPmStationModel queryByWkId(@Param("wkId")Long wkId,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmStationModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmStationModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmStationModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long id);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> ids);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> ids);

	//*****************************以下为扩展方法****************************
	
	List<Map<String,Object>> queryExistByCardNos(List<String> list);
}
