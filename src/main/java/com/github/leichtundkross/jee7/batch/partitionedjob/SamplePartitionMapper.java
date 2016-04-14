package com.github.leichtundkross.jee7.batch.partitionedjob;

import java.util.Properties;

import javax.batch.api.BatchProperty;
import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;
import javax.inject.Inject;

public class SamplePartitionMapper implements PartitionMapper {

	/**
	 * A partition per digit (0..9)
	 */
	private static final int PARTITIONS = 10;

	/**
	 * Default is number of threads = number of partitions. However, e want to allow a maximum of n threads parallel.
	 */
	@Inject
	@BatchProperty(name = "threads")
	private int threads;

	@Override
	public PartitionPlan mapPartitions() throws Exception {
		PartitionPlan partitionPlan = new PartitionPlanImpl();
		partitionPlan.setPartitions(PARTITIONS);
		partitionPlan.setThreads(threads);
		Properties[] propertiesArray = new Properties[PARTITIONS];
		for (int i = 0; i < PARTITIONS; i++) {
			Properties properties = new Properties();
			// property value must be a string
			properties.put("partition", "" + (i + 1));
			propertiesArray[i] = properties;
		}
		partitionPlan.setPartitionProperties(propertiesArray);
		return partitionPlan;
	}
}