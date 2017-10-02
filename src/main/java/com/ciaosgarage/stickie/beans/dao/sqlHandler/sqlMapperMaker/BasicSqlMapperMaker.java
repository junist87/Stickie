package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMapperMaker;

import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BasicSqlMapperMaker implements SqlMapperMaker {

    @Override
    public Map<String, Object> makeMapper(ValueObject vo, AttachStatement... statements) {
        Map<String, Object> mapper = new HashMap<>();
        mapper.putAll(getMapperToVo(vo));
        mapper.putAll(getMapperToAttachStatements(statements));
        return mapper;
    }

    @Override
    public Map<String, Object> makeMapper(AttachStatement... statements) {
        Map<String, Object> mapper = new HashMap<>();
        mapper.putAll(getMapperToAttachStatements(statements));
        return mapper;
    }

    private Map<String, Object> getMapperToAttachStatements(AttachStatement... statements) {
        Map<String, Object> mapper = new HashMap<>();
        for (AttachStatement statement : statements) {
            mapper.putAll(statement.getMapper());
        }
        return mapper;
    }

    private Map<String, Object> getMapperToVo(ValueObject vo) {
        Map<String, Object> mapper = new HashMap<>();
        for (Field field : vo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                mapper.put(field.getName(), field.get(vo));
            } catch (IllegalAccessException e) {
            }
        }

        return mapper;
    }

}
