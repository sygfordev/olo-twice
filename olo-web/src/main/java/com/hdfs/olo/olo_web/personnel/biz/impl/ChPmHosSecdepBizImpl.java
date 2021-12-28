package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmHosSecdepBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmHosSecdepMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosSecdepModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [医院二级科室表服务实现]
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmHosSecdepBiz")
public class ChPmHosSecdepBizImpl implements IChPmHosSecdepBiz {
	
	/**
	 * <p>Discription:[医院二级科室表Mapper]</p>
	 */	
	@Autowired
	private ChPmHosSecdepMapper chPmHosSecdepMapper;
	
	/**
	 * <p>Discription:[医院二级科室表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院二级科室表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmHosSecdepModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[医院二级科室表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院二级科室表数据分页条件
	 * @param chPmHosSecdepModel 医院二级科室表数据查询条件
	 * @param queryFields 医院二级科室表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmHosSecdepModel model,
			String queryFields)throws Exception{
			
		List<ChPmHosSecdepModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmHosSecdepMapper.queryCount((ChPmHosSecdepModel)page.getModel());
		list = this.chPmHosSecdepMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[医院二级科室表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosSecdepModel 医院二级科室表数据查询条件
	 * @param queryFields 医院二级科室表数据查询字段
	 * @return List<ChPmHosSecdepModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmHosSecdepModel> queryList(ChPmHosSecdepModel model,String queryFields)throws Exception{
		List<ChPmHosSecdepModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmHosSecdepMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[医院二级科室表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosSecdepModel 医院二级科室表数据查询条件
	 * @return List<ChPmHosSecdepModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmHosSecdepModel> queryList(ChPmHosSecdepModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[医院二级科室表数据查询总条数]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosSecdepModel 医院二级科室表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmHosSecdepModel model)throws Exception{
		return this.chPmHosSecdepMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询医院二级科室表数据]</p>
	 * Created on 2021年04月02日
	 * @param id 医院二级科室表数据id
	 * @return ChPmHosSecdepModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmHosSecdepModel queryById(Long id)throws Exception{
		ChPmHosSecdepModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmHosSecdepMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[医院二级科室表数据新增]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosSecdepModel 医院二级科室表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmHosSecdepModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmHosSecdepMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院二级科室表数据编辑]</p>
	 * Created on 2021年04月02日
	 * @param model 医院二级科室表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmHosSecdepModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getHsdNo())){
			count = this.chPmHosSecdepMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院二级科室表单条数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param id 医院二级科室表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmHosSecdepMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[医院二级科室表单条数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param id 医院二级科室表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmHosSecdepMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[医院二级科室表批量数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院二级科室表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmHosSecdepMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[医院二级科室表批量数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院二级科室表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmHosSecdepMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
