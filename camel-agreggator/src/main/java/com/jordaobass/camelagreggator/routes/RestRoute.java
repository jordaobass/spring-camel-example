package com.jordaobass.camelagreggator.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().host("0.0.0.0").port(8080).bindingMode(RestBindingMode.auto);

        rest("/integration")
                .get("/couch")
                .route().routeId("rest-all-couch")
                .to("direct:call-rest-all")
                .endRest()
                .get("/couch/{name}")
                .route().routeId("rest-author-by-name")
                .to("direct:call-rest-author")
                .endRest();
    }
}
