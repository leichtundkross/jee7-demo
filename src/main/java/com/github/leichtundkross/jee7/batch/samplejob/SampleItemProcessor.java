package com.github.leichtundkross.jee7.batch.samplejob;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import com.github.leichtundkross.jee7.batch.samplejob.model.Person;

@Named
public class SampleItemProcessor implements ItemProcessor {

	@Override
	public Person processItem(Object item) {
		String name = (String) item;
		return new Person(name);
	}
}
