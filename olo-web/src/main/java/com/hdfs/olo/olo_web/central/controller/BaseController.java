package com.hdfs.olo.olo_web.central.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.WebUtils;

import com.hdfs.olo.olo_web.central.biz.IComTabLogBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemDictBiz;
import com.hdfs.olo.olo_web.central.model.ComTabLogModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.UtilityHelper;


public abstract class BaseController {
	
	@Autowired
	private IComTabLogBiz comTabLogBiz;
	@Autowired
	private ISystemDictBiz systemDictBiz;
	
	public UserInfoModel getLoginUser(HttpServletRequest request)
	{
		return (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
	}
	/**
	 * 写入信息级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 */
	public void log2Info(HttpServletRequest request,String module,String moduleName)
	{
		log2Info(request, module, moduleName,"");
	}
	/**
	 * 写入信息级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 * @param remark
	 */
	public void log2Info(HttpServletRequest request,String module,String moduleName,String remark)
	{
		writeLog(request, module, moduleName, remark, DictionaryUtil.KEY_LOG_LEVEL_INFO);
	}
	
	
	/**
	 * 写入警告级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 * @param remark
	 */
	public void log2Warn(HttpServletRequest request,String module,String moduleName)
	{
		log2Warn(request, module, moduleName,"");
	}
	
	/**
	 * 写入警告级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 * @param remark
	 */
	public void log2Warn(HttpServletRequest request,String module,String moduleName,String remark)
	{
		writeLog(request, module, moduleName, remark, DictionaryUtil.KEY_LOG_LEVEL_WARN);
	}
	
	
	
	/**
	 * 写入错误级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 * @param remark
	 */
	public void log2Error(HttpServletRequest request,String module,String moduleName)
	{
		log2Error(request, module, moduleName,"");
	}
	
	/**
	 * 写入错误级别的日志
	 * @param request
	 * @param module
	 * @param moduleName
	 * @param remark
	 */
	public void log2Error(HttpServletRequest request,String module,String moduleName,String remark)
	{
		writeLog(request, module, moduleName, remark, DictionaryUtil.KEY_LOG_LEVEL_ERROR);
	}
	
	
	public void writeLog(final HttpServletRequest request,final String module,final String moduleName,final String remark,final Integer level)
	{
		UserInfoModel userInfo = (UserInfoModel) WebUtils.getSessionAttribute(request, DictionaryUtil.KEY_SESSION_USER);
		if(userInfo != null)
		{
			ComTabLogModel comTabLog = new ComTabLogModel();
			comTabLog.setBranchId(userInfo.getBranch().getId());
			comTabLog.setBranchName(userInfo.getBranch().getBranchName());
			comTabLog.setUserId(userInfo.getUserId());
			comTabLog.setUserName(userInfo.getUserRealName());
			String ip = UtilityHelper.getClientIP(request);
			comTabLog.setLogIp(ip);
			//comTabLog.setTlogMac(UtilityHelper.getCustomerMac(ip));
			comTabLog.setLogModuleId(module);
			comTabLog.setLogModuleName(moduleName);
			if(!StringHelper.isNullOrEmpty(remark))
			{
				String re = remark.length()>220?StringHelper.cutString(remark, 220):remark;
				comTabLog.setRemark(re);
			}
			comTabLog.setLogLevel(level);
			comTabLog.setStatus(DictionaryUtil.KEY_NORMAL+"");
			comTabLogBiz.save(comTabLog);
		}
	}
	
	
	private static final String DICT_4_COMMON = "sex,status,privType,dictType,menuLevel,YN";
	
	private static List<ComboxItem> DICT_SEX = null;
	private static List<ComboxItem> DICT_STATUS = null;
	private static List<ComboxItem> DICT_PRIV_TYPE = null;
	private static List<ComboxItem> DICT_DICT_TYPE = null;
	private static List<ComboxItem> DICT_MENU_LEVEL = null;
	private static List<ComboxItem> DICT_YN = null;
	
	public List<ComboxItem> getSexList()
	{
		if(DICT_SEX == null) this.getCommonDict();
		return DICT_SEX;
	}
	
	public List<ComboxItem> getStatusList()
	{
		if(DICT_STATUS == null)this.getCommonDict();
		return DICT_STATUS;
	}
	
	public List<ComboxItem> getPrivTypeList()
	{
		if(DICT_PRIV_TYPE == null)this.getCommonDict();
		return DICT_PRIV_TYPE;
	}
	
	public List<ComboxItem> getDictTypeList()
	{
		if(DICT_DICT_TYPE == null)this.getCommonDict();
		return DICT_DICT_TYPE;
	}
	public List<ComboxItem> getMenuLevelList()
	{
		if(DICT_MENU_LEVEL == null)this.getCommonDict();
		return DICT_MENU_LEVEL;
	}
	public List<ComboxItem> getYesNoList()
	{
		if(DICT_YN == null)this.getCommonDict();
		return DICT_YN;
	}
	
	public void getCommonDict()
	{
		//加载字典项
		Map<String,List<Map<String,Object>>> tMap = systemDictBiz.queryItemListByCodes
				(Arrays.asList(DICT_4_COMMON.split(",")));
		List<ComboxItem> comboxs = null;
		for(String dictCode:tMap.keySet())
		{
			List<Map<String,Object>> list = tMap.get(dictCode);
			comboxs = new ArrayList<ComboxItem>();
			for(Map<String,Object> item:list)
			{
				comboxs.add(new ComboxItem(item.get("item_key")+"",item.get("item_val")+""));
			}
			switch(dictCode)
			{
			case "sex":
				DICT_SEX = comboxs;
				break;
			case "status":
				DICT_STATUS = comboxs;
				break;
			case "privType":
				DICT_PRIV_TYPE = comboxs;
				break;
			case "dictType":
				DICT_DICT_TYPE = comboxs;
				break;
			case "menuLevel":
				DICT_MENU_LEVEL = comboxs;
				break;
			case "YN":
				DICT_YN = comboxs;
				break;
			}
		}
	}
}
