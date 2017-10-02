package com.ciaosgarage.stickie.beans.services.relationService;

import com.ciaosgarage.stickie.parameters.attachStatement.ATStatus;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.FindingUnit;
import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.services.daoService.DaoService;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.relation.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicRelationService implements RelationService {
    @Autowired
    DaoService service;

    StateWhere findFollower;
    StateWhere findFollowing;

    public BasicRelationService() {
        findFollower = new StateWhere("account");
        findFollower.setAtStatus(ATStatus.VARIABLE);
        findFollowing = new StateWhere("friend");
        findFollower.setAtStatus(ATStatus.VARIABLE);
    }

    public void setService(DaoService service) {
        this.service = service;
    }

    @Override
    public List getFollowers(Account account) {
        findFollower.setKey(account.getPk());
        return service.pullList(Relation.class, findFollower);
    }

    @Override
    public List getFollowings(Account account) {
        findFollowing.setKey(account.getPk());
        return service.pullList(Relation.class, findFollowing);
    }

    @Override
    public void follow(Account account, Account friend) {
        Relation relationship = new Relation();
        relationship.setAccount(account.getPk());
        relationship.setFriend(friend.getPk());
        service.push(relationship);
    }

    @Override
    public void unFollow(Account account, Account friend) {
        FindingUnit accountFindingUnit = new FindingUnit("account", account.getPk());
        FindingUnit friendFindingUnit = new FindingUnit("friend", friend.getPk());
        StateWhere findingCondition = new StateWhere(accountFindingUnit, friendFindingUnit);

        try {
            Relation getRelation = (Relation) service.pull(Relation.class, findingCondition);
            service.delete(getRelation);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
