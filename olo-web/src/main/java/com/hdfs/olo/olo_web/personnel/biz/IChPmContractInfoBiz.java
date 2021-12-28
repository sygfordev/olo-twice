package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.personnel.model.ChPmContractInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [合同信息服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmContractInfoBiz {
	
	/**
	 * <p>Discription:[合同信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 合同信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[合同信息数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 合同信息数据分页条件
	 * @param model 合同信息数据查询条件
	 * @param queryFields 合同信息数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmContractInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[合同信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 合同信息数据查询条件
	 * @param queryFields 合同信息数据查询字段集合
	 * @return List<ChPmContractInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmContractInfoModel> queryList(ChPmContractInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[合同信息数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 合同信息数据查询条件
	 * @return List<ChPmContractInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmContractInfoModel> queryList(ChPmContractInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[合同信息数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 合同信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmContractInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询合同信息数据]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
	 * @return ChPmContractInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmContractInfoModel queryById(Long id)throws Exception;
	
	/**
	 * 根据合同过期时间查询即将过期和已过期的合同
	 * @param isAlreadExist4Msg 是否已经存在预警消息
	 * @param val
	 * @param unit
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> queryByContEndTime(boolean isAlreadExist4Msg,int val,String unit)throws Exception;

	/**
	 * <p>Discription:[合同信息数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmContractInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[合同信息数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmContractInfoModel 合同信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmContractInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[合同信息数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 合同信息数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmContractInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[合同信息单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[合同信息单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 合同信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[合同信息批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 合同信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[合同信息批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 合同信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据执行编号对其下所有合同信息进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synContInfoSortVal(Long wkId)throws Exception;
	public void synContInfoSortVal();
}
