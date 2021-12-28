package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmOtherInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [其他信息服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmOtherInfoBiz {
	
	/**
	 * <p>Discription:[其他信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 其他信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[其他信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 其他信息数据分页条件
	 * @param model 其他信息数据查询条件
	 * @param queryFields 其他信息数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmOtherInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[其他信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 其他信息数据查询条件
	 * @param queryFields 其他信息数据查询字段集合
	 * @return List<ChPmOtherInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmOtherInfoModel> queryList(ChPmOtherInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[其他信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 其他信息数据查询条件
	 * @return List<ChPmOtherInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmOtherInfoModel> queryList(ChPmOtherInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[其他信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 其他信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmOtherInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询其他信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return ChPmOtherInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmOtherInfoModel queryById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[根据职工编号查询其他信息数据]</p>
	 * Created on 2021年03月29日
	 * @param wkId 职工编号
	 * @return ChPmOtherInfoModel 单条数据	 
	 * @author:huadf
	 */
	 public ChPmOtherInfoModel queryByWkId(Long wkId)throws Exception;

	/**
	 * <p>Discription:[其他信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public Long save(ChPmOtherInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[其他信息数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmOtherInfoModel 其他信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 public Integer saveBatch(List<ChPmOtherInfoModel> list)throws Exception;
	 
	/**
	 * <p>Discription:[其他信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 其他信息数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmOtherInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[其他信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[其他信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 其他信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[其他信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 其他信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[其他信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 其他信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
