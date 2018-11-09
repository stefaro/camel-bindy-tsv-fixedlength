package com.stefan.test.fixedlengthtest.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stefan.test.fixedlengthtest.model.OrderHeader;

public class ProcessOrderHeader implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(ProcessOrderHeader.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, OrderHeader> headermap = exchange.getIn()
				.getHeader(BindyFixedLengthDataFormat.CAMEL_BINDY_FIXED_LENGTH_HEADER, Map.class);

		OrderHeader header = headermap.get(OrderHeader.class.getName());

		logger.info("Processing order: {}", header.getId());
		logger.info("...date1: {}", header.getDate1());
		logger.info("...date2: {}", header.getDate2());
		logger.info("...numberOfFile: {}", header.getNumerOfFile());
	}
}