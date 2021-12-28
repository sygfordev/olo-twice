package com.hdfs.olo.olo_web.salary.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaAdjustFormulaModel;

/**
 * Description: [调资-公式表服务]
 * Created on 2021年05月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaAdjustFormulaBiz {
	
	/**
	 * <p>Discription:[调资-公式表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资-公式表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[调资-公式表数据分页查询]</p>
	 * Created on 2021年05月25日
	 * @param page 调资-公式表数据分页条件
	 * @param model 调资-公式表数据查询条件
	 * @param queryFields 调资-公式表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaAdjustFormulaModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资-公式表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资-公式表数据查询条件
	 * @param queryFields 调资-公式表数据查询字段集合
	 * @return List<ChSaAdjustFormulaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustFormulaModel> queryList(ChSaAdjustFormulaModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[调资-公式表数据不分页查询]</p>
	 * Created on 2021年05月25日
	 * @param model 调资-公式表数据查询条件
	 * @return List<ChSaAdjustFormulaModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaAdjustFormulaModel> queryList(ChSaAdjustFormulaModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表数据查询总条数]</p>
	 * Created on 2021年05月25日
	 * @param model 调资-公式表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaAdjustFormulaModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询调资-公式表数据]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return ChSaAdjustFormulaModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaAdjustFormulaModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[调资-公式表数据新增]</p>
	 * Created on 2021年05月25日
	 * @param chSaAdjustFormulaModel 调资-公式表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaAdjustFormulaModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表数据编辑]</p>
	 * Created on 2021年05月25日
	 * @param model 调资-公式表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaAdjustFormulaModel model)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表单条数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表单条数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param id 调资-公式表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表批量数据删除-物理]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资-公式表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表批量数据删除-逻辑]</p>
	 * Created on 2021年05月25日
	 * @param ids 调资-公式表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 * 导入薪级类别文件
	 * @param ins
	 * @param targetMonth
	 * @param batchNo
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,Object> doImport(InputStream ins,String formulaType)throws Exception;
	
	/**
	 * <p>Discription:[调资-公式表数据根据公式类型进行查询]</p>
	 * Created on 2021年05月25日
	 * @param types 公式类型  A1 A2 B1 B2 三类
	 * @return List<ChSaAdjustFormulaModel>列表数据							       	 
	 * @author:huadf
	 */
	public List<ChSaAdjustFormulaModel> queryList(List<String> types)throws Exception;
	
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别
	 * @param recordId
	 * @return
	 */
	public List<Map<String,Object>> queryAdjustTypeAndUniqueKey(Long recordId);
	/**
	 * 通过调资记录主键查找本次调资对应的薪级类别（计算公式）列表
	 * @param adjustType
	 * @param uniqueKey
	 * @return
	 */
	public List<ChSaAdjustFormulaModel> queryByAdjustTypeAndUniqueKey(String adjustType,String uniqueKey);
}
