package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmEduHeadBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmEduHeadMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduHeadModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [学历信息头部表服务实现]
 * Created on 2021年03月28日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmEduHeadBiz")
public class ChPmEduHeadBizImpl implements IChPmEduHeadBiz {
	
	/**
	 * <p>Discription:[学历信息头部表Mapper]</p>
	 */	
	@Autowired
	private ChPmEduHeadMapper chPmEduHeadMapper;
	
	/**
	 * <p>Discription:[学历信息头部表数据分页查询]</p>
	 * Created on 2021年03月28日
	 * @param page 学历信息头部表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmEduHeadModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[学历信息头部表数据分页查询]</p>
	 * Created on 2021年03月28日
	 * @param page 学历信息头部表数据分页条件
	 * @param chPmEduHeadModel 学历信息头部表数据查询条件
	 * @param queryFields 学历信息头部表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmEduHeadModel model,
			String queryFields)throws Exception{
			
		List<ChPmEduHeadModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmEduHeadMapper.queryCount((ChPmEduHeadModel)page.getModel());
		list = this.chPmEduHeadMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[学历信息头部表数据不分页查询]</p>
	 * Created on 2021年03月28日
	 * @param chPmEduHeadModel 学历信息头部表数据查询条件
	 * @param queryFields 学历信息头部表数据查询字段
	 * @return List<ChPmEduHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmEduHeadModel> queryList(ChPmEduHeadModel model,String queryFields)throws Exception{
		List<ChPmEduHeadModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmEduHeadMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[学历信息头部表数据不分页查询]</p>
	 * Created on 2021年03月28日
	 * @param chPmEduHeadModel 学历信息头部表数据查询条件
	 * @return List<ChPmEduHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmEduHeadModel> queryList(ChPmEduHeadModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[学历信息头部表数据查询总条数]</p>
	 * Created on 2021年03月28日
	 * @param chPmEduHeadModel 学历信息头部表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmEduHeadModel model)throws Exception{
		return this.chPmEduHeadMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询学历信息头部表数据]</p>
	 * Created on 2021年03月28日
	 * @param id 学历信息头部表数据id
	 * @return ChPmEduHeadModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmEduHeadModel queryById(Long id)throws Exception{
		ChPmEduHeadModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmEduHeadMapper.queryById(id,null);
		}
		return model;
	 }
	 /**
	 * <p>Discription:[根据id查询学历信息头部表数据]</p>
	 * Created on 2021年03月28日
	 * @param wkId 职工编号
	 * @return ChPmEduHeadModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmEduHeadModel queryByWkId(Long wkId)throws Exception
	{
		ChPmEduHeadModel model = null;
		if(!Objects.isNull(wkId)){
			model = this.chPmEduHeadMapper.queryByWkId(wkId,null);
		}
		return model;
	}
		
	/**
	 * <p>Discription:[学历信息头部表数据新增]</p>
	 * Created on 2021年03月28日
	 * @param chPmEduHeadModel 学历信息头部表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmEduHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmEduHeadMapper.insert(model);
		}
		return count;
	 }

	 /**
	 * <p>Discription:[学历信息头部表数据批量新增]</p>
	 * Created on 2021年03月28日
	 * @param chPmEduHeadModel 学历信息头部表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmEduHeadModel> list)throws Exception
	{
		Integer count = 0;
		if(null!= list && list.size()>0){
			count = this.chPmEduHeadMapper.insertBatch(list);
		}
		return count;
	}
	/**
	 * <p>Discription:[学历信息头部表数据编辑]</p>
	 * Created on 2021年03月28日
	 * @param model 学历信息头部表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmEduHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmEduHeadMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[学历信息头部表单条数据删除-逻辑]</p>
	 * Created on 2021年03月28日
	 * @param id 学历信息头部表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmEduHeadMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[学历信息头部表单条数据删除-物理]</p>
	 * Created on 2021年03月28日
	 * @param id 学历信息头部表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmEduHeadMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[学历信息头部表批量数据删除-物理]</p>
	 * Created on 2021年03月28日
	 * @param ids 学历信息头部表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmEduHeadMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[学历信息头部表批量数据删除-逻辑]</p>
	 * Created on 2021年03月28日
	 * @param ids 学历信息头部表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmEduHeadMapper.delBatchByIds4Logic(ids);
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
		return chPmEduHeadMapper.queryExistByCardNos(cardNos);
	}
}
