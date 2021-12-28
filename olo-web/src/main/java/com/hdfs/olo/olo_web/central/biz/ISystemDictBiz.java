package com.hdfs.olo.olo_web.central.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.central.model.SystemDictModel;

/**
 * Description: [系统字典服务]
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface ISystemDictBiz {
	
	/**
	 * <p>Discription:[系统字典数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[系统字典数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典数据分页条件
	 * @param model 系统字典数据查询条件
	 * @param queryFields 系统字典数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, SystemDictModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[系统字典数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典数据查询条件
	 * @param queryFields 系统字典数据查询字段集合
	 * @return List<SystemDictModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemDictModel> queryList(SystemDictModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[系统字典数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典数据查询条件
	 * @return List<SystemDictModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemDictModel> queryList(SystemDictModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典数据查询总条数]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SystemDictModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询系统字典数据]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return SystemDictModel 单条数据	 
	 * @author:huadf
	 */
	public SystemDictModel queryById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[根据code查询系统字典数据]</p>
	 * Created on 2021年03月04日
	 * @param code 系统字典数据code
	 * @return SystemDictModel 单条数据	 
	 * @author:huadf
	 */
	public SystemDictModel queryByCode(String dictCode)throws Exception;

	/**
	 * <p>Discription:[系统字典数据新增]</p>
	 * Created on 2021年03月04日
	 * @param systemDictModel 系统字典数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SystemDictModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典数据编辑]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SystemDictModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典单条数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[系统字典单条数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[系统字典批量数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[系统字典批量数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 *  通过字典编码集合查询字典项集合【分类】
	 * @param codes
	 * @return
	 * @throws Exception
	 */
	public Map<String,List<Map<String,Object>>> queryItemListByCodes(List<String> codes);
}
