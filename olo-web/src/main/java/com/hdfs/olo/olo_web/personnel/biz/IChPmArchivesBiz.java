package com.hdfs.olo.olo_web.personnel.biz;

import com.hdfs.olo.olo_web.personnel.model.ChPmArchivesModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [人事档案服务]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmArchivesBiz {
	
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[医院职工表数据分页查询]</p>
	 * Created on 2021年03月25日
	 * @param page 医院职工表数据分页条件
	 * @param model 医院职工表数据查询条件
	 * @param queryFields 医院职工表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmArchivesModel model, String queryFields)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表数据查询总条数]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmArchivesModel model)throws Exception;
	//********************以下为扩展方法***********************
}
