package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/**
 * Description: [系统角色表服务]
 * Created on 2020年03月17日
 * @author  huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public interface ISystemRoleBiz {

	/**
	 * <p>Discription:[系统角色表数据分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 系统角色表数据分页条件
	 * @param model 系统角色表数据查询条件
	 * @param pageInfo.queryFields 系统角色表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page<SystemRoleModel> pageInfo);
	 
	 /**
	 * <p>Discription:[系统角色表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 系统角色表数据分页条件
	 * @param model 系统角色表数据查询条件
	 * @param queryFields 系统角色表数据查询字段集合
	 * @return List<SystemRoleModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemRoleModel> queryList(SystemRoleModel model, String queryFields);
	 
	 /**
	 * <p>Discription:[系统角色表数据不分页查询]</p>
	 * Created on 2020年03月17日
	 * @param pager 系统角色表数据分页条件
	 * @param model 系统角色表数据查询条件
	 * @param queryFields 系统角色表数据查询字段集合
	 * @return List<SystemRoleModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemRoleModel> queryList(SystemRoleModel model);
	
	/**
	 * <p>Discription:[系统角色表数据查询总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SystemRoleModel model);
	
	/**
	 * <p>Discription:[根据id查询系统角色表数据]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
	 * @return SystemRoleModel 单条数据	 
	 * @author:huadf
	 */
	public SystemRoleModel queryById(Long id);

	/**
	 * <p>Discription:[系统角色表数据新增]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SystemRoleModel model);
	
	/**
	 * <p>Discription:[系统角色表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 系统角色表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SystemRoleModel model);
	
	/**
	 * <p>Discription:[系统角色表数据删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id);
	
	/**
	 * <p>Discription:[系统角色表数据删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param id 系统角色表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id);
	
	/**
	 * <p>Discription:[系统角色表数据批量删除，物理删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统角色表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds(String ids);
	
	/**
	 * <p>Discription:[系统角色表数据批量删除，逻辑删除]</p>
	 * Created on 2020年03月17日
	 * @param ids 系统角色表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(String ids);
}
