package com.hdfs.olo.olo_web.social.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;
import com.hdfs.olo.olo_web.social.biz.IChSocialInfoBiz;
import com.hdfs.olo.olo_web.social.biz.IStatistics4SocialBiz;
import com.hdfs.olo.olo_web.social.model.ChSocialInfoExtModel;

/** 
 * Description: [统计服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/social/statistics/*")
public class Statistics4SocialController extends BaseController{
	public final static String Module_Name = "社保统计";
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IChSocialInfoBiz chSocialInfoBiz;
	@Autowired
	private IStatistics4SocialBiz statistics4SocialBiz;
	@Autowired
	private IHuTCommonService huTCommonService;
	
	//工资条字典缓存时间
	Long cachedTime = null;
	Map<String,List<String>> cachedMap = new HashMap<String,List<String>>();
	private static final Map<String,Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String,Object>();
		cachedColumnsMap.put("HOS_DEPART_1LEVEL_CN", "depart1");
		cachedColumnsMap.put("HOS_DEPART_2LEVEL_CN", "depart2");
		cachedColumnsMap.put("WORK_AREA_CN", "wkareas");
		cachedColumnsMap.put("WK_MODALITY_CN", "modality");
	}
	
	/**
	 * <p>Discription:[社保统计首页]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		logger.info("xxx");
	}
	
	/**
	 * 社保统计》定向养老/医疗/失业/工伤页面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "{type}/tostatis.jhtm")
	public String toIndex(HttpServletRequest request,ModelMap modelMap,@PathVariable("type")int type) {
		return "social/statistics/"+type;
	}
	/**
	 * <p>Discription:[养老/医疗/失业/工伤统计]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "100/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st100(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择查询区间！");
				return retMap;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_100(startMonth, endMonth);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	@RequestMapping(value = "101/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st101(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择查询区间！");
				return retMap;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_101(startMonth, endMonth);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	@RequestMapping(value = "102/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st102(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择查询区间！");
				return retMap;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_102(startMonth, endMonth);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	@RequestMapping(value = "103/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st103(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择查询区间！");
				return retMap;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_103(startMonth, endMonth);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	
	//养老、医疗、失业、工伤的导出
	public void export10X(HttpServletResponse response,String startMonth,String endMonth,int type) {
		try {
			List<Map<String,Object>> data = null;
			switch(type)
			{
			case 100:
				data = statistics4SocialBiz.load4_100(startMonth, endMonth);
				break;
			case 101:
				data = statistics4SocialBiz.load4_101(startMonth, endMonth);
				break;
			case 102:
				data = statistics4SocialBiz.load4_102(startMonth, endMonth);
				break;
			case 103:
				data = statistics4SocialBiz.load4_103(startMonth, endMonth);
				break;
			}
			String typeCn = 100!=type?101!=type?102!=type?"工伤":"失业":"医疗":"养老";
			String tmp = "{'hTitle':'"+typeCn+"保险按月汇总表','heads':[{'field':'social_ymonth','title':'查询时间','rowS':0,'rowE':2,'colS':0,'colE':0},{'field':'','title':'当期缴费工资基数','rowS':0,'rowE':0,'colS':1,'colE':6,'subs':[{'field':'','title':'单位','rowS':1,'rowE':1,'colS':1,'colE':3,'subs':[{'field':'base_comp_total','title':'缴费工资总额[单]','rowS':2,'rowE':2,'colS':1,'colE':1},{'field':'base_comp_laohe','title':'劳合属地[单]','rowS':2,'rowE':2,'colS':2,'colE':2},{'field':'base_comp_laowu','title':'劳务属地[单]','rowS':2,'rowE':2,'colS':3,'colE':3}]},{'field':'','title':'个人','rowS':1,'rowE':1,'colS':4,'colE':6,'subs':[{'field':'base_pers_total','title':'缴费工资总额[个]','rowS':2,'rowE':2,'colS':4,'colE':5},{'field':'base_pers_laohe','title':'劳合属地[个]','rowS':2,'rowE':2,'colS':5,'colE':5},{'field':'base_pers_laowu','title':'劳务属地[个]','rowS':2,'rowE':2,'colS':6,'colE':6}]}]},{'field':'','title':'养老保险费','rowS':0,'rowE':0,'colS':7,'colE':24,'subs':[{'field':'','title':'当期征缴情况','rowS':1,'rowE':1,'colS':7,'colE':15,'subs':[{'field':'amount','title':'当期应缴金额','rowS':2,'rowE':2,'colS':7,'colE':7},{'field':'amount_laohe','title':'劳合属地当期应缴金额','rowS':2,'rowE':2,'colS':8,'colE':8},{'field':'amount_laohe_comp','title':'[劳合]其中：单位部分当期应缴金额','rowS':2,'rowE':2,'colS':9,'colE':9},{'field':'amount_laowu','title':'劳务属地当期应缴金额','rowS':2,'rowE':2,'colS':10,'colE':10},{'field':'amount_laowu_comp','title':'[劳务]其中：单位部分当期应缴金额','rowS':2,'rowE':2,'colS':11,'colE':11},{'field':'amount_recap','title':'当期实缴金额','rowS':2,'rowE':2,'colS':12,'colE':12},{'field':'amount_recap_laohe','title':'劳合属地当期实缴金额','rowS':2,'rowE':2,'colS':13,'colE':13},{'field':'amount_recap_laowu','title':'劳务属地当期实缴金额','rowS':2,'rowE':2,'colS':14,'colE':14},{'field':'rate_dangqi','title':'征缴率[当期]','rowS':2,'rowE':2,'colS':15,'colE':15}]},{'field':'','title':'累计征缴情况','rowS':1,'rowE':1,'colS':16,'colE':24,'subs':[{'field':'amount_addup','title':'累计应缴金额','rowS':2,'rowE':2,'colS':16,'colE':16},{'field':'amount_addup_laohe','title':'劳合属地累计应缴金额','rowS':2,'rowE':2,'colS':17,'colE':17},{'field':'amount_addup_laohe_comp','title':'[劳合]其中：单位部分累计应缴金额','rowS':2,'rowE':2,'colS':18,'colE':18},{'field':'amount_addup_laowu','title':'劳务属地累计应缴金额','rowS':2,'rowE':2,'colS':19,'colE':19},{'field':'amount_addup_laowu_comp','title':'[劳务]其中：单位部分累计应缴金额','rowS':2,'rowE':2,'colS':20,'colE':20},{'field':'amount_addup_recap','title':'累计实缴金额','rowS':2,'rowE':2,'colS':21,'colE':21},{'field':'amount_addup_recap_laohe','title':'劳合属地累计实缴金额','rowS':2,'rowE':2,'colS':22,'colE':22},{'field':'amount_addup_recap_laowu','title':'劳务属地累计实缴金额','rowS':2,'rowE':2,'colS':23,'colE':23},{'field':'rate_addup','title':'征缴率[累计]','rowS':2,'rowE':2,'colS':24,'colE':24}]}]}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, typeCn+"保险按月汇总表", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
		}
	}
	@RequestMapping(value = "100/export.jhtm")
	@ResponseBody
	public void export100(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保导出[养老]:查询区间为空!");
				return;
			}
			this.export10X(response, startMonth, endMonth, 100);
		}catch(Exception e)
		{
			logger.error("养老保险按月汇总导出失败！error:",e);
		}
	}
	@RequestMapping(value = "101/export.jhtm")
	@ResponseBody
	public void export101(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保导出[医疗]:查询区间为空!");
				return;
			}
			this.export10X(response, startMonth, endMonth, 101);
		}catch(Exception e)
		{
			logger.error("医疗保险按月汇总导出失败！error:",e);
		}
	}
	@RequestMapping(value = "102/export.jhtm")
	@ResponseBody
	public void export102(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保导出[失业]:查询区间为空!");
				return;
			}
			this.export10X(response, startMonth, endMonth, 102);
		}catch(Exception e)
		{
			logger.error("失业保险按月汇总导出失败！error:",e);
		}
	}
	@RequestMapping(value = "103/export.jhtm")
	@ResponseBody
	public void export103(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保导出[工伤]:查询区间为空!");
				return;
			}
			this.export10X(response, startMonth, endMonth, 103);
		}catch(Exception e)
		{
			logger.error("工伤保险按月汇总导出失败！error:",e);
		}
	}
	
	
	/**
	 * 社保统计》定向用工形式应缴汇总页面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "104/tostatis.jhtm")
	public String to104(HttpServletRequest request,ModelMap modelMap) {
		flushCached(modelMap);
		return "social/statistics/104";
	}
	
	@RequestMapping(value = "104/loadHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadHead104(HttpServletRequest request,ModelMap modelMap) {
		String startYear = request.getParameter("startYear");
		if(StringHelper.isNullOrEmpty(startYear))
			startYear = String.valueOf(DateTimeHelper.getYear(new Date()));
		String str = 
			"["+
		      "["+
		    	  "{field: '', title: '用工形式社保应缴金额统计表',align:'center',colspan:37,width:'8%'}"+
		      "],"+
		      "["+
		      	  "{field: 'modality', title: '用工形式',align:'center',width:'12%'},"+
		    	  "{field: 'stkey', title: '社保统计',align:'left',width:'12%'},"+
		    	  "{field: '"+startYear+"-01', title: '"+startYear+"-01',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-02', title: '"+startYear+"-02',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-03', title: '"+startYear+"-03',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-04', title: '"+startYear+"-04',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-05', title: '"+startYear+"-05',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-06', title: '"+startYear+"-06',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-07', title: '"+startYear+"-07',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-08', title: '"+startYear+"-08',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-09', title: '"+startYear+"-09',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-10', title: '"+startYear+"-10',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-11', title: '"+startYear+"-11',align:'left',width:'10%'},"+
		    	  "{field: '"+startYear+"-12', title: '"+startYear+"-12',align:'left',width:'10%'}"+
		      "]"+
		    "]";
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("heads", JSONArray.parse(str));
		retMap.put("startYear", startYear);
		return retMap;
	}
	
	@RequestMapping(value = "104/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st104(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startYear = request.getParameter("startYear");
			if(StringHelper.isNullOrEmpty(startYear))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择统计年份！");
				return retMap;
			}
			
			String modalitysStr = request.getParameter("wkModalityCns");
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			model.setStartYear(startYear);
			model.setStartMonth(startYear+"-01");
			model.setEndMonth(startYear+"-12");
			model.setWkModalityCns(modalitys);
			List<Map<String,Object>> data = statistics4SocialBiz.load4_104(model);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
			retMap.put("startYear", startYear);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	
	@RequestMapping(value = "104/export.jhtm")
	@ResponseBody
	public void export104(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startYear = request.getParameter("startYear");
			if(StringHelper.isNullOrEmpty(startYear))
			{
				logger.error("社保统计>用工形式应缴汇总：请选择统计年份");
				return;
			}
			
			String modalitysStr = request.getParameter("wkModalityCns");
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			model.setStartYear(startYear);
			model.setStartMonth(startYear+"-01");
			model.setEndMonth(startYear+"-12");
			model.setWkModalityCns(modalitys);
			List<Map<String,Object>> data = statistics4SocialBiz.load4_104(model);
			String tmp = "{'hTitle':'用工形式社保应缴金额统计表','heads':[{'field':'modality','title':'用工形式','rowS':0,'rowE':0,'colS':0,'colE':0},{'field':'stkey','title':'社保统计','rowS':0,'rowE':0,'colS':1,'colE':1},{'field':'"+startYear+"-01','title':'"+startYear+"-01','rowS':0,'rowE':0,'colS':2,'colE':2},{'field':'"+startYear+"-02','title':'"+startYear+"-02','rowS':0,'rowE':0,'colS':3,'colE':3},{'field':'"+startYear+"-03','title':'"+startYear+"-03','rowS':0,'rowE':0,'colS':4,'colE':4},{'field':'"+startYear+"-04','title':'"+startYear+"-04','rowS':0,'rowE':0,'colS':5,'colE':5},{'field':'"+startYear+"-05','title':'"+startYear+"-05','rowS':0,'rowE':0,'colS':6,'colE':6},{'field':'"+startYear+"-06','title':'"+startYear+"-06','rowS':0,'rowE':0,'colS':7,'colE':7},{'field':'"+startYear+"-07','title':'"+startYear+"-07','rowS':0,'rowE':0,'colS':8,'colE':8},{'field':'"+startYear+"-08','title':'"+startYear+"-08','rowS':0,'rowE':0,'colS':9,'colE':9},{'field':'"+startYear+"-09','title':'"+startYear+"-09','rowS':0,'rowE':0,'colS':10,'colE':10},{'field':'"+startYear+"-10','title':'"+startYear+"-10','rowS':0,'rowE':0,'colS':11,'colE':11},{'field':'"+startYear+"-11','title':'"+startYear+"-11','rowS':0,'rowE':0,'colS':12,'colE':12},{'field':'"+startYear+"-12','title':'"+startYear+"-12','rowS':0,'rowE':0,'colS':13,'colE':13}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			statistics4SocialBiz.export104(response, "用工形式社保应缴金额统计表", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
		}
	}
	
	/**
	 * 社保统计》定向社保参保人数月统计页面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "200/tostatis.jhtm")
	public String tost200(HttpServletRequest request,ModelMap modelMap) {
		return "social/statistics/200";
	}
	/**
	 * <p>Discription:[社保参保人数月统计]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "200/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st200(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择查询区间！");
				return retMap;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_200(startMonth, endMonth);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	
	@RequestMapping(value = "200/export.jhtm")
	@ResponseBody
	public void export200(HttpServletRequest request,HttpServletResponse response) {
		try {
			String startMonth = request.getParameter("select-start");
			String endMonth = request.getParameter("select-end");
			if(StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保导出[参保人数月统计]:查询区间为空!");
				return;
			}
			List<Map<String,Object>> data = statistics4SocialBiz.load4_200(startMonth, endMonth);
			String tmp = "{'hTitle':'社保参保人数月统计表','heads':[{'field':'social_ymonth','title':'社保年月','rowS':0,'rowE':2,'colS':0,'colE':0},{'field':'','title':'单位人数','rowS':0,'rowE':0,'colS':1,'colE':8,'subs':[{'field':'','title':'期初人数','rowS':1,'rowE':1,'colS':1,'colE':4,'subs':[{'field':'size_laohe_bf','title':'在册劳合[初]','rowS':2,'rowE':2,'colS':1,'colE':1},{'field':'size_retire_bf','title':'退休人员[初]','rowS':2,'rowE':2,'colS':2,'colE':2},{'field':'size_laowu_bf','title':'劳务工[初]','rowS':2,'rowE':2,'colS':3,'colE':3},{'field':'size_total_bf','title':'合计[初]','rowS':2,'rowE':2,'colS':4,'colE':4}]},{'field':'','title':'期末人数','rowS':1,'rowE':1,'colS':5,'colE':8,'subs':[{'field':'size_laohe','title':'在册劳合[末]','rowS':2,'rowE':2,'colS':5,'colE':5},{'field':'size_retire','title':'退休人员[末]','rowS':2,'rowE':2,'colS':6,'colE':6},{'field':'size_laowu','title':'劳务工[末]','rowS':2,'rowE':2,'colS':7,'colE':7},{'field':'size_total','title':'合计[末]','rowS':2,'rowE':2,'colS':8,'colE':8}]}]},{'field':'','title':'基本养老保险费','rowS':0,'rowE':0,'colS':9,'colE':13,'subs':[{'field':'','title':'参保人员','rowS':1,'rowE':1,'colS':9,'colE':12,'subs':[{'field':'size_pen','title':'省直[养老]','rowS':2,'rowE':2,'colS':9,'colE':9},{'field':'size_pen_laohe','title':'属地劳合[养老]','rowS':2,'rowE':2,'colS':10,'colE':10},{'field':'size_pen_laowu','title':'属地劳务[养老]','rowS':2,'rowE':2,'colS':11,'colE':11},{'field':'size_pen_total','title':'人员合计[养老]','rowS':2,'rowE':2,'colS':12,'colE':12}]},{'field':'rate_pen','title':'参保率[养老]','rowS':1,'rowE':2,'colS':13,'colE':13}]},{'field':'','title':'基本医疗保险费','rowS':0,'rowE':0,'colS':14,'colE':18,'subs':[{'field':'','title':'参保人员','rowS':1,'rowE':1,'colS':14,'colE':17,'subs':[{'field':'size_medi','title':'焦煤焦作市[医疗]','rowS':2,'rowE':2,'colS':14,'colE':14},{'field':'size_medi_laohe','title':'属地劳合[医疗]','rowS':2,'rowE':2,'colS':15,'colE':15},{'field':'size_medi_laowu','title':'属地劳务[医疗]','rowS':2,'rowE':2,'colS':16,'colE':16},{'field':'size_medi_total','title':'人员合计[医疗]','rowS':2,'rowE':2,'colS':17,'colE':17}]},{'field':'rate_medi','title':'参保率[医疗]','rowS':1,'rowE':2,'colS':18,'colE':18}]},{'field':'','title':'工伤保险费','rowS':0,'rowE':0,'colS':19,'colE':23,'subs':[{'field':'','title':'参保人员','rowS':1,'rowE':1,'colS':19,'colE':22,'subs':[{'field':'size_injury','title':'焦煤焦作市[工伤]','rowS':2,'rowE':2,'colS':19,'colE':19},{'field':'size_injury_laohe','title':'属地劳合[工伤]','rowS':2,'rowE':2,'colS':20,'colE':20},{'field':'size_injury_laowu','title':'属地劳务[工伤]','rowS':2,'rowE':2,'colS':21,'colE':21},{'field':'size_injury_total','title':'人员合计[工伤]','rowS':2,'rowE':2,'colS':22,'colE':22}]},{'field':'rate_injury','title':'参保率[工伤]','rowS':1,'rowE':2,'colS':23,'colE':23}]},{'field':'','title':'失业保险费','rowS':0,'rowE':0,'colS':24,'colE':28,'subs':[{'field':'','title':'参保人员','rowS':1,'rowE':1,'colS':24,'colE':27,'subs':[{'field':'size_unemp','title':'焦煤焦作市[失业]','rowS':2,'rowE':2,'colS':24,'colE':24},{'field':'size_unemp_laohe','title':'属地劳合[失业]','rowS':2,'rowE':2,'colS':25,'colE':25},{'field':'size_unemp_laowu','title':'属地劳务[失业]','rowS':2,'rowE':2,'colS':26,'colE':26},{'field':'size_unemp_total','title':'人员合计[失业]','rowS':2,'rowE':2,'colS':27,'colE':27}]},{'field':'rate_unemp','title':'参保率[失业]','rowS':1,'rowE':2,'colS':28,'colE':28}]},{'field':'','title':'生育保险费','rowS':0,'rowE':0,'colS':29,'colE':33,'subs':[{'field':'','title':'参保人员','rowS':1,'rowE':1,'colS':29,'colE':32,'subs':[{'field':'size_birth','title':'焦煤焦作市[生育]','rowS':2,'rowE':2,'colS':29,'colE':29},{'field':'size_birth_laohe','title':'属地劳合[生育]','rowS':2,'rowE':2,'colS':30,'colE':30},{'field':'size_birth_laowu','title':'属地劳务[生育]','rowS':2,'rowE':2,'colS':31,'colE':31},{'field':'size_birth_total','title':'人员合计[生育]','rowS':2,'rowE':2,'colS':32,'colE':32}]},{'field':'rate_birth','title':'参保率[生育]','rowS':1,'rowE':2,'colS':33,'colE':33}]}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "社保参保人数月统计表", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
		}
	}
	
	/**
	 * 社保统计》定向年度社保征缴明细页面
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "201/tostatis.jhtm")
	public String tost201(HttpServletRequest request,ModelMap modelMap) {
		flushCached(modelMap);
		return "social/statistics/201";
	}
	
	/**
	 * <p>Discription:[年度社保征缴明细]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "201/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st201(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			String year = request.getParameter("year");
			String modalitysStr = request.getParameter("wkModalityCns");
			if(StringHelper.isNullOrEmpty(year))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择统计年份！");
				return retMap;
			}
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			
			List<Map<String,Object>> data = statistics4SocialBiz.load4_201(year, modalitys);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	
	@RequestMapping(value = "201/export.jhtm")
	@ResponseBody
	public void export201(HttpServletRequest request,HttpServletResponse response) {
		try {
			String year = request.getParameter("year");
			String modalitysStr = request.getParameter("wkModalityCns");
			if(StringHelper.isNullOrEmpty(year))
			{
				logger.error("社保导出[年度社保征缴明细]:统计年份为空!");
				return;
			}
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			
			List<Map<String,Object>> data = statistics4SocialBiz.load4_201(year, modalitys);
			String tmp = "{'hTitle':'中央医院社保征缴（应缴）汇总表','heads':[{'field':'social_ymonth','title':'社保年月','rowS':0,'rowE':1,'colS':0,'colE':0},{'field':'wk_modality_cn','title':'用工形式','rowS':0,'rowE':1,'colS':1,'colE':1},{'field':'','title':'人数','rowS':0,'rowE':0,'colS':2,'colE':7,'subs':[{'field':'num_pen','title':'养老','rowS':1,'rowE':1,'colS':2,'colE':2},{'field':'num_medi','title':'医疗','rowS':1,'rowE':1,'colS':3,'colE':3},{'field':'num_injury','title':'工伤','rowS':1,'rowE':1,'colS':4,'colE':4},{'field':'num_unemp','title':'失业','rowS':1,'rowE':1,'colS':5,'colE':5},{'field':'num_ovp','title':'省统筹','rowS':1,'rowE':1,'colS':6,'colE':6},{'field':'num_spmedi','title':'补充医疗','rowS':1,'rowE':1,'colS':7,'colE':7},]},{'field':'','title':'缴费基数','rowS':0,'rowE':0,'colS':8,'colE':13,'subs':[{'field':'base_pen','title':'养老[基数]','rowS':1,'rowE':1,'colS':8,'colE':8},{'field':'base_medi','title':'医疗[基数]','rowS':1,'rowE':1,'colS':9,'colE':9},{'field':'base_injury','title':'工伤[基数]','rowS':1,'rowE':1,'colS':10,'colE':10},{'field':'base_unemp','title':'失业[基数]','rowS':1,'rowE':1,'colS':11,'colE':11},{'field':'base_ovp','title':'省统筹[基数]','rowS':1,'rowE':1,'colS':12,'colE':12},{'field':'base_spmedi','title':'补充医疗[基数]','rowS':1,'rowE':1,'colS':13,'colE':13}]},{'field':'','title':'单位应缴部分','rowS':0,'rowE':0,'colS':14,'colE':22,'subs':[{'field':'comp_total','title':'单位小计','rowS':1,'rowE':1,'colS':14,'colE':14},{'field':'comp_pen','title':'养老[单位]','rowS':1,'rowE':1,'colS':15,'colE':15},{'field':'comp_medi','title':'医疗[单位]','rowS':1,'rowE':1,'colS':16,'colE':16},{'field':'comp_injury','title':'工伤[单位]','rowS':1,'rowE':1,'colS':17,'colE':17},{'field':'comp_unemp','title':'失业[单位]','rowS':1,'rowE':1,'colS':18,'colE':18},{'field':'comp_ovp','title':'省统筹[单位]','rowS':1,'rowE':1,'colS':19,'colE':19},{'field':'comp_spmedi','title':'补充医疗[单位]','rowS':1,'rowE':1,'colS':20,'colE':20},{'field':'comp_serious','title':'大病医疗[单位]','rowS':1,'rowE':1,'colS':21,'colE':21},{'field':'comp_overpaid','title':'补缴[单位]','rowS':1,'rowE':1,'colS':22,'colE':22}]},{'field':'','title':'个人应缴部分','rowS':0,'rowE':0,'colS':23,'colE':31,'subs':[{'field':'pers_total','title':'个人小计','rowS':1,'rowE':1,'colS':23,'colE':23},{'field':'pers_pen','title':'养老[个人]','rowS':1,'rowE':1,'colS':24,'colE':24},{'field':'pers_medi','title':'医疗[个人]','rowS':1,'rowE':1,'colS':25,'colE':25},{'field':'pers_injury','title':'工伤[个人]','rowS':1,'rowE':1,'colS':26,'colE':26},{'field':'pers_unemp','title':'失业[个人]','rowS':1,'rowE':1,'colS':27,'colE':27},{'field':'pers_ovp','title':'省统筹[个人]','rowS':1,'rowE':1,'colS':28,'colE':28},{'field':'pers_spmedi','title':'补充医疗[个人]','rowS':1,'rowE':1,'colS':29,'colE':29},{'field':'pers_serious','title':'大病医疗[个人]','rowS':1,'rowE':1,'colS':30,'colE':30},{'field':'pers_comple','title':'补缴[个人]','rowS':1,'rowE':1,'colS':31,'colE':31}]},{'field':'','title':'上交财务','rowS':0,'rowE':0,'colS':32,'colE':34,'subs':[{'field':'total_handin','title':'合计','rowS':1,'rowE':1,'colS':32,'colE':32},{'field':'comp_handin','title':'单位上缴','rowS':1,'rowE':1,'colS':33,'colE':33},{'field':'pers_handin','title':'个人上缴','rowS':1,'rowE':1,'colS':34,'colE':34}]}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "中央医院社保征缴（应缴）汇总表", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
		}
	}
	
	/**
	 * 社保统计》社保计划
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "202/tostatis.jhtm")
	public String tost202(HttpServletRequest request,ModelMap modelMap) {
		flushCached(modelMap);
		return "social/statistics/202";
	}
	
	/**
	 * <p>Discription:[社保计划]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "202/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> st202(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			String name = request.getParameter("name");
			String cardNo = request.getParameter("cardNo");
			String startMonth = request.getParameter("startMonth");
			String endMonth = request.getParameter("endMonth");
			String wkAreasCnsStr = request.getParameter("wkAreasCns");
			String modalitysStr = request.getParameter("wkModalityCns");
			String depart1Str = request.getParameter("hosDepart1levelCns");
			String depart2Str = request.getParameter("hosDepart2levelCns");
			if(StringHelper.isNullOrEmpty(startMonth) 
					&& StringHelper.isNullOrEmpty(endMonth))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "请选择统计区间！");
				return retMap;
			}
			model.setStartMonth(StringHelper.isNullOrEmpty(startMonth)?null:startMonth);
			model.setEndMonth(StringHelper.isNullOrEmpty(endMonth)?null:endMonth);
			
			model.setName(StringHelper.isNullOrEmpty(name)?null:name);
			model.setCardNo(StringHelper.isNullOrEmpty(cardNo)?null:cardNo);
			
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			model.setWkModalityCns(modalitys);
			List<String> wkareas = null;
			if(!StringHelper.isNullOrEmpty(wkAreasCnsStr)) {
				wkareas = Arrays.asList(wkAreasCnsStr.split(","));
			}
			model.setWkAreasCns(wkareas);
			List<String> depart1s = null;
			if(!StringHelper.isNullOrEmpty(depart1Str)) {
				depart1s = Arrays.asList(depart1Str.split(","));
			}
			model.setHosDepart1levelCns(depart1s);
			List<String> depart2s = null;
			if(!StringHelper.isNullOrEmpty(depart2Str)) {
				depart2s = Arrays.asList(depart2Str.split(","));
			}
			model.setHosDepart2levelCns(depart2s);
			
			List<Map<String,Object>> data = statistics4SocialBiz.load4_202(model);
			retMap.put("retCode", 200);
			retMap.put("retMsg", "查询成功！");
			retMap.put("retData", data);
		}catch(Exception e)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "查询失败！");
			logger.error("查询失败！error:",e);
		}
		return retMap;
	}
	
	@RequestMapping(value = "202/export.jhtm")
	@ResponseBody
	public void export202(HttpServletRequest request,HttpServletResponse response) {
		try {
			ChSocialInfoExtModel model = new ChSocialInfoExtModel();
			String name = request.getParameter("name");
			String cardNo = request.getParameter("cardNo");
			String startMonth = request.getParameter("startMonth");
			String endMonth = request.getParameter("endMonth");
			String wkAreasCnsStr = request.getParameter("wkAreasCns");
			String modalitysStr = request.getParameter("wkModalityCns");
			String depart1Str = request.getParameter("hosDepart1levelCns");
			String depart2Str = request.getParameter("hosDepart2levelCns");
			if(StringHelper.isNullOrEmpty(startMonth) 
					&& StringHelper.isNullOrEmpty(endMonth))
			{
				logger.error("社保：社保计划统计-缺少统计区间！");
				return;
			}
			model.setStartMonth(StringHelper.isNullOrEmpty(startMonth)?null:startMonth);
			model.setEndMonth(StringHelper.isNullOrEmpty(endMonth)?null:endMonth);
			
			model.setName(StringHelper.isNullOrEmpty(name)?null:name);
			model.setCardNo(StringHelper.isNullOrEmpty(cardNo)?null:cardNo);
			
			List<String> modalitys = null;
			if(!StringHelper.isNullOrEmpty(modalitysStr)) {
				modalitys = Arrays.asList(modalitysStr.split(","));
			}
			model.setWkModalityCns(modalitys);
			List<String> wkareas = null;
			if(!StringHelper.isNullOrEmpty(wkAreasCnsStr)) {
				wkareas = Arrays.asList(wkAreasCnsStr.split(","));
			}
			model.setWkAreasCns(wkareas);
			List<String> depart1s = null;
			if(!StringHelper.isNullOrEmpty(depart1Str)) {
				depart1s = Arrays.asList(depart1Str.split(","));
			}
			model.setHosDepart1levelCns(depart1s);
			List<String> depart2s = null;
			if(!StringHelper.isNullOrEmpty(depart2Str)) {
				depart2s = Arrays.asList(depart2Str.split(","));
			}
			model.setHosDepart2levelCns(depart2s);
			
			List<Map<String,Object>> data = statistics4SocialBiz.load4_202(model);
			String tmp = "{'hTitle':'社保计划表','heads':[{'field':'name','title':'姓名','rowS':0,'rowE':1,'colS':0,'colE':0},{'field':'card_no','title':'身份证号','rowS':0,'rowE':1,'colS':1,'colE':1},{'field':'start_time','title':'查询开始时间','rowS':0,'rowE':1,'colS':2,'colE':2},{'field':'end_time','title':'查询结束时间','rowS':0,'rowE':1,'colS':3,'colE':3},{'field':'work_area_cn','title':'工作地域','rowS':0,'rowE':1,'colS':4,'colE':4},{'field':'hos_depart_1level_cn','title':'一级科室','rowS':0,'rowE':1,'colS':5,'colE':5},{'field':'hos_depart_2level_cn','title':'二级科室','rowS':0,'rowE':1,'colS':6,'colE':6},{'field':'size','title':'人数','rowS':0,'rowE':1,'colS':7,'colE':7},{'field':'','title':'单位应缴金额','rowS':0,'rowE':0,'colS':8,'colE':14,'subs':[{'field':'comp_pen','title':'养老[单-应]','rowS':1,'rowE':1,'colS':8,'colE':8},{'field':'comp_medi','title':'医疗[单-应]','rowS':1,'rowE':1,'colS':9,'colE':9},{'field':'comp_injury','title':'工伤[单-应]','rowS':1,'rowE':1,'colS':10,'colE':10},{'field':'comp_unemp','title':'失业[单-应]','rowS':1,'rowE':1,'colS':11,'colE':11},{'field':'comp_ovp','title':'省统筹[单-应]','rowS':1,'rowE':1,'colS':12,'colE':12},{'field':'comp_spmedi','title':'补充医疗[单-应]','rowS':1,'rowE':1,'colS':13,'colE':13},{'field':'comp_serious','title':'大病医疗[单-应]','rowS':1,'rowE':1,'colS':14,'colE':14}]},{'field':'','title':'个人应缴金额','rowS':0,'rowE':0,'colS':15,'colE':21,'subs':[{'field':'pers_pen','title':'养老[个-应]','rowS':1,'rowE':1,'colS':15,'colE':15},{'field':'pers_medi','title':'医疗[个-应]','rowS':1,'rowE':1,'colS':16,'colE':16},{'field':'pers_injury','title':'工伤[个-应]','rowS':1,'rowE':1,'colS':17,'colE':17},{'field':'pers_unemp','title':'失业[个-应]','rowS':1,'rowE':1,'colS':18,'colE':18},{'field':'pers_ovp','title':'省统筹[个-应]','rowS':1,'rowE':1,'colS':19,'colE':19},{'field':'pers_spmedi','title':'补充医疗[个-应]','rowS':1,'rowE':1,'colS':20,'colE':20},{'field':'pers_serious','title':'大病医疗[个-应]','rowS':1,'rowE':1,'colS':21,'colE':21}]},{'field':'','title':'单位实缴金额','rowS':0,'rowE':0,'colS':22,'colE':28,'subs':[{'field':'comp_recap_pen','title':'养老[单-实]','rowS':1,'rowE':1,'colS':22,'colE':22},{'field':'comp_recap_medi','title':'医疗[单-实]','rowS':1,'rowE':1,'colS':23,'colE':23},{'field':'comp_recap_injury','title':'工伤[单-实]','rowS':1,'rowE':1,'colS':24,'colE':24},{'field':'comp_recap_unemp','title':'失业[单-实]','rowS':1,'rowE':1,'colS':25,'colE':25},{'field':'comp_recap_ovp','title':'省统筹[单-实]','rowS':1,'rowE':1,'colS':26,'colE':26},{'field':'comp_recap_spmedi','title':'补充医疗[单-实]','rowS':1,'rowE':1,'colS':27,'colE':27},{'field':'comp_recap_serious','title':'大病医疗[单-实]','rowS':1,'rowE':1,'colS':28,'colE':28}]},{'field':'','title':'个人实缴金额','rowS':0,'rowE':0,'colS':29,'colE':35,'subs':[{'field':'pers_recap_pen','title':'养老[个-实]','rowS':1,'rowE':1,'colS':29,'colE':29},{'field':'pers_recap_medi','title':'医疗[个-实]','rowS':1,'rowE':1,'colS':30,'colE':30},{'field':'pers_recap_injury','title':'工伤[个-实]','rowS':1,'rowE':1,'colS':31,'colE':31},{'field':'pers_recap_unemp','title':'失业[个-实]','rowS':1,'rowE':1,'colS':32,'colE':32},{'field':'pers_recap_ovp','title':'省统筹[个-实]','rowS':1,'rowE':1,'colS':33,'colE':33},{'field':'pers_recap_spmedi','title':'补充医疗[个-实]','rowS':1,'rowE':1,'colS':34,'colE':34},{'field':'pers_recap_serious','title':'大病医疗[个-实]','rowS':1,'rowE':1,'colS':35,'colE':35}]}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "社保计划表", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
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
//				fvallist = new ArrayList<String>();
//				if(null!=fValues) {
//					fvallist.addAll(Arrays.asList(fValues.split(",")));
//				}
				if(null == fvallist)fvallist = new ArrayList<String>();
				//fvallist.remove("#N/A");
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
