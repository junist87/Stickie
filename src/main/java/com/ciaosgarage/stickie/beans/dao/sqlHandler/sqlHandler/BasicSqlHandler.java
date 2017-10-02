package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler;

import com.ciaosgarage.stickie.parameters.attachStatement.ATStatus;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage.SqlIndex;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage.SqlStorage;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BasicSqlHandler implements SqlHandler {

    @Autowired
    private SqlStorage sqlStorage;

    @Autowired
    private SqlMapperMaker sqlMapperMaker;

    @Autowired
    private SqlMaker sqlMaker;

    public void setSqlMaker(SqlMaker sqlMaker) {
        this.sqlMaker = sqlMaker;
    }

    public void setSqlStorage(SqlStorage sqlStorage) {
        this.sqlStorage = sqlStorage;
    }

    public void setSqlMapperMaker(SqlMapperMaker sqlMapperMaker) {
        this.sqlMapperMaker = sqlMapperMaker;
    }

    @Override
    public SqlProduct getSqlProduct(ValueObject vo, SqlType type, AttachStatement... statements) {
        String sql;
        SqlIndex index = new SqlIndex(vo.getClass(), type, getFixedStatementClassSet(statements));
        Map<String, Object> mapper = sqlMapperMaker.makeMapper(vo, statements);

        sql = sqlStorage.getSql(index);

        if (sql == null) {
            sql = getNewSql(vo.getClass(), type, statements);
            sqlStorage.setSql(index, sql);
        }

        sql = this.appendVariableSql(sql, statements);
        return new SqlProduct(sql, mapper);
    }

    @Override
    public SqlProduct getSqlProduct(Class voInfo, SqlType type, AttachStatement... statements) {
        String sql;
        SqlIndex index = new SqlIndex(voInfo, type, getFixedStatementClassSet(statements));
        Map<String, Object> mapper = sqlMapperMaker.makeMapper(statements);

        sql = sqlStorage.getSql(index);
        if (sql == null) {
            sql = getNewSql(voInfo, type, statements);
            sqlStorage.setSql(index, sql);
        }

        sql = this.appendVariableSql(sql, statements);
        return new SqlProduct(sql, mapper);
    }

    private String getNewSql(Class voInfo, SqlType type, AttachStatement... statements) {

        switch (type) {
            case SELECT:
                return sqlMaker.select(voInfo, getFixedStatementList(statements));
            case INSERT:
                return sqlMaker.insert(voInfo);
            case DELETE:
                return sqlMaker.delete(voInfo);
            case UPDATE:
                return sqlMaker.update(voInfo);
            default:
                throw new RuntimeException("!- Can't Make SQL -!");
        }
    }

    private Set<Class> getFixedStatementClassSet(AttachStatement... statements) {
        Set<Class> set = new HashSet<>();
        for (AttachStatement statement : statements) {
            if (!statement.getType().equals(ATStatus.FIXED)) continue;
            set.add(statement.getClass());
        }
        return set;
    }

    private List<AttachStatement> getFixedStatementList(AttachStatement... statements) {
        List<AttachStatement> list = new ArrayList<>();
        for (AttachStatement statement : statements) {
            if (!statement.getType().equals(ATStatus.FIXED)) continue;
            list.add(statement);
        }
        return list;
    }

    private String appendVariableSql(String sql, AttachStatement... statements) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
        for (AttachStatement statement : statements) {
            if (!statement.getType().equals(ATStatus.VARIABLE)) continue;
            sqlBuffer.append(" " + statement.getStatement());
        }
        return sqlBuffer.toString();
    }


}
