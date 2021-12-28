package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipRewardBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipRewardMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;

@DataSource("salary")
@Service("chSaPayslipRewardBiz")
public class ChSaPayslipRewardBizImpl implements IChSaPayslipRewardBiz{

	/**
	 * <p>Discription:[薪资-工资单-奖励Mapper]</p>
	 */	
	@Autowired
	private ChSaPayslipRewardMapper chSaPayslipRewardMapper;
	@Override
	public List<ChSaPayslipRewardModel> queryList(String cardNo, String targetMonth) {
		if(StringHelper.isNullOrEmpty(cardNo) || StringHelper.isNullOrEmpty(targetMonth))
			return new ArrayList<ChSaPayslipRewardModel>();
		return chSaPayslipRewardMapper.queryList(cardNo, targetMonth);
	}

	@Override
	public List<String> queryRewardList(String startMonth,String endMonth)
	{
		if(StringHelper.isNullOrEmpty(startMonth) || StringHelper.isNullOrEmpty(endMonth))
			return new ArrayList<String>();
		return chSaPayslipRewardMapper.queryRewardList(startMonth, endMonth);
	}
	
	@Override
	public String queryLastMonth()
	{
		return chSaPayslipRewardMapper.queryLastMonth();
	}
	
	@Override
	public int insertBatch(List<ChSaPayslipRewardModel> list) {
		if(null == list || list.size()<=0)
			return 0;
		return chSaPayslipRewardMapper.insertBatch(list);
	}

	@Override
	public int delById(String cardNo, String targetMonth) {
		if(StringHelper.isNullOrEmpty(cardNo) || StringHelper.isNullOrEmpty(targetMonth))
			return 0;
		return chSaPayslipRewardMapper.delById(cardNo, targetMonth);
	}

	@Override
	public int delById4Logic(String cardNo, String targetMonth) {
		if(StringHelper.isNullOrEmpty(cardNo) || StringHelper.isNullOrEmpty(targetMonth))
			return 0;
		return chSaPayslipRewardMapper.delById4Logic(cardNo, targetMonth);
	}

	@Override
	public int delBatchByIds(List<Map<String, String>> list) {
		if(null == list || list.size()<=0)
			return 0;
		return chSaPayslipRewardMapper.delBatchByIds(list);
	}

	@Override
	public int delBatchByIds4Logic(List<Map<String, String>> list) {
		if(null == list || list.size()<=0)
			return 0;
		return chSaPayslipRewardMapper.delBatchByIds4Logic(list);
	}

	@Override
	public int delBatchByBtImpNo4Logic(String btimpNo) {
		if(StringHelper.isNullOrEmpty(btimpNo))
			return 0;
		return chSaPayslipRewardMapper.delBatchByBtImpNo4Logic(btimpNo);
	}
}
