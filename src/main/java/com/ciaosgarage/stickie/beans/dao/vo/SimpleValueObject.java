package com.ciaosgarage.stickie.beans.dao.vo;

import java.lang.reflect.Field;

public class SimpleValueObject implements ValueObject {
    @Override
    public Object getPrimaryKeyValue() {
        return getAnnotationPrimaryKey(this, ReturnObject.VALUE);
    }

    @Override
    public String getPrimaryKeyColumnName() {
        return (String) getAnnotationPrimaryKey(this, ReturnObject.COLUMNNAME);
    }

    @Override
    public String getTableName() {
        return this.getClass().getSimpleName();
    }

    private Object getAnnotationPrimaryKey(ValueObject vo, ReturnObject value) {
        Class targetClass = vo.getClass();
        try {
            for (Field field : targetClass.getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    switch (value) {
                        case VALUE:
                            return field.get(vo);
                        case COLUMNNAME:
                            return field.getName();
                        default:
                            return null;
                    }
                }
            }
        } catch (IllegalAccessException e) {
            return null;
        }
        return null;
    }

    private enum ReturnObject {
        VALUE, COLUMNNAME;
    }
}
