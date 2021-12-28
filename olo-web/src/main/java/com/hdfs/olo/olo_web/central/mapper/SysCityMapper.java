package com.hdfs.olo.olo_web.central.mapper;

import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [城市表Mapper]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface SysCityMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SysCityModel> queryPage(@Param("page")Page page, @Param("entity")SysCityModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<SysCityModel> queryList(@Param("entity")SysCityModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SysCityModel entity);
	
	/**
	* 查询单个实体
	*/
	SysCityModel queryById(@Param("id")Long cityNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(SysCityModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<SysCityModel> list);
	
	/**
	* 修改
	*/
	int update(SysCityModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long cityNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long cityNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> cityNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> cityNos);

	//*****************************以下为扩展方法****************************
}
