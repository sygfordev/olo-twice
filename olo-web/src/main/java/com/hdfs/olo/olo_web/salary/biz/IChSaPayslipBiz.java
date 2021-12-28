package com.hdfs.olo.olo_web.salary.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;

/**
 * Description: [薪资-工资单服务]
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaPayslipBiz {
	
	/**
	 * <p>Discription:[薪资-工资单数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资单数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[薪资-工资单数据分页查询]</p>
	 * Created on 2021年05月14日
	 * @param page 薪资-工资单数据分页条件
	 * @param model 薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaPayslipExtendModel model, String queryFields)throws Exception;
	 /**
	 * <p>Discription:[薪资-工资单数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param model 薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段集合
	 * @return List<ChSaPayslipModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaPayslipModel> queryList(ChSaPayslipModel model, String queryFields)throws Exception;
	 public	List<ChSaPayslipModel> queryListWithSerial(ChSaPayslipExtendModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[薪资-工资单数据不分页查询]</p>
	 * Created on 2021年05月14日
	 * @param model 薪资-工资单数据查询条件
	 * @return List<ChSaPayslipModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaPayslipModel> queryList(ChSaPayslipModel model)throws Exception;
	 public	List<ChSaPayslipModel> queryListWithSerial(ChSaPayslipExtendModel model)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单数据查询总条数]</p>
	 * Created on 2021年05月14日
	 * @param model 薪资-工资单数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaPayslipModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询薪资-工资单数据]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return ChSaPayslipModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaPayslipModel queryById(Long id)throws Exception;
	
	/**
	  * 根据身份证号和工资年月查询已存在的数据
	  * @param list
	  * @return
	  * @throws Exception
	  */
	 public List<String> queryExisted(List<String> list,String targetMonth)throws Exception;

	/**
	 * <p>Discription:[薪资-工资单数据新增]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaPayslipModel model)throws Exception;
	/**
	 * <p>Discription:[薪资-工资单数据批量新增]</p>
	 * Created on 2021年05月14日
	 * @param chSaPayslipModel 薪资-工资单数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChSaPayslipModel> list)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单数据编辑]</p>
	 * Created on 2021年05月14日
	 * @param model 薪资-工资单数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaPayslipModel model)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单单条数据删除-物理]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单单条数据删除-逻辑]</p>
	 * Created on 2021年05月14日
	 * @param id 薪资-工资单数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单批量数据删除-物理]</p>
	 * Created on 2021年05月14日
	 * @param ids 薪资-工资单数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[薪资-工资单批量数据删除-逻辑]</p>
	 * Created on 2021年05月14日
	 * @param ids 薪资-工资单数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 文件导入 
	 */
	public Map<Boolean,Object> doImport(InputStream ins,String targetMonth,String batchNo)throws Exception;
	
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	public List<String> loadSelectFields(String column);
	public List<String> loadSelectFields(String column,String year);
	/**
	 * 根据导入编号批量删除本批次导入数据
	 * @param btimpNo
	 * @return
	 */
	public int delBatchByBtImpNo(String btimpNo);
	/**
	 * 根据身份证号查询工资单
	 * @param cardNo
	 * @return
	 */
	public List<ChSaPayslipModel> query4Wechat(String cardNo) throws Exception;
	public List<ChSaPayslipModel> query4Wechat(String cardNo,String month) throws Exception;
}
