package com.hdfs.olo.olo_web.salary.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.salary.biz.IChSaWechatTokenBiz;

/**
 * 处理微信访问token过期时间
 */
// 	每隔5秒执行一次：*/5 * * * * ?
// 	每隔1分钟执行一次：0 */1 * * * ?
// 	每天23点执行一次：0 0 23 * * ?
// 	每天凌晨1点执行一次：0 0 1 * * ?
// 	每月1号凌晨1点执行一次：0 0 1 1 * ?
// 	每月最后一天23点执行一次：0 0 23 L * ?
// 	每周星期天凌晨1点实行一次：0 0 1 ? * L
// 	在26分、29分、33分执行一次：0 26,29,33 * * * ?
// 	每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

@Component
public class Task4HandToken {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IChSaWechatTokenBiz chSaWechatTokenBiz;
	//单位是毫秒
	//@Scheduled(fixedRate = 2000)
//	@Scheduled(cron = "*/5 * * * * ?")
	@Scheduled(cron = "0 */1 * * * ?")
	public void cleanToken(){
		try {
			chSaWechatTokenBiz.delByTime(5, "M");
			logger.info("清除微信访问token(5分钟前)成功!");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("清除微信访问token异常!error:"+e.getMessage());
		}
	}
}