package com.hdfs.olo.olo_web.central.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.central.biz.IUserInfoBiz;
import com.hdfs.olo.olo_web.central.mapper.UserInfoMapper;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [用户信息表服务实现]
 * Created on 2020年03月17日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年 xxxx
 */
@DataSource(value="smart")
@Service("userInfoBiz")
public class UserInfoBizImpl implements IUserInfoBiz {
	/**
	 * <p>Discription:[用户信息表Mapper]</p>
	 */	
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * <p>Discription:[用户信息表数据分页查询]</p>
	 * Created on 2020年11月04日
	 * @param page 用户信息表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@SuppressWarnings("rawtypes")
	public	void queryPageUserInfo(Page page)throws Exception{
	 	queryPageUserInfo(page,(UserInfoModel)page.getModel(),page.getQueryFields());
	}
	 
	/**
	 * <p>Discription:[用户信息表数据分页查询]</p>
	 * Created on 2020年11月04日
	 * @param page 用户信息表数据分页条件
	 * @param userInfoModel 用户信息表数据查询条件
	 * @param queryFields 用户信息表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPageUserInfo(Page page,UserInfoModel userInfoModel,
			String queryFields)throws Exception{
			
		List<UserInfoModel> listUserInfo = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.userInfoMapper.queryCountUserInfo((UserInfoModel)page.getModel());
		listUserInfo = this.userInfoMapper.queryPageUserInfo(page,userInfoModel,fields);
		page.setRecordTotal(count);
		page.setDatas(listUserInfo);
	}

	/**
	 * <p>Discription:[用户信息表数据不分页查询]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @param queryFields 用户信息表数据查询字段
	 * @return List<UserInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	public List<UserInfoModel> queryListUserInfo(UserInfoModel userInfoModel,String queryFields)throws Exception{
		List<UserInfoModel> listUserInfo = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		listUserInfo = this.userInfoMapper.queryListUserInfo(userInfoModel,fields);
		return listUserInfo;
	}
	
	/**
	 * <p>Discription:[用户信息表数据不分页查询]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @return List<UserInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<UserInfoModel> queryListUserInfo(UserInfoModel userInfoModel)throws Exception
	 {
	 	return queryListUserInfo(userInfoModel,null);
	 }

	/**
	 * <p>Discription:[用户信息表数据查询总条数]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCountUserInfo(UserInfoModel userInfoModel)throws Exception{
		return this.userInfoMapper.queryCountUserInfo(userInfoModel);
	}

	/**
	 * <p>Discription:[根据id查询用户信息表数据]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return UserInfoModel 单条数据	 
	 * @author:huadf
	 */
	public UserInfoModel queryUserInfoById(Long id)throws Exception{
		UserInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.userInfoMapper.queryUserInfoById(id,null);
		}
		return model;
	}

	/**
	 * <p>Discription:[用户信息表数据新增]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	public int saveUserInfo(UserInfoModel userInfoModel)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(userInfoModel)){
			count = this.userInfoMapper.insertUserInfo(userInfoModel);
		}
		return count;
	}

	/**
	 * <p>Discription:[用户信息表数据编辑]</p>
	 * Created on 2020年11月04日
	 * @param userInfoModel 用户信息表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	public int editUserInfo(UserInfoModel userInfoModel)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(userInfoModel) && !Objects.isNull(userInfoModel.getUserId())){
			count = this.userInfoMapper.updateUserInfo(userInfoModel);
		}
		return count;
	}

	/**
	 * <p>Discription:[用户信息表单条数据删除-逻辑]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	public int delUserInfoById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.userInfoMapper.delUserInfoById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[用户信息表单条数据删除-物理]</p>
	 * Created on 2020年11月04日
	 * @param id 用户信息表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	public int delUserInfoById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.userInfoMapper.delUserInfoById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[用户信息表批量数据删除-物理]</p>
	 * Created on 2020年11月04日
	 * @param ids 用户信息表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	public int delUserInfoByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.userInfoMapper.delUserInfoBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[用户信息表批量数据删除-逻辑]</p>
	 * Created on 2020年11月04日
	 * @param ids 用户信息表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	public int delUserInfoByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.userInfoMapper.delUserInfoBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	/**
	 * <p>Discription:[根据机构编号查询用户总条数]</p>
	 * Created on 2020年03月17日
	 * @param model 用户信息表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	@Override
	public Long queryCountByBranch(Long branchId)throws Exception
	{
		List<Long> branchIds = new ArrayList<Long>();
		branchIds.add(branchId);
		return this.queryCountByBranch(branchIds);
	}
	@Override
	public Long queryCountByBranch(List<Long> branchIds)throws Exception
	{
		Long count = this.userInfoMapper.queryCountByBranch(branchIds);
		return count;
	}
	
	/**
	 * <p>Discription:[根据account查询用户信息表数据]</p>
	 * Created on 2020年03月17日
	 * @param account 用户信息表数据account
	 * @return UserInfoModel 单条数据	 
	 * @author:huadf
	 */
	public UserInfoModel queryByAccount(String account)throws Exception
	{
		if(Objects.isNull(account)) return null;
		return this.userInfoMapper.queryByAccount(account,null);
	}

	/**
	 * <p>Discription:[用户信息表数据编辑]</p>
	 * Created on 2020年03月17日
	 * @param model 用户信息表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit4Pwd(Long userId,String pwd)throws Exception
	{
		if(Objects.isNull(userId) || Objects.isNull(pwd)){
			return 0;
		}
		return this.userInfoMapper.update4Pwd(userId,pwd);
	}
}
