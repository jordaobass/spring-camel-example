package com.jordaobass.couchservice.service;

import com.jordaobass.couchservice.domain.Couch;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CouchService {


    public List<Couch> all() {
        return Arrays.asList(
                new Couch("1", "stephen-king", "Stephen King"),
                new Couch("2", "jk-rowling", "J. K. Rowling"),
                new Couch("3", "jr-tolkien", "J. R. Tolkien"),
                new Couch("4", "agatha-christie", "Agatha Christie"),
                new Couch("5", "jane-austin", "Jane Austin")
        );
    }

    public Couch byName(String name) {
        return this.all().stream()
                .filter(a -> a.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public Couch byId(String id) {
        return this.all().stream()
                .filter(a -> a.getId().equals(id))
                .findAny()
                .orElse(null);
    }


}
