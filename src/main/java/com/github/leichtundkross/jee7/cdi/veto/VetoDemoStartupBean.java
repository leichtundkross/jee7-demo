package com.github.leichtundkross.jee7.cdi.veto;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@Singleton
@Startup
public class VetoDemoStartupBean {

	private static final Logger LOGGER = Logger.getLogger(VetoDemoStartupBean.class.getCanonicalName());

	@Resource
	private TimerService timerService;

	@Inject
	private Instance<MyInterface> instances;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void logAllBeans() {
		for (MyInterface impl : instances) {
			LOGGER.info("Found implementation for MyInterface: " + impl.getClass().getSimpleName());
		}
	}
}
