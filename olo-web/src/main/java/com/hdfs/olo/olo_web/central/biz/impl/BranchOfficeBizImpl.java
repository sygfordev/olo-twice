package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

import com.hdfs.olo.olo_web.central.biz.IBranchOfficeBiz;
import com.hdfs.olo.olo_web.central.mapper.BranchOfficeMapper;
import com.hdfs.olo.olo_web.central.mapper.UserInfoMapper;
import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [组织机构表服务实现]
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@DataSource(value="smart")
@Service("branchOfficeBiz")
public class BranchOfficeBizImpl implements IBranchOfficeBiz {
	
	/**
	 * <p>Discription:[组织机构表Mapper]</p>
	 */	
	@Autowired
	private BranchOfficeMapper branchOfficeMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * <p>Discription:[组织机构表数据分页查询]</p>
	 * Created on 2020年03月17日
	 * @param page 组织机构表数据分页条件
	 * @param model 组织机构表数据查询条件
	 * @param pageInfo.queryFields 组织机构表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	public void queryPage(HttpServletRequest request,Page<BranchOfficeModel> page){
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
			Long count = branchOfficeMapper.queryCount(page.getModel());
			List<BranchOfficeModel> list = this.branchOfficeMapper.queryPage(page,page.getModel(),fields);
			page.setRecordTotal(count);
			page.setDatas(list);
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * <p>Discription:[组织机构表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据查询条件
	 * @param queryFields 组织机构表数据查询字段
	 * @return List<BranchOfficeModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<BranchOfficeModel> queryList(BranchOfficeModel model,String queryFields){
		List<BranchOfficeModel> list = new ArrayList<BranchOfficeModel>();
		try{
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(queryFields)){
				fields = null;
			}else{
				fields = Arrays.asList(queryFields.split(","));
			}
			list = this.branchOfficeMapper.queryList(model,fields);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return list;
	}
	
	/**
	  * 根据parentIds查询组织机构
	  * @param parentIds
	  * @param queryFields
	  * @return
	  */
	 public	List<BranchOfficeModel> queryListByParentIds(List<?> parentIds,String queryFields)
	 {
		 List<BranchOfficeModel> list = new ArrayList<BranchOfficeModel>();
		 try{
			List<String> fields = new ArrayList<String>();
			if(Objects.isNull(queryFields)){
				fields = null;
			}else{
				fields = Arrays.asList(queryFields.split(","));
			}
			list = this.branchOfficeMapper.queryListByParentIds(parentIds,fields);
		 }catch(Exception e){
			 throw new RuntimeException(e);
		 }
		 return list;
	 }
	 
	 @Override
	 public List<BranchOfficeModel> queryListUpOrDowById(@Param("id")Long id,int type)
	 {
		 try{
			if(Objects.isNull(id)) throw new RuntimeException("请指定查找方向");
			List<BranchOfficeModel> retlist = null;
			retlist = (0==type)?this.branchOfficeMapper.queryListUpById(id):this.branchOfficeMapper.queryListDownById(id);
			return retlist;
		 }catch(Exception e){
			throw new RuntimeException(e);
		 }
	 }
	/**
	 * <p>Discription:[组织机构表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据查询条件
	 * @param queryFields 组织机构表数据查询字段
	 * @return List<BranchOfficeModel>数据
	 *													       	 
	 * @author:huadf
	 */
	public List<BranchOfficeModel> queryList(BranchOfficeModel model){
		return queryList(model,null);
	}

	/**
	 * <p>Discription:[组织机构表数据查询总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(BranchOfficeModel model){
		Long count = (long)0;
		try{
			count = this.branchOfficeMapper.queryCount(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[根据id查询组织机构表数据]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
	 * @return BranchOfficeModel 单条数据	 
	 * @author:huadf
	 */
	public BranchOfficeModel queryById(Long id){
		BranchOfficeModel Model = new BranchOfficeModel();
		try{
			if(Objects.isNull(id)) return null;
			Model = this.branchOfficeMapper.queryById(id,null);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return Model;
	}

	/**
	 * <p>Discription:[组织机构表数据新增]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	public int save(BranchOfficeModel model){
		int count = 0;
		try{
			if(Objects.isNull(model)) return 0;
			count = this.branchOfficeMapper.insert(model);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[组织机构表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	public int edit(BranchOfficeModel model){
		int count = 0;
		try{
			if(Objects.isNull(model) || Objects.isNull(model.getId())){
				return 0;
			}else{
				count = this.branchOfficeMapper.update(model);
			}	
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[组织机构表数据删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
	 * @return 成功条数 	
	 *								   
	 * @author:huadf
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public int delById(Long id){
		Integer count = 0;
		try{
			if(Objects.isNull(id)){
				return 0;
			}else{
				count = this.branchOfficeMapper.delById(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[组织机构表数据删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
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
				count = this.branchOfficeMapper.delById4Logic(id);
			}		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}

	/**
	 * <p>Discription:[组织机构表数据批量删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 组织机构表数据id的集合
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
				count = this.branchOfficeMapper.delBatchByIds(ids);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[组织机构表数据批量删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 组织机构表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(List<Long> ids){
		Integer count = 0;
		try{
			if(null == ids || ids.isEmpty()){
				return 0;
			}else{
				count = this.branchOfficeMapper.delBatchByIds4Logic(ids);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		return count;
	}
}
