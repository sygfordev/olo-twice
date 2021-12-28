package com.hdfs.olo.olo_web.central.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.MyBatisRepository;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * <p>Description: [用户信息表Mapper]</p>
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@MyBatisRepository
public interface UserInfoMapper{
	
	/**
	* 分页查询固定参数
	*/
	List<UserInfoModel> queryPageUserInfo(@Param("page")Page page, @Param("entity")UserInfoModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询固定参数
	*/
	List<UserInfoModel> queryListUserInfo(@Param("entity")UserInfoModel entity, @Param("queryFields")List queryFields);

	/**
	* 查询总数量
	*/
	Long queryCountUserInfo(@Param("entity")UserInfoModel entity);
	
	/**
	* 查询单个实体
	*/
	UserInfoModel queryUserInfoById(@Param("id")Long userId,  @Param("queryFields")List queryFields);
	
	/**
	* 新增(单条)
	*/
	int insertUserInfo(UserInfoModel entity);
	
	/**
	* 新增(批量)
	*/
	int insertUserInfoBatch(List<UserInfoModel> list);
	
	/**
	* 修改
	*/
	int updateUserInfo(UserInfoModel entity);
	
	/**
	*删除(单条-物理)
	*/
	int delUserInfoById(@Param("id")Long userId);
	
	/**
	*删除(单条-逻辑)
	*/
	int delUserInfoById4Logic(@Param("id")Long userId);
	
	/**
	*删除(批量-物理)
	*/
	int delUserInfoBatchByIds(List<Long> userIds);
	
	/**
	*删除(批量-逻辑)
	*/
	int delUserInfoBatchByIds4Logic(List<Long> userIds);
	
	//*****************************以下为扩展方法****************************
	/**
	* 根据机构编号查询总数量
	*/
	Long queryCountByBranch(List<Long> branchIds);
	
	/**
	* 查询单个实体
	*/
	UserInfoModel queryByAccount(@Param("account")String account,@Param("queryFields")List<String> queryFields);
	
	/**
	* 修改密码
	*/
	int update4Pwd(Long userId,String pwd);
	
}
