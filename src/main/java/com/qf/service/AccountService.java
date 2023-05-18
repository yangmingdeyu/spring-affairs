package com.qf.service;

public interface AccountService {

    /**
     * 转装功能, 谁向谁转装,转账多少
     * @param id1
     * @param id2
     * @param money
     */
    void transf(long id1,long id2,int money);
}
