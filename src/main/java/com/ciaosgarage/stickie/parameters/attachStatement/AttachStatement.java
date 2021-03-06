package com.ciaosgarage.stickie.parameters.attachStatement;

import java.util.Map;

/**
 * The interface AttachStatement.
 * SQL 문 작성시에 검색 조건을 만들어 주는 인터페이스
 */
public interface AttachStatement {
    /**
     * SQL 문을 작성하여 리턴한다
     *
     * @return SQL 문
     */
    String getStatement();

    /**
     * SQL 실행시 필요한 맵퍼를 작성하여 주는 메소드
     *
     * @return 작성된 Map
     */
    Map<String, Object> getMapper();

    /**
     * 속성을 리턴한다
     * VARIABLE : 가변성을 가진 SQL
     * FIXED : 고정성을 가진 SQL
     *
     * @return the type
     */
    ATStatus getType();

}
