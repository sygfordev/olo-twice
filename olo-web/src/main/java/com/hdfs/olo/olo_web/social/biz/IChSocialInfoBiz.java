package com.hdfs.olo.olo_web.social.biz;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoModel;

/**
 * Description: [社保信息服务]
 * Created on 2021年06月07日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
public interface IChSocialInfoBiz {
	
	/**
	 * <p>Discription:[社保信息数据分页查询]</p>
	 * Created on 2021年06月07日
	 * @param page 社保信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page)throws Exception;
	 
	/**
	 * <p>Discription:[社保信息数据分页查询]</p>
	 * Created on 2021年06月07日
	 * @param page 社保信息数据分页条件
	 * @param model 社保信息数据查询条件
	 * @param queryFields 社保信息数据查询字段集合
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 public	void queryPage(Page page, ChSocialInfoExtModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[社保信息数据不分页查询]</p>
	 * Created on 2021年06月07日
	 * @param model 社保信息数据查询条件
	 * @param queryFields 社保信息数据查询字段集合
	 * @return List<ChSocialInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSocialInfoModel> queryList(ChSocialInfoExtModel model, String queryFields)throws Exception;
	 
	 /**
	 * <p>Discription:[社保信息数据不分页查询]</p>
	 * Created on 2021年06月07日
	 * @param model 社保信息数据查询条件
	 * @return List<ChSocialInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 public	List<ChSocialInfoModel> queryList(ChSocialInfoExtModel model)throws Exception;
	
	/**
	 * <p>Discription:[社保信息数据查询总条数]</p>
	 * Created on 2021年06月07日
	 * @param model 社保信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	public Long queryCount(ChSocialInfoExtModel model)throws Exception;
	
	/**
	 * <p>Discription:[根据id查询社保信息数据]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return ChSocialInfoModel 单条数据	 
	 * @author:huadf
	 */
	public ChSocialInfoModel queryById(Long id)throws Exception;
	/**
	 * 根据主键编号列表查询
	 * @param ids
	 * @param queryFields
	 * @return
	 * @throws Exception
	 */
	public List<ChSocialInfoModel> queryByIds(List<Long> ids,String queryFields)throws Exception;
	
	
	public List<ChSocialInfoModel> queryListWithSerial(ChSocialInfoExtModel model, String queryFields)throws Exception;
	public List<ChSocialInfoModel> queryListWithSerial(ChSocialInfoExtModel model)throws Exception; 

	/**
	 * <p>Discription:[社保信息数据新增]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int save(ChSocialInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[社保信息数据批量新增]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChSocialInfoModel> list)throws Exception;
	
	/**
	 * <p>Discription:[社保信息数据编辑]</p>
	 * Created on 2021年06月07日
	 * @param model 社保信息数据
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int edit(ChSocialInfoModel model)throws Exception;
	
	/**
	 * <p>Discription:[社保信息单条数据删除-物理]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById(Long id)throws Exception;
	
	/**
	 * <p>Discription:[社保信息单条数据删除-逻辑]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return 成功条数 	
	 * @author:huadf
	 */
	public int delById4Logic(Long id)throws Exception;
	
	/**
	 * <p>Discription:[社保信息批量数据删除-物理]</p>
	 * Created on 2021年06月07日
	 * @param ids 社保信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds(List<Long> ids)throws Exception;
	
	/**
	 * <p>Discription:[社保信息批量数据删除-逻辑]</p>
	 * Created on 2021年06月07日
	 * @param ids 社保信息数据id的集合
	 * @return 成功条数 
	 * @author:huadf
	 */
	public int delByIds4Logic(List<Long> ids)throws Exception;
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	public List<String> loadSelectFields(String column);
	
	/**
	  * 根据身份证号和工资年月查询已存在的数据
	  * @param list
	  * @return
	  * @throws Exception
	  */
	public List<String> queryExisted(List<String> list,String targetMonth)throws Exception;
	/**
	 * 导入社保数据 
	 * @param ins
	 * @param targetMonth
	 * @param batchNo
	 * @return
	 * @throws Exception
	 */
	public Map<Boolean,Object> doImport(InputStream ins,String targetMonth,String batchNo)throws Exception;
	/**
	 * 查询补缴单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> query4Supple(ChSocialInfoExtModel model)throws Exception;
	
	/**
	 * 补缴单导出
	 * @param response
	 * @param title
	 * @param datas
	 * @param head
	 * @throws Exception
	 */
	public void export4supple(HttpServletResponse response,String title,List<Map<String,Object>> datas,HuToolHead head)throws Exception;
	
	/**
	 * 通过批次号撤销社保导入数据
	 * @param btimpNo
	 * @return
	 * @throws Exception
	 */
	public int delBatchByBtImpNo(String btimpNo)throws Exception;
}
