package com.ciaosgarage.stickie.beans.services.accountService;

import com.ciaosgarage.stickie.parameters.attachStatement.stateWhere.StateWhere;
import com.ciaosgarage.stickie.beans.services.daoService.DaoService;
import com.ciaosgarage.stickie.vo.ActivationStatus;
import com.ciaosgarage.stickie.vo.account.Account;
import com.ciaosgarage.stickie.vo.account.AuthorizationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


/*
 * 트랜젝션을 위해서 모든 서비스 단계의 로직은 예외처리를 반드시 한다.
 * 발생될 예외에 대해서 자세히 기술할 것
 *
 */
@Service
public class BasicAccountService implements AccountService {
    static final Integer LOGIN_TRY_MAX_COUNT = 5;

    @Autowired
    DaoService daoService;

    private StateWhere stateEmail = new StateWhere("email");

    @Override
    public void removeAccount(String email) throws CantRemoveRecordException {
        // 데이터를 가져오기 위해 더미 데이터 생성후 입력
        Account account = new Account();
        account.setEmail(email);

        try {
            // 이미 존재하는 값인지 검사, 없으면 EmptyResultDataAccessException
            stateEmail.setKey(email);
            account = (Account) daoService.pull(Account.class, stateEmail);

            // isDelete 컬럼 수정후
            account.setIsDelete(ActivationStatus.DELETED);

            // 데이터 입력
            daoService.push(account);
        } catch (EmptyResultDataAccessException e) {
            throw new CantRemoveRecordException();
        }
    }

    @Override
    public void updateAccount(String email, Account account) {
        try {
            // 기존값 가져오기, 없으면 EmptyResultDataAccessException 발생
            stateEmail.setKey(email);
            Account dbAccount = (Account) daoService.pull(Account.class, stateEmail);

            // 필수요소 존재 여부 조사, 없으면 NoExistColumnValueException 발생
            checkEssentialColumnValue(account);

            // 컬럼 수정후
            dbAccount.setIsDelete(account.getIsDelete());
            dbAccount.setPassword(account.getPassword());
            dbAccount.setNickname(account.getNickname());
            dbAccount.setAuthorization(account.getAuthorization());
            dbAccount.setLoginCount(account.getLoginCount());
            dbAccount.setLoginInfo(account.getLoginInfo());

            // 데이터 입력
            daoService.push(dbAccount);
        } catch (NoExistColumnValueException | EmptyResultDataAccessException e) {
            throw e;
        }

    }

    @Override
    public Account createAccount(String email, String password, String nickname, String loginInfo) {
        Account newAccount = new Account();
        newAccount.setEmail(email);
        newAccount.setPassword(password);
        newAccount.setNickname(nickname);
        newAccount.setLoginInfo(loginInfo);
        newAccount.setIsDelete(ActivationStatus.WORIKING);
        newAccount.setAuthorization(AuthorizationStatus.NONE);
        newAccount.setLoginCount(0);
        daoService.push(newAccount);

        // 데이터 입력후, 다시 출력하여 리턴한다(pk 값등이 체워져서 나온다)
        stateEmail.setKey(email);
        return (Account) daoService.pull(Account.class, stateEmail);
    }

    @Override
    public Account getAccount(String email, String password, String loginInfo) throws NoMatchingPasswordException, TryLoginOverCountException, EmptyResultDataAccessException {

        try {
            // 아이디가 있는지 검사, 없으면 EmptyResultDataAccessException
            stateEmail.setKey(email);
            Account dbAccount = (Account) daoService.pull(Account.class, stateEmail);

            // 로그인 시도 횟수 검사, 비교값보다 높으면 TryLoginOverCountException
            if (dbAccount.getLoginCount() > LOGIN_TRY_MAX_COUNT) throw new TryLoginOverCountException();

            // 비밀번호 검사, 다르면 NoMatchingPasswordException
            if (!dbAccount.getPassword().equals(password)) throw new NoMatchingPasswordException();

            // 로그인 완료. 필요한 정보를 입력하고 데이터를 출력한다
            // 접속 정보 저장
            dbAccount.setLoginInfo(loginInfo);

            // 로그인 시도 횟수 초기화
            dbAccount.setLoginCount(0);

            // 데이터 입력
            daoService.push(dbAccount);

            // account 를 리턴한다
            return dbAccount;
        } catch (TryLoginOverCountException e) {
            // 로그인 시도 횟수가 오버되었으므로  throw
            throw e;
        } catch (EmptyResultDataAccessException e) {
            // 아이디 정보가 아예 없다
            throw e;
        } catch (NoMatchingPasswordException e) {
            // 비밀번호가 틀렸으므로 로그인 카운트를 +1 하고  throw
            stateEmail.setKey(email);
            Account countUpAccount = (Account) daoService.pull(Account.class, stateEmail);
            countUpAccount.setLoginCount(countUpAccount.getLoginCount() + 1);
            countUpAccount.setLoginInfo(loginInfo);
            daoService.push(countUpAccount);
            throw e;
        }
    }

    private void checkEssentialColumnValue(Account account) throws NoExistColumnValueException {
        if ((account.getEmail() == null)
                && (account.getEmail().equals(""))) throw new NoExistColumnValueException("!- Account.email -!");
        if ((account.getPassword() == null)
                && (account.getPassword().equals("")))
            throw new NoExistColumnValueException("!- Account.password -!");
        if ((account.getLoginInfo() == null)
                && (account.getLoginInfo().equals("")))
            throw new NoExistColumnValueException("!- Account.loginInfo -!");

    }
}
