package com.github.leichtundkross.jee7.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedThreadFactory;

@Singleton
public class MyThreadPool {

	private static final int MIN_POOL_SIZE = 5;

	private static final int MAX_POOL_SIZE = 10;

	private static final long KEEP_ALIVE_TIME = 5000;

	private ExecutorService threadPoolExecutor = null;

	@Resource
	private ManagedThreadFactory factory;

	@PostConstruct
	public void init() {
		threadPoolExecutor = new ThreadPoolExecutor(MIN_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
				factory);
	}

	@PreDestroy
	public void releaseResources() {
		threadPoolExecutor.shutdown();
	}

	public void submit(Runnable command) {
		threadPoolExecutor.execute(command);
	}
}
