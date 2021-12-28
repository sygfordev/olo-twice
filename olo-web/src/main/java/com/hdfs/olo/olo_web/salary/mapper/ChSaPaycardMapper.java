package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;

/** 
 * <p>Description: [薪资-工资卡Mapper]</p>
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaPaycardMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSaPayslipModel> queryPage(@Param("page")Page<?> page, @Param("entity")ChSaPayslipModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSaPayslipModel> queryList(@Param("entity")ChSaPayslipModel entity, @Param("queryFields")List<?> queryFields);
	
	/**
	 * 根据起始年月和结束年月查询奖励项目
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	
	/**
	* 查询固定参数
	*/
	List<ChSaPayslipModel> queryListWithSerial(@Param("entity")ChSaPayslipExtendModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSaPayslipModel entity);
	
}
