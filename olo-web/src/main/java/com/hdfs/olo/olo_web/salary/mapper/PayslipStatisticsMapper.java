package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;

@MyBatisRepository
public interface PayslipStatisticsMapper {

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("model")ChSaPayslipExtendModel model);
	
	/**
	* 分页查询
	*/
	List<Map<String,Object>> queryPage(@Param("page")Page page,@Param("model")ChSaPayslipExtendModel model,
			@Param("months")List<String> months);
	
	/**
	 * 通过条件查询月份列表
	 * @param params
	 * @return
	 */
	List<String> queryMonthList(@Param("model")ChSaPayslipExtendModel model);
	
	/**
	 * 通过条件查询带有序号的工资统计
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryListWithSerial(@Param("model")ChSaPayslipExtendModel model,@Param("months")List<String> months);
	
	//===========================统计区域==========================
	/**
	 * 用工类型月工资汇总表
	 * @param rewards	绩效项集合
	 * @param curMonth  统计年月
	 * @return
	 */
	List<Map<String,Object>> st4modality(@Param("ymonth")String curMonth,@Param("rewards")List<String> rewards);
	
	/**
	 * 根据人员类别和工资汇总表项目进行检索汇总
	 * @param ymonth  统计年月
	 * @param modalitys 用工类型集合
	 * @param saSumProjects 工资汇总表项目集合
	 * @return
	 */
	List<Map<String,Object>> st4common(@Param("ymonth")String ymonth,@Param("modalitys")List<String> modalitys,@Param("saSumProjects")List<String> saSumProjects);
	
	/**
	 * 薪资年月统计大类
	 * @param ymonth  统计年月
	 * @return
	 */
	List<Map<String,Object>> st4class(@Param("ymonth")String ymonth);
	
	/**
	 * 通过年份、用工形式和薪酬奖励项列表统计
	 * @param year
	 * @param modality
	 * @param rewards
	 * @return
	 */
	List<Map<String,Object>> st4year(@Param("year")String year,@Param("modality")String modality,@Param("rewards")List<String> rewards);
}
