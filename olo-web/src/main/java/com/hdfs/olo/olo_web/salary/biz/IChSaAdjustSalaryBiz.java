package com.hdfs.olo.olo_web.salary.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustRecordModel;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustSalaryModel;

/**
 * Description: [调资表服务]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaAdjustSalaryBiz {
	
	/**
	 * <p>Discription:[调资表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[调资表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资表数据分页条件
	 * @param model 调资表数据查询条件
	 * @param queryFields 调资表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaAdjustSalaryModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资表数据查询条件
	 * @param queryFields 调资表数据查询字段集合
	 * @return List<ChSaAdjustSalaryModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustSalaryModel> queryList(ChSaAdjustSalaryModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资表数据查询条件
	 * @return List<ChSaAdjustSalaryModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustSalaryModel> queryList(ChSaAdjustSalaryModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param model 调资表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaAdjustSalaryModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询调资表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return ChSaAdjustSalaryModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaAdjustSalaryModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[调资表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustSalaryModel 调资表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaAdjustSalaryModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaAdjustSalaryModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[调资表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 导入薪级类别文件
	 * @param ins
	 * @param recModel 调资记录
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,Object> doImport(InputStream ins,ChSaAdjustRecordModel recModel)throws Exception;
	
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	public List<String> loadSelectFields(String column);
}
