package com.ciaosgarage.stickie.beans.dao.sqlExecutor.resultSetTranslator;

import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface ResultSetTranslator.
 * ResultSet 의 데이터를 지정한 vo 객체로 변환시켜주는 클래스
 */
public interface ResultSetTranslator {
    /**
     * ResultSet 의 데이터를 지정한 vo 객체로 변환시켜주는 메소드
     *
     * @param resultSet 변환할 데이터셋
     * @param voInfo    변환할 객체의 정보를 가지고 있는 class
     * @return 변환된 객체
     * @throws SQLException 변환에 실패시에는 SQLException 이 발생한다.
     */
    ValueObject translator(ResultSet resultSet, Class voInfo) throws SQLException;
}
