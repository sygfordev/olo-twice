package com.hdfs.olo.olo_web.salary.biz.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustFormulaBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustRecordBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustSalaryBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaAdjustSalaryMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustFormulaModel;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustRecordModel;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustSalaryModel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/** 
 * Description: [调资表服务实现]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaAdjustSalaryBiz")
public class ChSaAdjustSalaryBizImpl implements IChSaAdjustSalaryBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IHuTCommonService huTCommonService;
	@Autowired
	private IChSaAdjustFormulaBiz chSaAdjustFormulaBiz;
	@Autowired
	private IChSaAdjustRecordBiz chSaAdjustRecordBiz;
	/**
	 * <p>Discription:[调资表Mapper]</p>
	 */	
	@Autowired
	private ChSaAdjustSalaryMapper chSaAdjustSalaryMapper;
	
	/**
	 * <p>Discription:[调资表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaAdjustSalaryModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[调资表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资表数据分页条件
	 * @param chSaAdjustSalaryModel 调资表数据查询条件
	 * @param queryFields 调资表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaAdjustSalaryModel model,
			String queryFields)throws Exception{
			
		List<ChSaAdjustSalaryModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaAdjustSalaryMapper.queryCount((ChSaAdjustSalaryModel)page.getModel());
		if(count>0) list = this.chSaAdjustSalaryMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaAdjustSalaryModel>():list);
	}

	/**
	 * <p>Discription:[调资表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustSalaryModel 调资表数据查询条件
	 * @param queryFields 调资表数据查询字段
	 * @return List<ChSaAdjustSalaryModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaAdjustSalaryModel> queryList(ChSaAdjustSalaryModel model,String queryFields)throws Exception{
		List<ChSaAdjustSalaryModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaAdjustSalaryMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[调资表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustSalaryModel 调资表数据查询条件
	 * @return List<ChSaAdjustSalaryModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaAdjustSalaryModel> queryList(ChSaAdjustSalaryModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[调资表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustSalaryModel 调资表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaAdjustSalaryModel model)throws Exception{
		return this.chSaAdjustSalaryMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询调资表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return ChSaAdjustSalaryModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaAdjustSalaryModel queryById(Long id)throws Exception{
		ChSaAdjustSalaryModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaAdjustSalaryMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[调资表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustSalaryModel 调资表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChSaAdjustSalaryModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSaAdjustSalaryMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调资表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaAdjustSalaryModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaAdjustSalaryMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调资表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustSalaryMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[调资表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustSalaryMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[调资表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustSalaryMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[调资表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustSalaryMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	private static final List<String> allAdjusts = new ArrayList<String>();
	private static final Map<String,String> headerAlias = new HashMap<String,String>();;
	static {
		headerAlias.put("调资类别", "salaryAdjustTypeCn");
		headerAlias.put("姓名", "name");
		headerAlias.put("身份证号", "cardNo");
		headerAlias.put("工资账人员编号", "wagesId");
		headerAlias.put("工资账序号", "wagesSeq");
		headerAlias.put("工资账姓名", "wagesName");
		headerAlias.put("工资账用工形式", "wagesModalityCn");
		headerAlias.put("工资账科室", "wagesDepart");
		headerAlias.put("原岗位工资标准", "wagesStandardBef");
		headerAlias.put("一级科室", "hosDepart1levelCn");
		headerAlias.put("二级科室", "hosDepart2levelCn");
		headerAlias.put("最高学历", "edu4max");
		headerAlias.put("第一学历", "edu4first");
		headerAlias.put("工资学历", "edu4wage");
		headerAlias.put("参加工作时间", "firstWorkTime");
		headerAlias.put("工龄", "workedYear");
		headerAlias.put("现行政职务", "posit4nowCn");
		headerAlias.put("任职时间", "posit4nowStartTime");
		headerAlias.put("现行政级别年限", "posit4nowYears");
		headerAlias.put("行政级别", "positLevelCn");
		headerAlias.put("行政级别代码", "positLevelCode");
		headerAlias.put("任正职时间", "onPrinPositStartTime");
		headerAlias.put("任正职年限", "onPrinPositYears");
		headerAlias.put("任副职时间", "onDeptPositStartTime");
		headerAlias.put("任副职年限", "onDeptPositYears");
		headerAlias.put("最高职称", "title4max");
		headerAlias.put("取得时间", "title4maxGotTime");
		headerAlias.put("职称年限", "title4maxGotYears");
		headerAlias.put("职称序列", "title4maxClassCn");
		headerAlias.put("职称级别", "title4maxLevelCn");
		
		allAdjusts.add("A1");
		allAdjusts.add("A2");
		allAdjusts.add("B1");
		allAdjusts.add("B2");
		allAdjusts.add("三类");
	}
	
	/**
	 * 文件导入 
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Map<Boolean,Object> doImport(InputStream ins,ChSaAdjustRecordModel recModel)throws Exception
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		//创建阅读器
		ExcelReader reader = ExcelUtil.getReader(ins);
		//导入文件检测
		logger.info(">>>>>>>开始文件导入前的检测<<<<<<<<<<");
		
		//检测表头
		List<List<Object>> heads = reader.read(0, 0);
		if(null == heads || heads.size()<=0)
		{
			retMap.put(false,"未检测到有表头信息存在!");
			return retMap;
		}
		List<Object> eHeads = heads.get(0);
		StringBuffer notExists = new StringBuffer();
		for(String key:headerAlias.keySet())
		{
			if(eHeads.contains(key)) continue;
			if(notExists.length()>0) notExists.append("<br>");
			notExists.append(key);
		}
		if(notExists.length()>0) {
			retMap.put(false, "请检查以下表头是否存在!<br>"+notExists);
			return retMap;
		}
		//设置表头别名
		reader.setHeaderAlias(headerAlias);
		//导入时必须存在项
		boolean isExistMustConfig = false;
		List<String> mustExists = new ArrayList<String>();
		Field[] allFields = ChSaAdjustSalaryModel.class.getDeclaredFields();
		for(Field item:allFields)
		{
			//获取实体配置中必须存在项
			isExistMustConfig = item.isAnnotationPresent(MustExist.class);
			MustExist exist = isExistMustConfig?item.getAnnotation(MustExist.class):null;
			if(isExistMustConfig && exist.value())
				mustExists.add(item.getName());
		}
		
		
		List<Map<String, Object>> datas = reader.read(0, 1, Integer.MAX_VALUE);
		//开始封装对象
		List<ChSaAdjustSalaryModel> list = new ArrayList<ChSaAdjustSalaryModel>();
		ChSaAdjustSalaryModel model = null;
		
		Map<String,Object> item = null;
		Map<Boolean,Object> rowObjVal = null;
		StringBuffer errMsg = new StringBuffer();
		//本次调资涉及到的调资类型
		List<String> curAdjustTypes = new ArrayList<String>();
		for(int i=0;i<datas.size();i++)
		{
			item = datas.get(i);
			rowObjVal = huTCommonService.packageModel(ChSaAdjustSalaryModel.class,item,i+1,null,null,null,mustExists);
			if(rowObjVal.containsKey(true))
			{
				model = (ChSaAdjustSalaryModel)rowObjVal.get(true);
				list.add(model);
				if(!curAdjustTypes.contains(model.getSalaryAdjustTypeCn()))
					curAdjustTypes.add(model.getSalaryAdjustTypeCn());
				continue;
			}
			
			Map<Integer,String> errMap = (Map<Integer,String>)rowObjVal.get(false);
			for(Integer colNum:errMap.keySet())
			{
				if(errMsg.length()>0)errMsg.append(",");
				errMsg.append("[第"+(i+1)+"行"+colNum+"列]<br>");
			}
		}
		
		if(errMsg.length()>0){
			retMap.put(false, "文件中以下单元格存在异常!<br>"+errMsg);
			return retMap;
		}
		if(list.size()<=0){
			retMap.put(false, "您上传的岗位类别文件中内容为空!");
			return retMap;
		}
		
		//根据文件中存在的调资类型获取计算计算公式
		List<ChSaAdjustFormulaModel> formulas = chSaAdjustFormulaBiz.queryList(curAdjustTypes);
		if(null == formulas || formulas.size()<=0) {
			retMap.put(false, "系统中尚未检测到有效的薪级分类数据!");
			return retMap;
		}
		//对公式列表进行分类
		Map<String,List<ChSaAdjustFormulaModel>> formuMap = new HashMap<String,List<ChSaAdjustFormulaModel>>();
		for(ChSaAdjustFormulaModel formu:formulas)
		{
			if(formuMap.containsKey(formu.getFormulaType()))
			{
				formuMap.get(formu.getFormulaType()).add(formu);
				continue;
			}
			List<ChSaAdjustFormulaModel> tlist = new ArrayList<ChSaAdjustFormulaModel>();
			tlist.add(formu);
			formuMap.put(formu.getFormulaType(), tlist);
		}
		
		//开始执行公式计算
		doHandCalculation(recModel,list,formuMap);
		retMap.put(true, "调资成功!");
		return retMap;
	}
	
	/**
	 * 	对调资进行计算
	 * @param models 调资对象集合
	 * @param formulas 调资公式集合
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public void doHandCalculation(ChSaAdjustRecordModel recModel,List<ChSaAdjustSalaryModel> models,
			Map<String,List<ChSaAdjustFormulaModel>> formuMap) throws Exception{
		//第一步：保存调资记录
		Long recId = chSaAdjustRecordBiz.save(recModel);
		
		String adjustType = null;
		List<ChSaAdjustFormulaModel> formus = null;
		for(ChSaAdjustSalaryModel item:models)
		{
			item.setRecordId(recId);
			adjustType = item.getSalaryAdjustTypeCn();
			if(StringHelper.isNullOrEmpty(adjustType)) {
				item.setRemark("调资类别为空或格式异常!");
				continue;
			}
			formus = formuMap.get(adjustType);
			if(null == formus || formus.size()<=0) {
				item.setRemark("该调资类别在薪级类别中不存在!");
				continue;
			}
			boolean isMatched = false;
			BigDecimal amount = null;
			String targetLevel = null;
			//使用到的公式
			List<ChSaAdjustFormulaModel> used = new ArrayList<ChSaAdjustFormulaModel>();
			for(ChSaAdjustFormulaModel formu:formus)
			{
				targetLevel = formu.getTargetLevelCn();
				switch(formu.getTarget())
				{
				case "职称":
					if(!targetLevel.equals(item.getTitle4max()))
						break;
					amount = loadAmountByYears(formu,item.getTitle4maxGotYears());
					item.setAdjust4title(amount);
					formu.setTargetVal(amount);
					used.add(formu);
					break;
				case "职务":
					if(!targetLevel.equals(item.getPosit4nowCn()))
						break;
					amount = loadAmountByYears(formu,item.getPosit4nowYears());
					item.setAdjust4posit(amount);
					formu.setTargetVal(amount);
					used.add(formu);
					break;
				case "学历":
					if(!targetLevel.equals(item.getEdu4max()))
						break;
					amount = formu.getFormula2down();
					item.setAdjust4edu(amount);
					formu.setTargetVal(amount);
					used.add(formu);
					break;
				default:
					break;
				}
			}
			//获取最大值，赋值给调整后工资标准
			Map<String,ChSaAdjustFormulaModel> maxMap = loadMax(item,used);
			if(null == maxMap) {
				item.setRemark("未检测到匹配项!");
				continue;
			}
			for(String key:maxMap.keySet())
			{
				item.setAdjustProof(key);
				item.setWagesStandardAft(maxMap.get(key).getTargetVal());
				item.setAdjustDiffe(maxMap.get(key).getGratdations());
				item.setFormulaId(maxMap.get(key).getId());
				item.setRecordId(recId);
				break;
			}
		}
		chSaAdjustSalaryMapper.insertBatch(models);
	}
	
	/**
	 * 根据年限和计算公式返回对应的金额
	 * @param formu
	 * @param years
	 * @return
	 */
	private BigDecimal loadAmountByYears(ChSaAdjustFormulaModel formu,Double years)
	{
		if(null == years) return null;
		else if(years>=13) return formu.getFormula13up();
		else if(years>=0 && years<3) return formu.getFormula2down();
		else if(years>=3 && years<5) return formu.getFormula3to4();
		else if(years>=5 && years<7) return formu.getFormula5to6();
		else if(years>=7 && years<9) return formu.getFormula7to8();
		else if(years>=9 && years<11) return formu.getFormula9to10();
		else if(years>=11 && years<13) return formu.getFormula11to12();
		else return null;
	}
	
	/**
	 * 获取调资后的最大值
	 * @param model
	 * @return
	 */
	private Map<String,ChSaAdjustFormulaModel> loadMax(ChSaAdjustSalaryModel model,List<ChSaAdjustFormulaModel> used)
	{
		Map<String,ChSaAdjustFormulaModel> retMap = new HashMap<String,ChSaAdjustFormulaModel>();
		List<Double> list = new ArrayList<Double>();
		Double amount4Edu = null == model.getAdjust4edu()?0:model.getAdjust4edu().doubleValue();
		Double amount4Posit = null == model.getAdjust4posit()?0:model.getAdjust4posit().doubleValue();
		Double amount4Title = null == model.getAdjust4title()?0:model.getAdjust4title().doubleValue();
		list.add(amount4Edu);
		list.add(amount4Posit);
		list.add(amount4Title);
		Double maxVal = Collections.max(list);
		if(0 == maxVal) return null;
		//遍历公式，找到匹配的（最大值）公式
		ChSaAdjustFormulaModel matched = null;
		for(ChSaAdjustFormulaModel item:used) {
			if(null == item.getTargetVal() ||
					maxVal != item.getTargetVal().doubleValue()) continue;
			matched = item;
			break;
		}
		if(null == matched) return null;
		retMap.put(matched.getTarget(), matched);
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
		if(StringHelper.isNullOrEmpty(column)) return null;
		List<String> result = chSaAdjustSalaryMapper.querySelectFields(column);
		return result;
	}
}
