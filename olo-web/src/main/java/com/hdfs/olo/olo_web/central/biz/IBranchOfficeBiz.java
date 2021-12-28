package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [组织机构表服务]
 * Created on 2020年03月17日
 * @author  huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public interface IBranchOfficeBiz {

	/**
	 * <p>Discription:[组织机构表数据分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 组织机构表数据分页条件
	 * @param model 组织机构表数据查询条件
	 * @param pageInfo.queryFields 组织机构表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(HttpServletRequest request,Page<BranchOfficeModel> pageInfo);
	 
	 /**
	 * <p>Discription:[组织机构表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 组织机构表数据分页条件
	 * @param model 组织机构表数据查询条件
	 * @param queryFields 组织机构表数据查询字段集合
	 * @return List<BranchOfficeModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<BranchOfficeModel> queryList(BranchOfficeModel model, String queryFields);
	 
	 /**
	 * <p>Discription:[组织机构表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 组织机构表数据分页条件
	 * @param model 组织机构表数据查询条件
	 * @param queryFields 组织机构表数据查询字段集合
	 * @return List<BranchOfficeModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<BranchOfficeModel> queryList(BranchOfficeModel model);
	 
	 /**
	  * 根据parentIds查询组织机构
	  * @param parentIds
	  * @param queryFields
	  * @return
	  */
	 public	List<BranchOfficeModel> queryListByParentIds(List<?> parentIds,String queryFields);
	 
	 /**
	  * 根据组织机构ID向上/下查询
	  * @param id
	  * @param type  0:向上 1:向下
	  * @return
	  */
	 List<BranchOfficeModel> queryListUpOrDowById(@Param("id")Long id,int type);
	
	/**
	 * <p>Discription:[组织机构表数据查询总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(BranchOfficeModel model);
	
	/**
	 * <p>Discription:[根据id查询组织机构表数据]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
	 * @return BranchOfficeModel 单条数据	 
	 * @author:huadf
	 */
	public BranchOfficeModel queryById(Long id);

	/**
	 * <p>Discription:[组织机构表数据新增]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(BranchOfficeModel model);
	
	/**
	 * <p>Discription:[组织机构表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 组织机构表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(BranchOfficeModel model);
	
	/**
	 * <p>Discription:[组织机构表数据删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id);
	
	/**
	 * <p>Discription:[组织机构表数据删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param id 组织机构表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id);
	
	/**
	 * <p>Discription:[组织机构表数据批量删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 组织机构表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds(List<Long> ids);
	
	/**
	 * <p>Discription:[组织机构表数据批量删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 组织机构表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(List<Long> ids);
}
