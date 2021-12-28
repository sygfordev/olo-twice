package com.hdfs.olo.olo_web.social.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.social.biz.IChSocialRecordBiz;
import com.hdfs.olo.olo_web.social.mapper.ChSocialRecordMapper;
import com.hdfs.olo.olo_web.social.model.ChSocialRecordModel;

/** 
 * Description: [社保导入记录服务实现]
 * Created on 2021年06月09日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("social")
@Service("chSocialRecordBiz")
public class ChSocialRecordBizImpl implements IChSocialRecordBiz {
	
	/**
	 * <p>Discription:[社保导入记录Mapper]</p>
	 */	
	@Autowired
	private ChSocialRecordMapper chSocialRecordMapper;
	
	/**
	 * <p>Discription:[社保导入记录数据分页查询]</p>
	 * Created on 2021年06月09日
	 * @param page 社保导入记录数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSocialRecordModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[社保导入记录数据分页查询]</p>
	 * Created on 2021年06月09日
	 * @param page 社保导入记录数据分页条件
	 * @param chSocialRecordModel 社保导入记录数据查询条件
	 * @param queryFields 社保导入记录数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSocialRecordModel model,
			String queryFields)throws Exception{
			
		List<ChSocialRecordModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSocialRecordMapper.queryCount((ChSocialRecordModel)page.getModel());
		if(count>0) list = this.chSocialRecordMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSocialRecordModel>():list);
	}

	/**
	 * <p>Discription:[社保导入记录数据不分页查询]</p>
	 * Created on 2021年06月09日
	 * @param chSocialRecordModel 社保导入记录数据查询条件
	 * @param queryFields 社保导入记录数据查询字段
	 * @return List<ChSocialRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSocialRecordModel> queryList(ChSocialRecordModel model,String queryFields)throws Exception{
		List<ChSocialRecordModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSocialRecordMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[社保导入记录数据不分页查询]</p>
	 * Created on 2021年06月09日
	 * @param chSocialRecordModel 社保导入记录数据查询条件
	 * @return List<ChSocialRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSocialRecordModel> queryList(ChSocialRecordModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[社保导入记录数据查询总条数]</p>
	 * Created on 2021年06月09日
	 * @param chSocialRecordModel 社保导入记录数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSocialRecordModel model)throws Exception{
		return this.chSocialRecordMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询社保导入记录数据]</p>
	 * Created on 2021年06月09日
	 * @param id 社保导入记录数据id
	 * @return ChSocialRecordModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSocialRecordModel queryById(Long id)throws Exception{
		ChSocialRecordModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSocialRecordMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[社保导入记录数据新增]</p>
	 * Created on 2021年06月09日
	 * @param chSocialRecordModel 社保导入记录数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChSocialRecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSocialRecordMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[社保导入记录数据编辑]</p>
	 * Created on 2021年06月09日
	 * @param model 社保导入记录数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSocialRecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSocialRecordMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[社保导入记录单条数据删除-逻辑]</p>
	 * Created on 2021年06月09日
	 * @param id 社保导入记录数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSocialRecordMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[社保导入记录单条数据删除-物理]</p>
	 * Created on 2021年06月09日
	 * @param id 社保导入记录数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSocialRecordMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[社保导入记录批量数据删除-物理]</p>
	 * Created on 2021年06月09日
	 * @param ids 社保导入记录数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSocialRecordMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[社保导入记录批量数据删除-逻辑]</p>
	 * Created on 2021年06月09日
	 * @param ids 社保导入记录数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSocialRecordMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
