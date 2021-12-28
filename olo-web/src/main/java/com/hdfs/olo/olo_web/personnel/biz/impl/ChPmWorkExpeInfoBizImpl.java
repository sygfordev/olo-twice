package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkExpeInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmWorkExpeInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkExpeInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
/** 
 * Description: [工作经历信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmWorkExpeInfoBiz")
public class ChPmWorkExpeInfoBizImpl implements IChPmWorkExpeInfoBiz {
	
	/**
	 * <p>Discription:[工作经历信息Mapper]</p>
	 */	
	@Autowired
	private ChPmWorkExpeInfoMapper chPmWorkExpeInfoMapper;
	
	/**
	 * <p>Discription:[工作经历信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 工作经历信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmWorkExpeInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[工作经历信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 工作经历信息数据分页条件
	 * @param chPmWorkExpeInfoModel 工作经历信息数据查询条件
	 * @param queryFields 工作经历信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmWorkExpeInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmWorkExpeInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmWorkExpeInfoMapper.queryCount((ChPmWorkExpeInfoModel)page.getModel());
		list = this.chPmWorkExpeInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[工作经历信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmWorkExpeInfoModel 工作经历信息数据查询条件
	 * @param queryFields 工作经历信息数据查询字段
	 * @return List<ChPmWorkExpeInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmWorkExpeInfoModel> queryList(ChPmWorkExpeInfoModel model,String queryFields)throws Exception{
		List<ChPmWorkExpeInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmWorkExpeInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[工作经历信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmWorkExpeInfoModel 工作经历信息数据查询条件
	 * @return List<ChPmWorkExpeInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmWorkExpeInfoModel> queryList(ChPmWorkExpeInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[工作经历信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmWorkExpeInfoModel 工作经历信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmWorkExpeInfoModel model)throws Exception{
		return this.chPmWorkExpeInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询工作经历信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 工作经历信息数据id
	 * @return ChPmWorkExpeInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmWorkExpeInfoModel queryById(Long id)throws Exception{
		ChPmWorkExpeInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmWorkExpeInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[工作经历信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmWorkExpeInfoModel 工作经历信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmWorkExpeInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmWorkExpeInfoMapper.insert(model);
		}
		return count;
	 }
	 /**
	 * <p>Discription:[工作经历信息数据批量 新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmWorkExpeInfoModel 工作经历信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int saveBatch(List<ChPmWorkExpeInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmWorkExpeInfoMapper.insertBatch(list);
		}
		return count;
	 }
	/**
	 * <p>Discription:[工作经历信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 工作经历信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmWorkExpeInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmWorkExpeInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[工作经历信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 工作经历信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmWorkExpeInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[工作经历信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 工作经历信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmWorkExpeInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[工作经历信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 工作经历信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmWorkExpeInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[工作经历信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 工作经历信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmWorkExpeInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
