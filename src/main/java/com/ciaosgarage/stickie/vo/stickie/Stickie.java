package com.ciaosgarage.stickie.vo.stickie;

import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.RwType;
import com.ciaosgarage.stickie.beans.dao.vo.SimpleValueObject;

public class Stickie extends SimpleValueObject{
    @PrimaryKey(rwType = RwType.INSERTONLY)
    private String pk;
    private String contents;

    public String getPk() {
        return pk;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
