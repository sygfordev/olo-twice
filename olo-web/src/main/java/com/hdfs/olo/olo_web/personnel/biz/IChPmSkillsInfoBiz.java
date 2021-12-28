package com.hdfs.olo.olo_web.personnel.biz;

import java.util.List;

import com.hdfs.olo.olo_web.personnel.model.ChPmSkillsInfoModel;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/**
 * Description: [技能等级（技术工种）表服务]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChPmSkillsInfoBiz {
	
	/**
	 * <p>Discription:[技能等级（技术工种）表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级（技术工种）表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[技能等级（技术工种）表数据分页查询]</p>
	 * Created on 2021年03月29日
	 * @param page 技能等级（技术工种）表数据分页条件
	 * @param model 技能等级（技术工种）表数据查询条件
	 * @param queryFields 技能等级（技术工种）表数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChPmSkillsInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[技能等级（技术工种）表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级（技术工种）表数据查询条件
	 * @param queryFields 技能等级（技术工种）表数据查询字段集合
	 * @return List<ChPmSkillsInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmSkillsInfoModel> queryList(ChPmSkillsInfoModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[技能等级（技术工种）表数据不分页查询]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级（技术工种）表数据查询条件
	 * @return List<ChPmSkillsInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChPmSkillsInfoModel> queryList(ChPmSkillsInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表数据查询总条数]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级（技术工种）表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChPmSkillsInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询技能等级（技术工种）表数据]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return ChPmSkillsInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChPmSkillsInfoModel queryById(Long id)throws Exception;

	/**
	 * <p>Discription:[技能等级（技术工种）表数据新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChPmSkillsInfoModel model)throws Exception;
	/**
	 * <p>Discription:[技能等级（技术工种）表数据批量新增]</p>
	 * Created on 2021年03月29日
	 * @param chPmSkillsInfoModel 技能等级（技术工种）表数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChPmSkillsInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表数据编辑]</p>
	 * Created on 2021年03月29日
	 * @param model 技能等级（技术工种）表数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChPmSkillsInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表单条数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表单条数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param id 技能等级（技术工种）表数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表批量数据删除-物理]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级（技术工种）表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[技能等级（技术工种）表批量数据删除-逻辑]</p>
	 * Created on 2021年03月29日
	 * @param ids 技能等级（技术工种）表数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	/**
	 * 根据执行编号对其下所有技能级别进行排序编号
	 * @param wkId
	 * @throws Exception
	 */
	public void synSkillsInfoSortVal(Long wkId)throws Exception;
	public void synSkillsInfoSortVal();
}
