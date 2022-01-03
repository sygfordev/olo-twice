package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardModel;

/**
 * Description: [薪资-工资单服务] Created on 2021年05月14日
 * 
 * @author <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
public interface IChSaPaycardBiz {

	/**
	 * <p>
	 * Discription:[薪资-工资单数据分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param page 薪资-工资单数据分页条件
	 * @return 分页数据
	 * 
	 * @author:huadf
	 */
	public void queryPage(Page<?> page) throws Exception;

	/**
	 * <p>
	 * Discription:[薪资-工资单数据分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param page        薪资-工资单数据分页条件
	 * @param model       薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段集合
	 * @return 分页数据
	 * 
	 * @author:huadf
	 */
	public void queryPage(Page<?> page, ChSaPaycardExtendModel model, String queryFields) throws Exception;

	/**
	 * <p>
	 * Discription:[薪资-工资单数据不分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param model       薪资-工资单数据查询条件
	 * @param queryFields 薪资-工资单数据查询字段集合
	 * @return List<ChSaPayslipModel>列表数据
	 * 
	 * @author:huadf
	 */
	public List<ChSaPaycardModel> queryList(ChSaPaycardModel model, String queryFields) throws Exception;

	public List<ChSaPaycardModel> queryListWithSerial(ChSaPaycardExtendModel model, String queryFields)
			throws Exception;

	/**
	 * <p>
	 * Discription:[薪资-工资单数据不分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param model 薪资-工资单数据查询条件
	 * @return List<ChSaPayslipModel>列表数据
	 * 
	 * @author:huadf
	 */
	public List<ChSaPaycardModel> queryList(ChSaPaycardModel model) throws Exception;

	public List<ChSaPaycardModel> queryListWithSerial(ChSaPaycardExtendModel model) throws Exception;

	/**
	 * <p>
	 * Discription:[薪资-工资单数据查询总条数]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param model 薪资-工资单数据查询条件
	 * @return 查询条数
	 * @author:huadf
	 */
	public Long queryCount(ChSaPaycardModel model) throws Exception;

	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * 
	 * @param column
	 * @return
	 */
	public List<String> loadSelectFields(String column);

	/**
	 * 获取最近的一个奖励项目年月
	 * 
	 * @return
	 */
	String queryLastMonth();

}
