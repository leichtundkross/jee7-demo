package com.github.leichtundkross.jee7.batch.samplejob;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

@Named("SampleItemReader")
public class SampleItemReader extends AbstractItemReader {

	private Iterator<String> iter;

	@Override
	public void open(Serializable checkpoint) throws Exception {
		iter = Arrays.asList("Bob", "Kevin", "Stuart").iterator();
	}

	@Override
	public String readItem() {
		if (iter.hasNext()) {
			return iter.next();
		}

		return null;
	}
}