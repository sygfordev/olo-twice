package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmEduInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [学历信息表服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmEduInfoBiz {
	
	/**
	 * <p>Discription:[学历信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 学历信息表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[学历信息表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 学历信息表数据分页条件
	 * @param model 学历信息表数据查询条件
	 * @param queryFields 学历信息表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmEduInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[学历信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 学历信息表数据查询条件
	 * @param queryFields 学历信息表数据查询字段集合
	 * @return List<ChPmEduInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmEduInfoModel> queryList(ChPmEduInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[学历信息表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 学历信息表数据查询条件
	 * @return List<ChPmEduInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmEduInfoModel> queryList(ChPmEduInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 学历信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmEduInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询学历信息表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
	 * @return ChPmEduInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmEduInfoModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[学历信息表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmEduInfoModel model)throws Exception;
	/**
	 * <p>Discription:[学历信息表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmEduInfoModel 学历信息表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmEduInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 学历信息表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmEduInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 学历信息表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 学历信息表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[学历信息表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 学历信息表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据执行编号对其下所有教育经历进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synEduInfoSortVal(Long wkId)throws Exception;
	public void synEduInfoSortVal();
}
