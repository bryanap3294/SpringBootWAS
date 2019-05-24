package com.bryan.springbootwas.service.impl;

import com.bryan.springbootwas.model.Example;
import com.bryan.springbootwas.repository.ExampleJDBCTemplateRepository;
import com.bryan.springbootwas.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    protected ExampleJDBCTemplateRepository exampleJDBCTemplateRepository;

    @Override
    public List<Example> findAll() {
        return exampleJDBCTemplateRepository.findAll();
    }
}
