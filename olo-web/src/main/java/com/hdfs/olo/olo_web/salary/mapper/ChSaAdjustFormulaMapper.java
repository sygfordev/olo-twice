package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustFormulaModel;

/** 
 * <p>Description: [调资-公式表Mapper]</p>
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaAdjustFormulaMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSaAdjustFormulaModel> queryPage(@Param("page")Page page, @Param("entity")ChSaAdjustFormulaModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSaAdjustFormulaModel> queryList(@Param("entity")ChSaAdjustFormulaModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSaAdjustFormulaModel entity);
	
	/**
	* 查询单个实体
	*/
	ChSaAdjustFormulaModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChSaAdjustFormulaModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSaAdjustFormulaModel> list);
	
	/**
	* 修改
	*/
	int update(ChSaAdjustFormulaModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delByFormulaType4Logic(@Param("type")String type);
	
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
	* 根据公式类型集合进行查询
	*/
	List<ChSaAdjustFormulaModel> queryListByTypes(@Param("types")List<String> types);
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别
	 * @param recordId
	 * @return
	 */
	List<Map<String,Object>> queryAdjustTypeAndUniqueKey(@Param("recordId")Long recordId);
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别（计算公式）列表
	 * @param adjustType
	 * @param uniqueKey
	 * @return
	 */
	List<ChSaAdjustFormulaModel> queryByAdjustTypeAndUniqueKey(@Param("adjustType")String adjustType,@Param("uniqueKey")String uniqueKey);
}
