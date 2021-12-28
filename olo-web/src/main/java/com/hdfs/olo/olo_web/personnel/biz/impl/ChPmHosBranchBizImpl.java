package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IChPmHosBranchBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosDepartBiz;
import com.hdfs.olo.olo_web.personnel.mapper.ChPmHosBranchMapper;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.page.Page;

/** 
 * Description: [医院支部表服务实现]
 * Created on 2021年04月02日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource(value="personnel")
@Service("chPmHosBranchBiz")
public class ChPmHosBranchBizImpl implements IChPmHosBranchBiz {
	
	/**
	 * <p>Discription:[医院支部表Mapper]</p>
	 */	
	@Autowired
	private ChPmHosBranchMapper chPmHosBranchMapper;
	@Autowired
	private IChPmHosDepartBiz chPmHosDepartBiz;
	
	/**
	 * <p>Discription:[医院支部表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院支部表数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChPmHosBranchModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[医院支部表数据分页查询]</p>
	 * Created on 2021年04月02日
	 * @param page 医院支部表数据分页条件
	 * @param chPmHosBranchModel 医院支部表数据查询条件
	 * @param queryFields 医院支部表数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChPmHosBranchModel model,
			String queryFields)throws Exception{
			
		List<ChPmHosBranchModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chPmHosBranchMapper.queryCount((ChPmHosBranchModel)page.getModel());
		list = this.chPmHosBranchMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(list);
	}

	/**
	 * <p>Discription:[医院支部表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosBranchModel 医院支部表数据查询条件
	 * @param queryFields 医院支部表数据查询字段
	 * @return List<ChPmHosBranchModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChPmHosBranchModel> queryList(ChPmHosBranchModel model,String queryFields)throws Exception{
		List<ChPmHosBranchModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chPmHosBranchMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[医院支部表数据不分页查询]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosBranchModel 医院支部表数据查询条件
	 * @return List<ChPmHosBranchModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChPmHosBranchModel> queryList(ChPmHosBranchModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[医院支部表数据查询总条数]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosBranchModel 医院支部表数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChPmHosBranchModel model)throws Exception{
		return this.chPmHosBranchMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询医院支部表数据]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return ChPmHosBranchModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChPmHosBranchModel queryById(Long id)throws Exception{
		ChPmHosBranchModel model = null;
		if(!Objects.isNull(id)){
			model = this.chPmHosBranchMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[医院支部表数据新增]</p>
	 * Created on 2021年04月02日
	 * @param chPmHosBranchModel 医院支部表数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChPmHosBranchModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chPmHosBranchMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院支部表数据编辑]</p>
	 * Created on 2021年04月02日
	 * @param model 医院支部表数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChPmHosBranchModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getHbhNo())){
			count = this.chPmHosBranchMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[医院支部表单条数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmHosBranchMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[医院支部表单条数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param id 医院支部表数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chPmHosBranchMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[医院支部表批量数据删除-物理]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院支部表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmHosBranchMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[医院支部表批量数据删除-逻辑]</p>
	 * Created on 2021年04月02日
	 * @param ids 医院支部表数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chPmHosBranchMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 通过医院支部编号获取其下所有一级科室列表
	 * @param hbhNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ChPmHosDepartModel> queryByHbhNo(Integer hbhNo)throws Exception
	{
		if(null == hbhNo) return new ArrayList<ChPmHosDepartModel>();
		ChPmHosDepartModel model = new ChPmHosDepartModel();
		model.setHbhNo(hbhNo);
		List<ChPmHosDepartModel> list = chPmHosDepartBiz.queryList(model,"hdp_no,hdp_name");
		return list;
	}
}
