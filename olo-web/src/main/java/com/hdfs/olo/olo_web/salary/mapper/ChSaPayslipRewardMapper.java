package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;

/** 
 * <p>Description: [薪资-工资单-奖励Mapper]</p>
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaPayslipRewardMapper {
	/**
	* 查询固定参数
	*/
	List<ChSaPayslipRewardModel> queryList(@Param("cardNo")String cardNo,@Param("targetMonth")String targetMonth);
	
	/**
	 * 根据起始年月和结束年月查询奖励项目
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	
	/**
	 * 获取最近的一个奖励年月
	 * @return
	 */
	String queryLastMonth();
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSaPayslipRewardModel> list);
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("cardNo")String cardNo,@Param("targetMonth")String targetMonth);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("cardNo")String cardNo,@Param("targetMonth")String targetMonth);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Map<String,String>> list);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Map<String,String>> list);

	//*****************************以下为扩展方法****************************
	
	/**
	*根据导入编号批量删除(批量-逻辑)
	*/
	int delBatchByBtImpNo4Logic(@Param("btimpNo")String btimpNo);
}
