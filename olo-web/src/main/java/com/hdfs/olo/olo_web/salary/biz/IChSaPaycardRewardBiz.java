package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;

import com.hdfs.olo.olo_web.salary.model.ChSaPaycardRewardModel;

public interface IChSaPaycardRewardBiz {

	/**
	 * 查询固定参数
	 */
	List<ChSaPaycardRewardModel> queryList(String cardNo, String targetMonth);

	/**
	 * 根据时间段查询所有存在的奖励项目
	 * 
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<String> queryRewardList(String startMonth, String endMonth);
}
