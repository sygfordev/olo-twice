package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPaycardBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPaycardMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;

/** 
 * Description: [薪资-工资卡服务实现]
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaPaycardBiz")
public class ChSaPaycardBizImpl implements IChSaPaycardBiz {


	@Autowired
	private ChSaPaycardMapper chSaPaycardMapper;
	
	
	/**
	 * <p>Discription:[薪资-工资卡数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资卡数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaPayslipExtendModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[薪资-工资卡数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资卡数据分页条件
	 * @param chSaPayslipModel 薪资-工资卡数据查询条件
	 * @param queryFields 薪资-工资卡数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaPayslipExtendModel model,
			String queryFields)throws Exception{
			
		List<ChSaPayslipModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaPaycardMapper.queryCount((ChSaPayslipExtendModel)page.getModel());
		if(count>0) list = this.chSaPaycardMapper.queryPage(page,model,fields);
		//封装奖励数据
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaPayslipModel>():list);
	}
	
	/**
	 * <p>Discription:[薪资-工资卡数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资卡数据查询条件
	 * @param queryFields 薪资-工资卡数据查询字段
	 * @return List<ChSaPayslipModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaPayslipModel> queryList(ChSaPayslipModel model,String queryFields)throws Exception{
		List<ChSaPayslipModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaPaycardMapper.queryList(model,fields);
		return list;
	}
	
	@Override
	public List<ChSaPayslipModel> queryListWithSerial(ChSaPayslipExtendModel model, String queryFields)throws Exception
	{
		List<ChSaPayslipModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaPaycardMapper.queryListWithSerial(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[薪资-工资卡数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资卡数据查询条件
	 * @return List<ChSaPayslipModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaPayslipModel> queryList(ChSaPayslipModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }
	 @Override
	 public	List<ChSaPayslipModel> queryListWithSerial(ChSaPayslipExtendModel model)throws Exception
	 {
	 	return queryListWithSerial(model,null);
	 }

	/**
	 * <p>Discription:[薪资-工资卡数据查询总条数]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资卡数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaPayslipModel model)throws Exception{
		return this.chSaPaycardMapper.queryCount(model);
	 }
	 
	//********************以下为扩展方法***********************
	
	private static final Map<String,String> headerAlias = new HashMap<String,String>();
	static {
		headerAlias.put("姓名", "name");
		headerAlias.put("身份证号", "cardNo");
		headerAlias.put("银行卡号", "bankCardNo");
		headerAlias.put("手机号码", "mobileNo");
		headerAlias.put("人员类别", "wkModalityCn");
		headerAlias.put("人员编号", "wagesId");
		headerAlias.put("职务", "positCn");//20210625  由职务工种改为职务
		headerAlias.put("职称", "titleCn");
		headerAlias.put("医院支部", "hosBranchCn");
		headerAlias.put("一级科室", "hosDepart1levelCn");
		headerAlias.put("二级科室", "hosDepart2levelCn");
		headerAlias.put("部门类别", "departClassCn");
		headerAlias.put("部门类别属性", "departClassPop");
		//headerAlias.put("职务", "positCn");
		headerAlias.put("岗位", "stationCn");
		headerAlias.put("岗位类型", "stationTypeCn");
		headerAlias.put("岗位状态", "stationStatusCn");
		headerAlias.put("岗位序列", "stationSeqCn");
		headerAlias.put("职称序列", "titleClassCn");
		headerAlias.put("职称及技能等级级别", "skillsLevelCn");
		headerAlias.put("现学历", "eduLev4nowCn");
		headerAlias.put("年薪制人员", "yearlySalaryMan");
		headerAlias.put("工资汇总表项目", "saSumProject");
		headerAlias.put("报工系统部门分类", "rptWkDepClass");
		headerAlias.put("标准工数", "manwkStandard");
		headerAlias.put("出勤工数", "manwkAttend");
		headerAlias.put("旷工工数", "manwkMiner");
		headerAlias.put("离职工数", "manwkQuit");
		headerAlias.put("病假工数", "manwkSick");
		headerAlias.put("产假工数", "manwkMaternity");
		headerAlias.put("事假工数", "manwkPrivpassion");
		headerAlias.put("加班工数", "manwkOvertime");
		headerAlias.put("岗位工资标准", "wageStandard4posit");
		headerAlias.put("岗位日工资", "wageDay4posit");
		headerAlias.put("出勤工资", "wageAttend");
		headerAlias.put("病假工资", "wageSick");
		headerAlias.put("岗位工资合计", "wagePositTotal");
		headerAlias.put("夜班费", "wageNightShift");
		headerAlias.put("加班工资", "wageOvertime");
		headerAlias.put("年功工资", "wageYearg");
		headerAlias.put("卫生津贴", "allowanceHygiene");
		headerAlias.put("纠错工资", "wageErrorCorrent");
		headerAlias.put("电补", "supp4tel");
		headerAlias.put("交通补贴", "supp4traffic");
		headerAlias.put("下矿（井、乡）补贴", "supp4mining");
		headerAlias.put("其他补贴", "supp4other");
		headerAlias.put("大学生生活补贴", "supp4univeStuLife");
		headerAlias.put("其他", "supp4oth");
		headerAlias.put("津补贴合计", "suppTotal");
		headerAlias.put("基础工资合计", "wageTotal");//20210625  由工资合计改为基础工资合计
		headerAlias.put("绩效工资合计", "wageJxTotal");
		headerAlias.put("应发合计", "wagePayableTotal");
		headerAlias.put("养老保险", "bxPension");
		headerAlias.put("医疗保险", "bxMedical");
		headerAlias.put("失业保险", "bxUnemploy");
		headerAlias.put("大病保险", "bxSeriousIllness");
		headerAlias.put("住房公积金", "bxHousfund");
		headerAlias.put("企业年金", "bxAnnualCorpGold");
		headerAlias.put("社保扣款合计", "bxTotal");
		headerAlias.put("水电费", "cutWater2elect");
		headerAlias.put("卫生费", "cutHygiene");
		headerAlias.put("其他扣款", "cutOther");
		headerAlias.put("职工欠款", "cutArrears");
		headerAlias.put("扣款合计", "cutTotal");
		headerAlias.put("计税工资", "taxableWage");
		headerAlias.put("个税", "taxIncomePersonal");
		headerAlias.put("实发合计", "netSalary");//20210625  由实发工资改为实发合计
	}
	
	private static final List<String> matchHeads;//模糊匹配表头
	static {
		matchHeads = Arrays.asList("奖励:,奖励：".split(","));
	}
	
	/**
	 * 通过模糊匹配表头，检索出所有匹配到的数据
	 * @param itemMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private Map<String,Object> loadRewardMap(Map<String,Object> itemMap)
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		if(null == itemMap || itemMap.isEmpty()) return retMap;
		String realKey = null;
		for(String key:itemMap.keySet())
		{
			String matchKey = "";
			for(String match:matchHeads)
			{
				if(!key.startsWith(match))continue;
				matchKey = match;
				break;
			}
			if(StringHelper.isNullOrEmpty(matchKey))continue;
			realKey = key.replace(matchKey, "").trim();
			//若奖项除了匹配字段外无任何信息，则忽略该条奖项
			if(StringHelper.isNullOrEmpty(realKey))continue;
			retMap.put(realKey, itemMap.get(key));
		}
		return retMap;
	}
	

}
