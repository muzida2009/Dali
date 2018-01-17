package com.ericsson.action;

import com.ericsson.factory.BeanFactory;
import com.ericsson.service.AccountService;
import com.ericsson.service.impl.AccountServiceImpl;
import org.junit.Test;

/**
 * Created by Dali on 2018/1/6.
 */
public class Client {
    @Test
    public void testTransfer(){
//        AccountService accountService = new AccountServiceImpl();
        AccountService accountService = BeanFactory.getAccountService(new AccountServiceImpl());
        String srcName = "aaa";
        String dstName = "bbb";
        accountService.transfer(srcName, dstName,100F);
    }
}
