package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPaycardBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPaycardMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardModel;

/**
 * Description: [薪资-工资卡服务实现] Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaPaycardBiz")
public class ChSaPaycardBizImpl implements IChSaPaycardBiz {

	@Autowired
	private ChSaPaycardMapper chSaPaycardMapper;

	/**
	 * <p>
	 * Discription:[薪资-工资卡数据不分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param chSaPaycardModel 薪资-工资卡数据查询条件
	 * @return List<ChSaPaycardModel>列表数据
	 * 
	 * @author:huadf
	 */
	@Override
	public List<Map<String, Object>> queryList(ChSaPaycardModel model) throws Exception {

		List<Map<String, Object>> list = chSaPaycardMapper.queryList(model);
		ArrayList<Map<String, Object>> newlist = new ArrayList<>();
		for (Map<String, Object> map : list) {
			String month = map.get("NET_TARGET_YEARMONTH").toString();
			String WAGE_POSIT_TOTAL = map.get("WAGE_POSIT_TOTAL").toString();
			Map<String, Object> cardMap = new HashMap<>();
			cardMap.put("item", "岗位工资合计");
			if (month.contains("01")) {
				cardMap.put("Jan", map.get("01"));
			}
		}
		return chSaPaycardMapper.queryList(model);
	}

	@Override
	public List<ChSaPaycardModel> queryListWithSerial(ChSaPaycardExtendModel model) throws Exception {
		return chSaPaycardMapper.queryListWithSerial(model);
	}

	/**
	 * <p>
	 * Discription:[薪资-工资卡数据查询总条数]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param chSaPaycardModel 薪资-工资卡数据查询条件
	 * @return 查询条数
	 * @author:huadf
	 */
	@Override
	public Long queryCount(ChSaPaycardModel model) throws Exception {
		return this.chSaPaycardMapper.queryCount(model);
	}

	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * 
	 * @param column
	 * @return
	 */
	@Override
	public List<String> loadSelectFields(String column) {
		if (StringHelper.isNullOrEmpty(column))
			return null;
		List<String> result = chSaPaycardMapper.querySelectFields(column, null);
		return result;
	}

	// ********************以下为扩展方法***********************

	@SuppressWarnings("unused")
	private static List<String> itemList;// 模糊匹配表头
	static {
		// 岗位工资合计
		itemList = Arrays.asList("WAGE_POSIT_TOTAL");
		// 夜班费用
		itemList = Arrays.asList("WAGE_NIGHT_SHIFT");
		// 加班工资
		itemList = Arrays.asList("WAGE_OVERTIME");
		// 年功工资
		itemList = Arrays.asList("WAGE_YEARG");
		// 卫生津贴
		itemList = Arrays.asList("ALLOWANCE_HYGIENE");
		// 纠错工资
		itemList = Arrays.asList("WAGE_ERROR_CORRENT");
		// 电补
		itemList = Arrays.asList("SUPP_4TEL");
		// 其他
		itemList = Arrays.asList("SUPP_4OTH");
		// 工资合计
		itemList = Arrays.asList("WAGE_TOTAL");
		// 经核办绩效
		itemList = Arrays.asList("经核办绩效");
		// 绩效1经核办
		itemList = Arrays.asList("绩效1经核办");
		// 绩效2经核办
		itemList = Arrays.asList("绩效2经核办");
		// 绩效3
		itemList = Arrays.asList("绩效3");
		// 绩效4
		itemList = Arrays.asList("绩效4");
		// 绩效工资合计
		itemList = Arrays.asList("WAGE_JX_TOTAL");
		// 应发工资合计
		itemList = Arrays.asList("WAGE_PAYABLE_TOTAL");
		// 养老保险
		itemList = Arrays.asList("BX_PENSION");
		// 医疗保险
		itemList = Arrays.asList("BX_MEDICAL");
		// 失业保险
		itemList = Arrays.asList("BX_UNEMPLOY");
		// 住房公积金
		itemList = Arrays.asList("BX_HOUSFUND");
		// 年企业金
		itemList = Arrays.asList("BX_ANNUAL_CORP_GOLD");
		// 大病保险
		itemList = Arrays.asList("BX_SERIOUS_ILLNESS");
		// 保险合计
		itemList = Arrays.asList("BX_TOTAL");
		// 卫生费
		itemList = Arrays.asList("CUT_HYGIENE");
		// 职工欠款
		itemList = Arrays.asList("CUT_ARREARS");
		// 扣款合计
		itemList = Arrays.asList("CUT_TOTAL");
		// 实发工资
		itemList = Arrays.asList("NET_SALARY");
	}

	/**
	 * 通过模糊匹配表头，检索出所有匹配到的数据
	 * 
	 * @param itemMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String, Object> loadRewardMap(String startMonth, String endMont, Map<String, Object> itemMap) {
		Map<String, Object> paycardMap = getMonthHeads(startMonth, endMont);
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (null == itemMap || itemMap.isEmpty())
			return retMap;
		String realKey = null;
		for (String key : itemMap.keySet()) {
			String matchKey = "";
			for (String match : itemList) {
				if (!key.startsWith(match))
					continue;
				matchKey = match;
				break;
			}
			if (StringHelper.isNullOrEmpty(matchKey))
				continue;
			realKey = key.replace(matchKey, "").trim();
			// 若奖项除了匹配字段外无任何信息，则忽略该条奖项
			if (StringHelper.isNullOrEmpty(realKey))
				continue;
			retMap.put(realKey, itemMap.get(key));
		}
		return retMap;
	}

	private Map<String, Object> getMonthHeads(String startMonth, String endMonth) {
		Map<String, Object> paycardMap = new HashMap<String, Object>();
		paycardMap.put("item", "岗位工资合计");
		paycardMap.put("Jan", "");
		paycardMap.put("Feb", "");
		paycardMap.put("Mar", "");
		paycardMap.put("Apr", "");
		paycardMap.put("May", "");
		paycardMap.put("Jun", "");
		paycardMap.put("Jul", "");
		paycardMap.put("Aug", "");
		paycardMap.put("Sep", "");
		paycardMap.put("Oct", "");
		paycardMap.put("Nov", "");
		paycardMap.put("Dec", "");
		paycardMap.put("monthly", "");
		paycardMap.put("statisticalInterval", "");
		paycardMap.put("high", "");
		paycardMap.put("median", "");
		paycardMap.put("low", "");
		return paycardMap;
	}

	@Override
	public String queryLastMonth() {
		return chSaPaycardMapper.queryLastMonth();
	}

}
