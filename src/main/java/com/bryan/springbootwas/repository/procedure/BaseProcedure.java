package com.bryan.springbootwas.repository.procedure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.Map;

public abstract class BaseProcedure {

    private SimpleJdbcCall simpleJdbcCall;

    public BaseProcedure(final JdbcTemplate jdbcTemplate) {
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        init();
    }

    public SimpleJdbcCall getSimpleJdbcCall() {
        return simpleJdbcCall;
    }

    protected abstract void init();

    public Map<String, Object> execute(final Map<String, Object> param) {
        return getSimpleJdbcCall().execute(param);
    }
}
