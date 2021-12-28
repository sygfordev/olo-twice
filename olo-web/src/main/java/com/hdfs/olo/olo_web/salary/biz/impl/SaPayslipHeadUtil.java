package com.hdfs.olo.olo_web.salary.biz.impl;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

public class SaPayslipHeadUtil {

	/**
	 * 加载薪酬统计动态表头
	 * @param months
	 * @return
	 */
	public static JSONArray loadHead4_100(List<String> months)
	{
		int width4s = (months.size()<=0)?3:0;
		int width = months.size()<=0?0:(months.size()<=3?24/months.size():8);
		String tabHeadTmp1 = 
		"[["+
   	  		"{field: '', fixed: 'left',title:'序号',type:'numbers',align: 'center',width:'"+(8+width4s)+"%'},"+
   	  		"{field: 'WK_MODALITY_CN', title: '人员类别' ,fixed: 'left',width:'"+(8+width4s)+"%',align: 'center'}, "+
   	  		"{field: 'DEPART_CLASS_CN', title: '人员部门' ,fixed: 'left',width:'"+(8+width4s)+"%',align: 'center'}, "+
   	  		"{field: 'NAME', title: '名称' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'},"+
   	  		"{field: 'CARD_NO', title: '身份证号' ,fixed: 'left',width:'"+(12+width4s)+"%',align: 'center'},"+
   	  		"{field: 'TOTAL', title: '统计区间工资收入' ,width:'"+(10+width4s)+"%',align: 'center'},"+
   	  		"{field: 'MONTHS', title: '统计月份数' ,width:'"+(10+width4s)+"%',align: 'center'},"+
   	  		"{field: 'AVE_VAL', title: '统计区间月均收入' ,width:'"+(10+width4s)+"%',align: 'center'},";
     
		
		StringBuffer sups = new StringBuffer();
		if(null != months && months.size()>0)  
		{
			String item = null;
			for(int i=0;i<months.size();i++){
				item = months.get(i);
				sups.append("{field: '"+item+"', title: '"+item+"应发工资' ,align: 'center',width:'"+width+"%'},");
			}
		}
		sups.append("]]");
		JSONArray heads = JSONArray.parseArray(tabHeadTmp1+sups.toString());
		return heads;
	}
	
	/**
	 * （全年工资明细汇总）通过年份、用工形式和薪酬奖励项列表统计表头
	 * @param rewards 当年薪资包含的所有奖励项
	 * @param year 统计年份
	 * @return
	 */
	public static JSONArray loadHead4_101(String year,List<String> rewards)
	{
		int width4s = (rewards.size()<=0)?10:0;
		int width = rewards.size()<=0?0:(rewards.size()<=3?60/rewards.size():10);
		
		String tabHeadTmp1 = 
		"[["+
   	  		"{field: 'target_month',title:'月份',fixed: 'left',align: 'center',width:'"+(10+width4s)+"%'},"+
   	  		"{field: 'modality', title: '人员类别' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'},"+
   	  		"{field: 'wage_totals', title: '工资合计' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'},";
     
		StringBuffer sups = new StringBuffer();
		if(null != rewards && rewards.size()>0)  
		{
			String item = null;
			for(int i=0;i<rewards.size();i++){
				item = rewards.get(i);
				sups.append("{field: '"+item+"', title: '"+item+"' ,align: 'center',width:'"+width+"%'},");
			}
		}
		
		String tabheadTmp2 = 
				"{field: 'sub_total', title: '合计',width:'"+(10+width4s)+"%',align: 'center'}"+
				"]]";
		JSONArray heads = JSONArray.parseArray(tabHeadTmp1+sups.toString()+tabheadTmp2);
		return heads;
	}
	
	/**
	 * 加载用工类型月工资汇总表动态表头
	 * @param months
	 * @return
	 */
	public static JSONArray loadHead4_200(String curMonth,List<String> rewards)
	{
		int width4s = (rewards.size()<=0)?10:0;
		int width = rewards.size()<=0?0:(rewards.size()<=3?50/rewards.size():10);
		
		String tabHeadTmp1 = 
		"[["+
   	  		"{field: 'modality', title: '用工形式' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'}, "+
   	  		"{field: 'size', title: '人数' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'}, "+
   	  		"{field: 'wage_totals', title: '基础工资小计' ,fixed: 'left',width:'"+(10+width4s)+"%',align: 'center'},";
   	  		
		
		StringBuffer sups = new StringBuffer();
		if(null != rewards && rewards.size()>0)  
		{
			String item = null;
			for(int i=0;i<rewards.size();i++){
				item = rewards.get(i);
				sups.append("{field: '"+item+"', title: '"+item+"' ,align: 'center',width:'"+width+"%'},");
			}
		}
		
		String tabHeadTmp2 = 
   	  		"{field: 'reward_totals', title: '绩效工资合计' ,width:'"+(10+width4s)+"%',align: 'center'},"+
			"{field: 'payable_totals', title: '应发合计' ,width:'"+(10+width4s)+"%',align: 'center'}"+
		"]]";
		
		JSONArray heads = JSONArray.parseArray(tabHeadTmp1+sups.toString()+tabHeadTmp2);
		return heads;
	}
}
