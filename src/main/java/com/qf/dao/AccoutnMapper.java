package com.qf.dao;

import org.apache.ibatis.annotations.Param;

public interface AccoutnMapper {

    /**
     * 更新用户金钱
     * @param id
     * @param money
     * @return
     */
    int update(@Param("id") long id, @Param("money") int money);
}
