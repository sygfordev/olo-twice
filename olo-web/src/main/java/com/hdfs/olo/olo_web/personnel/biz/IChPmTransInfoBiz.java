package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmTransInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [调动信息表服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmTransInfoBiz {
	
	/**
	 * <p>Discription:[调动信息数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 调动信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[调动信息数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 调动信息数据分页条件
	 * @param model 调动信息数据查询条件
	 * @param queryFields 调动信息数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmTransInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调动信息数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param model 调动信息数据查询条件
	 * @param queryFields 调动信息数据查询字段集合
	 * @return List<ChPmTransInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmTransInfoModel> queryList(ChPmTransInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调动信息数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param model 调动信息数据查询条件
	 * @return List<ChPmTransInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmTransInfoModel> queryList(ChPmTransInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[调动信息数据查询总条数]</p>
	 * Created on 2021年04月15日
	 * @param model 调动信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmTransInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询调动信息数据]</p>
	 * Created on 2021年04月15日
	 * @param id 调动信息数据id
	 * @return ChPmTransInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmTransInfoModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[调动信息数据新增]</p>
	 * Created on 2021年04月15日
	 * @param chPmTransInfoModel 调动信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmTransInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[调动信息数据编辑]</p>
	 * Created on 2021年04月15日
	 * @param model 调动信息数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmTransInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[调动信息单条数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param id 调动信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调动信息单条数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param id 调动信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调动信息批量数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param ids 调动信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[调动信息批量数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param ids 调动信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
