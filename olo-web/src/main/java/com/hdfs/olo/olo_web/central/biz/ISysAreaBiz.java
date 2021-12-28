package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;

/**
 * Description: [区县表服务]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface ISysAreaBiz {
	
	/**
	 * <p>Discription:[区县表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 区县表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[区县表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 区县表数据分页条件
	 * @param model 区县表数据查询条件
	 * @param queryFields 区县表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, SysAreaModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[区县表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 区县表数据查询条件
	 * @param queryFields 区县表数据查询字段集合
	 * @return List<SysAreaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysAreaModel> queryList(SysAreaModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[区县表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 区县表数据查询条件
	 * @return List<SysAreaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysAreaModel> queryList(SysAreaModel model)throws Exception;
	
	/**
	 * <p>Discription:[区县表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param model 区县表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SysAreaModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询区县表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return SysAreaModel 单条数据	 
	 * @author:huadf
	 */
	public SysAreaModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[区县表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysAreaModel 区县表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SysAreaModel model)throws Exception;
	
	/**
	 * <p>Discription:[区县表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 区县表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SysAreaModel model)throws Exception;
	
	/**
	 * <p>Discription:[区县表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[区县表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 区县表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[区县表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 区县表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[区县表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 区县表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
