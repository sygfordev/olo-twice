package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipImprecordBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaPayslipImprecordMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipImprecordModel;

/** 
 * Description: [薪资-工资条导入记录服务实现]
 * Created on 2021年05月16日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaPayslipImprecordBiz")
public class ChSaPayslipImprecordBizImpl implements IChSaPayslipImprecordBiz {
	
	/**
	 * <p>Discription:[薪资-工资条导入记录Mapper]</p>
	 */	
	@Autowired
	private ChSaPayslipImprecordMapper chSaPayslipImprecordMapper;
	
	/**
	 * <p>Discription:[薪资-工资条导入记录数据分页查询]</p>
	 * Created on 2021年05月16日
	 * @param page 薪资-工资条导入记录数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaPayslipImprecordModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[薪资-工资条导入记录数据分页查询]</p>
	 * Created on 2021年05月16日
	 * @param page 薪资-工资条导入记录数据分页条件
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据查询条件
	 * @param queryFields 薪资-工资条导入记录数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaPayslipImprecordModel model,
			String queryFields)throws Exception{
			
		List<ChSaPayslipImprecordModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaPayslipImprecordMapper.queryCount((ChSaPayslipImprecordModel)page.getModel());
		if(count>0) list = this.chSaPayslipImprecordMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaPayslipImprecordModel>():list);
	}

	/**
	 * <p>Discription:[薪资-工资条导入记录数据不分页查询]</p>
	 * Created on 2021年05月16日
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据查询条件
	 * @param queryFields 薪资-工资条导入记录数据查询字段
	 * @return List<ChSaPayslipImprecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaPayslipImprecordModel> queryList(ChSaPayslipImprecordModel model,String queryFields)throws Exception{
		List<ChSaPayslipImprecordModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaPayslipImprecordMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[薪资-工资条导入记录数据不分页查询]</p>
	 * Created on 2021年05月16日
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据查询条件
	 * @return List<ChSaPayslipImprecordModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaPayslipImprecordModel> queryList(ChSaPayslipImprecordModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[薪资-工资条导入记录数据查询总条数]</p>
	 * Created on 2021年05月16日
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaPayslipImprecordModel model)throws Exception{
		return this.chSaPayslipImprecordMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询薪资-工资条导入记录数据]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return ChSaPayslipImprecordModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaPayslipImprecordModel queryById(Long id)throws Exception{
		ChSaPayslipImprecordModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaPayslipImprecordMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[薪资-工资条导入记录数据新增]</p>
	 * Created on 2021年05月16日
	 * @param chSaPayslipImprecordModel 薪资-工资条导入记录数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChSaPayslipImprecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSaPayslipImprecordMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[薪资-工资条导入记录数据编辑]</p>
	 * Created on 2021年05月16日
	 * @param model 薪资-工资条导入记录数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaPayslipImprecordModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaPayslipImprecordMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[薪资-工资条导入记录单条数据删除-逻辑]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaPayslipImprecordMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[薪资-工资条导入记录单条数据删除-物理]</p>
	 * Created on 2021年05月16日
	 * @param id 薪资-工资条导入记录数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaPayslipImprecordMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[薪资-工资条导入记录批量数据删除-物理]</p>
	 * Created on 2021年05月16日
	 * @param ids 薪资-工资条导入记录数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaPayslipImprecordMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[薪资-工资条导入记录批量数据删除-逻辑]</p>
	 * Created on 2021年05月16日
	 * @param ids 薪资-工资条导入记录数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaPayslipImprecordMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
}
