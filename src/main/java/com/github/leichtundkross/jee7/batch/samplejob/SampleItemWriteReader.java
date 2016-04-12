package com.github.leichtundkross.jee7.batch.samplejob;

import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.listener.AbstractItemWriteListener;
import javax.inject.Named;

@Named
public class SampleItemWriteReader extends AbstractItemWriteListener {

	private static final Logger LOGGER = Logger.getLogger(SampleItemWriteReader.class.getCanonicalName());

	@Override
	public void afterWrite(List<Object> items) throws Exception {
		LOGGER.info("Items written: " + items);
	}
}
