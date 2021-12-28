package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.ComTabLogModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;


/**
 * Description: [操作日志服务]
 * Created on 2020年08月24日
 * @author  huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public interface IComTabLogBiz {

	/**
	 * <p>Discription:[操作日志数据分页查询]</p>
	 * Created on 2020年08月24日
	 * @param pager 操作日志数据分页条件
	 * @param model 操作日志数据查询条件
	 * @param pageInfo.queryFields 操作日志数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page<ComTabLogModel> pageInfo);
	 
	 /**
	 * <p>Discription:[操作日志数据不分页查询]</p>
	 * Created on 2020年08月24日
	 * @param pager 操作日志数据分页条件
	 * @param model 操作日志数据查询条件
	 * @param queryFields 操作日志数据查询字段集合
	 * @return List<ComTabLogModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ComTabLogModel> queryList(ComTabLogModel model, String queryFields);
	 
	 /**
	 * <p>Discription:[操作日志数据不分页查询]</p>
	 * Created on 2020年08月24日
	 * @param pager 操作日志数据分页条件
	 * @param model 操作日志数据查询条件
	 * @param queryFields 操作日志数据查询字段集合
	 * @return List<ComTabLogModel>分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ComTabLogModel> queryList(ComTabLogModel model);
	
	/**
	 * <p>Discription:[操作日志数据查询总条数]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ComTabLogModel model);
	
	/**
	 * <p>Discription:[根据id查询操作日志数据]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
	 * @return ComTabLogModel 单条数据	 
	 * @author:huadf
	 */
	public ComTabLogModel queryById(Long id);

	/**
	 * <p>Discription:[操作日志数据新增]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ComTabLogModel model);
	
	/**
	 * <p>Discription:[操作日志数据编辑]</p>
	 * Created on 2020年08月24日
	 * @param model 操作日志数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ComTabLogModel model);
	
	/**
	 * <p>Discription:[操作日志数据删除，物理删除]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id);
	
	/**
	 * <p>Discription:[操作日志数据删除，逻辑删除]</p>
	 * Created on 2020年08月24日
	 * @param id 操作日志数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id);
	
	/**
	 * <p>Discription:[操作日志数据批量删除，物理删除]</p>
	 * Created on 2020年08月24日
	 * @param ids 操作日志数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds(List<Long> ids);
	
	/**
	 * <p>Discription:[操作日志数据批量删除，逻辑删除]</p>
	 * Created on 2020年08月24日
	 * @param ids 操作日志数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delBatchByIds4Logic(List<Long> ids);
}
