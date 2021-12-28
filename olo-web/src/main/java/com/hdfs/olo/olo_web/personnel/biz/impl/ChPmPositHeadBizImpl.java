package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmPositHeadBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmPositHeadMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [职务信息头表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmPositHeadBiz")
public class ChPmPositHeadBizImpl implements IChPmPositHeadBiz {
	
	/**
	 * <p>Discription:[职务信息头表Mapper]</p>
	 */	
	@Autowired
	private ChPmPositHeadMapper chPmPositHeadMapper;
	
	/**
	 * <p>Discription:[职务信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 职务信息头表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmPositHeadModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[职务信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 职务信息头表数据分页条件
	 * @param chPmPositHeadModel 职务信息头表数据查询条件
	 * @param queryFields 职务信息头表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmPositHeadModel model,
			String queryFields)throws Exception{
			
		List<ChPmPositHeadModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmPositHeadMapper.queryCount((ChPmPositHeadModel)page.getModel());
		list = this.chPmPositHeadMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[职务信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据查询条件
	 * @param queryFields 职务信息头表数据查询字段
	 * @return List<ChPmPositHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmPositHeadModel> queryList(ChPmPositHeadModel model,String queryFields)throws Exception{
		List<ChPmPositHeadModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmPositHeadMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[职务信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据查询条件
	 * @return List<ChPmPositHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmPositHeadModel> queryList(ChPmPositHeadModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[职务信息头表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmPositHeadModel model)throws Exception{
		return this.chPmPositHeadMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询职务信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return ChPmPositHeadModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmPositHeadModel queryById(Long id)throws Exception{
		ChPmPositHeadModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmPositHeadMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	 * <p>Discription:[根据wkId查询职务信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return ChPmPositHeadModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmPositHeadModel queryByWkId(Long wkId)throws Exception
	 {
		 ChPmPositHeadModel model = null;
		 if(!Objects.isNull(wkId)){
			model = this.chPmPositHeadMapper.queryByWkId(wkId,null);
		 }
		 return model;
	 }

	/**
	 * <p>Discription:[职务信息头表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmPositHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmPositHeadMapper.insert(model);
		}
		return count;
	 }
	 
	 /**
	 * <p>Discription:[职务信息头表数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmPositHeadModel> list)throws Exception
	{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmPositHeadMapper.insertBatch(list);
		}
		return count;
	}

	/**
	 * <p>Discription:[职务信息头表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 职务信息头表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmPositHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmPositHeadMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[职务信息头表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmPositHeadMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[职务信息头表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmPositHeadMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[职务信息头表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 职务信息头表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmPositHeadMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[职务信息头表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 职务信息头表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmPositHeadMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据身份证号列表查询已存在的信息
	 * @param cardNos
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryExistByCardNos(List<String> cardNos)
	{
		if(null == cardNos || cardNos.size()<=0)
			return new ArrayList<Map<String,Object>>();
		return chPmPositHeadMapper.queryExistByCardNos(cardNos);
	}
}
