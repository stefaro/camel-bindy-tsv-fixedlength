package com.stefan.test.fixedlengthtest;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Resources;

public class TestCamelRoute extends CamelTestSupport {

	@Produce(uri = "direct:start")
	private ProducerTemplate start;

	@EndpointInject(uri = "mock:result")
	private MockEndpoint result;

	@Before
	public void prepareRoute() throws Exception {
		context.getRouteDefinition("data-route").adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct:start");
				weaveAddLast().to("mock:result");
			}
		});
	}

	@Override
	protected RoutesBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				includeRoutes(new MyRouteBuilder());
			}
		};
	}

	@Test
	public void testCsvData() throws IOException, InterruptedException {
		// read test data from resource file
		String data = Resources.toString(Resources.getResource("test.data"), Charset.defaultCharset());
		// send data to route
		start.sendBody(data);

		result.setExpectedCount(1);
		result.assertIsSatisfied();
	}

}
