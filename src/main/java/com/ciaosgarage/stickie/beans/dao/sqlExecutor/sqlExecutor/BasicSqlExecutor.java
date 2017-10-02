package com.ciaosgarage.stickie.beans.dao.sqlExecutor.sqlExecutor;

import com.ciaosgarage.stickie.beans.dao.sqlExecutor.resultSetTranslator.ResultSetTranslator;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class BasicSqlExecutor implements SqlExecutor {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ResultSetTranslator resultSetTranslator;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    public void setResultSetTranslator(ResultSetTranslator resultSetTranslator) {
        this.resultSetTranslator = resultSetTranslator;
    }

    public int update(String sql, Map<String, Object> parameters) {
        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public ValueObject queryForObject(Class voInfo, String sql, Map<String, Object> parameters) {

        return jdbcTemplate.queryForObject(sql, parameters, (resultSet, i) -> resultSetTranslator.translator(resultSet, voInfo));
    }

    @Override
    public List<ValueObject> query(Class voInfo, String sql, Map<String, Object> parameters) {
        return jdbcTemplate.query(sql, parameters, (resultSet, i) -> resultSetTranslator.translator(resultSet, voInfo));
    }

    @Override
    public int count(String tableName) {
        String sql = "SELECT COUNT(*) FROM :tableName";
        Map<String, Object> mapper = new HashMap<>();
        mapper.put("tableName", tableName);

        return jdbcTemplate.queryForObject(sql, mapper, (resultSet, i) -> resultSet.getInt(1));
    }
}
