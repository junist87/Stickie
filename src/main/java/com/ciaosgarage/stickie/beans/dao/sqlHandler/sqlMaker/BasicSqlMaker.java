package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMaker;

import com.ciaosgarage.stickie.parameters.attachStatement.ATStatus;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.ColumnConfig;
import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.RwType;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class BasicSqlMaker implements SqlMaker {

    @Override
    public String update(Class voInfo) {
        StringBuffer sql = new StringBuffer("UPDATE " + voInfo.getSimpleName() + " SET ");
        sql.append(getUpdateValues(voInfo));
        sql.append(" " + getWherePrimaryKey(voInfo));
        return sql.toString();
    }

    @Override
    public String insert(Class voInfo) {
        StringBuffer sql = new StringBuffer("INSERT INTO " + voInfo.getSimpleName());
        sql.append(" " + getInsertValue(voInfo));
        return sql.toString();
    }

    @Override
    public String delete(Class voInfo) {
        StringBuffer sql = new StringBuffer("DELETE FROM " + voInfo.getSimpleName());
        sql.append(" " + getWherePrimaryKey(voInfo));
        return sql.toString();
    }

    @Override
    public String select(Class voInfo, List<AttachStatement> statements) {
        StringBuffer sql = new StringBuffer("SELECT * FROM " + voInfo.getSimpleName());
        for (AttachStatement statement : statements) {
            if (statement.getType().equals(ATStatus.VARIABLE)) continue;
            sql.append(" " + statement.getStatement());
        }
        return sql.toString();
    }

    private String getUpdateValues(Class voInfo) {
        StringBuffer sql = new StringBuffer();
        for (Field field : voInfo.getDeclaredFields()) {
            field.setAccessible(true);
            if (getRwType(field).equals(RwType.EDITABLE)) {
                sql.append(field.getName() + " = :" + field.getName() + ", ");
            }
        }
        return sql.substring(0, sql.length() - 2);
    }


    private String getWherePrimaryKey(Class voInfo) {
        for (Field field : voInfo.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                return "WHERE " + field.getName() + "= :" + field.getName();
            }
        }
        return null;
    }


    private String getInsertValue(Class voInfo) {
        StringBuffer columns = new StringBuffer();
        StringBuffer values = new StringBuffer();

        for (Field field : voInfo.getDeclaredFields()) {
            field.setAccessible(true);
            if (!getRwType(field).equals(RwType.READONLY)) {
                columns.append(field.getName() + ", ");
                values.append(":" + field.getName() + ", ");
            }
        }
        String sql = "(" + columns.substring(0, columns.length() - 2)
                + ") VALUES (" + values.substring(0, values.length() - 2) + ")";
        return sql;
    }

    private RwType getRwType(Field field) {
        RwType rwType = RwType.EDITABLE;
        if (field.isAnnotationPresent(PrimaryKey.class)) {
            PrimaryKey columnConfig = field.getAnnotation(PrimaryKey.class);
            rwType = columnConfig.rwType();
        }

        if (field.isAnnotationPresent(ColumnConfig.class)) {
            ColumnConfig columnConfig = field.getAnnotation(ColumnConfig.class);
            rwType = columnConfig.rwType();
        }


        return rwType;
    }
}
