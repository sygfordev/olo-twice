package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;

public interface IPayslipStatisticsBiz {

	/**
	* 查询固定参数
	*/
	public void queryPage(Page page,List<String> months);
	
	/**
	 * 通过条件检索查询的工资月份列表
	 * @param params
	 * @return
	 */
	public List<String> queryMonthList(Page page);
	
	/**
	 * 通过条件查询带有序号的工资统计
	 * @param model
	 * @param months
	 * @return
	 */
	public List<Map<String,Object>> queryListWithSerial(ChSaPayslipExtendModel model,List<String> months);
	
	//==============================================统计区域===================================================
	
	/**
	 * 用工类型月工资汇总表
	 * @param rewards	绩效项集合
	 * @param curMonth  统计年月
	 * @return
	 */
	public List<Map<String,Object>> st4modality(String curMonth);
	
	/**
	 * 根据人员类别和工资汇总表项目进行检索汇总
	 * @param ymonth  统计年月
	 * @param modalitys 用工类型集合
	 * @param saSumProjects 工资汇总表项目集合
	 * @return
	 */
	public List<Map<String,Object>> st4common(String ymonth,List<String> modalitys,List<String> saSumProjects);
	
	/**
	 * 根据薪资年月统计大类
	 * @param ymonth  统计年月
	 * @return
	 */
	public List<Map<String,Object>> st4class(String ymonth);
	
	/**
	 * 根据年份、用工形式和薪酬奖励项列表统计
	 * @param year  统计年份
	 * @return
	 */
	public List<Map<String,Object>> st4year(String year);
}
