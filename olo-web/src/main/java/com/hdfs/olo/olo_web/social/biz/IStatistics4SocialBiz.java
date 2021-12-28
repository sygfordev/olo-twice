package com.hdfs.olo.olo_web.social.biz;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;

/**
 * 社保统计业务
 * @author HUAWEI
 *
 */
public interface IStatistics4SocialBiz {
	
	/**
	 * 养老保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_100(String start,String end) throws Exception;
	
	/**
	 * 医疗保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_101(String start,String end) throws Exception;
	
	/**
	 * 失业保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_102(String start,String end) throws Exception;
	
	/**
	 * 工伤保险按月汇总
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_103(String start,String end) throws Exception;
	
	/**
	 * 用工形式应缴汇总
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_104(ChSocialInfoExtModel model) throws Exception;
	
	/**
	 * 用工形式应缴汇总导出
	 * @param response
	 * @param title
	 * @param datas
	 * @param head
	 * @throws Exception
	 */
	public void export104(HttpServletResponse response,String title,List<Map<String,Object>> datas,HuToolHead head)throws Exception;
	
	/**
	 * 社保参保人员月统计
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_200(String start,String end)throws Exception;
	
	/**
	 * 年度社保征缴明细表
	 * @param year
	 * @param mo
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_201(String year,List<String> modalitys)throws Exception;
	
	/**
	 * 社保计划表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> load4_202(ChSocialInfoExtModel model)throws Exception;
}
