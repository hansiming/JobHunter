package com.cszjo.jobhunter.dao;

import com.cszjo.jobhunter.model.Users;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Han on 2017/3/5.
 */
@Repository
public interface UserDao {

    int userNameIsExists(String userName);

    Users login(@Param("userName") String userName,@Param("password") String password);
}
