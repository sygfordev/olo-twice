package com.hdfs.olo.olo_web.personnel.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.personnel.model.ChPmImportRecordModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [人事档案导入记录表服务]
 * Created on 2021年04月15日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmImportRecordBiz {
	
	/**
	 * <p>Discription:[人事档案导入记录表数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 人事档案导入记录表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[人事档案导入记录表数据分页查询]</p>
	 * Created on 2021年04月15日
	 * @param page 人事档案导入记录表数据分页条件
	 * @param model 人事档案导入记录表数据查询条件
	 * @param queryFields 人事档案导入记录表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmImportRecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[人事档案导入记录表数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param model 人事档案导入记录表数据查询条件
	 * @param queryFields 人事档案导入记录表数据查询字段集合
	 * @return List<ChPmImportRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmImportRecordModel> queryList(ChPmImportRecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[人事档案导入记录表数据不分页查询]</p>
	 * Created on 2021年04月15日
	 * @param model 人事档案导入记录表数据查询条件
	 * @return List<ChPmImportRecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmImportRecordModel> queryList(ChPmImportRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表数据查询总条数]</p>
	 * Created on 2021年04月15日
	 * @param model 人事档案导入记录表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmImportRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询人事档案导入记录表数据]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return ChPmImportRecordModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmImportRecordModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[人事档案导入记录表数据新增]</p>
	 * Created on 2021年04月15日
	 * @param chPmImportRecordModel 人事档案导入记录表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmImportRecordModel model)throws Exception;
	
	/**
	 * 导入人事档案文件
	 * @param model
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,Object> doImport(ChPmImportRecordModel model,String filePath)throws Exception;
	public Map<Boolean,Object> doImport(ChPmImportRecordModel model,InputStream ins)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表数据编辑]</p>
	 * Created on 2021年04月15日
	 * @param model 人事档案导入记录表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmImportRecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表单条数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表单条数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param id 人事档案导入记录表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表批量数据删除-物理]</p>
	 * Created on 2021年04月15日
	 * @param ids 人事档案导入记录表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[人事档案导入记录表批量数据删除-逻辑]</p>
	 * Created on 2021年04月15日
	 * @param ids 人事档案导入记录表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
