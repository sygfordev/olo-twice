package com.hdfs.olo.olo_web.salary.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
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
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipImprecordBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipRewardBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipImprecordModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;

/** 
 * Description: [??????-?????????????????????]
 * Created on 2021???05???14???
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021??? xxxx
 */
@Controller
@RequestMapping("/salary/chSaPayslip/*")
public class ChSaPayslipController extends BaseController {
	
	public final static String Module_Name = "??????-?????????";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
//	@Autowired
//	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChSaPayslipBiz chSaPayslipBiz;
//	@Autowired
//	private IChPmHosDepartBiz chPmHosDepartBiz;
	@Autowired
	private IHuTCommonService huTCommonService; 
	@Autowired
	private IChSaPayslipImprecordBiz chSaPayslipImprecordBiz;
	@Autowired
	private IChSaPayslipRewardBiz chSaPayslipRewardBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_name = "name";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_bankCardNo = "bankCardNo";
	private	final static String PARA_mobileNo = "mobileNo";
	private	final static String PARA_wkModality = "wkModality";
	private	final static String PARA_wkModalityCn = "wkModalityCn";
	private	final static String PARA_wagesId = "wagesId";
	private	final static String PARA_posit = "posit";
	private	final static String PARA_positCn = "positCn";
	private	final static String PARA_title = "title";
	private	final static String PARA_titleCn = "titleCn";
	private	final static String PARA_hosBranch = "hosBranch";
	private	final static String PARA_hosBranchCn = "hosBranchCn";
	private	final static String PARA_hosDepart1level = "hosDepart1level";
	private	final static String PARA_hosDepart1levelCn = "hosDepart1levelCn";
	private	final static String PARA_hosDepart2level = "hosDepart2level";
	private	final static String PARA_hosDepart2levelCn = "hosDepart2levelCn";
	private	final static String PARA_departClassCn = "departClassCn";
	private	final static String PARA_departClassPop = "departClassPop";
	private	final static String PARA_stationCn = "stationCn";
	private	final static String PARA_stationTypeCn = "stationTypeCn";
	private	final static String PARA_stationStatusCn = "stationStatusCn";
	private	final static String PARA_stationSeqCn = "stationSeqCn";
	private	final static String PARA_titleClassCn = "titleClassCn";
	private	final static String PARA_skillsLevelCn = "skillsLevelCn";
	private	final static String PARA_eduLev4nowCn = "eduLev4nowCn";
	private	final static String PARA_yearlySalaryMan = "yearlySalaryMan";
	private	final static String PARA_saSumProject = "saSumProject";
	private	final static String PARA_rptWkDepClass = "rptWkDepClass";
	private	final static String PARA_manwkStandard = "manwkStandard";
	private	final static String PARA_manwkAttend = "manwkAttend";
	private	final static String PARA_manwkMiner = "manwkMiner";
	private	final static String PARA_manwkQuit = "manwkQuit";
	private	final static String PARA_manwkSick = "manwkSick";
	private	final static String PARA_manwkMaternity = "manwkMaternity";
	private	final static String PARA_manwkPrivpassion = "manwkPrivpassion";
	private	final static String PARA_manwkOvertime = "manwkOvertime";
	private	final static String PARA_wageStandard4posit = "wageStandard4posit";
	private	final static String PARA_wageDay4posit = "wageDay4posit";
	private	final static String PARA_wageAttend = "wageAttend";
	private	final static String PARA_wageSick = "wageSick";
	private	final static String PARA_wagePositTotal = "wagePositTotal";
	private	final static String PARA_wageNightShift = "wageNightShift";
	private	final static String PARA_wageOvertime = "wageOvertime";
	private	final static String PARA_wageYearg = "wageYearg";
	private	final static String PARA_allowanceHygiene = "allowanceHygiene";
	private	final static String PARA_wageErrorCorrent = "wageErrorCorrent";
	private	final static String PARA_supp4tel = "supp4tel";
	private	final static String PARA_supp4traffic = "supp4traffic";
	private	final static String PARA_supp4mining = "supp4mining";
	private	final static String PARA_supp4other = "supp4other";
	private	final static String PARA_supp4univeStuLife = "supp4univeStuLife";
	private	final static String PARA_supp4oth = "supp4oth";
	private	final static String PARA_suppTotal = "suppTotal";
	private	final static String PARA_wageTotal = "wageTotal";
	private	final static String PARA_wageJxTotal = "wageJxTotal";
	private	final static String PARA_wagePayableTotal = "wagePayableTotal";
	private	final static String PARA_bxPension = "bxPension";
	private	final static String PARA_bxMedical = "bxMedical";
	private	final static String PARA_bxUnemploy = "bxUnemploy";
	private	final static String PARA_bxSeriousIllness = "bxSeriousIllness";
	private	final static String PARA_bxHousfund = "bxHousfund";
	private	final static String PARA_bxAnnualCorpGold = "bxAnnualCorpGold";
	private	final static String PARA_bxTotal = "bxTotal";
	private	final static String PARA_cutWater2elect = "cutWater2elect";
	private	final static String PARA_cutHygiene = "cutHygiene";
	private	final static String PARA_cutOther = "cutOther";
	private	final static String PARA_cutArrears = "cutArrears";
	private	final static String PARA_cutTotal = "cutTotal";
	private	final static String PARA_taxableWage = "taxableWage";
	private	final static String PARA_taxIncomePersonal = "taxIncomePersonal";
	private	final static String PARA_netSalary = "netSalary";
	private	final static String PARA_netTargetYearmonth = "netTargetYearmonth";
	private	final static String PARA_status = "status";
	private	final static String PARA_btimpNo = "btimpNo";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	//??????????????????
	private	final static String PARA_positCns = "positCns";
	private	final static String PARA_titleCns = "titleCns";
	private	final static String PARA_hosBranchCns = "hosBranchCns";
	private	final static String PARA_wkModalityCns = "wkModalityCns";
	private	final static String PARA_hosDepart1levelCns = "hosDepart1levelCns";
	private	final static String PARA_hosDepart2levelCns = "hosDepart2levelCns";
	private	final static String PARA_departClassCns = "departClassCns";
	private	final static String PARA_departClassPops = "departClassPops";
	private	final static String PARA_stationCns = "stationCns";
	private	final static String PARA_stationTypeCns = "stationTypeCns";
	private	final static String PARA_stationStatusCns = "stationStatusCns";
	private	final static String PARA_stationSeqCns = "stationSeqCns";
	private	final static String PARA_titleClassCns = "titleClassCns";
	private	final static String PARA_skillsLevelCns = "skillsLevelCns";
	private	final static String PARA_eduLev4nowCns = "eduLev4nowCns";
	private	final static String PARA_saSumProjects = "saSumProjects";
	private	final static String PARA_rptWkDepClasss = "rptWkDepClasss";
	
	//??????-??????????????????????????????????????????????????????
	private final static String DICT_COMMON_CODES = "status,wkModality";
	
	//???????????????????????????
	Long cachedTime = null;
	Map<String,List<String>> cachedMap = new HashMap<String,List<String>>();
	private static final Map<String,Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String,Object>();
		cachedColumnsMap.put("HOS_BRANCH_CN", "branch");
		cachedColumnsMap.put("HOS_DEPART_1LEVEL_CN", "depart1");
		cachedColumnsMap.put("HOS_DEPART_2LEVEL_CN", "depart2");
		cachedColumnsMap.put("WK_MODALITY_CN", "modality");
		cachedColumnsMap.put("POSIT_CN", "posit");
		cachedColumnsMap.put("TITLE_CN", "title");
		cachedColumnsMap.put("DEPART_CLASS_CN", "departClass");
		cachedColumnsMap.put("DEPART_CLASS_POP", "departClassPop");
		cachedColumnsMap.put("STATION_CN", "station");
		cachedColumnsMap.put("STATION_TYPE_CN", "stationType");
		cachedColumnsMap.put("STATION_STATUS_CN", "stationStatus");
		cachedColumnsMap.put("STATION_SEQ_CN", "stationSeq");
		cachedColumnsMap.put("TITLE_CLASS_CN", "titleClass");
		cachedColumnsMap.put("SKILLS_LEVEL_CN", "skillsLevel");
		cachedColumnsMap.put("EDU_LEV_4NOW_CN", "eduLev4Now");
		cachedColumnsMap.put("SA_SUM_PROJECT", "saSumProject");
		cachedColumnsMap.put("RPT_WK_DEP_CLASS", "rptWkDepClass");
	}
	/**
	 * <p>Discription:[??????-?????????????????????]</p>
	 * Created on 2021???05???14???												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//??????????????????
		//fillCommonDict(modelMap);
		loadCachedDict4Payslip(modelMap);
	}
	 
	/**
	 * <p>Discription:[??????-???????????????????????????-??????]</p>
	 * Created on 2021???05???14???								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaPayslipExtendModel> page(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Result4Page<ChSaPayslipExtendModel> retBody = new Result4Page<ChSaPayslipExtendModel>();
		//????????????
		Page.Builder<ChSaPayslipExtendModel> builder = new Page.Builder<ChSaPayslipExtendModel>();
		setSearchParameters(request, builder);
		Page<ChSaPayslipExtendModel> page = builder.build();
		try {
			//??????
			page.setOrderBy("NET_TARGET_YEARMONTH desc");
			chSaPayslipBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("???????????????");
			retBody.setData(packRewardData(page));
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("???????????????");
			e.printStackTrace();
		}
		return retBody;
	}
	
	@RequestMapping(value = "index/loadTabHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadTabHead(HttpServletRequest request,ModelMap modelMap)
	{
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		if(StringHelper.isNullOrEmpty(startMonth) && 
				StringHelper.isNullOrEmpty(endMonth))
		{
			//??????????????????
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				startMonth = chSaPayslipRewardBiz.queryLastMonth();
				endMonth = startMonth;
			}
		}
		JSONArray heads = packRewardHead(startMonth,endMonth);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("heads", heads);
		retMap.put("startMonth", startMonth);
		retMap.put("endMonth", endMonth);
		return retMap;
	}

	/**
	 * <p>Discription:[??????-???????????????????????????]</p>
	 * @param request????????????
	 * @param builder???????????????
	 * Created on 2021???05???14???								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaPayslipExtendModel> builder) throws Exception{
		//????????????????????????
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//??????????????????
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		String name = request.getParameter(PARA_name);
		//name=!StringHelper.isNullOrEmpty(name)?new String(name.getBytes("iso8859-1"),"UTF-8"):null;
		String cardNo = request.getParameter(PARA_cardNo);
		String bankCardNo = request.getParameter(PARA_bankCardNo);
		String mobileNo = request.getParameter(PARA_mobileNo);
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String wagesId = request.getParameter(PARA_wagesId);
		String positStr = request.getParameter(PARA_posit);
		Integer posit = !StringHelper.isNullOrEmpty(positStr)?Integer.parseInt(positStr):null;
		String positCn = request.getParameter(PARA_positCn);
		String titleStr = request.getParameter(PARA_title);
		Integer title = !StringHelper.isNullOrEmpty(titleStr)?Integer.parseInt(titleStr):null;
		String titleCn = request.getParameter(PARA_titleCn);
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelStr = request.getParameter(PARA_hosDepart2level);
		Integer hosDepart2level = !StringHelper.isNullOrEmpty(hosDepart2levelStr)?Integer.parseInt(hosDepart2levelStr):null;
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String departClassCn = request.getParameter(PARA_departClassCn);
		String departClassPop = request.getParameter(PARA_departClassPop);
		String stationCn = request.getParameter(PARA_stationCn);
		String stationTypeCn = request.getParameter(PARA_stationTypeCn);
		String stationStatusCn = request.getParameter(PARA_stationStatusCn);
		String stationSeqCn = request.getParameter(PARA_stationSeqCn);
		String titleClassCn = request.getParameter(PARA_titleClassCn);
		String skillsLevelCn = request.getParameter(PARA_skillsLevelCn);
		String eduLev4nowCn = request.getParameter(PARA_eduLev4nowCn);
		String yearlySalaryMan = request.getParameter(PARA_yearlySalaryMan);
		String saSumProject = request.getParameter(PARA_saSumProject);
		String rptWkDepClass = request.getParameter(PARA_rptWkDepClass);
		String manwkStandardStr = request.getParameter(PARA_manwkStandard);
		BigDecimal manwkStandard = !StringHelper.isNullOrEmpty(manwkStandardStr)?new BigDecimal(manwkStandardStr):null;
		String manwkAttendStr = request.getParameter(PARA_manwkAttend);
		BigDecimal manwkAttend = !StringHelper.isNullOrEmpty(manwkAttendStr)?new BigDecimal(manwkAttendStr):null;
		String manwkMinerStr = request.getParameter(PARA_manwkMiner);
		BigDecimal manwkMiner = !StringHelper.isNullOrEmpty(manwkMinerStr)?new BigDecimal(manwkMinerStr):null;
		String manwkQuitStr = request.getParameter(PARA_manwkQuit);
		BigDecimal manwkQuit = !StringHelper.isNullOrEmpty(manwkQuitStr)?new BigDecimal(manwkQuitStr):null;
		String manwkSickStr = request.getParameter(PARA_manwkSick);
		BigDecimal manwkSick = !StringHelper.isNullOrEmpty(manwkSickStr)?new BigDecimal(manwkSickStr):null;
		String manwkMaternityStr = request.getParameter(PARA_manwkMaternity);
		BigDecimal manwkMaternity = !StringHelper.isNullOrEmpty(manwkMaternityStr)?new BigDecimal(manwkMaternityStr):null;
		String manwkPrivpassionStr = request.getParameter(PARA_manwkPrivpassion);
		BigDecimal manwkPrivpassion = !StringHelper.isNullOrEmpty(manwkPrivpassionStr)?new BigDecimal(manwkPrivpassionStr):null;
		String manwkOvertimeStr = request.getParameter(PARA_manwkOvertime);
		BigDecimal manwkOvertime = !StringHelper.isNullOrEmpty(manwkOvertimeStr)?new BigDecimal(manwkOvertimeStr):null;
		String wageStandard4positStr = request.getParameter(PARA_wageStandard4posit);
		BigDecimal wageStandard4posit = !StringHelper.isNullOrEmpty(wageStandard4positStr)?new BigDecimal(wageStandard4positStr):null;
		String wageDay4positStr = request.getParameter(PARA_wageDay4posit);
		BigDecimal wageDay4posit = !StringHelper.isNullOrEmpty(wageDay4positStr)?new BigDecimal(wageDay4positStr):null;
		String wageAttendStr = request.getParameter(PARA_wageAttend);
		BigDecimal wageAttend = !StringHelper.isNullOrEmpty(wageAttendStr)?new BigDecimal(wageAttendStr):null;
		String wageSickStr = request.getParameter(PARA_wageSick);
		BigDecimal wageSick = !StringHelper.isNullOrEmpty(wageSickStr)?new BigDecimal(wageSickStr):null;
		String wagePositTotalStr = request.getParameter(PARA_wagePositTotal);
		BigDecimal wagePositTotal = !StringHelper.isNullOrEmpty(wagePositTotalStr)?new BigDecimal(wagePositTotalStr):null;
		String wageNightShiftStr = request.getParameter(PARA_wageNightShift);
		BigDecimal wageNightShift = !StringHelper.isNullOrEmpty(wageNightShiftStr)?new BigDecimal(wageNightShiftStr):null;
		String wageOvertimeStr = request.getParameter(PARA_wageOvertime);
		BigDecimal wageOvertime = !StringHelper.isNullOrEmpty(wageOvertimeStr)?new BigDecimal(wageOvertimeStr):null;
		String wageYeargStr = request.getParameter(PARA_wageYearg);
		BigDecimal wageYearg = !StringHelper.isNullOrEmpty(wageYeargStr)?new BigDecimal(wageYeargStr):null;
		String allowanceHygieneStr = request.getParameter(PARA_allowanceHygiene);
		BigDecimal allowanceHygiene = !StringHelper.isNullOrEmpty(allowanceHygieneStr)?new BigDecimal(allowanceHygieneStr):null;
		String wageErrorCorrentStr = request.getParameter(PARA_wageErrorCorrent);
		BigDecimal wageErrorCorrent = !StringHelper.isNullOrEmpty(wageErrorCorrentStr)?new BigDecimal(wageErrorCorrentStr):null;
		String supp4telStr = request.getParameter(PARA_supp4tel);
		BigDecimal supp4tel = !StringHelper.isNullOrEmpty(supp4telStr)?new BigDecimal(supp4telStr):null;
		String supp4trafficStr = request.getParameter(PARA_supp4traffic);
		BigDecimal supp4traffic = !StringHelper.isNullOrEmpty(supp4trafficStr)?new BigDecimal(supp4trafficStr):null;
		String supp4miningStr = request.getParameter(PARA_supp4mining);
		BigDecimal supp4mining = !StringHelper.isNullOrEmpty(supp4miningStr)?new BigDecimal(supp4miningStr):null;
		String supp4otherStr = request.getParameter(PARA_supp4other);
		BigDecimal supp4other = !StringHelper.isNullOrEmpty(supp4otherStr)?new BigDecimal(supp4otherStr):null;
		String supp4univeStuLifeStr = request.getParameter(PARA_supp4univeStuLife);
		BigDecimal supp4univeStuLife = !StringHelper.isNullOrEmpty(supp4univeStuLifeStr)?new BigDecimal(supp4univeStuLifeStr):null;
		String supp4othStr = request.getParameter(PARA_supp4oth);
		BigDecimal supp4oth = !StringHelper.isNullOrEmpty(supp4othStr)?new BigDecimal(supp4othStr):null;
		String suppTotalStr = request.getParameter(PARA_suppTotal);
		BigDecimal suppTotal = !StringHelper.isNullOrEmpty(suppTotalStr)?new BigDecimal(suppTotalStr):null;
		String wageTotalStr = request.getParameter(PARA_wageTotal);
		BigDecimal wageTotal = !StringHelper.isNullOrEmpty(wageTotalStr)?new BigDecimal(wageTotalStr):null;
		String wageJxTotalStr = request.getParameter(PARA_wageJxTotal);
		BigDecimal wageJxTotal = !StringHelper.isNullOrEmpty(wageJxTotalStr)?new BigDecimal(wageJxTotalStr):null;
		String wagePayableTotalStr = request.getParameter(PARA_wagePayableTotal);
		BigDecimal wagePayableTotal = !StringHelper.isNullOrEmpty(wagePayableTotalStr)?new BigDecimal(wagePayableTotalStr):null;
		String bxPensionStr = request.getParameter(PARA_bxPension);
		BigDecimal bxPension = !StringHelper.isNullOrEmpty(bxPensionStr)?new BigDecimal(bxPensionStr):null;
		String bxMedicalStr = request.getParameter(PARA_bxMedical);
		BigDecimal bxMedical = !StringHelper.isNullOrEmpty(bxMedicalStr)?new BigDecimal(bxMedicalStr):null;
		String bxUnemployStr = request.getParameter(PARA_bxUnemploy);
		BigDecimal bxUnemploy = !StringHelper.isNullOrEmpty(bxUnemployStr)?new BigDecimal(bxUnemployStr):null;
		String bxSeriousIllnessStr = request.getParameter(PARA_bxSeriousIllness);
		BigDecimal bxSeriousIllness = !StringHelper.isNullOrEmpty(bxSeriousIllnessStr)?new BigDecimal(bxSeriousIllnessStr):null;
		String bxHousfundStr = request.getParameter(PARA_bxHousfund);
		BigDecimal bxHousfund = !StringHelper.isNullOrEmpty(bxHousfundStr)?new BigDecimal(bxHousfundStr):null;
		String bxAnnualCorpGoldStr = request.getParameter(PARA_bxAnnualCorpGold);
		BigDecimal bxAnnualCorpGold = !StringHelper.isNullOrEmpty(bxAnnualCorpGoldStr)?new BigDecimal(bxAnnualCorpGoldStr):null;
		String bxTotalStr = request.getParameter(PARA_bxTotal);
		BigDecimal bxTotal = !StringHelper.isNullOrEmpty(bxTotalStr)?new BigDecimal(bxTotalStr):null;
		String cutWater2electStr = request.getParameter(PARA_cutWater2elect);
		BigDecimal cutWater2elect = !StringHelper.isNullOrEmpty(cutWater2electStr)?new BigDecimal(cutWater2electStr):null;
		String cutHygieneStr = request.getParameter(PARA_cutHygiene);
		BigDecimal cutHygiene = !StringHelper.isNullOrEmpty(cutHygieneStr)?new BigDecimal(cutHygieneStr):null;
		String cutOtherStr = request.getParameter(PARA_cutOther);
		BigDecimal cutOther = !StringHelper.isNullOrEmpty(cutOtherStr)?new BigDecimal(cutOtherStr):null;
		String cutArrearsStr = request.getParameter(PARA_cutArrears);
		BigDecimal cutArrears = !StringHelper.isNullOrEmpty(cutArrearsStr)?new BigDecimal(cutArrearsStr):null;
		String cutTotalStr = request.getParameter(PARA_cutTotal);
		BigDecimal cutTotal = !StringHelper.isNullOrEmpty(cutTotalStr)?new BigDecimal(cutTotalStr):null;
		String taxableWageStr = request.getParameter(PARA_taxableWage);
		BigDecimal taxableWage = !StringHelper.isNullOrEmpty(taxableWageStr)?new BigDecimal(taxableWageStr):null;
		String taxIncomePersonalStr = request.getParameter(PARA_taxIncomePersonal);
		BigDecimal taxIncomePersonal = !StringHelper.isNullOrEmpty(taxIncomePersonalStr)?new BigDecimal(taxIncomePersonalStr):null;
		String netSalaryStr = request.getParameter(PARA_netSalary);
		BigDecimal netSalary = !StringHelper.isNullOrEmpty(netSalaryStr)?new BigDecimal(netSalaryStr):null;
		String netTargetYearmonthStr = request.getParameter(PARA_netTargetYearmonth);
		Date netTargetYearmonth = !StringHelper.isNullOrEmpty(netTargetYearmonthStr)?DateTimeHelper.strToDate(netTargetYearmonthStr, "yyyy-MM"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String btimpNo = request.getParameter(PARA_btimpNo);
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		//????????????
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		
		String positCns = request.getParameter(PARA_positCns);
		List<String> positList = !StringHelper.isNullOrEmpty(positCns)?Arrays.asList(positCns.split(",")):null;
		String titleCns = request.getParameter(PARA_titleCns);
		List<String> titleList = !StringHelper.isNullOrEmpty(titleCns)?Arrays.asList(titleCns.split(",")):null;
		String hosBranchs = request.getParameter(PARA_hosBranchCns);
		List<String> branchList = !StringHelper.isNullOrEmpty(hosBranchs)?Arrays.asList(hosBranchs.split(",")):null;
		String hosDepart1levelCns = request.getParameter(PARA_hosDepart1levelCns);
		List<String> departLevel1s = !StringHelper.isNullOrEmpty(hosDepart1levelCns)?Arrays.asList(hosDepart1levelCns.split(",")):null;
		String hosDepart2levelCns = request.getParameter(PARA_hosDepart2levelCns);
		List<String> departLevel2s = !StringHelper.isNullOrEmpty(hosDepart2levelCns)?Arrays.asList(hosDepart2levelCns.split(",")):null;
		String wkModalityCns = request.getParameter(PARA_wkModalityCns);
		List<String> wkmodalitys = !StringHelper.isNullOrEmpty(wkModalityCns)?Arrays.asList(wkModalityCns.split(",")):null;
		
		String departClassCns = request.getParameter(PARA_departClassCns);
		List<String> departClassList = !StringHelper.isNullOrEmpty(departClassCns)?Arrays.asList(departClassCns.split(",")):null;
		String departClassPops = request.getParameter(PARA_departClassPops);
		List<String> departClassPopList = !StringHelper.isNullOrEmpty(departClassPops)?Arrays.asList(departClassPops.split(",")):null;
		String stationCns = request.getParameter(PARA_stationCns);
		List<String> stationList = !StringHelper.isNullOrEmpty(stationCns)?Arrays.asList(stationCns.split(",")):null;
		String stationTypeCns = request.getParameter(PARA_stationTypeCns);
		List<String> stationTypeList = !StringHelper.isNullOrEmpty(stationTypeCns)?Arrays.asList(stationTypeCns.split(",")):null;
		String stationStatusCns = request.getParameter(PARA_stationStatusCns);
		List<String> stationStatusList = !StringHelper.isNullOrEmpty(stationStatusCns)?Arrays.asList(stationStatusCns.split(",")):null;
		String stationSeqCns = request.getParameter(PARA_stationSeqCns);
		List<String> stationSeqList = !StringHelper.isNullOrEmpty(stationSeqCns)?Arrays.asList(stationSeqCns.split(",")):null;
		String titleClassCns = request.getParameter(PARA_titleClassCns);
		List<String> titleClassList = !StringHelper.isNullOrEmpty(titleClassCns)?Arrays.asList(titleClassCns.split(",")):null;
		String skillsLevelCns = request.getParameter(PARA_skillsLevelCns);
		List<String> skillsLevelList = !StringHelper.isNullOrEmpty(skillsLevelCns)?Arrays.asList(skillsLevelCns.split(",")):null;
		String eduLev4nowCns = request.getParameter(PARA_eduLev4nowCns);
		List<String> eduLev4nowList = !StringHelper.isNullOrEmpty(eduLev4nowCns)?Arrays.asList(eduLev4nowCns.split(",")):null;
		String saSumProjects = request.getParameter(PARA_saSumProjects);
		List<String> saSumProjectList = !StringHelper.isNullOrEmpty(saSumProjects)?Arrays.asList(saSumProjects.split(",")):null;
		String rptWkDepClasss = request.getParameter(PARA_rptWkDepClasss);
		List<String> rptWkDepClassList = !StringHelper.isNullOrEmpty(rptWkDepClasss)?Arrays.asList(rptWkDepClasss.split(",")):null;
		
		ChSaPayslipExtendModel model = new ChSaPayslipExtendModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBankCardNo(null!=bankCardNo && !"".equals(bankCardNo.trim())?bankCardNo.trim():null);
		model.setMobileNo(null!=mobileNo && !"".equals(mobileNo.trim())?mobileNo.trim():null);
		model.setWkModality(null!=wkModality?wkModality:null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setPosit(null!=posit?posit:null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setTitle(null!=title?title:null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setHosBranch(null!=hosBranch?hosBranch:null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1level(null!=hosDepart1level?hosDepart1level:null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2level(null!=hosDepart2level?hosDepart2level:null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setDepartClassCn(null!=departClassCn && !"".equals(departClassCn.trim())?departClassCn.trim():null);
		model.setDepartClassPop(null!=departClassPop && !"".equals(departClassPop.trim())?departClassPop.trim():null);
		model.setStationCn(null!=stationCn && !"".equals(stationCn.trim())?stationCn.trim():null);
		model.setStationTypeCn(null!=stationTypeCn && !"".equals(stationTypeCn.trim())?stationTypeCn.trim():null);
		model.setStationStatusCn(null!=stationStatusCn && !"".equals(stationStatusCn.trim())?stationStatusCn.trim():null);
		model.setStationSeqCn(null!=stationSeqCn && !"".equals(stationSeqCn.trim())?stationSeqCn.trim():null);
		model.setTitleClassCn(null!=titleClassCn && !"".equals(titleClassCn.trim())?titleClassCn.trim():null);
		model.setSkillsLevelCn(null!=skillsLevelCn && !"".equals(skillsLevelCn.trim())?skillsLevelCn.trim():null);
		model.setEduLev4nowCn(null!=eduLev4nowCn && !"".equals(eduLev4nowCn.trim())?eduLev4nowCn.trim():null);
		model.setYearlySalaryMan(null!=yearlySalaryMan && !"".equals(yearlySalaryMan.trim())?yearlySalaryMan.trim():null);
		model.setSaSumProject(null!=saSumProject && !"".equals(saSumProject.trim())?saSumProject.trim():null);
		model.setRptWkDepClass(null!=rptWkDepClass && !"".equals(rptWkDepClass.trim())?rptWkDepClass.trim():null);
		model.setManwkStandard(null!=manwkStandard?manwkStandard:null);
		model.setManwkAttend(null!=manwkAttend?manwkAttend:null);
		model.setManwkMiner(null!=manwkMiner?manwkMiner:null);
		model.setManwkQuit(null!=manwkQuit?manwkQuit:null);
		model.setManwkSick(null!=manwkSick?manwkSick:null);
		model.setManwkMaternity(null!=manwkMaternity?manwkMaternity:null);
		model.setManwkPrivpassion(null!=manwkPrivpassion?manwkPrivpassion:null);
		model.setManwkOvertime(null!=manwkOvertime?manwkOvertime:null);
		model.setWageStandard4posit(null!=wageStandard4posit?wageStandard4posit:null);
		model.setWageDay4posit(null!=wageDay4posit?wageDay4posit:null);
		model.setWageAttend(null!=wageAttend?wageAttend:null);
		model.setWageSick(null!=wageSick?wageSick:null);
		model.setWagePositTotal(null!=wagePositTotal?wagePositTotal:null);
		model.setWageNightShift(null!=wageNightShift?wageNightShift:null);
		model.setWageOvertime(null!=wageOvertime?wageOvertime:null);
		model.setWageYearg(null!=wageYearg?wageYearg:null);
		model.setAllowanceHygiene(null!=allowanceHygiene?allowanceHygiene:null);
		model.setWageErrorCorrent(null!=wageErrorCorrent?wageErrorCorrent:null);
		model.setSupp4tel(null!=supp4tel?supp4tel:null);
		model.setSupp4traffic(null!=supp4traffic?supp4traffic:null);
		model.setSupp4mining(null!=supp4mining?supp4mining:null);
		model.setSupp4other(null!=supp4other?supp4other:null);
		model.setSupp4univeStuLife(null!=supp4univeStuLife?supp4univeStuLife:null);
		model.setSupp4oth(null!=supp4oth?supp4oth:null);
		model.setSuppTotal(null!=suppTotal?suppTotal:null);
		model.setWageTotal(null!=wageTotal?wageTotal:null);
		model.setWageJxTotal(null!=wageJxTotal?wageJxTotal:null);
		model.setWagePayableTotal(null!=wagePayableTotal?wagePayableTotal:null);
		model.setBxPension(null!=bxPension?bxPension:null);
		model.setBxMedical(null!=bxMedical?bxMedical:null);
		model.setBxUnemploy(null!=bxUnemploy?bxUnemploy:null);
		model.setBxSeriousIllness(null!=bxSeriousIllness?bxSeriousIllness:null);
		model.setBxHousfund(null!=bxHousfund?bxHousfund:null);
		model.setBxAnnualCorpGold(null!=bxAnnualCorpGold?bxAnnualCorpGold:null);
		model.setBxTotal(null!=bxTotal?bxTotal:null);
		model.setCutWater2elect(null!=cutWater2elect?cutWater2elect:null);
		model.setCutHygiene(null!=cutHygiene?cutHygiene:null);
		model.setCutOther(null!=cutOther?cutOther:null);
		model.setCutArrears(null!=cutArrears?cutArrears:null);
		model.setCutTotal(null!=cutTotal?cutTotal:null);
		model.setTaxableWage(null!=taxableWage?taxableWage:null);
		model.setTaxIncomePersonal(null!=taxIncomePersonal?taxIncomePersonal:null);
		model.setNetSalary(null!=netSalary?netSalary:null);
		model.setNetTargetYearmonth(null!=netTargetYearmonth?DateTimeHelper.format(netTargetYearmonth, "yyyy-MM"):null);
		model.setStatus(null!=status?status:null);
		model.setBtimpNo(null!=btimpNo && !"".equals(btimpNo.trim())?btimpNo.trim():null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
		
		//??????????????????
		if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
		{
			startMonth = chSaPayslipRewardBiz.queryLastMonth();
			endMonth = startMonth;
		}
		model.setStartMonth(StringHelper.isNullOrEmpty(startMonth)?null:startMonth);
		model.setEndMonth(StringHelper.isNullOrEmpty(endMonth)?null:endMonth);
		model.setPositCns(positList);
		model.setTitleCns(titleList);
		model.setHosBranchCns(branchList);
		model.setHosDepart1levelCns(departLevel1s);
		model.setHosDepart2levelCns(departLevel2s);
		model.setWkModalityCns(wkmodalitys);
        model.setDepartClassCns(departClassList);
        model.setDepartClassPops(departClassPopList);
        model.setStationCns(stationList);
        model.setStationSeqCns(stationSeqList);
        model.setStationTypeCns(stationTypeList);
        model.setStationStatusCns(stationStatusList);
        model.setTitleClassCns(titleClassList);
        model.setSkillsLevelCns(skillsLevelList);
        model.setEduLev4nowCns(eduLev4nowList);
        model.setSaSumProjects(saSumProjectList);
        model.setRptWkDepClasss(rptWkDepClassList);
        
        //???????????????
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[??????-???????????????]</p>
	 * @param request????????????
	 * @param type  0:?????? 1?????????
	 * @return Map key???true/false  val???
	 * Created on 2021???05???14???								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChSaPayslipModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//??????????????????
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		String name = request.getParameter(PARA_name);
		String cardNo = request.getParameter(PARA_cardNo);
		String bankCardNo = request.getParameter(PARA_bankCardNo);
		String mobileNo = request.getParameter(PARA_mobileNo);
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String wagesId = request.getParameter(PARA_wagesId);
		String positStr = request.getParameter(PARA_posit);
		Integer posit = !StringHelper.isNullOrEmpty(positStr)?Integer.parseInt(positStr):null;
		String positCn = request.getParameter(PARA_positCn);
		String titleStr = request.getParameter(PARA_title);
		Integer title = !StringHelper.isNullOrEmpty(titleStr)?Integer.parseInt(titleStr):null;
		String titleCn = request.getParameter(PARA_titleCn);
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelStr = request.getParameter(PARA_hosDepart2level);
		Integer hosDepart2level = !StringHelper.isNullOrEmpty(hosDepart2levelStr)?Integer.parseInt(hosDepart2levelStr):null;
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String departClassCn = request.getParameter(PARA_departClassCn);
		String departClassPop = request.getParameter(PARA_departClassPop);
		String stationCn = request.getParameter(PARA_stationCn);
		String stationTypeCn = request.getParameter(PARA_stationTypeCn);
		String stationStatusCn = request.getParameter(PARA_stationStatusCn);
		String stationSeqCn = request.getParameter(PARA_stationSeqCn);
		String titleClassCn = request.getParameter(PARA_titleClassCn);
		String skillsLevelCn = request.getParameter(PARA_skillsLevelCn);
		String eduLev4nowCn = request.getParameter(PARA_eduLev4nowCn);
		String yearlySalaryMan = request.getParameter(PARA_yearlySalaryMan);
		String saSumProject = request.getParameter(PARA_saSumProject);
		String rptWkDepClass = request.getParameter(PARA_rptWkDepClass);
		String manwkStandardStr = request.getParameter(PARA_manwkStandard);
		BigDecimal manwkStandard = !StringHelper.isNullOrEmpty(manwkStandardStr)?new BigDecimal(manwkStandardStr):null;
		String manwkAttendStr = request.getParameter(PARA_manwkAttend);
		BigDecimal manwkAttend = !StringHelper.isNullOrEmpty(manwkAttendStr)?new BigDecimal(manwkAttendStr):null;
		String manwkMinerStr = request.getParameter(PARA_manwkMiner);
		BigDecimal manwkMiner = !StringHelper.isNullOrEmpty(manwkMinerStr)?new BigDecimal(manwkMinerStr):null;
		String manwkQuitStr = request.getParameter(PARA_manwkQuit);
		BigDecimal manwkQuit = !StringHelper.isNullOrEmpty(manwkQuitStr)?new BigDecimal(manwkQuitStr):null;
		String manwkSickStr = request.getParameter(PARA_manwkSick);
		BigDecimal manwkSick = !StringHelper.isNullOrEmpty(manwkSickStr)?new BigDecimal(manwkSickStr):null;
		String manwkMaternityStr = request.getParameter(PARA_manwkMaternity);
		BigDecimal manwkMaternity = !StringHelper.isNullOrEmpty(manwkMaternityStr)?new BigDecimal(manwkMaternityStr):null;
		String manwkPrivpassionStr = request.getParameter(PARA_manwkPrivpassion);
		BigDecimal manwkPrivpassion = !StringHelper.isNullOrEmpty(manwkPrivpassionStr)?new BigDecimal(manwkPrivpassionStr):null;
		String manwkOvertimeStr = request.getParameter(PARA_manwkOvertime);
		BigDecimal manwkOvertime = !StringHelper.isNullOrEmpty(manwkOvertimeStr)?new BigDecimal(manwkOvertimeStr):null;
		String wageStandard4positStr = request.getParameter(PARA_wageStandard4posit);
		BigDecimal wageStandard4posit = !StringHelper.isNullOrEmpty(wageStandard4positStr)?new BigDecimal(wageStandard4positStr):null;
		String wageDay4positStr = request.getParameter(PARA_wageDay4posit);
		BigDecimal wageDay4posit = !StringHelper.isNullOrEmpty(wageDay4positStr)?new BigDecimal(wageDay4positStr):null;
		String wageAttendStr = request.getParameter(PARA_wageAttend);
		BigDecimal wageAttend = !StringHelper.isNullOrEmpty(wageAttendStr)?new BigDecimal(wageAttendStr):null;
		String wageSickStr = request.getParameter(PARA_wageSick);
		BigDecimal wageSick = !StringHelper.isNullOrEmpty(wageSickStr)?new BigDecimal(wageSickStr):null;
		String wagePositTotalStr = request.getParameter(PARA_wagePositTotal);
		BigDecimal wagePositTotal = !StringHelper.isNullOrEmpty(wagePositTotalStr)?new BigDecimal(wagePositTotalStr):null;
		String wageNightShiftStr = request.getParameter(PARA_wageNightShift);
		BigDecimal wageNightShift = !StringHelper.isNullOrEmpty(wageNightShiftStr)?new BigDecimal(wageNightShiftStr):null;
		String wageOvertimeStr = request.getParameter(PARA_wageOvertime);
		BigDecimal wageOvertime = !StringHelper.isNullOrEmpty(wageOvertimeStr)?new BigDecimal(wageOvertimeStr):null;
		String wageYeargStr = request.getParameter(PARA_wageYearg);
		BigDecimal wageYearg = !StringHelper.isNullOrEmpty(wageYeargStr)?new BigDecimal(wageYeargStr):null;
		String allowanceHygieneStr = request.getParameter(PARA_allowanceHygiene);
		BigDecimal allowanceHygiene = !StringHelper.isNullOrEmpty(allowanceHygieneStr)?new BigDecimal(allowanceHygieneStr):null;
		String wageErrorCorrentStr = request.getParameter(PARA_wageErrorCorrent);
		BigDecimal wageErrorCorrent = !StringHelper.isNullOrEmpty(wageErrorCorrentStr)?new BigDecimal(wageErrorCorrentStr):null;
		String supp4telStr = request.getParameter(PARA_supp4tel);
		BigDecimal supp4tel = !StringHelper.isNullOrEmpty(supp4telStr)?new BigDecimal(supp4telStr):null;
		String supp4trafficStr = request.getParameter(PARA_supp4traffic);
		BigDecimal supp4traffic = !StringHelper.isNullOrEmpty(supp4trafficStr)?new BigDecimal(supp4trafficStr):null;
		String supp4miningStr = request.getParameter(PARA_supp4mining);
		BigDecimal supp4mining = !StringHelper.isNullOrEmpty(supp4miningStr)?new BigDecimal(supp4miningStr):null;
		String supp4otherStr = request.getParameter(PARA_supp4other);
		BigDecimal supp4other = !StringHelper.isNullOrEmpty(supp4otherStr)?new BigDecimal(supp4otherStr):null;
		String supp4univeStuLifeStr = request.getParameter(PARA_supp4univeStuLife);
		BigDecimal supp4univeStuLife = !StringHelper.isNullOrEmpty(supp4univeStuLifeStr)?new BigDecimal(supp4univeStuLifeStr):null;
		String supp4othStr = request.getParameter(PARA_supp4oth);
		BigDecimal supp4oth = !StringHelper.isNullOrEmpty(supp4othStr)?new BigDecimal(supp4othStr):null;
		String suppTotalStr = request.getParameter(PARA_suppTotal);
		BigDecimal suppTotal = !StringHelper.isNullOrEmpty(suppTotalStr)?new BigDecimal(suppTotalStr):null;
		String wageTotalStr = request.getParameter(PARA_wageTotal);
		BigDecimal wageTotal = !StringHelper.isNullOrEmpty(wageTotalStr)?new BigDecimal(wageTotalStr):null;
		String wageJxTotalStr = request.getParameter(PARA_wageJxTotal);
		BigDecimal wageJxTotal = !StringHelper.isNullOrEmpty(wageJxTotalStr)?new BigDecimal(wageJxTotalStr):null;
		String wagePayableTotalStr = request.getParameter(PARA_wagePayableTotal);
		BigDecimal wagePayableTotal = !StringHelper.isNullOrEmpty(wagePayableTotalStr)?new BigDecimal(wagePayableTotalStr):null;
		String bxPensionStr = request.getParameter(PARA_bxPension);
		BigDecimal bxPension = !StringHelper.isNullOrEmpty(bxPensionStr)?new BigDecimal(bxPensionStr):null;
		String bxMedicalStr = request.getParameter(PARA_bxMedical);
		BigDecimal bxMedical = !StringHelper.isNullOrEmpty(bxMedicalStr)?new BigDecimal(bxMedicalStr):null;
		String bxUnemployStr = request.getParameter(PARA_bxUnemploy);
		BigDecimal bxUnemploy = !StringHelper.isNullOrEmpty(bxUnemployStr)?new BigDecimal(bxUnemployStr):null;
		String bxSeriousIllnessStr = request.getParameter(PARA_bxSeriousIllness);
		BigDecimal bxSeriousIllness = !StringHelper.isNullOrEmpty(bxSeriousIllnessStr)?new BigDecimal(bxSeriousIllnessStr):null;
		String bxHousfundStr = request.getParameter(PARA_bxHousfund);
		BigDecimal bxHousfund = !StringHelper.isNullOrEmpty(bxHousfundStr)?new BigDecimal(bxHousfundStr):null;
		String bxAnnualCorpGoldStr = request.getParameter(PARA_bxAnnualCorpGold);
		BigDecimal bxAnnualCorpGold = !StringHelper.isNullOrEmpty(bxAnnualCorpGoldStr)?new BigDecimal(bxAnnualCorpGoldStr):null;
		String bxTotalStr = request.getParameter(PARA_bxTotal);
		BigDecimal bxTotal = !StringHelper.isNullOrEmpty(bxTotalStr)?new BigDecimal(bxTotalStr):null;
		String cutWater2electStr = request.getParameter(PARA_cutWater2elect);
		BigDecimal cutWater2elect = !StringHelper.isNullOrEmpty(cutWater2electStr)?new BigDecimal(cutWater2electStr):null;
		String cutHygieneStr = request.getParameter(PARA_cutHygiene);
		BigDecimal cutHygiene = !StringHelper.isNullOrEmpty(cutHygieneStr)?new BigDecimal(cutHygieneStr):null;
		String cutOtherStr = request.getParameter(PARA_cutOther);
		BigDecimal cutOther = !StringHelper.isNullOrEmpty(cutOtherStr)?new BigDecimal(cutOtherStr):null;
		String cutArrearsStr = request.getParameter(PARA_cutArrears);
		BigDecimal cutArrears = !StringHelper.isNullOrEmpty(cutArrearsStr)?new BigDecimal(cutArrearsStr):null;
		String cutTotalStr = request.getParameter(PARA_cutTotal);
		BigDecimal cutTotal = !StringHelper.isNullOrEmpty(cutTotalStr)?new BigDecimal(cutTotalStr):null;
		String taxableWageStr = request.getParameter(PARA_taxableWage);
		BigDecimal taxableWage = !StringHelper.isNullOrEmpty(taxableWageStr)?new BigDecimal(taxableWageStr):null;
		String taxIncomePersonalStr = request.getParameter(PARA_taxIncomePersonal);
		BigDecimal taxIncomePersonal = !StringHelper.isNullOrEmpty(taxIncomePersonalStr)?new BigDecimal(taxIncomePersonalStr):null;
		String netSalaryStr = request.getParameter(PARA_netSalary);
		BigDecimal netSalary = !StringHelper.isNullOrEmpty(netSalaryStr)?new BigDecimal(netSalaryStr):null;
		String netTargetYearmonthStr = request.getParameter(PARA_netTargetYearmonth);
		Date netTargetYearmonth = !StringHelper.isNullOrEmpty(netTargetYearmonthStr)?DateTimeHelper.strToDate(netTargetYearmonthStr, "yyyy-MM"):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:null;
		String btimpNo = request.getParameter(PARA_btimpNo);
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setBankCardNo(null!=bankCardNo && !"".equals(bankCardNo.trim())?bankCardNo.trim():null);
		model.setMobileNo(null!=mobileNo && !"".equals(mobileNo.trim())?mobileNo.trim():null);
		model.setWkModality(null!=wkModality?wkModality:null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setPosit(null!=posit?posit:null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setTitle(null!=title?title:null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setHosBranch(null!=hosBranch?hosBranch:null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1level(null!=hosDepart1level?hosDepart1level:null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2level(null!=hosDepart2level?hosDepart2level:null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setDepartClassCn(null!=departClassCn && !"".equals(departClassCn.trim())?departClassCn.trim():null);
		model.setDepartClassPop(null!=departClassPop && !"".equals(departClassPop.trim())?departClassPop.trim():null);
		model.setStationCn(null!=stationCn && !"".equals(stationCn.trim())?stationCn.trim():null);
		model.setStationTypeCn(null!=stationTypeCn && !"".equals(stationTypeCn.trim())?stationTypeCn.trim():null);
		model.setStationStatusCn(null!=stationStatusCn && !"".equals(stationStatusCn.trim())?stationStatusCn.trim():null);
		model.setStationSeqCn(null!=stationSeqCn && !"".equals(stationSeqCn.trim())?stationSeqCn.trim():null);
		model.setTitleClassCn(null!=titleClassCn && !"".equals(titleClassCn.trim())?titleClassCn.trim():null);
		model.setSkillsLevelCn(null!=skillsLevelCn && !"".equals(skillsLevelCn.trim())?skillsLevelCn.trim():null);
		model.setEduLev4nowCn(null!=eduLev4nowCn && !"".equals(eduLev4nowCn.trim())?eduLev4nowCn.trim():null);
		model.setYearlySalaryMan(null!=yearlySalaryMan && !"".equals(yearlySalaryMan.trim())?yearlySalaryMan.trim():null);
		model.setSaSumProject(null!=saSumProject && !"".equals(saSumProject.trim())?saSumProject.trim():null);
		model.setRptWkDepClass(null!=rptWkDepClass && !"".equals(rptWkDepClass.trim())?rptWkDepClass.trim():null);
		model.setManwkStandard(null!=manwkStandard?manwkStandard:null);
		model.setManwkAttend(null!=manwkAttend?manwkAttend:null);
		model.setManwkMiner(null!=manwkMiner?manwkMiner:null);
		model.setManwkQuit(null!=manwkQuit?manwkQuit:null);
		model.setManwkSick(null!=manwkSick?manwkSick:null);
		model.setManwkMaternity(null!=manwkMaternity?manwkMaternity:null);
		model.setManwkPrivpassion(null!=manwkPrivpassion?manwkPrivpassion:null);
		model.setManwkOvertime(null!=manwkOvertime?manwkOvertime:null);
		model.setWageStandard4posit(null!=wageStandard4posit?wageStandard4posit:null);
		model.setWageDay4posit(null!=wageDay4posit?wageDay4posit:null);
		model.setWageAttend(null!=wageAttend?wageAttend:null);
		model.setWageSick(null!=wageSick?wageSick:null);
		model.setWagePositTotal(null!=wagePositTotal?wagePositTotal:null);
		model.setWageNightShift(null!=wageNightShift?wageNightShift:null);
		model.setWageOvertime(null!=wageOvertime?wageOvertime:null);
		model.setWageYearg(null!=wageYearg?wageYearg:null);
		model.setAllowanceHygiene(null!=allowanceHygiene?allowanceHygiene:null);
		model.setWageErrorCorrent(null!=wageErrorCorrent?wageErrorCorrent:null);
		model.setSupp4tel(null!=supp4tel?supp4tel:null);
		model.setSupp4traffic(null!=supp4traffic?supp4traffic:null);
		model.setSupp4mining(null!=supp4mining?supp4mining:null);
		model.setSupp4other(null!=supp4other?supp4other:null);
		model.setSupp4univeStuLife(null!=supp4univeStuLife?supp4univeStuLife:null);
		model.setSupp4oth(null!=supp4oth?supp4oth:null);
		model.setSuppTotal(null!=suppTotal?suppTotal:null);
		model.setWageTotal(null!=wageTotal?wageTotal:null);
		model.setWageJxTotal(null!=wageJxTotal?wageJxTotal:null);
		model.setWagePayableTotal(null!=wagePayableTotal?wagePayableTotal:null);
		model.setBxPension(null!=bxPension?bxPension:null);
		model.setBxMedical(null!=bxMedical?bxMedical:null);
		model.setBxUnemploy(null!=bxUnemploy?bxUnemploy:null);
		model.setBxSeriousIllness(null!=bxSeriousIllness?bxSeriousIllness:null);
		model.setBxHousfund(null!=bxHousfund?bxHousfund:null);
		model.setBxAnnualCorpGold(null!=bxAnnualCorpGold?bxAnnualCorpGold:null);
		model.setBxTotal(null!=bxTotal?bxTotal:null);
		model.setCutWater2elect(null!=cutWater2elect?cutWater2elect:null);
		model.setCutHygiene(null!=cutHygiene?cutHygiene:null);
		model.setCutOther(null!=cutOther?cutOther:null);
		model.setCutArrears(null!=cutArrears?cutArrears:null);
		model.setCutTotal(null!=cutTotal?cutTotal:null);
		model.setTaxableWage(null!=taxableWage?taxableWage:null);
		model.setTaxIncomePersonal(null!=taxIncomePersonal?taxIncomePersonal:null);
		model.setNetSalary(null!=netSalary?netSalary:null);
		model.setNetTargetYearmonth(null!=netTargetYearmonth?DateTimeHelper.format(netTargetYearmonth, "yyyy-MM"):null);
		model.setStatus(null!=status?status:null);
		model.setBtimpNo(null!=btimpNo && !"".equals(btimpNo.trim())?btimpNo.trim():null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
	}
	
	/**
	 * ??????????????????
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//??????????????????
		//fillCommonDict(modelMap);
		//loadCachedDict4Payslip(modelMap);
	}
	
	/**
	 * ????????????
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChSaPayslipModel model = new ChSaPayslipModel();
			//????????????
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//??????
			chSaPayslipBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"??????:" + model+",??????:"+model);
			//??????????????????
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ??????????????????
	 * @param modelMap
	 */
	@RequestMapping(value="revoke.jhtm")
	public void revoke(ModelMap modelMap) throws Exception{
	}
	
	/**
	 * ????????????
	 * @param modelMap
	 */
	@RequestMapping(value="revoke/do.jhtm")
	@ResponseBody
	public Map<String, Object> doRevoke(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			String btImpNo = request.getParameter("btimpNo");
			if (StringHelper.isNullOrEmpty(btImpNo)) 
				return LayAjaxHelper.fail("??????????????????!");
			//??????
			chSaPayslipBiz.delBatchByBtImpNo(btImpNo);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"????????????????????????:" + btImpNo+",??????:?????????????????????");
			//??????????????????
			map =  LayAjaxHelper.success("????????????!");
		} catch (Exception ex) {
			log2Error(request, Module_Name, "????????????",ex.getMessage());
			map = LayAjaxHelper.fail("????????????!");
			ex.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) throw new Exception("??????????????????????????????!");
		try {
			//??????????????????
			//fillCommonDict(modelMap);
			
			ChSaPayslipModel model = chSaPayslipBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("????????????-??????????????????error:",e);
		}
	}
	
	/**
	 *  ????????????
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
			ChSaPayslipModel model = chSaPayslipBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chSaPayslipBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"??????:" + model.getId()+",??????:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//????????????
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,@RequestParam("id")Long id) {
		Map<String, Object> map = null;
		try {
			if(null == id) return LayAjaxHelper.fail("????????????????????????!");
			ChSaPayslipModel model = chSaPayslipBiz.queryById(id);
			if(model != null)
			{
				chSaPayslipBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"??????:" + model+",??????:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete, ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ????????????
	 * @param request
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("???????????????!");
			chSaPayslipBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"??????:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * ??????????????????
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) throws Exception{
		ChSaPayslipModel model = null;
		try {
			//??????????????????
			fillCommonDict(modelMap);
			//??????????????????
			model = chSaPayslipBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"??????:" + model+",??????:" + model);
		}catch(Exception e)
		{
			logger.error("????????????-??????????????????error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"??????:" + model+",??????:" + model);
		}
		
	}
	//********************?????????????????????***********************
	
	/**
	 * ???????????????????????????Map
	 * @param id
	 * @param modelMap
	 */
	private void fillCommonDict(ModelMap modelMap) throws Exception
	{
		//??????????????????
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
	
	
	@RequestMapping(value = "export.jhtm")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response) {
		//????????????
		Page.Builder<ChSaPayslipExtendModel> builder = new Page.Builder<ChSaPayslipExtendModel>();
		try {
			setSearchParameters(request, builder);
			Page<ChSaPayslipExtendModel> page = builder.build();
			//?????????????????????????????????????????????
			List<String> rewards = chSaPayslipRewardBiz.queryRewardList(
					page.getModel().getStartMonth(), page.getModel().getEndMonth());
			//???????????????????????????excel???header
			JSONObject headJson = PayslipExportHeadUtil.createHead(rewards);
			if(null == headJson || headJson.isEmpty()) throw new Exception("??????????????????");
			List<ChSaPayslipModel> data = chSaPayslipBiz.queryListWithSerial(page.getModel());
			
			//???????????????JSON??????
			JSONObject jsonItem = null;
			ChSaPayslipModel item = null;
			List<ChSaPayslipRewardModel> rlist = null;
			List<String> rtlist = null;
			List<JSONObject> retlist = new ArrayList<JSONObject>();
			for(int i=0;i<data.size();i++)
			{
				item = data.get(i);
				jsonItem = new JSONObject();
				jsonItem = JSONObject.parseObject(JSONObject.toJSONString(item,SerializerFeature.WriteMapNullValue));
				rlist = chSaPayslipRewardBiz.queryList(item.getCardNo(), item.getNetTargetYearmonth());
				rtlist = new ArrayList<String>();
				if(null == rlist || rlist.size()<=0) continue;
				for(ChSaPayslipRewardModel sub:rlist)
				{
					jsonItem.put(sub.getRewardItem(),sub.getRewardAmount());
					rtlist.add(sub.getRewardItem());
				}
				//?????????????????????
				List<String> tlist = new ArrayList<String>();
				tlist.addAll(rewards);
				tlist.removeAll(rtlist);
				for(String rew:tlist)
				{
					jsonItem.put(rew, "");
				};
				retlist.add(jsonItem);
			}
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "???????????????", retlist, headInfo);
		}catch(Exception e)
		{
			logger.error("??????????????????error:",e);
		}
	}
	/**
	 * ????????????
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "doImport.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> doImport(HttpServletRequest request,@RequestParam MultipartFile[] file) {
		Map<String, Object> map = null;
		InputStream inputs = null;
		try {
			//?????????????????????
			UserInfoModel loginUser = this.getLoginUser(request);
			if(null == loginUser) return LayAjaxHelper.fail("?????????????????????????????????????????????!");
			String netTargetYearmonthStr = request.getParameter(PARA_netTargetYearmonth);
			Date netTargetYearmonthDate = !StringHelper.isNullOrEmpty(netTargetYearmonthStr)?DateTimeHelper.strToDate(netTargetYearmonthStr, "yyyy-MM"):null;
			String netTargetYearmonth = null!=netTargetYearmonthDate?DateTimeHelper.format(netTargetYearmonthDate, "yyyy-MM"):null;
			if(null == netTargetYearmonth)
				return LayAjaxHelper.fail("?????????????????????!");
			String type = null;
			String fileName = null;
			
			String batchNo = "BSAP_"+System.currentTimeMillis()+(int)(Math.random()*10);
			//?????????????????????????????????
			Map<Boolean,Object> data = null;
        	for (MultipartFile item : file) {
        		fileName = item.getOriginalFilename();
        		type = null != fileName?fileName.substring(fileName.indexOf("."), fileName.length()):null;
        		//filePath = fileRoot+model.getBatchNo()+type;
        		if(!type.contains("xls") && !type.contains("xlsx"))
        			break;
        		inputs = item.getInputStream();
        		data = chSaPayslipBiz.doImport(inputs,netTargetYearmonth,batchNo);
        	}
        	
        	//???????????????????????????
        	ChSaPayslipImprecordModel impRecordModel = new ChSaPayslipImprecordModel();
        	impRecordModel.setBatchNo(batchNo);
        	impRecordModel.setBatchType("?????????");
        	
        	impRecordModel.setBatchUserAccount(loginUser.getAccount());
        	impRecordModel.setBatchUser(loginUser.getUserRealName());
        	
			log2Info(request, Module_Name, LogDictionary.Module_Oper_import,"??????:"+impRecordModel.getBatchNo() +",??????:?????????");
			//??????????????????
			StringBuffer msg = new StringBuffer();
			msg.append("??????"+(data.containsKey(true)?"??????!":"??????!"));
			if(data.containsKey(true)) {
				Map<String,Integer> dataMap = (Map<String,Integer>)data.get(true);
				msg.append("</br>[?????????"+dataMap.get("succ")+" ??????:"+dataMap.get("fail")
				+" ?????????:"+dataMap.get("exist")+(dataMap.containsKey("excep")?" ??????:"+dataMap.get("excep"):"")
				+"  ??????:"+dataMap.get("allSize")+"]");
				impRecordModel.setSucNum(Long.valueOf(dataMap.get("succ")));
				impRecordModel.setFaiNum(Long.valueOf(dataMap.get("fail")));
				impRecordModel.setExeNum(Long.valueOf(dataMap.get("excep")));
				impRecordModel.setExistNum(Long.valueOf(dataMap.get("exist")));
			}else {
				msg.append("</br>["+data.get(false)+"]");
			}
			impRecordModel.setBatchMsg(msg.toString());
			chSaPayslipImprecordBiz.save(impRecordModel);
				
			map =  data.containsKey(true)?LayAjaxHelper.success(msg.toString())
					:LayAjaxHelper.fail(msg.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("???????????????error:",ex);
		}finally {
			if(null != inputs)
				try {
					inputs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}	
		return map;
	}
	
	
	/**
	 * ??????????????????
	 * @param modelMap
	 */
	@RequestMapping(value="impPaySlipCurMonth.jhtm")
	public void impPaySlip(ModelMap modelMap) throws Exception{
		
	}
	

	private JSONArray packRewardHead(String startMonth,String endMonth)
	{
		//???????????????????????????????????????????????????
		List<String> rewards = chSaPayslipRewardBiz.queryRewardList(startMonth, endMonth);
		String tabHeadTmp1 = 
		"[["+
   	  		"{field: '', fixed: 'left',title:'??????',type:'numbers',align: 'center',rowspan:3},"+
   	  		"{field: 'wagesId', title: '????????????' ,fixed: 'left',sort: true,width:'8%',align: 'center',rowspan:3}, "+
   	  		"{field: 'name', title: '????????????' ,fixed: 'left',sort: true,width:'8%',align: 'center',rowspan:3}, "+
   	  		"{field: 'cardNo', title: '????????????' ,fixed: 'left',width:'14%',align: 'center',rowspan:3},"+
   	  		"{field: 'bankCardNo', title: '????????????' ,width:'8%',align: 'center',rowspan:3},"+
   	  		"{field: 'mobileNo', title: '?????????' ,width:'10%',align: 'center',rowspan:3},"+
   	  		"{field: 'positCn', title: '??????' ,width:'8%',align: 'center',rowspan:3},"+
   	  		"{field: 'titleCn', title: '??????' ,width:'8%',rowspan:3},"+
   	  		"{field: 'hosBranchCn', title: '??????' ,width:'8%',rowspan:3},"+
   	  		"{field: 'hosDepart1levelCn', title: '????????????' ,width:'8%',rowspan:3},"+
   	  		"{field: 'hosDepart2levelCn', title: '????????????' ,width:'8%',rowspan:3},"+
   	  		"{field: 'wkModalityCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'departClassCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'departClassPop', title: '??????????????????' ,align: 'center',width:'10%',rowspan:3},"+
   	  		"{field: 'stationCn', title: '??????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'stationTypeCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'stationStatusCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'stationSeqCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'titleClassCn', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'skillsLevelCn', title: '??????????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'eduLev4nowCn', title: '?????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'yearlySalaryMan', title: '???????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'saSumProject', title: '?????????????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'rptWkDepClass', title: '????????????????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: '', title: '??????' ,align: 'center',colspan:7},"+
   	  		"{field: '', title: '????????????' ,align: 'center',colspan:"+(21+rewards.size())+"},"+
   	  		"{field: '', title: '??????' ,align: 'center',colspan:12},"+
   	  		"{field: 'taxableWage', title: '????????????' ,align: 'center',width:'8%',rowspan:3},"+
   	  		"{field: 'taxIncomePersonal', title: '??????' ,width:'8%',align: 'center',rowspan:3},"+
		  	"{field: 'netSalary', title: '????????????' ,width:'8%',align: 'center',rowspan:3},"+
		  	"{field: 'netTargetYearmonth', title: '????????????' ,templet: '<div>{{#if(d.netTargetYearmonth){}} {{layui.util.toDateString(d.netTargetYearmonth, "+"\"yyyy-MM\""+") }} {{#} else{}} {{#}}}</div>',align: 'center',width:'8%',rowspan:3},"+
		  	"{field: 'status', title: '??????',templet: '#statusTpl',sort:true,align: 'center',width:'8%',rowspan:3,hide:true},"+
		  	"{field: 'createTime', title: '????????????',templet: '<div>{{ layui.util.toDateString(d.createTime, "+"\"yyyy-MM-dd HH:mm:ss\""+") }}</div>',align: 'center',width:'10%',rowspan:3}"+
		  	"{field: 'btimpNo', title: '????????????',align: 'center',width:'10%',rowspan:3}"+
		  	/*{field: 'updateTime', title: '????????????',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',align: 'center',width:'10%',rowspan:3,hide:true},
	      	{title: '??????', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chSaPayslip-manager-operator',width:'15%',hide:true}*/
     "]"+
     ","+
     "["+
   	  "{"+
   		  "field: 'manwkStandard', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkAttend', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkMiner', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkQuit', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkSick', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkMaternity', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'manwkPrivpassion', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'wageStandard4posit', title: '??????????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'wageDay4posit', title: '???????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: '', title: '????????????' ,align: 'center',colspan:3},"+
		  "{field: 'wageNightShift', title: '?????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: '', title: '????????????' ,align: 'center',colspan:2},"+
		  "{field: 'wageYearg', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  
		  "{field: '', title: '?????????' ,width:'8%',align: 'center',colspan:9},"+
		  
		  "{field: 'wageTotal', title: '??????????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  //??????????????????????????????????????????????????????????????????????????????head????????????head???
		  "{field: "+(rewards.size()>0?"\'\'":"\'wageJxTotal\'")+", title: '????????????',width:'8%',align: 'center'"+(rewards.size()>0?(",colspan:"+(1+rewards.size())):",rowspan:2")+"},"+
		  "{field: 'wagePayableTotal', title: '??????????????????' ,align: 'center',width:'8%',rowspan:2},"+
		  "{field: '', title: '????????????',align: 'center',colspan:7},"+
		  "{field: 'cutWater2elect', title: '?????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'cutHygiene', title: '?????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'cutArrears', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'cutOther', title: '????????????' ,width:'8%',align: 'center',rowspan:2},"+
		  "{field: 'cutTotal', title: '????????????' ,width:'8%',align: 'center',rowspan:2}"+
     "]"+
     ","+
 	 " ["+
 		  /*{type: 'checkbox', fixed: 'left',hide:true},
		  {field: 'id', title: '????????????' ,sort: true,width:'8%',hide:true},
		  {field: 'workerId', title: '????????????' ,width:'8%',hide:true},*/
		  "{field: 'wageAttend', title: '????????????' ,align: 'center',width:'8%'},"+
		  "{field: 'wageSick', title: '????????????' ,align: 'center',width:'8%'},"+
		  "{field: 'wagePositTotal', title: '??????????????????' ,align: 'center',width:'8%'},"+
		  "{field: 'manwkOvertime', title: '????????????' ,align: 'center',width:'8%'},"+
		  "{field: 'wageOvertime', title: '????????????' ,align: 'center',width:'8%'},"+
		  "{field: 'allowanceHygiene', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4tel', title: '??????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4traffic', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4mining', title: '???????????????????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4other', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4univeStuLife', title: '?????????????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'wageErrorCorrent', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'supp4oth', title: '??????' ,width:'8%',align: 'center'},"+
		  "{field: 'suppTotal', title: '???????????????' ,width:'8%',align: 'center'}";
		  
		StringBuffer sups = new StringBuffer();
		if(null != rewards && rewards.size()>0)  
		{
			for(String item:rewards){
				sups.append("{field: '"+item+"', title: '"+item+"' ,align: 'center',width:'8%'},");
			}
		}
		
	String tabHeadTmp2 = 
		  (rewards.size()>0?"{field: 'wageJxTotal', title: '??????????????????' ,width:'8%',align: 'center'},":"")+
		  "{field: 'bxPension', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxMedical', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxUnemploy', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxSeriousIllness', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxHousfund', title: '???????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxAnnualCorpGold', title: '????????????' ,width:'8%',align: 'center'},"+
		  "{field: 'bxTotal', title: '????????????' ,width:'8%',align: 'center'}"+
	  "]]";
		JSONArray heads = JSONArray.parseArray(tabHeadTmp1+sups.toString()+tabHeadTmp2);
		return heads;
	}
	
	private List<?> packRewardData(Page<ChSaPayslipExtendModel> page)
	{
		if(null == page.getDatas() || page.getDatas().size()<=0)
			return page.getDatas();
		//???????????????????????????????????????????????????
		List<String> rewards = chSaPayslipRewardBiz.queryRewardList(
				page.getModel().getStartMonth(), page.getModel().getEndMonth());
		if(null == rewards || rewards.size()<=0) return page.getDatas();
		
		JSONObject jsonItem = null;
		ChSaPayslipModel item = null;
		List<ChSaPayslipRewardModel> rlist = null;
		List<JSONObject> retlist = new ArrayList<JSONObject>();
		for(int i=0;i<page.getDatas().size();i++)
		{
			item = page.getDatas().get(i);
			jsonItem = JSONObject.parseObject(JSONObject.toJSONString(item));
			rlist = chSaPayslipRewardBiz.queryList(item.getCardNo(), item.getNetTargetYearmonth());
			if(null == rlist || rlist.size()<=0) continue;
			for(ChSaPayslipRewardModel sub:rlist)
			{
				jsonItem.put(sub.getRewardItem(), sub.getRewardAmount()/*null == sub.getRewardAmount()?"--":sub.getRewardAmount()*/);
			}
			retlist.add(jsonItem);
		}
		return retlist;
	}
	
	/**
	 * ??????????????????????????????????????????????????????????????????
	 */
	private synchronized void loadCachedDict4Payslip(ModelMap modelMap)
	{
		if(null == modelMap) return;
		//??????????????????????????????????????????????????????????????????????????????
		Long curTime = System.currentTimeMillis();
		if(null == cachedTime || curTime - cachedTime > (24*60*60*1000) || cachedMap.isEmpty())
		{
			cachedTime = curTime;//????????????????????????????????????
			cachedMap.clear();//?????????????????????????????????
			//String fValues = null;
			List<String> fvallist = null;
			for(String column:cachedColumnsMap.keySet()) {
				fvallist = chSaPayslipBiz.loadSelectFields(column);
//				if(null!=fValues)
//					fvallist = Arrays.asList(fValues.split(","));
//				else
//					fvallist = new ArrayList<String>();
				if(null == fvallist) fvallist = new ArrayList<String>();
				cachedMap.put(cachedColumnsMap.get(column)+"List", fvallist);
			}
		}
		//????????????????????????????????????
		List<ComboxItem> comboxs = null;
		for(String key:cachedMap.keySet())
		{
			List<String> list = cachedMap.get(key);
			comboxs = new ArrayList<ComboxItem>();
			for(String item:list)
			{
				comboxs.add(new ComboxItem(item,item));
			}
			//modelMap.addAttribute(key, comboxs);
			modelMap.addAttribute(key, JSON.toJSON(comboxs));//??????????????????
		}
	}
}
