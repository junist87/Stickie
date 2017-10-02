package com.ciaosgarage.test.daoTest;

import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.stickie.vo.seqTable.SeqTableStatus;

public class SeqTableDummy {

    public SeqTable seqTable01;
    public SeqTable seqTable02;
    public SeqTable seqTable03;


    public SeqTableDummy() {
        seqTable01 = new SeqTable();
        seqTable01.setPk(1);
        seqTable01.setStatus(SeqTableStatus.WORKING);
        seqTable01.setTablename("Account");
        seqTable01.setTargetPk("AAA");

        seqTable02 = new SeqTable();
        seqTable02.setPk(2);
        seqTable02.setStatus(SeqTableStatus.WORKING);
        seqTable02.setTablename("Stickie");
        seqTable02.setTargetPk("AB");

        seqTable03 = new SeqTable();
        seqTable03.setPk(3);
        seqTable03.setStatus(SeqTableStatus.WORKING);
        seqTable03.setTablename("Account");
        seqTable03.setTargetPk("AB");
    }
}
