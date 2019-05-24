package com.bryan.springbootwas.repository.procedure;

import com.bryan.springbootwas.model.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlReturnResultSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FindAllExampleProcedure extends BaseProcedure{

    private static final Logger LOGGER = LoggerFactory.getLogger(FindAllExampleProcedure.class);

    public static final String PROCEDURE_NAME = "SP_Cardlist";

    public static final String OUT_EXAMPLE = "OUT_EXAMPLE";

    public FindAllExampleProcedure(final JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    protected void init() {
        getSimpleJdbcCall()
                .withProcedureName(PROCEDURE_NAME)
                .declareParameters(new SqlReturnResultSet(OUT_EXAMPLE, new FindAllExampleRowMapper()));
    }

    private class FindAllExampleRowMapper implements RowMapper<Example> {

        @Override
        public Example mapRow(ResultSet resultSet, int i) throws SQLException {

            Example example = new Example();
            example.setId(resultSet.getInt("card_id"));
            example.setDescription(resultSet.getString("card_description"));

            return example;
        }
    }
}
