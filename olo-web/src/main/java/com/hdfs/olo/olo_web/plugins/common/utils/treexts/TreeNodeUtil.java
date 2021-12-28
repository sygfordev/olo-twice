package com.hdfs.olo.olo_web.plugins.common.utils.treexts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;

public class TreeNodeUtil<T> {
	
	public Long rootId;
	public String idKey;
	public String pidKey;
	public String tIdKey;
	public String tPidKey;
	public String subKey;
	public Map<String,String> keyMaps;
	public String checkedKey;
	public List<Long> checkedIds;
	
	private static List<Long> _1LevelIds = null;
	private static List<Long> _2LevelIds = null;
	
	public TreeNodeUtil(CvtConfig conf)
	{
		this.rootId = (null == conf || null == conf.rootId)?0l:conf.rootId;
		this.idKey = (null == conf || StringHelper.isNullOrEmpty(conf.idKey))?"id":conf.idKey;
		this.pidKey = (null == conf || StringHelper.isNullOrEmpty(conf.pidKey))?"pid":conf.pidKey;
		this.subKey = (null == conf || StringHelper.isNullOrEmpty(conf.subKey))?"children":conf.subKey;
		this.keyMaps = (null == conf || null == conf.keyMaps || conf.keyMaps.isEmpty())
				?new HashMap<String,String>():conf.keyMaps;
		this.checkedKey = (null == conf || StringHelper.isNullOrEmpty(conf.checkedKey))?"checked":conf.checkedKey;
		this.checkedIds = (null == conf || null == conf.checkedIds)?new ArrayList<Long>():conf.checkedIds;
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject merge(List<?> list) throws Exception{
		JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
		TreeNodeUtil.TreeNodeManager manager = this.new TreeNodeManager(array);
		_1LevelIds = new ArrayList<Long>();
		_2LevelIds = new ArrayList<Long>();
		Long id = null;
		Long pId = null;
		JSONObject item = null;
		for (int i=0;i<array.size();i++) {
			item = array.getJSONObject(i);
			for(String key:keyMaps.keySet())
			{
				item.put(keyMaps.get(key), item.get(key));
			}
			pId = item.getLong(this.pidKey);
			id = item.getLong(this.idKey);
			if(checkedIds.size()>0 && this.checkedIds.contains(id))
				item.put(this.checkedKey, true);
			if(rootId.equals(pId))
			{
				_1LevelIds.add(id);
				continue;
			}
			if(_1LevelIds.contains(pId) &&
					!_2LevelIds.contains(id))
				_2LevelIds.add(id);
			if(128 == pId)
				System.out.println(1111);
			JSONObject t = manager.getTreeNode(pId);
			if(null == t) 
				continue;
			if(!t.containsKey(this.subKey))
				t.put(this.subKey, new JSONArray());
			t.put("spread",(_1LevelIds.contains(id) || _2LevelIds.contains(id)
					|| _2LevelIds.contains(pId))?true:false);
			t.getJSONArray(this.subKey).add(item);
		}
		JSONObject root = manager.getRoot();
		if(null == root || root.isEmpty())
			throw new Exception("根目录不存在或存在多个！");
		return root;
	}
	
	class TreeNodeManager {
		private JSONArray array;
		public TreeNodeManager(JSONArray array) {
			this.array = array;
		}
		public JSONObject getTreeNode(Long id) {
			JSONObject item = null;
			for (int i=0;i<array.size();i++) {
				item = array.getJSONObject(i);
				Long curId = item.getLong(TreeNodeUtil.this.idKey);
				if (String.valueOf(id).equals(curId+""))
					return item;
			}
			return null;
		}
		public JSONObject getRoot() {
			Long pid = null;
			JSONObject item = null;
			for (int i=0;i<array.size();i++) {
				item = array.getJSONObject(i);
				pid = item.getLong(TreeNodeUtil.this.pidKey);
				if (rootId == pid)
					return item;
			}
			return null;
		}
	}
	@SuppressWarnings("rawtypes")
	public static class CvtConfig {
		private Long rootId;
		private String idKey;
		private String pidKey;
		private String subKey;
		private Map<String,String> keyMaps;
		public String checkedKey;
		public List<Long> checkedIds;
		
		public CvtConfig(){}
		@SuppressWarnings("unchecked")
		public CvtConfig(TreeNodeUtil util){
			this.rootId = util.rootId;
			this.idKey = util.idKey;
			this.pidKey = util.pidKey;
			this.subKey = util.subKey;
			this.keyMaps = util.keyMaps;
			this.checkedKey = util.checkedKey;
			this.checkedIds = util.checkedIds;
		}
		
		public CvtConfig pidKey(String pidKey) {
			this.pidKey = pidKey;
			return this;
		}
		public CvtConfig idKey(String idKey) {
			this.idKey = idKey;
			return this;
		}
		public CvtConfig subKey(String subKey) {
			this.subKey = subKey;
			return this;
		}
		public CvtConfig rootId(Long rootId) {
			this.rootId = rootId;
			return this;
		}
		public CvtConfig keyMaps(Map<String,String> keyMaps) {
			this.keyMaps = keyMaps;
			return this;
		}
		public CvtConfig checkedKey(String checkedKey) {
			this.checkedKey = checkedKey;
			return this;
		}
		public CvtConfig checkedIds(List<Long> checkedIds) {
			this.checkedIds = checkedIds;
			return this;
		}
		public TreeNodeUtil build()
		{
			return new TreeNodeUtil(this);
		}
	}
}

