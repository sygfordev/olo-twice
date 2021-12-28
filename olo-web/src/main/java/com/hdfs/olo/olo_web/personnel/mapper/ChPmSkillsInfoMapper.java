package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [技能等级（技术工种）表Mapper]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmSkillsInfoMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmSkillsInfoModel> queryPage(@Param("page")Page page, @Param("entity")ChPmSkillsInfoModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmSkillsInfoModel> queryList(@Param("entity")ChPmSkillsInfoModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmSkillsInfoModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmSkillsInfoModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmSkillsInfoModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmSkillsInfoModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmSkillsInfoModel entity);
	
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
	 * 根据职工编号对其下所有技能等级进行排序编号
	 * @param wkId
	 */
	void synSkillsInfoSortVal(Long wkId);
	/**
	 * 查询所有需要做排序的职工编号列表
	 * @return
	 */
	List<Long> queryAllWkIds4NeedSort();
}
