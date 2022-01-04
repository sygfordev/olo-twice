package com.hdfs.olo.olo_web.salary.controller;

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

import com.alibaba.druid.util.StringUtils;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
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
		String name = request.getParameter("name");
		String cardNo = request.getParameter("cardNo");
		// 扩展属性
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		ChSaPaycardExtendModel model = new ChSaPaycardExtendModel();
		model.setName(null != name && !"".equals(name.trim()) ? name.trim() : null);
		model.setCardNo(null != cardNo && !"".equals(cardNo.trim()) ? cardNo.trim() : null);
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
				retBody.setMsg("姓名为空，获取失败！");
				return retBody;
			}
			if (StringUtils.isEmpty(cardNo)) {
				retBody.setCode(Page4LayStatus.FAILED);
				retBody.setMsg("身份证号为空，获取失败！");
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

	@RequestMapping(value = "export.jhtm")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) {
		// 请求参数置入
		String name = request.getParameter("name");
		String cardNo = request.getParameter("cardNo");
		// 扩展属性
		String startMonth = request.getParameter("startMonth");
		String endMonth = request.getParameter("endMonth");
		ChSaPaycardExtendModel model = new ChSaPaycardExtendModel();
		model.setName(null != name && !"".equals(name.trim()) ? name.trim() : null);
		model.setCardNo(null != cardNo && !"".equals(cardNo.trim()) ? cardNo.trim() : null);
		// 设置扩展属性
		if (StringHelper.isNullOrEmpty(startMonth) && StringHelper.isNullOrEmpty(endMonth)) {
			startMonth = chSaPaycardBiz.queryLastMonth();
			endMonth = startMonth;
		}
		model.setStartMonth(StringHelper.isNullOrEmpty(startMonth) ? null : startMonth);
		model.setEndMonth(StringHelper.isNullOrEmpty(endMonth) ? null : endMonth);
		try {
			chSaPaycardBiz.exportExcel(request, response, model);
		} catch (Exception e) {
			logger.error("工资卡导出！error:", e);
		}
	}

}
