package com.ciaosgarage.test.ServicesTest.daoServiceTest.daoService;

import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.services.daoService.DaoService;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.relation.Relation;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.stickie.vo.stickie.Stickie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class DaoServiceTest {

    @Autowired
    DaoService daoService;

    @Autowired
    Dao dao;

    @Before
    public void setUp() {
        assertNotNull(daoService);
        dao.deleteAll(SeqTable.class);
        dao.deleteAll(Stickie.class);
        dao.deleteAll(Relation.class);
        dao.deleteAll(Account.class);
    }

    @Test
    public void push() {
        Stickie stickie01 = new Stickie();

        String contents = "가나다라마바사";
        stickie01.setContents(contents);
        daoService.push(stickie01);

        Stickie getStickie01 = (Stickie) daoService.pull(Stickie.class, new StateWhere("contents", contents));

        assertThat(stickie01.getContents(), is(getStickie01.getContents()));


        Relation relation = new Relation();
        relation.setAccount("A");
        relation.setFriend("B");
        daoService.push(relation);
    }
}
