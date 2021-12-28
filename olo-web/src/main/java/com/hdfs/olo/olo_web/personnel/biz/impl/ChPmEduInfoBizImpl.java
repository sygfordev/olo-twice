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

import com.hdfs.olo.olo_web.personnel.biz.IChPmEduInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmEduInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmEduInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [学历信息表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmEduInfoBiz")
public class ChPmEduInfoBizImpl implements IChPmEduInfoBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * <p>Discription:[学历信息表Mapper]</p>
	 */	
	@Autowired
	private ChPmEduInfoMapper chPmEduInfoMapper;
	
	/**
	 * <p>Discription:[学历信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 学历信息表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmEduInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[学历信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 学历信息表数据分页条件
	 * @param chPmEduInfoModel 学历信息表数据查询条件
	 * @param queryFields 学历信息表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmEduInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmEduInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmEduInfoMapper.queryCount((ChPmEduInfoModel)page.getModel());
		list = this.chPmEduInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[学历信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据查询条件
	 * @param queryFields 学历信息表数据查询字段
	 * @return List<ChPmEduInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmEduInfoModel> queryList(ChPmEduInfoModel model,String queryFields)throws Exception{
		List<ChPmEduInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmEduInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[学历信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据查询条件
	 * @return List<ChPmEduInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmEduInfoModel> queryList(ChPmEduInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[学历信息表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmEduInfoModel model)throws Exception{
		return this.chPmEduInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询学历信息表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
	 * @return ChPmEduInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmEduInfoModel queryById(Long id)throws Exception{
		ChPmEduInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmEduInfoMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[学历信息表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChPmEduInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmEduInfoMapper.insert(model);
		}
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	 }
	 
	 /**
	 * <p>Discription:[学历信息表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int saveBatch(List<ChPmEduInfoModel> list)throws Exception{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chPmEduInfoMapper.insertBatch(list);
		}
		//if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	 }

	/**
	 * <p>Discription:[学历信息表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 学历信息表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int edit(ChPmEduInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmEduInfoMapper.update(model);
		}
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	 }

	/**
	 * <p>Discription:[学历信息表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
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
		ChPmEduInfoModel model = chPmEduInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		
		//执行删除操作
		Integer count = this.chPmEduInfoMapper.delById(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		
		return count;
	}
	
	/**
	 * <p>Discription:[学历信息表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
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
		ChPmEduInfoModel model = chPmEduInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		//执行删除操作
		Integer count = this.chPmEduInfoMapper.delById4Logic(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[学历信息表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 学历信息表数据id的集合
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
		ChPmEduInfoModel model = chPmEduInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmEduInfoMapper.delBatchByIds(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[学历信息表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 学历信息表数据id的集合
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
		ChPmEduInfoModel model = chPmEduInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmEduInfoMapper.delBatchByIds4Logic(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmEduInfoMapper.synEduInfoSortVal(model.getWorkerId());
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据执行编号对其下所有教育经历进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synEduInfoSortVal(Long wkId)throws Exception
	{
		this.chPmEduInfoMapper.synEduInfoSortVal(wkId);
	}
	public void synEduInfoSortVal()
	{
		try {
			List<Long> wkIds = chPmEduInfoMapper.queryAllWkIds4NeedSort();
			if(wkIds.size()==0) return;
			for(Long wkId:wkIds)
			{
				this.synEduInfoSortVal(wkId);
			}
		}catch(Exception e)
		{
			logger.error("学历信息批量排序异常! error:",e);
		}
	}
}
