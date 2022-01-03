package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;

public interface IChSaPayslipRewardBiz {

	/**
	 * 查询固定参数
	 */
	List<ChSaPayslipRewardModel> queryList(String cardNo, String targetMonth);

	/**
	 * 根据时间段查询所有存在的奖励项目
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(String startMonth, String endMonth);

	/**
	 * 获取最近的一个奖励项目年月
	 * 
	 * @return
	 */
	String queryLastMonth();

	/**
	 * 新增(批量)
	 */
	int insertBatch(List<ChSaPayslipRewardModel> list);

	/**
	 * 删除(单条-物理)
	 */
	int delById(String cardNo, String targetMonth);

	/**
	 * 删除(单条-逻辑)
	 */
	int delById4Logic(String cardNo, String targetMonth);

	/**
	 * 删除(批量-物理)
	 */
	int delBatchByIds(List<Map<String, String>> list);

	/**
	 * 删除(批量-逻辑)
	 */
	int delBatchByIds4Logic(List<Map<String, String>> list);

	// *****************************以下为扩展方法****************************

	/**
	 * 通过导入编号批量删除导入记录
	 * 
	 * @param btimpNo
	 * @return
	 */
	int delBatchByBtImpNo4Logic(String btimpNo);
}
