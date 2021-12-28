package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [医院支部表服务]
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmHosBranchBiz {
	
	/**
	 * <p>Discription:[医院支部表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院支部表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[医院支部表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院支部表数据分页条件
	 * @param model 医院支部表数据查询条件
	 * @param queryFields 医院支部表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmHosBranchModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[医院支部表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param model 医院支部表数据查询条件
	 * @param queryFields 医院支部表数据查询字段集合
	 * @return List<ChPmHosBranchModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmHosBranchModel> queryList(ChPmHosBranchModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[医院支部表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param model 医院支部表数据查询条件
	 * @return List<ChPmHosBranchModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmHosBranchModel> queryList(ChPmHosBranchModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表数据查询总条数]</p>
	 * Created on 2021年04月02日
	 * @param model 医院支部表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmHosBranchModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询医院支部表数据]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return ChPmHosBranchModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmHosBranchModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[医院支部表数据新增]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosBranchModel 医院支部表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmHosBranchModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表数据编辑]</p>
	 * Created on 2021年04月02日
	 * @param model 医院支部表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmHosBranchModel model)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表单条数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表单条数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表批量数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院支部表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[医院支部表批量数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院支部表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 通过医院支部编号获取其下所有一级科室列表
	 * @param hbhNo
	 * @return
	 * @throws Exception
	 */
	public List<ChPmHosDepartModel> queryByHbhNo(Integer hbhNo)throws Exception;
}
