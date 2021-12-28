package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustRecordModel;

/**
 * Description: [调资记录表服务]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaAdjustRecordBiz {
	
	/**
	 * <p>Discription:[调资记录表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资记录表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[调资记录表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资记录表数据分页条件
	 * @param model 调资记录表数据查询条件
	 * @param queryFields 调资记录表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaAdjustRecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资记录表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资记录表数据查询条件
	 * @param queryFields 调资记录表数据查询字段集合
	 * @return List<ChSaAdjustRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustRecordModel> queryList(ChSaAdjustRecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资记录表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资记录表数据查询条件
	 * @return List<ChSaAdjustRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustRecordModel> queryList(ChSaAdjustRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param model 调资记录表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaAdjustRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询调资记录表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return ChSaAdjustRecordModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaAdjustRecordModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[调资记录表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustRecordModel 调资记录表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public Long save(ChSaAdjustRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资记录表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaAdjustRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资记录表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资记录表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[调资记录表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资记录表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
