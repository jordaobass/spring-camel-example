package com.jordaobass.couchservice.controller;

import com.jordaobass.couchservice.domain.Couch;
import com.jordaobass.couchservice.service.CouchService;
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
@RequestMapping("/couch")
public class CouchController {


    @Autowired
    private CouchService service;

    @GetMapping
    public ResponseEntity<List<Couch>> all() {
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Couch> byName(@PathVariable("name") String name) {
        Couch couch = service.byName(name);
        if(Objects.isNull(couch))
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(couch, HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    public ResponseEntity<Couch> byId(@PathVariable("id") String id) {
        Couch couch = service.byId(id);
        if(Objects.isNull(couch))
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(couch, HttpStatus.OK);
    }
}
