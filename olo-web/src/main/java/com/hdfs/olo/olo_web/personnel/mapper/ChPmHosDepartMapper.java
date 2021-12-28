package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [医院一级科室表Mapper]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmHosDepartMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmHosDepartModel> queryPage(@Param("page")Page page, @Param("entity")ChPmHosDepartModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmHosDepartModel> queryList(@Param("entity")ChPmHosDepartModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmHosDepartModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmHosDepartModel queryById(@Param("id")Long hdpNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmHosDepartModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmHosDepartModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmHosDepartModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long hdpNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long hdpNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> hdpNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> hdpNos);

	//*****************************以下为扩展方法****************************
}
