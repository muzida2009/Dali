package com.ericsson.service.impl;

import com.ericsson.dao.AccountDao;
import com.ericsson.dao.impl.AccountDaoImpl;
import com.ericsson.domain.Account;
import com.ericsson.service.AccountService;

/**
 * Created by Dali on 2018/1/6.
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();
    @Override
    public void transfer(String srcName, String dstName, float money) {
        /*
            1. 根据srcName查询账户
            2. 根据dstName查询账户
            3. 原账户减钱
            4. 目标账户加钱
         */
            Account srcAccount = accountDao.queryAccountByName(srcName);
            Account dstAccount = accountDao.queryAccountByName(dstName);
            if (srcAccount != null && dstAccount != null){
                srcAccount.setMoney(srcAccount.getMoney() - money);
                dstAccount.setMoney(dstAccount.getMoney() + money);
                accountDao.update(srcAccount);      //更新原账户
                int error = 1/0;                    //模拟转账异常
                accountDao.update(dstAccount);      //更新目标账户
            }
    }
    @Override
    public void save(Account account) {

    }
    @Override
    public void update(Account account) {

    }
    @Override
    public Account queryAccountByName(String name) {
        return null;
    }
    @Override
    public Account queryAccountById(Integer accountId) {
        return null;
    }
}
