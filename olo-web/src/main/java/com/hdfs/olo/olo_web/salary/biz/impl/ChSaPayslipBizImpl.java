package com.hdfs.olo.olo_web.salary.biz.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.ReflectionUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipMapper;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipRewardMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
/** 
 * Description: [薪资-工资单服务实现]
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaPayslipBiz")
public class ChSaPayslipBizImpl implements IChSaPayslipBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	
//	@Autowired
//	private ISystemDictBiz dictBiz;
	@Autowired
	private IHuTCommonService huTCommonService;
	/**
	 * <p>Discription:[薪资-工资单Mapper]</p>
	 */	
	@Autowired
	private ChSaPayslipMapper chSaPayslipMapper;
	@Autowired
	private ChSaPayslipRewardMapper chSaPayslipRewardMapper;
	/**
	 * <p>Discription:[薪资-工资单数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资单数据分页条件
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
	 * <p>Discription:[薪资-工资单数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资单数据分页条件
	 * @param chSaPayslipModel 薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段
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
		Long count = this.chSaPayslipMapper.queryCount((ChSaPayslipExtendModel)page.getModel());
		if(count>0) list = this.chSaPayslipMapper.queryPage(page,model,fields);
		//封装奖励数据
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaPayslipModel>():list);
	}
	
	/**
	 * <p>Discription:[薪资-工资单数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段
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
		list = this.chSaPayslipMapper.queryList(model,fields);
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
		list = this.chSaPayslipMapper.queryListWithSerial(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[薪资-工资单数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据查询条件
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
	 * <p>Discription:[薪资-工资单数据查询总条数]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaPayslipModel model)throws Exception{
		return this.chSaPayslipMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询薪资-工资单数据]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return ChSaPayslipModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaPayslipModel queryById(Long id)throws Exception{
		ChSaPayslipModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaPayslipMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	  * 根据身份证号和工资年月查询已存在的数据
	  * @param list
	  * @return
	  * @throws Exception
	  */
	 @Override
	 public List<String> queryExisted(List<String> list,String targetMonth)throws Exception
	 {
		 if(null == list || list.size()<=0 || 
				 StringHelper.isNullOrEmpty(targetMonth)) return null;
		 return this.chSaPayslipMapper.queryExisted(list,targetMonth);
	 }
	/**
	 * <p>Discription:[薪资-工资单数据新增]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChSaPayslipModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSaPayslipMapper.insert(model);
			
			Object amount = null;
			Double amountVal = null;
			ChSaPayslipRewardModel item = null;
			List<ChSaPayslipRewardModel> rewards = new ArrayList<ChSaPayslipRewardModel>();
			for(String key:model.getRewardMap().keySet())
			{
				item = new ChSaPayslipRewardModel();
				item.setCardNo(model.getCardNo());
				item.setTargetYearMonth(model.getNetTargetYearmonth());
				item.setRewardItem(key);
				amount = model.getRewardMap().get(key);
				amountVal = null!=amount?Double.parseDouble(amount+""):null;
				item.setRewardAmount(new BigDecimal(amountVal));
				rewards.add(item);
			}
			chSaPayslipRewardMapper.insertBatch(rewards);
		}
		return count;
	 }
	 /**
	 * <p>Discription:[薪资-工资单数据批量新增]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int saveBatch(List<ChSaPayslipModel> list)throws Exception{
		Integer count = 0;
		if(null == list || list.size()<=0)
			return count;
		
		count = this.chSaPayslipMapper.insertBatch(list);
		List<ChSaPayslipRewardModel> rewards = new ArrayList<ChSaPayslipRewardModel>();
		for(ChSaPayslipModel payslip:list)
		{
			Object amount = null;
			Double amountVal = null;
			ChSaPayslipRewardModel item = null;
			for(String key:payslip.getRewardMap().keySet())
			{
				item = new ChSaPayslipRewardModel();
				item.setCardNo(payslip.getCardNo());
				item.setTargetYearMonth(payslip.getNetTargetYearmonth());
				item.setRewardItem(key);
				item.setBtimpNo(payslip.getBtimpNo());
				amount = payslip.getRewardMap().get(key);
				amountVal = !StringHelper.isNullOrEmpty(String.valueOf(amount))?Double.parseDouble(amount+""):null;
				item.setRewardAmount(null!=amountVal?new BigDecimal(amountVal):null);
				rewards.add(item);
			}
		}
		chSaPayslipRewardMapper.insertBatch(rewards);
		return count;
	 }

	/**
	 * <p>Discription:[薪资-工资单数据编辑]</p>
	 * Created on 2021年05月14日
	 * @param model 薪资-工资单数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaPayslipModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaPayslipMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[薪资-工资单单条数据删除-逻辑]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaPayslipMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[薪资-工资单单条数据删除-物理]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaPayslipMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[薪资-工资单批量数据删除-物理]</p>
	 * Created on 2021年05月14日
	 * @param ids 薪资-工资单数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaPayslipMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[薪资-工资单批量数据删除-逻辑]</p>
	 * Created on 2021年05月14日
	 * @param ids 薪资-工资单数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaPayslipMapper.delBatchByIds4Logic(ids);
		}
		return count;
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
	
	private static final Integer BATCH_SIZE = 500;//分片处理数量
	private static final String REWARD_FIELD = "rewardMap";
	private static final List<String> matchHeads;//模糊匹配表头
	static {
		matchHeads = Arrays.asList("奖励:,奖励：".split(","));
	}
	/**
	 * 文件导入 
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Map<Boolean,Object> doImport(InputStream ins,String targetMonth,String batchNo)throws Exception
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		//创建阅读器
		ExcelReader reader = ExcelUtil.getReader(ins);
		//导入文件检测
		logger.info(">>>>>>>开始文件导入前的检测<<<<<<<<<<");
		
		//检测表头
		Map<Boolean,Object> headCkMap = huTCommonService.check4Header(reader, headerAlias);
		if(headCkMap.containsKey(false)) 
			return headCkMap;
		if(null != headerAlias && headerAlias.size()>0)
			reader.setHeaderAlias(headerAlias);
		
		boolean isExistMustConfig = false;
		List<String> mustExists = new ArrayList<String>();//导入时必须存在项
		
		Field[] allFields = ChSaPayslipModel.class.getDeclaredFields();
		for(Field item:allFields)
		{
			//获取实体配置中必须存在项
			isExistMustConfig = item.isAnnotationPresent(MustExist.class);
			MustExist exist = isExistMustConfig?item.getAnnotation(MustExist.class):null;
			if(isExistMustConfig && exist.value())
				mustExists.add(item.getName());
		}
		
		//校验导入必输项是否存在于导入表头中
		if(mustExists.size()>0) {
			boolean isAllExist4Must = true;
			for(String mItem:mustExists)
			{
				if(headerAlias.containsValue(mItem))
					continue;
				isAllExist4Must = false;
				break;
			}
			if(!isAllExist4Must) {
				retMap.put(false, "导入文件中缺失必输项!");
				return retMap;
			}
		}
		
		//读取文件中所有数据
		List<Map<String, Object>> readAll = reader.readAll();
		
		Map<String,Object> item = null;
		Map<Boolean,Object> rowObjVal = null;
		boolean isReadSucc = true;//是否全部读取成功
		List<String> readFailedRowInfo = new LinkedList<String>();//读取失败的行数
		StringBuffer errMsg = new StringBuffer();
		//最终封装的实体对象列表
		List<Object> modelList = new ArrayList<Object>();
		for (int i=0;i<readAll.size();i++) {
			item = readAll.get(i);
			rowObjVal = huTCommonService.packageModel(ChSaPayslipModel.class,item,i+1,null,null,null,mustExists);
			if(rowObjVal.containsKey(true))
			{
				Field f = ReflectionUtil.getDeclaredField(rowObjVal.get(true),REWARD_FIELD);
				ReflectionUtil.setFieldValByType(rowObjVal.get(true), f, loadRewardMap(item));
				modelList.add(rowObjVal.get(true));
				continue;
			}
			isReadSucc = false;
			Map<Integer,String> errMap = (Map<Integer,String>)rowObjVal.get(false);
			for(Integer colNum:errMap.keySet())
			{
				if(errMsg.length()>0)errMsg.append(",");
				errMsg.append("[第"+(i+1)+"行"+colNum+"列]");
			}
			readFailedRowInfo.add(errMsg.toString());
			break;
		}
		if(modelList.size()<=0 || !isReadSucc) {
			retMap.put(false, readFailedRowInfo.toString()+"数据匹配失败");
			return retMap;
		}
		
		ChSaPayslipModel model = null;
		Object obj = null;
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		List<String> tmp4Icards = new ArrayList<String>();
		List<ChSaPayslipModel> payslips = new ArrayList<ChSaPayslipModel>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			model = (ChSaPayslipModel)obj;
			if(tmp4Icards.contains(model.getCardNo()))
			{
				logger.info("[工资条]数据文件中，身份证号["+model.getCardNo()+"]存在重复数据,默认取第一条！");
				continue;
			}
			
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			model.setNetTargetYearmonth(targetMonth);
			model.setBtimpNo(batchNo);
			payslips.add(model);
			tmp4Icards.add(model.getCardNo());
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != payslips.size() && modelList.size() != (i+1))
				continue;
			
			if(tmp4Icards.size()<=0) continue;
			
			//去除已存在数据
			List<String> nos = this.queryExisted(tmp4Icards,targetMonth);
			tmp4Icards.clear();
			if(nos.size()>0)
			{
				for(Iterator<ChSaPayslipModel> it = payslips.iterator();it.hasNext();)
				{
					if(!nos.contains(it.next().getCardNo())) continue;
					it.remove();
				}
				existSize+=nos.size();
			}
			//执行入库操作
			int size = payslips.size()>0?this.saveBatch(payslips):0;
			savedSize += size;
			payslips.clear();
		}
		
		//根据导入类型进行批量导入
		Map<String,Integer> insMap = new HashMap<String,Integer>();
		insMap.put("succ", savedSize);
		insMap.put("exist", existSize);
		insMap.put("fail", failSize);
		insMap.put("excep",readAll.size()-(savedSize+existSize+failSize));
		insMap.put("allSize", savedSize+existSize+failSize);
		
		retMap.put(true, insMap);
		return retMap;
	}
	
	/**
	 * 通过模糊匹配表头，检索出所有匹配到的数据
	 * @param itemMap
	 * @return
	 */
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
	
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	@Override
	public List<String> loadSelectFields(String column)
	{
		return loadSelectFields(column,null);
	}
	@Override
	public List<String> loadSelectFields(String column,String year){
		if(StringHelper.isNullOrEmpty(column)) return null;
		List<String> result = chSaPayslipMapper.querySelectFields(column,year);
		return result;
	}
	
	/**
	 * 根据导入编号批量删除本批次导入数据
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delBatchByBtImpNo(String btimpNo)
	{
		if(StringHelper.isNullOrEmpty(btimpNo))
			return 0;
		chSaPayslipRewardMapper.delBatchByBtImpNo4Logic(btimpNo);
		return chSaPayslipMapper.delBatchByBtImpNo4Logic(btimpNo);
	}
	/**
	 * 根据身份证号查询工资单
	 * @param cardNo
	 * @return
	 */
	public List<ChSaPayslipModel> query4Wechat(String cardNo) throws Exception
	{
		if(StringHelper.isNullOrEmpty(cardNo))
			return null;
		return chSaPayslipMapper.query4Wechat(cardNo,null);
	}
	
	public List<ChSaPayslipModel> query4Wechat(String cardNo,String month) throws Exception{
		if(StringHelper.isNullOrEmpty(cardNo) || StringHelper.isNullOrEmpty(month))
			return null;
		return chSaPayslipMapper.query4Wechat(cardNo,month);
	}
}
