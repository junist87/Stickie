package com.ciaosgarage.stickie.beans.services.daoService;

import com.ciaosgarage.stickie.beans.dao.dao.Dao;
import com.ciaosgarage.stickie.parameters.attachStatement.AttachStatement;
import com.ciaosgarage.stickie.beans.dao.vo.PrimaryKey;
import com.ciaosgarage.stickie.beans.dao.vo.ValueObject;
import com.ciaosgarage.stickie.beans.services.daoService.seqTableHandler.SeqTableHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;


@Service
public class BasicDaoService implements DaoService {
    @Autowired
    Dao dao;

    @Autowired
    SeqTableHandler seqTableHandler;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void setSeqTableHandler(SeqTableHandler seqTableHandler) {
        this.seqTableHandler = seqTableHandler;
    }

    @Override
    public ValueObject pull(Class voInfo, AttachStatement... statements) {
        return dao.get(voInfo, statements);
    }

    @Override
    public List pullList(Class voInfo, AttachStatement... statements) {
        List<ValueObject> list = dao.getList(voInfo, statements);
        if (list.size() == 0) throw new EmptyResultDataAccessException(0);
        return list;
    }

    @Override
    public void push(ValueObject vo) {
        if (vo.getPrimaryKeyValue() == null) {
            String pk = seqTableHandler.getPk(vo.getClass());
            setPrimaryKeyValue(vo, pk);
            dao.add(vo);
        } else {
            dao.update(vo);
        }
    }

    @Override
    public void delete(ValueObject vo) {
        dao.delete(vo);
    }

    private void setPrimaryKeyValue(ValueObject vo, Object key) {
        try {
            for (Field field : vo.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(PrimaryKey.class)) {
                    field.set(vo, key);
                    return;
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("!- Can't insert primary key value");
        }

    }


}
