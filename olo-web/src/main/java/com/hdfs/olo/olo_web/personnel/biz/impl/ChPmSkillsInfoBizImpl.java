package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.personnel.biz.IChPmSkillsInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmSkillsInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduInfoModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [技能等级（技术工种）表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmSkillsInfoBiz")
public class ChPmSkillsInfoBizImpl implements IChPmSkillsInfoBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * <p>Discription:[技能等级（技术工种）表Mapper]</p>
	 */	
	@Autowired
	private ChPmSkillsInfoMapper chPmSkillsInfoMapper;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级（技术工种）表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmSkillsInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[技能等级（技术工种）表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级（技术工种）表数据分页条件
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据查询条件
	 * @param queryFields 技能等级（技术工种）表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmSkillsInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmSkillsInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmSkillsInfoMapper.queryCount((ChPmSkillsInfoModel)page.getModel());
		list = this.chPmSkillsInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[技能等级（技术工种）表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据查询条件
	 * @param queryFields 技能等级（技术工种）表数据查询字段
	 * @return List<ChPmSkillsInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmSkillsInfoModel> queryList(ChPmSkillsInfoModel model,String queryFields)throws Exception{
		List<ChPmSkillsInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmSkillsInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[技能等级（技术工种）表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据查询条件
	 * @return List<ChPmSkillsInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmSkillsInfoModel> queryList(ChPmSkillsInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[技能等级（技术工种）表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmSkillsInfoModel model)throws Exception{
		return this.chPmSkillsInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询技能等级（技术工种）表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return ChPmSkillsInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmSkillsInfoModel queryById(Long id)throws Exception{
		ChPmSkillsInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmSkillsInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[技能等级（技术工种）表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChPmSkillsInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmSkillsInfoMapper.insert(model);
		}
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	 }
	 /**
	 * <p>Discription:[技能等级（技术工种）表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int saveBatch(List<ChPmSkillsInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmSkillsInfoMapper.insertBatch(list);
		}
		//if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	 }

	/**
	 * <p>Discription:[技能等级（技术工种）表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级（技术工种）表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int edit(ChPmSkillsInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmSkillsInfoMapper.update(model);
		}
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	 }

	/**
	 * <p>Discription:[技能等级（技术工种）表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delById(Long id)throws Exception{
		//查询是否存在该条数据
		if(Objects.isNull(id)) return 0;
		List<?> queryFields = Arrays.asList("ID,WORKER_ID".split(","));
		ChPmSkillsInfoModel model = chPmSkillsInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		
		//执行删除操作
		Integer count = this.chPmSkillsInfoMapper.delById(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		
		return count;
	}
	
	/**
	 * <p>Discription:[技能等级（技术工种）表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delById4Logic(Long id)throws Exception{
		//查询是否存在该条数据
		if(Objects.isNull(id)) return 0;
		List<?> queryFields = Arrays.asList("ID,WORKER_ID".split(","));
		ChPmSkillsInfoModel model = chPmSkillsInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		//执行删除操作
		Integer count = this.chPmSkillsInfoMapper.delById4Logic(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[技能等级（技术工种）表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级（技术工种）表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null == ids || ids.size()==0) return count;
		//查询是否存在该条数据
		List<?> queryFields = Arrays.asList("ID,WORKER_ID".split(","));
		ChPmSkillsInfoModel model = chPmSkillsInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmSkillsInfoMapper.delBatchByIds(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[技能等级（技术工种）表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级（技术工种）表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null == ids || ids.size()==0) return count;
		//查询是否存在该条数据
		List<?> queryFields = Arrays.asList("ID,WORKER_ID".split(","));
		ChPmSkillsInfoModel model = chPmSkillsInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmSkillsInfoMapper.delBatchByIds4Logic(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmSkillsInfoMapper.synSkillsInfoSortVal(model.getWorkerId());
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据执行编号对其下所有技能级别进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synSkillsInfoSortVal(Long wkId)throws Exception
	{
		this.chPmSkillsInfoMapper.synSkillsInfoSortVal(wkId);
	}
	public void synSkillsInfoSortVal()
	{
		try {
			List<Long> wkIds = chPmSkillsInfoMapper.queryAllWkIds4NeedSort();
			if(wkIds.size()==0) return;
			for(Long wkId:wkIds)
			{
				this.synSkillsInfoSortVal(wkId);
			}
		}catch(Exception e)
		{
			logger.error("技能级别批量排序异常! error:",e);
		}
	}
}
