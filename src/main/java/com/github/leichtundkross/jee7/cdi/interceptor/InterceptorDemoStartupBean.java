package com.github.leichtundkross.jee7.cdi.interceptor;

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
public class InterceptorDemoStartupBean {

	@Resource
	private TimerService timerService;

	@Inject
	private Instance<CDIBean> beanInstance;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void initCDIBean() {
		beanInstance.get();
	}
}
