package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;

/**
 * <p>
 * Description: [薪资-工资单Mapper]
 * </p>
 * Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaPayslipMapper {

	/**
	 * 分页查询固定参数
	 */
	List<ChSaPayslipModel> queryPage(@Param("page") Page<?> page, @Param("entity") ChSaPayslipModel entity,
			@Param("queryFields") List<?> queryFields);

	/**
	 * 查询固定参数
	 */
	List<ChSaPayslipModel> queryList(@Param("entity") ChSaPayslipModel entity,
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
	List<ChSaPayslipModel> queryListWithSerial(@Param("entity") ChSaPayslipExtendModel entity,
			@Param("queryFields") List<?> queryFields);

	/**
	 * 查询总数量
	 */
	Long queryCount(@Param("entity") ChSaPayslipModel entity);

	/**
	 * 查询单个实体
	 */
	ChSaPayslipModel queryById(@Param("id") Long id, @Param("queryFields") List<?> queryFields);

	/**
	 * 根据身份证号和工资年月查询已存在的数据
	 * 
	 * @param list
	 * @return
	 */
	List<String> queryExisted(@Param("list") List<String> list, @Param("targetMonth") String targetMonth);

	/**
	 * 新增(单条)
	 */
	int insert(ChSaPayslipModel entity);

	/**
	 * 新增(批量)
	 */
	int insertBatch(List<ChSaPayslipModel> list);

	/**
	 * 修改
	 */
	int update(ChSaPayslipModel entity);

	/**
	 * 删除(单条-物理)
	 */
	int delById(@Param("id") Long id);

	/**
	 * 删除(单条-逻辑)
	 */
	int delById4Logic(@Param("id") Long id);

	/**
	 * 删除(批量-物理)
	 */
	int delBatchByIds(List<Long> ids);

	/**
	 * 删除(批量-逻辑)
	 */
	int delBatchByIds4Logic(List<Long> ids);

	// *****************************以下为扩展方法****************************
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * 
	 * @param column
	 * @return
	 */
	List<String> querySelectFields(@Param("column") String column, @Param("year") String year);

	/**
	 * 根据身份证号查询工资单
	 * 
	 * @param cardNo
	 * @return
	 */
	List<ChSaPayslipModel> query4Wechat(@Param("cardNo") String cardNo, @Param("month") String month);

	/**
	 * 根据导入编号批量删除(批量-逻辑)
	 */
	int delBatchByBtImpNo4Logic(@Param("btimpNo") String btimpNo);
}
