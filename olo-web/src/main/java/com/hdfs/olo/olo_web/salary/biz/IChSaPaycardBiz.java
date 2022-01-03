package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;
import java.util.Map;

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
	 * Discription:[薪资-工资单数据不分页查询]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @param model 薪资-工资单数据查询条件
	 * @return List<ChSaPayslipModel>列表数据
	 * 
	 * @author:huadf
	 */
	public List<Map<String, Object>> queryList(ChSaPaycardModel model) throws Exception;

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
