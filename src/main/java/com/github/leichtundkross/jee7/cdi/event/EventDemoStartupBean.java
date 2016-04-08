package com.github.leichtundkross.jee7.cdi.event;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.EventMetadata;
import javax.inject.Inject;

@Singleton
@Startup
public class EventDemoStartupBean {

	private static final Logger LOGGER = Logger.getLogger(EventDemoStartupBean.class.getCanonicalName());

	@Resource
	private TimerService timerService;

	@Inject
	private Event<String> stringEvents;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(5000l, new TimerConfig(null, false));
	}

	@Timeout
	public void fireEvent() {
		stringEvents.fire("this is a CDI event");
	}

	public void observe(@Observes String event, EventMetadata metadata) {
		LOGGER.info("Received event: " + event);
		LOGGER.info("with metadata: " + metadata);
	}
}
