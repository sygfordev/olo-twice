package com.hdfs.olo.olo_web.social.mapper;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.social.model.ChSocialRecordModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/** 
 * <p>Description: [社保导入记录Mapper]</p>
 * Created on 2021年06月09日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSocialRecordMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSocialRecordModel> queryPage(@Param("page")Page page, @Param("entity")ChSocialRecordModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSocialRecordModel> queryList(@Param("entity")ChSocialRecordModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSocialRecordModel entity);
	
	/**
	* 查询单个实体
	*/
	ChSocialRecordModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChSocialRecordModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSocialRecordModel> list);
	
	/**
	* 修改
	*/
	int update(ChSocialRecordModel entity);
	
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
}
