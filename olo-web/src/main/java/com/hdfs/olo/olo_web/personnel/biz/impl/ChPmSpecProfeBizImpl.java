package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmSpecProfeBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmSpecProfeMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmSpecProfeModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [特殊工种服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmSpecProfeBiz")
public class ChPmSpecProfeBizImpl implements IChPmSpecProfeBiz {
	
	/**
	 * <p>Discription:[特殊工种Mapper]</p>
	 */	
	@Autowired
	private ChPmSpecProfeMapper chPmSpecProfeMapper;
	
	/**
	 * <p>Discription:[特殊工种数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 特殊工种数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmSpecProfeModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[特殊工种数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 特殊工种数据分页条件
	 * @param chPmSpecProfeModel 特殊工种数据查询条件
	 * @param queryFields 特殊工种数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmSpecProfeModel model,
			String queryFields)throws Exception{
			
		List<ChPmSpecProfeModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmSpecProfeMapper.queryCount((ChPmSpecProfeModel)page.getModel());
		list = this.chPmSpecProfeMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[特殊工种数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSpecProfeModel 特殊工种数据查询条件
	 * @param queryFields 特殊工种数据查询字段
	 * @return List<ChPmSpecProfeModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmSpecProfeModel> queryList(ChPmSpecProfeModel model,String queryFields)throws Exception{
		List<ChPmSpecProfeModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmSpecProfeMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[特殊工种数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSpecProfeModel 特殊工种数据查询条件
	 * @return List<ChPmSpecProfeModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmSpecProfeModel> queryList(ChPmSpecProfeModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[特殊工种数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmSpecProfeModel 特殊工种数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmSpecProfeModel model)throws Exception{
		return this.chPmSpecProfeMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询特殊工种数据]</p>
	 * Created on 2021年03月29日
	 * @param id 特殊工种数据id
	 * @return ChPmSpecProfeModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmSpecProfeModel queryById(Long id)throws Exception{
		ChPmSpecProfeModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmSpecProfeMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[特殊工种数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSpecProfeModel 特殊工种数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmSpecProfeModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmSpecProfeMapper.insert(model);
		}
		return count;
	 }
	 /**
	 * <p>Discription:[特殊工种数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSpecProfeModel 特殊工种数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmSpecProfeModel> list)throws Exception
	{
		Integer count = 0;
		if(null!=list && list.size()>0){
			count = this.chPmSpecProfeMapper.insertBatch(list);
		}
		return count;
	}

	/**
	 * <p>Discription:[特殊工种数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 特殊工种数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmSpecProfeModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmSpecProfeMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[特殊工种单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 特殊工种数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmSpecProfeMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[特殊工种单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 特殊工种数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmSpecProfeMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[特殊工种批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 特殊工种数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmSpecProfeMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[特殊工种批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 特殊工种数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmSpecProfeMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
