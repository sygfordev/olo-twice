package com.hdfs.olo.olo_web.central.mapper;

import com.hdfs.olo.olo_web.central.model.SystemDictModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [系统字典Mapper]</p>
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface SystemDictMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SystemDictModel> queryPage(@Param("page")Page page, @Param("entity")SystemDictModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<SystemDictModel> queryList(@Param("entity")SystemDictModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SystemDictModel entity);
	
	/**
	* 查询单个实体
	*/
	SystemDictModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 查询单个实体
	*/
	SystemDictModel queryByCode(@Param("code")String code,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(SystemDictModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<SystemDictModel> list);
	
	/**
	* 修改
	*/
	int update(SystemDictModel entity);
	
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
	
	/**
	* 根据字典编码查询
	*/
	List<SystemDictModel> queryListByCodes(List<String> codes);
}
