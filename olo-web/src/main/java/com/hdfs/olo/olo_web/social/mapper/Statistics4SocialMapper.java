package com.hdfs.olo.olo_web.social.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;

@MyBatisRepository
public interface Statistics4SocialMapper {

	/**
	 * 养老保险按月汇总
	 * @return
	 */
	List<Map<String,Object>> st4pen(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	
	/**
	 * 医疗保险按月汇总
	 * @return
	 */
	List<Map<String,Object>> st4medi(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	/**
	 * 失业保险按月汇总
	 * @return
	 */
	List<Map<String,Object>> st4unemp(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	
	/**
	 * 工伤保险按月汇总
	 * @return
	 */
	List<Map<String,Object>> st4injury(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	
	/**
	 * 社保参保人员月统计
	 * @param startMonth
	 * @param endMonth
	 * @return
	 */
	List<Map<String,Object>> st4Insured(@Param("startMonth")String startMonth,@Param("endMonth")String endMonth);
	/**
	 * 年度社保征缴明细表
	 * @param year
	 * @param modalitys
	 * @return
	 */
	List<Map<String,Object>> st4zhengjiaoYear(@Param("year")String year,@Param("modalitys")List<String> modalitys,@Param("modalityStr")String modalityStr);
	
	/**
	 * 社保计划表
	 * @param model
	 * @return
	 */
	List<Map<String,Object>> st4socialplan(@Param("model")ChSocialInfoExtModel model);
	
	/**
	 * 用工形式应缴汇总
	 * @param model
	 * @return
	 */
	List<Map<String,Object>> st4Modality(@Param("model")ChSocialInfoExtModel model);
	
}
