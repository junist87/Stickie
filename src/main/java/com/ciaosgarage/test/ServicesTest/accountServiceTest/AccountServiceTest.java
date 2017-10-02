package com.ciaosgarage.test.ServicesTest.accountServiceTest;

import com.ciaosgarage.stickie.beans.contexts.StickieContext;
import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.services.accountService.AccountService;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.account.AuthorizationStatus;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StickieContext.class)
public class AccountServiceTest {
    @Autowired
    AccountService service;

    @Autowired
    Dao dao;

    AccountDummy dummy;

    @Before
    public void setUp() {
        assertNotNull(service);
        dummy = new AccountDummy();
        dao.deleteAll(SeqTable.class);
        dao.deleteAll(Account.class);
    }

    @Test
    public void createAccountAndGet() {
        Account account = dummy.account01;
        Account getAccount = service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        checkSameAccount(account, getAccount);

        account = dummy.account02;

        getAccount = service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        checkSameAccount(account, getAccount);

        account = dummy.account03;
        getAccount = service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        checkSameAccount(account, getAccount);

        account = dummy.account04;
        getAccount = service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        checkSameAccount(account, getAccount);

        account = dummy.account05;
        getAccount = service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        checkSameAccount(account, getAccount);
    }

    @Test
    public void updateAccount() {
        Account account = dummy.account01;
        service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        account.setAuthorization(AuthorizationStatus.PASS);
        account.setNickname("@SDGD");
        account.setPassword("adee");
        service.updateAccount(account.getEmail(), account);
        Account getAccount = service.getAccount(account.getEmail(), account.getPassword(), account.getLoginInfo());
        checkSameAccount(account, getAccount);


        account = dummy.account02;
        service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        account.setAuthorization(AuthorizationStatus.PASS);
        account.setNickname("3dad");
        account.setPassword("@#GAD");
        service.updateAccount(account.getEmail(), account);
        getAccount = service.getAccount(account.getEmail(), account.getPassword(), account.getLoginInfo());
        checkSameAccount(account, getAccount);

        account = dummy.account03;
        service.createAccount(account.getEmail(), account.getPassword(), account.getNickname(), account.getLoginInfo());
        account.setAuthorization(AuthorizationStatus.PASS);
        account.setNickname("903nd");
        account.setPassword("09avndps");
        service.updateAccount(account.getEmail(), account);
        getAccount = service.getAccount(account.getEmail(), account.getPassword(), account.getLoginInfo());
        checkSameAccount(account, getAccount);
    }



    public void checkSameAccount(Account a1, Account a2) {
        try {
            for (Field field : a1.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(PrimaryKey.class)) continue;
                if (field.getName().equals("createDate")) continue;

                System.out.println("Check[" + field.getName() + "] " +
                        field.get(a1) + " : " + field.get(a2));
                assertThat(field.get(a1), is(field.get(a2)));
            }
        } catch (IllegalAccessException e) {
            fail();
            e.printStackTrace();
        }
    }
}
