package com.ciaosgarage.stickie.beans.dao.dao;

import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

/**
 * The interface dao.
 * 데이터베이스에 접근하여 데이터를 열람(SELECT), 추가(INSERT), 수정(UPDATE), 삭제(DELETE) 하는 기능
 * <p>
 * <img src = ""/>
 * 클래스 다이어그램
 */
public interface Dao {
    /**
     * Add value object.
     * 새로운 데이터를 입력한다.
     *
     * @param vo 입력할 대상
     * @return 입력완료된 레코드의 갯수를 리턴한다
     */
    int add(ValueObject vo);

    /**
     * 데이터베이스에서 1개의 레코드를 읽어오는 메소드
     *
     * @param voInfo     데이터베이스에 접근할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @param statements 데이터를 읽어올때 필요한 조건
     * @return 조건에 맞는 데이터를 추출하려 리턴한다
     * @throws EmptyResultDataAccessException 검색결과가 없으면 생성되는 RuntimeException
     */
    ValueObject get(java.lang.Class voInfo, AttachStatement... statements) throws EmptyResultDataAccessException;

    /**
     * 데이터베이스에서 여러개의 레코드를 읽어오는 메소드
     *
     * @param voInfo     데이터베이스에 접근할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @param statements 데이터를 읽어올때 필요한 조건
     * @return 조건에 맞는 데이터를 추출하려 리턴한다
     */
    List<ValueObject> getList(java.lang.Class voInfo, AttachStatement... statements);

    /**
     * 데이터베이스에서 해당 정보를 수정하는 메소드
     * primary key 를 기준으로 수정한다
     * primary key 가 없으면 수정하지 않는다.
     *
     * @param vo 수정할 데이터를 가지고 있는 vo 객체
     * @return 수정한 데이터베이스의 레코드 수를 리턴한다.
     */
    int update(ValueObject vo);

    /**
     * 데이터베이스에서 해당 정보를 가지고 있는 레코드를 지우는 메소드
     *
     * @param vo 삭제할 정보를 가지고 있는 데이터를 가지고 있는 vo 객체
     * @return 삭제한 데이터베이스의 레코드 수를 리턴한다.
     */
    int delete(ValueObject vo);

    /**
     * 해당 테이블의 전체 레코드 수를 리턴하는 메소드
     *
     * @param voInfo 검색할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @return 해당 테이블의 레코드 갯수
     */
    int count(java.lang.Class voInfo);

    /**
     * 테이블의 모든 레코드르 삭제하는 메소드
     *
     * @param voInfo 삭제할 테이블의 정보를 가지고 있는 vo 객체의 class
     * @return 삭제한 레코드의 갯수
     */
    int deleteAll(java.lang.Class voInfo);
}
