package com.hdfs.olo.olo_web.salary.controller;

import java.util.ArrayList;
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

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.plugins.common.constant.CommonConstant;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.salary.biz.IChSaPaycardBiz;
import com.hdfs.olo.olo_web.salary.model.ChSaPaycardExtendModel;

/**
 * Description: [薪资-工资卡服务实现] Created on 2021年12月28日
 * 
 * @author syg
 * @version 1.0 Copyright (c) 2021年 xxxx
 */
@Controller
@RequestMapping("/salary/chSaPaycard/*")
public class ChSaPaycardController extends BaseController {

	public final static String Module_Name = "薪资-工资卡";
	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IChSaPaycardBiz chSaPaycardBiz;

	private final static String PARA_name = "name";

	// 工资条字典缓存时间
	Long cachedTime = null;
	Map<String, List<String>> cachedMap = new HashMap<String, List<String>>();
	private static final Map<String, Object> cachedColumnsMap;
	static {
		cachedColumnsMap = new HashMap<String, Object>();
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
	 * <p>
	 * Discription:[薪资-工资单请求首页]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request, ModelMap modelMap) throws Exception {
		loadCachedDict4Payslip(modelMap);
	}

	@RequestMapping(value = "index/loadTabHead.jhtm")
	@ResponseBody
	public Map<String, Object> loadTabHead(HttpServletRequest request, ModelMap modelMap) {
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		if (StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth)) {
			// 设置扩展属性
			if (StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth)) {
				startMonth = chSaPaycardBiz.queryLastMonth();
				endMonth = startMonth;
			}
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("startMonth", startMonth);
		retMap.put("endMonth", endMonth);
		return retMap;
	}

	/**
	 * <p>
	 * Discription:[薪资-工资卡首页数据加载-分页]
	 * </p>
	 * Created on 2021年05月14日
	 * 
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<ChSaPaycardExtendModel> page(HttpServletRequest request, ModelMap modelMap) throws Exception {
		Result4Page<ChSaPaycardExtendModel> retBody = new Result4Page<ChSaPaycardExtendModel>();
		// 请求参数置入
		String name = request.getParameter(PARA_name);
		// 扩展属性
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		ChSaPaycardExtendModel model = new ChSaPaycardExtendModel();
		model.setName(null != name && !"".equals(name.trim()) ? name.trim() : null);
		// 设置扩展属性
		if (StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth)) {
			startMonth = chSaPaycardBiz.queryLastMonth();
			endMonth = startMonth;
		}
		model.setStartMonth(StringHelper.isNullOrEmpty(startMonth) ? null : startMonth);
		model.setEndMonth(StringHelper.isNullOrEmpty(endMonth) ? null : endMonth);
		try {
			if (StringUtils.isEmpty(name)) {
				retBody.setCode(Page4LayStatus.FAILED);
				retBody.setMsg("name为空，获取失败！");
				return retBody;
			}
			List<Map<String, Object>> list = chSaPaycardBiz.queryList(model);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(list);
			Long count = (long) 33;
			retBody.setCount(count);
		} catch (Exception e) {
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
			e.printStackTrace();
			logger.error(this.getClass() + "工资卡获取失败！", e);
		}
		return retBody;
	}

	/**
	 * 若超过设置的缓存时间，则重新缓存搜索区域条件
	 */
	private synchronized void loadCachedDict4Payslip(ModelMap modelMap) {
		if (null == modelMap)
			return;
		// 第一步：判断是否超过缓存时常，若超过，则重新加载数据
		Long curTime = System.currentTimeMillis();
		if (null == cachedTime || curTime - cachedTime > (24 * 60 * 60 * 1000) || cachedMap.isEmpty()) {
			cachedTime = curTime;// 将当前时间设置为缓存时间
			cachedMap.clear();// 清空当前的缓存数据列表
			// String fValues = null;
			List<String> fvallist = null;
			for (String column : cachedColumnsMap.keySet()) {
				fvallist = chSaPaycardBiz.loadSelectFields(column);
				if (null == fvallist)
					fvallist = new ArrayList<String>();
				cachedMap.put(cachedColumnsMap.get(column) + "List", fvallist);
			}
		}
		// 第二步：重新返回缓存数据
		List<ComboxItem> comboxs = null;
		for (String key : cachedMap.keySet()) {
			List<String> list = cachedMap.get(key);
			comboxs = new ArrayList<ComboxItem>();
			for (String item : list) {
				comboxs.add(new ComboxItem(item, item));
			}
			modelMap.addAttribute(key, JSON.toJSON(comboxs));// 用于页面多选
		}
	}

	/**
	 * <p>
	 * Discription:[薪资-工资卡设置查询条件]
	 * </p>
	 * 
	 * @param request请求对象
	 * @param builder分页构造器 Created on 2021年05月14日
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<ChSaPaycardExtendModel> builder)
			throws Exception {
		// 页面分页信息置入
		String pageStr = request.getParameter(CommonConstant.PARA_PAGE);
		String limitStr = request.getParameter(CommonConstant.PARA_LIMIT);
		Integer curPageIndex = null != pageStr ? Integer.parseInt(pageStr) : null;
		Integer pageSize = null != limitStr ? Integer.parseInt(limitStr) : null;
		// 请求参数置入
		String name = request.getParameter(PARA_name);

		// 扩展属性
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");

		ChSaPaycardExtendModel model = new ChSaPaycardExtendModel();
		model.setName(null != name && !"".equals(name.trim()) ? name.trim() : null);

		// 设置扩展属性
		if (StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth)) {
			startMonth = chSaPaycardBiz.queryLastMonth();
			endMonth = startMonth;
		}
		model.setStartMonth(StringHelper.isNullOrEmpty(startMonth) ? null : startMonth);
		model.setEndMonth(StringHelper.isNullOrEmpty(endMonth) ? null : endMonth);

		// 置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}

}
