package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.central.model.SystemDictItemModel;

/**
 * Description: [系统字典项服务]
 * Created on 2021年03月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface ISystemDictItemBiz {
	
	/**
	 * <p>Discription:[系统字典项数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典项数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[系统字典项数据分页查询]</p>
	 * Created on 2021年03月04日
	 * @param page 系统字典项数据分页条件
	 * @param model 系统字典项数据查询条件
	 * @param queryFields 系统字典项数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, SystemDictItemModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[系统字典项数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典项数据查询条件
	 * @param queryFields 系统字典项数据查询字段集合
	 * @return List<SystemDictItemModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemDictItemModel> queryList(SystemDictItemModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[系统字典项数据不分页查询]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典项数据查询条件
	 * @return List<SystemDictItemModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SystemDictItemModel> queryList(SystemDictItemModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项数据查询总条数]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典项数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SystemDictItemModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询系统字典项数据]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return SystemDictItemModel 单条数据	 
	 * @author:huadf
	 */
	public SystemDictItemModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[系统字典项数据新增]</p>
	 * Created on 2021年03月04日
	 * @param systemDictItemModel 系统字典项数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SystemDictItemModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项数据编辑]</p>
	 * Created on 2021年03月04日
	 * @param model 系统字典项数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SystemDictItemModel model)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项单条数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项单条数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param id 系统字典项数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项批量数据删除-物理]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典项数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[系统字典项批量数据删除-逻辑]</p>
	 * Created on 2021年03月04日
	 * @param ids 系统字典项数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据字典主键查询顶级字典项列表
	 * @param dictId
	 * @return
	 * @throws Exception
	 */
	public List<SystemDictItemModel> loadItemByDictId(Long dictId) throws Exception;
	
	/**
	 * 通过字典编码和字典项编码获取字典项值
	 * @param itemKey
	 * @param dictCode
	 * @return
	 */
	public String getValByKeyWithDictCode(String itemKey,String dictCode);
}
