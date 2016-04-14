package com.github.leichtundkross.jee7.batch.partitionedjob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 * This is a mock for a batch source source. Using streams we do not need to create the source values again and again.
 */
@Singleton
public class PartitionableSource {

	private Collection<Integer> values;

	@PostConstruct
	public void init() {
		values = new ArrayList<>(100);
		for (int i = 1; i <= 100; i++) {
			values.add(i);
		}
	}

	public Stream<Integer> createStream() {
		Supplier<Stream<Integer>> supplier = () -> values.stream();
		return supplier.get();
	}
}
