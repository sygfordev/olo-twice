package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;
import java.util.Map;

/**
 * 人事档案-统计服务层
 * @author huadf
 *
 */
public interface IStatistics4pBiz {
	/**
	 * <p>Discription:[统计机构编制]</p>
	 * Created on 2021年03月25日
	 * @return 统计数据											       	 
	 * @author:huadf
	 */
	 public	List<Map<String,Object>> s4organization()throws Exception;
}
