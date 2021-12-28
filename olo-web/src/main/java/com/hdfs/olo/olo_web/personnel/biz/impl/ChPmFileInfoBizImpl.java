package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmFileInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmFileInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmFileInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [文件信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmFileInfoBiz")
public class ChPmFileInfoBizImpl implements IChPmFileInfoBiz {
	
	/**
	 * <p>Discription:[文件信息Mapper]</p>
	 */	
	@Autowired
	private ChPmFileInfoMapper chPmFileInfoMapper;
	
	/**
	 * <p>Discription:[文件信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 文件信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmFileInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[文件信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 文件信息数据分页条件
	 * @param chPmFileInfoModel 文件信息数据查询条件
	 * @param queryFields 文件信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmFileInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmFileInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmFileInfoMapper.queryCount((ChPmFileInfoModel)page.getModel());
		list = this.chPmFileInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[文件信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmFileInfoModel 文件信息数据查询条件
	 * @param queryFields 文件信息数据查询字段
	 * @return List<ChPmFileInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmFileInfoModel> queryList(ChPmFileInfoModel model,String queryFields)throws Exception{
		List<ChPmFileInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmFileInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[文件信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmFileInfoModel 文件信息数据查询条件
	 * @return List<ChPmFileInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmFileInfoModel> queryList(ChPmFileInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[文件信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmFileInfoModel 文件信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmFileInfoModel model)throws Exception{
		return this.chPmFileInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询文件信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 文件信息数据id
	 * @return ChPmFileInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmFileInfoModel queryById(Long id)throws Exception{
		ChPmFileInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmFileInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[文件信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmFileInfoModel 文件信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmFileInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmFileInfoMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[文件信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 文件信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmFileInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmFileInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[文件信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 文件信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmFileInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[文件信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 文件信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmFileInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[文件信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 文件信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmFileInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[文件信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 文件信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmFileInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
