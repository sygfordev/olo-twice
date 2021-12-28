package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.biz.IChSaAdjustRecordBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaAdjustRecordMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustRecordModel;

/** 
 * Description: [调资记录表服务实现]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaAdjustRecordBiz")
public class ChSaAdjustRecordBizImpl implements IChSaAdjustRecordBiz {
	
	/**
	 * <p>Discription:[调资记录表Mapper]</p>
	 */	
	@Autowired
	private ChSaAdjustRecordMapper chSaAdjustRecordMapper;
	
	/**
	 * <p>Discription:[调资记录表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资记录表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaAdjustRecordModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[调资记录表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资记录表数据分页条件
	 * @param chSaAdjustRecordModel 调资记录表数据查询条件
	 * @param queryFields 调资记录表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaAdjustRecordModel model,
			String queryFields)throws Exception{
			
		List<ChSaAdjustRecordModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaAdjustRecordMapper.queryCount((ChSaAdjustRecordModel)page.getModel());
		if(count>0) list = this.chSaAdjustRecordMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaAdjustRecordModel>():list);
	}

	/**
	 * <p>Discription:[调资记录表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustRecordModel 调资记录表数据查询条件
	 * @param queryFields 调资记录表数据查询字段
	 * @return List<ChSaAdjustRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaAdjustRecordModel> queryList(ChSaAdjustRecordModel model,String queryFields)throws Exception{
		List<ChSaAdjustRecordModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaAdjustRecordMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[调资记录表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustRecordModel 调资记录表数据查询条件
	 * @return List<ChSaAdjustRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaAdjustRecordModel> queryList(ChSaAdjustRecordModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[调资记录表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustRecordModel 调资记录表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaAdjustRecordModel model)throws Exception{
		return this.chSaAdjustRecordMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询调资记录表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return ChSaAdjustRecordModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaAdjustRecordModel queryById(Long id)throws Exception{
		ChSaAdjustRecordModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaAdjustRecordMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[调资记录表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustRecordModel 调资记录表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChSaAdjustRecordModel model)throws Exception{
		//Long count = null;
		if(!Objects.isNull(model)){
			this.chSaAdjustRecordMapper.insert(model);
		}
		return model.getId();
	 }

	/**
	 * <p>Discription:[调资记录表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资记录表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaAdjustRecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaAdjustRecordMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调资记录表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustRecordMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[调资记录表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaAdjustRecordMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[调资记录表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资记录表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustRecordMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[调资记录表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资记录表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaAdjustRecordMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
