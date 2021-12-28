package com.hdfs.olo.olo_web.salary.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.DateTimeHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.EncryptUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.ValidateCodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaPayslipRewardBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaWechatAccountBiz;
import com.hdfs.olo.olo_web.salary.biz.IChSaWechatTokenBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipExtendModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipModel;
import com.hdfs.olo.olo_web.salary.model.ChSaPayslipRewardModel;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatAccountModel;
import com.hdfs.olo.olo_web.salary.model.ChSaWechatTokenModel;

/** 
 * Description: [微信公众号服务实现]
 * Created on 2021年05月14日
 * @author  <a href="mailto: jeenry@126.com">huadf</a>
 * @version 1.0 
 * Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/wechat/api/*")
public class WeChatApiController extends BaseController {
	
	public final static String Module_Name = "微信公众号";
	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private IChSaPayslipBiz chSaPayslipBiz;
	@Autowired
	private IChSaWechatTokenBiz chSaWechatTokenBiz;
	@Autowired
	private IChSaPayslipRewardBiz chSaPayslipRewardBiz;
	@Autowired
	private IChSaWechatAccountBiz chSaWechatAccountBiz;
	
	private	final static String PARA_cardNo = "cardNo";
	private	final static String PARA_passwd = "passwd";
	private	final static String PARA_token = "token";
	private	final static String PARA_month = "month";
	
	/**
	 * 转向微信公众号登录页面
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wlogin.jhtm")
	public String wlogin(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//刷新密钥缓存
		flushSecretKey(modelMap);
		modelMap.put("secretKey", secretKey);
		return "wechat/wlogin";
	}
	
	private static String secretKey = "@jiaomei";
	Long cachedTime = null;//密鑰緩存時長
	/**
	 * <p>Discription:[工资单查询登录]</p>
	 * Created on 2021年05月14日												       	 
	 * @author:huadf
	 */
	@RequestMapping("doPlogin.jhtm")
	@ResponseBody
	public Map<String,Object> plogin(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Date endTime = DateTimeHelper.createDate("2021-12-25");
	    if (endTime.getTime() <= new Date().getTime()) {
	      return LayAjaxHelper.fail("暂停服务");
	    }
		String idcard = request.getParameter(PARA_cardNo);
		String passwd = request.getParameter(PARA_passwd);
		try {
			idcard = EncryptUtil.desDecode(idcard,secretKey);
			passwd = EncryptUtil.desDecode(passwd,secretKey);
		}catch(Exception e)
		{
			return LayAjaxHelper.fail("系统异常，请重试!");
		}
		if(StringHelper.isNullOrEmpty(idcard) || StringHelper.isNullOrEmpty(passwd)) {
			return LayAjaxHelper.fail("请输入身份证号或查询密码!");
		}
		//通过身份证号查询是否存在有效的工资单记录
		ChSaPayslipExtendModel model = new ChSaPayslipExtendModel();
		model.setCardNo(idcard);
		model.setStatus(DictionaryUtil.KEY_NORMAL);
		Long count = chSaPayslipBiz.queryCount(model);
		
		//根据身份证号获取查询密码
		ChSaWechatAccountModel wmodel = new ChSaWechatAccountModel();
		wmodel.setCardNo(idcard);
		wmodel.setStatus(DictionaryUtil.KEY_NORMAL);
		List<ChSaWechatAccountModel> alist = chSaWechatAccountBiz.queryList(wmodel,null);
		
		//开始校验
		if(count<=0) 
			return LayAjaxHelper.fail("该身份证号在工资单系统中匹配失败!");
		if(null == alist || alist.size()<=0)
			return LayAjaxHelper.fail("您尚未设置查询密码，请先设置查询密码!");
		Integer errTimes = alist.get(0).getErrTimes();
		if(null != errTimes && errTimes>=3)
			return LayAjaxHelper.fail("由于多次密码错误，该账号暂被锁定，请明天再次尝试!");
		String oldpwd = alist.get(0).getPasswd();
		if(StringHelper.isNullOrEmpty(oldpwd))
			return LayAjaxHelper.fail("系统异常，请联系管理员!");
		
		//密码匹配
		EncryptHelper encryptHelper = EncryptHelper.getInstance();
		String newString = encryptHelper.encryptString(passwd);
		if(!encryptHelper.compareString(oldpwd, newString))
		{
			chSaWechatAccountBiz.inc4ErrTimes(alist.get(0).getId(), 1);
			return LayAjaxHelper.fail("请输入正确的查询密码!");
		}
		//生成访问token
		ChSaWechatTokenModel tmodel = new ChSaWechatTokenModel();
		tmodel.setCardNo(idcard);
		tmodel.setType(0);
		tmodel.setToken(ValidateCodeUtil.genCode(20));
		chSaWechatTokenBiz.save(tmodel);
		
		return LayAjaxHelper.success("校验通过!",tmodel.getToken());
	}
	
	/**
	 * 转向访问密码设置页面
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("wsetpwd.jhtm")
	public String wsetpwd(HttpServletRequest request,ModelMap modelMap) throws Exception{
		//刷新密钥缓存
		flushSecretKey(modelMap);
		modelMap.put("secretKey", secretKey);
		return "wechat/wsetpwd";
	}
	
	/**
	 * <p>Discription:[添加微信公众号访问密码</p>
	 * Created on 2021年05月14日												       	 
	 * @author:huadf
	 */
	@RequestMapping("wsetpwd/do.jhtm")
	@ResponseBody
	public Map<String,Object> doSetPwd(HttpServletRequest request,ModelMap modelMap){
		Date endTime = DateTimeHelper.createDate("2021-12-25");
	    if (endTime.getTime() <= new Date().getTime()) {
	      return LayAjaxHelper.fail("暂停服务");
	    }
		String idcard = request.getParameter(PARA_cardNo);
		String passwd = request.getParameter(PARA_passwd);
		try {
			idcard = EncryptUtil.desDecode(idcard,secretKey);
			passwd = EncryptUtil.desDecode(passwd,secretKey);
		}catch(Exception e)
		{
			return LayAjaxHelper.fail("系统异常，请重试!");
		}
		
		if(StringHelper.isNullOrEmpty(idcard) || StringHelper.isNullOrEmpty(passwd))
			return LayAjaxHelper.fail("请输入身份证号或查询密码!");
		try {
			//通过身份证号查询是否存在有效的工资单记录
			ChSaPayslipExtendModel model = new ChSaPayslipExtendModel();
			model.setCardNo(idcard);
			model.setStatus(DictionaryUtil.KEY_NORMAL);
			Long count = chSaPayslipBiz.queryCount(model);
			if(count<=0) 
				return LayAjaxHelper.fail("该身份证号在工资单系统中匹配失败!");
			//设置访问密码
			Map<Boolean,String> handMap = chSaWechatAccountBiz.doSetAccPwd(idcard, passwd);
			if(handMap.containsKey(true))
				return LayAjaxHelper.success(handMap.get(true));
			else
				return LayAjaxHelper.fail(handMap.get(false));
		}catch(Exception e) {
			logger.error("职工设置访问密码时异常！error：",e);
		}
		return LayAjaxHelper.fail("系统异常，请稍后再试!");
	}
	
	/**
	 * <p>Discription:[工资单查询登录]</p>
	 * Created on 2021年05月14日												       	 
	 * @author:huadf
	 */
	@RequestMapping("checkToken.jhtm")
	@ResponseBody
	public Map<String,Object> checkToken(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Date endTime = DateTimeHelper.createDate("2021-12-25");
	    if (endTime.getTime() <= new Date().getTime()) {
	      return LayAjaxHelper.fail("暂停服务");
	    }
		String token = request.getParameter(PARA_token);
		if(StringHelper.isNullOrEmpty(token)) {
			return LayAjaxHelper.fail("您的请求有误，请刷新页面重试!");
		}
		
		ChSaWechatTokenModel model = new ChSaWechatTokenModel();
		model.setToken(token);
		Long count = chSaWechatTokenBiz.queryCount(model);
		if(count<=0) return LayAjaxHelper.fail("访问token已过期，请刷新页面重试!");
		return LayAjaxHelper.success("校验通过!");
	}
	
	/**
	 * <p>Discription:[根据访问token查询工资单]</p>
	 * Created on 2021年05月14日												       	 
	 * @author:huadf
	 */
	@RequestMapping("payslip.jhtm")
	public String payslip(HttpServletRequest request,ModelMap modelMap) throws Exception{
		String token = request.getParameter(PARA_token);
		String month = request.getParameter(PARA_month);
		modelMap.put("token", token);
		modelMap.put("month", month);
		return "wechat/payslip";
	}
	
	@RequestMapping(value = "payslip/loadTabHead.jhtm")
	@ResponseBody
	public Map<String,Object> loadTabHead(HttpServletRequest request,ModelMap modelMap)
	{
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		JSONArray heads = packRewardHead(startMonth,endMonth);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("heads", heads);
		retMap.put("startMonth", startMonth);
		retMap.put("endMonth", endMonth);
		return retMap;
	}
	
	@RequestMapping("payslip/do.jhtm")
	@ResponseBody
	public Map<String,Object> loadPayslips(HttpServletRequest request,ModelMap modelMap) throws Exception{
		Map<String,Object> retMap = new HashMap<String,Object>();
		String token = request.getParameter(PARA_token);
		String month = request.getParameter(PARA_month);
		if(StringHelper.isNullOrEmpty(token) || StringHelper.isNullOrEmpty(month)) {
			retMap.put("retCode", 201);
			retMap.put("retMsg", "缺失请求参数，请刷新页面重试!");
			return retMap;
		}
		ChSaWechatTokenModel model = new ChSaWechatTokenModel();
		model.setToken(token);
		Long count = chSaWechatTokenBiz.queryCount(model);
		if(count<=0){
			retMap.put("retCode", 202);
			retMap.put("retMsg", "访问token已过期，请刷新页面重试!");
			return retMap;
		}
		List<ChSaWechatTokenModel> list = chSaWechatTokenBiz.queryList(model);
		String cardNo = list.get(0).getCardNo();
		List<ChSaPayslipModel> datas = chSaPayslipBiz.query4Wechat(cardNo,month);
		retMap.put("retCode", 200);
		retMap.put("retMsg", "查询成功!");
		retMap.put("retData", packRewardData(datas,month,month));
		return retMap;
	}
	
	private JSONArray packRewardHead(String startMonth,String endMonth)
	{
		//通过开始时间和结束时间查找奖励项目
		List<String> rewards = chSaPayslipRewardBiz.queryRewardList(startMonth, endMonth);
		String tabHeadTmp1 = 
		"[["+
			"{field: 'netTargetYearmonth', title: '工资月份' ,templet: '<div>{{#if(d.netTargetYearmonth){}} {{layui.util.toDateString(d.netTargetYearmonth, "+"\"yyyy-MM\""+") }} {{#} else{}} {{#}}}</div>',align: 'center',minWidth:'120',rowspan:3},"+
   	  		"{field: 'name', title: '姓名' ,minWidth:'120',align: 'center',rowspan:3}, "+
   	  		"{field: 'cardNo', title: '身份证号' ,minWidth:'180',align: 'center',rowspan:3},"+
//   	  		"{field: '', title: '工数' ,align: 'center',colspan:7},"+
   	  		"{field: '', title: '应发工资' ,align: 'center',colspan:"+(21+rewards.size())+"},"+
   	  		"{field: '', title: '扣款' ,align: 'center',colspan:12},"+
   	  		"{field: 'taxableWage', title: '计税工资' ,align: 'center',minWidth:'120',rowspan:3},"+
   	  		"{field: 'taxIncomePersonal', title: '个税' ,minWidth:'120',align: 'center',rowspan:3},"+
		  	"{field: 'netSalary', title: '实发合计' ,minWidth:'120',align: 'center',rowspan:3},"+
     "]"+
     ","+
     "["+
//   		  "{field: 'manwkStandard', title: '标准工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkAttend', title: '出勤工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkMiner', title: '旷工工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkQuit', title: '离职工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkSick', title: '病假工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkMaternity', title: '产假工数' ,minWidth:'120',align: 'center',rowspan:2},"+
//		  "{field: 'manwkPrivpassion', title: '事假工数' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: 'wageStandard4posit', title: '岗位工资标准' ,minWidth:'80',align: 'center',rowspan:2},"+
		  "{field: 'wageDay4posit', title: '岗位日工资' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: '', title: '岗位工资' ,align: 'center',colspan:3},"+
		  "{field: 'wageNightShift', title: '夜班费' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: '', title: '加班工资' ,align: 'center',colspan:2},"+
		  "{field: 'wageYearg', title: '年功工资' ,minWidth:'120',align: 'center',rowspan:2},"+
		  
		  "{field: '', title: '津补贴' ,minWidth:'120',align: 'center',colspan:9},"+
		  
		  "{field: 'wageTotal', title: '基础工资合计' ,minWidth:'120',align: 'center',rowspan:2},"+
		  //若只存在绩效工资合计，没有绩效项，则进行行合并（该条head为最底层head）
		  "{field: "+(rewards.size()>0?"\'\'":"\'wageJxTotal\'")+", title: '绩效工资',minWidth:'120',align: 'center'"+(rewards.size()>0?(",colspan:"+(1+rewards.size())):",rowspan:2")+"},"+
		  "{field: 'wagePayableTotal', title: '应发工资合计' ,align: 'center',minWidth:'120',rowspan:2},"+
		  "{field: '', title: '社会保险',align: 'center',colspan:7},"+
		  "{field: 'cutWater2elect', title: '水电费' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: 'cutHygiene', title: '卫生费' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: 'cutArrears', title: '职工欠款' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: 'cutOther', title: '其他扣款' ,minWidth:'120',align: 'center',rowspan:2},"+
		  "{field: 'cutTotal', title: '扣款合计' ,minWidth:'120',align: 'center',rowspan:2}"+
     "]"+
     ","+
 	 " ["+
		  "{field: 'wageAttend', title: '出勤工资' ,align: 'center',minWidth:'120'},"+
		  "{field: 'wageSick', title: '病假工资' ,align: 'center',minWidth:'120'},"+
		  "{field: 'wagePositTotal', title: '岗位工资合计' ,align: 'center',minWidth:'120'},"+
		  "{field: 'manwkOvertime', title: '加班工数' ,align: 'center',minWidth:'120'},"+
		  "{field: 'wageOvertime', title: '加班工资' ,align: 'center',minWidth:'120'},"+
		  "{field: 'allowanceHygiene', title: '卫生津贴' ,minWidth:'120',align: 'center'},"+
		  "{field: 'supp4tel', title: '电补' ,minWidth:'120',align: 'center'},"+
		  "{field: 'supp4traffic', title: '交通补贴' ,minWidth:'120',align: 'center'},"+
		  "{field: 'supp4mining', title: '下矿（井、乡）补贴' ,minWidth:'180',align: 'center'},"+
		  "{field: 'supp4other', title: '其他补贴' ,minWidth:'120',align: 'center'},"+
		  "{field: 'supp4univeStuLife', title: '大学生生活补贴' ,minWidth:'150',align: 'center'},"+
		  "{field: 'wageErrorCorrent', title: '纠错工资' ,minWidth:'120',align: 'center'},"+
		  "{field: 'supp4oth', title: '其他' ,minWidth:'120',align: 'center'},"+
		  "{field: 'suppTotal', title: '津补贴合计' ,minWidth:'120',align: 'center'}";
		  
		StringBuffer sups = new StringBuffer();
		if(null != rewards && rewards.size()>0)  
		{
			for(String item:rewards){
				sups.append("{field: '"+item+"', title: '"+item+"' ,align: 'center',minWidth:'200'},");
			}
		}
		
	String tabHeadTmp2 = 
		  (rewards.size()>0?"{field: 'wageJxTotal', title: '绩效工资合计' ,minWidth:'120',align: 'center'},":"")+
		  "{field: 'bxPension', title: '养老保险' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxMedical', title: '医疗保险' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxUnemploy', title: '失业保险' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxSeriousIllness', title: '大病保险' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxHousfund', title: '住房公积金' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxAnnualCorpGold', title: '企业年金' ,minWidth:'120',align: 'center'},"+
		  "{field: 'bxTotal', title: '保险合计' ,minWidth:'120',align: 'center'}"+
	  "]]";
		JSONArray heads = JSONArray.parseArray(tabHeadTmp1+sups.toString()+tabHeadTmp2);
		return heads;
	}
	
	private List<?> packRewardData(List<ChSaPayslipModel> datas,String startMonth,String endMonth)
	{
		if(null == datas || datas.size()<=0) return datas;
		//通过开始时间和结束时间查找奖励项目
		List<String> rewards = chSaPayslipRewardBiz.queryRewardList(startMonth,endMonth);
		if(null == rewards || rewards.size()<=0) return datas;
		
		JSONObject jsonItem = null;
		ChSaPayslipModel item = null;
		List<ChSaPayslipRewardModel> rlist = null;
		List<JSONObject> retlist = new ArrayList<JSONObject>();
		for(int i=0;i<datas.size();i++)
		{
			item = datas.get(i);
			jsonItem = JSONObject.parseObject(JSONObject.toJSONString(item));
			rlist = chSaPayslipRewardBiz.queryList(item.getCardNo(), item.getNetTargetYearmonth());
			if(null == rlist || rlist.size()<=0) continue;
			for(ChSaPayslipRewardModel sub:rlist)
			{
				jsonItem.put(sub.getRewardItem(), sub.getRewardAmount());
			}
			retlist.add(jsonItem);
		}
		return retlist;
	}
	
	/**
	 * 若超过设置的缓存时间，则重新缓存搜索区域条件
	 */
	private synchronized void flushSecretKey(ModelMap modelMap)
	{
		if(null == modelMap) return;
		//第一步：判断是否超过缓存时常，若超过，则重新加载数据
		Long curTime = System.currentTimeMillis();
		if(null == cachedTime || curTime - cachedTime > (24*60*60*1000))
		{
			cachedTime = curTime;//将当前时间设置为缓存时间
			secretKey = ValidateCodeUtil.genCode(8);
		}
	}
}
