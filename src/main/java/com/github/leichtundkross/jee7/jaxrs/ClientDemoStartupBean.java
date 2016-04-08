package com.github.leichtundkross.jee7.jaxrs;

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
public class ClientDemoStartupBean {

	@Resource
	private TimerService timerService;

	@Inject
	private CustomerClient client;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void getCustomer() {
		client.getCustomer("Google");
	}
}
