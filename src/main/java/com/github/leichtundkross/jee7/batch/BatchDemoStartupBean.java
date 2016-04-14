package com.github.leichtundkross.jee7.batch;

import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class BatchDemoStartupBean {

	private static final Logger LOGGER = Logger.getLogger(BatchDemoStartupBean.class.getCanonicalName());

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(7000l, new TimerConfig(null, false));
	}

	@Timeout
	public void startJob() {
		startJob("sampleJob");
		startJob("partitionedJob");
	}

	private void startJob(String jobName) {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long executionId = jobOperator.start(jobName, new Properties());
		LOGGER.info("Started Job '" + jobName + "' with id " + executionId);

		// check status
		try {
			Thread.sleep(1000);
			JobExecution jobExecution = jobOperator.getJobExecution(executionId);
			LOGGER.info("Job Instance has status " + jobExecution.getExitStatus());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
