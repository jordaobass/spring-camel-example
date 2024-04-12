package com.jordaobass.camelagreggator.routes;

import com.jordaobass.camelagreggator.aggregators.JsonRestCallsAggregator;
import com.jordaobass.camelagreggator.processors.GetCouchIdProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class RestAggregatorRoute extends RouteBuilder{

    private static final int OK_CODE = 200;
    private static final int APP_RESPONSE_CODE = 204;

    @Override
    public void configure() throws Exception {
        from("direct:call-rest-author")
                .routeId("call-rest-services")
                .to("direct:author-service")
                .choice()
                .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(OK_CODE))
                .process(new GetCouchIdProcessor())
                .enrich("direct:books-service", new JsonRestCallsAggregator())
                .otherwise()
                .setHeader(Exchange.HTTP_RESPONSE_CODE).constant(APP_RESPONSE_CODE);

        from("direct:call-rest-all")
                .routeId("all-service")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .to("http://{{couch.url}}/couch");


        from("direct:author-service")
                .routeId("author-service")
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://{{couch.url}}/couch/${header.name}");

        from("direct:books-service")
                .routeId("books-service")
                .onException(Exception.class)
                .handled(true)
                .setBody(constant("[]"))
                .end()
                .removeHeaders("CamelHttp*")
                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
                .toD("http://{{costumer.url}}/books/${header.id}");
    }
}
