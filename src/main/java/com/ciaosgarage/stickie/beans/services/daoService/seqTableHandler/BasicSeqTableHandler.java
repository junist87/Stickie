package com.ciaosgarage.stickie.beans.services.daoService.seqTableHandler;

import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.parameters.attachStatement.stateLimit.StateLimit;
import com.ciaosgarage.stickie.parameters.attachStatement.stateOrderBy.SOBOperator;
import com.ciaosgarage.stickie.parameters.attachStatement.stateOrderBy.StateOrderBy;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.vo.seqTable.SeqTable;
import com.ciaosgarage.stickie.vo.seqTable.SeqTableStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class BasicSeqTableHandler implements SeqTableHandler {

    @Autowired
    Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public String getPk(Class voInfo) {
        SeqTable newRcd = getNewRecord(voInfo);
        dao.add(newRcd);
        return newRcd.getTargetPk();
    }


    private SeqTable getNewRecord(Class voInfo) {
        String targetTable = voInfo.getSimpleName();

        try {
            // 마지막 레코드를 추출한다
            AttachStatement[] statements = new AttachStatement[]{
                    new StateWhere("tablename", targetTable),
                    new StateOrderBy(SOBOperator.DESC, "pk"),
                    new StateLimit(1)};

            // 마지막 값을 불러내  pk + 1 시키고 새로운 vo 객체를 생성한다.
            SeqTable lastRcd = (SeqTable) dao.get(SeqTable.class, statements);
            Long newTargetPk = Long.parseLong(lastRcd.getTargetPk(), 16) + 1;
            return getEmptyNewRecord(voInfo, newTargetPk);
        } catch (EmptyResultDataAccessException e) {
            // 마지막 레코드가 없을시 초기값 1을 넣어준다
            return getEmptyNewRecord(voInfo, 1);
        }
    }

    private SeqTable getEmptyNewRecord(java.lang.Class voInfo, long targetNewPk) {
        SeqTable newRcd = new SeqTable();
        newRcd.setTargetPk(Long.toHexString(targetNewPk));
        newRcd.setPk(getNewPkInSeqTable());
        newRcd.setTablename(voInfo.getSimpleName());
        newRcd.setStatus(SeqTableStatus.WORKING);
        return newRcd;
    }


    private int getNewPkInSeqTable() {
        try {

            AttachStatement[] statements = new AttachStatement[]{
                    new StateOrderBy(SOBOperator.DESC, "pk"),
                    new StateLimit(1)
            };
            SeqTable lastRcd = (SeqTable) dao.get(SeqTable.class, statements);
            return lastRcd.getPk() + 1;
        } catch (EmptyResultDataAccessException e) {
            // seqTable 이 비어있는경우
            return 1;
        }
    }
}
