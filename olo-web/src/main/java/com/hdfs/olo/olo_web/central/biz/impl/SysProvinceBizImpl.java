package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISysCityBiz;
import com.hdfs.olo.olo_web.central.biz.ISysProvinceBiz;
import com.hdfs.olo.olo_web.central.mapper.SysProvinceMapper;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.central.model.SysProvinceModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [省份表服务实现]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="smart")
@Service("sysProvinceBiz")
public class SysProvinceBizImpl implements ISysProvinceBiz {
	
	/**
	 * <p>Discription:[省份表Mapper]</p>
	 */	
	@Autowired
	private SysProvinceMapper sysProvinceMapper;
	@Autowired
	private ISysCityBiz sysCityBiz;
	
	/**
	 * <p>Discription:[省份表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 省份表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(SysProvinceModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[省份表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 省份表数据分页条件
	 * @param sysProvinceModel 省份表数据查询条件
	 * @param queryFields 省份表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,SysProvinceModel model,
			String queryFields)throws Exception{
			
		List<SysProvinceModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.sysProvinceMapper.queryCount((SysProvinceModel)page.getModel());
		list = this.sysProvinceMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[省份表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysProvinceModel 省份表数据查询条件
	 * @param queryFields 省份表数据查询字段
	 * @return List<SysProvinceModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<SysProvinceModel> queryList(SysProvinceModel model,String queryFields)throws Exception{
		List<SysProvinceModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.sysProvinceMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[省份表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param sysProvinceModel 省份表数据查询条件
	 * @return List<SysProvinceModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<SysProvinceModel> queryList(SysProvinceModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[省份表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param sysProvinceModel 省份表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(SysProvinceModel model)throws Exception{
		return this.sysProvinceMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询省份表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return SysProvinceModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public SysProvinceModel queryById(Long id)throws Exception{
		SysProvinceModel model = null;
		if(!Objects.isNull(id)){
			model = this.sysProvinceMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[省份表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysProvinceModel 省份表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(SysProvinceModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.sysProvinceMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[省份表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 省份表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(SysProvinceModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getProvNo())){
			count = this.sysProvinceMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[省份表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysProvinceMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[省份表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.sysProvinceMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[省份表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 省份表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysProvinceMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[省份表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 省份表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.sysProvinceMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 通过省份编号获取其下所有城市列表
	 * @param provNo 省份编号
	 * @return
	 */
	public List<SysCityModel> loadCityListByProvNo(Integer provNo) throws Exception
	{
		if(null == provNo) return new ArrayList<SysCityModel>();
		SysCityModel model = new SysCityModel();
		model.setProvNo(provNo);
		List<SysCityModel> list = sysCityBiz.queryList(model, "CITY_NO,CITY_NAME");
		return list;
	}
}
