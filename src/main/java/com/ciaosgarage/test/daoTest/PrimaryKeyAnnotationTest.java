package com.ciaosgarage.test.daoTest;

import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.SimpleValueObject;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class PrimaryKeyAnnotationTest {

    class TempVO extends SimpleValueObject{
        @PrimaryKey
        private String pk;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }
    }

    class TempNoPkVo extends SimpleValueObject {
        private String pk;

        public String getPk() {
            return pk;
        }

        public void setPk(String pk) {
            this.pk = pk;
        }
    }

    @Test
    public void getPrimaryKey() {
        TempVO vo = new TempVO();
        vo.setPk("ABC");
        assertThat(vo.getPrimaryKeyValue(), is("ABC"));
        assertThat(vo.getPrimaryKeyColumnName(), is("pk"));


        TempNoPkVo noPkVo = new TempNoPkVo();
        noPkVo.setPk("ABC");
        assertNull(noPkVo.getPrimaryKeyValue());
        assertNull(noPkVo.getPrimaryKeyColumnName());
    }
}
