package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardRewardModel;

/**
 * <p>
 * Description: [薪资-工资单-奖励Mapper]
 * </p>
 * Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaPaycardRewardMapper {
	/**
	 * 查询固定参数
	 */
	List<ChSaPaycardRewardModel> queryList(@Param("cardNo") String cardNo, @Param("targetMonth") String targetMonth);

	/**
	 * 根据起始年月和结束年月查询奖励项目
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth);

	/**
	 * 获取最近的一个奖励年月
	 * 
	 * @return
	 */
	String queryLastMonth();

}
