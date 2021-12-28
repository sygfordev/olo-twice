package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaWechatTokenBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaWechatTokenMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatTokenModel;

/** 
 * Description: [微信访问Token服务实现]
 * Created on 2021年05月26日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaWechatTokenBiz")
public class ChSaWechatTokenBizImpl implements IChSaWechatTokenBiz {
	
	/**
	 * <p>Discription:[微信访问TokenMapper]</p>
	 */	
	@Autowired
	private ChSaWechatTokenMapper chSaWechatTokenMapper;
	
	/**
	 * <p>Discription:[微信访问Token数据分页查询]</p>
	 * Created on 2021年05月26日
	 * @param page 微信访问Token数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaWechatTokenModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[微信访问Token数据分页查询]</p>
	 * Created on 2021年05月26日
	 * @param page 微信访问Token数据分页条件
	 * @param chSaWechatTokenModel 微信访问Token数据查询条件
	 * @param queryFields 微信访问Token数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaWechatTokenModel model,
			String queryFields)throws Exception{
			
		List<ChSaWechatTokenModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaWechatTokenMapper.queryCount((ChSaWechatTokenModel)page.getModel());
		if(count>0) list = this.chSaWechatTokenMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaWechatTokenModel>():list);
	}

	/**
	 * <p>Discription:[微信访问Token数据不分页查询]</p>
	 * Created on 2021年05月26日
	 * @param chSaWechatTokenModel 微信访问Token数据查询条件
	 * @param queryFields 微信访问Token数据查询字段
	 * @return List<ChSaWechatTokenModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaWechatTokenModel> queryList(ChSaWechatTokenModel model,String queryFields)throws Exception{
		List<ChSaWechatTokenModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaWechatTokenMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[微信访问Token数据不分页查询]</p>
	 * Created on 2021年05月26日
	 * @param chSaWechatTokenModel 微信访问Token数据查询条件
	 * @return List<ChSaWechatTokenModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaWechatTokenModel> queryList(ChSaWechatTokenModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[微信访问Token数据查询总条数]</p>
	 * Created on 2021年05月26日
	 * @param chSaWechatTokenModel 微信访问Token数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaWechatTokenModel model)throws Exception{
		return this.chSaWechatTokenMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询微信访问Token数据]</p>
	 * Created on 2021年05月26日
	 * @param id 微信访问Token数据id
	 * @return ChSaWechatTokenModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaWechatTokenModel queryById(Long id)throws Exception{
		ChSaWechatTokenModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaWechatTokenMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[微信访问Token数据新增]</p>
	 * Created on 2021年05月26日
	 * @param chSaWechatTokenModel 微信访问Token数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChSaWechatTokenModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			if(!StringHelper.isNullOrEmpty(model.getCardNo()))
				this.chSaWechatTokenMapper.delByIdcard(model.getCardNo());
			count = this.chSaWechatTokenMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[微信访问Token数据编辑]</p>
	 * Created on 2021年05月26日
	 * @param model 微信访问Token数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaWechatTokenModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaWechatTokenMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[微信访问Token单条数据删除-逻辑]</p>
	 * Created on 2021年05月26日
	 * @param id 微信访问Token数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaWechatTokenMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[微信访问Token批量数据删除-物理]</p>
	 * Created on 2021年05月26日
	 * @param ids 微信访问Token数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaWechatTokenMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[微信访问Token根据身份证号删除]</p>
	 * Created on 2021年05月26日
	 * @param cardNo 身份证号
	 * @return 成功条数 
	 * @author:huadf
	 */
	@Override
	public int delByIdcard(String cardNo)throws Exception{
		Integer count = 0;
		if(!StringHelper.isNullOrEmpty(cardNo)){
			count = this.chSaWechatTokenMapper.delByIdcard(cardNo);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 清除minutes时间到现在的数据，单位unit
	 * @param num 数据
	 * @param unit 单位：H小时  M分钟  S秒
	 * @return
	 * @throws Exception
	 */
	public int delByTime(int num,String unit)throws Exception
	{
		if(num<=0 || (!"H".equals(unit) && !"M".equals(unit) && !"S".equals(unit)))
			throw new Exception("清除token所需参数异常!");
		Integer count = this.chSaWechatTokenMapper.delByTime(num,unit);
		return count;
	}
}
