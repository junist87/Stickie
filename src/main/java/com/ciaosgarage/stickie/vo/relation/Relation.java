package com.ciaosgarage.stickie.vo.relation;

import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.RwType;
import com.ciaosgarage.stickie.beans.dao.vo.SimpleValueObject;

public class Relation extends SimpleValueObject {
    @PrimaryKey(rwType = RwType.INSERTONLY)
    private String pk;
    private String account;
    private String friend;

    public String getPk() {
        return pk;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String accountPk) {
        this.account = accountPk;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friendPk) {
        this.friend = friendPk;
    }
}
