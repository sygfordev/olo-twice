package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmFamilyMemberInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmFamilyMemberInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmFamilyMemberInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [家庭成员服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmFamilyMemberInfoBiz")
public class ChPmFamilyMemberInfoBizImpl implements IChPmFamilyMemberInfoBiz {
	
	/**
	 * <p>Discription:[家庭成员Mapper]</p>
	 */	
	@Autowired
	private ChPmFamilyMemberInfoMapper chPmFamilyMemberInfoMapper;
	
	/**
	 * <p>Discription:[家庭成员数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 家庭成员数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmFamilyMemberInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[家庭成员数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 家庭成员数据分页条件
	 * @param chPmFamilyMemberInfoModel 家庭成员数据查询条件
	 * @param queryFields 家庭成员数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmFamilyMemberInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmFamilyMemberInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmFamilyMemberInfoMapper.queryCount((ChPmFamilyMemberInfoModel)page.getModel());
		list = this.chPmFamilyMemberInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[家庭成员数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据查询条件
	 * @param queryFields 家庭成员数据查询字段
	 * @return List<ChPmFamilyMemberInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmFamilyMemberInfoModel> queryList(ChPmFamilyMemberInfoModel model,String queryFields)throws Exception{
		List<ChPmFamilyMemberInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmFamilyMemberInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[家庭成员数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据查询条件
	 * @return List<ChPmFamilyMemberInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmFamilyMemberInfoModel> queryList(ChPmFamilyMemberInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[家庭成员数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmFamilyMemberInfoModel model)throws Exception{
		return this.chPmFamilyMemberInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询家庭成员数据]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return ChPmFamilyMemberInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmFamilyMemberInfoModel queryById(Long id)throws Exception{
		ChPmFamilyMemberInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmFamilyMemberInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[家庭成员数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmFamilyMemberInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmFamilyMemberInfoMapper.insert(model);
		}
		return count;
	 }
	 /**
	 * <p>Discription:[家庭成员数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmFamilyMemberInfoModel> list)throws Exception
	{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmFamilyMemberInfoMapper.insertBatch(list);
		}
		return count;
	}
	/**
	 * <p>Discription:[家庭成员数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 家庭成员数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmFamilyMemberInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmFamilyMemberInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[家庭成员单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmFamilyMemberInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[家庭成员单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmFamilyMemberInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[家庭成员批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 家庭成员数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmFamilyMemberInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[家庭成员批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 家庭成员数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmFamilyMemberInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
