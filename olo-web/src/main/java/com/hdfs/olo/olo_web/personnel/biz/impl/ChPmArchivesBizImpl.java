package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmArchivesBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmArchivesMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmArchivesModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [医院职工表服务实现]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmArchivesBiz")
public class ChPmArchivesBizImpl implements IChPmArchivesBiz {
	
	/**
	 * <p>Discription:[医院职工表Mapper]</p>
	 */	
	@Autowired
	private ChPmArchivesMapper chPmArchivesMapper;
	
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmArchivesModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @param queryFields 医院职工表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmArchivesModel model,
			String queryFields)throws Exception{
			
		List<ChPmArchivesModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmArchivesMapper.queryCount((ChPmArchivesModel)page.getModel());
		if(count>0)list = this.chPmArchivesMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChPmArchivesModel>():list);
	}

	/**
	 * <p>Discription:[医院职工表数据查询总条数]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmArchivesModel model)throws Exception{
		return this.chPmArchivesMapper.queryCount(model);
	 }
	//********************以下为扩展方法***********************
}
