package com.hdfs.olo.olo_web.salary.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IPayslipStatisticsBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipMapper;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipRewardMapper;
import com.hdfs.olo.olo_web.salary.mapper.PayslipStatisticsMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;

@DataSource("salary")
@Service("payslipStatisticsBiz")
public class PayslipStatisticsBizImpl implements IPayslipStatisticsBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private ChSaPayslipMapper chSaPayslipMapper;
	@Autowired
	private ChSaPayslipRewardMapper chSaPayslipRewardMapper;
	@Autowired
	private PayslipStatisticsMapper payslipStatisticsMapper;
	@Override
	public void queryPage(Page page,List<String> months) {
		//查询总条数和记录
		List<Map<String,Object>> list = null;
		Long count = this.payslipStatisticsMapper.queryCount((ChSaPayslipExtendModel)page.getModel());
		if(count>0) list = this.payslipStatisticsMapper.queryPage(page,(ChSaPayslipExtendModel)page.getModel(),months);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<Map<String,Object>>():list);
	}
	
	/**
	 * 通过条件检索查询的工资月份列表
	 * @param params
	 * @return
	 */
	@Override
	public List<String> queryMonthList(Page page)
	{
		return payslipStatisticsMapper.queryMonthList((ChSaPayslipExtendModel)page.getModel());
	}
	
	@Override
	public List<Map<String,Object>> queryListWithSerial(ChSaPayslipExtendModel model,List<String> months)
	{
		return payslipStatisticsMapper.queryListWithSerial(model, months);
	}
	
	//==============================================统计区域===================================================
	/**
	 * 用工类型月工资汇总表
	 * @param rewards	绩效项集合
	 * @param curMonth  统计年月
	 * @return
	 */
	@Override
	public List<Map<String,Object>> st4modality(String curMonth)
	{
		if (StringHelper.isNullOrEmpty(curMonth)) {
			return new ArrayList<Map<String,Object>>();
		}
		List<String> rewards = chSaPayslipRewardMapper.queryRewardList(curMonth, curMonth);
		List<Map<String,Object>> tdatas = payslipStatisticsMapper.st4modality(curMonth, rewards);
		if(null == tdatas || tdatas.size()<=0) return tdatas;
		Map<String,Object> cvt1Map = new HashMap<String,Object>();
		if(rewards.size()>0) {
			String item = null;
			for(int i=0;i<rewards.size();i++){
				item = rewards.get(i);
				cvt1Map.put(item, null);
			}
		}
		cvt1Map.put("modality", "全院合计");
		//{疗养院绩效=12808.00, modality=总院小计, 经核办绩效=109360.00, reward_totals=5616219.00, 慈佑颐康院绩效=18357.00, target_ymonth=2021-05, 经核办绩效1=0.00, 经核办绩效2=5255681.00, wage_totals=5044886.560000, 年薪制人员绩效=148053.00, 空调班绩效=7700.00, 放射科介入=64260.00, size=1707, payable_totals=10661105.560000}
		
		String modality = null;
		for(Map<String,Object> item:tdatas)
		{
			modality = String.valueOf(item.get("modality"));
			if(!"总院小计".equals(modality) && !"基层小计".equals(modality))
				continue;
			for(String key:item.keySet())
			{
				/*Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		        Matcher m = p.matcher(key);
		        if (!m.find()) continue;*/
		        if(null == cvt1Map.get(key)) {
		        	cvt1Map.put(key,item.get(key));
		        	continue;
		        }
		        if(item.get(key) instanceof BigDecimal) {
		        	cvt1Map.put(key, ((BigDecimal)cvt1Map.get(key)).add((BigDecimal)item.get(key)).setScale(2, BigDecimal.ROUND_HALF_UP));//.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		        }else if(item.get(key) instanceof Long) {
		        	cvt1Map.put(key, ((Long)cvt1Map.get(key)+(Long)item.get(key)));
		        }
			}
		}
		tdatas.add(tdatas.size(), cvt1Map);
		return tdatas;
	}
	
	/**
	 * 根据人员类别和工资汇总表项目进行检索汇总
	 * @param ymonth  统计年月
	 * @param modalitys 用工类型集合
	 * @param saSumProjects 工资汇总表项目集合
	 * @return
	 */
	@Override
	public List<Map<String,Object>> st4common(String ymonth,List<String> modalitys,List<String> saSumProjects){
		if(StringHelper.isNullOrEmpty(ymonth)) {
			return new ArrayList<>();
		}
		List<Map<String,Object>> datas = payslipStatisticsMapper.st4common(ymonth, modalitys, saSumProjects);
		if(null == datas || datas.size() == 0) return datas;
		String saSumProject = null;
		for(Map<String,Object> item:datas)
		{
			saSumProject = String.valueOf(item.get("sa_sum_project"));
			if(!"total".equals(saSumProject)) continue;
			item.put("sa_sum_project", "合计");
		}
		return datas;
	}
	
	/**
	 * 根据薪资年月统计大类
	 * @param ymonth  统计年月
	 * @return
	 */
	@Override
	public List<Map<String,Object>> st4class(String ymonth){
		if(StringHelper.isNullOrEmpty(ymonth)) {
			return new ArrayList<>();
		}
		List<Map<String,Object>> datas = payslipStatisticsMapper.st4class(ymonth);
		return datas;
	}
	
	/**
	 * 根据年份、用工形式和薪酬奖励项列表统计
	 * @param year  统计年份
	 * @return
	 */
	@Override
	public List<Map<String,Object>> st4year(String year){
		if(StringHelper.isNullOrEmpty(year)) {
			return new ArrayList<>();
		}
		//根据年份查询所有用工类型集合和所有薪资奖项
		List<String> modalitys = chSaPayslipMapper.querySelectFields("WK_MODALITY_CN", year);
		List<String> rewards = chSaPayslipRewardMapper.queryRewardList(year+"-01", year+"-12");
		
		List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
		if(null != modalitys && modalitys.size()>0) {
			List<Map<String,Object>> idatas = null;
			for(int i=0;i<modalitys.size();i++)
			{
				idatas = payslipStatisticsMapper.st4year(year, modalitys.get(i), rewards);
				datas.addAll(datas.size(), idatas);
			}
		}
		return datas;
	}
}
