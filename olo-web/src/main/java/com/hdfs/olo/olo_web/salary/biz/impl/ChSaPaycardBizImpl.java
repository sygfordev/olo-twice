package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
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

	@Autowired
	private IHuTCommonService huTCommonService;

	/**
	 * <p>
	 * Discription:[薪资-工资卡数据不分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param chSaPaycardModel 薪资-工资卡数据查询条件
	 * @return List<Map<String, Object>>列表数据
	 * 
	 * @author:huadf
	 */
	@Override
	public List<Map<String, Object>> queryList(ChSaPaycardModel model) throws Exception {
		List<Map<String, Object>> list = chSaPaycardMapper.queryList(model);
		ArrayList<Map<String, Object>> newlist = new ArrayList<>();
		if (!list.isEmpty()) {
			for (Map.Entry<String, String> entry : itemMap.entrySet()) {
				String item = entry.getKey();
				String itemCode = entry.getValue();
				Map<String, Object> cardMap = getItemMap(list, item, itemCode);
				newlist.add(cardMap);
			}
		}
		return newlist;
	}

	private Map<String, Object> getItemMap(List<Map<String, Object>> list, String item, String itemCode) {
		Map<String, Object> cardMap = new HashMap<>();
		for (Map<String, Object> map : list) {
			String month = map.get("NET_TARGET_YEARMONTH").toString();
			cardMap.put("item", item);
			if (month.contains("01")) {
				cardMap.put("Jan", map.get(itemCode));
			}
			if (month.contains("02")) {
				cardMap.put("Feb", map.get(itemCode));
			}
			if (month.contains("03")) {
				cardMap.put("Mar", map.get(itemCode));
			}
			if (month.contains("04")) {
				cardMap.put("Apr", map.get(itemCode));
			}
			if (month.contains("05")) {
				cardMap.put("May", map.get(itemCode));
			}
			if (month.contains("06")) {
				cardMap.put("Jun", map.get(itemCode));
			}
			if (month.contains("07")) {
				cardMap.put("Jul", map.get(itemCode));
			}
			if (month.contains("08")) {
				cardMap.put("Aug", map.get(itemCode));
			}
			if (month.contains("09")) {
				cardMap.put("Sep", map.get(itemCode));
			}
			if (month.contains("10")) {
				cardMap.put("Oct", map.get(itemCode));
			}
			if (month.contains("11")) {
				cardMap.put("Nov", map.get(itemCode));
			}
			if (month.contains("12")) {
				cardMap.put("Dec", map.get(itemCode));
			}
			cardMap.put("monthly", map.get("monthly"));
			cardMap.put("statisticalInterval", map.get("statisticalInterval"));
			cardMap.put("high", map.get("high"));
			cardMap.put("median", map.get("median"));
			cardMap.put("low", map.get("low"));

		}
		return cardMap;
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

	private static Map<String, String> itemMap = new LinkedHashMap<String, String>();
	static {
		// 岗位工资合计
		itemMap.put("岗位工资合计", "WAGE_POSIT_TOTAL");
		// 夜班费用
		itemMap.put("夜班费用", "WAGE_NIGHT_SHIFT");
		// 加班工资
		itemMap.put("加班工资", "WAGE_OVERTIME");
		// 年功工资
		itemMap.put("年功工资", "WAGE_YEARG");
		// 卫生津贴
		itemMap.put("卫生津贴", "ALLOWANCE_HYGIENE");
		// 纠错工资
		itemMap.put("纠错工资", "WAGE_ERROR_CORRENT");
		// 电补
		itemMap.put("电补", "SUPP_4TEL");
		// 其他
		itemMap.put("其他", "SUPP_4OTH");
		// 工资合计
		itemMap.put("工资合计", "WAGE_TOTAL");
		// 经核办绩效
		itemMap.put("经核办绩效", "VERIFY_REWARD");
		// 绩效1经核办
		itemMap.put("绩效1经核办", "VERIFY_REWARD1");
		// 绩效2经核办
		itemMap.put("绩效2经核办", "VERIFY_REWARD2");
		// 绩效3
		itemMap.put("绩效3", "VERIFY_REWARD3");
		// 绩效4
		itemMap.put("绩效4", "VERIFY_REWARD4");
		// 绩效工资合计
		itemMap.put("绩效工资合计", "WAGE_JX_TOTAL");
		// 应发工资合计
		itemMap.put("应发工资合计", "WAGE_PAYABLE_TOTAL");
		// 养老保险
		itemMap.put("养老保险", "BX_PENSION");
		// 医疗保险
		itemMap.put("医疗保险", "BX_MEDICAL");
		// 失业保险
		itemMap.put("失业保险", "BX_UNEMPLOY");
		// 住房公积金
		itemMap.put("住房公积金", "BX_HOUSFUND");
		// 年企业金
		itemMap.put("年企业金", "BX_ANNUAL_CORP_GOLD");
		// 大病保险
		itemMap.put("大病保险", "BX_SERIOUS_ILLNESS");
		// 保险合计
		itemMap.put("保险合计", "BX_TOTAL");
		// 卫生费
		itemMap.put("卫生费", "CUT_HYGIENE");
		// 职工欠款
		itemMap.put("职工欠款", "CUT_ARREARS");
		// 代扣税
		itemMap.put("代扣税", "TAX_INCOME_PERSONAL");
		// 扣款合计
		itemMap.put("扣款合计", "CUT_TOTAL");
		// 实发工资
		itemMap.put("实发工资", "NET_SALARY");
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

	@Override
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ChSaPaycardModel model)
			throws Exception {
		List<Map<String, Object>> retlist = this.queryList(model);
		JSONObject headJson = createHead();
		if (null == headJson || headJson.isEmpty())
			throw new Exception("表头信息异常");
		HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
		huTCommonService.export(response, model.getName() + "工资卡导出", retlist, headInfo);

	}

	public static JSONObject createHead() {
		String head1 = "{" + "'hTitle':'中央医院-工资卡'," + "'heads':[" + "{" + "'field':'item'," + "'title':'项目',"
				+ "'rowS':0," + "'rowE':2," + "'colS':0," + "'colE':0" + "}," + "{" + "'field':'Jan'," + "'title':'一月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':1," + "'colE':1" + "}," + "{" + "'field':'Feb'," + "'title':'二月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':2," + "'colE':2" + "}," + "{" + "'field':'Mar'," + "'title':'三月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':3," + "'colE':3" + "}," + "{" + "'field':'Apr'," + "'title':'四月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':4," + "'colE':4" + "}," + "{" + "'field':'May'," + "'title':'五月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':5," + "'colE':5" + "}," + "{" + "'field':'Jun'," + "'title':'六月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':6," + "'colE':6" + "}," + "{" + "'field':'Jul'," + "'title':'七月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':7," + "'colE':7" + "}," + "{" + "'field':'Aug'," + "'title':'八月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':8," + "'colE':8" + "}," + "{" + "'field':'Sep'," + "'title':'九月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':9," + "'colE':9" + "}," + "{" + "'field':'Oct'," + "'title':'十月',"
				+ "'rowS':0," + "'rowE':2," + "'colS':10," + "'colE':10" + "}," + "{" + "'field':'Nov',"
				+ "'title':'十一月'," + "'rowS':0," + "'rowE':2," + "'colS':11," + "'colE':11" + "}," + "{"
				+ "'field':'Dec'," + "'title':'十二月'," + "'rowS':0," + "'rowE':2," + "'colS':12," + "'colE':12" + "},"
				+ "{" + "'field':'monthly'," + "'title':'月均'," + "'rowS':0," + "'rowE':2," + "'colS':13," + "'colE':13"
				+ "}," + "{" + "'field':'statisticalInterval'," + "'title':'统计区间'," + "'rowS':0," + "'rowE':2,"
				+ "'colS':14," + "'colE':14" + "}," + "{" + "'field':'high'," + "'title':'高位数'," + "'rowS':0,"
				+ "'rowE':2," + "'colS':15," + "'colE':15" + "}," + "{" + "'field':'median'," + "'title':'中位数',"
				+ "'rowS':0," + "'rowE':2," + "'colS':16," + "'colE':16" + "}," + "{" + "'field':'low',"
				+ "'title':'低位数'," + "'rowS':0," + "'rowE':2," + "'colS':17," + "'colE':17" + "}" + "]" + "}";
		return JSONObject.parseObject(head1);
	}
}
