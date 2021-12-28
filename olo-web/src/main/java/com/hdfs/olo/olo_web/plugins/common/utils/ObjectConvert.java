package com.hdfs.olo.olo_web.plugins.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

public class ObjectConvert {
	
	/**
	 * 將JSONObject转为Map对象
	 * @param obj
	 * @return
	 */
	public static Map<String,Object> cvtJSON2Map(JSONObject obj)
	{
		if(null == obj) return null;
		if(obj.isEmpty()) return new HashMap<String,Object>();
		Map<String, Object> data =new HashMap<>();
		//循环转换
		Iterator it =obj.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) it.next();
		    data.put(entry.getKey(), entry.getValue());
		}
		return data;
	}
}
