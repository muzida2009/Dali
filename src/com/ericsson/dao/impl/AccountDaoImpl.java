package com.ericsson.dao.impl;

import com.ericsson.dao.AccountDao;
import com.ericsson.domain.Account;
import com.ericsson.utils.TransactionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by Dali on 2018/1/6.
 */
public class AccountDaoImpl implements AccountDao {
    private QueryRunner query = new QueryRunner();
    @Override
    public void save(Account account) {
        String sql = "insert into account (name,money) values (?,?)";
        try {
            int updateedId = this.query.update(TransactionManager.getConnection(), sql, account.getName(), account.getMoney());
            System.out.println(" The inserted id is : " + updateedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override

    public void update(Account account) {
        String sql = "update account set name=? , money=? where id=?";
        try {
            query.update(TransactionManager.getConnection(),sql,account.getName(),account.getMoney(),account.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Account queryAccountByName(String name) {
        String sql = "select * from account where name=?";
        Account newAccount = null;
        try {
            newAccount = this.query.query(TransactionManager.getConnection(),sql, new BeanHandler<Account>(Account.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newAccount;
    }
    @Override
    public Account queryAccountById(Integer accountId) {
        String sql = "select * from account where id=?";
        Account newAccount = null;
        try {
            newAccount = this.query.query(TransactionManager.getConnection(), sql, new BeanHandler<Account>(Account.class), accountId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newAccount;
    }
}
