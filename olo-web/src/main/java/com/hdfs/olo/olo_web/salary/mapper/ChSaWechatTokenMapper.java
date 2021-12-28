package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatTokenModel;

/** 
 * <p>Description: [微信访问TokenMapper]</p>
 * Created on 2021年05月26日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaWechatTokenMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSaWechatTokenModel> queryPage(@Param("page")Page page, @Param("entity")ChSaWechatTokenModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSaWechatTokenModel> queryList(@Param("entity")ChSaWechatTokenModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSaWechatTokenModel entity);
	
	/**
	* 查询单个实体
	*/
	ChSaWechatTokenModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChSaWechatTokenModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSaWechatTokenModel> list);
	
	/**
	* 修改
	*/
	int update(ChSaWechatTokenModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long id);
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> ids);

	int delByIdcard(@Param("cardNo")String cardNo);
	//*****************************以下为扩展方法****************************
	/**
	 * 清除minutes时间到现在的数据，单位unit
	 * @param num 数据
	 * @param unit 单位：H小时  M分钟  S秒
	 * @return
	 * @throws Exception
	 */
	int delByTime(@Param("num")int num,@Param("unit")String unit);
}
