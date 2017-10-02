package com.ciaosgarage.stickie.beans.services.relationService;


import com.ciaosgarage.stickie.vo.account.Account;

import java.util.List;

public interface RelationService {
    List getFollowers(Account account);
    List getFollowings(Account account);
    void follow(Account account, Account friend);
    void unFollow(Account account, Account friend);
}
