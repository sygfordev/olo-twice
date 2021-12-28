package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmStationBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmStationMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmStationModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [用工及岗位表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmStationBiz")
public class ChPmStationBizImpl implements IChPmStationBiz {
	
	/**
	 * <p>Discription:[用工及岗位表Mapper]</p>
	 */	
	@Autowired
	private ChPmStationMapper chPmStationMapper;
	
	/**
	 * <p>Discription:[用工及岗位表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 用工及岗位表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmStationModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[用工及岗位表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 用工及岗位表数据分页条件
	 * @param chPmStationModel 用工及岗位表数据查询条件
	 * @param queryFields 用工及岗位表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmStationModel model,
			String queryFields)throws Exception{
			
		List<ChPmStationModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmStationMapper.queryCount((ChPmStationModel)page.getModel());
		list = this.chPmStationMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[用工及岗位表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmStationModel 用工及岗位表数据查询条件
	 * @param queryFields 用工及岗位表数据查询字段
	 * @return List<ChPmStationModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmStationModel> queryList(ChPmStationModel model,String queryFields)throws Exception{
		List<ChPmStationModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmStationMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[用工及岗位表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmStationModel 用工及岗位表数据查询条件
	 * @return List<ChPmStationModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmStationModel> queryList(ChPmStationModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[用工及岗位表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmStationModel 用工及岗位表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmStationModel model)throws Exception{
		return this.chPmStationMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询用工及岗位表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 用工及岗位表数据id
	 * @return ChPmStationModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmStationModel queryById(Long id)throws Exception{
		ChPmStationModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmStationMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	  * <p>Discription:[根据id查询用工及岗位表数据]</p>
	  * Created on 2021年03月29日
	  * @param wkId 职工编号
	  * @return ChPmStationModel 单条数据	 
	  * @author:huadf
	  */
	  @Override
	  public ChPmStationModel queryByWkId(Long wkId)throws Exception{
		  ChPmStationModel model = null;
		  if(!Objects.isNull(wkId)){
			  model = this.chPmStationMapper.queryByWkId(wkId,null);
		  }
		  return model;
	  }

	/**
	 * <p>Discription:[用工及岗位表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmStationModel 用工及岗位表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Long save(ChPmStationModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmStationMapper.insert(model);
		}
		return model.getId();
	 }
	 /**
	 * <p>Discription:[用工及岗位表数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmStationModel 用工及岗位表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public Integer saveBatch(List<ChPmStationModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmStationMapper.insertBatch(list);
		}
		return count;
	 }

	/**
	 * <p>Discription:[用工及岗位表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 用工及岗位表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmStationModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmStationMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[用工及岗位表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 用工及岗位表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmStationMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[用工及岗位表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 用工及岗位表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmStationMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[用工及岗位表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 用工及岗位表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmStationMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[用工及岗位表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 用工及岗位表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmStationMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据身份证号列表查询已存在的信息
	 * @param cardNos
	 * @return
	 */
	public List<Map<String,Object>> queryExistByCardNos(List<String> cardNos)
	{
		if(null == cardNos || cardNos.size()<=0)
			return new ArrayList<Map<String,Object>>();
		return chPmStationMapper.queryExistByCardNos(cardNos);
	}
}
