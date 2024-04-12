package com.jordaobass.customerservice.service;

import com.jordaobass.customerservice.domain.Costumer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CostumerService {
    public List<Costumer> all() {
        return Arrays.asList(
                new Costumer("1", "stephen-king", "Stephen King"),
                new Costumer("2", "jk-rowling", "J. K. Rowling"),
                new Costumer("3", "jr-tolkien", "J. R. Tolkien"),
                new Costumer("4", "agatha-christie", "Agatha Christie"),
                new Costumer("5", "jane-austin", "Jane Austin")
        );
    }

    public Costumer byName(String name) {
        return this.all().stream()
                .filter(a -> a.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public Costumer byId(String id) {
        return this.all().stream()
                .filter(a -> a.getId().equals(id))
                .findAny()
                .orElse(null);
    }


}
