package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.mapper.SystemDictItemMapper;
import com.hdfs.olo.olo_web.central.mapper.SystemDictMapper;
import com.hdfs.olo.olo_web.central.model.SystemDictModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;

/** 
 * Description: [系统字典服务实现]
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("systemDictBiz")
public class SystemDictBizImpl implements ISystemDictBiz {
	
	/**
	 * <p>Discription:[系统字典Mapper]</p>
	 */	
	@Autowired
	private SystemDictMapper systemDictMapper;
	/**
	 * <p>Discription:[系统字典项Mapper]</p>
	 */	
	@Autowired
	private SystemDictItemMapper systemDictItemMapper;
	
	/**
	 * <p>Discription:[系统字典数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
		SystemDictModel model = (SystemDictModel)page.getModel();
		model.setCreator(null);model.setUpdator(null);
	 	queryPage(page,(SystemDictModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[系统字典数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典数据分页条件
	 * @param systemDictModel 系统字典数据查询条件
	 * @param queryFields 系统字典数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,SystemDictModel model,
			String queryFields)throws Exception{
			
		List<SystemDictModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.systemDictMapper.queryCount((SystemDictModel)page.getModel());
		list = this.systemDictMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[系统字典数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param systemDictModel 系统字典数据查询条件
	 * @param queryFields 系统字典数据查询字段
	 * @return List<SystemDictModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<SystemDictModel> queryList(SystemDictModel model,String queryFields)throws Exception{
		List<SystemDictModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.systemDictMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[系统字典数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param systemDictModel 系统字典数据查询条件
	 * @return List<SystemDictModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<SystemDictModel> queryList(SystemDictModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[系统字典数据查询总条数]</p>
	 * Created on 2021年03月04日
	 * @param systemDictModel 系统字典数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(SystemDictModel model)throws Exception{
		return this.systemDictMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询系统字典数据]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return SystemDictModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public SystemDictModel queryById(Long id)throws Exception{
		SystemDictModel model = null;
		if(!Objects.isNull(id)){
			model = this.systemDictMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	 * <p>Discription:[根据code查询系统字典数据]</p>
	 * Created on 2021年03月04日
	 * @param code 系统字典数据code
	 * @return SystemDictModel 单条数据	 
	 * @author:huadf
	 */
	public SystemDictModel queryByCode(String dictCode)throws Exception
	{
		SystemDictModel model = null;
		if(!StringHelper.isNullOrEmpty(dictCode)){
			model = this.systemDictMapper.queryByCode(dictCode, null);
		}
		return model;
	}
	/**
	 * <p>Discription:[系统字典数据新增]</p>
	 * Created on 2021年03月04日
	 * @param systemDictModel 系统字典数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 //@Transactional(transactionManager="springTransactionManager",propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(SystemDictModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.systemDictMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[系统字典数据编辑]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(SystemDictModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.systemDictMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[系统字典单条数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.systemDictMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[系统字典单条数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.systemDictMapper.delById4Logic(id);
			systemDictItemMapper.delByDictId4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[系统字典批量数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.systemDictMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统字典批量数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.systemDictMapper.delBatchByIds4Logic(ids);
			systemDictItemMapper.delBatchByDictIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	@Override
	public Map<String,List<Map<String,Object>>> queryItemListByCodes(List<String> codes)
	{
		Map<String,List<Map<String,Object>>> retMap = new HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> list = this.systemDictItemMapper.queryDictItemByCodes(codes);
		if(0 == list.size())return retMap;
		String dictCode = null;
		Integer dictType = null;
		Map<String,Object> item = null;
		List<Map<String,Object>> groupList = null;
		for(int i=0;i<list.size();i++)
		{
			item = list.get(i);
			dictType = (Integer)item.get("dict_type");
			if(1 == dictType)
				item.put("item_key", Integer.parseInt(""+item.get("item_key")));
			if(i == 0)
			{
				groupList = new ArrayList<Map<String,Object>>();
				groupList.add(item);
				if((i+1)==list.size())
					retMap.put(item.get("code")+"", groupList);
				else
					dictCode = item.get("dict_code")+"";
				continue;
			}
			if(item.get("dict_code").equals(dictCode))
			{
				groupList.add(item);
			}else
			{
				retMap.put(dictCode, groupList);
				groupList = new ArrayList<Map<String,Object>>();
				groupList.add(item);
				dictCode = item.get("dict_code")+"";
			}
			if((i+1)==list.size())
				retMap.put(item.get("dict_code")+"", groupList);
		}
		return retMap;
	}
}
