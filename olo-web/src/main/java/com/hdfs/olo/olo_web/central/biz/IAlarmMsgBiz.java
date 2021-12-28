package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.AlarmMsgModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [警报信息服务]
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IAlarmMsgBiz {
	
	/**
	 * <p>Discription:[警报信息数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[警报信息数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报信息数据分页条件
	 * @param model 警报信息数据查询条件
	 * @param queryFields 警报信息数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, AlarmMsgModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[警报信息数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param model 警报信息数据查询条件
	 * @param queryFields 警报信息数据查询字段集合
	 * @return List<ChPmAlarmMsgModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<AlarmMsgModel> queryList(AlarmMsgModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[警报信息数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param model 警报信息数据查询条件
	 * @return List<ChPmAlarmMsgModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<AlarmMsgModel> queryList(AlarmMsgModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报信息数据查询总条数]</p>
	 * Created on 2021年09月04日
	 * @param model 警报信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(AlarmMsgModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询警报信息数据]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return ChPmAlarmMsgModel 单条数据	 
	 * @author:huadf
	 */
	public AlarmMsgModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[警报信息数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(AlarmMsgModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报信息数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmMsgModel 警报信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<AlarmMsgModel> list)throws Exception;
	
	/**
	 * <p>Discription:[警报信息数据编辑]</p>
	 * Created on 2021年09月04日
	 * @param model 警报信息数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(AlarmMsgModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报信息单条数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[警报信息单条数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param id 警报信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[警报信息批量数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[警报信息批量数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
