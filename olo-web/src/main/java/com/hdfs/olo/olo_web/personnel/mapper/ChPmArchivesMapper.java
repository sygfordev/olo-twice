package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.personnel.model.ChPmArchivesModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [人事档案Mapper]</p>
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChPmArchivesMapper{
	
	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChPmArchivesModel entity);
	/**
	* 分页查询固定参数
	*/
	List<ChPmArchivesModel> queryPage(@Param("page")Page page, @Param("entity")ChPmArchivesModel entity, @Param("queryFields")List<?> queryFields);
	//*****************************以下为扩展方法****************************
}
