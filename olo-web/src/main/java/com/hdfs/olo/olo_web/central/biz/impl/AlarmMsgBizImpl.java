package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.IAlarmMsgBiz;
import com.hdfs.olo.olo_web.central.mapper.AlarmMsgMapper;
import com.hdfs.olo.olo_web.central.model.AlarmMsgModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [警报信息服务实现]
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("alarmMsgBiz")
public class AlarmMsgBizImpl implements IAlarmMsgBiz {
	
	/**
	 * <p>Discription:[警报信息Mapper]</p>
	 */	
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;
	
	/**
	 * <p>Discription:[警报信息数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(AlarmMsgModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[警报信息数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报信息数据分页条件
	 * @param chPmAlarmMsgModel 警报信息数据查询条件
	 * @param queryFields 警报信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,AlarmMsgModel model,
			String queryFields)throws Exception{
			
		List<AlarmMsgModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.alarmMsgMapper.queryCount((AlarmMsgModel)page.getModel());
		if(count>0) list = this.alarmMsgMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<AlarmMsgModel>():list);
	}

	/**
	 * <p>Discription:[警报信息数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据查询条件
	 * @param queryFields 警报信息数据查询字段
	 * @return List<ChPmAlarmMsgModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<AlarmMsgModel> queryList(AlarmMsgModel model,String queryFields)throws Exception{
		List<AlarmMsgModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.alarmMsgMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[警报信息数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据查询条件
	 * @return List<ChPmAlarmMsgModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<AlarmMsgModel> queryList(AlarmMsgModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[警报信息数据查询总条数]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(AlarmMsgModel model)throws Exception{
		return this.alarmMsgMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询警报信息数据]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return ChPmAlarmMsgModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public AlarmMsgModel queryById(Long id)throws Exception{
		AlarmMsgModel model = null;
		if(!Objects.isNull(id)){
			model = this.alarmMsgMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[警报信息数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(AlarmMsgModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.alarmMsgMapper.insert(model);
		}
		return count;
	 }
	 
	/**
	 * <p>Discription:[警报信息数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	@Override
	public int saveBatch(List<AlarmMsgModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.alarmMsgMapper.insertBatch(list);
		}
		return count;
	}

	/**
	 * <p>Discription:[警报信息数据编辑]</p>
	 * Created on 2021年09月04日
	 * @param model 警报信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(AlarmMsgModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.alarmMsgMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[警报信息单条数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.alarmMsgMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[警报信息单条数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.alarmMsgMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[警报信息批量数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.alarmMsgMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[警报信息批量数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.alarmMsgMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
