package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISysAreaBiz;
import com.hdfs.olo.olo_web.central.biz.ISysCityBiz;
import com.hdfs.olo.olo_web.central.mapper.SysCityMapper;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [城市表服务实现]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("sysCityBiz")
public class SysCityBizImpl implements ISysCityBiz {
	
	/**
	 * <p>Discription:[城市表Mapper]</p>
	 */	
	@Autowired
	private SysCityMapper sysCityMapper;
	@Autowired
	private ISysAreaBiz sysAreaBiz;
	
	/**
	 * <p>Discription:[城市表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 城市表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(SysCityModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[城市表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 城市表数据分页条件
	 * @param sysCityModel 城市表数据查询条件
	 * @param queryFields 城市表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,SysCityModel model,
			String queryFields)throws Exception{
			
		List<SysCityModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.sysCityMapper.queryCount((SysCityModel)page.getModel());
		list = this.sysCityMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[城市表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysCityModel 城市表数据查询条件
	 * @param queryFields 城市表数据查询字段
	 * @return List<SysCityModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<SysCityModel> queryList(SysCityModel model,String queryFields)throws Exception{
		List<SysCityModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.sysCityMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[城市表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysCityModel 城市表数据查询条件
	 * @return List<SysCityModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<SysCityModel> queryList(SysCityModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[城市表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param sysCityModel 城市表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(SysCityModel model)throws Exception{
		return this.sysCityMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询城市表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return SysCityModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public SysCityModel queryById(Long id)throws Exception{
		SysCityModel model = null;
		if(!Objects.isNull(id)){
			model = this.sysCityMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[城市表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysCityModel 城市表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(SysCityModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.sysCityMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[城市表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 城市表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(SysCityModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getCityNo())){
			count = this.sysCityMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[城市表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysCityMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[城市表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysCityMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[城市表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 城市表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysCityMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[城市表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 城市表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysCityMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 *  通过城市编码获取其下所有区县列表
	 * @param cityNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysAreaModel> loadAreaListByCityNo(Integer cityNo)throws Exception
	{
		if(null == cityNo) return new ArrayList<SysAreaModel>();
		SysAreaModel model = new SysAreaModel();
		model.setCityNo(cityNo);
		List<SysAreaModel> list = sysAreaBiz.queryList(model, "AREA_NO,AREA_NAME");
		return list;
	}
	
	/**
	 *  通过省份编码和城市编码获取其下所有区县列表
	 * @param provNo
	 * @param cityNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysAreaModel> loadAreaListByProvCityNo(Integer provNo,Integer cityNo)throws Exception
	{
		if(null == cityNo && null == provNo) return new ArrayList<SysAreaModel>();
		SysAreaModel model = new SysAreaModel();
		if(null != cityNo)model.setCityNo(cityNo);
		if(null != provNo)model.setProvNo(provNo);
		List<SysAreaModel> list = sysAreaBiz.queryList(model, "AREA_NO,AREA_NAME");
		return list;
	}
}
