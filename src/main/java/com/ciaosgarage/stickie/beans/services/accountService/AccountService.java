package com.ciaosgarage.stickie.beans.services.accountService;

import com.ciaosgarage.stickie.vo.account.Account;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountService {
    void removeAccount(String email) throws CantRemoveRecordException;

    void updateAccount(String email, Account account);

    Account createAccount(String email, String password, String nickname, String loginInfo);

    Account getAccount(String email, String password, String loginInfo) throws CantAccessAccountException;

}
