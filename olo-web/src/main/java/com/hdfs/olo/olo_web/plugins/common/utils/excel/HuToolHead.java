package com.hdfs.olo.olo_web.plugins.common.utils.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.plugins.common.utils.ObjectConvert;

public class HuToolHead {
	//表头标题
	private String hTitle;
	//表头签名信息
	private Map<String,Object> sign;
	//表头类型
	private HeadType type;
	//表头项
	private List<HuTHeadItem> heads;
	
	public HeadType getType() {
		return type;
	}
	public void setType(HeadType type) {
		this.type = type;
	}
	public List<HuTHeadItem> getHeads() {
		return heads;
	}
	public void setHeads(List<HuTHeadItem> heads) {
		this.heads = heads;
	}
	public String gethTitle() {
		return hTitle;
	}
	public void sethTitle(String hTitle) {
		this.hTitle = hTitle;
	}
	public Map<String, Object> getSign() {
		return sign;
	}
	public void setSign(Map<String, Object> sign) {
		this.sign = sign;
	}
	
	enum HeadType{
		TOP
	}
	
	public static HuToolHead cvtJSON4HuToolHead(JSONObject headInfo)
	{
		if(null == headInfo || headInfo.size()<=0) 
			return null;
		HuToolHead huToolHead = null;
        try {
            JSONArray array = headInfo.getJSONArray("heads");    //如果json格式的字符串里含有数组格式的属性，将其转换成JSONArray，以方便后面转换成对应的实体
            List<HuTHeadItem> items = new ArrayList<HuTHeadItem>();
            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                HuTHeadItem item = JSON.toJavaObject(object, HuTHeadItem.class);
                items.add(item);
            }
            String hTitle = headInfo.getString("hTitle");  //简单的直接获取值
            huToolHead = new HuToolHead();                 //对SearchFilter对象进行组装
            huToolHead.sethTitle(hTitle);
            JSONObject sign = headInfo.getJSONObject("sign");
            huToolHead.setSign(ObjectConvert.cvtJSON2Map(sign));
            huToolHead.setHeads(items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return huToolHead;
	}
	
	public static void main(String args[])
	{
		String tmp = "{'hTitle':'你好','heads':[{'field':'name','title':'姓名','rowS':0,'rowE':0,'colS':0,'colE':1,'subs':[]},{'field':'idcard','title':'身份证号','rowS':0,'rowE':0,'colS':0,'colE':1,'subs':[]},{'field':'group','title':'分组','rowS':0,'rowE':0,'colS':0,'colE':1,'subs':[{'field':'g1','title':'组1','rowS':0,'rowE':0,'colS':0,'colE':1,'subs':[]},{'field':'g2','title':'组2','rowS':0,'rowE':0,'colS':0,'colE':1,'subs':[]}]}]}";
		JSONObject obj = JSONObject.parseObject(tmp);
		System.out.println(obj);
		cvtJSON4HuToolHead(obj);
	}
}
