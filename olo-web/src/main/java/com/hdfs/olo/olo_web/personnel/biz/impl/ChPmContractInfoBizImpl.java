package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.personnel.biz.IChPmContractInfoBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmContractInfoMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;

/** 
 * Description: [合同信息服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmContractInfoBiz")
public class ChPmContractInfoBizImpl implements IChPmContractInfoBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * <p>Discription:[合同信息Mapper]</p>
	 */	
	@Autowired
	private ChPmContractInfoMapper chPmContractInfoMapper;
	
	/**
	 * <p>Discription:[合同信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 合同信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmContractInfoModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[合同信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 合同信息数据分页条件
	 * @param chPmContractInfoModel 合同信息数据查询条件
	 * @param queryFields 合同信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmContractInfoModel model,
			String queryFields)throws Exception{
			
		List<ChPmContractInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmContractInfoMapper.queryCount((ChPmContractInfoModel)page.getModel());
		list = this.chPmContractInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[合同信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据查询条件
	 * @param queryFields 合同信息数据查询字段
	 * @return List<ChPmContractInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmContractInfoModel> queryList(ChPmContractInfoModel model,String queryFields)throws Exception{
		List<ChPmContractInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmContractInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[合同信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据查询条件
	 * @return List<ChPmContractInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmContractInfoModel> queryList(ChPmContractInfoModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[合同信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmContractInfoModel model)throws Exception{
		return this.chPmContractInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询合同信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
	 * @return ChPmContractInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmContractInfoModel queryById(Long id)throws Exception{
		ChPmContractInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmContractInfoMapper.queryById(id,null);
		}
		return model;
	 }
	 
	/**
	 * 根据合同过期时间查询即将过期和已过期的合同
	 * @param val
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryByContEndTime(boolean isAlreadExist4Msg,int val,String unit)throws Exception{
		if(StringHelper.isNullOrEmpty(unit)) throw new Exception("单位为空!");
		
		List<Map<String,Object>> list = this.chPmContractInfoMapper.queryByContEndTime(isAlreadExist4Msg,val, unit);
		return list;
	}

	/**
	 * <p>Discription:[合同信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChPmContractInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmContractInfoMapper.insert(model);
		}
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		return count;
	 }

	 /**
	 * <p>Discription:[合同信息数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmContractInfoModel> list)throws Exception
	{
		Integer count = 0;
		if(null!=list && list.size()>0){
			count = this.chPmContractInfoMapper.insertBatch(list);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[合同信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 合同信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int edit(ChPmContractInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chPmContractInfoMapper.update(model);
		}
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		return count;
	 }

	/**
	 * <p>Discription:[合同信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
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
		ChPmContractInfoModel model = chPmContractInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		
		//执行删除操作
		Integer count = this.chPmContractInfoMapper.delById(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		
		return count;
	}
	
	/**
	 * <p>Discription:[合同信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
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
		ChPmContractInfoModel model = chPmContractInfoMapper.queryById(id, queryFields);
		if(null == model) return 0;
		//执行删除操作
		Integer count = this.chPmContractInfoMapper.delById4Logic(id);
		
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[合同信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 合同信息数据id的集合
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
		ChPmContractInfoModel model = chPmContractInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmContractInfoMapper.delBatchByIds(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		return count;
	}

	/**
	 * <p>Discription:[合同信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 合同信息数据id的集合
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
		ChPmContractInfoModel model = chPmContractInfoMapper.queryById(ids.get(0), queryFields);
		if(null == model) return 0;
		//执行删除操作
		count = this.chPmContractInfoMapper.delBatchByIds4Logic(ids);
		//删除成功后，开始对存量数据进行排序
		if(count>0) this.chPmContractInfoMapper.synContInfoSortVal(model.getWorkerId());
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * 根据执行编号对其下所有合同信息进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synContInfoSortVal(Long wkId)throws Exception
	{
		this.chPmContractInfoMapper.synContInfoSortVal(wkId);
	}
	public void synContInfoSortVal()
	{
		try {
			List<Long> wkIds = chPmContractInfoMapper.queryAllWkIds4NeedSort();
			if(wkIds.size()==0) return;
			for(Long wkId:wkIds)
			{
				this.synContInfoSortVal(wkId);
			}
		}catch(Exception e)
		{
			logger.error("学历信息批量排序异常! error:",e);
		}
	}
}
