package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.personnel.model.ChPmPositHeadModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [职务信息头表服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmPositHeadBiz {
	
	/**
	 * <p>Discription:[职务信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 职务信息头表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[职务信息头表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 职务信息头表数据分页条件
	 * @param model 职务信息头表数据查询条件
	 * @param queryFields 职务信息头表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmPositHeadModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[职务信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 职务信息头表数据查询条件
	 * @param queryFields 职务信息头表数据查询字段集合
	 * @return List<ChPmPositHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmPositHeadModel> queryList(ChPmPositHeadModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[职务信息头表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 职务信息头表数据查询条件
	 * @return List<ChPmPositHeadModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmPositHeadModel> queryList(ChPmPositHeadModel model)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 职务信息头表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmPositHeadModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询职务信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return ChPmPositHeadModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmPositHeadModel queryById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[根据wkId查询职务信息头表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return ChPmPositHeadModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmPositHeadModel queryByWkId(Long wkId)throws Exception;

	/**
	 * <p>Discription:[职务信息头表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmPositHeadModel model)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmPositHeadModel 职务信息头表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmPositHeadModel> list)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 职务信息头表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmPositHeadModel model)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 职务信息头表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 职务信息头表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[职务信息头表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 职务信息头表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 * 根据身份证号列表查询已存在的信息
	 * @param cardNos
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryExistByCardNos(List<String> cardNos);
}
