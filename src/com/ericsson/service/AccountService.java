package com.ericsson.service;

import com.ericsson.domain.Account;

/**
 * Created by Dali on 2018/1/6.
 */
public interface AccountService {
    void transfer(String srcName, String dstName, float money);

    void save(Account account);
    void update(Account account);

    Account queryAccountByName(String name);
    Account queryAccountById(Integer accountId);
}
