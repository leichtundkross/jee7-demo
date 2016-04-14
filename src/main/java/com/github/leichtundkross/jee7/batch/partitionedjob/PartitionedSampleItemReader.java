package com.github.leichtundkross.jee7.batch.partitionedjob;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.batch.api.BatchProperty;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PartitionedSampleItemReader extends AbstractItemReader {

	/* Remember: Injection only works using @Named annotation */
	@Inject
	private PartitionableSource source;

	@Inject
	@BatchProperty(name = "partition")
	private int partition;

	private Iterator<Integer> iter;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		// filter source stream to partition relevant values
		Stream<Integer> stream = source.createStream();
		iter = stream.filter(i -> i >= ((partition - 1) * 10)) //
				.filter(i -> i < partition * 10) //
				.collect(Collectors.toList()).iterator();
	}

	@Override
	public String readItem() {
		if (iter.hasNext()) {
			return "item " + iter.next();
		}

		return null;
	}
}