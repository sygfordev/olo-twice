package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmIncdecInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmIncdecInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmIncdecInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [增减情况服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmIncdecInfoBiz")
public class ChPmIncdecInfoBizImpl implements IChPmIncdecInfoBiz {
	
	/**
	 * <p>Discription:[增减情况Mapper]</p>
	 */	
	@Autowired
	private ChPmIncdecInfoMapper chPmIncdecInfoMapper;
	
	/**
	 * <p>Discription:[增减情况数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 增减情况数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmIncdecInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[增减情况数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 增减情况数据分页条件
	 * @param chPmIncdecInfoModel 增减情况数据查询条件
	 * @param queryFields 增减情况数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmIncdecInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmIncdecInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmIncdecInfoMapper.queryCount((ChPmIncdecInfoModel)page.getModel());
		list = this.chPmIncdecInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[增减情况数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据查询条件
	 * @param queryFields 增减情况数据查询字段
	 * @return List<ChPmIncdecInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmIncdecInfoModel> queryList(ChPmIncdecInfoModel model,String queryFields)throws Exception{
		List<ChPmIncdecInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmIncdecInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[增减情况数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据查询条件
	 * @return List<ChPmIncdecInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmIncdecInfoModel> queryList(ChPmIncdecInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[增减情况数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmIncdecInfoModel model)throws Exception{
		return this.chPmIncdecInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询增减情况数据]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return ChPmIncdecInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmIncdecInfoModel queryById(Long id)throws Exception{
		ChPmIncdecInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmIncdecInfoMapper.queryById(id,null);
		}
		return model;
	 }

	 /**
	 * <p>Discription:[根据id查询增减情况数据]</p>
	 * Created on 2021年03月29日
	 * @param wkId 职工编号
	 * @return ChPmIncdecInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmIncdecInfoModel queryByWkId(Long wkId)throws Exception
	{
		ChPmIncdecInfoModel model = null;
		if(!Objects.isNull(wkId)){
			model = this.chPmIncdecInfoMapper.queryByWkId(wkId,null);
		}
		return model;
	}
	
	/**
	 * <p>Discription:[增减情况数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChPmIncdecInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmIncdecInfoMapper.insert(model);
		}
		return model.getId();
	 }
	 /**
	 * <p>Discription:[增减情况数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Integer saveBatch(List<ChPmIncdecInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmIncdecInfoMapper.insertBatch(list);
		}
		return count;
	 }

	/**
	 * <p>Discription:[增减情况数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 增减情况数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmIncdecInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmIncdecInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[增减情况单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmIncdecInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[增减情况单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmIncdecInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[增减情况批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 增减情况数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmIncdecInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[增减情况批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 增减情况数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmIncdecInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
