package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [医院职工表Mapper]</p>
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
@Component
public interface ChPmWorkerMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmWorkerModel> queryPage(Page page, ChPmWorkerModel entity, List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmWorkerModel> queryList(ChPmWorkerModel entity, List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(ChPmWorkerModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmWorkerModel queryById(@Param("id")Long id, @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	Long insert(ChPmWorkerModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmWorkerModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmWorkerModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(Long id);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> ids);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> ids);

	//*****************************以下为扩展方法****************************
	List<String> queryExisted(@Param("list")List<String> list);
	
	/**
	 * 根据身份证号批量查询职工信息
	 * @param cardNos
	 * @return
	 */
	List<Map<String,Object>> queryByCardNoBatch(@Param("list")List<String> list);
	
	/**
	 * 根据身份证号批量查询职工的其他(其他信息、档案信息和增减信息)
	 * @param cardNos
	 * @return
	 */
	List<Map<String,Object>> queryExist4OthByCardNos(List<String> list);
}
