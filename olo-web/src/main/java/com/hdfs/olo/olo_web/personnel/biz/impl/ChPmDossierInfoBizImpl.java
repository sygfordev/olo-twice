package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmDossierInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmDossierInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmDossierInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
/** 
 * Description: [人事档案服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmDossierInfoBiz")
public class ChPmDossierInfoBizImpl implements IChPmDossierInfoBiz {
	
	/**
	 * <p>Discription:[人事档案Mapper]</p>
	 */	
	@Autowired
	private ChPmDossierInfoMapper chPmDossierInfoMapper;
	
	/**
	 * <p>Discription:[人事档案数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 人事档案数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmDossierInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[人事档案数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 人事档案数据分页条件
	 * @param chPmDossierInfoModel 人事档案数据查询条件
	 * @param queryFields 人事档案数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmDossierInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmDossierInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmDossierInfoMapper.queryCount((ChPmDossierInfoModel)page.getModel());
		list = this.chPmDossierInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[人事档案数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmDossierInfoModel 人事档案数据查询条件
	 * @param queryFields 人事档案数据查询字段
	 * @return List<ChPmDossierInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmDossierInfoModel> queryList(ChPmDossierInfoModel model,String queryFields)throws Exception{
		List<ChPmDossierInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmDossierInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[人事档案数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmDossierInfoModel 人事档案数据查询条件
	 * @return List<ChPmDossierInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmDossierInfoModel> queryList(ChPmDossierInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[人事档案数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmDossierInfoModel 人事档案数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmDossierInfoModel model)throws Exception{
		return this.chPmDossierInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询人事档案数据]</p>
	 * Created on 2021年03月29日
	 * @param id 人事档案数据id
	 * @return ChPmDossierInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmDossierInfoModel queryById(Long id)throws Exception{
		ChPmDossierInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmDossierInfoMapper.queryById(id,null);
		}
		return model;
	 }
	 /**
	 * <p>Discription:[根据id查询人事档案数据]</p>
	 * Created on 2021年03月29日
	 * @param wkId 职工编号
	 * @return ChPmDossierInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmDossierInfoModel queryByWkId(Long wkId)throws Exception{
		ChPmDossierInfoModel model = null;
		if(!Objects.isNull(wkId)){
			model = this.chPmDossierInfoMapper.queryByWkId(wkId,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[人事档案数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmDossierInfoModel 人事档案数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChPmDossierInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmDossierInfoMapper.insert(model);
		}
		return model.getId();
	 }
	 
	 /**
	 * <p>Discription:[人事档案数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmDossierInfoModel 人事档案数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Integer saveBatch(List<ChPmDossierInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmDossierInfoMapper.insertBatch(list);
		}
		return count;
	 }

	/**
	 * <p>Discription:[人事档案数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 人事档案数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmDossierInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmDossierInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[人事档案单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 人事档案数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmDossierInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[人事档案单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 人事档案数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmDossierInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[人事档案批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 人事档案数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmDossierInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[人事档案批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 人事档案数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmDossierInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
