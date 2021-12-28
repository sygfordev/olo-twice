package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.IComTabLogBiz;
import com.hdfs.olo.olo_web.central.mapper.ComTabLogMapper;
import com.hdfs.olo.olo_web.central.model.ComTabLogModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/** 
 * Description: [操作日志服务实现]
 * Created on 2020年08月24日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@DataSource(value="smart")
@Service("comTabLogBiz")
public class ComTabLogBizImpl implements IComTabLogBiz {
	
	/**
	 * <p>Discription:[操作日志Mapper]</p>
	 */	
	@Resource
	private ComTabLogMapper comTabLogMapper;
	
	/**
	 * <p>Discription:[操作日志数据分页查询]</p>
	 * Created on 2020年08月24日
	 * @param page 操作日志数据分页条件
	 * @param model 操作日志数据查询条件
	 * @param pageInfo.queryFields 操作日志数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	public void queryPage(Page<ComTabLogModel> page){
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
			Long count = comTabLogMapper.queryCount(page.getModel());
			List<ComTabLogModel> list = this.comTabLogMapper.queryPage(page,page.getModel(),fields);
			page.setRecordTotal(count);
			page.setDatas(list);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Discription:[操作日志数据不分页查询]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据查询条件
	 * @param queryFields 操作日志数据查询字段
	 * @return List<ComTabLogModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<ComTabLogModel> queryList(ComTabLogModel model,String queryFields){
		List<ComTabLogModel> list = new ArrayList<ComTabLogModel>();
		try{
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(queryFields)){
				fields = null;
			}else{
				fields = Arrays.asList(queryFields.split(","));
			}
			list = this.comTabLogMapper.queryList(model,fields);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	 * <p>Discription:[操作日志数据不分页查询]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据查询条件
	 * @param queryFields 操作日志数据查询字段
	 * @return List<ComTabLogModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<ComTabLogModel> queryList(ComTabLogModel model){
		return queryList(model,null);
	}

	/**
	 * <p>Discription:[操作日志数据查询总条数]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ComTabLogModel model){
		Long count = (long)0;
		try{
			count = this.comTabLogMapper.queryCount(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[根据id查询操作日志数据]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
	 * @return ComTabLogModel 单条数据	 
	 * @author:huadf
	 */
	public ComTabLogModel queryById(Long id){
		ComTabLogModel Model = new ComTabLogModel();
		try{
			if(Objects.isNull(id)) return null;
			Model = this.comTabLogMapper.queryById(id,null);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return Model;
	}

	/**
	 * <p>Discription:[操作日志数据新增]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	public int save(ComTabLogModel model){
		int count = 0;
		try{
			if(Objects.isNull(model)) return 0;
			count = this.comTabLogMapper.insert(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[操作日志数据编辑]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	public int edit(ComTabLogModel model){
		int count = 0;
		try{
			if(Objects.isNull(model) || Objects.isNull(model.getLogId())){
				return 0;
			}else{
				count = this.comTabLogMapper.update(model);
			}	
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[操作日志数据删除，物理删除]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
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
				count = this.comTabLogMapper.delById(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[操作日志数据删除，逻辑删除]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
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
				count = this.comTabLogMapper.delById4Logic(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[操作日志数据批量删除，物理删除]</p>
	 * Created on 2020年08月24日
	 * @param ids 操作日志数据id的集合
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
				count = this.comTabLogMapper.delBatchByIds(ids);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[操作日志数据批量删除，逻辑删除]</p>
	 * Created on 2020年08月24日
	 * @param ids 操作日志数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(List<Long> ids){
		Integer count = 0;
		try{
			if(null == ids || ids.isEmpty()){
				return 0;
			}else{
				count = this.comTabLogMapper.delBatchByIds4Logic(ids);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
}
