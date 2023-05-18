package com.qf.service.impl;

import com.qf.dao.AccoutnMapper;
import com.qf.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class AccountServiceImplTest {

    @Test
    public void transf() {
        //转账功能的实现
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        AccountServiceImpl accountService = context.getBean(AccountServiceImpl.class);
        accountService.transf(1,2,100);
        // accountService.transf(2,1,100);

    }
}