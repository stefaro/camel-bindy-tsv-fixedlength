package com.stefan.test.fixedlengthtest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.fixed.BindyFixedLengthDataFormat;

import com.stefan.test.fixedlengthtest.model.Order;
import com.stefan.test.fixedlengthtest.processors.ProcessOrderHeader;

/**
 * A Camel Java8 DSL Router
 */
public class MyRouteBuilder extends RouteBuilder {

	public void configure() {

		//@formatter:off
    	from("file:testdir").routeId("data-route")
			.unmarshal(new BindyFixedLengthDataFormat(Order.class))

			.to("log:test?showAll=true")
			
			.log("Loop through data")

			.process(new ProcessOrderHeader())
			
			.log("Processing order lines:")
    		.split(body())
    			.log("LINE: ${body}")
    		.end()
    	
			.log("DATA processed")
		;
    }
}
