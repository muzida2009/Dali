package com.ericsson.dao;

import com.ericsson.domain.Account;

/**
 * Created by Dali on 2018/1/6.
 */
public interface AccountDao {
    void save(Account account);
    void update(Account account);

    Account queryAccountByName(String name);
    Account queryAccountById(Integer accountId);
}
