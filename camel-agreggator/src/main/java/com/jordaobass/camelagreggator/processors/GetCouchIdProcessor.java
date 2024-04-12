package com.jordaobass.camelagreggator.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class GetCouchIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String couch = exchange.getIn().getBody(String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = parser.parseMap(couch);
        String couchId = (String) jsonMap.get("id");

        exchange.getIn().setHeader("id", couchId);
        exchange.getIn().setBody(couch);
    }

}
