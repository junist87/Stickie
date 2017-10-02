package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage;

import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlType;

import java.util.Set;

public class SqlIndex {
    private Class voInfo;
    private SqlType type;
    private Set<Class> attachStatementsInfo;

    public SqlIndex(Class voInfo, SqlType type, Set<Class> attachStatementsInfo) {
        this.voInfo = voInfo;
        this.type = type;
        this.attachStatementsInfo = attachStatementsInfo;
    }

    public Class getVoInfo() {
        return voInfo;
    }

    public SqlType getType() {
        return type;
    }

    public Set<Class> getAttachStatementsInfo() {
        return attachStatementsInfo;
    }
}
