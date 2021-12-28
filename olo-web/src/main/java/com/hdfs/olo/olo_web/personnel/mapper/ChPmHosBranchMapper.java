package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [医院支部表Mapper]</p>
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmHosBranchMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmHosBranchModel> queryPage(@Param("page")Page page, @Param("entity")ChPmHosBranchModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmHosBranchModel> queryList(@Param("entity")ChPmHosBranchModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmHosBranchModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmHosBranchModel queryById(@Param("id")Long hbhNo,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmHosBranchModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmHosBranchModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmHosBranchModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long hbhNo);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long hbhNo);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> hbhNos);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> hbhNos);

	//*****************************以下为扩展方法****************************
}
