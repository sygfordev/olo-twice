package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmOtherInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmOtherInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmOtherInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [其他信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmOtherInfoBiz")
public class ChPmOtherInfoBizImpl implements IChPmOtherInfoBiz {
	
	/**
	 * <p>Discription:[其他信息Mapper]</p>
	 */	
	@Autowired
	private ChPmOtherInfoMapper chPmOtherInfoMapper;
	
	/**
	 * <p>Discription:[其他信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 其他信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmOtherInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[其他信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 其他信息数据分页条件
	 * @param chPmOtherInfoModel 其他信息数据查询条件
	 * @param queryFields 其他信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmOtherInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmOtherInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmOtherInfoMapper.queryCount((ChPmOtherInfoModel)page.getModel());
		list = this.chPmOtherInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[其他信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据查询条件
	 * @param queryFields 其他信息数据查询字段
	 * @return List<ChPmOtherInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmOtherInfoModel> queryList(ChPmOtherInfoModel model,String queryFields)throws Exception{
		List<ChPmOtherInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmOtherInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[其他信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据查询条件
	 * @return List<ChPmOtherInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmOtherInfoModel> queryList(ChPmOtherInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[其他信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmOtherInfoModel model)throws Exception{
		return this.chPmOtherInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询其他信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return ChPmOtherInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmOtherInfoModel queryById(Long id)throws Exception{
		ChPmOtherInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmOtherInfoMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	 * <p>Discription:[根据职工编号查询其他信息数据]</p>
	 * Created on 2021年03月29日
	 * @param wkId 职工编号
	 * @return ChPmOtherInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmOtherInfoModel queryByWkId(Long wkId)throws Exception{
		ChPmOtherInfoModel model = null;
		if(!Objects.isNull(wkId)){
			model = this.chPmOtherInfoMapper.queryByWkId(wkId,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[其他信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChPmOtherInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmOtherInfoMapper.insert(model);
		}
		return model.getId();
	 }

	 /**
	 * <p>Discription:[其他信息数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Integer saveBatch(List<ChPmOtherInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmOtherInfoMapper.insertBatch(list);
		}
		return count;
	 }
	 
	/**
	 * <p>Discription:[其他信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 其他信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmOtherInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmOtherInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[其他信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmOtherInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[其他信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmOtherInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[其他信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 其他信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmOtherInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[其他信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 其他信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmOtherInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
