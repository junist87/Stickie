package com.ciaosgarage.stickie.parameters.attachStatement.stateOrderBy;

import com.ciaosgarage.stickie.parameters.attachStatement.ATStatus;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;

import java.util.HashMap;
import java.util.Map;

public class StateOrderBy implements AttachStatement {
    private SOBOperator operator;
    private String columnName;

    public StateOrderBy(SOBOperator operator, String columnName) {
        this.operator = operator;
        this.columnName = columnName;

    }

    @Override
    public String getStatement() {
        String sql = "ORDER BY " + columnName + " " + getOperator();
        return sql;
    }

    private String getOperator() {
        switch (operator) {
            case ASC:
                return "ASC";
            case DESC:
                return "DESC";
            default:
                return null;
        }
    }

    @Override
    public Map<String, Object> getMapper() {
        return new HashMap<>();
    }

    @Override
    public ATStatus getType() {
        return ATStatus.VARIABLE;
    }
}
