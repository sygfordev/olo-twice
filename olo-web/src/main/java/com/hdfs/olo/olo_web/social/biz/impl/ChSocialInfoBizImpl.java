package com.hdfs.olo.olo_web.social.biz.impl;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.MoneyConvert;
import com.hdfs.olo.olo_web.plugins.common.utils.ReflectionUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuTHeadItem;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.plugins.excel.annotaion.MustExist;
import com.hdfs.olo.olo_web.social.biz.IChSocialInfoBiz;
import com.hdfs.olo.olo_web.social.mapper.ChSocialInfoMapper;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoModel;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.BigExcelWriter;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import cn.hutool.poi.excel.WorkbookUtil;

/** 
 * Description: [社保信息服务实现]
 * Created on 2021年06月07日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@DataSource("social")
@Service("chSocialInfoBiz")
public class ChSocialInfoBizImpl implements IChSocialInfoBiz {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IHuTCommonService huTCommonService;
	/**
	 * <p>Discription:[社保信息Mapper]</p>
	 */	
	@Autowired
	private ChSocialInfoMapper chSocialInfoMapper;
	
	/**
	 * <p>Discription:[社保信息数据分页查询]</p>
	 * Created on 2021年06月07日
	 * @param page 社保信息数据分页条件
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 @SuppressWarnings("rawtypes")
	 public	void queryPage(Page page)throws Exception{
	 	queryPage(page,(ChSocialInfoExtModel)page.getModel(),page.getQueryFields());
	 }
	 
	/**
	 * <p>Discription:[社保信息数据分页查询]</p>
	 * Created on 2021年06月07日
	 * @param page 社保信息数据分页条件
	 * @param chSocialInfoModel 社保信息数据查询条件
	 * @param queryFields 社保信息数据查询字段
	 * @return 分页数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void queryPage(Page page,ChSocialInfoExtModel model,
			String queryFields)throws Exception{
			
		List<ChSocialInfoModel> list = null;
		//判断参数是否为空
		if(Objects.isNull(page)){
			return;
		}
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		//查询总条数和记录
		Long count = this.chSocialInfoMapper.queryCount((ChSocialInfoExtModel)page.getModel());
		if(count>0) list = this.chSocialInfoMapper.queryPage(page,model,fields);
		page.setRecordTotal(count);
		page.setDatas(null == list?new ArrayList<ChSocialInfoModel>():list);
	}

	/**
	 * <p>Discription:[社保信息数据不分页查询]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据查询条件
	 * @param queryFields 社保信息数据查询字段
	 * @return List<ChSocialInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	@Override
	public List<ChSocialInfoModel> queryList(ChSocialInfoExtModel model,String queryFields)throws Exception{
		List<ChSocialInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSocialInfoMapper.queryList(model,fields);
		return list;
	}
	
	/**
	 * <p>Discription:[社保信息数据不分页查询]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据查询条件
	 * @return List<ChSocialInfoModel>列表数据
	 *													       	 
	 * @author:huadf
	 */
	 @Override
	 public	List<ChSocialInfoModel> queryList(ChSocialInfoExtModel model)throws Exception
	 {
	 	return queryList(model,null);
	 }

	/**
	 * <p>Discription:[社保信息数据查询总条数]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据查询条件
	 * @return 查询条数	 
	 * @author:huadf
	 */
	 @Override
	 public Long queryCount(ChSocialInfoExtModel model)throws Exception{
		return this.chSocialInfoMapper.queryCount(model);
	 }

	/**
	 * <p>Discription:[根据id查询社保信息数据]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return ChSocialInfoModel 单条数据	 
	 * @author:huadf
	 */
	 @Override
	 public ChSocialInfoModel queryById(Long id)throws Exception{
		ChSocialInfoModel model = null;
		if(!Objects.isNull(id)){
			model = this.chSocialInfoMapper.queryById(id,null);
		}
		return model;
	 }
	 
	/**
	 * 根据主键编号列表查询
	 * @param ids
	 * @param queryFields
	 * @return
	 * @throws Exception
	 */
	public List<ChSocialInfoModel> queryByIds(List<Long> ids,String queryFields)throws Exception{
		if(null == ids || ids.size()<=0)
			return new ArrayList<>();
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		List<ChSocialInfoModel> list = this.chSocialInfoMapper.queryByIds(ids,fields);
		return list;
	}
	 
	@Override
	public List<ChSocialInfoModel> queryListWithSerial(ChSocialInfoExtModel model, String queryFields)throws Exception
	{
		List<ChSocialInfoModel> list = null;
		
		List<String> fields = null;
		if(!Objects.isNull(queryFields)){
			fields = Arrays.asList(queryFields.split(","));
		}
		list = this.chSocialInfoMapper.queryListWithSerial(model,fields);
		return list;
	}
 
	 @Override
	 public	List<ChSocialInfoModel> queryListWithSerial(ChSocialInfoExtModel model)throws Exception
	 {
	 	return queryListWithSerial(model,null);
	 }

	/**
	 * <p>Discription:[社保信息数据新增]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据
	 * @return String 添加成功的id
	 
	 * @author:huadf
	 */
	 @Override
	 public int save(ChSocialInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model)){
			count = this.chSocialInfoMapper.insert(model);
		}
		return count;
	 }

	 /**
	 * <p>Discription:[社保信息数据批量新增]</p>
	 * Created on 2021年06月07日
	 * @param chSocialInfoModel 社保信息数据
	 * @return String 添加成功的id
	 * @author:huadf
	 */
	public int saveBatch(List<ChSocialInfoModel> list)throws Exception
	{
		Integer count = 0;
		if(null != list && list.size()>0){
			count = this.chSocialInfoMapper.insertBatch(list);
		}
		return count;
	}
	
	/**
	 * <p>Discription:[社保信息数据编辑]</p>
	 * Created on 2021年06月07日
	 * @param model 社保信息数据
	 * @return 成功条数 	
	 *								    
	 * @author:huadf
	 */
	 @Override
	 public int edit(ChSocialInfoModel model)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(model) && !Objects.isNull(model.getId())){
			count = this.chSocialInfoMapper.update(model);
		}
		return count;
	 }

	/**
	 * <p>Discription:[社保信息单条数据删除-逻辑]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSocialInfoMapper.delById(id);
		}	
		return count;
	}
	
	/**
	 * <p>Discription:[社保信息单条数据删除-物理]</p>
	 * Created on 2021年06月07日
	 * @param id 社保信息数据id
	 * @return 成功条数 	
	 *
	 * @author:huadf
	 */
	@Override
	public int delById4Logic(Long id)throws Exception{
		Integer count = 0;
		if(!Objects.isNull(id)){
			count = this.chSocialInfoMapper.delById4Logic(id);
		}	
		return count;
	}

	/**
	 * <p>Discription:[社保信息批量数据删除-物理]</p>
	 * Created on 2021年06月07日
	 * @param ids 社保信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSocialInfoMapper.delBatchByIds(ids);
		}
		return count;
	}

	/**
	 * <p>Discription:[社保信息批量数据删除-逻辑]</p>
	 * Created on 2021年06月07日
	 * @param ids 社保信息数据id的集合
	 * @return 成功条数 
	 *
	 * @author:huadf
	 */
	@Override
	public int delByIds4Logic(List<Long> ids)throws Exception{
		Integer count = 0;
		if(null != ids && ids.size()>0){
			count = this.chSocialInfoMapper.delBatchByIds4Logic(ids);
		}
		return count;
	}
	
	//********************以下为扩展方法***********************
	
	/**
	 * 根据字段名获取所有状态正常的去重列表
	 * @param column
	 * @return
	 */
	@Override
	public List<String> loadSelectFields(String column)
	{
		if(StringHelper.isNullOrEmpty(column)) return null;
		List<String> result = chSocialInfoMapper.querySelectFields(column);
		return result;
	}
	
	/**
	  * 根据身份证号和工资年月查询已存在的数据
	  * @param list
	  * @return
	  * @throws Exception
	  */
	 @Override
	 public List<String> queryExisted(List<String> list,String targetMonth)throws Exception
	 {
		 if(null == list || list.size()<=0 || 
				 StringHelper.isNullOrEmpty(targetMonth)) return null;
		 return this.chSocialInfoMapper.queryExisted(list,targetMonth);
	 }
	
	private static final Map<String,String> headerAlias = new HashMap<String,String>();
	static {
		headerAlias.put("姓名", "name");
		headerAlias.put("身份证号", "cardNo");
		headerAlias.put("银行卡号", "bankCardNo");
		headerAlias.put("手机号码", "mobileNo");
		headerAlias.put("人员类别", "wkModalityCn");
		headerAlias.put("人员编号", "wagesId");
		headerAlias.put("职务工种", "positCn");
		headerAlias.put("职称", "titleCn");
		headerAlias.put("医院支部", "hosBranchCn");
		headerAlias.put("一级科室", "hosDepart1levelCn");
		headerAlias.put("二级科室", "hosDepart2levelCn");
	}
	
	private static final Integer BATCH_SIZE = 500;//分片处理数量
	/**
	 * 文件导入 
	 */
	@SuppressWarnings({"unchecked" })
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public Map<Boolean,Object> doImport(InputStream ins,String targetMonth,String batchNo)throws Exception
	{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		//创建阅读器
		ExcelReader reader = ExcelUtil.getReader(ins);
		//导入文件检测
		logger.info(">>>>>>>开始文件导入前的检测<<<<<<<<<<");
		
		List<Sheet> sheets = reader.getSheets();
//		if(null != sheets && sheets.size()>1) {
//			retMap.put(false, "暂不支持多sheet导入!");
//			return retMap;
//		}
		
		boolean isExistMustConfig = false;
		List<String> mustExists = new ArrayList<String>();//导入时必须存在项
		
		Field[] allFields = ChSocialInfoModel.class.getDeclaredFields();
		for(Field item:allFields)
		{
			//获取实体配置中必须存在项
			isExistMustConfig = item.isAnnotationPresent(MustExist.class);
			MustExist exist = isExistMustConfig?item.getAnnotation(MustExist.class):null;
			if(isExistMustConfig && exist.value())
				mustExists.add(item.getName());
		}
		
		Integer lastRowNum = sheets.get(0).getLastRowNum();
		if(null == lastRowNum || lastRowNum<4) {
			retMap.put(false, "数据文件为空!");
			return retMap;
		}
		//读取文件中所有数据
		List<List<Object>> allDatas = new ArrayList<List<Object>>();
		int startRowNum = 4;
		int endRowNum = 0;
		int roundTimes = (lastRowNum-4)/500+((lastRowNum-4)%500>0?1:0);
		for(int i=1;i<=roundTimes;i++)
		{
			endRowNum = startRowNum+499>=lastRowNum?lastRowNum:startRowNum+499;
			allDatas.addAll(reader.read(startRowNum, endRowNum));
			startRowNum = endRowNum+1;
		}
		
		List<Object> item = null;
		Map<Boolean,Object> rowObjVal = null;
		boolean isReadSucc = true;//是否全部读取成功
		List<String> readFailedRowInfo = new LinkedList<String>();//读取失败的行数
		StringBuffer errMsg = new StringBuffer();
		//最终封装的实体对象列表
		List<Object> modelList = new ArrayList<Object>();
		for (int i=0;i<allDatas.size();i++) {
			item = allDatas.get(i);
			rowObjVal = packModel(item,mustExists);
			if(rowObjVal.containsKey(true))
			{
				modelList.add(rowObjVal.get(true));
				continue;
			}
			isReadSucc = false;
			Map<Integer,String> errMap = (Map<Integer,String>)rowObjVal.get(false);
			for(Integer colNum:errMap.keySet())
			{
				if(errMsg.length()>0)errMsg.append(",");
				errMsg.append("[第"+(i+1)+"行"+colNum+"列]");
			}
			readFailedRowInfo.add(errMsg.toString());
			break;
		}
		if(modelList.size()<=0 || !isReadSucc) {
			retMap.put(false, readFailedRowInfo.toString()+"数据匹配失败");
			return retMap;
		}
		
		ChSocialInfoModel model = null;
		Object obj = null;
		
		int savedSize = 0;//入库数量
		int existSize = 0;//已存在数量
		int failSize = 0;//失败的数量
		List<String> tmp4Icards = new ArrayList<String>();
		List<ChSocialInfoModel> socials = new ArrayList<ChSocialInfoModel>();
		for(int i=0;i<modelList.size();i++)
		{
			obj = modelList.get(i);
			model = (ChSocialInfoModel)obj;
			if(tmp4Icards.contains(model.getCardNo()))
			{
				logger.info("[工资条]数据文件中，身份证号["+model.getCardNo()+"]存在重复数据,默认取第一条！");
				continue;
			}
			
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			model.setSocialYmonth(targetMonth);
			model.setBtimpNo(batchNo);
			
			//判断是否存在补缴数据
			model.setIsExistSupple(0);
			if(
				(null != model.getSPenPersRecapDiffe() && model.getSPenPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSMediPersRecapDiffe() && model.getSMediPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSUnempPersRecapDiffe() && model.getSUnempPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSInjuryPersRecapDiffe() && model.getSInjuryPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSBirthPersRecapDiffe() && model.getSBirthPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSAnnuityPersRecapDiffe() && model.getSAnnuityPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSOvpPersRecapDiffe() && model.getSOvpPersRecapDiffe().doubleValue()>0) ||
				(null != model.getSSpMediPersRecapDiffe() && model.getSSpMediPersRecapDiffe().doubleValue()>0)
			)
				model.setIsExistSupple(1);
			socials.add(model);
			tmp4Icards.add(model.getCardNo());
			//若满足单批次处理数量  或  数据最后一批次（包含未满足单批次数量）
			if(BATCH_SIZE != socials.size() && modelList.size() != (i+1))
				continue;
			
			if(tmp4Icards.size()<=0) continue;
			
			//去除已存在数据
			List<String> nos = this.queryExisted(tmp4Icards,targetMonth);
			tmp4Icards.clear();
			if(nos.size()>0)
			{
				for(Iterator<ChSocialInfoModel> it = socials.iterator();it.hasNext();)
				{
					if(!nos.contains(it.next().getCardNo())) continue;
					it.remove();
				}
				existSize+=nos.size();
			}
			//执行入库操作
			int size = socials.size()>0?this.saveBatch(socials):0;
			savedSize += size;
			socials.clear();
		}
		
		//根据导入类型进行批量导入
		Map<String,Integer> insMap = new HashMap<String,Integer>();
		insMap.put("succ", savedSize);
		insMap.put("exist", existSize);
		insMap.put("fail", failSize);
		insMap.put("excep",allDatas.size()-(savedSize+existSize+failSize));
		insMap.put("allSize", savedSize+existSize+failSize);
		
		retMap.put(true, insMap);
		return retMap;
	}
	
	static final String[] FIELDS4IMP = {"cardNo","name","compName","hosBranchCn","hosDepart1levelCn","hosDepart2levelCn","workAreaCn","wkModalityCn","titleCn","positCn","changeTime",
			"sPenBase","sPenCompRatio","sPenCompAmount","sPenPersRatio","sPenPersAmount","sPenCompRecapAmount","sPenPersRecapAmount","sPenCompRecapDiffe","sPenPersRecapDiffe","sPenCompOverpaid","sPenPersComple",
			"sMediBase","sMediCompRatio","sMediCompAmount","sMediPersRatio","sMediPersAmount","sMediCompRecapAmount","sMediPersRecapAmount","sMediCompRecapDiffe","sMediPersRecapDiffe","sMediCompOverpaid","sMediPersComple",
			"sUnempBase","sUnempCompRatio","sUnempCompAmount","sUnempPersRatio","sUnempPersAmount","sUnempCompRecapAmount","sUnempPersRecapAmount","sUnempCompRecapDiffe","sUnempPersRecapDiffe","sUnempCompOverpaid","sUnempPersComple",
			"sInjuryBase","sInjuryCompRatio","sInjuryCompAmount","sInjuryPersRatio","sInjuryPersAmount","sInjuryCompRecapAmount","sInjuryPersRecapAmount","sInjuryCompRecapDiffe","sInjuryPersRecapDiffe","sInjuryCompOverpaid","sInjuryPersComple",
			"sBirthBase","sBirthCompRatio","sBirthCompAmount","sBirthPersRatio","sBirthPersAmount","sBirthCompRecapAmount","sBirthPersRecapAmount","sBirthCompRecapDiffe","sBirthPersRecapDiffe","sBirthCompOverpaid","sBirthPersComple",
			"sAnnuityBase","sAnnuityCompRatio","sAnnuityCompAmount","sAnnuityPersRatio","sAnnuityPersAmount","sAnnuityCompRecapAmount","sAnnuityPersRecapAmount","sAnnuityCompRecapDiffe","sAnnuityPersRecapDiffe","sAnnuityCompOverpaid","sAnnuityPersComple",
			"sOvpBase","sOvpCompRatio","sOvpCompAmount","sOvpPersRatio","sOvpPersAmount","sOvpCompRecapAmount","sOvpPersRecapAmount","sOvpCompRecapDiffe","sOvpPersRecapDiffe","sOvpCompOverpaid","sOvpPersComple",
			"sSpMediBase","sSpMediCompRatio","sSpMediCompAmount","sSpMediPersRatio","sSpMediPersAmount","sSpMediCompRecapAmount","sSpMediPersRecapAmount","sSpMediCompRecapDiffe","sSpMediPersRecapDiffe","sSpMediCompOverpaid","sSpMediPersComple",
			"remark4month","remark4year","persRecapDiffe4addup","persCompleDiffe4addup"
			};
	@SuppressWarnings({"static-access" })
	private Map<Boolean,Object> packModel(List<Object> row,List<String> mustExists) throws Exception{
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		Map<Integer,String> errMap = new HashMap<Integer,String>();
		if(null == row || row.size()<=0) throw new Exception("数据为空!");
		if(null == mustExists) mustExists = new ArrayList<String>();
		Class<?> clazz = ChSocialInfoModel.class;
		Object obj = clazz.forName(clazz.getName()).newInstance();
		Field field = null;
		for(int i=0;i<FIELDS4IMP.length;i++)
		{
			field = ReflectionUtil.getDeclaredField(obj.getClass(),FIELDS4IMP[i]);
			if(null == field) continue;
			if(mustExists.contains(field.getName()) && 
					StringHelper.isNullOrEmpty(String.valueOf(row.get(i)))) {
				errMap.put(i+1, "必输项为空");
				break;
			}
			ReflectionUtil.setFieldValByType(obj, field, i+1<=row.size()?row.get(i):"");
		}
		if(errMap.size()>0)
			retMap.put(false, errMap);
		else 
			retMap.put(true, obj);
		return retMap;
	}
	
	private static final int BT_SUPPLE = 100;
	/**
	 * 查询补缴单
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> query4Supple(ChSocialInfoExtModel model)throws Exception
	{
		List<Map<String,Object>> datas = chSocialInfoMapper.query4Supple(model);
		if(null == datas || datas.size()<=0) return new ArrayList<>();
		//主键编号集合（采用分片查询）
		List<Long> ids = new ArrayList<Long>();
		Map<Long,ChSocialInfoModel> tsups = new HashMap<Long,ChSocialInfoModel>();
		
		Map<String,Object> itemMap = null;
		for(int i=0;i<datas.size();i++) {
			itemMap = datas.get(i);
			if("total".equals(itemMap.get("id"))) break;
			ids.add(Long.valueOf(""+itemMap.get("id")));
			if(0 != (i%BT_SUPPLE) && (i+2)!=datas.size())
				continue;
			List<ChSocialInfoModel> tlist = this.queryByIds(ids, "ID,NAME,CARD_NO,S_PEN_BASE,SOCIAL_YMONTH");
			for(ChSocialInfoModel val:tlist)
			{
				tsups.put(val.getId(), val);
			}
			ids.clear();
		}
		
		ChSocialInfoModel sample = null;
		for(int i=0;i<datas.size();i++) {
			itemMap = datas.get(i);
			if("total".equals(itemMap.get("id"))) break;
			//若是汇总（最后一行）则直接跳出
			if((i+1)==datas.size()) break;
			sample = tsups.get(Long.valueOf(""+itemMap.get("id")));
			if(null == sample)continue;
			itemMap.put("name", sample.getName());
			itemMap.put("idcard", sample.getCardNo());
			itemMap.put("social_ymonth", sample.getSocialYmonth());
			itemMap.put("msize", 1);
			itemMap.put("base", sample.getSPenBase());
		}
		//最后一行统计设置
		datas.get(datas.size()-1).put("name", "小计");
		datas.get(datas.size()-1).put("msize", datas.size()-1);
		datas.get(datas.size()-1).put("base", "-");
		datas.get(datas.size()-1).put("social_ymonth", "-");
		datas.get(datas.size()-1).put("idcard", "-");
		return datas;
	}
	
	/**
	 * 补缴单导出
	 * @param response
	 * @param title
	 * @param datas
	 * @param head
	 * @throws Exception
	 */
	@Override
	public void export4supple(HttpServletResponse response,String title,List<Map<String,Object>> datas,HuToolHead head)throws Exception{
		SXSSFWorkbook wb = WorkbookUtil.createSXSSFBook(SXSSFWorkbook.DEFAULT_WINDOW_SIZE);
		Sheet sheet = WorkbookUtil.getOrCreateSheet(wb, "社保补缴单");
		ExcelWriter bigWriter = new BigExcelWriter(sheet);
        //bigWriter.writeHeadRow(new LinkedList());
        
        List<HuTHeadItem> heads = head.getHeads();
        int lastCol = huTCommonService.addHeadAlias(bigWriter,heads,-1);
		
        //根据需要合并添加第一列表头
        int titleNeedRowNum = 1;
        bigWriter.merge(0, 0, 0, lastCol, head.gethTitle(), true);
        
        Integer headRank = huTCommonService.writeHead(bigWriter,heads,titleNeedRowNum,null);
        if(null == headRank || headRank<=0) throw new Exception("表头信息异常!");
        //暂时先固定表头层级深度
        //if(headRank>3) headRank = 3;
        // 只导出配置好的列名
        bigWriter.setCurrentRow((headRank)+titleNeedRowNum);
        bigWriter.setOnlyAlias(true);
        bigWriter.write(datas);
        
        //写入其他信息
        Integer suppsIndex = headRank+titleNeedRowNum+datas.size();
        bigWriter.writeCellValue(0,suppsIndex,"合计（大写）");
        BigDecimal total = new BigDecimal(String.valueOf(datas.get(datas.size()-1).get("total")));
        bigWriter.merge(suppsIndex,suppsIndex, 1, lastCol, MoneyConvert.convertCn(total), false);
        //bigWriter.writeCellValue(1,suppsIndex+1,MoneyConvert.convertCn(total));
        CellStyle cellStyle=wb.createCellStyle(); // 创建单元格样式
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        //bigWriter.getOrCreateCellStyle(x, y)
        bigWriter.setStyle(cellStyle, 1, suppsIndex);
        //CellUtil.mergingCells(sheet, suppsIndex+1, suppsIndex+1, 1, lastCol, cellStyle);
        
        
        bigWriter.writeCellValue(0,suppsIndex+2,"审核人：");
        bigWriter.merge(suppsIndex+2,suppsIndex+2, 1, 2, "", false);
        bigWriter.writeCellValue(3,suppsIndex+2,"制表人：");
        bigWriter.merge(suppsIndex+2, suppsIndex+2, 4, 5, "", false);
        bigWriter.writeCellValue(15,suppsIndex+3,"日期：");
        bigWriter.writeCellValue(16,suppsIndex+3,DateTimeHelper.format(new Date(), "yyyyMMdd"));
        
        StyleSet styleSet = bigWriter.getStyleSet();
        styleSet.setWrapText();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //response.setHeader("filename", URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        response.setHeader("Content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(title+".xlsx", "UTF-8"));
        ServletOutputStream out = response.getOutputStream();
        bigWriter.flush(out, true);
        bigWriter.close();
        IoUtil.close(out);
	}
	
	/**
	 * 通过批次号撤销社保导入数据
	 * @param btimpNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public int delBatchByBtImpNo(String btimpNo)throws Exception{
		if(StringHelper.isNullOrEmpty(btimpNo))
			return 0;
		return chSocialInfoMapper.delBatchByBtImpNo4Logic(btimpNo);
	}
}
