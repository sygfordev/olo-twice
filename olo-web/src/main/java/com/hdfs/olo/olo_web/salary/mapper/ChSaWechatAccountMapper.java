package com.hdfs.olo.olo_web.salary.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatAccountModel;

/** 
 * <p>Description: [微信访问账户Mapper]</p>
 * Created on 2021年06月01日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@MyBatisRepository
public interface ChSaWechatAccountMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<ChSaWechatAccountModel> queryPage(@Param("page")Page page, @Param("entity")ChSaWechatAccountModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询固定参数
	*/
	List<ChSaWechatAccountModel> queryList(@Param("entity")ChSaWechatAccountModel entity, @Param("queryFields")List<?> queryFields);

	/**
	* 查询总数量
	*/
	Long queryCount(@Param("entity")ChSaWechatAccountModel entity);
	
	/**
	* 查询单个实体
	*/
	ChSaWechatAccountModel queryById(@Param("id")Long id,  @Param("queryFields")List<?> queryFields);
	
	/**
	* 新增(单条)
	*/
	int insert(ChSaWechatAccountModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertBatch(List<ChSaWechatAccountModel> list);
	
	/**
	* 修改
	*/
	int update(ChSaWechatAccountModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delById(@Param("id")Long id);
	
	/**
	*删除(单条-逻辑)
	*/
	int delById4Logic(@Param("id")Long id);
	
	/**
	*删除(批量-物理)
	*/
	int delBatchByIds(List<Long> ids);
	
	/**
	*删除(批量-逻辑)
	*/
	int delBatchByIds4Logic(List<Long> ids);

	//*****************************以下为扩展方法****************************
	/**
	 * 根据身份证号让该账号的失败次数+incVal
	 * @param idcard
	 * @param incVal
	 * @return
	 */
	int inc4ErrTimesByCardNo(@Param("idcard")String idcard,@Param("incVal")int incVal);
	int inc4ErrTimesById(@Param("id")Long id,@Param("incVal")int incVal);
	
	/**
	 * 清除密码错误次数
	 * @return
	 */
	int clean4ErrTimes();
}
