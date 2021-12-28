package com.hdfs.olo.olo_web.central.mapper;

import com.hdfs.olo.olo_web.central.model.SystemDictItemModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [系统字典项Mapper]</p>
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface SystemDictItemMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<SystemDictItemModel> queryPage(@Param("page")Page page, @Param("entity")SystemDictItemModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<SystemDictItemModel> queryList(@Param("entity")SystemDictItemModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")SystemDictItemModel entity);
	
	/**
	* 查询单个实体
	*/
	SystemDictItemModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(SystemDictItemModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<SystemDictItemModel> list);
	
	/**
	* 修改
	*/
	int update(SystemDictItemModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long id);
	
	/**
	*通过字典编号删除(单条-逻辑)
	*/
	int delByDictId4Logic(@Param("dictId")Long dictId);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> ids);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> ids);
	/**
	*通过字典编号删除(批量-逻辑)
	*/
	int delBatchByDictIds4Logic(List<Long> dictIds);

	//*****************************以下为扩展方法****************************
	
	//根据字典编码集合查询字典项集合
	List<Map<String,Object>> queryDictItemByCodes(List<String> codes);
}
