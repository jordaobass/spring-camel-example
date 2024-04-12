package com.jordaobass.camelagreggator.aggregators;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.util.json.JsonObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

public class JsonRestCallsAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        String costumer = newExchange.getIn().getBody(String.class);
        String couch = oldExchange.getIn().getBody(String.class);

        JsonParser parser = JsonParserFactory.getJsonParser();
        JsonObject json = new JsonObject();
        json.put("author", parser.parseMap(couch));
        json.put("books", parser.parseList(costumer));

        newExchange.getIn().setBody(json.toJson());
        return newExchange;
    }
}
