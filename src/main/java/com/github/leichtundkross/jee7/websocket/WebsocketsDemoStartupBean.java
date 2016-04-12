package com.github.leichtundkross.jee7.websocket;

import java.io.IOException;
import java.net.URI;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

@Singleton
@Startup
public class WebsocketsDemoStartupBean {

	@Resource
	private TimerService timerService;

	@PostConstruct
	public void delayInit() {
		timerService.createSingleActionTimer(20000l, new TimerConfig(null, false));
	}

	@Timeout
	public void registerClient() {
		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			String uri = "ws://localhost:8080/jee7-demo/chat";
			container.connectToServer(ChatClientEndpoint.class, URI.create(uri));
		} catch (DeploymentException | IOException e) {
			e.printStackTrace();
		}
	}
}
