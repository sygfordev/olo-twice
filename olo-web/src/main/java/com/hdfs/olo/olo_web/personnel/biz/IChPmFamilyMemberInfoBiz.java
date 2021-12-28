package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmFamilyMemberInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [家庭成员服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmFamilyMemberInfoBiz {
	
	/**
	 * <p>Discription:[家庭成员数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 家庭成员数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[家庭成员数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 家庭成员数据分页条件
	 * @param model 家庭成员数据查询条件
	 * @param queryFields 家庭成员数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmFamilyMemberInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[家庭成员数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 家庭成员数据查询条件
	 * @param queryFields 家庭成员数据查询字段集合
	 * @return List<ChPmFamilyMemberInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmFamilyMemberInfoModel> queryList(ChPmFamilyMemberInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[家庭成员数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 家庭成员数据查询条件
	 * @return List<ChPmFamilyMemberInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmFamilyMemberInfoModel> queryList(ChPmFamilyMemberInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 家庭成员数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmFamilyMemberInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询家庭成员数据]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return ChPmFamilyMemberInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmFamilyMemberInfoModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[家庭成员数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmFamilyMemberInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmFamilyMemberInfoModel 家庭成员数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmFamilyMemberInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 家庭成员数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmFamilyMemberInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 家庭成员数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 家庭成员数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[家庭成员批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 家庭成员数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
