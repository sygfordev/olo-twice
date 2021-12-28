package com.hdfs.olo.olo_web.personnel.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.personnel.biz.IHuTCommonService;
import com.hdfs.olo.olo_web.personnel.biz.IStatistics4pBiz;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.excel.HuToolHead;

/** 
 * Description: [统计服务实现]
 * Created on 2021年03月29日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/personnel/statistics/*")
public class StatisticsController extends BaseController{
	public final static String Module_Name = "人事档案统计";
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IStatistics4pBiz statistics4pBiz;
	@Autowired
	private IHuTCommonService huTCommonService;
	
	/**
	 * <p>Discription:[统计机构编制]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "index.jhtm")
	public void organIndex(HttpServletRequest request,ModelMap modelMap) {
		logger.info("xxx");
	}
	
	@RequestMapping(value = "100/tostatis.jhtm")
	public String torganIndex(HttpServletRequest request,ModelMap modelMap) {
		return "personnel/statistics/100";
	}
	/**
	 * <p>Discription:[统计机构编制]</p>
	 * Created on 2021年04月15日								       	 
	 * @author:huadf
	 */
	@RequestMapping(value = "100/dostatis.jhtm")
	@ResponseBody
	public Map<String,Object> organization(HttpServletRequest request,ModelMap modelMap) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		try {
			//统计机构编制
			List<Map<String,Object>> data = statistics4pBiz.s4organization();
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
	
	@RequestMapping(value = "100/export.jhtm")
	@ResponseBody
	public void export(HttpServletRequest request,HttpServletResponse response) {
		//Map<String,Object> retMap = new HashMap<String,Object>();
		Date curDate = new Date();
		String month = DateTimeHelper.getYear(curDate)+"年"+DateTimeHelper.getMonth(curDate)+"月";
		try {
			//统计机构编制
			List<Map<String,Object>> data = statistics4pBiz.s4organization();
			String tmp = "{'hTitle':'"+month+"基层单位机构设置、定员及干部职数统计表','heads':[{'field':'序号','title':'序号','rowS':0,'rowE':1,'colS':0,'colE':0,'subs':[]},{'field':'机构名称','title':'机构名称','rowS':0,'rowE':1,'colS':1,'colE':1,'subs':[]},{'field':'在岗','title':'在岗人数','rowS':0,'rowE':1,'colS':2,'colE':2,'subs':[]},{'field':'离岗人数','title':'离岗人数','rowS':0,'rowE':0,'colS':3,'colE':8,'subs':[{'field':'调研员','title':'调研员','rowS':1,'rowE':1,'colS':3,'colE':3,'subs':[]},{'field':'协理员','title':'协理员','rowS':1,'rowE':1,'colS':4,'colE':4,'subs':[]},{'field':'人力资源市场','title':'人力资源市场','rowS':1,'rowE':1,'colS':5,'colE':5,'subs':[]},{'field':'内退人员','title':'内退人员','rowS':1,'rowE':1,'colS':6,'colE':6,'subs':[]},{'field':'留职','title':'留职','rowS':1,'rowE':1,'colS':7,'colE':7,'subs':[]},{'field':'其他','title':'其他','rowS':1,'rowE':1,'colS':8,'colE':8,'subs':[]}]},{'field':'科级人数','title':'科级人数','rowS':0,'rowE':1,'colS':9,'colE':9,'subs':[]},{'field':'名单','title':'名单','rowS':0,'rowE':0,'colS':10,'colE':13,'subs':[{'field':'正科人数','title':'正科人数','rowS':1,'rowE':1,'colS':10,'colE':10,'subs':[]},{'field':'副科人数','title':'副科人数','rowS':1,'rowE':1,'colS':11,'colE':11,'subs':[]},{'field':'正科级科员','title':'正科级科员','rowS':1,'rowE':1,'colS':12,'colE':12,'subs':[]},{'field':'副科级科员','title':'副科级科员','rowS':1,'rowE':1,'colS':13,'colE':13,'subs':[]}]},{'field':'备注','title':'备注','rowS':0,'rowE':1,'colS':14,'colE':14,'subs':[]}]}";
			JSONObject headJson = JSONObject.parseObject(tmp);
			HuToolHead headInfo = HuToolHead.cvtJSON4HuToolHead(headJson);
			huTCommonService.export(response, "编制统计", data, headInfo);
		}catch(Exception e)
		{
			logger.error("查询失败！error:",e);
		}
	}
}
