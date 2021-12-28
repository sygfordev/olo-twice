package com.hdfs.olo.olo_web.social.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hdfs.olo.olo_web.social.biz.IStatistics4SocialBiz;

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
public class Task4StPen {
	private Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private IStatistics4SocialBiz statistics4SocialBiz;
	//单位是毫秒
	//@Scheduled(fixedRate = 2000)
//	@Scheduled(cron = "*/5 * * * * ?")
//	@Scheduled(cron = "0 */1 * * * ?")
	public void cleanToken(){
		try {
			statistics4SocialBiz.load4_100("2021-04", "2021-05");
			logger.info("养老统计!");
		}catch(Exception e)
		{
			e.printStackTrace();
			logger.error("养老统计异常!error:"+e.getMessage());
		}
	}
}