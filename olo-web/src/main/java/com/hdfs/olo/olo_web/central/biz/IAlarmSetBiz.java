package com.hdfs.olo.olo_web.central.biz;

import java.util.List;

import com.hdfs.olo.olo_web.central.model.AlarmSetModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [警报设置服务]
 * Created on 2021年09月04日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IAlarmSetBiz {
	
	/**
	 * <p>Discription:[警报设置数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报设置数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[警报设置数据分页查询]</p>
	 * Created on 2021年09月04日
	 * @param page 警报设置数据分页条件
	 * @param model 警报设置数据查询条件
	 * @param queryFields 警报设置数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, AlarmSetModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[警报设置数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param model 警报设置数据查询条件
	 * @param queryFields 警报设置数据查询字段集合
	 * @return List<ChPmAlarmSetModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<AlarmSetModel> queryList(AlarmSetModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[警报设置数据不分页查询]</p>
	 * Created on 2021年09月04日
	 * @param model 警报设置数据查询条件
	 * @return List<ChPmAlarmSetModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<AlarmSetModel> queryList(AlarmSetModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报设置数据查询总条数]</p>
	 * Created on 2021年09月04日
	 * @param model 警报设置数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(AlarmSetModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询警报设置数据]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return ChPmAlarmSetModel 单条数据	 
	 * @author:huadf
	 */
	public AlarmSetModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[警报设置数据新增]</p>
	 * Created on 2021年09月04日
	 * @param chPmAlarmSetModel 警报设置数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(AlarmSetModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报设置数据编辑]</p>
	 * Created on 2021年09月04日
	 * @param model 警报设置数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(AlarmSetModel model)throws Exception;
	
	/**
	 * <p>Discription:[警报设置单条数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[警报设置单条数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param id 警报设置数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[警报设置批量数据删除-物理]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报设置数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[警报设置批量数据删除-逻辑]</p>
	 * Created on 2021年09月04日
	 * @param ids 警报设置数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
}
