package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.ISystemRoleBiz;
import com.hdfs.olo.olo_web.central.mapper.SystemRoleMapper;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/** 
 * Description: [系统角色表服务实现]
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@DataSource(value="smart")
@Service("systemRoleBiz")
public class SystemRoleBizImpl implements ISystemRoleBiz {
	
	/**
	 * <p>Discription:[系统角色表Mapper]</p>
	 */	
	@Resource
	private SystemRoleMapper systemRoleMapper;
	
	/**
	 * <p>Discription:[系统角色表数据分页查询]</p>
	 * Created on 2020年03月17日
	 * @param page 系统角色表数据分页条件
	 * @param model 系统角色表数据查询条件
	 * @param pageInfo.queryFields 系统角色表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	public void queryPage(Page<SystemRoleModel> page){
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
			Long count = systemRoleMapper.queryCount(page.getModel());
			List<SystemRoleModel> list = this.systemRoleMapper.queryPage(page,page.getModel(),fields);
			page.setRecordTotal(count);
			page.setDatas(list);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Discription:[系统角色表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据查询条件
	 * @param queryFields 系统角色表数据查询字段
	 * @return List<SystemRoleModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<SystemRoleModel> queryList(SystemRoleModel model,String queryFields){
		List<SystemRoleModel> list = new ArrayList<SystemRoleModel>();
		try{
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(queryFields)){
				fields = null;
			}else{
				fields = Arrays.asList(queryFields.split(","));
			}
			list = this.systemRoleMapper.queryList(model,fields);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * <p>Discription:[系统角色表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据查询条件
	 * @param queryFields 系统角色表数据查询字段
	 * @return List<SystemRoleModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<SystemRoleModel> queryList(SystemRoleModel model){
		return queryList(model,null);
	}

	/**
	 * <p>Discription:[系统角色表数据查询总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SystemRoleModel model){
		Long count = (long)0;
		try{
			count = this.systemRoleMapper.queryCount(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[根据id查询系统角色表数据]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
	 * @return SystemRoleModel 单条数据	 
	 * @author:huadf
	 */
	public SystemRoleModel queryById(Long id){
		SystemRoleModel Model = new SystemRoleModel();
		try{
			if(Objects.isNull(id)) return null;
			Model = this.systemRoleMapper.queryById(id,null);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return Model;
	}

	/**
	 * <p>Discription:[系统角色表数据新增]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	public int save(SystemRoleModel model){
		int count = 0;
		try{
			if(Objects.isNull(model)) return 0;
			count = this.systemRoleMapper.insert(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统角色表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	public int edit(SystemRoleModel model){
		int count = 0;
		try{
			if(Objects.isNull(model) || Objects.isNull(model.getRoleId())){
				return 0;
			}else{
				count = this.systemRoleMapper.update(model);
			}	
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统角色表数据删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
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
				count = this.systemRoleMapper.delById(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[系统角色表数据删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
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
				count = this.systemRoleMapper.delById4Logic(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[系统角色表数据批量删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统角色表数据id的集合
	 * @return 成功条数
	 *								  	 
	 * @author:huadf
	 */
	public int delBatchByIds(String ids){
		Integer count = 0;
		try{
			if(Objects.isNull(ids)){
				return 0;
			}else{
				List<String> list = Arrays.asList(ids.split(","));
				count = this.systemRoleMapper.delBatchByIds(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[系统角色表数据批量删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统角色表数据id的集合
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
				count = this.systemRoleMapper.delBatchByIds4Logic(list);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
}
