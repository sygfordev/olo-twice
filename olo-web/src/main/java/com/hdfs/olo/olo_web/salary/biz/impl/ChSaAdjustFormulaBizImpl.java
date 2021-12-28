package com.hdfs.olo.olo_web.salary.biz.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.alibaba.fastjson.JSON;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.ValidateCodeUtil;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustFormulaBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaAdjustFormulaMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustFormulaModel;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/** 
 * Description: [调资-公式表服务实现]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaAdjustFormulaBiz")
public class ChSaAdjustFormulaBizImpl implements IChSaAdjustFormulaBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IHuTCommonService huTCommonService;
	/**
	 * <p>Discription:[调资-公式表Mapper]</p>
	 */	
	@Autowired
	private ChSaAdjustFormulaMapper chSaAdjustFormulaMapper;
	
	/**
	 * <p>Discription:[调资-公式表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资-公式表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaAdjustFormulaModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[调资-公式表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资-公式表数据分页条件
	 * @param chSaAdjustFormulaModel 调资-公式表数据查询条件
	 * @param queryFields 调资-公式表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaAdjustFormulaModel model,
			String queryFields)throws Exception{
			
		List<ChSaAdjustFormulaModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaAdjustFormulaMapper.queryCount((ChSaAdjustFormulaModel)page.getModel());
		if(count>0) list = this.chSaAdjustFormulaMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaAdjustFormulaModel>():list);
	}

	/**
	 * <p>Discription:[调资-公式表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustFormulaModel 调资-公式表数据查询条件
	 * @param queryFields 调资-公式表数据查询字段
	 * @return List<ChSaAdjustFormulaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaAdjustFormulaModel> queryList(ChSaAdjustFormulaModel model,String queryFields)throws Exception{
		List<ChSaAdjustFormulaModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaAdjustFormulaMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[调资-公式表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustFormulaModel 调资-公式表数据查询条件
	 * @return List<ChSaAdjustFormulaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaAdjustFormulaModel> queryList(ChSaAdjustFormulaModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[调资-公式表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustFormulaModel 调资-公式表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaAdjustFormulaModel model)throws Exception{
		return this.chSaAdjustFormulaMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询调资-公式表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return ChSaAdjustFormulaModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaAdjustFormulaModel queryById(Long id)throws Exception{
		ChSaAdjustFormulaModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaAdjustFormulaMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[调资-公式表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustFormulaModel 调资-公式表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChSaAdjustFormulaModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSaAdjustFormulaMapper.insert(model);
		}
		return count;
	 }
	 
	/**
	 * <p>Discription:[调资-公式表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资-公式表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaAdjustFormulaModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaAdjustFormulaMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调资-公式表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustFormulaMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[调资-公式表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustFormulaMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[调资-公式表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资-公式表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustFormulaMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[调资-公式表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资-公式表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustFormulaMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	private static final List<String> fixTargets = new ArrayList<String>();
	private static final Map<String,String> headerAlias = new HashMap<String,String>();
	static {
		headerAlias.put("职称\\职务", "target");
		headerAlias.put("级别", "targetLevelCn");
		headerAlias.put("2年以下", "formula2down");
		headerAlias.put("3-4年", "formula3to4");
		headerAlias.put("5-6年", "formula5to6");
		headerAlias.put("7-8年", "formula7to8");
		headerAlias.put("9-10年", "formula9to10");
		headerAlias.put("11-12年", "formula11to12");
		headerAlias.put("13年以上", "formula13up");
		headerAlias.put("级差", "gratdations");
		
		fixTargets.add("职称");
		fixTargets.add("职务");
		fixTargets.add("学历");
	}
	
	
	/**
	 * 文件导入 
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Map<Boolean,Object> doImport(InputStream ins,String formulaType)throws Exception
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		//创建阅读器
		ExcelReader reader = ExcelUtil.getReader(ins);
		//导入文件检测
		logger.info(">>>>>>>开始文件导入前的检测<<<<<<<<<<");
		
		//检测表头
		List<List<Object>> heads = reader.read(1, 1);
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
		Field[] allFields = ChSaAdjustFormulaModel.class.getDeclaredFields();
		for(Field item:allFields)
		{
			//获取实体配置中必须存在项
			isExistMustConfig = item.isAnnotationPresent(MustExist.class);
			MustExist exist = isExistMustConfig?item.getAnnotation(MustExist.class):null;
			if(isExistMustConfig && exist.value())
				mustExists.add(item.getName());
		}
		
		
		List<Map<String, Object>> datas = reader.read(1, 2, Integer.MAX_VALUE);
		String str = JSON.toJSONString(datas);
		logger.info(str);
		//开始封装对象
		List<ChSaAdjustFormulaModel> list = new ArrayList<ChSaAdjustFormulaModel>();
		ChSaAdjustFormulaModel model = null;
		
		Map<String,Object> item = null;
		Map<Boolean,Object> rowObjVal = null;
		String targetCn = null;
		String targetLevelCn = null;
		StringBuffer errMsg = new StringBuffer();
		String uniqueKey = ValidateCodeUtil.genCode(20);
		outLoop:
		for(int i=0;i<datas.size();i++)
		{
			item = datas.get(i);
			rowObjVal = huTCommonService.packageModel(ChSaAdjustFormulaModel.class,item,i+1,null,null,null,mustExists);
			if(rowObjVal.containsKey(true))
			{
				model = (ChSaAdjustFormulaModel)rowObjVal.get(true);
				model.setFormulaType(formulaType);
				if(!StringHelper.isNullOrEmpty(model.getTarget()))
					targetCn = model.getTarget();
				else
					model.setTarget(targetCn);
				//判断是否存在非 职称|职务|学历  项
				if(!fixTargets.contains(model.getTarget()))
				{
					errMsg.append("上传的文件中存在职称、职务和学历外的薪级项!");
					break;
				}
				targetLevelCn = model.getTargetLevelCn();
				if(StringHelper.isNullOrEmpty(targetLevelCn))
				{
					errMsg.append(model.getTarget()+"中存在为空的等级!");
					break;
				}
				targetLevelCn = targetLevelCn.replace("/", "、").replace("\\", "、");
				if(!targetLevelCn.contains("、"))
				{
					model.setUniqueKey(uniqueKey);
					list.add(model);
					continue;
				}
				String[] levels = targetLevelCn.split("、");
				if(null == levels || levels.length<=0) continue;
				ChSaAdjustFormulaModel clone = null;
				for(String lev:levels)
				{
					clone = model.clone();
					clone.setTargetLevelCn(lev);
					if(StringHelper.isNullOrEmpty(targetLevelCn))
					{
						errMsg.append(model.getTarget()+"中存在为空的等级!");
						break outLoop;
					}
					clone.setUniqueKey(uniqueKey);
					list.add(clone);
					continue;
				}
				continue;
			}
			
			Map<Integer,String> errMap = (Map<Integer,String>)rowObjVal.get(false);
			for(Integer colNum:errMap.keySet())
			{
				if(errMsg.length()>0)errMsg.append(",");
				errMsg.append("[第"+(i+1)+"行"+colNum+"列]<br>");
			}
		}
		
		if(errMsg.length()>0)
		{
			retMap.put(false, "文件中以下单元格存在异常!<br>"+errMsg);
			return retMap;
		}
		chSaAdjustFormulaMapper.delByFormulaType4Logic(formulaType);
		chSaAdjustFormulaMapper.insertBatch(list);
		
		retMap.put(true, "导入成功!");
		return retMap;
	}
	
	/**
	 * <p>Discription:[调资-公式表数据根据公式类型进行查询]</p>
	 * Created on 2021年05月25日
	 * @param types 公式类型  A1 A2 B1 B2 三类
	 * @return List<ChSaAdjustFormulaModel>列表数据							       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaAdjustFormulaModel> queryList(List<String> types)throws Exception{
		if(null == types || types.isEmpty()) 
			return new ArrayList<ChSaAdjustFormulaModel>();
		return this.chSaAdjustFormulaMapper.queryListByTypes(types);
	}
	
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别
	 * @param recordId
	 * @return
	 */
	public List<Map<String,Object>> queryAdjustTypeAndUniqueKey(Long recordId)
	{
		if(null == recordId) return new ArrayList<Map<String,Object>>();
		return chSaAdjustFormulaMapper.queryAdjustTypeAndUniqueKey(recordId);
	}
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别（计算公式）
	 * @param recordId
	 * @return
	 */
	@Override
	public List<ChSaAdjustFormulaModel> queryByAdjustTypeAndUniqueKey(String adjustType,String uniqueKey)
	{
		if(StringHelper.isNullOrEmpty(adjustType) ||
				StringHelper.isNullOrEmpty(uniqueKey)) 
			return new ArrayList<ChSaAdjustFormulaModel>();
		return chSaAdjustFormulaMapper.queryByAdjustTypeAndUniqueKey(adjustType,uniqueKey);
	}
}
