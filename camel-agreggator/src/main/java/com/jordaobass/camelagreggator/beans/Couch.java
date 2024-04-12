package com.jordaobass.camelagreggator.beans;


import java.util.Map;

import org.apache.camel.Exchange;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Component;

@Component
public class Couch {

    public void getId(Exchange exchange) {
        String couch = exchange.getIn().getBody(String.class);
        JsonParser parser = JsonParserFactory.getJsonParser();
        Map<String, Object> jsonMap = parser.parseMap(couch);
        String couchId = (String) jsonMap.get("id");

        exchange.getIn().setHeader("id", couchId);
        exchange.getIn().setBody(couch);
    }
}
