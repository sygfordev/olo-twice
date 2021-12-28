package com.hdfs.olo.olo_web.plugins.common.utils.treexts;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.central.model.BranchOfficeModel;

public class Tree4BranchUtil {

	private static List<Long> _1LevelIds = new ArrayList<Long>();
	private static List<Long> _2LevelIds = new ArrayList<Long>();
	
	private Long rootId;
	public Tree4BranchUtil(Long rootId)
	{
		this.rootId = rootId;
		_1LevelIds.clear();
		_2LevelIds.clear();
	}
	public static JSONObject merge(List<BranchOfficeModel> list,Long rootId){
		Tree4BranchUtil util = new Tree4BranchUtil(rootId);
		Tree4BranchUtil.TreeNodeManager manager = util.new TreeNodeManager(list);
		for (BranchOfficeModel item : list) {
			if(rootId.equals(item.getParentId()))//rootId为-1
			{
				_1LevelIds.add(item.getId());
				continue;
			}
			if(_1LevelIds.contains(item.getParentId()) &&
					!_2LevelIds.contains(item.getId()))
				_2LevelIds.add(item.getId());
			
			JSONObject t = manager.getTreeNode(item.getParentId());
			t.put("spread",(_1LevelIds.contains(item.getId()) || _2LevelIds.contains(item.getId())
					|| _2LevelIds.contains(item.getParentId()))?true:false);
			t.getJSONArray("children").add(manager.getTreeNode(item.getId()));
		}
		
		JSONObject root = manager.getRoot();
		//root.put("disabled", true);
		return root;
	}
	
	class TreeNodeManager {
		private JSONArray list;
		public TreeNodeManager(List<BranchOfficeModel> items) {
			list = new JSONArray();
			String tmp = null;
			for(BranchOfficeModel item:items)
			{
				tmp = JSONObject.toJSONString(item);
				tmp = tmp.replace("branchName", "title");
				JSONObject tmpObj = JSONObject.parseObject(tmp);
				//tmpObj.put("spread",(_1LevelIds.contains(item.getId()) || _2LevelIds.contains(item.getId()))?true:false);
				list.add(tmpObj);
			}
		}
		public JSONObject getTreeNode(Long id) {
			JSONObject item = null;
			for (int i=0;i<list.size();i++) {
				item = list.getJSONObject(i);
				if (id == item.getLong("id"))
					return item;
			}
			return null;
		}
		public JSONObject getRoot() {
			JSONObject item = null;
			for (int i=0;i<list.size();i++) {
				item = list.getJSONObject(i);
				if (rootId == item.getLong("parentId"))
					return item;
			}
			return null;
		}
	}
}

