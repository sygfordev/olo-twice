package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.hdfs.olo.olo_web.central.biz.ISystemPrivBiz;
import com.hdfs.olo.olo_web.central.mapper.SystemPrivMapper;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.PageSetingExt;

/** 
 * Description: [系统权限表服务实现]
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@DataSource(value="smart")
@Service("systemPrivBiz")
public class SystemPrivBizImpl implements ISystemPrivBiz {
	
	/**
	 * <p>Discription:[系统权限表Mapper]</p>
	 */	
	@Resource
	private SystemPrivMapper systemPrivMapper;
	
	/**
	 * <p>Discription:[系统权限表数据分页查询]</p>
	 * Created on 2020年03月17日
	 * @param page 系统权限表数据分页条件
	 * @param model 系统权限表数据查询条件
	 * @param pageInfo.queryFields 系统权限表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	public void queryPage(HttpServletRequest request,Page<SystemPrivModel> page){
		try{
			//判断参数是否为空
			if(Objects.isNull(page)) return;
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(page.getQueryFields())){
				fields = null;
			}else{
				fields = Arrays.asList(page.getQueryFields().split(","));
			}
			//查询总条数和记录
			Long count = systemPrivMapper.queryCount(page.getModel());
			List<SystemPrivModel> list = this.systemPrivMapper.queryPage(page,page.getModel(),fields);
			page.setRecordTotal(count);
			page.setDatas(list);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Discription:[系统权限表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 系统权限表数据查询条件
	 * @param queryFields 系统权限表数据查询字段
	 * @return List<SystemPrivModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<SystemPrivModel> queryList(SystemPrivModel model,String queryFields){
		List<SystemPrivModel> list = new ArrayList<SystemPrivModel>();
		try{
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(queryFields)){
				fields = null;
			}else{
				fields = Arrays.asList(queryFields.split(","));
			}
			list = this.systemPrivMapper.queryList(model,fields);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * <p>Discription:[系统权限表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 系统权限表数据查询条件
	 * @param queryFields 系统权限表数据查询字段
	 * @return List<SystemPrivModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<SystemPrivModel> queryList(SystemPrivModel model){
		return queryList(model,null);
	}

	/**
	 * <p>Discription:[系统权限表数据查询总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 系统权限表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SystemPrivModel model){
		Long count = (long)0;
		try{
			count = this.systemPrivMapper.queryCount(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[根据id查询系统权限表数据]</p>
	 * Created on 2020年03月17日
	 * @param id 系统权限表数据id
	 * @return SystemPrivModel 单条数据	 
	 * @author:huadf
	 */
	public SystemPrivModel queryById(Long id){
		SystemPrivModel model = new SystemPrivModel();
		try{
			if(Objects.isNull(id)) return null;
			model = this.systemPrivMapper.queryById(id,null);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return model;
	}
	
	@Override
	public List<SystemPrivModel> queryListBySuper(Long superId)
	{
		return this.systemPrivMapper.queryBySuperId(superId);
	}

	/**
	 * <p>Discription:[系统权限表数据新增]</p>
	 * Created on 2020年03月17日
	 * @param model 系统权限表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	public int save(SystemPrivModel model){
		int count = 0;
		try{
			if(Objects.isNull(model)) return 0;
			count = this.systemPrivMapper.insert(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统权限表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 系统权限表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	public int edit(SystemPrivModel model){
		int count = 0;
		try{
			if(Objects.isNull(model) || Objects.isNull(model.getPrivId())){
				return 0;
			}else{
				count = this.systemPrivMapper.update(model);
			}	
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统权限表数据删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统权限表数据id
	 * @return 成功条数 	
	 *								   
	 * @author:huadf
	 */
	public int delById(Long id){
		Integer count = 0;
		try{
			if(Objects.isNull(id)){
				return 0;
			}else{
				count = this.systemPrivMapper.delById(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[系统权限表数据删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统权限表数据id
	 * @return 成功条数 	
	 *								   
	 * @author:huadf
	 */
	public int delById4Logic(Long id){
		Integer count = 0;
		try{
			if(Objects.isNull(id)){
				return 0;
			}else{
				count = this.systemPrivMapper.delById4Logic(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统权限表数据批量删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统权限表数据id的集合
	 * @return 成功条数
	 *								  	 
	 * @author:huadf
	 */
	public int delBatchByIds(List<Long> ids){
		Integer count = 0;
		try{
			if(null == ids || ids.isEmpty()){
				return 0;
			}else{
				count = this.systemPrivMapper.delBatchByIds(ids);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[系统权限表数据批量删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统权限表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(String ids){
		Integer count = 0;
		try{
			if(Objects.isNull(ids)){
				return 0;
			}else{
				List<String> list = Arrays.asList(ids.split(","));
				count = this.systemPrivMapper.delBatchByIds4Logic(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
}
