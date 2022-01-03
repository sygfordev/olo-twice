package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardModel;

/**
 * <p>
 * Description: [薪资-工资卡Mapper]
 * </p>
 * Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaPaycardMapper {

	/**
	 * 分页查询固定参数
	 */
	List<ChSaPaycardModel> queryPage(@Param("page") Page<?> page, @Param("entity") ChSaPaycardModel entity,
			@Param("queryFields") List<?> queryFields);

	/**
	 * 查询固定参数
	 */
	List<ChSaPaycardModel> queryList(@Param("entity") ChSaPaycardModel entity,
			@Param("queryFields") List<?> queryFields);

	/**
	 * 根据起始年月和结束年月查询奖励项目
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth);

	/**
	 * 查询固定参数
	 */
	List<ChSaPaycardModel> queryListWithSerial(@Param("entity") ChSaPaycardExtendModel entity,
			@Param("queryFields") List<?> queryFields);

	/**
	 * 查询总数量
	 */
	Long queryCount(@Param("entity") ChSaPaycardModel entity);

	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * 
	 * @param column
	 * @return
	 */
	List<String> querySelectFields(@Param("column") String column, @Param("year") String year);

	/**
	 * 获取最近的一个奖励项目年月
	 * 
	 * @return
	 */
	String queryLastMonth();

}
