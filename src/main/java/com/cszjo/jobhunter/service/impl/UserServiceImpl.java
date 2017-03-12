package com.cszjo.jobhunter.service.impl;

import com.cszjo.jobhunter.dao.UserDao;
import com.cszjo.jobhunter.model.BaseResponse;
import com.cszjo.jobhunter.model.LoginResponse;
import com.cszjo.jobhunter.model.ResponseInfo;
import com.cszjo.jobhunter.model.ResponseStatus;
import com.cszjo.jobhunter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Han on 2017/3/5.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("loginResponse")
    private LoginResponse response;

    @Autowired
    private UserDao userDao;

    @Override
    public LoginResponse login(String userName, String password) {

        //username is not exists
        if(userDao.userNameIsExists(userName) == 0) {
            response.setStatus(ResponseStatus.FAIL);
            response.setInfo(ResponseInfo.USER_NAME_NOT_EXISTS);
            return response;
        }

        if(userDao.login(userName, password) == null) {
            response.setStatus(ResponseStatus.FAIL);
            response.setInfo(ResponseInfo.PASSWORD_ERROR);
            return response;
        }

        response.setStatus(ResponseStatus.SUCCESS);
        response.setInfo(ResponseInfo.SUCCESS);
        return response;
    }
}
