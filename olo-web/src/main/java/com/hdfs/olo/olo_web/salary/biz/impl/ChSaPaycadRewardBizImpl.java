package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPaycardRewardBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPaycardRewardMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardRewardModel;

@DataSource("salary")
@Service("chSaPaycardRewardBiz")
public class ChSaPaycadRewardBizImpl implements IChSaPaycardRewardBiz {

	/**
	 * <p>
	 * Discription:[薪资-工资卡-奖励Mapper]
	 * </p>
	 */
	@Autowired
	private ChSaPaycardRewardMapper chSaPaycardRewardMapper;

	@Override
	public List<ChSaPaycardRewardModel> queryList(String cardNo, String targetMonth) {
		if (StringHelper.isNullOrEmpty(cardNo) || StringHelper.isNullOrEmpty(targetMonth))
			return new ArrayList<ChSaPaycardRewardModel>();
		return chSaPaycardRewardMapper.queryList(cardNo, targetMonth);
	}

	@Override
	public List<String> queryRewardList(String startMonth, String endMonth) {
		if (StringHelper.isNullOrEmpty(startMonth) || StringHelper.isNullOrEmpty(endMonth))
			return new ArrayList<String>();
		return chSaPaycardRewardMapper.queryRewardList(startMonth, endMonth);
	}

}
