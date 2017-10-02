package com.ciaosgarage.stickie.vo.seqTable;

import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.SimpleValueObject;

import java.sql.Date;

public class SeqTable extends SimpleValueObject {
    @PrimaryKey
    private Integer pk;
    private Date createDate;
    private String targetPk;
    private String tablename;
    private Integer status;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTargetPk() {
        return targetPk;
    }

    public void setTargetPk(String targetPk) {
        this.targetPk = targetPk;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public SeqTableStatus getStatus() {
        return SeqTableStatus.getSeqTableStatus(status);
    }

    public void setStatus(SeqTableStatus status) {
        this.status = SeqTableStatus.getIntValue(status);
    }
}
