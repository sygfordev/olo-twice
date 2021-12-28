package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISysAreaBiz;
import com.hdfs.olo.olo_web.central.mapper.SysAreaMapper;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [区县表服务实现]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("sysAreaBiz")
public class SysAreaBizImpl implements ISysAreaBiz {
	
	/**
	 * <p>Discription:[区县表Mapper]</p>
	 */	
	@Autowired
	private SysAreaMapper sysAreaMapper;
	
	/**
	 * <p>Discription:[区县表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 区县表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(SysAreaModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[区县表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 区县表数据分页条件
	 * @param sysAreaModel 区县表数据查询条件
	 * @param queryFields 区县表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,SysAreaModel model,
			String queryFields)throws Exception{
			
		List<SysAreaModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.sysAreaMapper.queryCount((SysAreaModel)page.getModel());
		list = this.sysAreaMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[区县表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysAreaModel 区县表数据查询条件
	 * @param queryFields 区县表数据查询字段
	 * @return List<SysAreaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<SysAreaModel> queryList(SysAreaModel model,String queryFields)throws Exception{
		List<SysAreaModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.sysAreaMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[区县表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysAreaModel 区县表数据查询条件
	 * @return List<SysAreaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<SysAreaModel> queryList(SysAreaModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[区县表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param sysAreaModel 区县表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(SysAreaModel model)throws Exception{
		return this.sysAreaMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询区县表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return SysAreaModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public SysAreaModel queryById(Long id)throws Exception{
		SysAreaModel model = null;
		if(!Objects.isNull(id)){
			model = this.sysAreaMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[区县表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysAreaModel 区县表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(SysAreaModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.sysAreaMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[区县表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 区县表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(SysAreaModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getAreaNo())){
			count = this.sysAreaMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[区县表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysAreaMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[区县表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysAreaMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[区县表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 区县表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysAreaMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[区县表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 区县表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysAreaMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
