package com.ciaosgarage.stickie.beans.dao.dao;

import com.ciaosgarage.stickie.beans.dao.sqlExecutor.sqlExecutor.SqlExecutor;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlHandler;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlProduct;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlType;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class BasicDao implements Dao {
    @Autowired
    private SqlHandler sqlHandler;

    @Autowired
    private SqlExecutor sqlExecutor;

    @Override
    public int add(ValueObject vo) {
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(vo, SqlType.INSERT);
        return sqlExecutor.update(sqlProduct.getSql(), sqlProduct.getMapper());
    }

    @Override
    public ValueObject get(Class voInfo, AttachStatement... statements) {
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(voInfo, SqlType.SELECT, statements);
        return sqlExecutor.queryForObject(voInfo, sqlProduct.getSql(), sqlProduct.getMapper());
    }

    @Override
    public List<ValueObject> getList(Class voInfo, AttachStatement... statements) {
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(voInfo, SqlType.SELECT, statements);
        return sqlExecutor.query(voInfo, sqlProduct.getSql(), sqlProduct.getMapper());
    }

    @Override
    public int update(ValueObject vo) {
        // primary key 값이 기준이므로 체크한다.
        if (vo.getPrimaryKeyValue() == null) return 0;
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(vo, SqlType.UPDATE);
        return sqlExecutor.update(sqlProduct.getSql(), sqlProduct.getMapper());
    }

    @Override
    public int delete(ValueObject vo) {
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(vo, SqlType.DELETE, new StateWhere(vo.getPrimaryKeyColumnName(), vo.getPrimaryKeyValue()));
        return sqlExecutor.update(sqlProduct.getSql(), sqlProduct.getMapper());
    }

    @Override
    public int count(Class voInfo) {
        return sqlExecutor.count(voInfo.getSimpleName());
    }

    @Override
    public int deleteAll(Class voInfo) {
        return sqlExecutor.update("DELETE FROM " + voInfo.getSimpleName(), new HashMap<>());
    }
}
