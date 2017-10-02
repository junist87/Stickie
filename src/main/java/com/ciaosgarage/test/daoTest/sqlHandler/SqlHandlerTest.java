package com.ciaosgarage.test.daoTest.sqlHandler;

import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlHandler;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlProduct;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlType;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.test.daoTest.SeqTableDummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class SqlHandlerTest {

    @Autowired
    SqlHandler sqlHandler;

    @Autowired
    SqlMapperMaker sqlMapperMaker;

    SeqTable seqTable01;
    SeqTable seqTable02;
    SeqTable seqTable03;


    @Before
    public void setUp() {
        assertNotNull(sqlHandler);

        SeqTableDummy dummy = new SeqTableDummy();

        seqTable01 = dummy.seqTable01;

        seqTable02 = dummy.seqTable02;

        seqTable03 = dummy.seqTable03;
    }

    @Test
    public void getSql() {
        SqlProduct sqlProduct = sqlHandler.getSqlProduct(seqTable01, SqlType.SELECT);

        String sql = sqlProduct.getSql();
        Map<String, Object> mapper = sqlProduct.getMapper();


        System.out.println(sql);
    }

    private void checkMapper(Map<String, Object> getMap, ValueObject valueObject, AttachStatement... statements) {
        if (!getMap.equals(sqlMapperMaker.makeMapper(valueObject, statements))) fail();
    }
}
