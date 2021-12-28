package com.hdfs.olo.olo_web.social.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoModel;

/** 
 * <p>Description: [社保信息Mapper]</p>
 * Created on 2021年06月07日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSocialInfoMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSocialInfoModel> queryPage(@Param("page")Page page, @Param("entity")ChSocialInfoExtModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSocialInfoModel> queryList(@Param("entity")ChSocialInfoExtModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSocialInfoExtModel entity);
	
	/**
	* 查询单个实体
	*/
	ChSocialInfoModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	/**
	 * 根据主键列表查询数据
	 * @param ids
	 * @param queryFields
	 * @return
	 */
	List<ChSocialInfoModel> queryByIds(@Param("ids")List<Long> ids,@Param("queryFields")List<?> queryFields);
	
	/**
	* 查询固定参数
	*/
	List<ChSocialInfoModel> queryListWithSerial(@Param("entity")ChSocialInfoExtModel entity, @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChSocialInfoModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSocialInfoModel> list);
	
	/**
	* 修改
	*/
	int update(ChSocialInfoModel entity);
	
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
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	List<String> querySelectFields(@Param("column")String column);
	/**
	 * 根据身份证号列表和社保月份查找已存在的数据
	 * @param list
	 * @param targetMonth
	 * @return
	 */
	List<String> queryExisted(@Param("list")List<String> list,@Param("targetMonth")String targetMonth);
	
	/**
	 * 查询补缴单
	 * @return
	 */
	List<Map<String,Object>> query4Supple(@Param("entity")ChSocialInfoExtModel entity);
	
	/**
	*根据导入编号批量删除(批量-逻辑)
	*/
	int delBatchByBtImpNo4Logic(@Param("btimpNo")String btimpNo);
}
