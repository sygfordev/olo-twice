package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.ValidateCodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaWechatAccountBiz;
import com.hdfs.olo.olo_web.salary.mapper.ChSaWechatAccountMapper;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatAccountModel;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatTokenModel;

/** 
 * Description: [微信访问账户服务实现]
 * Created on 2021年06月01日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("salary")
@Service("chSaWechatAccountBiz")
public class ChSaWechatAccountBizImpl implements IChSaWechatAccountBiz {
	
	@Autowired
	private IChSaPayslipBiz chSaPayslipBiz;
	
	/**
	 * <p>Discription:[微信访问账户Mapper]</p>
	 */	
	@Autowired
	private ChSaWechatAccountMapper chSaWechatAccountMapper;
	
	/**
	 * <p>Discription:[微信访问账户数据分页查询]</p>
	 * Created on 2021年06月01日
	 * @param page 微信访问账户数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSaWechatAccountModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[微信访问账户数据分页查询]</p>
	 * Created on 2021年06月01日
	 * @param page 微信访问账户数据分页条件
	 * @param chSaWechatAccountModel 微信访问账户数据查询条件
	 * @param queryFields 微信访问账户数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSaWechatAccountModel model,
			String queryFields)throws Exception{
			
		List<ChSaWechatAccountModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSaWechatAccountMapper.queryCount((ChSaWechatAccountModel)page.getModel());
		if(count>0) list = this.chSaWechatAccountMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSaWechatAccountModel>():list);
	}

	/**
	 * <p>Discription:[微信访问账户数据不分页查询]</p>
	 * Created on 2021年06月01日
	 * @param chSaWechatAccountModel 微信访问账户数据查询条件
	 * @param queryFields 微信访问账户数据查询字段
	 * @return List<ChSaWechatAccountModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSaWechatAccountModel> queryList(ChSaWechatAccountModel model,String queryFields)throws Exception{
		List<ChSaWechatAccountModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSaWechatAccountMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[微信访问账户数据不分页查询]</p>
	 * Created on 2021年06月01日
	 * @param chSaWechatAccountModel 微信访问账户数据查询条件
	 * @return List<ChSaWechatAccountModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSaWechatAccountModel> queryList(ChSaWechatAccountModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[微信访问账户数据查询总条数]</p>
	 * Created on 2021年06月01日
	 * @param chSaWechatAccountModel 微信访问账户数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSaWechatAccountModel model)throws Exception{
		return this.chSaWechatAccountMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询微信访问账户数据]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return ChSaWechatAccountModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSaWechatAccountModel queryById(Long id)throws Exception{
		ChSaWechatAccountModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSaWechatAccountMapper.queryById(id,null);
		}
		return model;
	 }

	/**
	 * <p>Discription:[微信访问账户数据新增]</p>
	 * Created on 2021年06月01日
	 * @param chSaWechatAccountModel 微信访问账户数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	 public int save(ChSaWechatAccountModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSaWechatAccountMapper.insert(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[微信访问账户数据编辑]</p>
	 * Created on 2021年06月01日
	 * @param model 微信访问账户数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSaWechatAccountModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSaWechatAccountMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[微信访问账户单条数据删除-逻辑]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaWechatAccountMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[微信访问账户单条数据删除-物理]</p>
	 * Created on 2021年06月01日
	 * @param id 微信访问账户数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSaWechatAccountMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[微信访问账户批量数据删除-物理]</p>
	 * Created on 2021年06月01日
	 * @param ids 微信访问账户数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaWechatAccountMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[微信访问账户批量数据删除-逻辑]</p>
	 * Created on 2021年06月01日
	 * @param ids 微信访问账户数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSaWechatAccountMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 设置查询密码
	 * @param idcard
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Map<Boolean,String> doSetAccPwd(String idcard,String passwd)throws Exception
	{
		Map<Boolean,String> retMap = new HashMap<Boolean,String>();
		//通过身份证号查询是否存在有效的工资单记录
		ChSaPayslipExtendModel model = new ChSaPayslipExtendModel();
		model.setCardNo(idcard);
		model.setStatus(DictionaryUtil.KEY_NORMAL);
		Long count = chSaPayslipBiz.queryCount(model);
		
		//根据身份证号获取查询密码
		ChSaWechatAccountModel wmodel = new ChSaWechatAccountModel();
		wmodel.setCardNo(idcard);
		wmodel.setStatus(DictionaryUtil.KEY_NORMAL);
		List<ChSaWechatAccountModel> alist = this.queryList(wmodel, "CARD_NO,PASSWD");
		
		//开始校验
		if(count<=0) {
			retMap.put(false, "该身份证号在工资单系统中匹配失败!");
			return retMap;
		}
		if(null != alist && alist.size()>0) {
			retMap.put(false, "该用户的查询密码已存在，请勿重复设置!");
			return retMap;
		}
		
		//生成密码并入库
		EncryptHelper encryptHelper = EncryptHelper.getInstance();
		String newString = encryptHelper.encryptString(passwd);
		wmodel.setPasswd(newString);
		wmodel.setType(0);//微信公众号
		int i = this.save(wmodel);
		retMap.put(i>0?true:false, i>0?"访问密码设置成功！":"访问密码设置失败！");
		return retMap;
	}
	
	/**
	 * 让密码失败次数+incVal
	 * @param idcard
	 * @param incVal
	 * @return
	 * @throws Exception
	 */
	@Override
	public int inc4ErrTimes(String idcard,int incVal) throws Exception
	{
		Integer count = 0;
		if(!StringHelper.isNullOrEmpty(idcard) && incVal>=0){
			count = this.chSaWechatAccountMapper.inc4ErrTimesByCardNo(idcard, incVal);
		}
		return count;
	}
	@Override
	public int inc4ErrTimes(Long id,int incVal) throws Exception
	{
		Integer count = 0;
		if(null != id && incVal>=0){
			count = this.chSaWechatAccountMapper.inc4ErrTimesById(id, incVal);
		}
		return count;
	}
	
	/**
	 * 清除所有密码错误次数
	 * @return
	 * @throws Exception
	 */
	public int clean4ErrTimes()throws Exception
	{
		return this.chSaWechatAccountMapper.clean4ErrTimes();
	}
}
