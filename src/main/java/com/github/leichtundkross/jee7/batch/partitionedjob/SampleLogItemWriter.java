package com.github.leichtundkross.jee7.batch.partitionedjob;

import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemWriter;

public class SampleLogItemWriter extends AbstractItemWriter {

	private static final Logger LOGGER = Logger.getLogger(SampleLogItemWriter.class.getCanonicalName());

	@Override
	public void writeItems(List<Object> items) throws Exception {
		items.stream() //
				.forEach((item) -> LOGGER.info((String) item));
	}
}
