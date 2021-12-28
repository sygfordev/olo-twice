package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmIncdecInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [增减情况服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmIncdecInfoBiz {
	
	/**
	 * <p>Discription:[增减情况数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 增减情况数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[增减情况数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 增减情况数据分页条件
	 * @param model 增减情况数据查询条件
	 * @param queryFields 增减情况数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmIncdecInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[增减情况数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 增减情况数据查询条件
	 * @param queryFields 增减情况数据查询字段集合
	 * @return List<ChPmIncdecInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmIncdecInfoModel> queryList(ChPmIncdecInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[增减情况数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 增减情况数据查询条件
	 * @return List<ChPmIncdecInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmIncdecInfoModel> queryList(ChPmIncdecInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[增减情况数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 增减情况数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmIncdecInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询增减情况数据]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return ChPmIncdecInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmIncdecInfoModel queryById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询增减情况数据]</p>
	 * Created on 2021年03月29日
	 * @param wkId 职工编号
	 * @return ChPmIncdecInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmIncdecInfoModel queryByWkId(Long wkId)throws Exception;

	/**
	 * <p>Discription:[增减情况数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public Long save(ChPmIncdecInfoModel model)throws Exception;
	/**
	 * <p>Discription:[增减情况数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmIncdecInfoModel 增减情况数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 public Integer saveBatch(List<ChPmIncdecInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[增减情况数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 增减情况数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmIncdecInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[增减情况单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[增减情况单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 增减情况数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[增减情况批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 增减情况数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[增减情况批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 增减情况数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
