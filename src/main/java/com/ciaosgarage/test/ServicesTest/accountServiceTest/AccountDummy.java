package com.ciaosgarage.test.ServicesTest.accountServiceTest;

import com.ciaosgarage.stickie.vo.ActivationStatus;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.account.AuthorizationStatus;

import java.util.ArrayList;
import java.util.List;

public class AccountDummy {
    public Account account01;
    public Account account02;
    public Account account03;
    public Account account04;
    public Account account05;

    public AccountDummy() {
        account01 = new Account();
        account01.setIsDelete(ActivationStatus.WORIKING);
        account01.setEmail("Apple@apple.com");
        account01.setPassword("ABCD");
        account01.setNickname("Adol");
        account01.setAuthorization(AuthorizationStatus.NONE);
        account01.setLoginCount(0);
        account01.setLoginInfo("162.213.231.1");

        account02 = new Account();
        account02.setIsDelete(ActivationStatus.WORIKING);
        account02.setEmail("Banana@apple.com");
        account02.setPassword("!@#D");
        account02.setNickname("Bienna");
        account02.setAuthorization(AuthorizationStatus.NONE);
        account02.setLoginCount(0);
        account02.setLoginInfo("43.22.231.1");

        account03 = new Account();
        account03.setIsDelete(ActivationStatus.WORIKING);
        account03.setEmail("Citron@apple.com");
        account03.setPassword("BA@!");
        account03.setNickname("Cyan");
        account03.setAuthorization(AuthorizationStatus.NONE);
        account03.setLoginCount(0);
        account03.setLoginInfo("12.62.231.1");


        account04 = new Account();
        account04.setIsDelete(ActivationStatus.WORIKING);
        account04.setEmail("Durian@apple.com");
        account04.setPassword("oa!@");
        account04.setNickname("Dave");
        account04.setAuthorization(AuthorizationStatus.NONE);
        account04.setLoginCount(0);
        account04.setLoginInfo("643.313.2.1");


        account05 = new Account();
        account05.setIsDelete(ActivationStatus.WORIKING);
        account05.setEmail("Elderberry@apple.com");
        account05.setPassword("o1mca");
        account05.setNickname("Eiden");
        account05.setAuthorization(AuthorizationStatus.NONE);
        account05.setLoginCount(0);
        account05.setLoginInfo("124.2.231.1");
    }

    public Account getRandomAccount() {
        Account account = new Account();
        account.setIsDelete(ActivationStatus.WORIKING);
        account.setEmail(String.valueOf(account.hashCode()));
        account.setPassword(String.valueOf((int) (Math.random() * 100000)));
        account.setNickname(String.valueOf((int) (Math.random() * 100000)));
        account.setAuthorization(AuthorizationStatus.NONE);
        account.setLoginCount(0);
        account.setLoginInfo(String.valueOf((int) (Math.random() * 100000)));
        return account;
    }

    public List<Account> getRandomAccountList(int size) {
        List<Account> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(getRandomAccount());
        }

        return list;
    }
}
