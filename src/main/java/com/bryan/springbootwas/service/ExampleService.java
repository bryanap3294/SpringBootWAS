package com.bryan.springbootwas.service;

import com.bryan.springbootwas.model.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExampleService {

    public List<Example> findAll();
}
