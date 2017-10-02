package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMapperMaker;

import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;

import java.util.Map;

/**
 * The interface SqlMapperMaker
 * SQL 실행시에 필요한 맵퍼를 만들어 주는 클래스
 */
public interface SqlMapperMaker {
    /**
     *  SQL 실행시에 필요한 맵퍼를 만들어 주는 메소드
     *
     * @param vo         맵퍼 작성에 필요한 데이터를 가지고 있는 vo 객체
     * @param statements 정보 검색시에 작성된 statement
     * @return the map
     */
    Map<String, Object> makeMapper(ValueObject vo, AttachStatement... statements);

    /**
     * SQL 실행시에 필요한 맵퍼를 만들어 주는 메소드
     *
     * @param statements 정보 검색시에 작성된 statement
     * @return the map
     */
    Map<String, Object> makeMapper(AttachStatement... statements);
}
