package com.ciaosgarage.test.ServicesTest.relationServiceTest;


import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.beans.services.daoService.DaoService;
import com.ciaosgarage.stickie.beans.services.relationService.RelationService;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.relation.Relation;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.stickie.vo.stickie.Stickie;
import com.ciaosgarage.test.ServicesTest.accountServiceTest.AccountDummy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class RelationServiceTest {

    @Autowired
    RelationService relationService;

    @Autowired
    DaoService daoService;

    @Autowired
    Dao dao;

    AccountDummy dummy;
    List<Account> accountList;
    int accountSize = 100;
    int testSize = 100;

    @Before
    public void setUp() {
        dao.deleteAll(Account.class);
        dao.deleteAll(Relation.class);
        dao.deleteAll(SeqTable.class);
        dao.deleteAll(Stickie.class);


        assertNotNull(relationService);
        dummy = new AccountDummy();
        accountList = dummy.getRandomAccountList(accountSize);


        List<Account> getList = new ArrayList<>();
        for (Account account : accountList) {
            daoService.push(account);
            getList.add((Account) daoService.pull(Account.class, new StateWhere("email", account.getEmail())));
        }
        accountList = getList;
    }

    @Test
    public void relation() {
        List<Relation> relationList = new ArrayList<>();
        Relation relation;

        int account = 0;
        int friend = 0;


        for (int i = 0; i < testSize; i++) {
            do {
                account = (int) (Math.random() * accountSize);
                friend = (int) (Math.random() * accountSize);
            } while (account == friend);

            relationService.follow(accountList.get(account), accountList.get(friend));

            relation = new Relation();
            relation.setAccount(accountList.get(account).getPk());
            relation.setFriend(accountList.get(friend).getPk());
            relationList.add(relation);
        }

        List<Relation> getList = daoService.pullList(Relation.class);

        checkList(relationList, getList);
    }

    private void checkList(List<Relation> list, List<Relation> getList) {
        System.out.println("listSize = " + list.size() + ", getListSize = " + getList.size());
        Collections.sort(list, new AccountComparator());
        Collections.sort(list, new FriendComparator());
        Collections.sort(getList, new AccountComparator());
        Collections.sort(getList, new FriendComparator());

        for (int i = 0; i < testSize; i++) {
            assertThat(list.get(i).getAccount(), is(getList.get(i).getAccount()));
            assertThat(list.get(i).getFriend(), is(getList.get(i).getFriend()));
        }
    }

    class AccountComparator implements Comparator<Relation> {

        @Override
        public int compare(Relation o1, Relation o2) {
            if (Long.valueOf(o1.getAccount(), 16) > Long.valueOf(o2.getAccount(), 16)) {
                return 1;
            } else if (Long.valueOf(o1.getAccount(), 16) < Long.valueOf(o2.getAccount(), 16)) {
                return -1;
            }
            return 0;
        }
    }

    class FriendComparator implements Comparator<Relation> {

        @Override
        public int compare(Relation o1, Relation o2) {
            if (Long.valueOf(o1.getFriend(), 16) > Long.valueOf(o2.getFriend(), 16)) {
                return 1;
            } else if (Long.valueOf(o1.getFriend(), 16) < Long.valueOf(o2.getFriend(), 16)) {
                return -1;
            }
            return 0;
        }
    }
}
