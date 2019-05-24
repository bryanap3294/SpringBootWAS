package com.bryan.springbootwas.repository;

import com.bryan.springbootwas.model.Example;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleJDBCTemplateRepository {

    List<Example> findAll();
}
