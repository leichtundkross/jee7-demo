package com.github.leichtundkross.jee7.batch.partitionedjob;

import java.util.Properties;

import javax.batch.api.partition.PartitionMapper;
import javax.batch.api.partition.PartitionPlan;
import javax.batch.api.partition.PartitionPlanImpl;

public class SamplePartitionMapper implements PartitionMapper {

	/**
	 * A partition per digit (0..9)
	 */
	private static final int PARTITIONS = 10;

	private static final int THREADS = 1;

	@Override
	public PartitionPlan mapPartitions() throws Exception {
		PartitionPlan partitionPlan = new PartitionPlanImpl();
		partitionPlan.setPartitions(PARTITIONS);
		partitionPlan.setThreads(THREADS);
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