package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.mapper.SystemDictItemMapper;
import com.hdfs.olo.olo_web.central.mapper.SystemDictMapper;
import com.hdfs.olo.olo_web.central.model.SystemDictItemModel;
import com.hdfs.olo.olo_web.central.model.SystemDictModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [系统字典项服务实现]
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("systemDictItemBiz")
public class SystemDictItemBizImpl implements ISystemDictItemBiz {
	
	@Autowired
	private SystemDictMapper systemDictMapper;
	/**
	 * <p>Discription:[系统字典项Mapper]</p>
	 */	
	@Autowired
	private SystemDictItemMapper systemDictItemMapper;
	
	/**
	 * <p>Discription:[系统字典项数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典项数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(SystemDictItemModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[系统字典项数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典项数据分页条件
	 * @param systemDictItemModel 系统字典项数据查询条件
	 * @param queryFields 系统字典项数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,SystemDictItemModel model,
			String queryFields)throws Exception{
			
		List<SystemDictItemModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.systemDictItemMapper.queryCount((SystemDictItemModel)page.getModel());
		list = this.systemDictItemMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[系统字典项数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param systemDictItemModel 系统字典项数据查询条件
	 * @param queryFields 系统字典项数据查询字段
	 * @return List<SystemDictItemModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<SystemDictItemModel> queryList(SystemDictItemModel model,String queryFields)throws Exception{
		List<SystemDictItemModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.systemDictItemMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[系统字典项数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param systemDictItemModel 系统字典项数据查询条件
	 * @return List<SystemDictItemModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<SystemDictItemModel> queryList(SystemDictItemModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[系统字典项数据查询总条数]</p>
	 * Created on 2021年03月04日
	 * @param systemDictItemModel 系统字典项数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(SystemDictItemModel model)throws Exception{
		return this.systemDictItemMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询系统字典项数据]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return SystemDictItemModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public SystemDictItemModel queryById(Long id)throws Exception{
		SystemDictItemModel model = null;
		if(!Objects.isNull(id)){
			model = this.systemDictItemMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[系统字典项数据新增]</p>
	 * Created on 2021年03月04日
	 * @param systemDictItemModel 系统字典项数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(SystemDictItemModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.systemDictItemMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[系统字典项数据编辑]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典项数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(SystemDictItemModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.systemDictItemMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[系统字典项单条数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.systemDictItemMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[系统字典项单条数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.systemDictItemMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[系统字典项批量数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典项数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.systemDictItemMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统字典项批量数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典项数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.systemDictItemMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据字典主键查询顶级字典项列表
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SystemDictItemModel> loadItemByDictId(Long dictId) throws Exception
	{
		if(null == dictId) throw new Exception("加载字典项父级时未检测到字典编号存在！");
		try {
			//为父级字典项做数据准备
			SystemDictItemModel model = new SystemDictItemModel();
			model.setDictId(dictId);
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			List<SystemDictItemModel> list = this.queryList(model);
			Map<Long,List<SystemDictItemModel>> tMap = new HashMap<Long,List<SystemDictItemModel>>(); 
			
			//分类子字典项
			Iterator<SystemDictItemModel> it = list.iterator();
			while(it.hasNext())
			{
				SystemDictItemModel item = it.next();
				if(0 == item.getExistSuper())//不存在
					continue;
				if(tMap.containsKey(item.getId()))
				{
					tMap.get(item.getId()).add(item);
				}else
				{
					List<SystemDictItemModel> subs = new ArrayList<SystemDictItemModel>();
					subs.add(item);
					tMap.put(item.getId(), subs);
					it.remove();
				}
			}
			
			if(tMap.size()<=0) return list;
			for(SystemDictItemModel item:list)
			{
				item.setSubList(tMap.get(item.getId()));
			}
			return list;
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("加载层级字典项时异常！");
		}
	}
	
	/**
	 * 通过字典编码和字典项编码获取字典项值
	 * @param itemKey
	 * @param dictCode
	 * @return
	 */
	public String getValByKeyWithDictCode(String itemKey,String dictCode)
	{
		List<String> queryFields = Arrays.asList("ID,NAME,CODE".split(","));
		try {
			SystemDictModel dict = systemDictMapper.queryByCode(dictCode, queryFields);
			if(null == dict)return null;
			SystemDictItemModel itemModel = new SystemDictItemModel();
			itemModel.setDictId(dict.getId());
			itemModel.setItemKey(itemKey);
			itemModel.setStatus(DictionaryUtil.KEY_NORMAL);
			List<SystemDictItemModel> itemList = this.queryList(itemModel);
			if(null == itemList || itemList.size()<=0)return null;
			return itemList.get(0).getItemVal();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
