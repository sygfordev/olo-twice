package com.hdfs.olo.olo_web.central.mapper;

import com.hdfs.olo.olo_web.central.model.SysProvinceModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [省份表Mapper]</p>
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface SysProvinceMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SysProvinceModel> queryPage(@Param("page")Page page, @Param("entity")SysProvinceModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<SysProvinceModel> queryList(@Param("entity")SysProvinceModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SysProvinceModel entity);
	
	/**
	* 查询单个实体
	*/
	SysProvinceModel queryById(@Param("id")Long provNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(SysProvinceModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<SysProvinceModel> list);
	
	/**
	* 修改
	*/
	int update(SysProvinceModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long provNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long provNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> provNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> provNos);

	//*****************************以下为扩展方法****************************
}
