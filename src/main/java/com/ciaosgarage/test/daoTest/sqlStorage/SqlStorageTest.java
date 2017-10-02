package com.ciaosgarage.test.daoTest.sqlStorage;

import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlHandler.SqlType;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage.SqlIndex;
import com.ciaosgarage.stickie.beans.dao.sqlHandler.sqlStorage.SqlStorage;
import com.ciaosgarage.stickie.beans.dao.vo.RwType;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class SqlStorageTest {

    @Autowired
    SqlStorage sqlStorage;

    SqlIndex index01;
    SqlIndex index02;

    @Before
    public void setUp() {
        assertNotNull(sqlStorage);

        Set<Class> set = new HashSet<>();
        set.add(StateWhere.class);
        index01 = new SqlIndex(Account.class, SqlType.SELECT, set);

        set = new HashSet<>();
        set.add(StateWhere.class);
        set.add(RwType.class);
        set.add(Account.class);
        index02 = new SqlIndex(SeqTable.class, SqlType.DELETE, set);
    }

    @Test
    public void setSql() {
        String sql01 = "Good";
        sqlStorage.setSql(index01, sql01);

        assertThat(sqlStorage.getSql(index01), is(sql01));

        String sql02 = "ABCD";
        sqlStorage.setSql(index02, sql02);
        assertThat(sqlStorage.getSql(index02), is(sql02));
    }
}
