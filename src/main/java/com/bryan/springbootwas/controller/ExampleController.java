package com.bryan.springbootwas.controller;

import com.bryan.springbootwas.model.Example;
import com.bryan.springbootwas.service.ExampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/example")
public class ExampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    protected ExampleService exampleService;

    @GetMapping
    public String sayHelloWorld(){
        return "Hello World";
    }

    @GetMapping("/")
    public ResponseEntity<List<Example>> findAll(){

        List<Example> exampleList = this.exampleService.findAll();

        return new ResponseEntity<>(exampleList, HttpStatus.OK);
    }
}
