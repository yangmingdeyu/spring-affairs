package com.qf.service.impl;

import com.qf.dao.AccoutnMapper;
import com.qf.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccoutnMapper accoutnMapper;
    @Override
    public void transf(long id1, long id2, int money) {
        System.out.println("正在进行转账");
        accoutnMapper.update(id1,-money);
        // int i = 1/0;
        accoutnMapper.update(id2,money);
        System.out.println("转账成功");
    }

    public void setAccoutnMapper(AccoutnMapper accoutnMapper) {
        this.accoutnMapper = accoutnMapper;
    }
}
