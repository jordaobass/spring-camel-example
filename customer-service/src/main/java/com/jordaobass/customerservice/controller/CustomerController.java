package com.jordaobass.customerservice.controller;


import com.jordaobass.customerservice.domain.Costumer;
import com.jordaobass.customerservice.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/books")
public class CustomerController {


    @Autowired
    private CostumerService service;

    @GetMapping
    public ResponseEntity<List<Costumer>> all() {
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Costumer> byName(@PathVariable("name") String name) {
        Costumer costumer = service.byName(name);
        if(Objects.isNull(costumer))
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(costumer, HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    public ResponseEntity<Costumer> byId(@PathVariable("id") String id) {
        Costumer costumer = service.byId(id);
        if(Objects.isNull(costumer))
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(costumer, HttpStatus.OK);
    }
}
