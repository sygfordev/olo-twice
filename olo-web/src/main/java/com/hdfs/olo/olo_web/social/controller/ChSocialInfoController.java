package com.hdfs.olo.olo_web.social.controller;

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
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
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
import com.hdfs.olo.olo_web.social.biz.IChSocialInfoBiz;
import com.hdfs.olo.olo_web.social.biz.IChSocialRecordBiz;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoModel;
import com.hdfs.olo.olo_web.social.model.ChSocialRecordModel;

/** 
 * Description: [社保信息服务实现]
 * Created on 2021年06月07日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/social/chSocialInfo/*")
public class ChSocialInfoController extends BaseController {
	
	public final static String Module_Name = "社保信息";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChSocialInfoBiz chSocialInfoBiz;
	@Autowired
	private IChSocialRecordBiz chSocialRecordBiz;
	@Autowired
	private IHuTCommonService huTCommonService;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_name = "name";
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_compName = "compName";
	private	final static String PARA_wkModalityCn = "wkModalityCn";
	private	final static String PARA_workAreaCn = "workAreaCn";
	private	final static String PARA_positCn = "positCn";
	private	final static String PARA_titleCn = "titleCn";
	private	final static String PARA_hosBranchCn = "hosBranchCn";
	private	final static String PARA_hosDepart1levelCn = "hosDepart1levelCn";
	private	final static String PARA_hosDepart2levelCn = "hosDepart2levelCn";
	private	final static String PARA_socialYmonth = "socialYmonth";
	private	final static String PARA_sPenBase = "sPenBase";
	private	final static String PARA_sPenCompRatio = "sPenCompRatio";
	private	final static String PARA_sPenCompAmount = "sPenCompAmount";
	private	final static String PARA_sPenCompRecapAmount = "sPenCompRecapAmount";
	private	final static String PARA_sPenCompRecapDiffe = "sPenCompRecapDiffe";
	private	final static String PARA_sPenCompOverpaid = "sPenCompOverpaid";
	private	final static String PARA_sPenPersRatio = "sPenPersRatio";
	private	final static String PARA_sPenPersAmount = "sPenPersAmount";
	private	final static String PARA_sPenPersRecapAmount = "sPenPersRecapAmount";
	private	final static String PARA_sPenPersRecapDiffe = "sPenPersRecapDiffe";
	private	final static String PARA_sPenPersComple = "sPenPersComple";
	private	final static String PARA_sMediBase = "sMediBase";
	private	final static String PARA_sMediCompRatio = "sMediCompRatio";
	private	final static String PARA_sMediCompAmount = "sMediCompAmount";
	private	final static String PARA_sMediCompRecapAmount = "sMediCompRecapAmount";
	private	final static String PARA_sMediCompRecapDiffe = "sMediCompRecapDiffe";
	private	final static String PARA_sMediCompOverpaid = "sMediCompOverpaid";
	private	final static String PARA_sMediPersRatio = "sMediPersRatio";
	private	final static String PARA_sMediPersAmount = "sMediPersAmount";
	private	final static String PARA_sMediPersRecapAmount = "sMediPersRecapAmount";
	private	final static String PARA_sMediPersRecapDiffe = "sMediPersRecapDiffe";
	private	final static String PARA_sMediPersComple = "sMediPersComple";
	private	final static String PARA_sUnempBase = "sUnempBase";
	private	final static String PARA_sUnempCompRatio = "sUnempCompRatio";
	private	final static String PARA_sUnempCompAmount = "sUnempCompAmount";
	private	final static String PARA_sUnempCompRecapAmount = "sUnempCompRecapAmount";
	private	final static String PARA_sUnempCompRecapDiffe = "sUnempCompRecapDiffe";
	private	final static String PARA_sUnempCompOverpaid = "sUnempCompOverpaid";
	private	final static String PARA_sUnempPersRatio = "sUnempPersRatio";
	private	final static String PARA_sUnempPersAmount = "sUnempPersAmount";
	private	final static String PARA_sUnempPersRecapAmount = "sUnempPersRecapAmount";
	private	final static String PARA_sUnempPersRecapDiffe = "sUnempPersRecapDiffe";
	private	final static String PARA_sUnempPersComple = "sUnempPersComple";
	private	final static String PARA_sInjuryBase = "sInjuryBase";
	private	final static String PARA_sInjuryCompRatio = "sInjuryCompRatio";
	private	final static String PARA_sInjuryCompAmount = "sInjuryCompAmount";
	private	final static String PARA_sInjuryCompRecapAmount = "sInjuryCompRecapAmount";
	private	final static String PARA_sInjuryCompRecapDiffe = "sInjuryCompRecapDiffe";
	private	final static String PARA_sInjuryCompOverpaid = "sInjuryCompOverpaid";
	private	final static String PARA_sInjuryPersRatio = "sInjuryPersRatio";
	private	final static String PARA_sInjuryPersAmount = "sInjuryPersAmount";
	private	final static String PARA_sInjuryPersRecapAmount = "sInjuryPersRecapAmount";
	private	final static String PARA_sInjuryPersRecapDiffe = "sInjuryPersRecapDiffe";
	private	final static String PARA_sInjuryPersComple = "sInjuryPersComple";
	private	final static String PARA_sBirthBase = "sBirthBase";
	private	final static String PARA_sBirthCompRatio = "sBirthCompRatio";
	private	final static String PARA_sBirthCompAmount = "sBirthCompAmount";
	private	final static String PARA_sBirthCompRecapAmount = "sBirthCompRecapAmount";
	private	final static String PARA_sBirthCompRecapDiffe = "sBirthCompRecapDiffe";
	private	final static String PARA_sBirthCompOverpaid = "sBirthCompOverpaid";
	private	final static String PARA_sBirthPersRatio = "sBirthPersRatio";
	private	final static String PARA_sBirthPersAmount = "sBirthPersAmount";
	private	final static String PARA_sBirthPersRecapAmount = "sBirthPersRecapAmount";
	private	final static String PARA_sBirthPersRecapDiffe = "sBirthPersRecapDiffe";
	private	final static String PARA_sBirthPersComple = "sBirthPersComple";
	private	final static String PARA_sAnnuityBase = "sAnnuityBase";
	private	final static String PARA_sAnnuityCompRatio = "sAnnuityCompRatio";
	private	final static String PARA_sAnnuityCompAmount = "sAnnuityCompAmount";
	private	final static String PARA_sAnnuityCompRecapAmount = "sAnnuityCompRecapAmount";
	private	final static String PARA_sAnnuityCompRecapDiffe = "sAnnuityCompRecapDiffe";
	private	final static String PARA_sAnnuityCompOverpaid = "sAnnuityCompOverpaid";
	private	final static String PARA_sAnnuityPersRatio = "sAnnuityPersRatio";
	private	final static String PARA_sAnnuityPersAmount = "sAnnuityPersAmount";
	private	final static String PARA_sAnnuityPersRecapAmount = "sAnnuityPersRecapAmount";
	private	final static String PARA_sAnnuityPersRecapDiffe = "sAnnuityPersRecapDiffe";
	private	final static String PARA_sAnnuityPersComple = "sAnnuityPersComple";
	private	final static String PARA_sOvpBase = "sOvpBase";
	private	final static String PARA_sOvpCompRatio = "sOvpCompRatio";
	private	final static String PARA_sOvpCompAmount = "sOvpCompAmount";
	private	final static String PARA_sOvpCompRecapAmount = "sOvpCompRecapAmount";
	private	final static String PARA_sOvpCompRecapDiffe = "sOvpCompRecapDiffe";
	private	final static String PARA_sOvpCompOverpaid = "sOvpCompOverpaid";
	private	final static String PARA_sOvpPersRatio = "sOvpPersRatio";
	private	final static String PARA_sOvpPersAmount = "sOvpPersAmount";
	private	final static String PARA_sOvpPersRecapAmount = "sOvpPersRecapAmount";
	private	final static String PARA_sOvpPersRecapDiffe = "sOvpPersRecapDiffe";
	private	final static String PARA_sOvpPersComple = "sOvpPersComple";
	private	final static String PARA_sSpMediBase = "sSpMediBase";
	private	final static String PARA_sSpMediCompRatio = "sSpMediCompRatio";
	private	final static String PARA_sSpMediCompAmount = "sSpMediCompAmount";
	private	final static String PARA_sSpMediCompRecapAmount = "sSpMediCompRecapAmount";
	private	final static String PARA_sSpMediCompRecapDiffe = "sSpMediCompRecapDiffe";
	private	final static String PARA_sSpMediCompOverpaid = "sSpMediCompOverpaid";
	private	final static String PARA_sSpMediPersRatio = "sSpMediPersRatio";
	private	final static String PARA_sSpMediPersAmount = "sSpMediPersAmount";
	private	final static String PARA_sSpMediPersRecapAmount = "sSpMediPersRecapAmount";
	private	final static String PARA_sSpMediPersRecapDiffe = "sSpMediPersRecapDiffe";
	private	final static String PARA_sSpMediPersComple = "sSpMediPersComple";
	private	final static String PARA_remark4month = "remark4month";
	private	final static String PARA_remark4year = "remark4year";
	private	final static String PARA_persRecapDiffe4addup = "persRecapDiffe4addup";
	private	final static String PARA_persCompleDiffe4addup = "persCompleDiffe4addup";
	private	final static String PARA_status = "status";
	private	final static String PARA_btimpNo = "btimpNo";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private	final static String PARA_compNames = "compNames";
	private	final static String PARA_wkModalityCns = "wkModalityCns";
	private	final static String PARA_hosDepart1levelCns = "hosDepart1levelCns";
	private	final static String PARA_hosDepart2levelCns = "hosDepart2levelCns";
	
	//新增-编辑和详情加载数据时的字典数据回显项
	private final static String DICT_COMMON_CODES = "status,YN";
	
	//工资条字典缓存时间
	Long cachedTime = null;
	Map<String,List<String>> cachedMap = new HashMap<String,List<String>>();
	private static final Map<String,Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String,Object>();
		cachedColumnsMap.put("HOS_DEPART_1LEVEL_CN", "depart1");
		cachedColumnsMap.put("HOS_DEPART_2LEVEL_CN", "depart2");
		cachedColumnsMap.put("WK_MODALITY_CN", "modality");
		cachedColumnsMap.put("COMP_NAME", "compName");
	}
		
	/**
	 * <p>Discription:[社保信息请求首页]</p>
	 * Created on 2021年06月07日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//填充字典数据
		//fillCommonDict(modelMap);
		flushCached(modelMap);
		//modelMap.put("curMonth", DateTimeHelper.getMonth(new Date()));
	}
	 
	/**
	 * <p>Discription:[社保信息首页数据加载-分页]</p>
	 * Created on 2021年06月07日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSocialInfoExtModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChSocialInfoExtModel> retBody = new Result4Page<ChSocialInfoExtModel>();
		//查询参数
		Page.Builder<ChSocialInfoExtModel> builder = new Page.Builder<ChSocialInfoExtModel>();
		setSearchParameters(request, builder);
		Page<ChSocialInfoExtModel> page = builder.build();
		if(StringHelper.isNullOrEmpty(page.getModel().getSocialYmonth())) {
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("请选择社保年月！");
			return retBody;
		}
//		String curMonth = DateTimeHelper.format(new Date(), "yyyy-MM");
//		page.getModel().setSocialYmonth(curMonth);
		try {
			//分页
			chSocialInfoBiz.queryPage(page);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(page.getDatas());
			retBody.setCount(page.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		return retBody;
	}
	
	//===============================补缴单区域=================================
	/**
	 * <p>Discription:[社保补缴请求首页]</p>
	 * Created on 2021年06月07日												       	 
	 * @author:huadf
	 */
	@RequestMapping("supple.jhtm")
	public void supple(HttpServletRequest request,ModelMap modelMap) throws Exception{
		flushCached(modelMap);
		//modelMap.put("curMonth", DateTimeHelper.getMonth(new Date()));
	}
	 
	/**
	 * <p>Discription:[社保补缴首页数据加载-分页]</p>
	 * Created on 2021年06月07日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "supple/do.jhtm")
	@ResponseBody
	public Map<String,Object> doSupple(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			//获取请求参数
			String name = request.getParameter(PARA_name);
			String idcard = request.getParameter(PARA_cardNo);
			if(StringHelper.isNullOrEmpty(name) && StringHelper.isNullOrEmpty(idcard)) {
				retMap.put("retCode", 201);
				retMap.put("retMsg","请输入姓名或身份证号！");
				return retMap;
			}
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			if(!StringHelper.isNullOrEmpty(name))model.setName(name);
			if(!StringHelper.isNullOrEmpty(idcard))model.setCardNo(idcard);
			//分页
			List<Map<String,Object>> list = chSocialInfoBiz.query4Supple(model);
			retMap.put("retCode", 200);
			retMap.put("retMsg","获取成功！");
			retMap.put("retData",list);
			retMap.put("count",list.size());
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg","获取失败！");
			logger.error("加载社保补缴单失败！",e);
		}
		return retMap;
	}
	/**
	 * 补缴单导出
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "export4supple.jhtm")
	@ResponseBody
	public void export4supple(HttpServletRequest request,HttpServletResponse response) {
		try {
			//获取请求参数
			String name = request.getParameter(PARA_name);
			String idard = request.getParameter(PARA_cardNo);
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			if(!StringHelper.isNullOrEmpty(name))model.setName(name);
			if(!StringHelper.isNullOrEmpty(idard))model.setCardNo(idard);
			//生成header
			JSONObject headJson = SocialExportHeadUtil.createSuppleHead();
			if(null == headJson || headJson.isEmpty()) throw new Exception("表头信息异常");
			List<Map<String,Object>> list = chSocialInfoBiz.query4Supple(model);
			//logger.info("数据："+JSON.toJSONString(list));
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			chSocialInfoBiz.export4supple(response, "社保补缴单", list, headInfo);
			//huTCommonService.export(response, "社保补缴单", list, headInfo);
		}catch(Exception e)
		{
			logger.error("社保补缴单导出失败！error:",e);
		}
	}
	//===============================补缴单区域=================================
	
	/**
	 * <p>Discription:[社保信息设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年06月07日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSocialInfoExtModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):1;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):10;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String name = request.getParameter(PARA_name);
		String cardNo = request.getParameter(PARA_cardNo);
		String compName = request.getParameter(PARA_compName);
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String workAreaCn = request.getParameter(PARA_workAreaCn);
		String positCn = request.getParameter(PARA_positCn);
		String titleCn = request.getParameter(PARA_titleCn);
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String socialYmonth = request.getParameter(PARA_socialYmonth);
		String sPenBaseStr = request.getParameter(PARA_sPenBase);
		BigDecimal sPenBase = !StringHelper.isNullOrEmpty(sPenBaseStr)?new BigDecimal(sPenBaseStr):null;
		String sPenCompRatioStr = request.getParameter(PARA_sPenCompRatio);
		BigDecimal sPenCompRatio = !StringHelper.isNullOrEmpty(sPenCompRatioStr)?new BigDecimal(sPenCompRatioStr):null;
		String sPenCompAmountStr = request.getParameter(PARA_sPenCompAmount);
		BigDecimal sPenCompAmount = !StringHelper.isNullOrEmpty(sPenCompAmountStr)?new BigDecimal(sPenCompAmountStr):null;
		String sPenCompRecapAmountStr = request.getParameter(PARA_sPenCompRecapAmount);
		BigDecimal sPenCompRecapAmount = !StringHelper.isNullOrEmpty(sPenCompRecapAmountStr)?new BigDecimal(sPenCompRecapAmountStr):null;
		String sPenCompRecapDiffeStr = request.getParameter(PARA_sPenCompRecapDiffe);
		BigDecimal sPenCompRecapDiffe = !StringHelper.isNullOrEmpty(sPenCompRecapDiffeStr)?new BigDecimal(sPenCompRecapDiffeStr):null;
		String sPenCompOverpaidStr = request.getParameter(PARA_sPenCompOverpaid);
		BigDecimal sPenCompOverpaid = !StringHelper.isNullOrEmpty(sPenCompOverpaidStr)?new BigDecimal(sPenCompOverpaidStr):null;
		String sPenPersRatioStr = request.getParameter(PARA_sPenPersRatio);
		BigDecimal sPenPersRatio = !StringHelper.isNullOrEmpty(sPenPersRatioStr)?new BigDecimal(sPenPersRatioStr):null;
		String sPenPersAmountStr = request.getParameter(PARA_sPenPersAmount);
		BigDecimal sPenPersAmount = !StringHelper.isNullOrEmpty(sPenPersAmountStr)?new BigDecimal(sPenPersAmountStr):null;
		String sPenPersRecapAmountStr = request.getParameter(PARA_sPenPersRecapAmount);
		BigDecimal sPenPersRecapAmount = !StringHelper.isNullOrEmpty(sPenPersRecapAmountStr)?new BigDecimal(sPenPersRecapAmountStr):null;
		String sPenPersRecapDiffeStr = request.getParameter(PARA_sPenPersRecapDiffe);
		BigDecimal sPenPersRecapDiffe = !StringHelper.isNullOrEmpty(sPenPersRecapDiffeStr)?new BigDecimal(sPenPersRecapDiffeStr):null;
		String sPenPersCompleStr = request.getParameter(PARA_sPenPersComple);
		BigDecimal sPenPersComple = !StringHelper.isNullOrEmpty(sPenPersCompleStr)?new BigDecimal(sPenPersCompleStr):null;
		String sMediBaseStr = request.getParameter(PARA_sMediBase);
		BigDecimal sMediBase = !StringHelper.isNullOrEmpty(sMediBaseStr)?new BigDecimal(sMediBaseStr):null;
		String sMediCompRatioStr = request.getParameter(PARA_sMediCompRatio);
		BigDecimal sMediCompRatio = !StringHelper.isNullOrEmpty(sMediCompRatioStr)?new BigDecimal(sMediCompRatioStr):null;
		String sMediCompAmountStr = request.getParameter(PARA_sMediCompAmount);
		BigDecimal sMediCompAmount = !StringHelper.isNullOrEmpty(sMediCompAmountStr)?new BigDecimal(sMediCompAmountStr):null;
		String sMediCompRecapAmountStr = request.getParameter(PARA_sMediCompRecapAmount);
		BigDecimal sMediCompRecapAmount = !StringHelper.isNullOrEmpty(sMediCompRecapAmountStr)?new BigDecimal(sMediCompRecapAmountStr):null;
		String sMediCompRecapDiffeStr = request.getParameter(PARA_sMediCompRecapDiffe);
		BigDecimal sMediCompRecapDiffe = !StringHelper.isNullOrEmpty(sMediCompRecapDiffeStr)?new BigDecimal(sMediCompRecapDiffeStr):null;
		String sMediCompOverpaidStr = request.getParameter(PARA_sMediCompOverpaid);
		BigDecimal sMediCompOverpaid = !StringHelper.isNullOrEmpty(sMediCompOverpaidStr)?new BigDecimal(sMediCompOverpaidStr):null;
		String sMediPersRatioStr = request.getParameter(PARA_sMediPersRatio);
		BigDecimal sMediPersRatio = !StringHelper.isNullOrEmpty(sMediPersRatioStr)?new BigDecimal(sMediPersRatioStr):null;
		String sMediPersAmountStr = request.getParameter(PARA_sMediPersAmount);
		BigDecimal sMediPersAmount = !StringHelper.isNullOrEmpty(sMediPersAmountStr)?new BigDecimal(sMediPersAmountStr):null;
		String sMediPersRecapAmountStr = request.getParameter(PARA_sMediPersRecapAmount);
		BigDecimal sMediPersRecapAmount = !StringHelper.isNullOrEmpty(sMediPersRecapAmountStr)?new BigDecimal(sMediPersRecapAmountStr):null;
		String sMediPersRecapDiffeStr = request.getParameter(PARA_sMediPersRecapDiffe);
		BigDecimal sMediPersRecapDiffe = !StringHelper.isNullOrEmpty(sMediPersRecapDiffeStr)?new BigDecimal(sMediPersRecapDiffeStr):null;
		String sMediPersCompleStr = request.getParameter(PARA_sMediPersComple);
		BigDecimal sMediPersComple = !StringHelper.isNullOrEmpty(sMediPersCompleStr)?new BigDecimal(sMediPersCompleStr):null;
		String sUnempBaseStr = request.getParameter(PARA_sUnempBase);
		BigDecimal sUnempBase = !StringHelper.isNullOrEmpty(sUnempBaseStr)?new BigDecimal(sUnempBaseStr):null;
		String sUnempCompRatioStr = request.getParameter(PARA_sUnempCompRatio);
		BigDecimal sUnempCompRatio = !StringHelper.isNullOrEmpty(sUnempCompRatioStr)?new BigDecimal(sUnempCompRatioStr):null;
		String sUnempCompAmountStr = request.getParameter(PARA_sUnempCompAmount);
		BigDecimal sUnempCompAmount = !StringHelper.isNullOrEmpty(sUnempCompAmountStr)?new BigDecimal(sUnempCompAmountStr):null;
		String sUnempCompRecapAmountStr = request.getParameter(PARA_sUnempCompRecapAmount);
		BigDecimal sUnempCompRecapAmount = !StringHelper.isNullOrEmpty(sUnempCompRecapAmountStr)?new BigDecimal(sUnempCompRecapAmountStr):null;
		String sUnempCompRecapDiffeStr = request.getParameter(PARA_sUnempCompRecapDiffe);
		BigDecimal sUnempCompRecapDiffe = !StringHelper.isNullOrEmpty(sUnempCompRecapDiffeStr)?new BigDecimal(sUnempCompRecapDiffeStr):null;
		String sUnempCompOverpaidStr = request.getParameter(PARA_sUnempCompOverpaid);
		BigDecimal sUnempCompOverpaid = !StringHelper.isNullOrEmpty(sUnempCompOverpaidStr)?new BigDecimal(sUnempCompOverpaidStr):null;
		String sUnempPersRatioStr = request.getParameter(PARA_sUnempPersRatio);
		BigDecimal sUnempPersRatio = !StringHelper.isNullOrEmpty(sUnempPersRatioStr)?new BigDecimal(sUnempPersRatioStr):null;
		String sUnempPersAmountStr = request.getParameter(PARA_sUnempPersAmount);
		BigDecimal sUnempPersAmount = !StringHelper.isNullOrEmpty(sUnempPersAmountStr)?new BigDecimal(sUnempPersAmountStr):null;
		String sUnempPersRecapAmountStr = request.getParameter(PARA_sUnempPersRecapAmount);
		BigDecimal sUnempPersRecapAmount = !StringHelper.isNullOrEmpty(sUnempPersRecapAmountStr)?new BigDecimal(sUnempPersRecapAmountStr):null;
		String sUnempPersRecapDiffeStr = request.getParameter(PARA_sUnempPersRecapDiffe);
		BigDecimal sUnempPersRecapDiffe = !StringHelper.isNullOrEmpty(sUnempPersRecapDiffeStr)?new BigDecimal(sUnempPersRecapDiffeStr):null;
		String sUnempPersCompleStr = request.getParameter(PARA_sUnempPersComple);
		BigDecimal sUnempPersComple = !StringHelper.isNullOrEmpty(sUnempPersCompleStr)?new BigDecimal(sUnempPersCompleStr):null;
		String sInjuryBaseStr = request.getParameter(PARA_sInjuryBase);
		BigDecimal sInjuryBase = !StringHelper.isNullOrEmpty(sInjuryBaseStr)?new BigDecimal(sInjuryBaseStr):null;
		String sInjuryCompRatioStr = request.getParameter(PARA_sInjuryCompRatio);
		BigDecimal sInjuryCompRatio = !StringHelper.isNullOrEmpty(sInjuryCompRatioStr)?new BigDecimal(sInjuryCompRatioStr):null;
		String sInjuryCompAmountStr = request.getParameter(PARA_sInjuryCompAmount);
		BigDecimal sInjuryCompAmount = !StringHelper.isNullOrEmpty(sInjuryCompAmountStr)?new BigDecimal(sInjuryCompAmountStr):null;
		String sInjuryCompRecapAmountStr = request.getParameter(PARA_sInjuryCompRecapAmount);
		BigDecimal sInjuryCompRecapAmount = !StringHelper.isNullOrEmpty(sInjuryCompRecapAmountStr)?new BigDecimal(sInjuryCompRecapAmountStr):null;
		String sInjuryCompRecapDiffeStr = request.getParameter(PARA_sInjuryCompRecapDiffe);
		BigDecimal sInjuryCompRecapDiffe = !StringHelper.isNullOrEmpty(sInjuryCompRecapDiffeStr)?new BigDecimal(sInjuryCompRecapDiffeStr):null;
		String sInjuryCompOverpaidStr = request.getParameter(PARA_sInjuryCompOverpaid);
		BigDecimal sInjuryCompOverpaid = !StringHelper.isNullOrEmpty(sInjuryCompOverpaidStr)?new BigDecimal(sInjuryCompOverpaidStr):null;
		String sInjuryPersRatioStr = request.getParameter(PARA_sInjuryPersRatio);
		BigDecimal sInjuryPersRatio = !StringHelper.isNullOrEmpty(sInjuryPersRatioStr)?new BigDecimal(sInjuryPersRatioStr):null;
		String sInjuryPersAmountStr = request.getParameter(PARA_sInjuryPersAmount);
		BigDecimal sInjuryPersAmount = !StringHelper.isNullOrEmpty(sInjuryPersAmountStr)?new BigDecimal(sInjuryPersAmountStr):null;
		String sInjuryPersRecapAmountStr = request.getParameter(PARA_sInjuryPersRecapAmount);
		BigDecimal sInjuryPersRecapAmount = !StringHelper.isNullOrEmpty(sInjuryPersRecapAmountStr)?new BigDecimal(sInjuryPersRecapAmountStr):null;
		String sInjuryPersRecapDiffeStr = request.getParameter(PARA_sInjuryPersRecapDiffe);
		BigDecimal sInjuryPersRecapDiffe = !StringHelper.isNullOrEmpty(sInjuryPersRecapDiffeStr)?new BigDecimal(sInjuryPersRecapDiffeStr):null;
		String sInjuryPersCompleStr = request.getParameter(PARA_sInjuryPersComple);
		BigDecimal sInjuryPersComple = !StringHelper.isNullOrEmpty(sInjuryPersCompleStr)?new BigDecimal(sInjuryPersCompleStr):null;
		String sBirthBaseStr = request.getParameter(PARA_sBirthBase);
		BigDecimal sBirthBase = !StringHelper.isNullOrEmpty(sBirthBaseStr)?new BigDecimal(sBirthBaseStr):null;
		String sBirthCompRatioStr = request.getParameter(PARA_sBirthCompRatio);
		BigDecimal sBirthCompRatio = !StringHelper.isNullOrEmpty(sBirthCompRatioStr)?new BigDecimal(sBirthCompRatioStr):null;
		String sBirthCompAmountStr = request.getParameter(PARA_sBirthCompAmount);
		BigDecimal sBirthCompAmount = !StringHelper.isNullOrEmpty(sBirthCompAmountStr)?new BigDecimal(sBirthCompAmountStr):null;
		String sBirthCompRecapAmountStr = request.getParameter(PARA_sBirthCompRecapAmount);
		BigDecimal sBirthCompRecapAmount = !StringHelper.isNullOrEmpty(sBirthCompRecapAmountStr)?new BigDecimal(sBirthCompRecapAmountStr):null;
		String sBirthCompRecapDiffeStr = request.getParameter(PARA_sBirthCompRecapDiffe);
		BigDecimal sBirthCompRecapDiffe = !StringHelper.isNullOrEmpty(sBirthCompRecapDiffeStr)?new BigDecimal(sBirthCompRecapDiffeStr):null;
		String sBirthCompOverpaidStr = request.getParameter(PARA_sBirthCompOverpaid);
		BigDecimal sBirthCompOverpaid = !StringHelper.isNullOrEmpty(sBirthCompOverpaidStr)?new BigDecimal(sBirthCompOverpaidStr):null;
		String sBirthPersRatioStr = request.getParameter(PARA_sBirthPersRatio);
		BigDecimal sBirthPersRatio = !StringHelper.isNullOrEmpty(sBirthPersRatioStr)?new BigDecimal(sBirthPersRatioStr):null;
		String sBirthPersAmountStr = request.getParameter(PARA_sBirthPersAmount);
		BigDecimal sBirthPersAmount = !StringHelper.isNullOrEmpty(sBirthPersAmountStr)?new BigDecimal(sBirthPersAmountStr):null;
		String sBirthPersRecapAmountStr = request.getParameter(PARA_sBirthPersRecapAmount);
		BigDecimal sBirthPersRecapAmount = !StringHelper.isNullOrEmpty(sBirthPersRecapAmountStr)?new BigDecimal(sBirthPersRecapAmountStr):null;
		String sBirthPersRecapDiffeStr = request.getParameter(PARA_sBirthPersRecapDiffe);
		BigDecimal sBirthPersRecapDiffe = !StringHelper.isNullOrEmpty(sBirthPersRecapDiffeStr)?new BigDecimal(sBirthPersRecapDiffeStr):null;
		String sBirthPersCompleStr = request.getParameter(PARA_sBirthPersComple);
		BigDecimal sBirthPersComple = !StringHelper.isNullOrEmpty(sBirthPersCompleStr)?new BigDecimal(sBirthPersCompleStr):null;
		String sAnnuityBaseStr = request.getParameter(PARA_sAnnuityBase);
		BigDecimal sAnnuityBase = !StringHelper.isNullOrEmpty(sAnnuityBaseStr)?new BigDecimal(sAnnuityBaseStr):null;
		String sAnnuityCompRatioStr = request.getParameter(PARA_sAnnuityCompRatio);
		BigDecimal sAnnuityCompRatio = !StringHelper.isNullOrEmpty(sAnnuityCompRatioStr)?new BigDecimal(sAnnuityCompRatioStr):null;
		String sAnnuityCompAmountStr = request.getParameter(PARA_sAnnuityCompAmount);
		BigDecimal sAnnuityCompAmount = !StringHelper.isNullOrEmpty(sAnnuityCompAmountStr)?new BigDecimal(sAnnuityCompAmountStr):null;
		String sAnnuityCompRecapAmountStr = request.getParameter(PARA_sAnnuityCompRecapAmount);
		BigDecimal sAnnuityCompRecapAmount = !StringHelper.isNullOrEmpty(sAnnuityCompRecapAmountStr)?new BigDecimal(sAnnuityCompRecapAmountStr):null;
		String sAnnuityCompRecapDiffeStr = request.getParameter(PARA_sAnnuityCompRecapDiffe);
		BigDecimal sAnnuityCompRecapDiffe = !StringHelper.isNullOrEmpty(sAnnuityCompRecapDiffeStr)?new BigDecimal(sAnnuityCompRecapDiffeStr):null;
		String sAnnuityCompOverpaidStr = request.getParameter(PARA_sAnnuityCompOverpaid);
		BigDecimal sAnnuityCompOverpaid = !StringHelper.isNullOrEmpty(sAnnuityCompOverpaidStr)?new BigDecimal(sAnnuityCompOverpaidStr):null;
		String sAnnuityPersRatioStr = request.getParameter(PARA_sAnnuityPersRatio);
		BigDecimal sAnnuityPersRatio = !StringHelper.isNullOrEmpty(sAnnuityPersRatioStr)?new BigDecimal(sAnnuityPersRatioStr):null;
		String sAnnuityPersAmountStr = request.getParameter(PARA_sAnnuityPersAmount);
		BigDecimal sAnnuityPersAmount = !StringHelper.isNullOrEmpty(sAnnuityPersAmountStr)?new BigDecimal(sAnnuityPersAmountStr):null;
		String sAnnuityPersRecapAmountStr = request.getParameter(PARA_sAnnuityPersRecapAmount);
		BigDecimal sAnnuityPersRecapAmount = !StringHelper.isNullOrEmpty(sAnnuityPersRecapAmountStr)?new BigDecimal(sAnnuityPersRecapAmountStr):null;
		String sAnnuityPersRecapDiffeStr = request.getParameter(PARA_sAnnuityPersRecapDiffe);
		BigDecimal sAnnuityPersRecapDiffe = !StringHelper.isNullOrEmpty(sAnnuityPersRecapDiffeStr)?new BigDecimal(sAnnuityPersRecapDiffeStr):null;
		String sAnnuityPersCompleStr = request.getParameter(PARA_sAnnuityPersComple);
		BigDecimal sAnnuityPersComple = !StringHelper.isNullOrEmpty(sAnnuityPersCompleStr)?new BigDecimal(sAnnuityPersCompleStr):null;
		String sOvpBaseStr = request.getParameter(PARA_sOvpBase);
		BigDecimal sOvpBase = !StringHelper.isNullOrEmpty(sOvpBaseStr)?new BigDecimal(sOvpBaseStr):null;
		String sOvpCompRatioStr = request.getParameter(PARA_sOvpCompRatio);
		BigDecimal sOvpCompRatio = !StringHelper.isNullOrEmpty(sOvpCompRatioStr)?new BigDecimal(sOvpCompRatioStr):null;
		String sOvpCompAmountStr = request.getParameter(PARA_sOvpCompAmount);
		BigDecimal sOvpCompAmount = !StringHelper.isNullOrEmpty(sOvpCompAmountStr)?new BigDecimal(sOvpCompAmountStr):null;
		String sOvpCompRecapAmountStr = request.getParameter(PARA_sOvpCompRecapAmount);
		BigDecimal sOvpCompRecapAmount = !StringHelper.isNullOrEmpty(sOvpCompRecapAmountStr)?new BigDecimal(sOvpCompRecapAmountStr):null;
		String sOvpCompRecapDiffeStr = request.getParameter(PARA_sOvpCompRecapDiffe);
		BigDecimal sOvpCompRecapDiffe = !StringHelper.isNullOrEmpty(sOvpCompRecapDiffeStr)?new BigDecimal(sOvpCompRecapDiffeStr):null;
		String sOvpCompOverpaidStr = request.getParameter(PARA_sOvpCompOverpaid);
		BigDecimal sOvpCompOverpaid = !StringHelper.isNullOrEmpty(sOvpCompOverpaidStr)?new BigDecimal(sOvpCompOverpaidStr):null;
		String sOvpPersRatioStr = request.getParameter(PARA_sOvpPersRatio);
		BigDecimal sOvpPersRatio = !StringHelper.isNullOrEmpty(sOvpPersRatioStr)?new BigDecimal(sOvpPersRatioStr):null;
		String sOvpPersAmountStr = request.getParameter(PARA_sOvpPersAmount);
		BigDecimal sOvpPersAmount = !StringHelper.isNullOrEmpty(sOvpPersAmountStr)?new BigDecimal(sOvpPersAmountStr):null;
		String sOvpPersRecapAmountStr = request.getParameter(PARA_sOvpPersRecapAmount);
		BigDecimal sOvpPersRecapAmount = !StringHelper.isNullOrEmpty(sOvpPersRecapAmountStr)?new BigDecimal(sOvpPersRecapAmountStr):null;
		String sOvpPersRecapDiffeStr = request.getParameter(PARA_sOvpPersRecapDiffe);
		BigDecimal sOvpPersRecapDiffe = !StringHelper.isNullOrEmpty(sOvpPersRecapDiffeStr)?new BigDecimal(sOvpPersRecapDiffeStr):null;
		String sOvpPersCompleStr = request.getParameter(PARA_sOvpPersComple);
		BigDecimal sOvpPersComple = !StringHelper.isNullOrEmpty(sOvpPersCompleStr)?new BigDecimal(sOvpPersCompleStr):null;
		String sSpMediBaseStr = request.getParameter(PARA_sSpMediBase);
		BigDecimal sSpMediBase = !StringHelper.isNullOrEmpty(sSpMediBaseStr)?new BigDecimal(sSpMediBaseStr):null;
		String sSpMediCompRatioStr = request.getParameter(PARA_sSpMediCompRatio);
		BigDecimal sSpMediCompRatio = !StringHelper.isNullOrEmpty(sSpMediCompRatioStr)?new BigDecimal(sSpMediCompRatioStr):null;
		String sSpMediCompAmountStr = request.getParameter(PARA_sSpMediCompAmount);
		BigDecimal sSpMediCompAmount = !StringHelper.isNullOrEmpty(sSpMediCompAmountStr)?new BigDecimal(sSpMediCompAmountStr):null;
		String sSpMediCompRecapAmountStr = request.getParameter(PARA_sSpMediCompRecapAmount);
		BigDecimal sSpMediCompRecapAmount = !StringHelper.isNullOrEmpty(sSpMediCompRecapAmountStr)?new BigDecimal(sSpMediCompRecapAmountStr):null;
		String sSpMediCompRecapDiffeStr = request.getParameter(PARA_sSpMediCompRecapDiffe);
		BigDecimal sSpMediCompRecapDiffe = !StringHelper.isNullOrEmpty(sSpMediCompRecapDiffeStr)?new BigDecimal(sSpMediCompRecapDiffeStr):null;
		String sSpMediCompOverpaidStr = request.getParameter(PARA_sSpMediCompOverpaid);
		BigDecimal sSpMediCompOverpaid = !StringHelper.isNullOrEmpty(sSpMediCompOverpaidStr)?new BigDecimal(sSpMediCompOverpaidStr):null;
		String sSpMediPersRatioStr = request.getParameter(PARA_sSpMediPersRatio);
		BigDecimal sSpMediPersRatio = !StringHelper.isNullOrEmpty(sSpMediPersRatioStr)?new BigDecimal(sSpMediPersRatioStr):null;
		String sSpMediPersAmountStr = request.getParameter(PARA_sSpMediPersAmount);
		BigDecimal sSpMediPersAmount = !StringHelper.isNullOrEmpty(sSpMediPersAmountStr)?new BigDecimal(sSpMediPersAmountStr):null;
		String sSpMediPersRecapAmountStr = request.getParameter(PARA_sSpMediPersRecapAmount);
		BigDecimal sSpMediPersRecapAmount = !StringHelper.isNullOrEmpty(sSpMediPersRecapAmountStr)?new BigDecimal(sSpMediPersRecapAmountStr):null;
		String sSpMediPersRecapDiffeStr = request.getParameter(PARA_sSpMediPersRecapDiffe);
		BigDecimal sSpMediPersRecapDiffe = !StringHelper.isNullOrEmpty(sSpMediPersRecapDiffeStr)?new BigDecimal(sSpMediPersRecapDiffeStr):null;
		String sSpMediPersCompleStr = request.getParameter(PARA_sSpMediPersComple);
		BigDecimal sSpMediPersComple = !StringHelper.isNullOrEmpty(sSpMediPersCompleStr)?new BigDecimal(sSpMediPersCompleStr):null;
		String remark4month = request.getParameter(PARA_remark4month);
		String remark4year = request.getParameter(PARA_remark4year);
		String persRecapDiffe4addupStr = request.getParameter(PARA_persRecapDiffe4addup);
		BigDecimal persRecapDiffe4addup = !StringHelper.isNullOrEmpty(persRecapDiffe4addupStr)?new BigDecimal(persRecapDiffe4addupStr):null;
		String persCompleDiffe4addupStr = request.getParameter(PARA_persCompleDiffe4addup);
		BigDecimal persCompleDiffe4addup = !StringHelper.isNullOrEmpty(persCompleDiffe4addupStr)?new BigDecimal(persCompleDiffe4addupStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String btimpNo = request.getParameter(PARA_btimpNo);
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		//扩展属性
		String compNamesStr = request.getParameter(PARA_compNames);
		List<String> compNames = !StringHelper.isNullOrEmpty(compNamesStr)?Arrays.asList(compNamesStr.split(",")):null;
		String wkModalityCns = request.getParameter(PARA_wkModalityCns);
		List<String> wkmodalitys = !StringHelper.isNullOrEmpty(wkModalityCns)?Arrays.asList(wkModalityCns.split(",")):null;
		String hosDepart1levelCns = request.getParameter(PARA_hosDepart1levelCns);
		List<String> departLevel1s = !StringHelper.isNullOrEmpty(hosDepart1levelCns)?Arrays.asList(hosDepart1levelCns.split(",")):null;
		String hosDepart2levelCns = request.getParameter(PARA_hosDepart2levelCns);
		List<String> departLevel2s = !StringHelper.isNullOrEmpty(hosDepart2levelCns)?Arrays.asList(hosDepart2levelCns.split(",")):null;
		
		ChSocialInfoExtModel model = new ChSocialInfoExtModel();
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setCompName(null!=compName && !"".equals(compName.trim())?compName.trim():null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWorkAreaCn(null!=workAreaCn && !"".equals(workAreaCn.trim())?workAreaCn.trim():null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setSocialYmonth(null!=socialYmonth && !"".equals(socialYmonth.trim())?socialYmonth.trim():null);
		model.setSPenBase(null!=sPenBase?sPenBase:null);
		model.setSPenCompRatio(null!=sPenCompRatio?sPenCompRatio:null);
		model.setSPenCompAmount(null!=sPenCompAmount?sPenCompAmount:null);
		model.setSPenCompRecapAmount(null!=sPenCompRecapAmount?sPenCompRecapAmount:null);
		model.setSPenCompRecapDiffe(null!=sPenCompRecapDiffe?sPenCompRecapDiffe:null);
		model.setSPenCompOverpaid(null!=sPenCompOverpaid?sPenCompOverpaid:null);
		model.setSPenPersRatio(null!=sPenPersRatio?sPenPersRatio:null);
		model.setSPenPersAmount(null!=sPenPersAmount?sPenPersAmount:null);
		model.setSPenPersRecapAmount(null!=sPenPersRecapAmount?sPenPersRecapAmount:null);
		model.setSPenPersRecapDiffe(null!=sPenPersRecapDiffe?sPenPersRecapDiffe:null);
		model.setSPenPersComple(null!=sPenPersComple?sPenPersComple:null);
		model.setSMediBase(null!=sMediBase?sMediBase:null);
		model.setSMediCompRatio(null!=sMediCompRatio?sMediCompRatio:null);
		model.setSMediCompAmount(null!=sMediCompAmount?sMediCompAmount:null);
		model.setSMediCompRecapAmount(null!=sMediCompRecapAmount?sMediCompRecapAmount:null);
		model.setSMediCompRecapDiffe(null!=sMediCompRecapDiffe?sMediCompRecapDiffe:null);
		model.setSMediCompOverpaid(null!=sMediCompOverpaid?sMediCompOverpaid:null);
		model.setSMediPersRatio(null!=sMediPersRatio?sMediPersRatio:null);
		model.setSMediPersAmount(null!=sMediPersAmount?sMediPersAmount:null);
		model.setSMediPersRecapAmount(null!=sMediPersRecapAmount?sMediPersRecapAmount:null);
		model.setSMediPersRecapDiffe(null!=sMediPersRecapDiffe?sMediPersRecapDiffe:null);
		model.setSMediPersComple(null!=sMediPersComple?sMediPersComple:null);
		model.setSUnempBase(null!=sUnempBase?sUnempBase:null);
		model.setSUnempCompRatio(null!=sUnempCompRatio?sUnempCompRatio:null);
		model.setSUnempCompAmount(null!=sUnempCompAmount?sUnempCompAmount:null);
		model.setSUnempCompRecapAmount(null!=sUnempCompRecapAmount?sUnempCompRecapAmount:null);
		model.setSUnempCompRecapDiffe(null!=sUnempCompRecapDiffe?sUnempCompRecapDiffe:null);
		model.setSUnempCompOverpaid(null!=sUnempCompOverpaid?sUnempCompOverpaid:null);
		model.setSUnempPersRatio(null!=sUnempPersRatio?sUnempPersRatio:null);
		model.setSUnempPersAmount(null!=sUnempPersAmount?sUnempPersAmount:null);
		model.setSUnempPersRecapAmount(null!=sUnempPersRecapAmount?sUnempPersRecapAmount:null);
		model.setSUnempPersRecapDiffe(null!=sUnempPersRecapDiffe?sUnempPersRecapDiffe:null);
		model.setSUnempPersComple(null!=sUnempPersComple?sUnempPersComple:null);
		model.setSInjuryBase(null!=sInjuryBase?sInjuryBase:null);
		model.setSInjuryCompRatio(null!=sInjuryCompRatio?sInjuryCompRatio:null);
		model.setSInjuryCompAmount(null!=sInjuryCompAmount?sInjuryCompAmount:null);
		model.setSInjuryCompRecapAmount(null!=sInjuryCompRecapAmount?sInjuryCompRecapAmount:null);
		model.setSInjuryCompRecapDiffe(null!=sInjuryCompRecapDiffe?sInjuryCompRecapDiffe:null);
		model.setSInjuryCompOverpaid(null!=sInjuryCompOverpaid?sInjuryCompOverpaid:null);
		model.setSInjuryPersRatio(null!=sInjuryPersRatio?sInjuryPersRatio:null);
		model.setSInjuryPersAmount(null!=sInjuryPersAmount?sInjuryPersAmount:null);
		model.setSInjuryPersRecapAmount(null!=sInjuryPersRecapAmount?sInjuryPersRecapAmount:null);
		model.setSInjuryPersRecapDiffe(null!=sInjuryPersRecapDiffe?sInjuryPersRecapDiffe:null);
		model.setSInjuryPersComple(null!=sInjuryPersComple?sInjuryPersComple:null);
		model.setSBirthBase(null!=sBirthBase?sBirthBase:null);
		model.setSBirthCompRatio(null!=sBirthCompRatio?sBirthCompRatio:null);
		model.setSBirthCompAmount(null!=sBirthCompAmount?sBirthCompAmount:null);
		model.setSBirthCompRecapAmount(null!=sBirthCompRecapAmount?sBirthCompRecapAmount:null);
		model.setSBirthCompRecapDiffe(null!=sBirthCompRecapDiffe?sBirthCompRecapDiffe:null);
		model.setSBirthCompOverpaid(null!=sBirthCompOverpaid?sBirthCompOverpaid:null);
		model.setSBirthPersRatio(null!=sBirthPersRatio?sBirthPersRatio:null);
		model.setSBirthPersAmount(null!=sBirthPersAmount?sBirthPersAmount:null);
		model.setSBirthPersRecapAmount(null!=sBirthPersRecapAmount?sBirthPersRecapAmount:null);
		model.setSBirthPersRecapDiffe(null!=sBirthPersRecapDiffe?sBirthPersRecapDiffe:null);
		model.setSBirthPersComple(null!=sBirthPersComple?sBirthPersComple:null);
		model.setSAnnuityBase(null!=sAnnuityBase?sAnnuityBase:null);
		model.setSAnnuityCompRatio(null!=sAnnuityCompRatio?sAnnuityCompRatio:null);
		model.setSAnnuityCompAmount(null!=sAnnuityCompAmount?sAnnuityCompAmount:null);
		model.setSAnnuityCompRecapAmount(null!=sAnnuityCompRecapAmount?sAnnuityCompRecapAmount:null);
		model.setSAnnuityCompRecapDiffe(null!=sAnnuityCompRecapDiffe?sAnnuityCompRecapDiffe:null);
		model.setSAnnuityCompOverpaid(null!=sAnnuityCompOverpaid?sAnnuityCompOverpaid:null);
		model.setSAnnuityPersRatio(null!=sAnnuityPersRatio?sAnnuityPersRatio:null);
		model.setSAnnuityPersAmount(null!=sAnnuityPersAmount?sAnnuityPersAmount:null);
		model.setSAnnuityPersRecapAmount(null!=sAnnuityPersRecapAmount?sAnnuityPersRecapAmount:null);
		model.setSAnnuityPersRecapDiffe(null!=sAnnuityPersRecapDiffe?sAnnuityPersRecapDiffe:null);
		model.setSAnnuityPersComple(null!=sAnnuityPersComple?sAnnuityPersComple:null);
		model.setSOvpBase(null!=sOvpBase?sOvpBase:null);
		model.setSOvpCompRatio(null!=sOvpCompRatio?sOvpCompRatio:null);
		model.setSOvpCompAmount(null!=sOvpCompAmount?sOvpCompAmount:null);
		model.setSOvpCompRecapAmount(null!=sOvpCompRecapAmount?sOvpCompRecapAmount:null);
		model.setSOvpCompRecapDiffe(null!=sOvpCompRecapDiffe?sOvpCompRecapDiffe:null);
		model.setSOvpCompOverpaid(null!=sOvpCompOverpaid?sOvpCompOverpaid:null);
		model.setSOvpPersRatio(null!=sOvpPersRatio?sOvpPersRatio:null);
		model.setSOvpPersAmount(null!=sOvpPersAmount?sOvpPersAmount:null);
		model.setSOvpPersRecapAmount(null!=sOvpPersRecapAmount?sOvpPersRecapAmount:null);
		model.setSOvpPersRecapDiffe(null!=sOvpPersRecapDiffe?sOvpPersRecapDiffe:null);
		model.setSOvpPersComple(null!=sOvpPersComple?sOvpPersComple:null);
		model.setSSpMediBase(null!=sSpMediBase?sSpMediBase:null);
		model.setSSpMediCompRatio(null!=sSpMediCompRatio?sSpMediCompRatio:null);
		model.setSSpMediCompAmount(null!=sSpMediCompAmount?sSpMediCompAmount:null);
		model.setSSpMediCompRecapAmount(null!=sSpMediCompRecapAmount?sSpMediCompRecapAmount:null);
		model.setSSpMediCompRecapDiffe(null!=sSpMediCompRecapDiffe?sSpMediCompRecapDiffe:null);
		model.setSSpMediCompOverpaid(null!=sSpMediCompOverpaid?sSpMediCompOverpaid:null);
		model.setSSpMediPersRatio(null!=sSpMediPersRatio?sSpMediPersRatio:null);
		model.setSSpMediPersAmount(null!=sSpMediPersAmount?sSpMediPersAmount:null);
		model.setSSpMediPersRecapAmount(null!=sSpMediPersRecapAmount?sSpMediPersRecapAmount:null);
		model.setSSpMediPersRecapDiffe(null!=sSpMediPersRecapDiffe?sSpMediPersRecapDiffe:null);
		model.setSSpMediPersComple(null!=sSpMediPersComple?sSpMediPersComple:null);
		model.setRemark4month(null!=remark4month && !"".equals(remark4month.trim())?remark4month.trim():null);
		model.setRemark4year(null!=remark4year && !"".equals(remark4year.trim())?remark4year.trim():null);
		model.setPersRecapDiffe4addup(null!=persRecapDiffe4addup?persRecapDiffe4addup:null);
		model.setPersCompleDiffe4addup(null!=persCompleDiffe4addup?persCompleDiffe4addup:null);
		model.setStatus(null!=status?status:null);
		model.setBtimpNo(null!=btimpNo && !"".equals(btimpNo.trim())?btimpNo.trim():null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
		
		model.setHosDepart1levelCns(departLevel1s);
		model.setHosDepart2levelCns(departLevel2s);
		model.setWkModalityCns(wkmodalitys);
		model.setCompNames(compNames);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[社保信息校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年06月07日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChSocialInfoModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		if(null == id)
		{
			stringBuffer.append("输入自增主键!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String name = request.getParameter(PARA_name);
		if(StringHelper.isNullOrEmpty(name))
		{
			stringBuffer.append("输入姓名!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String cardNo = request.getParameter(PARA_cardNo);
		String compName = request.getParameter(PARA_compName);
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String workAreaCn = request.getParameter(PARA_workAreaCn);
		String positCn = request.getParameter(PARA_positCn);
		String titleCn = request.getParameter(PARA_titleCn);
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String socialYmonth = request.getParameter(PARA_socialYmonth);
		if(StringHelper.isNullOrEmpty(socialYmonth))
		{
			stringBuffer.append("输入社保年月!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String sPenBaseStr = request.getParameter(PARA_sPenBase);
		BigDecimal sPenBase = !StringHelper.isNullOrEmpty(sPenBaseStr)?new BigDecimal(sPenBaseStr):null;
		String sPenCompRatioStr = request.getParameter(PARA_sPenCompRatio);
		BigDecimal sPenCompRatio = !StringHelper.isNullOrEmpty(sPenCompRatioStr)?new BigDecimal(sPenCompRatioStr):null;
		String sPenCompAmountStr = request.getParameter(PARA_sPenCompAmount);
		BigDecimal sPenCompAmount = !StringHelper.isNullOrEmpty(sPenCompAmountStr)?new BigDecimal(sPenCompAmountStr):null;
		String sPenCompRecapAmountStr = request.getParameter(PARA_sPenCompRecapAmount);
		BigDecimal sPenCompRecapAmount = !StringHelper.isNullOrEmpty(sPenCompRecapAmountStr)?new BigDecimal(sPenCompRecapAmountStr):null;
		String sPenCompRecapDiffeStr = request.getParameter(PARA_sPenCompRecapDiffe);
		BigDecimal sPenCompRecapDiffe = !StringHelper.isNullOrEmpty(sPenCompRecapDiffeStr)?new BigDecimal(sPenCompRecapDiffeStr):null;
		String sPenCompOverpaidStr = request.getParameter(PARA_sPenCompOverpaid);
		BigDecimal sPenCompOverpaid = !StringHelper.isNullOrEmpty(sPenCompOverpaidStr)?new BigDecimal(sPenCompOverpaidStr):null;
		String sPenPersRatioStr = request.getParameter(PARA_sPenPersRatio);
		BigDecimal sPenPersRatio = !StringHelper.isNullOrEmpty(sPenPersRatioStr)?new BigDecimal(sPenPersRatioStr):null;
		String sPenPersAmountStr = request.getParameter(PARA_sPenPersAmount);
		BigDecimal sPenPersAmount = !StringHelper.isNullOrEmpty(sPenPersAmountStr)?new BigDecimal(sPenPersAmountStr):null;
		String sPenPersRecapAmountStr = request.getParameter(PARA_sPenPersRecapAmount);
		BigDecimal sPenPersRecapAmount = !StringHelper.isNullOrEmpty(sPenPersRecapAmountStr)?new BigDecimal(sPenPersRecapAmountStr):null;
		String sPenPersRecapDiffeStr = request.getParameter(PARA_sPenPersRecapDiffe);
		BigDecimal sPenPersRecapDiffe = !StringHelper.isNullOrEmpty(sPenPersRecapDiffeStr)?new BigDecimal(sPenPersRecapDiffeStr):null;
		String sPenPersCompleStr = request.getParameter(PARA_sPenPersComple);
		BigDecimal sPenPersComple = !StringHelper.isNullOrEmpty(sPenPersCompleStr)?new BigDecimal(sPenPersCompleStr):null;
		String sMediBaseStr = request.getParameter(PARA_sMediBase);
		BigDecimal sMediBase = !StringHelper.isNullOrEmpty(sMediBaseStr)?new BigDecimal(sMediBaseStr):null;
		String sMediCompRatioStr = request.getParameter(PARA_sMediCompRatio);
		BigDecimal sMediCompRatio = !StringHelper.isNullOrEmpty(sMediCompRatioStr)?new BigDecimal(sMediCompRatioStr):null;
		String sMediCompAmountStr = request.getParameter(PARA_sMediCompAmount);
		BigDecimal sMediCompAmount = !StringHelper.isNullOrEmpty(sMediCompAmountStr)?new BigDecimal(sMediCompAmountStr):null;
		String sMediCompRecapAmountStr = request.getParameter(PARA_sMediCompRecapAmount);
		BigDecimal sMediCompRecapAmount = !StringHelper.isNullOrEmpty(sMediCompRecapAmountStr)?new BigDecimal(sMediCompRecapAmountStr):null;
		String sMediCompRecapDiffeStr = request.getParameter(PARA_sMediCompRecapDiffe);
		BigDecimal sMediCompRecapDiffe = !StringHelper.isNullOrEmpty(sMediCompRecapDiffeStr)?new BigDecimal(sMediCompRecapDiffeStr):null;
		String sMediCompOverpaidStr = request.getParameter(PARA_sMediCompOverpaid);
		BigDecimal sMediCompOverpaid = !StringHelper.isNullOrEmpty(sMediCompOverpaidStr)?new BigDecimal(sMediCompOverpaidStr):null;
		String sMediPersRatioStr = request.getParameter(PARA_sMediPersRatio);
		BigDecimal sMediPersRatio = !StringHelper.isNullOrEmpty(sMediPersRatioStr)?new BigDecimal(sMediPersRatioStr):null;
		String sMediPersAmountStr = request.getParameter(PARA_sMediPersAmount);
		BigDecimal sMediPersAmount = !StringHelper.isNullOrEmpty(sMediPersAmountStr)?new BigDecimal(sMediPersAmountStr):null;
		String sMediPersRecapAmountStr = request.getParameter(PARA_sMediPersRecapAmount);
		BigDecimal sMediPersRecapAmount = !StringHelper.isNullOrEmpty(sMediPersRecapAmountStr)?new BigDecimal(sMediPersRecapAmountStr):null;
		String sMediPersRecapDiffeStr = request.getParameter(PARA_sMediPersRecapDiffe);
		BigDecimal sMediPersRecapDiffe = !StringHelper.isNullOrEmpty(sMediPersRecapDiffeStr)?new BigDecimal(sMediPersRecapDiffeStr):null;
		String sMediPersCompleStr = request.getParameter(PARA_sMediPersComple);
		BigDecimal sMediPersComple = !StringHelper.isNullOrEmpty(sMediPersCompleStr)?new BigDecimal(sMediPersCompleStr):null;
		String sUnempBaseStr = request.getParameter(PARA_sUnempBase);
		BigDecimal sUnempBase = !StringHelper.isNullOrEmpty(sUnempBaseStr)?new BigDecimal(sUnempBaseStr):null;
		String sUnempCompRatioStr = request.getParameter(PARA_sUnempCompRatio);
		BigDecimal sUnempCompRatio = !StringHelper.isNullOrEmpty(sUnempCompRatioStr)?new BigDecimal(sUnempCompRatioStr):null;
		String sUnempCompAmountStr = request.getParameter(PARA_sUnempCompAmount);
		BigDecimal sUnempCompAmount = !StringHelper.isNullOrEmpty(sUnempCompAmountStr)?new BigDecimal(sUnempCompAmountStr):null;
		String sUnempCompRecapAmountStr = request.getParameter(PARA_sUnempCompRecapAmount);
		BigDecimal sUnempCompRecapAmount = !StringHelper.isNullOrEmpty(sUnempCompRecapAmountStr)?new BigDecimal(sUnempCompRecapAmountStr):null;
		String sUnempCompRecapDiffeStr = request.getParameter(PARA_sUnempCompRecapDiffe);
		BigDecimal sUnempCompRecapDiffe = !StringHelper.isNullOrEmpty(sUnempCompRecapDiffeStr)?new BigDecimal(sUnempCompRecapDiffeStr):null;
		String sUnempCompOverpaidStr = request.getParameter(PARA_sUnempCompOverpaid);
		BigDecimal sUnempCompOverpaid = !StringHelper.isNullOrEmpty(sUnempCompOverpaidStr)?new BigDecimal(sUnempCompOverpaidStr):null;
		String sUnempPersRatioStr = request.getParameter(PARA_sUnempPersRatio);
		BigDecimal sUnempPersRatio = !StringHelper.isNullOrEmpty(sUnempPersRatioStr)?new BigDecimal(sUnempPersRatioStr):null;
		String sUnempPersAmountStr = request.getParameter(PARA_sUnempPersAmount);
		BigDecimal sUnempPersAmount = !StringHelper.isNullOrEmpty(sUnempPersAmountStr)?new BigDecimal(sUnempPersAmountStr):null;
		String sUnempPersRecapAmountStr = request.getParameter(PARA_sUnempPersRecapAmount);
		BigDecimal sUnempPersRecapAmount = !StringHelper.isNullOrEmpty(sUnempPersRecapAmountStr)?new BigDecimal(sUnempPersRecapAmountStr):null;
		String sUnempPersRecapDiffeStr = request.getParameter(PARA_sUnempPersRecapDiffe);
		BigDecimal sUnempPersRecapDiffe = !StringHelper.isNullOrEmpty(sUnempPersRecapDiffeStr)?new BigDecimal(sUnempPersRecapDiffeStr):null;
		String sUnempPersCompleStr = request.getParameter(PARA_sUnempPersComple);
		BigDecimal sUnempPersComple = !StringHelper.isNullOrEmpty(sUnempPersCompleStr)?new BigDecimal(sUnempPersCompleStr):null;
		String sInjuryBaseStr = request.getParameter(PARA_sInjuryBase);
		BigDecimal sInjuryBase = !StringHelper.isNullOrEmpty(sInjuryBaseStr)?new BigDecimal(sInjuryBaseStr):null;
		String sInjuryCompRatioStr = request.getParameter(PARA_sInjuryCompRatio);
		BigDecimal sInjuryCompRatio = !StringHelper.isNullOrEmpty(sInjuryCompRatioStr)?new BigDecimal(sInjuryCompRatioStr):null;
		String sInjuryCompAmountStr = request.getParameter(PARA_sInjuryCompAmount);
		BigDecimal sInjuryCompAmount = !StringHelper.isNullOrEmpty(sInjuryCompAmountStr)?new BigDecimal(sInjuryCompAmountStr):null;
		String sInjuryCompRecapAmountStr = request.getParameter(PARA_sInjuryCompRecapAmount);
		BigDecimal sInjuryCompRecapAmount = !StringHelper.isNullOrEmpty(sInjuryCompRecapAmountStr)?new BigDecimal(sInjuryCompRecapAmountStr):null;
		String sInjuryCompRecapDiffeStr = request.getParameter(PARA_sInjuryCompRecapDiffe);
		BigDecimal sInjuryCompRecapDiffe = !StringHelper.isNullOrEmpty(sInjuryCompRecapDiffeStr)?new BigDecimal(sInjuryCompRecapDiffeStr):null;
		String sInjuryCompOverpaidStr = request.getParameter(PARA_sInjuryCompOverpaid);
		BigDecimal sInjuryCompOverpaid = !StringHelper.isNullOrEmpty(sInjuryCompOverpaidStr)?new BigDecimal(sInjuryCompOverpaidStr):null;
		String sInjuryPersRatioStr = request.getParameter(PARA_sInjuryPersRatio);
		BigDecimal sInjuryPersRatio = !StringHelper.isNullOrEmpty(sInjuryPersRatioStr)?new BigDecimal(sInjuryPersRatioStr):null;
		String sInjuryPersAmountStr = request.getParameter(PARA_sInjuryPersAmount);
		BigDecimal sInjuryPersAmount = !StringHelper.isNullOrEmpty(sInjuryPersAmountStr)?new BigDecimal(sInjuryPersAmountStr):null;
		String sInjuryPersRecapAmountStr = request.getParameter(PARA_sInjuryPersRecapAmount);
		BigDecimal sInjuryPersRecapAmount = !StringHelper.isNullOrEmpty(sInjuryPersRecapAmountStr)?new BigDecimal(sInjuryPersRecapAmountStr):null;
		String sInjuryPersRecapDiffeStr = request.getParameter(PARA_sInjuryPersRecapDiffe);
		BigDecimal sInjuryPersRecapDiffe = !StringHelper.isNullOrEmpty(sInjuryPersRecapDiffeStr)?new BigDecimal(sInjuryPersRecapDiffeStr):null;
		String sInjuryPersCompleStr = request.getParameter(PARA_sInjuryPersComple);
		BigDecimal sInjuryPersComple = !StringHelper.isNullOrEmpty(sInjuryPersCompleStr)?new BigDecimal(sInjuryPersCompleStr):null;
		String sBirthBaseStr = request.getParameter(PARA_sBirthBase);
		BigDecimal sBirthBase = !StringHelper.isNullOrEmpty(sBirthBaseStr)?new BigDecimal(sBirthBaseStr):null;
		String sBirthCompRatioStr = request.getParameter(PARA_sBirthCompRatio);
		BigDecimal sBirthCompRatio = !StringHelper.isNullOrEmpty(sBirthCompRatioStr)?new BigDecimal(sBirthCompRatioStr):null;
		String sBirthCompAmountStr = request.getParameter(PARA_sBirthCompAmount);
		BigDecimal sBirthCompAmount = !StringHelper.isNullOrEmpty(sBirthCompAmountStr)?new BigDecimal(sBirthCompAmountStr):null;
		String sBirthCompRecapAmountStr = request.getParameter(PARA_sBirthCompRecapAmount);
		BigDecimal sBirthCompRecapAmount = !StringHelper.isNullOrEmpty(sBirthCompRecapAmountStr)?new BigDecimal(sBirthCompRecapAmountStr):null;
		String sBirthCompRecapDiffeStr = request.getParameter(PARA_sBirthCompRecapDiffe);
		BigDecimal sBirthCompRecapDiffe = !StringHelper.isNullOrEmpty(sBirthCompRecapDiffeStr)?new BigDecimal(sBirthCompRecapDiffeStr):null;
		String sBirthCompOverpaidStr = request.getParameter(PARA_sBirthCompOverpaid);
		BigDecimal sBirthCompOverpaid = !StringHelper.isNullOrEmpty(sBirthCompOverpaidStr)?new BigDecimal(sBirthCompOverpaidStr):null;
		String sBirthPersRatioStr = request.getParameter(PARA_sBirthPersRatio);
		BigDecimal sBirthPersRatio = !StringHelper.isNullOrEmpty(sBirthPersRatioStr)?new BigDecimal(sBirthPersRatioStr):null;
		String sBirthPersAmountStr = request.getParameter(PARA_sBirthPersAmount);
		BigDecimal sBirthPersAmount = !StringHelper.isNullOrEmpty(sBirthPersAmountStr)?new BigDecimal(sBirthPersAmountStr):null;
		String sBirthPersRecapAmountStr = request.getParameter(PARA_sBirthPersRecapAmount);
		BigDecimal sBirthPersRecapAmount = !StringHelper.isNullOrEmpty(sBirthPersRecapAmountStr)?new BigDecimal(sBirthPersRecapAmountStr):null;
		String sBirthPersRecapDiffeStr = request.getParameter(PARA_sBirthPersRecapDiffe);
		BigDecimal sBirthPersRecapDiffe = !StringHelper.isNullOrEmpty(sBirthPersRecapDiffeStr)?new BigDecimal(sBirthPersRecapDiffeStr):null;
		String sBirthPersCompleStr = request.getParameter(PARA_sBirthPersComple);
		BigDecimal sBirthPersComple = !StringHelper.isNullOrEmpty(sBirthPersCompleStr)?new BigDecimal(sBirthPersCompleStr):null;
		String sAnnuityBaseStr = request.getParameter(PARA_sAnnuityBase);
		BigDecimal sAnnuityBase = !StringHelper.isNullOrEmpty(sAnnuityBaseStr)?new BigDecimal(sAnnuityBaseStr):null;
		String sAnnuityCompRatioStr = request.getParameter(PARA_sAnnuityCompRatio);
		BigDecimal sAnnuityCompRatio = !StringHelper.isNullOrEmpty(sAnnuityCompRatioStr)?new BigDecimal(sAnnuityCompRatioStr):null;
		String sAnnuityCompAmountStr = request.getParameter(PARA_sAnnuityCompAmount);
		BigDecimal sAnnuityCompAmount = !StringHelper.isNullOrEmpty(sAnnuityCompAmountStr)?new BigDecimal(sAnnuityCompAmountStr):null;
		String sAnnuityCompRecapAmountStr = request.getParameter(PARA_sAnnuityCompRecapAmount);
		BigDecimal sAnnuityCompRecapAmount = !StringHelper.isNullOrEmpty(sAnnuityCompRecapAmountStr)?new BigDecimal(sAnnuityCompRecapAmountStr):null;
		String sAnnuityCompRecapDiffeStr = request.getParameter(PARA_sAnnuityCompRecapDiffe);
		BigDecimal sAnnuityCompRecapDiffe = !StringHelper.isNullOrEmpty(sAnnuityCompRecapDiffeStr)?new BigDecimal(sAnnuityCompRecapDiffeStr):null;
		String sAnnuityCompOverpaidStr = request.getParameter(PARA_sAnnuityCompOverpaid);
		BigDecimal sAnnuityCompOverpaid = !StringHelper.isNullOrEmpty(sAnnuityCompOverpaidStr)?new BigDecimal(sAnnuityCompOverpaidStr):null;
		String sAnnuityPersRatioStr = request.getParameter(PARA_sAnnuityPersRatio);
		BigDecimal sAnnuityPersRatio = !StringHelper.isNullOrEmpty(sAnnuityPersRatioStr)?new BigDecimal(sAnnuityPersRatioStr):null;
		String sAnnuityPersAmountStr = request.getParameter(PARA_sAnnuityPersAmount);
		BigDecimal sAnnuityPersAmount = !StringHelper.isNullOrEmpty(sAnnuityPersAmountStr)?new BigDecimal(sAnnuityPersAmountStr):null;
		String sAnnuityPersRecapAmountStr = request.getParameter(PARA_sAnnuityPersRecapAmount);
		BigDecimal sAnnuityPersRecapAmount = !StringHelper.isNullOrEmpty(sAnnuityPersRecapAmountStr)?new BigDecimal(sAnnuityPersRecapAmountStr):null;
		String sAnnuityPersRecapDiffeStr = request.getParameter(PARA_sAnnuityPersRecapDiffe);
		BigDecimal sAnnuityPersRecapDiffe = !StringHelper.isNullOrEmpty(sAnnuityPersRecapDiffeStr)?new BigDecimal(sAnnuityPersRecapDiffeStr):null;
		String sAnnuityPersCompleStr = request.getParameter(PARA_sAnnuityPersComple);
		BigDecimal sAnnuityPersComple = !StringHelper.isNullOrEmpty(sAnnuityPersCompleStr)?new BigDecimal(sAnnuityPersCompleStr):null;
		String sOvpBaseStr = request.getParameter(PARA_sOvpBase);
		BigDecimal sOvpBase = !StringHelper.isNullOrEmpty(sOvpBaseStr)?new BigDecimal(sOvpBaseStr):null;
		String sOvpCompRatioStr = request.getParameter(PARA_sOvpCompRatio);
		BigDecimal sOvpCompRatio = !StringHelper.isNullOrEmpty(sOvpCompRatioStr)?new BigDecimal(sOvpCompRatioStr):null;
		String sOvpCompAmountStr = request.getParameter(PARA_sOvpCompAmount);
		BigDecimal sOvpCompAmount = !StringHelper.isNullOrEmpty(sOvpCompAmountStr)?new BigDecimal(sOvpCompAmountStr):null;
		String sOvpCompRecapAmountStr = request.getParameter(PARA_sOvpCompRecapAmount);
		BigDecimal sOvpCompRecapAmount = !StringHelper.isNullOrEmpty(sOvpCompRecapAmountStr)?new BigDecimal(sOvpCompRecapAmountStr):null;
		String sOvpCompRecapDiffeStr = request.getParameter(PARA_sOvpCompRecapDiffe);
		BigDecimal sOvpCompRecapDiffe = !StringHelper.isNullOrEmpty(sOvpCompRecapDiffeStr)?new BigDecimal(sOvpCompRecapDiffeStr):null;
		String sOvpCompOverpaidStr = request.getParameter(PARA_sOvpCompOverpaid);
		BigDecimal sOvpCompOverpaid = !StringHelper.isNullOrEmpty(sOvpCompOverpaidStr)?new BigDecimal(sOvpCompOverpaidStr):null;
		String sOvpPersRatioStr = request.getParameter(PARA_sOvpPersRatio);
		BigDecimal sOvpPersRatio = !StringHelper.isNullOrEmpty(sOvpPersRatioStr)?new BigDecimal(sOvpPersRatioStr):null;
		String sOvpPersAmountStr = request.getParameter(PARA_sOvpPersAmount);
		BigDecimal sOvpPersAmount = !StringHelper.isNullOrEmpty(sOvpPersAmountStr)?new BigDecimal(sOvpPersAmountStr):null;
		String sOvpPersRecapAmountStr = request.getParameter(PARA_sOvpPersRecapAmount);
		BigDecimal sOvpPersRecapAmount = !StringHelper.isNullOrEmpty(sOvpPersRecapAmountStr)?new BigDecimal(sOvpPersRecapAmountStr):null;
		String sOvpPersRecapDiffeStr = request.getParameter(PARA_sOvpPersRecapDiffe);
		BigDecimal sOvpPersRecapDiffe = !StringHelper.isNullOrEmpty(sOvpPersRecapDiffeStr)?new BigDecimal(sOvpPersRecapDiffeStr):null;
		String sOvpPersCompleStr = request.getParameter(PARA_sOvpPersComple);
		BigDecimal sOvpPersComple = !StringHelper.isNullOrEmpty(sOvpPersCompleStr)?new BigDecimal(sOvpPersCompleStr):null;
		String sSpMediBaseStr = request.getParameter(PARA_sSpMediBase);
		BigDecimal sSpMediBase = !StringHelper.isNullOrEmpty(sSpMediBaseStr)?new BigDecimal(sSpMediBaseStr):null;
		String sSpMediCompRatioStr = request.getParameter(PARA_sSpMediCompRatio);
		BigDecimal sSpMediCompRatio = !StringHelper.isNullOrEmpty(sSpMediCompRatioStr)?new BigDecimal(sSpMediCompRatioStr):null;
		String sSpMediCompAmountStr = request.getParameter(PARA_sSpMediCompAmount);
		BigDecimal sSpMediCompAmount = !StringHelper.isNullOrEmpty(sSpMediCompAmountStr)?new BigDecimal(sSpMediCompAmountStr):null;
		String sSpMediCompRecapAmountStr = request.getParameter(PARA_sSpMediCompRecapAmount);
		BigDecimal sSpMediCompRecapAmount = !StringHelper.isNullOrEmpty(sSpMediCompRecapAmountStr)?new BigDecimal(sSpMediCompRecapAmountStr):null;
		String sSpMediCompRecapDiffeStr = request.getParameter(PARA_sSpMediCompRecapDiffe);
		BigDecimal sSpMediCompRecapDiffe = !StringHelper.isNullOrEmpty(sSpMediCompRecapDiffeStr)?new BigDecimal(sSpMediCompRecapDiffeStr):null;
		String sSpMediCompOverpaidStr = request.getParameter(PARA_sSpMediCompOverpaid);
		BigDecimal sSpMediCompOverpaid = !StringHelper.isNullOrEmpty(sSpMediCompOverpaidStr)?new BigDecimal(sSpMediCompOverpaidStr):null;
		String sSpMediPersRatioStr = request.getParameter(PARA_sSpMediPersRatio);
		BigDecimal sSpMediPersRatio = !StringHelper.isNullOrEmpty(sSpMediPersRatioStr)?new BigDecimal(sSpMediPersRatioStr):null;
		String sSpMediPersAmountStr = request.getParameter(PARA_sSpMediPersAmount);
		BigDecimal sSpMediPersAmount = !StringHelper.isNullOrEmpty(sSpMediPersAmountStr)?new BigDecimal(sSpMediPersAmountStr):null;
		String sSpMediPersRecapAmountStr = request.getParameter(PARA_sSpMediPersRecapAmount);
		BigDecimal sSpMediPersRecapAmount = !StringHelper.isNullOrEmpty(sSpMediPersRecapAmountStr)?new BigDecimal(sSpMediPersRecapAmountStr):null;
		String sSpMediPersRecapDiffeStr = request.getParameter(PARA_sSpMediPersRecapDiffe);
		BigDecimal sSpMediPersRecapDiffe = !StringHelper.isNullOrEmpty(sSpMediPersRecapDiffeStr)?new BigDecimal(sSpMediPersRecapDiffeStr):null;
		String sSpMediPersCompleStr = request.getParameter(PARA_sSpMediPersComple);
		BigDecimal sSpMediPersComple = !StringHelper.isNullOrEmpty(sSpMediPersCompleStr)?new BigDecimal(sSpMediPersCompleStr):null;
		String remark4month = request.getParameter(PARA_remark4month);
		String remark4year = request.getParameter(PARA_remark4year);
		String persRecapDiffe4addupStr = request.getParameter(PARA_persRecapDiffe4addup);
		BigDecimal persRecapDiffe4addup = !StringHelper.isNullOrEmpty(persRecapDiffe4addupStr)?new BigDecimal(persRecapDiffe4addupStr):null;
		String persCompleDiffe4addupStr = request.getParameter(PARA_persCompleDiffe4addup);
		BigDecimal persCompleDiffe4addup = !StringHelper.isNullOrEmpty(persCompleDiffe4addupStr)?new BigDecimal(persCompleDiffe4addupStr):null;
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		if(null == status)
		{
			stringBuffer.append("输入状态 0：正常  1：异常!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String btimpNo = request.getParameter(PARA_btimpNo);
		if(StringHelper.isNullOrEmpty(btimpNo))
		{
			stringBuffer.append("输入导入编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String remark = request.getParameter(PARA_remark);
		String createTimeStr = request.getParameter(PARA_createTime);
		Date createTime = !StringHelper.isNullOrEmpty(createTimeStr)?DateTimeHelper.strToDate(createTimeStr, "yyyy-MM-dd"):null;
		String updateTimeStr = request.getParameter(PARA_updateTime);
		Date updateTime = !StringHelper.isNullOrEmpty(updateTimeStr)?DateTimeHelper.strToDate(updateTimeStr, "yyyy-MM-dd"):null;
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setName(null!=name && !"".equals(name.trim())?name.trim():null);
		model.setCardNo(null!=cardNo && !"".equals(cardNo.trim())?cardNo.trim():null);
		model.setCompName(null!=compName && !"".equals(compName.trim())?compName.trim():null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWorkAreaCn(null!=workAreaCn && !"".equals(workAreaCn.trim())?workAreaCn.trim():null);
		model.setPositCn(null!=positCn && !"".equals(positCn.trim())?positCn.trim():null);
		model.setTitleCn(null!=titleCn && !"".equals(titleCn.trim())?titleCn.trim():null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setSocialYmonth(null!=socialYmonth && !"".equals(socialYmonth.trim())?socialYmonth.trim():null);
		model.setSPenBase(null!=sPenBase?sPenBase:null);
		model.setSPenCompRatio(null!=sPenCompRatio?sPenCompRatio:null);
		model.setSPenCompAmount(null!=sPenCompAmount?sPenCompAmount:null);
		model.setSPenCompRecapAmount(null!=sPenCompRecapAmount?sPenCompRecapAmount:null);
		model.setSPenCompRecapDiffe(null!=sPenCompRecapDiffe?sPenCompRecapDiffe:null);
		model.setSPenCompOverpaid(null!=sPenCompOverpaid?sPenCompOverpaid:null);
		model.setSPenPersRatio(null!=sPenPersRatio?sPenPersRatio:null);
		model.setSPenPersAmount(null!=sPenPersAmount?sPenPersAmount:null);
		model.setSPenPersRecapAmount(null!=sPenPersRecapAmount?sPenPersRecapAmount:null);
		model.setSPenPersRecapDiffe(null!=sPenPersRecapDiffe?sPenPersRecapDiffe:null);
		model.setSPenPersComple(null!=sPenPersComple?sPenPersComple:null);
		model.setSMediBase(null!=sMediBase?sMediBase:null);
		model.setSMediCompRatio(null!=sMediCompRatio?sMediCompRatio:null);
		model.setSMediCompAmount(null!=sMediCompAmount?sMediCompAmount:null);
		model.setSMediCompRecapAmount(null!=sMediCompRecapAmount?sMediCompRecapAmount:null);
		model.setSMediCompRecapDiffe(null!=sMediCompRecapDiffe?sMediCompRecapDiffe:null);
		model.setSMediCompOverpaid(null!=sMediCompOverpaid?sMediCompOverpaid:null);
		model.setSMediPersRatio(null!=sMediPersRatio?sMediPersRatio:null);
		model.setSMediPersAmount(null!=sMediPersAmount?sMediPersAmount:null);
		model.setSMediPersRecapAmount(null!=sMediPersRecapAmount?sMediPersRecapAmount:null);
		model.setSMediPersRecapDiffe(null!=sMediPersRecapDiffe?sMediPersRecapDiffe:null);
		model.setSMediPersComple(null!=sMediPersComple?sMediPersComple:null);
		model.setSUnempBase(null!=sUnempBase?sUnempBase:null);
		model.setSUnempCompRatio(null!=sUnempCompRatio?sUnempCompRatio:null);
		model.setSUnempCompAmount(null!=sUnempCompAmount?sUnempCompAmount:null);
		model.setSUnempCompRecapAmount(null!=sUnempCompRecapAmount?sUnempCompRecapAmount:null);
		model.setSUnempCompRecapDiffe(null!=sUnempCompRecapDiffe?sUnempCompRecapDiffe:null);
		model.setSUnempCompOverpaid(null!=sUnempCompOverpaid?sUnempCompOverpaid:null);
		model.setSUnempPersRatio(null!=sUnempPersRatio?sUnempPersRatio:null);
		model.setSUnempPersAmount(null!=sUnempPersAmount?sUnempPersAmount:null);
		model.setSUnempPersRecapAmount(null!=sUnempPersRecapAmount?sUnempPersRecapAmount:null);
		model.setSUnempPersRecapDiffe(null!=sUnempPersRecapDiffe?sUnempPersRecapDiffe:null);
		model.setSUnempPersComple(null!=sUnempPersComple?sUnempPersComple:null);
		model.setSInjuryBase(null!=sInjuryBase?sInjuryBase:null);
		model.setSInjuryCompRatio(null!=sInjuryCompRatio?sInjuryCompRatio:null);
		model.setSInjuryCompAmount(null!=sInjuryCompAmount?sInjuryCompAmount:null);
		model.setSInjuryCompRecapAmount(null!=sInjuryCompRecapAmount?sInjuryCompRecapAmount:null);
		model.setSInjuryCompRecapDiffe(null!=sInjuryCompRecapDiffe?sInjuryCompRecapDiffe:null);
		model.setSInjuryCompOverpaid(null!=sInjuryCompOverpaid?sInjuryCompOverpaid:null);
		model.setSInjuryPersRatio(null!=sInjuryPersRatio?sInjuryPersRatio:null);
		model.setSInjuryPersAmount(null!=sInjuryPersAmount?sInjuryPersAmount:null);
		model.setSInjuryPersRecapAmount(null!=sInjuryPersRecapAmount?sInjuryPersRecapAmount:null);
		model.setSInjuryPersRecapDiffe(null!=sInjuryPersRecapDiffe?sInjuryPersRecapDiffe:null);
		model.setSInjuryPersComple(null!=sInjuryPersComple?sInjuryPersComple:null);
		model.setSBirthBase(null!=sBirthBase?sBirthBase:null);
		model.setSBirthCompRatio(null!=sBirthCompRatio?sBirthCompRatio:null);
		model.setSBirthCompAmount(null!=sBirthCompAmount?sBirthCompAmount:null);
		model.setSBirthCompRecapAmount(null!=sBirthCompRecapAmount?sBirthCompRecapAmount:null);
		model.setSBirthCompRecapDiffe(null!=sBirthCompRecapDiffe?sBirthCompRecapDiffe:null);
		model.setSBirthCompOverpaid(null!=sBirthCompOverpaid?sBirthCompOverpaid:null);
		model.setSBirthPersRatio(null!=sBirthPersRatio?sBirthPersRatio:null);
		model.setSBirthPersAmount(null!=sBirthPersAmount?sBirthPersAmount:null);
		model.setSBirthPersRecapAmount(null!=sBirthPersRecapAmount?sBirthPersRecapAmount:null);
		model.setSBirthPersRecapDiffe(null!=sBirthPersRecapDiffe?sBirthPersRecapDiffe:null);
		model.setSBirthPersComple(null!=sBirthPersComple?sBirthPersComple:null);
		model.setSAnnuityBase(null!=sAnnuityBase?sAnnuityBase:null);
		model.setSAnnuityCompRatio(null!=sAnnuityCompRatio?sAnnuityCompRatio:null);
		model.setSAnnuityCompAmount(null!=sAnnuityCompAmount?sAnnuityCompAmount:null);
		model.setSAnnuityCompRecapAmount(null!=sAnnuityCompRecapAmount?sAnnuityCompRecapAmount:null);
		model.setSAnnuityCompRecapDiffe(null!=sAnnuityCompRecapDiffe?sAnnuityCompRecapDiffe:null);
		model.setSAnnuityCompOverpaid(null!=sAnnuityCompOverpaid?sAnnuityCompOverpaid:null);
		model.setSAnnuityPersRatio(null!=sAnnuityPersRatio?sAnnuityPersRatio:null);
		model.setSAnnuityPersAmount(null!=sAnnuityPersAmount?sAnnuityPersAmount:null);
		model.setSAnnuityPersRecapAmount(null!=sAnnuityPersRecapAmount?sAnnuityPersRecapAmount:null);
		model.setSAnnuityPersRecapDiffe(null!=sAnnuityPersRecapDiffe?sAnnuityPersRecapDiffe:null);
		model.setSAnnuityPersComple(null!=sAnnuityPersComple?sAnnuityPersComple:null);
		model.setSOvpBase(null!=sOvpBase?sOvpBase:null);
		model.setSOvpCompRatio(null!=sOvpCompRatio?sOvpCompRatio:null);
		model.setSOvpCompAmount(null!=sOvpCompAmount?sOvpCompAmount:null);
		model.setSOvpCompRecapAmount(null!=sOvpCompRecapAmount?sOvpCompRecapAmount:null);
		model.setSOvpCompRecapDiffe(null!=sOvpCompRecapDiffe?sOvpCompRecapDiffe:null);
		model.setSOvpCompOverpaid(null!=sOvpCompOverpaid?sOvpCompOverpaid:null);
		model.setSOvpPersRatio(null!=sOvpPersRatio?sOvpPersRatio:null);
		model.setSOvpPersAmount(null!=sOvpPersAmount?sOvpPersAmount:null);
		model.setSOvpPersRecapAmount(null!=sOvpPersRecapAmount?sOvpPersRecapAmount:null);
		model.setSOvpPersRecapDiffe(null!=sOvpPersRecapDiffe?sOvpPersRecapDiffe:null);
		model.setSOvpPersComple(null!=sOvpPersComple?sOvpPersComple:null);
		model.setSSpMediBase(null!=sSpMediBase?sSpMediBase:null);
		model.setSSpMediCompRatio(null!=sSpMediCompRatio?sSpMediCompRatio:null);
		model.setSSpMediCompAmount(null!=sSpMediCompAmount?sSpMediCompAmount:null);
		model.setSSpMediCompRecapAmount(null!=sSpMediCompRecapAmount?sSpMediCompRecapAmount:null);
		model.setSSpMediCompRecapDiffe(null!=sSpMediCompRecapDiffe?sSpMediCompRecapDiffe:null);
		model.setSSpMediCompOverpaid(null!=sSpMediCompOverpaid?sSpMediCompOverpaid:null);
		model.setSSpMediPersRatio(null!=sSpMediPersRatio?sSpMediPersRatio:null);
		model.setSSpMediPersAmount(null!=sSpMediPersAmount?sSpMediPersAmount:null);
		model.setSSpMediPersRecapAmount(null!=sSpMediPersRecapAmount?sSpMediPersRecapAmount:null);
		model.setSSpMediPersRecapDiffe(null!=sSpMediPersRecapDiffe?sSpMediPersRecapDiffe:null);
		model.setSSpMediPersComple(null!=sSpMediPersComple?sSpMediPersComple:null);
		model.setRemark4month(null!=remark4month && !"".equals(remark4month.trim())?remark4month.trim():null);
		model.setRemark4year(null!=remark4year && !"".equals(remark4year.trim())?remark4year.trim():null);
		model.setPersRecapDiffe4addup(null!=persRecapDiffe4addup?persRecapDiffe4addup:null);
		model.setPersCompleDiffe4addup(null!=persCompleDiffe4addup?persCompleDiffe4addup:null);
		model.setStatus(null!=status?status:null);
		model.setBtimpNo(null!=btimpNo && !"".equals(btimpNo.trim())?btimpNo.trim():null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
		model.setCreateTime(null!=createTime?createTime:null);
		model.setUpdateTime(null!=updateTime?updateTime:null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(ModelMap modelMap) throws Exception{
		//填充字典数据
		fillCommonDict(modelMap);
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
			ChSocialInfoModel model = new ChSocialInfoModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			chSocialInfoBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model+",名称:"+model);
			//返回成功信息
			map =  LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(@RequestParam("id")Long id, ModelMap modelMap) throws Exception{
		if(null == id) throw new Exception("编辑数据时，主键为空!");
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			
			ChSocialInfoModel model = chSocialInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
		}catch(Exception e)
		{
			logger.error("加载社保信息失败！error:",e);
		}
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
			ChSocialInfoModel model = chSocialInfoBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			chSocialInfoBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getId()+",名称:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
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
			ChSocialInfoModel model = chSocialInfoBiz.queryById(id);
			if(model != null)
			{
				chSocialInfoBiz.delById4Logic(id);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model+",名称:"+model);
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
			chSocialInfoBiz.delByIds4Logic(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 查看详细页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="details.jhtm")
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) throws Exception{
		ChSocialInfoModel model = null;
		try {
			//填充字典数据
			fillCommonDict(modelMap);
			//加载详情对象
			model = chSocialInfoBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载社保信息失败！error:",e);
			log2Error(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}
	}
	//********************以下为扩展方法***********************
	
	/**
	 * <p>Discription:[转向导入当月社保页面]</p>
	 * Created on 2021年06月07日												       	 
	 * @author:huadf
	 */
	@RequestMapping("imp4CurMonth.jhtm")
	public void imp4CurMonth(HttpServletRequest request,ModelMap modelMap) throws Exception{
		modelMap.put("curMonth", DateTimeHelper.format(new Date(), "yyyy-MM"));
	}
	
	/**
	 * 导入文件
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
			//获取当前登录人
			UserInfoModel loginUser = this.getLoginUser(request);
			if(null == loginUser) return LayAjaxHelper.fail("当前未处于登录状态，请重新登录!");
			String socialYmonthStr = request.getParameter(PARA_socialYmonth);
			Date socialYmonthDate = !StringHelper.isNullOrEmpty(socialYmonthStr)?DateTimeHelper.strToDate(socialYmonthStr, "yyyy-MM"):null;
			String socialYmonth = null!=socialYmonthDate?DateTimeHelper.format(socialYmonthDate, "yyyy-MM"):null;
			if(null == socialYmonth)
				return LayAjaxHelper.fail("请选择社保年月!");
			String type = null;
			String fileName = null;
			
			String batchNo = "BSOC_"+System.currentTimeMillis()+(int)(Math.random()*10);
			//导入操作返回的结果数据
			Map<Boolean,Object> data = null;
        	for (MultipartFile item : file) {
        		fileName = item.getOriginalFilename();
        		type = null != fileName?fileName.substring(fileName.indexOf("."), fileName.length()):null;
        		//filePath = fileRoot+model.getBatchNo()+type;
        		if(!type.contains("xls") && !type.contains("xlsx"))
        			break;
        		inputs = item.getInputStream();
        		data = chSocialInfoBiz.doImport(inputs,socialYmonth,batchNo);
        	}
        	
        	//封装工资条导入记录
        	ChSocialRecordModel record = new ChSocialRecordModel();
        	record.setBatchNo(batchNo);
        	
        	record.setBatchUserAccount(loginUser.getAccount());
        	record.setBatchUser(loginUser.getUserRealName());
        	
			log2Info(request, Module_Name, LogDictionary.Module_Oper_import,"编号:"+record.getBatchNo() +",名称:社保");
			//返回成功信息
			StringBuffer msg = new StringBuffer();
			msg.append("导入"+(data.containsKey(true)?"成功!":"失败!"));
			if(data.containsKey(true)) {
				Map<String,Integer> dataMap = (Map<String,Integer>)data.get(true);
				msg.append("</br>[成功："+dataMap.get("succ")+" 失败:"+dataMap.get("fail")
				+" 已存在:"+dataMap.get("exist")+(dataMap.containsKey("excep")?" 异常:"+dataMap.get("excep"):"")
				+"  总量:"+dataMap.get("allSize")+"]");
				record.setSucNum(Long.valueOf(dataMap.get("succ")));
				record.setFaiNum(Long.valueOf(dataMap.get("fail")));
				record.setExeNum(Long.valueOf(dataMap.get("excep")));
				record.setExistNum(Long.valueOf(dataMap.get("exist")));
			}else {
				msg.append("</br>["+data.get(false)+"]");
			}
			record.setBatchMsg(msg.toString());
			chSocialRecordBiz.save(record);
				
			map =  data.containsKey(true)?LayAjaxHelper.success(msg.toString())
					:LayAjaxHelper.fail(msg.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL);
			logger.error("导入失败！error:",ex);
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
	
	@RequestMapping(value = "export.jhtm")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response) {
		//查询参数
		Page.Builder<ChSocialInfoExtModel> builder = new Page.Builder<ChSocialInfoExtModel>();
		try {
			setSearchParameters(request, builder);
			Page<ChSocialInfoExtModel> page = builder.build();
			//根据奖励项个数生成excel的header
			JSONObject headJson = SocialExportHeadUtil.createHead();
			if(null == headJson || headJson.isEmpty()) throw new Exception("表头信息异常");
			List<ChSocialInfoModel> data = chSocialInfoBiz.queryListWithSerial(page.getModel());
			
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "社保导出", data, headInfo);
		}catch(Exception e)
		{
			logger.error("社保单导出失败！error:",e);
		}
	}
	
	/**
	 * 撤销转向页面
	 * @param modelMap
	 */
	@RequestMapping(value="revoke.jhtm")
	public void revoke(ModelMap modelMap) throws Exception{
	}
	
	
	/**
	 * 执行撤销
	 * @param modelMap
	 */
	@RequestMapping(value="revoke/do.jhtm")
	@ResponseBody
	public Map<String, Object> doRevoke(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			String btImpNo = request.getParameter("btimpNo");
			if (StringHelper.isNullOrEmpty(btImpNo)) 
				return LayAjaxHelper.fail("批次编号为空!");
			//增加
			chSocialInfoBiz.delBatchByBtImpNo(btImpNo);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"撤销社保导入编号:" + btImpNo+",名称:批量撤销社保");
			//返回成功信息
			map =  LayAjaxHelper.success("撤销成功!");
		} catch (Exception ex) {
			log2Error(request, Module_Name, "批量撤销",ex.getMessage());
			map = LayAjaxHelper.fail("撤销失败!");
			ex.printStackTrace();
		}
		return map;
	}
	
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
	
	/**
	 * 若超过设置的缓存时间，则重新缓存搜索区域条件
	 */
	private synchronized void flushCached(ModelMap modelMap)
	{
		if(null == modelMap) return;
		//第一步：判断是否超过缓存时常，若超过，则重新加载数据
		Long curTime = System.currentTimeMillis();
		if(null == cachedTime || curTime - cachedTime > (24*60*60*1000))
		{
			cachedTime = curTime;//将当前时间设置为缓存时间
			cachedMap.clear();//清空当前的缓存数据列表
			//String fValues = null;
			List<String> fvallist = null;
			for(String column:cachedColumnsMap.keySet()) {
				fvallist = chSocialInfoBiz.loadSelectFields(column);
//				if(null!=fValues)
//					fvallist = Arrays.asList(fValues.split(","));
//				else
//					fvallist = new ArrayList<String>();
				if(null == fvallist)fvallist = new ArrayList<String>();
				cachedMap.put(cachedColumnsMap.get(column)+"List", fvallist);
			}
		}
		//第二步：重新返回缓存数据
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
			modelMap.addAttribute(key, JSON.toJSON(comboxs));//用于页面多选
		}
	}
}
