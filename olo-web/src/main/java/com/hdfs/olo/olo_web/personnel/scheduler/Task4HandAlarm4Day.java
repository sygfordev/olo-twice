package com.hdfs.olo.olo_web.personnel.scheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.central.biz.IAlarmMsgBiz;
import com.hdfs.olo.olo_web.central.biz.IAlarmSetBiz;
import com.hdfs.olo.olo_web.central.model.AlarmMsgModel;
import com.hdfs.olo.olo_web.central.model.AlarmSetModel;
import com.hdfs.olo.olo_web.personnel.biz.IChPmContractInfoBiz;
import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;

/**
 * @Description: TODO
 * @Title:  Task1.java
 * @author: Matthew
 * @date: 2019年3月19日 下午7:30:18
 * @version V1.0
 */
@Component
public class Task4HandAlarm4Day {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IAlarmSetBiz alarmSetBiz;
	@Autowired
	private IAlarmMsgBiz alarmMsgBiz;
	@Autowired
	private IChPmContractInfoBiz chPmContractInfoBiz;
	
	static final Map<String,String> TIMEMAP = new HashMap<String,String>();
	static {
		TIMEMAP.put("0","DAY");
		TIMEMAP.put("1","MONTH");
		TIMEMAP.put("2","YEAR");
	}
	//单位是毫秒
//	@Scheduled(fixedRate = 2000)
	//19:44-19:45之间每个2秒输出下时间
//	@Scheduled(cron = "*/5 * * * * ?")
	@Scheduled(cron = "0 0 1 * * ?")
	public void task1(){
		AlarmSetModel model = new AlarmSetModel();
		model.setAlarmType(0);//合同到期预警
		model.setStatus(DictionaryUtil.KEY_NORMAL);
		List<AlarmSetModel> list = null;
		try {
			list = alarmSetBiz.queryList(model);
		}catch(Exception e){
			logger.error("查询警报配置失败!");
		}
		if(null == list || list.size()<=0) 
		{
			//logger.info("未检测到有合同过期预警配置存在!");
			return;
		}
		
		long count = 0L;
		try {
			count = alarmMsgBiz.queryCount(new AlarmMsgModel());
		} catch (Exception e) {
			logger.error("查询已存在预警消息条数异常! error:",e);
			e.printStackTrace();
			return;
		}
		logger.info("开始执行合同过期警报配置中的策略!");
		List<Map<String,Object>> contracts = null;
		try {
			contracts = chPmContractInfoBiz.queryByContEndTime((count>0?true:false),list.get(0).getAlarmAdSize(), TIMEMAP.get(list.get(0).getAlarmAdUnit()));
		} catch (Exception e) {
			logger.error("查询已到期或即将到期合同时异常! error:",e);
			e.printStackTrace();
		}
		if(null == contracts || contracts.size()<=0)
			return;
		
		AlarmMsgModel msg = null;
		String realAlarmMsg = null;
		List<AlarmMsgModel> msgs = new ArrayList<AlarmMsgModel>();
		for(Map<String,Object> item:contracts)
		{
			msg = new AlarmMsgModel();
			msg.setMsgType(model.getAlarmType());
			msg.setStatus(DictionaryUtil.KEY_NORMAL);
			realAlarmMsg = list.get(0).getAlarmTeInfo().replace("{name}", item.get("NAME")+"").replace("{contractNo}", item.get("CONT_NO")+"")
					.replace("{contEndTime}", item.get("CONT_END_TIME")+"");
			msg.setMsgCont(realAlarmMsg);
			msgs.add(msg);
		}
		try {
			alarmMsgBiz.saveBatch(msgs);
			logger.info("合同到期预警处理成功!");
		} catch (Exception e) {
			logger.error("合同到期预警处理异常! error:",e);
			e.printStackTrace();
		}
	}
}