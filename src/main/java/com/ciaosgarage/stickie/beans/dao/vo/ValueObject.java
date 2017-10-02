package com.ciaosgarage.stickie.beans.dao.vo;

public interface ValueObject {
    Object getPrimaryKeyValue();
//    void setPrimaryKeyValue(Object value);
    String getPrimaryKeyColumnName();
    String getTableName();
}
