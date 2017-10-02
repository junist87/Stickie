package com.ciaosgarage.test.ServicesTest.daoServiceTest.seqTableHandler;

import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.beans.services.daoService.seqTableHandler.SeqTableHandler;
import com.ciaosgarage.stickie.vo.relation.Relation;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.stickie.Stickie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class SeqTableHandlerTest {

    @Autowired
    SeqTableHandler handler;

    @Autowired
    Dao dao;

    @Before
    public void setUp() {
        dao.deleteAll(SeqTable.class);
        assertNotNull(handler);

    }

    @Test
    public void getPk() {


        long stickieLastPk = 0;
        long accountLastPk = 0;
        long friendsLastPk = 0;

        for (int i = 0; i < 100; i++) {
            int swt = (int) (Math.random() * 3);
            switch (swt) {
                case 0:
                    handler.getPk(Stickie.class);
                    stickieLastPk += 1;
                    break;
                case 1:
                    handler.getPk(Account.class);
                    accountLastPk += 1;
                    break;
                case 2:
                    handler.getPk(Relation.class);
                    friendsLastPk += 1;
                    break;
                default:
            }
        }

        assertThat(handler.getPk(Stickie.class), is(Long.toHexString(stickieLastPk + 1)));
        assertThat(handler.getPk(Account.class), is(Long.toHexString(accountLastPk + 1)));
        assertThat(handler.getPk(Relation.class), is(Long.toHexString(friendsLastPk + 1)));
    }

}
