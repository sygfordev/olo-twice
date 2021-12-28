package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmContractInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmDossierInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmEduHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmEduInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmFamilyMemberInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmImportRecordBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmIncdecInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmOtherInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmPositHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmPositInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmSpecProfeBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmStationBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmTitleHeadBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmTitleInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkExpeInfoBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkerBiz;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmImportRecordMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmDossierInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmFamilyMemberInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmImportRecordModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmIncdecInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmOthInfo4ImpModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmOtherInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSpecProfeModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmStationModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleHeadModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmTitleInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkExpeInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmOthInfo4ImpModel.Builder;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.ArchiveImportConst;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.CalendarUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.DayCompare;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/** 
 * Description: [人事档案导入记录表服务实现]
 * Created on 2021年04月15日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmImportRecordBiz")
public class ChPmImportRecordBizImpl implements IChPmImportRecordBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * <p>Discription:[人事档案导入记录表Mapper]</p>
	 */	
	@Autowired
	private ChPmImportRecordMapper chPmImportRecordMapper;
	
	@Autowired
	private IChPmWorkerBiz chPmWorkerBiz;
	@Autowired
	private IChPmEduInfoBiz chPmEduInfoBiz;
	@Autowired
	private IChPmEduHeadBiz chPmEduHeadBiz;
	@Autowired
	private IChPmPositInfoBiz chPmPositInfoBiz;
	@Autowired
	private IChPmPositHeadBiz chPmPositHeadBiz;
	@Autowired
	private IChPmWorkExpeInfoBiz chPmWorkExpeInfoBiz;
	@Autowired
	private IChPmTitleInfoBiz chPmTitleInfoBiz;
	@Autowired
	private IChPmTitleHeadBiz chPmTitleHeadBiz;
	@Autowired
	private IChPmSkillsInfoBiz chPmSkillsInfoBiz;
	@Autowired
	private IChPmSkillsHeadBiz chPmSkillsHeadBiz;
	@Autowired
	private IChPmStationBiz chPmStationBiz;
	@Autowired
	private IChPmContractInfoBiz chPmContractInfoBiz;
	@Autowired
	private IChPmFamilyMemberInfoBiz chPmFamilyMemberInfoBiz;
	@Autowired
	private IChPmSpecProfeBiz chPmSpecProfeBiz;
	@Autowired
	private IChPmOtherInfoBiz chPmOtherInfoBiz;
	@Autowired
	private IChPmDossierInfoBiz chPmDossierInfoBiz;
	@Autowired
	private IChPmIncdecInfoBiz chPmIncdecInfoBiz;
	
	
	@Autowired
	private IHuTCommonService huTCommonService;
	
	/**
	 * <p>Discription:[人事档案导入记录表数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 人事档案导入记录表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmImportRecordModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[人事档案导入记录表数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 人事档案导入记录表数据分页条件
	 * @param chPmImportRecordModel 人事档案导入记录表数据查询条件
	 * @param queryFields 人事档案导入记录表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmImportRecordModel model,
			String queryFields)throws Exception{
			
		List<ChPmImportRecordModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmImportRecordMapper.queryCount((ChPmImportRecordModel)page.getModel());
		if(count>0) list = this.chPmImportRecordMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChPmImportRecordModel>():list);
	}

	/**
	 * <p>Discription:[人事档案导入记录表数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param chPmImportRecordModel 人事档案导入记录表数据查询条件
	 * @param queryFields 人事档案导入记录表数据查询字段
	 * @return List<ChPmImportRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmImportRecordModel> queryList(ChPmImportRecordModel model,String queryFields)throws Exception{
		List<ChPmImportRecordModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmImportRecordMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[人事档案导入记录表数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param chPmImportRecordModel 人事档案导入记录表数据查询条件
	 * @return List<ChPmImportRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmImportRecordModel> queryList(ChPmImportRecordModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[人事档案导入记录表数据查询总条数]</p>
	 * Created on 2021年04月15日
	 * @param chPmImportRecordModel 人事档案导入记录表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmImportRecordModel model)throws Exception{
		return this.chPmImportRecordMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询人事档案导入记录表数据]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return ChPmImportRecordModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmImportRecordModel queryById(Long id)throws Exception{
		ChPmImportRecordModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmImportRecordMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[人事档案导入记录表数据新增]</p>
	 * Created on 2021年04月15日
	 * @param chPmImportRecordModel 人事档案导入记录表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmImportRecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmImportRecordMapper.insert(model);
		}
		return count;
	 }
	 
	 private static final Map<String,String> headerAlias;
	 static {
		 headerAlias = new HashMap<String,String>();
		 headerAlias.put("姓名", "name");
		 headerAlias.put("性别", "sex");
		 headerAlias.put("年龄", "age");
		 headerAlias.put("身份证号", "cardNo");
		 headerAlias.put("出生日期", "birthDay");
		 headerAlias.put("民族", "nation");
		 headerAlias.put("政治面貌", "politics");
		 headerAlias.put("首次工作时间", "firstWorkTime");
		 headerAlias.put("进入本单位时间", "intoLocalCompTime");
		 headerAlias.put("籍贯-省份", "nativePlaceProv");
		 headerAlias.put("籍贯-城市", "nativePlaceCity");
		 headerAlias.put("户口性质", "residenceType");
		 headerAlias.put("户口所在地-省份", "residenceBirthlandProv");
		 headerAlias.put("户口所在地-城市", "residenceBirthlandCity");
		 headerAlias.put("家庭住址-省份", "homeProv");
		 headerAlias.put("家庭住址-城市", "homeCity");
		 headerAlias.put("家庭住址详细地址", "homeAddr");
		 headerAlias.put("手机号", "telphoneNo");
		 headerAlias.put("邮箱", "mailBox");
		 headerAlias.put("调资类别", "salaryAdjustType");
	 }
	 /**
	 * 导入人事档案文件
	 * @param model
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,Object> doImport(ChPmImportRecordModel model,String filePath)throws Exception
	{
		ExcelReader reader = ExcelUtil.getReader(filePath);
		reader.setHeaderAlias(headerAlias);

		// 读取list时默认首个非空行为标题
		List<List<Object>> read = reader.read();

		List<Map<String, Object>> readAll = reader.readAll();
		for (Map<String, Object> map : readAll) {
			System.out.println(map.containsKey("userName"));
			System.out.println(map.containsKey("storageName"));
			System.out.println(map.containsKey("checkPerm"));
			System.out.println(map.containsKey("allotAuditPerm"));
		}
		return null;
	}
	
	/**
	 * <p>Discription:[人事档案导入记录表数据编辑]</p>
	 * Created on 2021年04月15日
	 * @param model 人事档案导入记录表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmImportRecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmImportRecordMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[人事档案导入记录表单条数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmImportRecordMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[人事档案导入记录表单条数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmImportRecordMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[人事档案导入记录表批量数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param ids 人事档案导入记录表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmImportRecordMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[人事档案导入记录表批量数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param ids 人事档案导入记录表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmImportRecordMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	//分片处理数量
	public static final Integer BATCH_SIZE = 500;
	final List tmpList = Arrays.asList("0,6,7,8,9".split(","));
	/**
	 * 文件导入 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Boolean,Object> doImport(ChPmImportRecordModel model,InputStream ins)throws Exception
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		//创建阅读器
		ExcelReader reader = ExcelUtil.getReader(ins);
		//导入文件检测
		logger.info(">>>>>>>开始文件导入前的检测<<<<<<<<<<");
		Integer type = model.getBatchType();
		Map<Class,Map<String,String>> clazzHeadMap = ArchiveImportConst.loadAdapterHead(type);
		if(null == clazzHeadMap || clazzHeadMap.isEmpty())
		{
			logger.info(">>>>>>>文件导入检测结束：未检测到导入表头配置<<<<<<<<<<");
			retMap.put(false, "未检测到导入表头配置！");
			return retMap;
		}
		Class clazz = null;
		Map<String,String> head = null;
		for(Class key:clazzHeadMap.keySet())
		{
			clazz = key;
			head = clazzHeadMap.get(key);
			break;
		}
		
		//检测表头
		Map<Boolean,Object> headCkMap = huTCommonService.check4Header(reader, head);
		if(headCkMap.containsKey(false)) 
			return headCkMap;
		
		Map<Boolean,Object> ckMap = huTCommonService.check4Import(reader, head, clazz);
		if(ckMap.containsKey(false)) {
			logger.info(">>>>>>>文件导入检测结束：检测失败<<<<<<<<<<");
			return ckMap;
		}
		logger.info(">>>>>>>文件导入检测结束：检测成功，开始导入<<<<<<<<<<");
		List<Object> modelList = (List<Object>)ckMap.get(true);
		
		//根据导入类型进行批量导入
		Map<String,Integer> insMap = null;
		switch(type)
		{
		case 0://基本信息
			insMap = hand4Type0(modelList);
			break;
		case 1://学历信息
			insMap = hand4Type1(modelList);
			break;
		case 2://工作经历
			insMap = hand4Type2(modelList);
			break;
		case 3://职务信息
			insMap = hand4Type3(modelList);
			break;
		case 4://职称信息
			insMap = hand4Type4(modelList);
			break;
		case 5://技能等级
			insMap = hand4Type5(modelList);
			break;
		case 6://现学历信息
			insMap = hand4Type6(modelList);
			break;
		case 7://现职务信息
			insMap = hand4Type7(modelList);
			break;
		case 8://现职称信息
			insMap = hand4Type8(modelList);
			break;
		case 9://现技能等级
			insMap = hand4Type9(modelList);
			break;
		case 10://用工及岗位
			insMap = hand4Type10(modelList);
			break;
		case 11://合同信息
			insMap = hand4Type11(modelList);
			break;
		case 12://家庭成员
			insMap = hand4Type12(modelList);
			break;
		case 13://特殊工种
			insMap = hand4Type13(modelList);
			break;
		case 14://其他:包含其他信息、档案信息和增减信息
			insMap = hand4Type14(modelList);
			break;
		}
		Integer succ = null == insMap.get("succ")?0:insMap.get("succ");
		Integer fail = null == insMap.get("fail")?0:insMap.get("fail");
		Integer exist = null == insMap.get("exist")?0:insMap.get("exist");
		Integer excep = null == insMap.get("excep")?0:insMap.get("excep");
		Integer allSize = null == insMap.get("allSize")?0:insMap.get("allSize");
		
		//封装导入记录对象
		model.setBatchNo("BT"+System.currentTimeMillis());
		model.setBatchType(type);
		model.setSucNum(Long.valueOf(succ));
		model.setFaiNum(Long.valueOf(fail+exist));
		model.setStatus(DictionaryUtil.KEY_NORMAL);
		model.setBatchMsg("成功数量:"+succ+";失败数量:"+fail+(tmpList.contains(type)?(";已存在数量:"+exist):"")+";异常数量:"+excep+";总数量:"+allSize);
		this.save(model);
		retMap.put(true, insMap);
		return retMap;
	}
	
	/**
	 * 批量入库-基本信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type0(List<Object> modelList)
	{
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		ChPmWorkerModel wModel = null;
		Object obj = null;
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		List<String> tmp4Icards = new ArrayList<String>();
		List<String> cardNos4Disinct = new ArrayList<String>();
		List<ChPmWorkerModel> workers = new ArrayList<ChPmWorkerModel>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			wModel = (ChPmWorkerModel)obj;
			if(cardNos4Disinct.contains(wModel.getCardNo()))
			{
				logger.info("数据文件中，身份证号["+wModel.getCardNo()+"]存在重复数据,默认取第一条！");
				continue;
			}
			//处理年限问题
			Integer workedYear = null;
			if(null != wModel.getFirstWorkTime()) {
				DayCompare wkdc = CalendarUtil.dayComparePrecise(wModel.getFirstWorkTime(), new Date());
				workedYear = null != wkdc?wkdc.getYear():null;
			}
			if(null != workedYear)wModel.setWorkedYear(workedYear);
			
			//处理年龄问题
			Integer age = null;
			if(null != wModel.getBirthDay()) {
				DayCompare wkdc = CalendarUtil.dayComparePrecise(wModel.getBirthDay(), new Date());
				age = null != wkdc?wkdc.getYear():null;
			}
			if(null != age)wModel.setAge(age);
			
			wModel.setStatus(DictionaryUtil.KEY_NORMAL);
			workers.add(wModel);
			tmp4Icards.add(wModel.getCardNo());
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != workers.size() && modelList.size() != (i+1))
				continue;
			
			if(tmp4Icards.size()<=0) continue;
			
			//去除已存在数据
			List<String> nos = chPmWorkerBiz.queryExisted(tmp4Icards);
			tmp4Icards.clear();
			if(nos.size()>0)
			{
				for(Iterator<ChPmWorkerModel> it = workers.iterator();it.hasNext();)
				{
					if(!nos.contains(it.next().getCardNo())) continue;
					it.remove();
				}
				existSize+=nos.size();
			}
			//执行入库操作
			try {
				int size = workers.size()>0?chPmWorkerBiz.saveBatch(workers):0;
				savedSize += size;
			}catch(Exception e)
			{
				failSize += workers.size();
				logger.error("单批次入库失败");
				e.printStackTrace();
			}
			workers.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-学历信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type1(List<Object> modelList)
	{
		Object obj = null;
		ChPmEduInfoModel eModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			eModel = (ChPmEduInfoModel)obj;
			if(!tmp4Icards.contains(eModel.getCardNo())) {
				tmp4Icards.add(eModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmEduInfoModel> edus = new ArrayList<ChPmEduInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			eModel = (ChPmEduInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(eModel.getCardNo()))
			{
				eModel.setStatus(DictionaryUtil.KEY_NORMAL);
				eModel.setWorkerId(card2IdMaps.get(eModel.getCardNo()));
				edus.add(eModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != edus.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = edus.size()>0?chPmEduInfoBiz.saveBatch(edus):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += edus.size();
			}
			edus.clear();
		}
		//如果存在导入成功数据，则启动排序功能
		if(savedSize>0) chPmEduInfoBiz.synEduInfoSortVal();
		
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-工作经历
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type2(List<Object> modelList)
	{
		Object obj = null;
		ChPmWorkExpeInfoModel weiModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			weiModel = (ChPmWorkExpeInfoModel)obj;
			if(!tmp4Icards.contains(weiModel.getCardNo())) {
				tmp4Icards.add(weiModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmWorkExpeInfoModel> weis = new ArrayList<ChPmWorkExpeInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			weiModel = (ChPmWorkExpeInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(weiModel.getCardNo()))
			{
				weiModel.setStatus(DictionaryUtil.KEY_NORMAL);
				weiModel.setWorkerId(card2IdMaps.get(weiModel.getCardNo()));
				weis.add(weiModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != weis.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = weis.size()>0?chPmWorkExpeInfoBiz.saveBatch(weis):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += weis.size();
			}
			weis.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-职务信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type3(List<Object> modelList)
	{
		Object obj = null;
		ChPmPositInfoModel piModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			piModel = (ChPmPositInfoModel)obj;
			if(!tmp4Icards.contains(piModel.getCardNo())) {
				tmp4Icards.add(piModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmPositInfoModel> pInfos = new ArrayList<ChPmPositInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			piModel = (ChPmPositInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(piModel.getCardNo()))
			{
				piModel.setStatus(DictionaryUtil.KEY_NORMAL);
				piModel.setWorkerId(card2IdMaps.get(piModel.getCardNo()));
				pInfos.add(piModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != pInfos.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = pInfos.size()>0?chPmPositInfoBiz.saveBatch(pInfos):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += pInfos.size();
			}
			pInfos.clear();
		}
		//如果存在导入成功数据，则启动排序功能
		if(savedSize>0) chPmPositInfoBiz.synPositInfoSortVal();
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-职称信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type4(List<Object> modelList)
	{
		Object obj = null;
		ChPmTitleInfoModel tiModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			tiModel = (ChPmTitleInfoModel)obj;
			if(!tmp4Icards.contains(tiModel.getCardNo())) {
				tmp4Icards.add(tiModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmTitleInfoModel> tInfos = new ArrayList<ChPmTitleInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			tiModel = (ChPmTitleInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(tiModel.getCardNo()))
			{
				tiModel.setStatus(DictionaryUtil.KEY_NORMAL);
				tiModel.setWorkerId(card2IdMaps.get(tiModel.getCardNo()));
				tInfos.add(tiModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != tInfos.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = tInfos.size()>0?chPmTitleInfoBiz.saveBatch(tInfos):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += tInfos.size();
			}
			tInfos.clear();
		}
		//如果存在导入成功数据，则启动排序功能
		if(savedSize>0) chPmTitleInfoBiz.synTitleInfoSortVal();
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	/**
	 * 批量入库-技能信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type5(List<Object> modelList)
	{
		Object obj = null;
		ChPmSkillsInfoModel siModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			siModel = (ChPmSkillsInfoModel)obj;
			if(!tmp4Icards.contains(siModel.getCardNo())) {
				tmp4Icards.add(siModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmSkillsInfoModel> sInfos = new ArrayList<ChPmSkillsInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			siModel = (ChPmSkillsInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(siModel.getCardNo()))
			{
				siModel.setStatus(DictionaryUtil.KEY_NORMAL);
				siModel.setWorkerId(card2IdMaps.get(siModel.getCardNo()));
				sInfos.add(siModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != sInfos.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = sInfos.size()>0?chPmSkillsInfoBiz.saveBatch(sInfos):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += sInfos.size();
			}
			sInfos.clear();
		}
		//如果存在导入成功数据，则启动排序功能
		if(savedSize>0) chPmSkillsInfoBiz.synSkillsInfoSortVal();
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-现学历信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type6(List<Object> modelList)
	{
		Object obj = null;
		ChPmEduHeadModel ehModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			ehModel = (ChPmEduHeadModel)obj;
			if(!tmp4Icards.contains(ehModel.getCardNo())) {
				tmp4Icards.add(ehModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmEduHeadBiz.queryExistByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			Long existHead = null;
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				existHead = (Long)item.get("head_exist");
				//若该职工的学历Head信息已存在，则直接跳过并记录
				if(1 == existHead){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, wkId);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		tmp4Icards.clear();
		List<ChPmEduHeadModel> ehInfos = new ArrayList<ChPmEduHeadModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			ehModel = (ChPmEduHeadModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(ehModel.getCardNo()) && 
					card2IdMaps.containsKey(ehModel.getCardNo()))
			{
				ehModel.setStatus(DictionaryUtil.KEY_NORMAL);
				ehModel.setWorkerId(card2IdMaps.get(ehModel.getCardNo()));
				ehInfos.add(ehModel);
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(ehModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != ehInfos.size() && modelList.size() != i)
				continue;
			if(ehInfos.size()<=0) continue;
			//执行入库操作
			try {
				int size = ehInfos.size()>0?chPmEduHeadBiz.saveBatch(ehInfos):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += ehInfos.size();
			}
			ehInfos.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
	
	/**
	 * 批量入库-现职务信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type7(List<Object> modelList){
		Object obj = null;
		ChPmPositHeadModel pModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			pModel = (ChPmPositHeadModel)obj;
			if(!tmp4Icards.contains(pModel.getCardNo())) {
				tmp4Icards.add(pModel.getCardNo());
			}
			
			//处理年限问题
			Integer treat4nowYears = null;
			if(null != pModel.getTreat4nowStartTime())
			{
				DayCompare c = CalendarUtil.dayComparePrecise(pModel.getTreat4nowStartTime(), new Date());
				treat4nowYears = c.getYear()+1;
			}
			if(null != treat4nowYears)pModel.setTreat4nowYears(treat4nowYears);
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmPositHeadBiz.queryExistByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			Long existHead = null;
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				existHead = (Long)item.get("head_exist");
				//若该职工的学历Head信息已存在，则直接跳过并记录
				if(1 == existHead){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, wkId);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		tmp4Icards.clear();
		List<ChPmPositHeadModel> pHeads = new ArrayList<ChPmPositHeadModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			pModel = (ChPmPositHeadModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(pModel.getCardNo()) && 
					card2IdMaps.containsKey(pModel.getCardNo()))
			{
				pModel.setStatus(DictionaryUtil.KEY_NORMAL);
				pModel.setWorkerId(card2IdMaps.get(pModel.getCardNo()));
				pHeads.add(pModel);
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(pModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != pHeads.size() && modelList.size() != i)
				continue;
			if(pHeads.size()<=0) continue;
			//执行入库操作
			try {
				int size = pHeads.size()>0?chPmPositHeadBiz.saveBatch(pHeads):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += pHeads.size();
			}
			pHeads.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
	/**
	 * 批量入库-现职称信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type8(List<Object> modelList){
		Object obj = null;
		ChPmTitleHeadModel thModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			thModel = (ChPmTitleHeadModel)obj;
			if(!tmp4Icards.contains(thModel.getCardNo())) {
				tmp4Icards.add(thModel.getCardNo());
			}
			
			//处理年限问题
			Integer title4nowGotYears = null;
			if(null != thModel.getTitle4nowGotTime())
			{
				DayCompare c = CalendarUtil.dayComparePrecise(thModel.getTitle4nowGotTime(), new Date());
				title4nowGotYears = c.getYear()+1;
			}
			if(null != title4nowGotYears)thModel.setTitle4nowGotYears(title4nowGotYears);
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmTitleHeadBiz.queryExistByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			Long existHead = null;
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				existHead = (Long)item.get("head_exist");
				//若该职工的学历Head信息已存在，则直接跳过并记录
				if(1 == existHead){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, wkId);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		tmp4Icards.clear();
		List<ChPmTitleHeadModel> thHeads = new ArrayList<ChPmTitleHeadModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			thModel = (ChPmTitleHeadModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(thModel.getCardNo()) && 
					card2IdMaps.containsKey(thModel.getCardNo()))
			{
				thModel.setStatus(DictionaryUtil.KEY_NORMAL);
				thModel.setWorkerId(card2IdMaps.get(thModel.getCardNo()));
				thHeads.add(thModel);
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(thModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != thHeads.size() && modelList.size() != i)
				continue;
			if(thHeads.size()<=0) continue;
			//执行入库操作
			try {
				int size = thHeads.size()>0?chPmTitleHeadBiz.saveBatch(thHeads):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += thHeads.size();
			}
			thHeads.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
	/**
	 * 批量入库-现技能等级
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type9(List<Object> modelList){
		Object obj = null;
		ChPmSkillsHeadModel shModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			shModel = (ChPmSkillsHeadModel)obj;
			if(!tmp4Icards.contains(shModel.getCardNo())) {
				tmp4Icards.add(shModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmSkillsHeadBiz.queryExistByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			Long existHead = null;
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				existHead = (Long)item.get("head_exist");
				//若该职工的学历Head信息已存在，则直接跳过并记录
				if(1 == existHead){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, wkId);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		tmp4Icards.clear();
		List<ChPmSkillsHeadModel> sHeads = new ArrayList<ChPmSkillsHeadModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			shModel = (ChPmSkillsHeadModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(shModel.getCardNo()) && 
					card2IdMaps.containsKey(shModel.getCardNo()))
			{
				shModel.setStatus(DictionaryUtil.KEY_NORMAL);
				shModel.setWorkerId(card2IdMaps.get(shModel.getCardNo()));
				sHeads.add(shModel);
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(shModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != sHeads.size() && modelList.size() != i)
				continue;
			if(sHeads.size()<=0) continue;
			//执行入库操作
			try {
				int size = sHeads.size()>0?chPmSkillsHeadBiz.saveBatch(sHeads):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += sHeads.size();
			}
			sHeads.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
	
	/**
	 * 批量入库-用工及岗位
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type10(List<Object> modelList){
		Object obj = null;
		ChPmStationModel sModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			sModel = (ChPmStationModel)obj;
			if(!tmp4Icards.contains(sModel.getCardNo())) {
				tmp4Icards.add(sModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmStationBiz.queryExistByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			Long existStation = null;
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				existStation = (Long)item.get("station_exist");
				//若该职工的用工及岗位信息已存在，则直接跳过并记录
				if(1 == existStation){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, wkId);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		tmp4Icards.clear();
		List<ChPmStationModel> stations = new ArrayList<ChPmStationModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			sModel = (ChPmStationModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(sModel.getCardNo()) && 
					card2IdMaps.containsKey(sModel.getCardNo()))
			{
				sModel.setStatus(DictionaryUtil.KEY_NORMAL);
				sModel.setWorkerId(card2IdMaps.get(sModel.getCardNo()));
				stations.add(sModel);
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(sModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != stations.size() && modelList.size() != i)
				continue;
			if(stations.size()<=0) continue;
			//执行入库操作
			try {
				int size = stations.size()>0?chPmStationBiz.saveBatch(stations):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += stations.size();
			}
			stations.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
	
	/**
	 * 批量入库-合同信息
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type11(List<Object> modelList)
	{
		Object obj = null;
		ChPmContractInfoModel contModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			contModel = (ChPmContractInfoModel)obj;
			if(!tmp4Icards.contains(contModel.getCardNo())) {
				tmp4Icards.add(contModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmContractInfoModel> conts = new ArrayList<ChPmContractInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			contModel = (ChPmContractInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(contModel.getCardNo()))
			{
				contModel.setStatus(DictionaryUtil.KEY_NORMAL);
				contModel.setWorkerId(card2IdMaps.get(contModel.getCardNo()));
				conts.add(contModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != conts.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = conts.size()>0?chPmContractInfoBiz.saveBatch(conts):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += conts.size();
			}
			conts.clear();
		}
		//如果存在导入成功数据，则启动排序功能
		if(savedSize>0) chPmContractInfoBiz.synContInfoSortVal();
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-家庭成员
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type12(List<Object> modelList)
	{
		Object obj = null;
		ChPmFamilyMemberInfoModel fModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			fModel = (ChPmFamilyMemberInfoModel)obj;
			if(!tmp4Icards.contains(fModel.getCardNo())) {
				tmp4Icards.add(fModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmFamilyMemberInfoModel> familys = new ArrayList<ChPmFamilyMemberInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			fModel = (ChPmFamilyMemberInfoModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(fModel.getCardNo()))
			{
				fModel.setStatus(DictionaryUtil.KEY_NORMAL);
				fModel.setWorkerId(card2IdMaps.get(fModel.getCardNo()));
				familys.add(fModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != familys.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = familys.size()>0?chPmFamilyMemberInfoBiz.saveBatch(familys):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += familys.size();
			}
			familys.clear();
		}
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	
	/**
	 * 批量入库-特殊工种
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type13(List<Object> modelList)
	{
		Object obj = null;
		ChPmSpecProfeModel spModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		//根据身份证号查询职工编号并赋值
		Map<String,Long> card2IdMaps = new HashMap<String,Long>();
		List<String> tmp4Icards = new ArrayList<String>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			spModel = (ChPmSpecProfeModel)obj;
			if(!tmp4Icards.contains(spModel.getCardNo())) {
				tmp4Icards.add(spModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryByCardNoBatch(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			String idcard = null;
			for(Map<String,Object> item:exists)
			{
				idcard = item.get("card_no")+"";
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, (Long)item.get("id"));
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size());
			retMap.put("exist", 0);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		int i=0;
		List<ChPmSpecProfeModel> specs = new ArrayList<ChPmSpecProfeModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			spModel = (ChPmSpecProfeModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(card2IdMaps.containsKey(spModel.getCardNo()))
			{
				spModel.setStatus(DictionaryUtil.KEY_NORMAL);
				spModel.setWorkerId(card2IdMaps.get(spModel.getCardNo()));
				specs.add(spModel);
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != specs.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int size = specs.size()>0?chPmSpecProfeBiz.saveBatch(specs):0;
				savedSize += size;
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += specs.size();
			}
			specs.clear();
		}
				
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("allSize", savedSize+existSize+failSize);
		return retMap;
	}
	/**
	 * 批量入库-其他（包含其他信息、人事档案信息和增减信息）
	 * @param modelList
	 * @return
	 */
	private Map<String,Integer> hand4Type14(List<Object> modelList){
		Object obj = null;
		ChPmOthInfo4ImpModel oModel = null;
		Map<String,Integer> retMap = new HashMap<String,Integer>();
		
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		
		Long oexist = null;
		Long dexist = null;
		Long iexist = null;
		
		//根据身份证号查询职工编号并赋值
		Map<String,Map<String,Object>> card2IdMaps = new HashMap<String,Map<String,Object>>();
		List<String> tmp4Icards = new ArrayList<String>();
		
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			oModel = (ChPmOthInfo4ImpModel)obj;
			if(!tmp4Icards.contains(oModel.getCardNo())) {
				tmp4Icards.add(oModel.getCardNo());
			}
			
			if(BATCH_SIZE != tmp4Icards.size() && modelList.size() != (i+1))
				continue;
			if(tmp4Icards.size()<=0) continue;
			List<Map<String,Object>> exists = chPmWorkerBiz.queryExist4OthByCardNos(tmp4Icards);
			tmp4Icards.clear();
			if(exists.size()<=0) continue;
			
			String idcard = null;Long wkId = null;
			for(Map<String,Object> item:exists)
			{
				wkId = (Long)item.get("id");
				idcard = item.get("card_no")+"";
				oexist = (Long)item.get("o_exist");
				dexist = (Long)item.get("d_exist");
				iexist = (Long)item.get("i_exist");
				//若该职工的用工及岗位信息已存在，则直接跳过并记录
				if(1 == oexist && 1 == dexist && 1 == iexist){
					existSize+=1;
					continue;
				}
				if(card2IdMaps.containsKey(idcard)) continue;
				card2IdMaps.put(idcard, item);
			}
		}
		if(card2IdMaps.size()<=0)
		{
			retMap.put("succ", 0);
			retMap.put("fail", modelList.size()-existSize);
			retMap.put("exist", existSize);
			retMap.put("allSize", modelList.size());
			return retMap;
		}
		
		
		
		int i=0;
		Long wkId = null;
		String idcard = null;
		tmp4Icards.clear();
		List<ChPmOtherInfoModel> others = new ArrayList<ChPmOtherInfoModel>();
		List<ChPmDossierInfoModel> dossiers = new ArrayList<ChPmDossierInfoModel>();
		List<ChPmIncdecInfoModel> incdecs = new ArrayList<ChPmIncdecInfoModel>();
		for(Iterator<Object> it = modelList.iterator();it.hasNext();)
		{
			i++;obj = it.next();
			oModel = (ChPmOthInfo4ImpModel)obj;
			
			//判断该条数据对应的职工编号是否存在
			if(!tmp4Icards.contains(oModel.getCardNo()) && 
					card2IdMaps.containsKey(oModel.getCardNo()))
			{
				Map<String,Object> mapps = card2IdMaps.get(oModel.getCardNo());
				wkId = (Long)mapps.get("id");
				idcard = mapps.get("card_no")+"";
				oexist = (Long)mapps.get("o_exist");
				dexist = (Long)mapps.get("d_exist");
				iexist = (Long)mapps.get("i_exist");
				
				oModel.setWorkerId(wkId);
				
				ChPmOthInfo4ImpModel.Builder inner = new Builder(oModel);
				if(0 == oexist) others.add(inner.buildOth());
				if(0 == dexist) dossiers.add(inner.buildDos());
				if(0 == iexist) incdecs.add(inner.buildIncD());
				
				//检索到有效数据后，将其记录在临时列表中
				tmp4Icards.add(oModel.getCardNo());
			}
			
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != others.size() && modelList.size() != i)
				continue;
			//执行入库操作
			try {
				int osize = others.size()>0?chPmOtherInfoBiz.saveBatch(others):0;
				int dsize = dossiers.size()>0?chPmDossierInfoBiz.saveBatch(dossiers):0;
				int isize = incdecs.size()>0?chPmIncdecInfoBiz.saveBatch(incdecs):0;
				savedSize += ((osize>dsize?osize:dsize)>isize?(osize>dsize?osize:dsize):isize);
			}catch(Exception e)
			{
				logger.error("单批次入库失败");
				e.printStackTrace();
				failSize += ((others.size()>dossiers.size()?others.size():dossiers.size())>incdecs.size()?(others.size()>dossiers.size()?others.size():dossiers.size()):incdecs.size());;
			}
			others.clear();dossiers.clear();incdecs.clear();
		}
		retMap.put("succ", savedSize);
		retMap.put("exist", existSize);
		retMap.put("fail", failSize);
		retMap.put("excep", modelList.size()-savedSize-existSize-failSize);
		retMap.put("allSize", modelList.size());
		return retMap;
	}
}
