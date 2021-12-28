package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkerBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmWorkerMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [医院职工表服务实现]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmWorkBiz")
public class ChPmWorkerBizImpl implements IChPmWorkerBiz {
	
	/**
	 * <p>Discription:[医院职工表Mapper]</p>
	 */	
	@Autowired
	private ChPmWorkerMapper chPmWorkMapper;
	
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmWorkerModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @param queryFields 医院职工表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmWorkerModel model,
			String queryFields)throws Exception{
			
		List<ChPmWorkerModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmWorkMapper.queryCount((ChPmWorkerModel)page.getModel());
		list = this.chPmWorkMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[医院职工表数据不分页查询]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @param queryFields 医院职工表数据查询字段
	 * @return List<ChPmWorkModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmWorkerModel> queryList(ChPmWorkerModel model,String queryFields)throws Exception{
		List<ChPmWorkerModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmWorkMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[医院职工表数据不分页查询]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @return List<ChPmWorkModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmWorkerModel> queryList(ChPmWorkerModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[医院职工表数据查询总条数]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmWorkerModel model)throws Exception{
		return this.chPmWorkMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询医院职工表数据]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return ChPmWorkModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmWorkerModel queryById(Long id)throws Exception{
		ChPmWorkerModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmWorkMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[医院职工表数据新增]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChPmWorkerModel model)throws Exception{
		Long count = null;
		if(!Objects.isNull(model)){
			count = this.chPmWorkMapper.insert(model);
		}
		return model.getId();
	 }
	 
	 /**
	 * <p>Discription:[医院职工表数据新增]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Integer saveBatch(List<ChPmWorkerModel> list)throws Exception{
		Integer count = null;
		if(null != list && list.size()>0){
			count = this.chPmWorkMapper.insertBatch(list);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院职工表数据编辑]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmWorkerModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmWorkMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院职工表单条数据删除-逻辑]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmWorkMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[医院职工表单条数据删除-物理]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmWorkMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[医院职工表批量数据删除-物理]</p>
	 * Created on 2021年03月25日
	 * @param ids 医院职工表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmWorkMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[医院职工表批量数据删除-逻辑]</p>
	 * Created on 2021年03月25日
	 * @param ids 医院职工表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmWorkMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	public List<String> queryExisted(List<String> idcardNos)
	{
		if(null == idcardNos || idcardNos.size()<=0)
			return new ArrayList<String>();
		return this.chPmWorkMapper.queryExisted(idcardNos);
	}
	public List<Map<String,Object>> queryByCardNoBatch(List<String> idcardNos)
	{
		if(null == idcardNos || idcardNos.size()<=0)
			return new ArrayList<Map<String,Object>>();
		return this.chPmWorkMapper.queryByCardNoBatch(idcardNos);
	}
	
	public List<Map<String,Object>> queryExist4OthByCardNos(List<String> list)
	{
		if(null == list || list.size()<=0)
			return new ArrayList<Map<String,Object>>();
		return this.chPmWorkMapper.queryExist4OthByCardNos(list);
	}
}
