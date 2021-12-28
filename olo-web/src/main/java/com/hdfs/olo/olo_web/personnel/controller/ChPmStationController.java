package com.hdfs.olo.olo_web.personnel.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.hdfs.olo.olo_web.central.biz.ISystemDictItemBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosBranchBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosDepartBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmHosSecdepBiz;
import com.hdfs.olo.olo_web.personnel.biz.IChPmStationBiz;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosBranchModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosDepartModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmHosSecdepModel;
import com.hdfs.olo.olo_web.personnel.model.ChPmStationModel;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/** 
 * Description: [用工及岗位表服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/chPmStation/*")
public class ChPmStationController extends BaseController {
	
	public final static String Module_Name = "用工及岗位表";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ISystemDictBiz dictBiz;
	@Autowired
	private ISystemDictItemBiz dictItemBiz;
	@Autowired
	private IChPmStationBiz chPmStationBiz;
	@Autowired
	private IChPmHosBranchBiz chPmHosBranchBiz;
	@Autowired
	private IChPmHosDepartBiz chPmHosDepartBiz;
	@Autowired
	private IChPmHosSecdepBiz chPmHosSecdepBiz;
	
	private	final static String PARA_id = "id";
	private	final static String PARA_workerId = "workerId";
	private	final static String PARA_wagesId = "wagesId";
	private	final static String PARA_identity = "identity";
	private	final static String PARA_identityCn = "identityCn";
	private	final static String PARA_wkModality = "wkModality";
	private	final static String PARA_wkModalityCn = "wkModalityCn";
	private	final static String PARA_wkType = "wkType";
	private	final static String PARA_wkTypeCn = "wkTypeCn";
	private	final static String PARA_laborRelNo = "laborRelNo";
	private	final static String PARA_laborRelCn = "laborRelCn";
	private	final static String PARA_stationSitu = "stationSitu";
	private	final static String PARA_stationSituCn = "stationSituCn";
	private	final static String PARA_stationType = "stationType";
	private	final static String PARA_stationTypeCn = "stationTypeCn";
	private	final static String PARA_stationStatus = "stationStatus";
	private	final static String PARA_stationStatusCn = "stationStatusCn";
	private	final static String PARA_stationSeqNo = "stationSeqNo";
	private	final static String PARA_stationSeqCn = "stationSeqCn";
	private	final static String PARA_isMajorPerson = "isMajorPerson";
	private	final static String PARA_inMajorNow = "inMajorNow";
	private	final static String PARA_inMajorNowCn = "inMajorNowCn";
	private	final static String PARA_workArea = "workArea";
	private	final static String PARA_workAreaCn = "workAreaCn";
	private	final static String PARA_hosBranch = "hosBranch";
	private	final static String PARA_hosBranchCn = "hosBranchCn";
	private	final static String PARA_hosDepart1level = "hosDepart1level";
	private	final static String PARA_hosDepart1levelCn = "hosDepart1levelCn";
	private	final static String PARA_hosDepart2level = "hosDepart2level";
	private	final static String PARA_hosDepart2levelCn = "hosDepart2levelCn";
	private	final static String PARA_homeAddress = "homeAddress";
	private	final static String PARA_telphoneNo = "telphoneNo";
	private	final static String PARA_mailBox = "mailBox";
	private	final static String PARA_status = "status";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_createTime = "createTime";
	private	final static String PARA_updateTime = "updateTime";
	
	private final static String DICT_COMMON_CODES = "status,identity,wkType,wkModality,laborRel,stationSitu,"
			+ "stationType,stationStatus,workArea,stationSeq,YN,inMajor";
	
	/**
	 * <p>Discription:[用工及岗位表请求首页]</p>
	 * Created on 2021年03月29日												       	 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
	}
	 
	/**
	 * <p>Discription:[用工及岗位表首页数据加载-分页]</p>
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChPmStationModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<ChPmStationModel> retBody = new Result4Page<ChPmStationModel>();
		//查询参数
		Page.Builder<ChPmStationModel> builder = new Page.Builder<ChPmStationModel>();
		setSearchParameters(request, builder);
		Page<ChPmStationModel> page = builder.build();
		try {
			//分页
			chPmStationBiz.queryPage(page);
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
	 * <p>Discription:[用工及岗位表设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2021年03月29日								       	 
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChPmStationModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr  = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		String wagesId = request.getParameter(PARA_wagesId);
		String identityStr = request.getParameter(PARA_identity);
		Integer identity = !StringHelper.isNullOrEmpty(identityStr)?Integer.parseInt(identityStr):null;
		String identityCn = request.getParameter(PARA_identityCn);
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String wkTypeStr = request.getParameter(PARA_wkType);
		Integer wkType = !StringHelper.isNullOrEmpty(wkTypeStr)?Integer.parseInt(wkTypeStr):null;
		String wkTypeCn = request.getParameter(PARA_wkTypeCn);
		String laborRelNoStr = request.getParameter(PARA_laborRelNo);
		Integer laborRelNo = !StringHelper.isNullOrEmpty(laborRelNoStr)?Integer.parseInt(laborRelNoStr):null;
		String laborRelCn = request.getParameter(PARA_laborRelCn);
		String stationSituStr = request.getParameter(PARA_stationSitu);
		Integer stationSitu = !StringHelper.isNullOrEmpty(stationSituStr)?Integer.parseInt(stationSituStr):null;
		String stationSituCn = request.getParameter(PARA_stationSituCn);
		String stationTypeStr = request.getParameter(PARA_stationType);
		Integer stationType = !StringHelper.isNullOrEmpty(stationTypeStr)?Integer.parseInt(stationTypeStr):null;
		String stationTypeCn = request.getParameter(PARA_stationTypeCn);
		String stationStatusStr = request.getParameter(PARA_stationStatus);
		Integer stationStatus = !StringHelper.isNullOrEmpty(stationStatusStr)?Integer.parseInt(stationStatusStr):null;
		String stationStatusCn = request.getParameter(PARA_stationStatusCn);
		String stationSeqNoStr = request.getParameter(PARA_stationSeqNo);
		Integer stationSeqNo = !StringHelper.isNullOrEmpty(stationSeqNoStr)?Integer.parseInt(stationSeqNoStr):null;
		String stationSeqCn = request.getParameter(PARA_stationSeqCn);
		String isMajorPersonStr = request.getParameter(PARA_isMajorPerson);
		Integer isMajorPerson = !StringHelper.isNullOrEmpty(isMajorPersonStr)?Integer.parseInt(isMajorPersonStr):null;
		//String inMajorNowStr = request.getParameter(PARA_inMajorNow);
		//Integer inMajorNow = !StringHelper.isNullOrEmpty(inMajorNowStr)?Integer.parseInt(inMajorNowStr):null;
		String inMajorNowCn = request.getParameter(PARA_inMajorNowCn);
		String workAreaStr = request.getParameter(PARA_workArea);
		Integer workArea = !StringHelper.isNullOrEmpty(workAreaStr)?Integer.parseInt(workAreaStr):null;
		String workAreaCn = request.getParameter(PARA_workAreaCn);
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelStr = request.getParameter(PARA_hosDepart2level);
		Integer hosDepart2level = !StringHelper.isNullOrEmpty(hosDepart2levelStr)?Integer.parseInt(hosDepart2levelStr):null;
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String homeAddress = request.getParameter(PARA_homeAddress);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		String remark = request.getParameter(PARA_remark);
		status = null==status?DictionaryUtil.KEY_NORMAL:status;
		
		ChPmStationModel model = new ChPmStationModel();
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setIdentity(null!=identity?identity:null);
		model.setIdentityCn(null!=identityCn && !"".equals(identityCn.trim())?identityCn.trim():null);
		model.setWkModality(null!=wkModality?wkModality:null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWkType(null!=wkType?wkType:null);
		model.setWkTypeCn(null!=wkTypeCn && !"".equals(wkTypeCn.trim())?wkTypeCn.trim():null);
		model.setLaborRelNo(null!=laborRelNo?laborRelNo:null);
		model.setLaborRelCn(null!=laborRelCn && !"".equals(laborRelCn.trim())?laborRelCn.trim():null);
		model.setStationSitu(null!=stationSitu?stationSitu:null);
		model.setStationSituCn(null!=stationSituCn && !"".equals(stationSituCn.trim())?stationSituCn.trim():null);
		model.setStationType(null!=stationType?stationType:null);
		model.setStationTypeCn(null!=stationTypeCn && !"".equals(stationTypeCn.trim())?stationTypeCn.trim():null);
		model.setStationStatus(null!=stationStatus?stationStatus:null);
		model.setStationStatusCn(null!=stationStatusCn && !"".equals(stationStatusCn.trim())?stationStatusCn.trim():null);
		model.setStationSeqNo(null!=stationSeqNo?stationSeqNo:null);
		model.setStationSeqCn(null!=stationSeqCn && !"".equals(stationSeqCn.trim())?stationSeqCn.trim():null);
		model.setIsMajorPerson(null!=isMajorPerson?isMajorPerson:null);
		//model.setInMajorNow(null!=inMajorNow?inMajorNow:null);
		model.setInMajorNowCn(null!=inMajorNowCn&& !"".equals(inMajorNowCn)?inMajorNowCn.trim():null);
		model.setWorkArea(null!=workArea?workArea:null);
		model.setWorkAreaCn(null!=workAreaCn && !"".equals(workAreaCn.trim())?workAreaCn.trim():null);
		model.setHosBranch(null!=hosBranch?hosBranch:null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1level(null!=hosDepart1level?hosDepart1level:null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2level(null!=hosDepart2level?hosDepart2level:null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setHomeAddress(null!=homeAddress && !"".equals(homeAddress.trim())?homeAddress.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
        
        //置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[用工及岗位表校验]</p>
	 * @param request请求对象
	 * @param type  0:添加 1：修改
	 * @return Map key：true/false  val：
	 * Created on 2021年04月10日								       	 
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, ChPmStationModel model) {
		Map<Boolean,Object> retMap = new HashMap<Boolean,Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//请求参数置入
		String idStr = request.getParameter(PARA_id);
		Long id = !StringHelper.isNullOrEmpty(idStr)?Long.parseLong(idStr):null;
//		if(null == id)
//		{
//			stringBuffer.append("输入自增主键!");
//			stringBuffer.append(LayAjaxHelper.CR);
//		}
		String workerIdStr = request.getParameter(PARA_workerId);
		Long workerId = !StringHelper.isNullOrEmpty(workerIdStr)?Long.parseLong(workerIdStr):null;
		if(null == workerId)
		{
			stringBuffer.append("输入职工编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wagesId = request.getParameter(PARA_wagesId);
		if(StringHelper.isNullOrEmpty(wagesId))
		{
			stringBuffer.append("输入人员编号（工资出账）!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String identityStr = request.getParameter(PARA_identity);
		Integer identity = !StringHelper.isNullOrEmpty(identityStr)?Integer.parseInt(identityStr):null;
		if(null == identity)
		{
			stringBuffer.append("输入个人身份!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String identityCn = request.getParameter(PARA_identityCn);
		String wkModalityStr = request.getParameter(PARA_wkModality);
		Integer wkModality = !StringHelper.isNullOrEmpty(wkModalityStr)?Integer.parseInt(wkModalityStr):null;
		if(null == wkModality)
		{
			stringBuffer.append("输入用工形式!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wkModalityCn = request.getParameter(PARA_wkModalityCn);
		String wkTypeStr = request.getParameter(PARA_wkType);
		Integer wkType = !StringHelper.isNullOrEmpty(wkTypeStr)?Integer.parseInt(wkTypeStr):null;
		if(null == wkType)
		{
			stringBuffer.append("输入用工类型!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String wkTypeCn = request.getParameter(PARA_wkTypeCn);
		String laborRelNoStr = request.getParameter(PARA_laborRelNo);
		Integer laborRelNo = !StringHelper.isNullOrEmpty(laborRelNoStr)?Integer.parseInt(laborRelNoStr):null;
		if(null == laborRelNo)
		{
			stringBuffer.append("输入劳动关系编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String laborRelCn = request.getParameter(PARA_laborRelCn);
		String stationSituStr = request.getParameter(PARA_stationSitu);
		Integer stationSitu = !StringHelper.isNullOrEmpty(stationSituStr)?Integer.parseInt(stationSituStr):null;
		if(null == stationSitu)
		{
			stringBuffer.append("输入在岗情况!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String stationSituCn = request.getParameter(PARA_stationSituCn);
		String stationTypeStr = request.getParameter(PARA_stationType);
		Integer stationType = !StringHelper.isNullOrEmpty(stationTypeStr)?Integer.parseInt(stationTypeStr):null;
		if(null == stationType)
		{
			stringBuffer.append("输入岗位类型!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String stationTypeCn = request.getParameter(PARA_stationTypeCn);
		String stationStatusStr = request.getParameter(PARA_stationStatus);
		Integer stationStatus = !StringHelper.isNullOrEmpty(stationStatusStr)?Integer.parseInt(stationStatusStr):null;
		String stationStatusCn = request.getParameter(PARA_stationStatusCn);
		String stationSeqNoStr = request.getParameter(PARA_stationSeqNo);
		Integer stationSeqNo = !StringHelper.isNullOrEmpty(stationSeqNoStr)?Integer.parseInt(stationSeqNoStr):null;
		if(null == stationSeqNo)
		{
			stringBuffer.append("输入岗位序列编号!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String stationSeqCn = request.getParameter(PARA_stationSeqCn);
		String isMajorPersonStr = request.getParameter(PARA_isMajorPerson);
		Integer isMajorPerson = !StringHelper.isNullOrEmpty(isMajorPersonStr)?Integer.parseInt(isMajorPersonStr):null;
		if(null == isMajorPerson)
		{
			stringBuffer.append("输入是否专业人员!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		//String inMajorNowStr = request.getParameter(PARA_inMajorNow);
		//Integer inMajorNow = !StringHelper.isNullOrEmpty(inMajorNowStr)?Integer.parseInt(inMajorNowStr):null;
		String inMajorNowCn = request.getParameter(PARA_inMajorNowCn);
		
		String workAreaStr = request.getParameter(PARA_workArea);
		Integer workArea = !StringHelper.isNullOrEmpty(workAreaStr)?Integer.parseInt(workAreaStr):null;
		String workAreaCn = request.getParameter(PARA_workAreaCn);
		String hosBranchStr = request.getParameter(PARA_hosBranch);
		Integer hosBranch = !StringHelper.isNullOrEmpty(hosBranchStr)?Integer.parseInt(hosBranchStr):null;
		if(null == hosBranch)
		{
			stringBuffer.append("输入医院支部!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		String hosBranchCn = request.getParameter(PARA_hosBranchCn);
		String hosDepart1levelStr = request.getParameter(PARA_hosDepart1level);
		Integer hosDepart1level = !StringHelper.isNullOrEmpty(hosDepart1levelStr)?Integer.parseInt(hosDepart1levelStr):null;
		String hosDepart1levelCn = request.getParameter(PARA_hosDepart1levelCn);
		String hosDepart2levelStr = request.getParameter(PARA_hosDepart2level);
		Integer hosDepart2level = !StringHelper.isNullOrEmpty(hosDepart2levelStr)?Integer.parseInt(hosDepart2levelStr):null;
		String hosDepart2levelCn = request.getParameter(PARA_hosDepart2levelCn);
		String homeAddress = request.getParameter(PARA_homeAddress);
		String telphoneNo = request.getParameter(PARA_telphoneNo);
		String mailBox = request.getParameter(PARA_mailBox);
		String statusStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statusStr)?Integer.parseInt(statusStr):null;
		status = null == status?DictionaryUtil.KEY_NORMAL:status;
		String remark = request.getParameter(PARA_remark);
		
		if (stringBuffer.length() > 0) return;
		model.setId(null!=id?id:null);
		model.setWorkerId(null!=workerId?workerId:null);
		model.setWagesId(null!=wagesId && !"".equals(wagesId.trim())?wagesId.trim():null);
		model.setIdentity(null!=identity?identity:null);
		model.setIdentityCn(null!=identityCn && !"".equals(identityCn.trim())?identityCn.trim():null);
		model.setWkModality(null!=wkModality?wkModality:null);
		model.setWkModalityCn(null!=wkModalityCn && !"".equals(wkModalityCn.trim())?wkModalityCn.trim():null);
		model.setWkType(null!=wkType?wkType:null);
		model.setWkTypeCn(null!=wkTypeCn && !"".equals(wkTypeCn.trim())?wkTypeCn.trim():null);
		model.setLaborRelNo(null!=laborRelNo?laborRelNo:null);
		model.setLaborRelCn(null!=laborRelCn && !"".equals(laborRelCn.trim())?laborRelCn.trim():null);
		model.setStationSitu(null!=stationSitu?stationSitu:null);
		model.setStationSituCn(null!=stationSituCn && !"".equals(stationSituCn.trim())?stationSituCn.trim():null);
		model.setStationType(null!=stationType?stationType:null);
		model.setStationTypeCn(null!=stationTypeCn && !"".equals(stationTypeCn.trim())?stationTypeCn.trim():null);
		model.setStationStatus(null!=stationStatus?stationStatus:null);
		model.setStationStatusCn(null!=stationStatusCn && !"".equals(stationStatusCn.trim())?stationStatusCn.trim():null);
		model.setStationSeqNo(null!=stationSeqNo?stationSeqNo:null);
		model.setStationSeqCn(null!=stationSeqCn && !"".equals(stationSeqCn.trim())?stationSeqCn.trim():null);
		model.setIsMajorPerson(null!=isMajorPerson?isMajorPerson:null);
		//model.setInMajorNow(null!=inMajorNow?inMajorNow:null);
		model.setInMajorNowCn(null!=inMajorNowCn&& !"".equals(inMajorNowCn)?inMajorNowCn.trim():null);
		model.setWorkArea(null!=workArea?workArea:null);
		model.setWorkAreaCn(null!=workAreaCn && !"".equals(workAreaCn.trim())?workAreaCn.trim():null);
		model.setHosBranch(null!=hosBranch?hosBranch:null);
		model.setHosBranchCn(null!=hosBranchCn && !"".equals(hosBranchCn.trim())?hosBranchCn.trim():null);
		model.setHosDepart1level(null!=hosDepart1level?hosDepart1level:null);
		model.setHosDepart1levelCn(null!=hosDepart1levelCn && !"".equals(hosDepart1levelCn.trim())?hosDepart1levelCn.trim():null);
		model.setHosDepart2level(null!=hosDepart2level?hosDepart2level:null);
		model.setHosDepart2levelCn(null!=hosDepart2levelCn && !"".equals(hosDepart2levelCn.trim())?hosDepart2levelCn.trim():null);
		model.setHomeAddress(null!=homeAddress && !"".equals(homeAddress.trim())?homeAddress.trim():null);
		model.setTelphoneNo(null!=telphoneNo && !"".equals(telphoneNo.trim())?telphoneNo.trim():null);
		model.setMailBox(null!=mailBox && !"".equals(mailBox.trim())?mailBox.trim():null);
		model.setStatus(null!=status?status:null);
		model.setRemark(null!=remark && !"".equals(remark.trim())?remark.trim():null);
	}
	
	/**
	 * 转向新增页面
	 * @param modelMap
	 */
	@RequestMapping(value="add.jhtm")
	public void add(Long wkId,ModelMap modelMap) throws Exception{
		if(null == wkId) throw new Exception("职工编号不存在！");
		modelMap.addAttribute("wkId", wkId);
		//字典数据加载
		this.fillCommonDict(modelMap);
		
		//医院支部信息加载
		List<ChPmHosBranchModel> hbras = chPmHosBranchBiz.queryList(new ChPmHosBranchModel(),"hbh_no,hbh_name");
		modelMap.addAttribute("hbranchs", hbras);
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
			ChPmStationModel model = new ChPmStationModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			if(null != model.getId())
				return LayAjaxHelper.fail("当前职工的用工及岗位信息已录入，请勿重复录入！");
			//增加
			Long id = chPmStationBiz.save(model);
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
//	@RequestMapping(value="edit.jhtm")
//	public void edit(@RequestParam("id")Long id,ModelMap modelMap) throws Exception{
//		if(null == id) throw new Exception("主键为空!");
//		try {
//			List<ComboxItem> statusList = getStatusList();
//			modelMap.addAttribute("statusList", statusList);
//			
//			ChPmStationModel model = chPmStationBiz.queryById(id);
//			modelMap.addAttribute("model", model);
//		}catch(Exception e)
//		{
//			logger.error("加载用工及岗位表失败！error:",e);
//		}
//	}
	
	/**
	 * 转向编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//加载请求参数并返回页面
		String wkIdStr = request.getParameter("wkId");
		Long wkId = !StringHelper.isNullOrEmpty(wkIdStr)?Long.parseLong(wkIdStr):null;
		if(null == wkId) throw new Exception("职工编号为空");
		String oper = request.getParameter("oper");
		oper = !"edit".equals(oper)&&!"detail".equals(oper)?"add":oper;
		modelMap.addAttribute("oper", oper);
		modelMap.addAttribute("wkId", wkId);
		
		//加载岗位对象
		ChPmStationModel model = chPmStationBiz.queryByWkId(wkId);
		modelMap.addAttribute("model", model);
		
		//字典数据加载
		this.fillCommonDict(modelMap);
		
		//医院支部信息加载
		List<ChPmHosBranchModel> hbras = chPmHosBranchBiz.queryList(new ChPmHosBranchModel(),"hbh_no,hbh_name");
		modelMap.addAttribute("hbranchs", hbras);
		
		if(null == model)return;
		//获取一级科室和二级科室列表
		List<ChPmHosDepartModel> depLevel1 = chPmHosBranchBiz.queryByHbhNo(model.getHosBranch());
		List<ChPmHosSecdepModel> depLevel2 = chPmHosDepartBiz.queryByHdpNo(model.getHosDepart1level());
		modelMap.addAttribute("hdepLevel1", depLevel1);
		modelMap.addAttribute("hdepLevel2", depLevel2);
	}
	
	/**
	 *  编辑操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("workerId") Long wkId) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			ChPmStationModel model = null;
			ChPmStationModel model1 = chPmStationBiz.queryByWkId(wkId);
			if(null != model1) 
				model = model1;
			else 
				model = new ChPmStationModel();
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0 && null != model1) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			
			if(null != model1)
				chPmStationBiz.edit(model);
			else
				chPmStationBiz.save(model);
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
			ChPmStationModel model = chPmStationBiz.queryById(id);
			if(model != null)
			{
				chPmStationBiz.delById4Logic(id);
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
			chPmStationBiz.delByIds4Logic(ids);
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
		ChPmStationModel model = null;
		try {
			model = chPmStationBiz.queryById(id);
			modelMap.addAttribute("model", model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model+",名称:" + model);
		}catch(Exception e)
		{
			logger.error("加载用工及岗位表失败！error:",e);
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
