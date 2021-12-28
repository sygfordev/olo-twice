package com.hdfs.olo.olo_web.personnel.mapper;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
/**
 * 人事档案-统计
 * @author huadf
 *
 */
@MyBatisRepository
public interface Statistics4pMapper {

	/**
	 * 统计机构编制汇总信息
	 * @return
	 */
	List<Map<String,Object>> s4orgTotal();
	
	/**
	 * 统计机构编制-支部统计
	 * @return
	 */
	List<Map<String,Object>> s4orgBranch();
	
	/**
	 * 统计机构编制-未指定支部统计
	 * @return
	 */
	List<Map<String,Object>> s4orgBranchUnKnown();
	
	/**
	 * 统计机构编制-根据支部统计一级科室统计
	 * @param hbhNo 支部编号
	 * @return
	 */
	List<Map<String,Object>> s4orgDepart(Integer hbhNo);
}
