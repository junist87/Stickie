package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage;

public interface SqlStorage {
    String getSql(SqlIndex index);
    boolean setSql(SqlIndex index, String sql);
}
