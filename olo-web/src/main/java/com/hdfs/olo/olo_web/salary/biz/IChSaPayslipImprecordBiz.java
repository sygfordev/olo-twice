package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipImprecordModel;

/**
 * Description: [薪资-工资条导入记录服务]
 * Created on 2021年05月16日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaPayslipImprecordBiz {
	
	/**
	 * <p>Discription:[薪资-工资条导入记录数据分页查询]</p>
	 * Created on 2021年05月16日
	 * @param page 薪资-工资条导入记录数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[薪资-工资条导入记录数据分页查询]</p>
	 * Created on 2021年05月16日
	 * @param page 薪资-工资条导入记录数据分页条件
	 * @param model 薪资-工资条导入记录数据查询条件
	 * @param queryFields 薪资-工资条导入记录数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaPayslipImprecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[薪资-工资条导入记录数据不分页查询]</p>
	 * Created on 2021年05月16日
	 * @param model 薪资-工资条导入记录数据查询条件
	 * @param queryFields 薪资-工资条导入记录数据查询字段集合
	 * @return List<ChSaPayslipImprecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaPayslipImprecordModel> queryList(ChSaPayslipImprecordModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[薪资-工资条导入记录数据不分页查询]</p>
	 * Created on 2021年05月16日
	 * @param model 薪资-工资条导入记录数据查询条件
	 * @return List<ChSaPayslipImprecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaPayslipImprecordModel> queryList(ChSaPayslipImprecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录数据查询总条数]</p>
	 * Created on 2021年05月16日
	 * @param model 薪资-工资条导入记录数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaPayslipImprecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询薪资-工资条导入记录数据]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return ChSaPayslipImprecordModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaPayslipImprecordModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[薪资-工资条导入记录数据新增]</p>
	 * Created on 2021年05月16日
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaPayslipImprecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录数据编辑]</p>
	 * Created on 2021年05月16日
	 * @param model 薪资-工资条导入记录数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaPayslipImprecordModel model)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录单条数据删除-物理]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录单条数据删除-逻辑]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录批量数据删除-物理]</p>
	 * Created on 2021年05月16日
	 * @param ids 薪资-工资条导入记录数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录批量数据删除-逻辑]</p>
	 * Created on 2021年05月16日
	 * @param ids 薪资-工资条导入记录数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
