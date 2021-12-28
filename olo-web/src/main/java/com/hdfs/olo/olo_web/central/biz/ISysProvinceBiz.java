package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.central.model.SysProvinceModel;

/**
 * Description: [省份表服务]
 * Created on 2021年03月31日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface ISysProvinceBiz {
	
	/**
	 * <p>Discription:[省份表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 省份表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[省份表数据分页查询]</p>
	 * Created on 2021年03月31日
	 * @param page 省份表数据分页条件
	 * @param model 省份表数据查询条件
	 * @param queryFields 省份表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, SysProvinceModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[省份表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 省份表数据查询条件
	 * @param queryFields 省份表数据查询字段集合
	 * @return List<SysProvinceModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysProvinceModel> queryList(SysProvinceModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[省份表数据不分页查询]</p>
	 * Created on 2021年03月31日
	 * @param model 省份表数据查询条件
	 * @return List<SysProvinceModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<SysProvinceModel> queryList(SysProvinceModel model)throws Exception;
	
	/**
	 * <p>Discription:[省份表数据查询总条数]</p>
	 * Created on 2021年03月31日
	 * @param model 省份表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(SysProvinceModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询省份表数据]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return SysProvinceModel 单条数据	 
	 * @author:huadf
	 */
	public SysProvinceModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[省份表数据新增]</p>
	 * Created on 2021年03月31日
	 * @param sysProvinceModel 省份表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(SysProvinceModel model)throws Exception;
	
	/**
	 * <p>Discription:[省份表数据编辑]</p>
	 * Created on 2021年03月31日
	 * @param model 省份表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(SysProvinceModel model)throws Exception;
	
	/**
	 * <p>Discription:[省份表单条数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[省份表单条数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param id 省份表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[省份表批量数据删除-物理]</p>
	 * Created on 2021年03月31日
	 * @param ids 省份表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[省份表批量数据删除-逻辑]</p>
	 * Created on 2021年03月31日
	 * @param ids 省份表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 通过省份编号获取其下所有城市列表
	 * @param provNo 省份编号
	 * @return
	 */
	public List<SysCityModel> loadCityListByProvNo(Integer provNo) throws Exception;
}
