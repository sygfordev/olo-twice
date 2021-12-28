package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [合同信息Mapper]</p>
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmContractInfoMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChPmContractInfoModel> queryPage(@Param("page")Page page, @Param("entity")ChPmContractInfoModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChPmContractInfoModel> queryList(@Param("entity")ChPmContractInfoModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmContractInfoModel entity);
	
	/**
	* 查询单个实体
	*/
	ChPmContractInfoModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChPmContractInfoModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChPmContractInfoModel> list);
	
	/**
	* 修改
	*/
	int update(ChPmContractInfoModel entity);
	
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
	 * 根据职工编号对其下所有合同进行排序编号
	 * @param wkId
	 */
	void synContInfoSortVal(Long wkId);
	/**
	 * 查询所有需要做排序的职工编号列表
	 * @return
	 */
	List<Long> queryAllWkIds4NeedSort();
	
	/**
	 * 根据合同过期时间查询即将过期和已过期的合同
	 * @param isAlreadExist4Msg 
	 * @param val
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryByContEndTime(@Param("isAlreadExist4Msg")boolean isAlreadExist4Msg,@Param("val")int val,@Param("unit")String unit);
}
