package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage;

import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlType;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Repository
public class BasicSqlStorage implements SqlStorage {
    Map<java.lang.Class, Map<SqlType, Map<Set<Class>, String>>> sqlStorage;

    public BasicSqlStorage() {
        sqlStorage = new HashMap<>();
    }

    @Override
    public String getSql(SqlIndex index) {
        Map<SqlType, Map<Set<Class>, String>> classMap = sqlStorage.get(index.getVoInfo());
        if (classMap == null) return null;

        Map<Set<Class>, String> sqlTypeMap = classMap.get(index.getType());
        if (sqlTypeMap == null) return null;

        return sqlTypeMap.get(index.getAttachStatementsInfo());
    }

    @Override
    public boolean setSql(SqlIndex index, String sql) {
        try {
            Map<Set<Class>, String> sqlTypeMap = new HashMap<>();
            sqlTypeMap.put(index.getAttachStatementsInfo(), sql);

            Map<SqlType, Map<Set<Class>, String>> classMap = new HashMap<>();
            classMap.put(index.getType(), sqlTypeMap);

            sqlStorage.put(index.getVoInfo(), classMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
