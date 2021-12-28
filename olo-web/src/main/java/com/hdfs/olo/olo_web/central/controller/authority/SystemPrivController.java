package com.hdfs.olo.olo_web.central.controller.authority;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemPrivBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
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
 * <p>Description: [系统权限 控制层]</p>
 * Created on 2020年03月27日
 * @author huadf
 * @version 1.0 
 * Copyright (c) 2020年
 */
@Controller
@RequestMapping("/central/systemPriv/*")
public class SystemPrivController extends BaseController{
	public final static String Module_Name = "系统权限";
	Log logger = LogFactory.getLog(this.getClass());
	
	private	final static String PARA_privId = "privId";
	private	final static String PARA_privCnName = "privCnName";
	private	final static String PARA_privEnName = "privEnName";
	private	final static String PARA_privTarget = "privTarget";
	private	final static String PARA_remark = "remark";
	private	final static String PARA_privType = "privType";
	private	final static String PARA_menuLevel = "menuLevel";
	private	final static String PARA_sortVal = "sortVal";
	private	final static String PARA_privMethod = "privMethod";
	private	final static String PARA_privPermissioin = "privPermissioin";
	private	final static String PARA_privAction = "privAction";
	private	final static String PARA_privIcon = "privIcon";
	//private	final static String PARA_privSuper = "privSuper";
	private	final static String PARA_priv4Level1 = "menu4Level1";
	private	final static String PARA_priv4Level2 = "menu4Level2";
	private	final static String PARA_status = "status";
	
	@Autowired
	private ISystemPrivBiz systemPrivBiz;
	@Autowired
	private IAuthorityBiz authorityBiz;
	
	/**
	 * <p>Discription:[系统权限请求首页]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.put("statusList", statusList);
		
		SystemPrivModel model = new SystemPrivModel();
		model.setPrivType(1);
		List<SystemPrivModel> list = systemPrivBiz.queryList(model);
		modelMap.put("superList", list);
	}
	
	/**
	 * <p>Discription:[系统权限首页数据加载-分页]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "index/do.jhtm")
	@ResponseBody
	public Result4Page<SystemPrivModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<SystemPrivModel> retBody = new Result4Page<SystemPrivModel>();
		//查询参数
		Page.Builder<SystemPrivModel> builder = new Page.Builder<SystemPrivModel>();
		setSearchParameters(request, builder);
		Page<SystemPrivModel> pageInfo = builder.build();
		try {
			//分页
			systemPrivBiz.queryPage(request,pageInfo);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(pageInfo.getDatas());
			retBody.setCount(pageInfo.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		return retBody;
	}
	
	/**
	 * <p>Discription:[系统权限设置查询条件]</p>
	 * @param request请求对象
	 * @param builder分页构造器
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<SystemPrivModel> builder) {
		//页面分页信息置入
		String pageStr  = request.getParameter("page");
		String limitStr  = request.getParameter("limit");
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		//请求参数置入
		String privIdStr = request.getParameter(PARA_privId);
		Long privId = StringHelper.isNullOrEmpty(privIdStr)?null:Long.parseLong(privIdStr);
		String privCnName = request.getParameter(PARA_privCnName);
		String privEnName = request.getParameter(PARA_privEnName);
		String privTarget = request.getParameter(PARA_privTarget);
		String remark = request.getParameter(PARA_remark);
		String privTypeStr = request.getParameter(PARA_privType);
		Integer privType = !StringHelper.isNullOrEmpty(privTypeStr)?Integer.parseInt(privTypeStr):null;
		String menuLevelStr = request.getParameter(PARA_menuLevel);
		Integer menuLevel = !StringHelper.isNullOrEmpty(menuLevelStr)?Integer.parseInt(menuLevelStr):null;
		String privMethod = request.getParameter(PARA_privMethod);
		String privPermissioin = request.getParameter(PARA_privPermissioin);
		String privAction = request.getParameter(PARA_privAction);
		String privIcon = request.getParameter(PARA_privIcon);
		String priv4Level1Str = request.getParameter(PARA_priv4Level1);
		Long priv4Level1 = StringHelper.isNullOrEmpty(priv4Level1Str)?null:Long.parseLong(priv4Level1Str);
		String priv4Level2Str = request.getParameter(PARA_priv4Level2);
		Long priv4Level2 = StringHelper.isNullOrEmpty(priv4Level2Str)?null:Long.parseLong(priv4Level2Str);
		Long privSuper = null!=priv4Level1?null!=priv4Level2?priv4Level2:priv4Level1:null;
		
		String statuStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statuStr)?Integer.parseInt(statuStr):null;
		String sortValStr = request.getParameter(PARA_sortVal);
		Integer sortVal = !StringHelper.isNullOrEmpty(sortValStr)?Integer.parseInt(sortValStr):null;
        
		SystemPrivModel model = new SystemPrivModel();
        model.setPrivId(null!=privId?privId:null);
        model.setPrivCnName(null!=privCnName?privCnName:null);
        model.setPrivEnName(null!=privEnName?privEnName:null);
        model.setPrivTarget(null!=privTarget?privTarget:null);
        model.setRemark(null!=remark?remark:null);
        model.setPrivType(null!=privType?privType:null);
        model.setMenuLevel(null!=menuLevel?menuLevel:null);
        model.setPrivMethod(null!=privMethod?privMethod:null);
        model.setPrivPermissioin(null!=privPermissioin?privPermissioin:null);
        model.setPrivAction(null!=privAction?privAction:null);
        model.setPrivIcon(null!=privIcon?privIcon:null);
        model.setPrivSuper(null!=privSuper?privSuper:null);
        model.setStatus(null!=status?status:null);
        model.setSortVal(null!=sortVal?sortVal:null);
		//置入构造器
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(model);
	}
	
	/**
	 * <p>Discription:[系统权限 校验]</p>
	 * @param request请求对象
	 * @param errMsg错误信息
	 * @param model实体对象
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer errMsg, SystemPrivModel model) {
		String privIdStr = request.getParameter(PARA_privId);
		String privCnName = request.getParameter(PARA_privCnName);
		Long privId = StringHelper.isNullOrEmpty(privIdStr)?null:Long.parseLong(privIdStr);
		String privEnName = request.getParameter(PARA_privEnName);
		String privTarget = request.getParameter(PARA_privTarget);
		String remark = request.getParameter(PARA_remark);
		String privTypeStr = request.getParameter(PARA_privType);
		Integer privType = StringHelper.isNullOrEmpty(privTypeStr)?null:Integer.parseInt(privTypeStr);
		String privMethod = request.getParameter(PARA_privMethod);
		String privPermissioin = request.getParameter(PARA_privPermissioin);
		String privAction = request.getParameter(PARA_privAction);
		String privIcon = request.getParameter(PARA_privIcon);
		String menuLevelStr = request.getParameter(PARA_menuLevel);
		Integer menuLevel = StringHelper.isNullOrEmpty(menuLevelStr)?null:Integer.parseInt(menuLevelStr);
		String priv4Level1Str = request.getParameter(PARA_priv4Level1);
		Long priv4Level1 = StringHelper.isNullOrEmpty(priv4Level1Str)?null:Long.parseLong(priv4Level1Str);
		String priv4Level2Str = request.getParameter(PARA_priv4Level2);
		Long priv4Level2 = StringHelper.isNullOrEmpty(priv4Level2Str)?null:Long.parseLong(priv4Level2Str);
		Long privSuper = null!=priv4Level1?null!=priv4Level2?priv4Level2:priv4Level1:null;
		String statuStr = request.getParameter(PARA_status);
		Integer status = !StringHelper.isNullOrEmpty(statuStr)?Integer.parseInt(statuStr):null;
		if(2 == privType) menuLevel = null;
		if(null != menuLevel && 1 == menuLevel)
		{
			SystemPrivModel priv = new SystemPrivModel();
			priv.setPrivSuper(-1l);
			List<SystemPrivModel> root = systemPrivBiz.queryList(priv);
			privSuper = root.size()>0?root.get(0).getPrivId():null;
		}
		if(null == privSuper) {
			errMsg.append("请选择上级权限编号!");
			errMsg.append(LayAjaxHelper.CR);
		}
		
		if (errMsg.length() == 0) {
			model.setPrivId(null!=privId?privId:null);
			model.setPrivCnName(null!=privCnName?privCnName.trim():null);
			model.setPrivEnName(null!=privEnName?privEnName.trim():null);
			model.setPrivTarget(null!=privTarget?privTarget.trim():null);
			model.setRemark(null!=remark?remark.trim():null);
			model.setPrivType(null!=privType?privType:null);
			model.setMenuLevel(menuLevel);
			model.setPrivMethod(null!=privMethod?privMethod.trim():null);
			model.setPrivPermissioin(null!=privPermissioin?privPermissioin.trim():null);
			model.setPrivAction(null!=privAction?privAction.trim():null);
			model.setPrivIcon(null!=privIcon?privIcon.trim():null);
			model.setPrivSuper(null!=privSuper?privSuper:null);
			model.setStatus(null!=status?status:null);
		}
	}
	
	/**
	 * <p>Discription:[系统权限 添加转向]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value="add.jhtm")
	public void add(Long privSuper,ModelMap modelMap) {
		//SystemPrivModel superModel = systemPrivBiz.queryById(privSuper);
		List<ComboxItem> targetList =  getTargetList();
		List<ComboxItem> statusList = getStatusList();
		List<ComboxItem> typeList = getTypeList();
		List<ComboxItem> menuLevelList = getMenuLevelList();
		//modelMap.addAttribute("superModel", superModel);
		modelMap.addAttribute("targetList", targetList);
		modelMap.addAttribute("statusList", statusList);
		modelMap.addAttribute("typeList", typeList);
		modelMap.addAttribute("menuLevelList", menuLevelList);
	}
	
	/**
	 * <p>Discription:[系统权限 根据一级菜单查询二级菜单]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "loadPriv4Menu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loadPriv4Level2(HttpServletRequest request) {
		Map<String, Object> retMap = new HashMap<String,Object>();
		try {
			String menuLevelStr = request.getParameter("menuLevel");
			Integer menuLevel = StringHelper.isNullOrEmpty(menuLevelStr)?null:Integer.parseInt(menuLevelStr);
			String privSuperStr = request.getParameter("privSuper");
			Long privSuper = StringHelper.isNullOrEmpty(privSuperStr)?null:Long.parseLong(privSuperStr);
			if(null == menuLevel || (2 == menuLevel && null == privSuper))
			{
				retMap.put("retCode", 201);
				retMap.put("retMsg", "菜单级别或上级权限菜单编号不存在！");
				return retMap;
			}
			SystemPrivModel model = new SystemPrivModel();
			model.setPrivType(1);
			model.setPrivSuper(1==menuLevel?1l:privSuper);
			List<SystemPrivModel> list = systemPrivBiz.queryList(model, "PRIV_ID,PRIV_CN_NAME");
			
			retMap.put("retCode", 200);
			retMap.put("retMsg","加载成功!");
			retMap.put("retData", list);
		} catch (Exception ex) {
			retMap.put("retCode", 201);
			retMap.put("retMsg", "加载异常！");
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
		}
		return retMap;
	}
	
	/**
	 * <p>Discription:[系统权限 添加执行]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "add/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doAdd(HttpServletRequest request) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			SystemPrivModel model = new SystemPrivModel();
			//基本验证
			baseValidate(request, errMsg, model);
			//若验证失败,则返回
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			//执行添加
			systemPrivBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:"+model+",other:"+model);
			
			//返回成功信息
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_add,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[系统权限 编辑转向]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(Long privId, ModelMap modelMap) throws Exception{
		if(null == privId) return;
		List<ComboxItem> statusList = getStatusList();
		List<ComboxItem> targetList =  getTargetList();
		List<ComboxItem> typeList = getTypeList();
		SystemPrivModel model = systemPrivBiz.queryById(privId);
		
		modelMap.addAttribute("model", model);
		modelMap.addAttribute("targetList", targetList);
		modelMap.addAttribute("statusList", statusList);
		modelMap.addAttribute("typeList", typeList);
		List<ComboxItem> menuLevelList = getMenuLevelList();
		modelMap.addAttribute("menuLevelList", menuLevelList);
		
		List<SystemPrivModel> roots = systemPrivBiz.queryListBySuper(-1l);
		if(roots.size()!=1) throw new Exception("检测到系统中缺失根权限记录！");
		SystemPrivModel qModel = new SystemPrivModel();
		qModel.setPrivType(1);
		qModel.setMenuLevel(1);
		qModel.setPrivSuper(roots.get(0).getPrivId());
		List<SystemPrivModel> menu1 = systemPrivBiz.queryList(qModel, "PRIV_ID,PRIV_CN_NAME,PRIV_SUPER");
		modelMap.addAttribute("menu4Level1", menu1);
		if(2 == model.getPrivType())
		{
			SystemPrivModel superPriv = systemPrivBiz.queryById(model.getPrivSuper());
			qModel.setMenuLevel(2);
			qModel.setPrivSuper(superPriv.getPrivSuper());
			List<SystemPrivModel> menu2 = systemPrivBiz.queryList(qModel, "PRIV_ID,PRIV_CN_NAME,PRIV_SUPER");
			modelMap.addAttribute("menu4Level2", menu2);
			modelMap.addAttribute("curMenu2Id", superPriv.getPrivId());
			modelMap.addAttribute("curMenu1Id", superPriv.getPrivSuper());
		}else if(1 == model.getPrivType() && 2 == model.getMenuLevel())
		{
			modelMap.addAttribute("curMenu1Id", model.getPrivSuper());
		}
	}
	
	/**
	 * <p>Discription:[系统权限 编辑执行]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, Long privId) {
		Map<String, Object> map = null;
		try {
			StringBuffer errMsg = new StringBuffer();
			//必须输入参数
			if (null == privId) {
				errMsg.append("请输入编号!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			SystemPrivModel model = systemPrivBiz.queryById(privId);
			if(null == model)
			{
				errMsg.append("该记录在系统中不存在!");
				errMsg.append(LayAjaxHelper.CR);
				return LayAjaxHelper.fail(errMsg.toString());
			}
			baseValidate(request, errMsg, model);
			if (errMsg.length() > 0)
				return LayAjaxHelper.fail(errMsg.toString());
			
			systemPrivBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:"+model+",other:"+model);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[系统权限 单项删除执行]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "delete.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(HttpServletRequest request,Long privId) {
		Map<String, Object> map = null;
		try {
			if(null == privId) return LayAjaxHelper.fail("请选择编号!");
			SystemPrivModel model = systemPrivBiz.queryById(privId);
			if(null != model)
			{
				authorityBiz.deleteSystemPriv(privId);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:"+model+",other:"+model);
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + "," + ex.getMessage());
		}
		return map;
	}
	
	/**
	 * <p>Discription:[系统权限 批量删除执行]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			systemPrivBiz.delBatchByIds(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"用户编号:"+ids);
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_delete,ex.getMessage());
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	
	/**
	 * <p>Discription:[系统权限 详情转向/执行]</p>
	 * Created on 2020年03月27日
	 * @author:huadf
	 */
	@RequestMapping(value = "details.jhtm")
	public void details(HttpServletRequest request,Long privId, ModelMap modelMap) {
		if(null == privId) return;
		List<ComboxItem> statusList = getStatusList();
		List<ComboxItem> targetList =  getTargetList();
		List<ComboxItem> typeList = getTypeList();
		SystemPrivModel model = systemPrivBiz.queryById(privId);
		SystemPrivModel superModel = systemPrivBiz.queryById(model.getPrivSuper());
		
		modelMap.addAttribute("model", model);
		modelMap.addAttribute("superModel", superModel);
		modelMap.addAttribute("targetList", targetList);
		modelMap.addAttribute("statusList", statusList);
		modelMap.addAttribute("typeList", typeList);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:"+model+",other:"+model);
	}
	
	private static List<ComboxItem> targetList = null;
	private static List<ComboxItem> statusList = null;
	private static List<ComboxItem> typeList  = null;
	/**
	 * 显示方式下拉框
	 * @return
	 */
	public List<ComboxItem> getTargetList()
	{
		
		if(targetList == null)
		{
			targetList= new ArrayList<ComboxItem>();
			targetList.add(new ComboxItem(DictionaryUtil.KEY_ALL,DictionaryUtil.CN_KEY_NOTHING));
			targetList.add(new ComboxItem(DictionaryUtil.KEY_TARGET_1,DictionaryUtil.CN_KEY_TARGET_1));
			targetList.add(new ComboxItem(DictionaryUtil.KEY_TARGET_2,DictionaryUtil.CN_KEY_TARGET_2));
		}
		return targetList;
	}

	/**
	 * 状态下拉框
	 * @return
	 */
	public List<ComboxItem> getStatusList()
	{
		
		if(statusList == null)
		{
			statusList= new ArrayList<ComboxItem>();
			statusList.add(new ComboxItem(DictionaryUtil.KEY_NORMAL+"",DictionaryUtil.CN_KEY_NORMAL));
			statusList.add(new ComboxItem(DictionaryUtil.KEY_NOT_NORMAL+"",DictionaryUtil.CN_KEY_DISABLE));
		}
		return statusList;
	}
	
	/**
	 * 类型下拉框
	 * @return
	 */
	public List<ComboxItem> getTypeList()
	{
		if(typeList == null)
		{
			typeList = new ArrayList<ComboxItem>();
			typeList.add(new ComboxItem("1",DictionaryUtil.CN_KEY_PRIV_TYPE_1));
			typeList.add(new ComboxItem("2",DictionaryUtil.CN_KEY_PRIV_TYPE_2));
		}
		return typeList;
	}
}
