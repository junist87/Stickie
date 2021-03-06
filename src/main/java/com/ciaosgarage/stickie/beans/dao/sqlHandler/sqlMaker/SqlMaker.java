package com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMaker;


import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;

import java.util.List;

/**
 * The interface SqlMaker.
 * 입력된 데이터를 기준으로 SQL 문장을 작성하는 클래스
 * Update, Insert, Delete
 */
public interface SqlMaker {
    /**
     * Select 문을 만드는 메소드
     *
     * @param voInfo     SQL 작성에 필요한 vo 객체의 class 파일
     * @param statements SQL 작성에 필요한 추가구문
     * @return SQL 문
     */
    String select(Class voInfo, List<AttachStatement> statements);

    /**
     * Insert 문을 만드는 메소드
     *
     * @param voInfo SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String insert(Class voInfo);

    /**
     * Delete 문을 만드는 메소드
     * Primary Key 를 기준으로 삭제 SQL 문을 만든다.
     *
     * @param voInfo     SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String delete(Class voInfo);

    /**
     * Update 문을 만드는 메소드
     * Primary Key 를 기준으로 삭제 SQL 문을 만든다.
     * @param voInfo SQL 작성에 필요한 vo 객체의 class 파일
     * @return SQL 문
     */
    String update(Class voInfo);
}
