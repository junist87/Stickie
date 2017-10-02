package com.ciaosgarage.stickie.beans.services.daoService;

import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;


/**
 * The interface DaoService
 * 프로그램에서 쓰일 데이터를 총괄적으로 관리해주는 클래스
 */
public interface DaoService {
    /**
     * 데이터베이스에서 레코드 1개를 가져오는 메소드
     *
     * @param voInfo     가져올 데이터의 정보를 가지고 있는 vo 객체의 클래스
     * @param statements 가져올 데이터의 조건
     * @return 조건에 맞는 데이터
     * @throws EmptyResultDataAccessException 조건에 맞는 데이터가 없다면 RuntimeException이 발생한다
     */
    ValueObject pull(java.lang.Class voInfo, AttachStatement... statements) throws EmptyResultDataAccessException;

    /**
     * 데이터베이스에서 레코드 여러개를 가져오는 메소드
     *
     * @param voInfo     가져올 데이터의 정보를 가지고 있는 vo 객체의 클래스
     * @param statements 가져올 데이터의 조건
     * @return 조건에 맞는 데이터
     * @throws EmptyResultDataAccessException 조건에 맞는 데이터가 없다면 RuntimeException이 발생한다
     */
    List pullList(java.lang.Class voInfo, AttachStatement... statements) throws EmptyResultDataAccessException;

    /**
     * 데이터베이스에 데이터를 입력할때 사용하는 메소드
     * Primary key 값을 기준으로, 그 값이 존재 하지 않으면
     * seqTableHandler를 사용하여 값을 생성하고 데이터베이스에 입력한다
     * primary key 값이 있다면 기존값을 수정한다
     *
     * @param vo 입력할 vo 객체
     */
    void push(ValueObject vo);

    /**
     * 데이터베이스에서 레코드 하나를 삭제 할때 사용하는 메소드
     * Primary key 값을 기준으로 삭제한다
     *
     * @param vo 삭제할 vo 객체
     */
    void delete(ValueObject vo);
}
