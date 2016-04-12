package com.github.leichtundkross.jee7.concurrency;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

@Singleton
@Startup
public class CuncurrencyDemoStartupBean {

	private static final Logger LOGGER = Logger.getLogger(CuncurrencyDemoStartupBean.class.getCanonicalName());

	@Resource
	private TimerService timerService;

	@Inject
	private MyThreadPool threadPool;

	@Inject
	private AsynchronousEJB asyncEJB;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void executeJob() {
		threadPool.submit(() -> LOGGER.info("I am running in a managed pool :-)"));

		asyncEJB.synchronousMethod();
	}
}
