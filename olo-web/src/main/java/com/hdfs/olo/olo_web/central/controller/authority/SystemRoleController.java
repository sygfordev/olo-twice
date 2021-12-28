package com.hdfs.olo.olo_web.central.controller.authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.biz.IAuthorityBiz;
import com.hdfs.olo.olo_web.central.biz.ISystemRoleBiz;
import com.hdfs.olo.olo_web.central.controller.BaseController;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.central.model.SystemRoleModel;
import com.hdfs.olo.olo_web.central.model.UserInfoModel;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.constant.LogDictionary;
import com.hdfs.olo.olo_web.plugins.common.message.Page4LayStatus;
import com.hdfs.olo.olo_web.plugins.common.message.Result4Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page;
import com.hdfs.olo.olo_web.plugins.common.page.Page.Builder;
import com.hdfs.olo.olo_web.plugins.common.utils.ComboxItem;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;
import com.hdfs.olo.olo_web.plugins.common.utils.treexts.TreeNodeUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.web.LayAjaxHelper;

/**
 * 角色、角色-权限、角色-用户
 * @author huadf
 *
 */
@Controller
@RequestMapping("/central/systemRole/*")
public class SystemRoleController extends BaseController{
	
	public final static String Module_Name = "角色";
	Log logger = LogFactory.getLog(this.getClass());
	
	private	final static String PARA_roleName = "roleName";
	private	final static String PARA_roleLevel = "roleLevel";
	private	final static String PARA_roleStatus = "status";
	private	final static String PARA_roleRemark = "remark";

	@Autowired
	private ISystemRoleBiz systemRoleBiz;
	@Autowired
	private IAuthorityBiz authorityBiz;
	
	/**
	 * 获取列表
	 * @param request
	 * @param model
	 */
	@RequestMapping("index.jhtm")
	public void index(HttpServletRequest request,ModelMap modelMap) {
		
	}
	
	@RequestMapping("index/do.jhtm")
	@ResponseBody
	public Result4Page<SystemRoleModel> page(HttpServletRequest request,ModelMap modelMap) {
		Result4Page<SystemRoleModel> retBody = new Result4Page<SystemRoleModel>();
		//查询参数
		Page.Builder<SystemRoleModel> builder = new Page.Builder<SystemRoleModel>();
		setSearchParameters(request, builder);
		Page<SystemRoleModel> pageInfo = builder.build();
		try {
			//分页
			systemRoleBiz.queryPage(pageInfo);
			retBody.setCode(Page4LayStatus.SUCCESS);
			retBody.setMsg("获取成功！");
			retBody.setData(pageInfo.getDatas());
			retBody.setCount(pageInfo.getRecordTotal());
		}catch(Exception e)
		{
			retBody.setCode(Page4LayStatus.FAILED);
			retBody.setMsg("获取失败！");
		}
		log2Info(request, Module_Name, LogDictionary.Module_Oper_index);
		return retBody;
	}

	/**
	 * 设置查询参数
	 * @param request
	 * @param pageInfo
	 */
	private void setSearchParameters(HttpServletRequest request, Builder<SystemRoleModel> builder) {
		SystemRoleModel dto = new SystemRoleModel();
		String pageStr  = request.getParameter("page");
		String limitStr  = request.getParameter("limit");
		Integer curPageIndex  = null!=pageStr?Integer.parseInt(pageStr):null;
		Integer pageSize  = null!=limitStr?Integer.parseInt(limitStr):null;
		String roleName = request.getParameter(PARA_roleName);
		if(!StringHelper.isNullOrEmpty(roleName))
		{
			dto.setRoleName(roleName);
		}
		builder.curPageIndex(curPageIndex).pageSize(pageSize).model(dto);
	}

	/**
	 * 验证
	 * @param request
	 * @param stringBuffer  错误信息
	 * @param model	
	 */
	private void baseValidate(HttpServletRequest request,
			StringBuffer stringBuffer, SystemRoleModel model) {
		String roleName = request.getParameter(PARA_roleName);
		String roleLevel = request.getParameter(PARA_roleLevel);
		String status = request.getParameter(PARA_roleStatus);
		String remark = request.getParameter(PARA_roleRemark);
		if (StringHelper.isNullOrEmpty(roleName)) {
			stringBuffer.append("请输入角色名称!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if (StringHelper.isNullOrEmpty(roleLevel)) {
			stringBuffer.append("请选择角色级别!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if (StringHelper.isNullOrEmpty(status)) {
			stringBuffer.append("请选择状态!");
			stringBuffer.append(LayAjaxHelper.CR);
		}
		if (stringBuffer.length() == 0) {
			model.setRoleName(roleName.trim());
			model.setRoleLevel(roleLevel.trim());
			model.setStatus(status);
			model.setRemark(StringHelper.isNullOrEmpty(remark)?null:remark.trim());
		}
	}

	/**
	 * 获取增加页面
	 */
	@RequestMapping(value = "add.jhtm")
	public void add(HttpServletRequest request,ModelMap modelMap) {
		List<ComboxItem> statusList = getStatusList();
		modelMap.addAttribute("statusList", statusList);
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
			SystemRoleModel model = new SystemRoleModel();
			//基本验证
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) 
				return LayAjaxHelper.fail(stringBuffer.toString());
			//增加
			systemRoleBiz.save(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_add,"编号:" + model.getRoleId()+",名称:"+model.getRoleName());
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
	 *  获取编辑页面
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="edit.jhtm")
	public void edit(Long roleId, ModelMap modelMap) {
		if(null == roleId) return;
		List<ComboxItem> statusList = getStatusList();
		modelMap.addAttribute("statusList", statusList);
		
		SystemRoleModel model = systemRoleBiz.queryById(roleId);
		modelMap.addAttribute("model", model);
	}

	/**
	 *  编辑操作
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doEdit(HttpServletRequest request, @RequestParam("roleId") Long id) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			SystemRoleModel model = systemRoleBiz.queryById(id);
			baseValidate(request, stringBuffer, model);
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			systemRoleBiz.edit(model);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_edit,"编号:" + model.getRoleId()+",名称:"+model.getRoleName());
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
	public Map<String, Object> delete(HttpServletRequest request,Long roleId) {
		Map<String, Object> map = null;
		try {
			if(null == roleId) return LayAjaxHelper.fail("未检测到角色编号!");
			SystemRoleModel model = systemRoleBiz.queryById(roleId);
			if(model != null)
			{
				authorityBiz.deleteSystemRole(roleId);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"编号:" + model.getRoleId()+",名称:"+model.getRoleName());
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
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "batchDel.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> batchDel(HttpServletRequest request,@RequestParam("ids[]") List<Long> ids) {
		Map<String, Object> map = null;
		try {
			if(null == ids || ids.isEmpty()) return LayAjaxHelper.fail("请选择数据!");
			authorityBiz.deleteBatchSystemRole(ids);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_delete,"角色编号:"+ids);
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
	public void details(HttpServletRequest request,Long id, ModelMap modelMap) {
		SystemRoleModel model = systemRoleBiz.queryById(id);
		modelMap.addAttribute("model", model);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_details,"编号:" + model.getRoleId()+",名称:" + model.getRoleName());
	}
	
	
	/***********************角色-权限******************************/
	private static  List<ComboxItem> appendList = null;
	/**
	 * 是否追加，下拉框
	 */
	public List<ComboxItem> getAppendList()
	{
		if(appendList == null){
			 appendList = new ArrayList<ComboxItem>();
			 appendList.add(new ComboxItem(DictionaryUtil.KEY_NORMAL+"",DictionaryUtil.CN_KEY_YES));
			 appendList.add(new ComboxItem(DictionaryUtil.KEY_NOT_NORMAL+"",DictionaryUtil.CN_KEY_NO));
		}
		return appendList;
	}
	
	/**
	 * 获取角色-权限管理页面
	 */
	@RequestMapping(value="rolePrivCfg.jhtm")
	public void rolePrivCfg(HttpServletRequest request,Long roleId,ModelMap modelMap)
	{
		if(null == roleId) return;
		SystemRoleModel model = systemRoleBiz.queryById(roleId);
		//当前角色所拥有的权限功能
		List<SystemPrivModel> systemPrivList = authorityBiz.getAllSystemPrivByRoleId(roleId);
		//将权限功能的编号用','隔开然后组成一个字符串
		
		String str = "";
        if(systemPrivList.size() > 0)
        {
        	StringBuffer buffer = new StringBuffer();
            for (SystemPrivModel item : systemPrivList)
            {
            	buffer.append(item.getPrivId());
            	buffer.append(",");
            }
            str =  buffer.substring(0, buffer.length()-1);
        }
        //下拉框
        List<ComboxItem> appendList = getAppendList();
        
		modelMap.addAttribute("model", model);
		modelMap.addAttribute("systemPrivList", str);
		modelMap.addAttribute("appendList", appendList);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_rolePrivCfg,"编号:" + model.getRoleId()+",角色名称:" + model.getRoleName());
	}
	
	/**
	 * ajax获取所有权限
	 * @return
	 */
	@RequestMapping(value = "rolePrivCfg/getall.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public List<SystemPrivModel> rolePrivCfgGetAllSystemPriv()
	{
		return authorityBiz.getAllSystemPriv();
	}
	
	/**
	 * ajax获取所有权限
	 * @return
	 */
	@RequestMapping(value = "test12.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> test12()
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		//List list = authorityBiz.getAllSystemPriv();
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> it = null;
		for(int i=1;i<50;i++)
		{
			it = new HashMap<String,Object>();
			it.put("id", i);
			it.put("name", "名称"+i);
			it.put("pid", (i==4 || i==8)?0:2);
			list.add(it);
		}
		int[] checked = { 1,  2, 3, 4 };
		
		retMap.put("code", 0);
		retMap.put("msg", "cehgngong");
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("list", list);
		item.put("checkedId", checked);
		retMap.put("data", item);
		return retMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "test.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> test(HttpServletRequest request,Long roleId) {
		Map<String,Object> retMap = new HashMap<String,Object>();
		if(null == roleId)
		{
			retMap.put("code", 1);
			retMap.put("msg", "角色编号为空!");
			return retMap;
		}
		try {
			SystemRoleModel model = systemRoleBiz.queryById(roleId);
			if(null == model)
			{
				retMap.put("code", 1);
				retMap.put("msg", "当前角色不存在!");
				return retMap;
			}
			//当前角色所拥有的权限 和  所有权限查询
			List<SystemPrivModel> alls = authorityBiz.getAllSystemPriv();
			List<SystemPrivModel> seted = authorityBiz.getAllSystemPrivByRoleId(roleId);
			List<Long> checked = new ArrayList<Long>();
			for(SystemPrivModel item:seted)
			{
				checked.add(item.getPrivId());
			}
			
			TreeNodeUtil.CvtConfig builder = new TreeNodeUtil.CvtConfig();
			Map<String,String> keyMaps = new HashMap<String,String>();
			keyMaps.put("privCnName", "name");
			keyMaps.put("privId", "value");
			TreeNodeUtil util = builder.idKey("privId").pidKey("privSuper").rootId(-1l).subKey("list").checkedIds(checked).keyMaps(keyMaps).build();
			JSONObject tmp = util.merge(alls);
			JSONArray array = new JSONArray();
			array.add(tmp);
			JSONObject data = new JSONObject();
			data.put("trees", array);
			retMap.put("code", 0);
			retMap.put("msg", "加载成功");
			retMap.put("data", data);
		}catch(Exception e)
		{
			retMap.put("code", 1);
			retMap.put("msg", "加载失败！");
			logger.error("",e);
		}
		return retMap;
	}
	
	/**
	 * 角色-权限的编辑操作
	 * @param request
	 * @param id 角色编号
	 * @param systemPrivList 权限字符串，用逗号隔开
	 * @param isAppend 是否追加权限
	 * @return
	 */
	@RequestMapping(value = "rolePrivCfg/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doRolePrivCfgEdit(HttpServletRequest request,Long roleId,
			@RequestParam(value="systemPrivList[]")String[] systemPrivList,Integer isAppend) {
		Map<String, Object> map = null;
		try {
			StringBuffer stringBuffer = new StringBuffer();
			if(null == roleId)
			{
				stringBuffer.append("输入角色编号!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			
			if(StringHelper.isNullOrEmpty(systemPrivList))
			{
				stringBuffer.append("非法操作!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			if(null == isAppend)
			{
				stringBuffer.append("非法操作!");
				stringBuffer.append(LayAjaxHelper.CR);
			}
			if (stringBuffer.length() > 0) {
				return LayAjaxHelper.fail(stringBuffer.toString());
			}
			SystemRoleModel model = systemRoleBiz.queryById(roleId);
			if(model == null)
			{
				return LayAjaxHelper.fail("输入角色编号!");
			}
			
			//true是往用户-权限追加新的权限,false不追加
			boolean b = DictionaryUtil.KEY_NORMAL == isAppend ? true : false;
			
			//当前角色所拥有的权限功能
			List<SystemPrivModel> list = authorityBiz.getAllSystemPrivByRoleId(roleId);
			//功能编号字符串,以逗号隔开
			//String[] systemPrivString = systemPrivList.split(",");
			
			//已存的信息的集合
			List<SystemPrivModel> existList = new ArrayList<SystemPrivModel>();
			//增加信息的集合
			List<SystemPrivModel> addList = new ArrayList<SystemPrivModel>();
			//删除信息的集合
			List<SystemPrivModel> deleteList = new ArrayList<SystemPrivModel>();
			
			boolean isok = false;
			SystemPrivModel newSystemPriv = null;
			//遍历前台要保存的功能信息，如果存在数据库，则保存在exitList集合
			//如果没有存在数据库，则保存在addList
			for (String item : systemPrivList) {
				isok = false;//是否存在，true表示存在，false不存在
				for (SystemPrivModel priv : list) {
					if((priv.getPrivId().intValue()+"").equals(item))
					{
						isok = true;
						//已存在的信息
						existList.add(priv);
						break;
					}
				}
				if(!isok)//不存在的情况
				{
					newSystemPriv = new SystemPrivModel();
					newSystemPriv.setPrivId(Long.parseLong(item));
					//保存到addList
					addList.add(newSystemPriv);
				}
			}
			//判断exitList(已存在信息)和list(数据库中的信息)这两个集合，哪些信息是要删除的
			for (SystemPrivModel item : list) {
				isok = false;//是否存在，true表示存在，false不存在
				for (SystemPrivModel priv : existList) {
					if(priv.getPrivId().intValue()== item.getPrivId().intValue())
					{
						isok = true;
						break;
					}
				}
				if(!isok)//不存在
				{
					deleteList.add(item);
				}
			}
			//更新角色下面的权限，更新用户-权限信息
			authorityBiz.updateSystemPriv4Role(model, addList, deleteList, b);
			log2Info(request, Module_Name, LogDictionary.Module_Oper_rolePrivCfg+"-"+LogDictionary.Module_Oper_edit,"编号:" + model.getRoleId()+",角色名称:" + model.getRoleName());
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_rolePrivCfg+"-"+LogDictionary.Module_Oper_edit,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
	
	/**
	 * 获取用户-角色管理页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="userRoleCfg.jhtm")
	public void userRoleCfg(HttpServletRequest request,Long roleId,ModelMap modelMap)
	{
		if(null == roleId) return;
		SystemRoleModel model = systemRoleBiz.queryById(roleId);
		if(null == model) return;
		modelMap.addAttribute("model", model);
//		Object[] obj = authorityBiz.getUserInfoListByRoleId(roleId);
//		List<UserInfoModel> list0 = (List<UserInfoModel>) obj[0];//已选
//		List<UserInfoModel> list1 = (List<UserInfoModel>) obj[1];//可选
//		
//		modelMap.addAttribute("list0", list0);
//		modelMap.addAttribute("list1", list1);
		log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg,"编号:" + model.getRoleId()+",角色名称:" + model.getRoleName());
	}
	/**
	 * 获取用户-角色管理页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="userRoleCfg/loadData.jhtm")
	@ResponseBody
	public Map<String,Object> loadUserRoleCfgData(HttpServletRequest request,Long roleId,ModelMap modelMap)
	{
		Map<String,Object> retMap = new HashMap<String,Object>();
		if(null == roleId)
		{
			retMap.put("retCode", 201);
			retMap.put("retMsg", "角色编号为空");
			return retMap;
		}
		Object[] obj = authorityBiz.getUserInfoListByRoleId(roleId);
		List<UserInfoModel> list0 = (List<UserInfoModel>) obj[0];//已选
		List<UserInfoModel> list1 = (List<UserInfoModel>) obj[1];//可选
		
		list1.addAll(list0);
		JSONObject jsonObj = null;
		JSONArray selected = new JSONArray();
		JSONArray allselect = new JSONArray();
		for(UserInfoModel item:list0)
		{
			selected.add(item.getUserId());
		}
		for(UserInfoModel item:list1)
		{
			jsonObj = new JSONObject();
			jsonObj.put("value", item.getUserId());
			jsonObj.put("title", item.getUserRealName());
			allselect.add(jsonObj);
		}
		JSONObject data = new JSONObject();
		data.put("0",selected);
		data.put("1", allselect);
		retMap.put("retCode", 200);
		retMap.put("retMsg", "加载成功!");
		retMap.put("retData", data);
		return retMap;
	}
	
	/**
	 * 用户-角色管理  操作
	 * @param request
	 * @param id
	 * @param modelMap
	 */
	@RequestMapping(value="userRoleCfg/do.jhtm", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doUserRoleCfg(HttpServletRequest request,Long roleId)
	{
		Map<String, Object> map = null;
		try {
			SystemRoleModel model = systemRoleBiz.queryById(roleId);
			//选中的值
			String selectValue = request.getParameter("selectedIds");
			if(!StringHelper.isNullOrEmpty(selectValue))
			{
				Object[] obj = authorityBiz.getUserInfoListByRoleId(roleId);
				List<UserInfoModel> list0 = (List<UserInfoModel>) obj[0];//已选
				
				//用户编号字符串,以逗号隔开
				long[] selectedIds = (long[]) ConvertUtils.convert(selectValue.split(","),long.class);
				
				//已存的信息的集合
				List<UserInfoModel> existList = new ArrayList<UserInfoModel>();
				//增加信息的集合
				List<UserInfoModel> addList = new ArrayList<UserInfoModel>();
				//删除信息的集合
				List<UserInfoModel> deleteList = new ArrayList<UserInfoModel>();
				
				boolean isok = false;
				UserInfoModel newUserInfo = null;
				//遍历前台要保存的功能信息，如果存在数据库，则保存在exitList集合
				//如果没有存在数据库，则保存在addList
				for (Long item : selectedIds) {
					isok = false;//是否存在，true表示存在，false不存在
					for (UserInfoModel userInfo : list0) {
						if(userInfo.getUserId() == item)
						{
							isok = true;
							//已存在的信息
							existList.add(userInfo);
							break;
						}
					}
					if(!isok)//不存在的情况
					{
						newUserInfo = new UserInfoModel();
						newUserInfo.setUserId(item);
						//保存到addList
						addList.add(newUserInfo);
					}
				}
				//判断exitList(已存在信息)和list(数据库中的信息)这两个集合，哪些信息是要删除的
				for (UserInfoModel item : list0) {
					isok = false;//是否存在，true表示存在，false不存在
					for (UserInfoModel userInfo : existList) {
						if(userInfo.getUserId().equals(item.getUserId()))
						{
							isok = true;
							break;
						}
					}
					if(!isok)//不存在
					{
						deleteList.add(item);
					}
				}
				authorityBiz.updateUserRole(model, addList, deleteList);
				log2Info(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg,"编号:" + model.getRoleId()+",角色名称:" + model.getRoleName());
			}
			map = LayAjaxHelper.success(LayAjaxHelper.MEG_SUCCESS);
		} catch (Exception ex) {
			log2Error(request, Module_Name, LogDictionary.Module_Oper_userRoleCfg,ex.getMessage());
			//异常信息
			map = LayAjaxHelper.fail(LayAjaxHelper.MEG_FAIL + ","
					+ ex.getMessage());
		}
		return map;
	}
}
