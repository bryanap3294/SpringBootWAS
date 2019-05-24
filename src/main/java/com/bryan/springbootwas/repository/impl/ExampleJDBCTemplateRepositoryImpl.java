package com.bryan.springbootwas.repository.impl;

import com.bryan.springbootwas.model.Example;
import com.bryan.springbootwas.repository.ExampleJDBCTemplateRepository;
import com.bryan.springbootwas.repository.procedure.FindAllExampleProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExampleJDBCTemplateRepositoryImpl implements ExampleJDBCTemplateRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleJDBCTemplateRepositoryImpl.class);

    @Qualifier("exampleJdbcTemplate")
    @Autowired
    private JdbcTemplate exampleJdbcTemplate;

    private FindAllExampleProcedure findAllExampleProcedure;

    @PostConstruct
    private void setUp() {
        findAllExampleProcedure = new FindAllExampleProcedure(exampleJdbcTemplate);
    }

    @Override
    public List<Example> findAll() {

        Map<String, Object> procedureParam = new HashMap<>();
        Map<String, Object> procedureResult = findAllExampleProcedure.execute(procedureParam);

        List<Example> exampleList = (List<Example>)procedureResult.get(FindAllExampleProcedure.OUT_EXAMPLE);

        return exampleList;
    }
}
