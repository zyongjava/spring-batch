package cn.yong.zheng.batch.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.yong.zheng.batch.entity.BlackListDO;
import cn.yong.zheng.batch.service.BlackListService;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Autowired
	private BlackListService blackListService;
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("=============JOB FINISHED! Time to verify the results===============");
            List<BlackListDO> results = blackListService.queryAll();
			for (BlackListDO BlackListDO : results) {
				logger.info("Found <" + BlackListDO.getUuid() + "> in the database.");
			}
		}
	}
}
