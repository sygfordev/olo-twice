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

import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmArchivesBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosBranchBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmWorkerBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmArchivesModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmWorkerModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [人事档案服务实现]
 * Created on 2021年03月25日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/archives/*")
public class ArchivesController extends BaseController {
	
	public final static String Module_Name = "人事档案管理";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChPmArchivesBiz chPmArchivesBiz;
	@Autowired
	private IChPmWorkerBiz chPmWorkerBiz;
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private IChPmHosBranchBiz chPmHosBranchBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_wagesId = "wagesId";
	private	final static String PARA_name = "name";
	private	final static String PARA_sex = "sex";
	private	final static String PARA_age = "age";
	private	final static String PARA_cardType = "cardType";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_birthDay = "birthDay";
	private	final static String PARA_nation = "nation";
	private	final static String PARA_politics = "politics";
	private	final static String PARA_politicsInTime = "politicsInTime";
	private	final static String PARA_firstWorkTime = "firstWorkTime";
	private	final static String PARA_workedYear = "workedYear";
	private	final static String PARA_intoLocalCompTime = "intoLocalCompTime";
	private	final static String PARA_nativePlaceProv = "nativePlaceProv";
	private	final static String PARA_nativePlaceCity = "nativePlaceCity";
	private	final static String PARA_nativePlaceArea = "nativePlaceArea";
	private	final static String PARA_residenceType = "residenceType";
	private	final static String PARA_residenceBirthlandProv = "residenceBirthlandProv";
	private	final static String PARA_residenceBirthlandCity = "residenceBirthlandCity";
	private	final static String PARA_residenceBirthlandArea = "residenceBirthlandArea";
	private	final static String PARA_homeProv = "homeProv";
	private	final static String PARA_homeCity = "homeCity";
	private	final static String PARA_homeAddr = "homeAddr";
	private	final static String PARA_telphoneNo = "telphoneNo";
	private	final static String PARA_mailBox = "mailBox";
	private	final static String PARA_salaryAdjustType = "salaryAdjustType";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//岗位字段
	private	final static String PARA_identity = "identity";
	private	final static String PARA_wkModality = "wkModality";
	private	final static String PARA_stationSitu = "stationSitu";
	private	final static String PARA_workArea = "workArea";
	private	final static String PARA_hosBranch = "hosBranch";
	private	final static String PARA_hosDepart1level = "hosDepart1level";
	
	
	
	private final static String DICT_COMMON_CODES = "status,identity,wkType,wkModality,stationSitu,workArea,YN";
	
	
	/**
	 * <p>Discription:[医院职工表请求首页]</p>
	 * Created on 2021年03月25日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
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
		//医院支部信息加载
		List<ChPmHosBranchModel> hbras = chPmHosBranchBiz.queryList(new ChPmHosBranchModel(),"hbh_no,hbh_name");
		modelMap.addAttribute("hbranchs", hbras);
	}
	 
	/**
	 * <p>Discription:[医院职工表首页数据加载-分页]</p>
	 * Created on 2021年03月25日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmArchivesModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmArchivesModel> retBody = new Result4Page<ChPmArchivesModel>();
		//查询参数
		Page.Builder<ChPmArchivesModel> builder = new Page.Builder<ChPmArchivesModel>();
		setSearchParameters(request, builder);
		Page<ChPmArchivesModel> page = builder.build();
		try {
			//分页
			chPmArchivesBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			logger.error("查询失败!", e);
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
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmArchivesModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String wagesId = request.getParameter(PARA_wagesId);
		String name = request.getParameter(PARA_name);
		String sexStr = request.getParameter(PARA_sex);
		Integer sex = !StringHelper.isNullOrEmpty(sexStr)?Integer.parseInt(sexStr):null;
		String ageStr = request.getParameter(PARA_age);
		Integer age = !StringHelper.isNullOrEmpty(ageStr)?Integer.parseInt(ageStr):null;
		String cardTypeStr = request.getParameter(PARA_cardType);
		Integer cardType = !StringHelper.isNullOrEmpty(cardTypeStr)?Integer.parseInt(cardTypeStr):null;
		String cardNo = request.getParameter(PARA_cardNo);
		String birthDayStr = request.getParameter(PARA_birthDay);
		Date birthDay = !StringHelper.isNullOrEmpty(birthDayStr)?DateTimeHelper.createDate(birthDayStr):null;
		String nationStr = request.getParameter(PARA_nation);
		Integer nation = !StringHelper.isNullOrEmpty(nationStr)?Integer.parseInt(nationStr):null;
		String politicsStr = request.getParameter(PARA_politics);
		Integer politics = !StringHelper.isNullOrEmpty(politicsStr)?Integer.parseInt(politicsStr):null;
		String politicsInTimeStr = request.getParameter(PARA_politicsInTime);
		Date politicsInTime = !StringHelper.isNullOrEmpty(politicsInTimeStr)?DateTimeHelper.createDate(politicsInTimeStr):null;
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.createDate(firstWorkTimeStr):null;
		String workedYearStr = request.getParameter(PARA_workedYear);
		Integer workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Integer.parseInt(workedYearStr):null;
		String intoLocalCompTimeStr = request.getParameter(PARA_intoLocalCompTime);
		Date intoLocalCompTime = !StringHelper.isNullOrEmpty(intoLocalCompTimeStr)?DateTimeHelper.createDate(intoLocalCompTimeStr):null;
		String nativePlaceProvStr = request.getParameter(PARA_nativePlaceProv);
		String nativePlaceCityStr = request.getParameter(PARA_nativePlaceProv);
		Integer nativePlaceProv = !StringHelper.isNullOrEmpty(nativePlaceProvStr)?Integer.parseInt(nativePlaceProvStr):null;
		Integer nativePlaceCity = !StringHelper.isNullOrEmpty(nativePlaceCityStr)?Integer.parseInt(nativePlaceCityStr):null;
		
		String residenceTypeStr = request.getParameter(PARA_residenceType);
		Integer residenceType = !StringHelper.isNullOrEmpty(residenceTypeStr)?Integer.parseInt(residenceTypeStr):null;
		String residenceBirthlandProvStr = request.getParameter(PARA_residenceBirthlandProv);
		String residenceBirthlandCityStr = request.getParameter(PARA_residenceBirthlandCity);
		Integer residenceBirthlandProv = !StringHelper.isNullOrEmpty(residenceBirthlandProvStr)?Integer.parseInt(residenceBirthlandProvStr):null;
		Integer residenceBirthlandCity = !StringHelper.isNullOrEmpty(residenceBirthlandCityStr)?Integer.parseInt(residenceBirthlandCityStr):null;
		String homeProvStr = request.getParameter(PARA_homeProv);
		String homeCityStr = request.getParameter(PARA_homeCity);
		Integer homeProv = !StringHelper.isNullOrEmpty(homeProvStr)?Integer.parseInt(homeProvStr):null;
		Integer homeCity = !StringHelper.isNullOrEmpty(homeCityStr)?Integer.parseInt(homeCityStr):null;
		
		String homeAddr = request.getParameter(PARA_homeAddr);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String salaryAdjustTypeStr = request.getParameter(PARA_salaryAdjustType);
		Integer salaryAdjustType = !StringHelper.isNullOrEmpty(salaryAdjustTypeStr)?Integer.parseInt(salaryAdjustTypeStr):null;
		String remark = request.getParameter(PARA_remark);
		
		//岗位字段
		String identityStr = request.getParameter(PARA_identity);
		Integer identity = !StringHelper.isNullOrEmpty(identityStr)?Integer.parseInt(identityStr):null;
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		String stationSituStr = request.getParameter(PARA_stationSitu);
		Integer stationSitu = !StringHelper.isNullOrEmpty(stationSituStr)?Integer.parseInt(stationSituStr):null;
		String workAreaStr = request.getParameter(PARA_workArea);
		Integer workArea = !StringHelper.isNullOrEmpty(workAreaStr)?Integer.parseInt(workAreaStr):null;
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		
		
		ChPmArchivesModel model = new ChPmArchivesModel();
		model.setId(null!=id?id:null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setSex(null!=sex?sex:null);
		model.setAge(null!=age?age:null);
		model.setCardType(null!=cardType?cardType:null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBirthDay(null!=birthDay?birthDay:null);
		model.setNation(null!=nation?nation:null);
		model.setPolitics(null!=politics?politics:null);
		model.setPoliticsInTime(null!=politicsInTime?politicsInTime:null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setIntoLocalCompTime(null!=intoLocalCompTime?intoLocalCompTime:null);
		model.setNativePlaceProv(null!=nativePlaceProv?nativePlaceProv:null);
		model.setNativePlaceCity(null!=nativePlaceCity?nativePlaceCity:null);
		model.setResidenceType(null!=residenceType?residenceType:null);
		model.setResidenceBirthlandProv(null!=residenceBirthlandProv?residenceBirthlandProv:null);
		model.setResidenceBirthlandCity(null!=residenceBirthlandCity?residenceBirthlandCity:null);
		model.setHomeProv(null!=homeProv?homeProv:null);
		model.setHomeCity(null!=homeCity?homeCity:null);
		model.setHomeAddr(null!=homeAddr && !"".equals(homeAddr.trim())?homeAddr.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setSalaryAdjustType(null!=salaryAdjustType?salaryAdjustType:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		
		//岗位字段
		model.setIdentity(identity);
		model.setWkModality(wkModality);
		model.setStationSitu(stationSitu);
		model.setWorkArea(workArea);
		model.setHosBranch(hosBranch);
		model.setHosDepart1level(hosDepart1level);
		
        
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
			StringBuffer stringBuffer, ChPmArchivesModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String wagesId = request.getParameter(PARA_wagesId);
		String name = request.getParameter(PARA_name);
		String sexStr = request.getParameter(PARA_sex);
		Integer sex = !StringHelper.isNullOrEmpty(sexStr)?Integer.parseInt(sexStr):null;
		String ageStr = request.getParameter(PARA_age);
		Integer age = !StringHelper.isNullOrEmpty(ageStr)?Integer.parseInt(ageStr):null;
		String cardTypeStr = request.getParameter(PARA_cardType);
		Integer cardType = !StringHelper.isNullOrEmpty(cardTypeStr)?Integer.parseInt(cardTypeStr):null;
		String cardNo = request.getParameter(PARA_cardNo);
		String birthDayStr = request.getParameter(PARA_birthDay);
		Date birthDay = !StringHelper.isNullOrEmpty(birthDayStr)?DateTimeHelper.createDate(birthDayStr):null;
		String nationStr = request.getParameter(PARA_nation);
		Integer nation = !StringHelper.isNullOrEmpty(nationStr)?Integer.parseInt(nationStr):null;
		String politicsStr = request.getParameter(PARA_politics);
		Integer politics = !StringHelper.isNullOrEmpty(politicsStr)?Integer.parseInt(politicsStr):null;
		String politicsInTimeStr = request.getParameter(PARA_politicsInTime);
		Date politicsInTime = !StringHelper.isNullOrEmpty(politicsInTimeStr)?DateTimeHelper.createDate(politicsInTimeStr):null;
		String firstWorkTimeStr = request.getParameter(PARA_firstWorkTime);
		Date firstWorkTime = !StringHelper.isNullOrEmpty(firstWorkTimeStr)?DateTimeHelper.createDate(firstWorkTimeStr):null;
		String workedYearStr = request.getParameter(PARA_workedYear);
		Integer workedYear = !StringHelper.isNullOrEmpty(workedYearStr)?Integer.parseInt(workedYearStr):null;
		String intoLocalCompTimeStr = request.getParameter(PARA_intoLocalCompTime);
		Date intoLocalCompTime = !StringHelper.isNullOrEmpty(intoLocalCompTimeStr)?DateTimeHelper.createDate(intoLocalCompTimeStr):null;
		String nativePlaceProvStr = request.getParameter(PARA_nativePlaceProv);
		String nativePlaceCityStr = request.getParameter(PARA_nativePlaceProv);
		Integer nativePlaceProv = !StringHelper.isNullOrEmpty(nativePlaceProvStr)?Integer.parseInt(nativePlaceProvStr):null;
		Integer nativePlaceCity = !StringHelper.isNullOrEmpty(nativePlaceCityStr)?Integer.parseInt(nativePlaceCityStr):null;
		String residenceTypeStr = request.getParameter(PARA_residenceType);
		Integer residenceType = !StringHelper.isNullOrEmpty(residenceTypeStr)?Integer.parseInt(residenceTypeStr):null;
		String residenceBirthlandProvStr = request.getParameter(PARA_residenceBirthlandProv);
		String residenceBirthlandCityStr = request.getParameter(PARA_residenceBirthlandCity);
		Integer residenceBirthlandProv = !StringHelper.isNullOrEmpty(residenceBirthlandProvStr)?Integer.parseInt(residenceBirthlandProvStr):null;
		Integer residenceBirthlandCity = !StringHelper.isNullOrEmpty(residenceBirthlandCityStr)?Integer.parseInt(residenceBirthlandCityStr):null;
		String homeProvStr = request.getParameter(PARA_homeProv);
		String homeCityStr = request.getParameter(PARA_homeCity);
		Integer homeProv = !StringHelper.isNullOrEmpty(homeProvStr)?Integer.parseInt(homeProvStr):null;
		Integer homeCity = !StringHelper.isNullOrEmpty(homeCityStr)?Integer.parseInt(homeCityStr):null;
		String homeAddr = request.getParameter(PARA_homeAddr);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String salaryAdjustTypeStr = request.getParameter(PARA_salaryAdjustType);
		Integer salaryAdjustType = !StringHelper.isNullOrEmpty(salaryAdjustTypeStr)?Integer.parseInt(salaryAdjustTypeStr):null;
		String remark = request.getParameter(PARA_remark);
		
		//岗位字段
		String identityStr = request.getParameter(PARA_identity);
		Integer identity = !StringHelper.isNullOrEmpty(identityStr)?Integer.parseInt(identityStr):null;
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		String stationSituStr = request.getParameter(PARA_stationSitu);
		Integer stationSitu = !StringHelper.isNullOrEmpty(stationSituStr)?Integer.parseInt(stationSituStr):null;
		String workAreaStr = request.getParameter(PARA_workArea);
		Integer workArea = !StringHelper.isNullOrEmpty(workAreaStr)?Integer.parseInt(workAreaStr):null;
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		
		model.setId(null!=id?id:null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setSex(null!=sex?sex:null);
		model.setAge(null!=age?age:null);
		model.setCardType(null!=cardType?cardType:null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBirthDay(null!=birthDay?birthDay:null);
		model.setNation(null!=nation?nation:null);
		model.setPolitics(null!=politics?politics:null);
		model.setPoliticsInTime(null!=politicsInTime?politicsInTime:null);
		model.setFirstWorkTime(null!=firstWorkTime?firstWorkTime:null);
		model.setWorkedYear(null!=workedYear?workedYear:null);
		model.setIntoLocalCompTime(null!=intoLocalCompTime?intoLocalCompTime:null);
		model.setNativePlaceProv(null!=nativePlaceProv?nativePlaceProv:null);
		model.setNativePlaceCity(null!=nativePlaceCity?nativePlaceCity:null);
		model.setResidenceType(null!=residenceType?residenceType:null);
		model.setResidenceBirthlandProv(null!=residenceBirthlandProv?residenceBirthlandProv:null);
		model.setResidenceBirthlandCity(null!=residenceBirthlandCity?residenceBirthlandCity:null);
		model.setHomeProv(null!=homeProv?homeProv:null);
		model.setHomeCity(null!=homeCity?homeCity:null);
		model.setHomeAddr(null!=homeAddr && !"".equals(homeAddr.trim())?homeAddr.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setSalaryAdjustType(null!=salaryAdjustType?salaryAdjustType:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		
		//岗位字段
		model.setIdentity(identity);
		model.setWkModality(wkModality);
		model.setStationSitu(stationSitu);
		model.setWorkArea(workArea);
		model.setHosBranch(hosBranch);
		model.setHosDepart1level(hosDepart1level);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) {
		List<ComboxItem> sexList = getSexList();
		List<ComboxItem> statusList = getStatusList();
	
		modelMap.addAttribute("sexList", sexList);
		modelMap.addAttribute("statusList", statusList);
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(HttpServletRequest request, ModelMap modelMap) throws Exception{
		//加载请求参数并返回页面
		String wkIdStr = request.getParameter("id");
		Long wkId = !StringHelper.isNullOrEmpty(wkIdStr)?Long.parseLong(wkIdStr):null;
		if(null == wkId) throw new Exception("职工编号为空");
		modelMap.addAttribute("wkId", wkId);
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("oper", oper);
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
			logger.error("删除失败!error:",ex);
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
			logger.error("批量删除失败!error:",ex);
		}
		return map;
	}
	
	//********************以下为扩展方法***********************
}
