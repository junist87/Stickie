package com.ciaosgarage.test.daoTest.mapperMaker;

import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlMapperMaker.SqlMapperMaker;
import com.ciaosgarage.stickie.beans.contexts.DaoContext;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.test.daoTest.SeqTableDummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoContext.class)
public class mapperMakerTest {
    @Autowired
    SqlMapperMaker sqlMapperMaker;


    SeqTable seqTable01;
    SeqTable seqTable02;
    SeqTable seqTable03;



    @Before
    public void setUp() {
        assertNotNull(sqlMapperMaker);

        SeqTableDummy dummy = new SeqTableDummy();

        seqTable01 = dummy.seqTable01;

        seqTable02 = dummy.seqTable02;

        seqTable03 = dummy.seqTable03;
    }

    @Test
    public void get() {
        Map<String, Object> getMap = sqlMapperMaker.makeMapper(seqTable01);
        confirmMap(getMap, seqTable01);
    }

    private void confirmMap(Map<String, Object> getMap, Object target) {
        try {
            for (Field field: target.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!getMap.get(field.getName()).equals(field.get(target))) fail();
            }
        } catch (IllegalAccessException e) {
            fail();
        }
    }
}
