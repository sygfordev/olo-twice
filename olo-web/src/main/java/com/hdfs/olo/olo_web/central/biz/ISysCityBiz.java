package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.central.model.SysCityModel;

/**
 * Description: [城市表服务]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface ISysCityBiz {
	
	/**
	 * <p>Discription:[城市表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 城市表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[城市表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 城市表数据分页条件
	 * @param model 城市表数据查询条件
	 * @param queryFields 城市表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, SysCityModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[城市表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 城市表数据查询条件
	 * @param queryFields 城市表数据查询字段集合
	 * @return List<SysCityModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysCityModel> queryList(SysCityModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[城市表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 城市表数据查询条件
	 * @return List<SysCityModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysCityModel> queryList(SysCityModel model)throws Exception;
	
	/**
	 * <p>Discription:[城市表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param model 城市表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SysCityModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询城市表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return SysCityModel 单条数据	 
	 * @author:huadf
	 */
	public SysCityModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[城市表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysCityModel 城市表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SysCityModel model)throws Exception;
	
	/**
	 * <p>Discription:[城市表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 城市表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SysCityModel model)throws Exception;
	
	/**
	 * <p>Discription:[城市表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[城市表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 城市表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[城市表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 城市表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[城市表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 城市表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 *  通过城市编码获取其下所有区县列表
	 * @param cityNo
	 * @return
	 * @throws Exception
	 */
	public List<SysAreaModel> loadAreaListByCityNo(Integer cityNo)throws Exception;
	
	/**
	 *  通过省份编码和城市编码获取其下所有区县列表
	 * @param provNo
	 * @param cityNo
	 * @return
	 * @throws Exception
	 */
	public List<SysAreaModel> loadAreaListByProvCityNo(Integer provNo,Integer cityNo)throws Exception;
}
