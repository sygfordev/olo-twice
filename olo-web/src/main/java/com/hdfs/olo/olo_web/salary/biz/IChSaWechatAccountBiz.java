package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;
import java.util.Map;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatAccountModel;

/**
 * Description: [微信访问账户服务]
 * Created on 2021年06月01日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaWechatAccountBiz {
	
	/**
	 * <p>Discription:[微信访问账户数据分页查询]</p>
	 * Created on 2021年06月01日
	 * @param page 微信访问账户数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[微信访问账户数据分页查询]</p>
	 * Created on 2021年06月01日
	 * @param page 微信访问账户数据分页条件
	 * @param model 微信访问账户数据查询条件
	 * @param queryFields 微信访问账户数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaWechatAccountModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[微信访问账户数据不分页查询]</p>
	 * Created on 2021年06月01日
	 * @param model 微信访问账户数据查询条件
	 * @param queryFields 微信访问账户数据查询字段集合
	 * @return List<ChSaWechatAccountModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaWechatAccountModel> queryList(ChSaWechatAccountModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[微信访问账户数据不分页查询]</p>
	 * Created on 2021年06月01日
	 * @param model 微信访问账户数据查询条件
	 * @return List<ChSaWechatAccountModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaWechatAccountModel> queryList(ChSaWechatAccountModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户数据查询总条数]</p>
	 * Created on 2021年06月01日
	 * @param model 微信访问账户数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaWechatAccountModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询微信访问账户数据]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return ChSaWechatAccountModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaWechatAccountModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[微信访问账户数据新增]</p>
	 * Created on 2021年06月01日
	 * @param chSaWechatAccountModel 微信访问账户数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaWechatAccountModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户数据编辑]</p>
	 * Created on 2021年06月01日
	 * @param model 微信访问账户数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaWechatAccountModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户单条数据删除-物理]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户单条数据删除-逻辑]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户批量数据删除-物理]</p>
	 * Created on 2021年06月01日
	 * @param ids 微信访问账户数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[微信访问账户批量数据删除-逻辑]</p>
	 * Created on 2021年06月01日
	 * @param ids 微信访问账户数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 设置查询密码
	 * @param idcard
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,String> doSetAccPwd(String idcard,String passwd)throws Exception;
	/**
	 * 让密码失败次数+incVal
	 * @param idcard
	 * @param incVal
	 * @return
	 * @throws Exception
	 */
	public int inc4ErrTimes(String idcard,int incVal) throws Exception;
	public int inc4ErrTimes(Long id,int incVal) throws Exception;
	
	/**
	 * 清除所有密码错误次数
	 * @return
	 * @throws Exception
	 */
	public int clean4ErrTimes()throws Exception;
}
