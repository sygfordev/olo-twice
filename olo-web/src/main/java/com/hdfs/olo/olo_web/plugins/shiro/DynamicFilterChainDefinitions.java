package com.hdfs.olo.olo_web.plugins.shiro;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.IDynamicFilterChainDefinitions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.central.biz.ISystemPrivBiz;
import com.hdfs.olo.olo_web.central.model.SystemPrivModel;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;


/**
 * 	读取数据库，获取shiro所需的permission.
 *	Permission 代表了系统的原子权限 比如数据的修改、删除权限
 * @author huadf
 *
 */
@Component
public class DynamicFilterChainDefinitions implements IDynamicFilterChainDefinitions{
	
	@Autowired
	private ISystemPrivBiz systemPrivBiz;
	
	public Map<String, String> dynamicMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		//加载所有系统权限
		List<SystemPrivModel> list = systemPrivBiz.queryList(new SystemPrivModel());
		
		//过滤可用权限信息
		String method = null;
		String permissioin = null;
		for (SystemPrivModel item : list) {
			method = item.getPrivMethod();
			permissioin = item.getPrivPermissioin();
			if(StringHelper.isNullOrEmpty(method) || 
					StringHelper.isNullOrEmpty(permissioin))
			continue;
			method = method.trim();
			permissioin = permissioin.trim();
			if(permissioin.indexOf(":") > 0)
			{
				map.put(method,"perms["+permissioin+"]");
			}else
			{
				map.put(method,permissioin);
			}
		}
		return map;
	}
}
