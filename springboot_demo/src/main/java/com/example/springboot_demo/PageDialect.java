package com.example.springboot_demo;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

public class PageDialect extends AbstractProcessorDialect {
	private static final String DIALECT_NAME = "Page Dialect";

	public PageDialect() {
		super(DIALECT_NAME, "page", StandardDialect.PROCESSOR_PRECEDENCE);
	}

	protected PageDialect(String name, String prefix, int processorPrecedence) {
		super(name, prefix, processorPrecedence);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new PageAttributeTagProcessor(dialectPrefix));
		return processors;
	}

}
