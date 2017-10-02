package com.ciaosgarage.test.daoTest.sqlMaker;

import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMaker.SqlMaker;
import com.ciaosgarage.stickie.beans.contexts.DaoContext;
import com.ciaosgarage.stickie.vo.account.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
public class sqlMakerTest {
    @Autowired
    private SqlMaker sqlMaker;

    @Before
    public void setUp() {
        assertNotNull(sqlMaker);
    }

    @Test
    public void update() {
        String sql;
        sql = sqlMaker.update(Account.class);
        System.out.println(sql);
        assertThat(sql, is("UPDATE Account SET isDelete = :isDelete, password = :password, nickname = :nickname, authorization = :authorization, loginCount = :loginCount, loginInfo = :loginInfo WHERE pk= :pk"));
    }

    @Test
    public void insert() {
        String sql;
        sql = sqlMaker.insert(Account.class);
        System.out.println(sql);
        assertThat(sql, is("INSERT INTO Account (pk, isDelete, email, password, nickname, authorization, loginCount, loginInfo) VALUES (:pk, :isDelete, :email, :password, :nickname, :authorization, :loginCount, :loginInfo)"));
    }

    @Test
    public void delete() {
        String sql;
        sql = sqlMaker.delete(Account.class);
        System.out.println(sql);
        assertThat(sql, is("DELETE FROM Account WHERE email=:StateWhereemail"));
    }

    @Test
    public void select() {
        String sql;
        sql = sqlMaker.select(Account.class, Arrays.asList(new StateWhere("email", "teja0924@naver.com")));
        System.out.println(sql);
        assertThat(sql, is("SELECT * FROM Account WHERE email=:StateWhereemail"));
    }
}
