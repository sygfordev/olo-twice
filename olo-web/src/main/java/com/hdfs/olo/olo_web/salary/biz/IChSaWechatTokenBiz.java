package com.hdfs.olo.olo_web.salary.biz;

import java.util.List;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatTokenModel;

/**
 * Description: [微信访问Token服务]
 * Created on 2021年05月26日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSaWechatTokenBiz {
	
	/**
	 * <p>Discription:[微信访问Token数据分页查询]</p>
	 * Created on 2021年05月26日
	 * @param page 微信访问Token数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[微信访问Token数据分页查询]</p>
	 * Created on 2021年05月26日
	 * @param page 微信访问Token数据分页条件
	 * @param model 微信访问Token数据查询条件
	 * @param queryFields 微信访问Token数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSaWechatTokenModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[微信访问Token数据不分页查询]</p>
	 * Created on 2021年05月26日
	 * @param model 微信访问Token数据查询条件
	 * @param queryFields 微信访问Token数据查询字段集合
	 * @return List<ChSaWechatTokenModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaWechatTokenModel> queryList(ChSaWechatTokenModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[微信访问Token数据不分页查询]</p>
	 * Created on 2021年05月26日
	 * @param model 微信访问Token数据查询条件
	 * @return List<ChSaWechatTokenModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSaWechatTokenModel> queryList(ChSaWechatTokenModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问Token数据查询总条数]</p>
	 * Created on 2021年05月26日
	 * @param model 微信访问Token数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSaWechatTokenModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询微信访问Token数据]</p>
	 * Created on 2021年05月26日
	 * @param id 微信访问Token数据id
	 * @return ChSaWechatTokenModel 单条数据	 
	 * @author:huadf
	 */
	public ChSaWechatTokenModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[微信访问Token数据新增]</p>
	 * Created on 2021年05月26日
	 * @param chSaWechatTokenModel 微信访问Token数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSaWechatTokenModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问Token数据编辑]</p>
	 * Created on 2021年05月26日
	 * @param model 微信访问Token数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSaWechatTokenModel model)throws Exception;
	
	/**
	 * <p>Discription:[微信访问Token单条数据删除-物理]</p>
	 * Created on 2021年05月26日
	 * @param id 微信访问Token数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	/**
	 * <p>Discription:[微信访问Token批量数据删除-物理]</p>
	 * Created on 2021年05月26日
	 * @param ids 微信访问Token数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[微信访问Token根据身份证号删除]</p>
	 * Created on 2021年05月26日
	 * @param cardNo 身份证号
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIdcard(String cardNo)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 * 清除minutes时间到现在的数据，单位unit
	 * @param num 数据
	 * @param unit 单位：H小时  M分钟  S秒
	 * @return
	 * @throws Exception
	 */
	public int delByTime(int num,String unit)throws Exception;
}
