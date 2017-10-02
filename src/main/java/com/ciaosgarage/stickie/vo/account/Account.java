package com.ciaosgarage.stickie.vo.account;

import com.ciaosgarage.stickie.beans.dao.vo.ColumnConfig;
import com.ciaosgarage.stickie.beans.dao.vo.RwType;
import com.ciaosgarage.stickie.beans.dao.vo.SimpleValueObject;
import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.vo.ActivationStatus;

import java.util.Date;

public class Account extends SimpleValueObject {
    @PrimaryKey(rwType = RwType.INSERTONLY)
    private String pk;
    private Integer isDelete;

    @ColumnConfig(rwType = RwType.INSERTONLY)
    private String email;
    private String password;
    private String nickname;
    private Integer authorization;
    private Integer loginCount;
    private String loginInfo;

    @ColumnConfig(rwType = RwType.READONLY)
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public String getPk() {
        return pk;
    }

    public ActivationStatus getIsDelete() {
        return ActivationStatus.getActivationStatus(isDelete);
    }

    public void setIsDelete(ActivationStatus status) {
        this.isDelete = ActivationStatus.getIntValue(status);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AuthorizationStatus getAuthorization() {
        return AuthorizationStatus.getStatus(authorization);
    }

    public void setAuthorization(AuthorizationStatus status) {
        this.authorization = AuthorizationStatus.getIntValue(status);
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(String loginInfo) {
        this.loginInfo = loginInfo;
    }
}
