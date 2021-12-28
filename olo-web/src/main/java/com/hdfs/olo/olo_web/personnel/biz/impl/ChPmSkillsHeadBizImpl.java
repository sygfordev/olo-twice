package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsHeadBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmSkillsHeadMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsHeadModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [技能等级(技术工种)信息头表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmSkillsHeadBiz")
public class ChPmSkillsHeadBizImpl implements IChPmSkillsHeadBiz {
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表Mapper]</p>
	 */	
	@Autowired
	private ChPmSkillsHeadMapper chPmSkillsHeadMapper;
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级(技术工种)信息头表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmSkillsHeadModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级(技术工种)信息头表数据分页条件
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据查询条件
	 * @param queryFields 技能等级(技术工种)信息头表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmSkillsHeadModel model,
			String queryFields)throws Exception{
			
		List<ChPmSkillsHeadModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmSkillsHeadMapper.queryCount((ChPmSkillsHeadModel)page.getModel());
		list = this.chPmSkillsHeadMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据查询条件
	 * @param queryFields 技能等级(技术工种)信息头表数据查询字段
	 * @return List<ChPmSkillsHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmSkillsHeadModel> queryList(ChPmSkillsHeadModel model,String queryFields)throws Exception{
		List<ChPmSkillsHeadModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmSkillsHeadMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据查询条件
	 * @return List<ChPmSkillsHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmSkillsHeadModel> queryList(ChPmSkillsHeadModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmSkillsHeadModel model)throws Exception{
		return this.chPmSkillsHeadMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询技能等级(技术工种)信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级(技术工种)信息头表数据id
	 * @return ChPmSkillsHeadModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmSkillsHeadModel queryById(Long id)throws Exception{
		ChPmSkillsHeadModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmSkillsHeadMapper.queryById(id,null);
		}
		return model;
	 }
	 
	 /**
	 * <p>Discription:[根据职工编号查询技能等级(技术工种)信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级(技术工种)信息头表数据id
	 * @return ChPmSkillsHeadModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmSkillsHeadModel queryByWkId(Long wkId)throws Exception
	{
		ChPmSkillsHeadModel model = null;
		if(!Objects.isNull(wkId)){
			model = this.chPmSkillsHeadMapper.queryByWkId(wkId,null);
		}
		return model;
	}
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmSkillsHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmSkillsHeadMapper.insert(model);
		}
		return count;
	 }
	 /**
	 * <p>Discription:[技能等级(技术工种)信息头表数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsHeadModel 技能等级(技术工种)信息头表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmSkillsHeadModel> list)throws Exception
	{
		Integer count = 0;
		if(null!=list && list.size()>0){
			count = this.chPmSkillsHeadMapper.insertBatch(list);
		}
		return count;
	}
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级(技术工种)信息头表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmSkillsHeadModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmSkillsHeadMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[技能等级(技术工种)信息头表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级(技术工种)信息头表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmSkillsHeadMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[技能等级(技术工种)信息头表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级(技术工种)信息头表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmSkillsHeadMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[技能等级(技术工种)信息头表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级(技术工种)信息头表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmSkillsHeadMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[技能等级(技术工种)信息头表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级(技术工种)信息头表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmSkillsHeadMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据身份证号列表查询已存在的信息
	 * @param cardNos
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryExistByCardNos(List<String> cardNos)
	{
		if(null == cardNos || cardNos.size()<=0)
			return new ArrayList<Map<String,Object>>();
		return chPmSkillsHeadMapper.queryExistByCardNos(cardNos);
	}
}
