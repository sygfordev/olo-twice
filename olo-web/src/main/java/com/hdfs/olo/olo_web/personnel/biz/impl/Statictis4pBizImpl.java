package com.hdfs.olo.olo_web.personnel.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfs.olo.olo_web.personnel.biz.IStatistics4pBiz;
import com.hdfs.olo.olo_web.personnel.mapper.Statistics4pMapper;
import com.hdfs.olo.olo_web.plugins.annotaion.DataSource;

@DataSource(value="personnel")
@Service("statistics4pBiz")
public class Statictis4pBizImpl implements IStatistics4pBiz {

	@Autowired
	private Statistics4pMapper statistics4pMapper;
	
	@Override
	public List<Map<String,Object>> s4organization() throws Exception {
		List<Map<String,Object>> total = statistics4pMapper.s4orgTotal();
		List<Map<String,Object>> branch = statistics4pMapper.s4orgBranch();
		List<Map<String,Object>> unbran = statistics4pMapper.s4orgBranchUnKnown();
		
		List<Map<String,Object>> tbranch = new ArrayList<Map<String,Object>>(branch);
		Integer hbhNo = null;
		List<Map<String,Object>> departs = null;
		for(Map<String,Object> item:branch)
		{
			hbhNo = (Integer)item.get("机构编号");
			if(null == hbhNo) continue;
			item.put("doStyle", true);
			departs = statistics4pMapper.s4orgDepart(hbhNo);
			tbranch.addAll(tbranch.indexOf(item)+1, departs);
		}
		total.get(0).put("doStyle", true);
		unbran.get(0).put("doStyle", true);
		total.addAll(1, tbranch);
		total.addAll(total.size(), unbran);
		//设置序号
		for(int i=0;i<total.size();i++)
		{
			total.get(i).put("序号", i+1);
		}
		return total;
	}

}
