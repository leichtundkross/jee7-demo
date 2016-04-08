package com.github.leichtundkross.jee7.cdi.unmanaged;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class UnmanagedDemoStartupBean {

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void createAndUseUnmanagedObject() {
		new UnmanagedBean().sayHello();
	}
}
