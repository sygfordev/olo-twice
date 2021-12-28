package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.IAlarmSetBiz;
import com.hdfs.olo.olo_web.central.mapper.AlarmSetMapper;
import com.hdfs.olo.olo_web.central.model.AlarmSetModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [警报设置服务实现]
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("alarmSetBiz")
public class AlarmSetBizImpl implements IAlarmSetBiz {
	
	/**
	 * <p>Discription:[警报设置Mapper]</p>
	 */	
	@Autowired
	private AlarmSetMapper alarmSetMapper;
	
	/**
	 * <p>Discription:[警报设置数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报设置数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(AlarmSetModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[警报设置数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报设置数据分页条件
	 * @param chPmAlarmSetModel 警报设置数据查询条件
	 * @param queryFields 警报设置数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,AlarmSetModel model,
			String queryFields)throws Exception{
			
		List<AlarmSetModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.alarmSetMapper.queryCount((AlarmSetModel)page.getModel());
		if(count>0) list = this.alarmSetMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<AlarmSetModel>():list);
	}

	/**
	 * <p>Discription:[警报设置数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmSetModel 警报设置数据查询条件
	 * @param queryFields 警报设置数据查询字段
	 * @return List<ChPmAlarmSetModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<AlarmSetModel> queryList(AlarmSetModel model,String queryFields)throws Exception{
		List<AlarmSetModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.alarmSetMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[警报设置数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmSetModel 警报设置数据查询条件
	 * @return List<ChPmAlarmSetModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<AlarmSetModel> queryList(AlarmSetModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[警报设置数据查询总条数]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmSetModel 警报设置数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(AlarmSetModel model)throws Exception{
		return this.alarmSetMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询警报设置数据]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return ChPmAlarmSetModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public AlarmSetModel queryById(Long id)throws Exception{
		AlarmSetModel model = null;
		if(!Objects.isNull(id)){
			model = this.alarmSetMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[警报设置数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmSetModel 警报设置数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(AlarmSetModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.alarmSetMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[警报设置数据编辑]</p>
	 * Created on 2021年09月04日
	 * @param model 警报设置数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(AlarmSetModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.alarmSetMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[警报设置单条数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.alarmSetMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[警报设置单条数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.alarmSetMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[警报设置批量数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报设置数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.alarmSetMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[警报设置批量数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报设置数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.alarmSetMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
