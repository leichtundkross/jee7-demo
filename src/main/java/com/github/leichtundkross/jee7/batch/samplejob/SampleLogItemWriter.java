package com.github.leichtundkross.jee7.batch.samplejob;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

import com.github.leichtundkross.jee7.batch.samplejob.model.Person;

@Named
public class SampleLogItemWriter extends AbstractItemWriter {

	private static final Logger LOGGER = Logger.getLogger(SampleLogItemWriter.class.getCanonicalName());

	@Override
	public void open(Serializable checkpoint) throws Exception {
		super.open(checkpoint);

		LOGGER.info("Writing read persons to log...");
	}

	@Override
	public void writeItems(List<Object> items) throws Exception {
		items.stream() //
				.forEach((i) -> LOGGER.info(((Person) i).getName()));
	}
}
