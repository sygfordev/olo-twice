package com.hdfs.olo.olo_web.personnel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdfs.olo.olo_web.central.biz.ISysCityBiz;
import com.hdfs.olo.olo_web.central.biz.ISysProvinceBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.SysAreaModel;
import com.hdfs.olo.olo_web.central.model.SysCityModel;
import com.hdfs.olo.olo_web.central.model.SysProvinceModel;
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkerBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.CalendarUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.DayCompare;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

import cn.hutool.core.util.IdcardUtil;

/** 
 * Description: [医院职工表服务实现]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmWorker/*")
public class ChPmWorkerController extends BaseController {
	
	public final static String Module_Name = "医院职工表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmWorkerBiz chPmWorkerBiz;
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private ISysProvinceBiz sysProvinceBiz;
	@Autowired
	private ISysCityBiz sysCityBiz;
	
	private	final static String PARA_id = "id";
	//private	final static String PARA_wagesId = "wagesId";
	private	final static String PARA_name = "name";
	private	final static String PARA_sex = "sex";
	private	final static String PARA_age = "age";
	private	final static String PARA_cardType = "cardType";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_birthDay = "birthDay";
	private	final static String PARA_nation = "nation";
	private	final static String PARA_nationCn = "nationCn";
	private	final static String PARA_politics = "politics";
	private	final static String PARA_politicsCn = "politicsCn";
	private	final static String PARA_politicsInTime = "politicsInTime";
	private	final static String PARA_firstWorkTime = "firstWorkTime";
	private	final static String PARA_workedYear = "workedYear";
	private	final static String PARA_intoLocalCompTime = "intoLocalCompTime";
	private	final static String PARA_nativePlaceProv = "nativePlaceProv";
	private	final static String PARA_nativePlaceProvCn = "nativePlaceProvCn";
	private	final static String PARA_nativePlaceCity = "nativePlaceCity";
	private	final static String PARA_nativePlaceCityCn = "nativePlaceCityCn";
	private	final static String PARA_nativePlaceArea = "nativePlaceArea";
	private	final static String PARA_nativePlaceAreaCn = "nativePlaceAreaCn";
	private	final static String PARA_residenceType = "residenceType";
	private	final static String PARA_residenceTypeCn = "residenceTypeCn";
	private	final static String PARA_residenceBirthlandProv = "residenceBirthlandProv";
	private	final static String PARA_residenceBirthlandProvCn = "residenceBirthlandProvCn";
	private	final static String PARA_residenceBirthlandCity = "residenceBirthlandCity";
	private	final static String PARA_residenceBirthlandCityCn = "residenceBirthlandCityCn";
	private	final static String PARA_residenceBirthlandArea = "residenceBirthlandArea";
	private	final static String PARA_residenceBirthlandAreaCn = "residenceBirthlandAreaCn";
	private	final static String PARA_residencePoliceStation = "residencePoliceStation";
	private	final static String PARA_homeProv = "homeProv";
	private	final static String PARA_homeProvCn = "homeProvCn";
	private	final static String PARA_homeCity = "homeCity";
	private	final static String PARA_homeCityCn = "homeCityCn";
	private	final static String PARA_homeAddr = "homeAddr";
	private	final static String PARA_telphoneNo = "telphoneNo";
	private	final static String PARA_mailBox = "mailBox";
	private	final static String PARA_salaryAdjustType = "salaryAdjustType";
	private	final static String PARA_salaryAdjustTypeCn = "salaryAdjustTypeCn";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,sex,nation,politics,residence,adjustType";
	
	/**
	 * <p>Discription:[医院职工表请求首页]</p>
	 * Created on 2021年03月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[医院职工表首页数据加载-分页]</p>
	 * Created on 2021年03月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmWorkerModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmWorkerModel> retBody = new Result4Page<ChPmWorkerModel>();
		//查询参数
		Page.Builder<ChPmWorkerModel> builder = new Page.Builder<ChPmWorkerModel>();
		setSearchParameters(request, builder);
		Page<ChPmWorkerModel> page = builder.build();
		try {
			//分页
			chPmWorkerBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			logger.error("查询失败！error:",e);
		}
		return retBody;
	}

	/**
	 * <p>Discription:[医院职工表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月25日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmWorkerModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		//String wagesId = request.getParameter(PARA_wagesId);
		String name = request.getParameter(PARA_name);
		String sexStr = request.getParameter(PARA_sex);
		Integer sex = !StringHelper.isNullOrEmpty(sexStr)?Integer.parseInt(sexStr):null;
		String cardTypeStr = request.getParameter(PARA_cardType);
		Integer cardType = !StringHelper.isNullOrEmpty(cardTypeStr)?Integer.parseInt(cardTypeStr):null;
		String cardNo = request.getParameter(PARA_cardNo);
		String birthDayStr = request.getParameter(PARA_birthDay);
		Date birthDay = !StringHelper.isNullOrEmpty(birthDayStr)?DateTimeHelper.strToDate(birthDayStr,"yyyy-MM"):null;
		Integer age = null;
		DayCompare dc = CalendarUtil.dayComparePrecise(birthDay, new Date());
		if(null != dc) age = dc.getYear();
		
		String nationStr = request.getParameter(PARA_nation);
		Integer nation = !StringHelper.isNullOrEmpty(nationStr)?Integer.parseInt(nationStr):null;
		String nationCn = request.getParameter(PARA_nationCn);
		String politicsStr = request.getParameter(PARA_politics);
		Integer politics = !StringHelper.isNullOrEmpty(politicsStr)?Integer.parseInt(politicsStr):null;
		String politicsCn = request.getParameter(PARA_politicsCn);
		String politicsInTimeStr = request.getParameter(PARA_politicsInTime);
		Date politicsInTime = !StringHelper.isNullOrEmpty(politicsInTimeStr)?DateTimeHelper.createDate(politicsInTimeStr):null;
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.createDate(firstWorkTimeStr):null;
		String workedYearStr = request.getParameter(PARA_workedYear);
		Integer workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Integer.parseInt(workedYearStr):null;
		String intoLocalCompTimeStr = request.getParameter(PARA_intoLocalCompTime);
		Date intoLocalCompTime = !StringHelper.isNullOrEmpty(intoLocalCompTimeStr)?DateTimeHelper.createDate(intoLocalCompTimeStr):null;
		String nativePlaceProvStr = request.getParameter(PARA_nativePlaceProv);
		String nativePlaceCityStr = request.getParameter(PARA_nativePlaceCity);
		String nativePlaceAreaStr = request.getParameter(PARA_nativePlaceArea);
		Integer nativePlaceProv = !StringHelper.isNullOrEmpty(nativePlaceProvStr)?Integer.parseInt(nativePlaceProvStr):null;
		Integer nativePlaceCity = !StringHelper.isNullOrEmpty(nativePlaceCityStr)?Integer.parseInt(nativePlaceCityStr):null;
		Integer nativePlaceArea = !StringHelper.isNullOrEmpty(nativePlaceAreaStr)?Integer.parseInt(nativePlaceAreaStr):null;
		
		String nativePlaceProvCn = request.getParameter(PARA_nativePlaceProvCn);
		String nativePlaceCityCn = request.getParameter(PARA_nativePlaceCityCn);
		String nativePlaceAreaCn = request.getParameter(PARA_nativePlaceAreaCn);
		
		String residenceTypeStr = request.getParameter(PARA_residenceType);
		Integer residenceType = !StringHelper.isNullOrEmpty(residenceTypeStr)?Integer.parseInt(residenceTypeStr):null;
		String residenceTypeCn = request.getParameter(PARA_residenceTypeCn);
		
		String residenceBirthlandProvStr = request.getParameter(PARA_residenceBirthlandProv);
		String residenceBirthlandCityStr = request.getParameter(PARA_residenceBirthlandCity);
		String residenceBirthlandAreaStr = request.getParameter(PARA_residenceBirthlandArea);
		Integer residenceBirthlandProv = !StringHelper.isNullOrEmpty(residenceBirthlandProvStr)?Integer.parseInt(residenceBirthlandProvStr):null;
		Integer residenceBirthlandCity = !StringHelper.isNullOrEmpty(residenceBirthlandCityStr)?Integer.parseInt(residenceBirthlandCityStr):null;
		Integer residenceBirthlandArea = !StringHelper.isNullOrEmpty(residenceBirthlandAreaStr)?Integer.parseInt(residenceBirthlandAreaStr):null;
		String residenceBirthlandProvCn = request.getParameter(PARA_residenceBirthlandProvCn);
		String residenceBirthlandCityCn = request.getParameter(PARA_residenceBirthlandCityCn);
		String residenceBirthlandAreaCn = request.getParameter(PARA_residenceBirthlandAreaCn);
		String residencePoliceStation = request.getParameter(PARA_residencePoliceStation);
		String homeProvStr = request.getParameter(PARA_homeProv);
		String homeCityStr = request.getParameter(PARA_homeCity);
		Integer homeProv = !StringHelper.isNullOrEmpty(homeProvStr)?Integer.parseInt(homeProvStr):null;
		Integer homeCity = !StringHelper.isNullOrEmpty(homeCityStr)?Integer.parseInt(homeCityStr):null;
		String homeProvCn = request.getParameter(PARA_homeProvCn);
		String homeCityCn = request.getParameter(PARA_homeCityCn);
		
		String homeAddr = request.getParameter(PARA_homeAddr);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String salaryAdjustTypeStr = request.getParameter(PARA_salaryAdjustType);
		Integer salaryAdjustType = !StringHelper.isNullOrEmpty(salaryAdjustTypeStr)?Integer.parseInt(salaryAdjustTypeStr):null;
		String salaryAdjustTypeCn = request.getParameter(PARA_salaryAdjustTypeCn);
		String remark = request.getParameter(PARA_remark);
		
		ChPmWorkerModel model = new ChPmWorkerModel();
		model.setId(null!=id?id:null);
		//model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setSex(null!=sex?sex:null);
		model.setAge(null!=age?age:null);
		model.setCardType(null!=cardType?cardType:null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBirthDay(null!=birthDay?birthDay:null);
		model.setNation(null!=nation?nation:null);
		model.setNationCn(nationCn);
		model.setPolitics(null!=politics?politics:null);
		model.setPoliticsCn(politicsCn);
		model.setPoliticsInTime(null!=politicsInTime?politicsInTime:null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setIntoLocalCompTime(null!=intoLocalCompTime?intoLocalCompTime:null);
		model.setNativePlaceProv(null!=nativePlaceProv?nativePlaceProv:null);
		model.setNativePlaceProvCn(nativePlaceProvCn);
		model.setNativePlaceCity(null!=nativePlaceCity?nativePlaceCity:null);
		model.setNativePlaceCityCn(nativePlaceCityCn);
		model.setNativePlaceArea(nativePlaceArea);
		model.setNativePlaceAreaCn(nativePlaceAreaCn);
		model.setResidenceType(null!=residenceType?residenceType:null);
		model.setResidenceTypeCn(residenceTypeCn);
		model.setResidenceBirthlandProv(null!=residenceBirthlandProv?residenceBirthlandProv:null);
		model.setResidenceBirthlandProvCn(residenceBirthlandProvCn);
		model.setResidenceBirthlandCity(null!=residenceBirthlandCity?residenceBirthlandCity:null);
		model.setResidenceBirthlandCityCn(residenceBirthlandCityCn);
		model.setResidenceBirthlandArea(residenceBirthlandArea);
		model.setResidenceBirthlandAreaCn(residenceBirthlandAreaCn);
		model.setResidencePoliceStation(residencePoliceStation);
		model.setHomeProv(null!=homeProv?homeProv:null);
		model.setHomeProvCn(homeProvCn);
		model.setHomeCity(null!=homeCity?homeCity:null);
		model.setHomeCityCn(homeCityCn);
		model.setHomeAddr(null!=homeAddr && !"".equals(homeAddr.trim())?homeAddr.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setSalaryAdjustType(null!=salaryAdjustType?salaryAdjustType:null);
		model.setSalaryAdjustTypeCn(salaryAdjustTypeCn);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[医院职工表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年03月25日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmWorkerModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		
		String name = request.getParameter(PARA_name);
		if(StringHelper.isNullOrEmpty(name))
		{
			stringBuffer.append("输入职工姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String sexStr = request.getParameter(PARA_sex);
		Integer sex = !StringHelper.isNullOrEmpty(sexStr)?Integer.parseInt(sexStr):null;
		if(null == sex)
		{
			stringBuffer.append("输入职工性别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		//String ageStr = request.getParameter(PARA_age);
		//Integer age = !StringHelper.isNullOrEmpty(ageStr)?Integer.parseInt(ageStr):null;
		/*
		 * if(null == age) { stringBuffer.append("输入职工年龄!");
		 * stringBuffer.append(LayAjaxHelper.CR); }
		 */
		
		String cardTypeStr = request.getParameter(PARA_cardType);
		Integer cardType = !StringHelper.isNullOrEmpty(cardTypeStr)?Integer.parseInt(cardTypeStr):null;
		if(null == cardType) cardType = 0;
		String cardNo = request.getParameter(PARA_cardNo);
		if(StringHelper.isNullOrEmpty(cardNo))
		{
			stringBuffer.append("输入身份证号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		
		String birthDayStr = request.getParameter(PARA_birthDay);
		Date birthDay = !StringHelper.isNullOrEmpty(birthDayStr)?DateTimeHelper.strToDate(birthDayStr,"yyyy-MM"):null;
		Integer age = null;
		DayCompare dc = CalendarUtil.dayComparePrecise(birthDay, new Date());
		if(null != dc) age = dc.getYear();
		
		String nationStr = request.getParameter(PARA_nation);
		Integer nation = !StringHelper.isNullOrEmpty(nationStr)?Integer.parseInt(nationStr):null;
		if(null == nation)
		{
			stringBuffer.append("输入民族!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String nationCn = request.getParameter(PARA_nationCn);
		String politicsStr = request.getParameter(PARA_politics);
		Integer politics = !StringHelper.isNullOrEmpty(politicsStr)?Integer.parseInt(politicsStr):null;
		String politicsCn = request.getParameter(PARA_politicsCn);
		String politicsInTimeStr = request.getParameter(PARA_politicsInTime);
		Date politicsInTime = !StringHelper.isNullOrEmpty(politicsInTimeStr)?DateTimeHelper.createDate(politicsInTimeStr):null;
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.createDate(firstWorkTimeStr):null;
		
		Integer workedYear = null;
		if(null != firstWorkTime) {
			DayCompare wkdc = CalendarUtil.dayComparePrecise(firstWorkTime, new Date());
			workedYear = null != wkdc?wkdc.getYear():null;
		}
		
		//String workedYearStr = request.getParameter(PARA_workedYear);
		//Integer workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Integer.parseInt(workedYearStr):null;
		String intoLocalCompTimeStr = request.getParameter(PARA_intoLocalCompTime);
		Date intoLocalCompTime = !StringHelper.isNullOrEmpty(intoLocalCompTimeStr)?DateTimeHelper.createDate(intoLocalCompTimeStr):null;
		String nativePlaceProvStr = request.getParameter(PARA_nativePlaceProv);
		String nativePlaceCityStr = request.getParameter(PARA_nativePlaceCity);
		String nativePlaceAreaStr = request.getParameter(PARA_nativePlaceArea);
		Integer nativePlaceProv = !StringHelper.isNullOrEmpty(nativePlaceProvStr)?Integer.parseInt(nativePlaceProvStr):null;
		Integer nativePlaceCity = !StringHelper.isNullOrEmpty(nativePlaceCityStr)?Integer.parseInt(nativePlaceCityStr):null;
		Integer nativePlaceArea = !StringHelper.isNullOrEmpty(nativePlaceAreaStr)?Integer.parseInt(nativePlaceAreaStr):null;
		String nativePlaceProvCn = request.getParameter(PARA_nativePlaceProvCn);
		String nativePlaceCityCn = request.getParameter(PARA_nativePlaceCityCn);
		String nativePlaceAreaCn = request.getParameter(PARA_nativePlaceAreaCn);
		
		String residenceTypeStr = request.getParameter(PARA_residenceType);
		Integer residenceType = !StringHelper.isNullOrEmpty(residenceTypeStr)?Integer.parseInt(residenceTypeStr):null;
		String residenceTypeCn = request.getParameter(PARA_residenceTypeCn);
		String residenceBirthlandProvStr = request.getParameter(PARA_residenceBirthlandProv);
		String residenceBirthlandCityStr = request.getParameter(PARA_residenceBirthlandCity);
		String residenceBirthlandAreaStr = request.getParameter(PARA_residenceBirthlandArea);
		Integer residenceBirthlandProv = !StringHelper.isNullOrEmpty(residenceBirthlandProvStr)?Integer.parseInt(residenceBirthlandProvStr):null;
		Integer residenceBirthlandCity = !StringHelper.isNullOrEmpty(residenceBirthlandCityStr)?Integer.parseInt(residenceBirthlandCityStr):null;
		Integer residenceBirthlandArea = !StringHelper.isNullOrEmpty(residenceBirthlandAreaStr)?Integer.parseInt(residenceBirthlandAreaStr):null;
		String residenceBirthlandProvCn = request.getParameter(PARA_residenceBirthlandProvCn);
		String residenceBirthlandCityCn = request.getParameter(PARA_residenceBirthlandCityCn);
		String residenceBirthlandAreaCn = request.getParameter(PARA_residenceBirthlandAreaCn);
		String residencePoliceStation = request.getParameter(PARA_residencePoliceStation);
		String homeProvStr = request.getParameter(PARA_homeProv);
		String homeCityStr = request.getParameter(PARA_homeCity);
		Integer homeProv = !StringHelper.isNullOrEmpty(homeProvStr)?Integer.parseInt(homeProvStr):null;
		Integer homeCity = !StringHelper.isNullOrEmpty(homeCityStr)?Integer.parseInt(homeCityStr):null;
		String homeProvCn = request.getParameter(PARA_homeProvCn);
		String homeCityCn = request.getParameter(PARA_homeCityCn);
		String homeAddr = request.getParameter(PARA_homeAddr);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String salaryAdjustTypeStr = request.getParameter(PARA_salaryAdjustType);
		Integer salaryAdjustType = !StringHelper.isNullOrEmpty(salaryAdjustTypeStr)?Integer.parseInt(salaryAdjustTypeStr):null;
		if(null == salaryAdjustType)
		{
			stringBuffer.append("清选择调资类别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String salaryAdjustTypeCn = request.getParameter(PARA_salaryAdjustTypeCn);
		String remark = request.getParameter(PARA_remark);
		
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setSex(null!=sex?sex:null);
		model.setAge(null!=age?age:null);
		model.setCardType(null!=cardType?cardType:null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBirthDay(null!=birthDay?birthDay:null);
		model.setNation(null!=nation?nation:null);
		model.setNationCn(nationCn);
		model.setPolitics(null!=politics?politics:null);
		model.setPoliticsCn(politicsCn);
		model.setPoliticsInTime(null!=politicsInTime?politicsInTime:null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setIntoLocalCompTime(null!=intoLocalCompTime?intoLocalCompTime:null);
		model.setNativePlaceProv(null!=nativePlaceProv?nativePlaceProv:null);
		model.setNativePlaceProvCn(nativePlaceProvCn);
		model.setNativePlaceCity(null!=nativePlaceCity?nativePlaceCity:null);
		model.setNativePlaceCityCn(nativePlaceCityCn);
		model.setNativePlaceArea(nativePlaceArea);
		model.setNativePlaceAreaCn(nativePlaceAreaCn);
		model.setResidenceType(null!=residenceType?residenceType:null);
		model.setResidenceTypeCn(residenceTypeCn);
		model.setResidenceBirthlandProv(null!=residenceBirthlandProv?residenceBirthlandProv:null);
		model.setResidenceBirthlandProvCn(residenceBirthlandProvCn);
		model.setResidenceBirthlandCity(null!=residenceBirthlandCity?residenceBirthlandCity:null);
		model.setResidenceBirthlandCityCn(residenceBirthlandCityCn);
		model.setResidenceBirthlandArea(residenceBirthlandArea);
		model.setResidenceBirthlandAreaCn(residenceBirthlandAreaCn);
		model.setResidencePoliceStation(residencePoliceStation);
		model.setHomeProv(null!=homeProv?homeProv:null);
		model.setHomeProvCn(homeProvCn);
		model.setHomeCity(null!=homeCity?homeCity:null);
		model.setHomeCityCn(homeCityCn);
		model.setHomeAddr(null!=homeAddr && !"".equals(homeAddr.trim())?homeAddr.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setSalaryAdjustType(null!=salaryAdjustType?salaryAdjustType:null);
		model.setSalaryAdjustTypeCn(salaryAdjustTypeCn);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//字典数据加载
		fillCommonDict(modelMap);
		//省份数据加载
		List<SysProvinceModel> list = sysProvinceBiz.queryList(new SysProvinceModel());
		modelMap.addAttribute("provList", list);
	}
	
	/**
	 * 增加操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmWorkerModel model = new ChPmWorkerModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			if(null != model.getId()) 
				return LayAjaxHelper.fail("当前职工的基本信息已录入，请勿重复录入！");
			//增加
			Long id = chPmWorkerBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS,id);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("保存失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(HttpServletRequest request, ModelMap modelMap) throws Exception{
		//加载请求参数并返回页面
		String idStr = request.getParameter("id");
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		if(null == id) throw new Exception("职工编号为空");
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("oper", oper);
				
		ChPmWorkerModel model = chPmWorkerBiz.queryById(id);
		modelMap.addAttribute("model",model);
		
		//字典数据加载
		fillCommonDict(modelMap);
		//省份数据加载
		List<SysProvinceModel> list = sysProvinceBiz.queryList(new SysProvinceModel());
		modelMap.addAttribute("provList", list);
		
		//获取籍贯、户口所在地和现居住地的城市列表
		List<SysCityModel> nativeCityList = null;
		List<SysAreaModel> nativeAreaList = null;
		if(null != model.getNativePlaceProv()) {
			SysCityModel cModel1 = new SysCityModel();
			cModel1.setProvNo(model.getNativePlaceProv());
			nativeCityList = sysCityBiz.queryList(cModel1);
		}
		if(null != model.getNativePlaceCity()) {
			nativeAreaList = sysCityBiz.loadAreaListByCityNo(model.getNativePlaceCity());
		}
		
		List<SysCityModel> residenceCityList = null;
		List<SysAreaModel> residenceAreaList = null;
		if(null != model.getResidenceBirthlandProv()) {
			SysCityModel cModel2 = new SysCityModel();
			cModel2.setProvNo(model.getResidenceBirthlandProv());
			residenceCityList = sysCityBiz.queryList(cModel2);
		}
		if(null != model.getResidenceBirthlandCity()) {
			residenceAreaList = sysCityBiz.loadAreaListByCityNo(model.getResidenceBirthlandCity());
		}
		
		SysCityModel cModel3 = new SysCityModel();
		cModel3.setProvNo(model.getHomeProv());
		List<SysCityModel> homeCityList = sysCityBiz.queryList(cModel3);
		
		modelMap.addAttribute("nativeCityList", nativeCityList);
		modelMap.addAttribute("nativeAreaList", nativeAreaList);
		modelMap.addAttribute("residenceCityList", residenceCityList);
		modelMap.addAttribute("residenceAreaList", residenceAreaList);
		modelMap.addAttribute("homeCityList", homeCityList);
	}
	
	/**
	 *  编辑操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("id") Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmWorkerModel model = chPmWorkerBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chPmWorkerBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("编辑失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 删除操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("id")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("未检测到编号存在!");
			ChPmWorkerModel model = chPmWorkerBiz.queryById(id);
			if(model != null)
			{
				chPmWorkerBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model+",名称:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("删除失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 删除操作
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			chPmWorkerBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("批量删除失败！error:",ex);
		}
		return map;
	}
	
	/**
	 * 查看详细页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		ChPmWorkerModel model = null;
		try {
			model = chPmWorkerBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载医院职工表失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
		
	}
	//********************以下为扩展方法***********************
	
	/**
	 * 填充字典数据到回显Map
	 * @param id
	 * @param modelMap
	 */
	private void fillCommonDict(ModelMap modelMap) throws Exception
	{
		//字典数据加载
		List<String> codes = Arrays.asList(DICT_COMMON_CODES.split(","));
		Map<String,List<Map<String,Object>>> dictMap = dictBiz.queryItemListByCodes(codes);
		List<ComboxItem> comboxs = null;
		for(String dictCode:dictMap.keySet())
		{
			List<Map<String,Object>> list = dictMap.get(dictCode);
			comboxs = new ArrayList<ComboxItem>();
			for(Map<String,Object> item:list)
			{
				comboxs.add(new ComboxItem(item.get("item_key")+"",item.get("item_val")+""));
			}
			modelMap.addAttribute(dictCode+"List", comboxs);
		}
	}
	
}
