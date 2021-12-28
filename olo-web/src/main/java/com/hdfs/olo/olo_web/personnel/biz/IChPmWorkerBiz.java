package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;

/**
 * Description: [医院职工表服务]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmWorkerBiz {
	
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
	 public	void queryPage(Page page, ChPmWorkerModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[医院职工表数据不分页查询]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据查询条件
	 * @param queryFields 医院职工表数据查询字段集合
	 * @return List<ChPmWorkModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmWorkerModel> queryList(ChPmWorkerModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[医院职工表数据不分页查询]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据查询条件
	 * @return List<ChPmWorkModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmWorkerModel> queryList(ChPmWorkerModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表数据查询总条数]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmWorkerModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询医院职工表数据]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return ChPmWorkModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmWorkerModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[医院职工表数据新增]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据
	 * @return Long 添加成功的id
	 * @author:huadf
	 */
	public Long save(ChPmWorkerModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表数据新增]</p>
	 * Created on 2021年03月25日
	 * @param chPmWorkModel 医院职工表数据
	 * @return Long 添加成功的id
	 * @author:huadf
	 */
	public Integer saveBatch(List<ChPmWorkerModel> list)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表数据编辑]</p>
	 * Created on 2021年03月25日
	 * @param model 医院职工表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmWorkerModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表单条数据删除-物理]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表单条数据删除-逻辑]</p>
	 * Created on 2021年03月25日
	 * @param id 医院职工表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表批量数据删除-物理]</p>
	 * Created on 2021年03月25日
	 * @param ids 医院职工表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[医院职工表批量数据删除-逻辑]</p>
	 * Created on 2021年03月25日
	 * @param ids 医院职工表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	public List<String> queryExisted(List<String> idcardNos);
	public List<Map<String,Object>> queryByCardNoBatch(List<String> idcardNos);
	
	public List<Map<String,Object>> queryExist4OthByCardNos(List<String> list);
}
