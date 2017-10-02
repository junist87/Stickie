package com.ciaosgarage.test.daoTest.dao;


import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.parameters.attachStatement.stateLimit.StateLimit;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.test.daoTest.SeqTableDummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class DaoTest {
    @Autowired
    Dao dao;

    SeqTable seqTable01;
    SeqTable seqTable02;
    SeqTable seqTable03;


    @Before
    public void setUp() {
        dao.deleteAll(SeqTable.class);

        SeqTableDummy dummy = new SeqTableDummy();

        seqTable01 = dummy.seqTable01;

        seqTable02 = dummy.seqTable02;

        seqTable03 = dummy.seqTable03;
    }

    @Test
    public void addAndGet() {

        dao.add(seqTable01);
        checkVo(seqTable01, (SeqTable) dao.get(SeqTable.class, new StateWhere(seqTable01.getPrimaryKeyColumnName(), seqTable01.getPrimaryKeyValue())));
        //assertThat(dao.count(seqTable.class), is(1));

        dao.add(seqTable02);
        checkVo(seqTable02, (SeqTable) dao.get(SeqTable.class, new StateWhere(seqTable02.getPrimaryKeyColumnName(), seqTable02.getPrimaryKeyValue())));
        //assertThat(dao.count(seqTable.class), is(2));

        dao.add(seqTable03);
        checkVo(seqTable03, (SeqTable) dao.get(SeqTable.class, new StateWhere(seqTable03.getPrimaryKeyColumnName(), seqTable03.getPrimaryKeyValue())));
        //assertThat(dao.count(seqTable.class), is(3));
    }

    @Test
    public void updateAndGet() {
        dao.add(seqTable01);
        seqTable02.setPk(seqTable01.getPk());
        dao.update(seqTable02);
        checkVo(seqTable02, (SeqTable) dao.get(SeqTable.class, new StateWhere(seqTable01.getPrimaryKeyColumnName(), seqTable01.getPrimaryKeyValue())));
    }

    @Test
    public void getWithStatement() {
        dao.add(seqTable01);

        dao.get(SeqTable.class, new StateLimit(1));

    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void delete() {
        dao.add(seqTable01);
        dao.add(seqTable02);
        dao.add(seqTable03);

        dao.delete(seqTable01);
        dao.get(SeqTable.class, new StateWhere(seqTable01.getPrimaryKeyColumnName(), seqTable01.getPrimaryKeyValue()));
    }

    private void checkVo(SeqTable s1, SeqTable s2) {
        try {
            for (Field field : s1.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("createDate")) continue;
                assertThat(field.get(s1), is(field.get(s2)));
            }
        } catch (IllegalAccessException e) {
            fail();
            e.printStackTrace();
        }
    }
}
