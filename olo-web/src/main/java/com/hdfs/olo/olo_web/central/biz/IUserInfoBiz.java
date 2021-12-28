package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [用户信息表服务]
 * Created on 2020年03月17日
 * @author  huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
public interface IUserInfoBiz {
	
	/**
	 * <p>Discription:[用户信息表数据分页查询]</p>
	 * Created on 2020年11月04日
	 * @param page 用户信息表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPageUserInfo(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[用户信息表数据分页查询]</p>
	 * Created on 2020年11月04日
	 * @param page 用户信息表数据分页条件
	 * @param userInfoModel 用户信息表数据查询条件
	 * @param queryFields 用户信息表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPageUserInfo(Page page, UserInfoModel userInfoModel, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[用户信息表数据不分页查询]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @param queryFields 用户信息表数据查询字段集合
	 * @return List<UserInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<UserInfoModel> queryListUserInfo(UserInfoModel userInfoModel, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[用户信息表数据不分页查询]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @return List<UserInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<UserInfoModel> queryListUserInfo(UserInfoModel userInfoModel)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表数据查询总条数]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCountUserInfo(UserInfoModel userInfoModel)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询用户信息表数据]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return UserInfoModel 单条数据	 
	 * @author:huadf
	 */
	public UserInfoModel queryUserInfoById(Long id)throws Exception;

	/**
	 * <p>Discription:[用户信息表数据新增]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveUserInfo(UserInfoModel userInfoModel)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表数据编辑]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int editUserInfo(UserInfoModel userInfoModel)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表单条数据删除-物理]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delUserInfoById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表单条数据删除-逻辑]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delUserInfoById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表批量数据删除-物理]</p>
	 * Created on 2020年11月04日
	 * @param ids 用户信息表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delUserInfoByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表批量数据删除-逻辑]</p>
	 * Created on 2020年11月04日
	 * @param ids 用户信息表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delUserInfoByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 * <p>Discription:[根据机构编号查询用户总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 用户信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCountByBranch(Long branchId)throws Exception;
	
	/**
	 * <p>Discription:[根据机构编号列表查询用户总条数]</p>
	 * Created on 2020年03月17日
	 * @param branchIds 用户信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCountByBranch(List<Long> branchIds)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询用户信息表数据]</p>
	 * Created on 2020年03月17日
	 * @param account 用户信息表数据account
	 * @return UserInfoModel 单条数据	 
	 * @author:huadf
	 */
	public UserInfoModel queryByAccount(String account)throws Exception;
	
	/**
	 * <p>Discription:[用户信息表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 用户信息表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit4Pwd(Long userId,String pwd)throws Exception;
}
