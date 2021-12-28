package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmTransInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmTransInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmTransInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [调动信息表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmTransInfoBiz")
public class ChPmTransInfoBizImpl implements IChPmTransInfoBiz {
	
	/**
	 * <p>Discription:[调动信息表Mapper]</p>
	 */	
	@Autowired
	private ChPmTransInfoMapper chPmTransInfoMapper;
	
	/**
	 * <p>Discription:[调动信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 调动信息表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmTransInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[调动信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 调动信息表数据分页条件
	 * @param chPmTransInfoModel 调动信息表数据查询条件
	 * @param queryFields 调动信息表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmTransInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmTransInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmTransInfoMapper.queryCount((ChPmTransInfoModel)page.getModel());
		list = this.chPmTransInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[调动信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmTransInfoModel 调动信息表数据查询条件
	 * @param queryFields 调动信息表数据查询字段
	 * @return List<ChPmTransInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmTransInfoModel> queryList(ChPmTransInfoModel model,String queryFields)throws Exception{
		List<ChPmTransInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmTransInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[调动信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmTransInfoModel 调动信息表数据查询条件
	 * @return List<ChPmTransInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmTransInfoModel> queryList(ChPmTransInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[调动信息表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmTransInfoModel 调动信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmTransInfoModel model)throws Exception{
		return this.chPmTransInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询调动信息表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 调动信息表数据id
	 * @return ChPmTransInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmTransInfoModel queryById(Long id)throws Exception{
		ChPmTransInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmTransInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[调动信息表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmTransInfoModel 调动信息表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmTransInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmTransInfoMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调动信息表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 调动信息表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmTransInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmTransInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[调动信息表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 调动信息表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmTransInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[调动信息表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 调动信息表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmTransInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[调动信息表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 调动信息表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmTransInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[调动信息表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 调动信息表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmTransInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
