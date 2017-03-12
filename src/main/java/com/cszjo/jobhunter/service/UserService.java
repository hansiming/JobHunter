package com.cszjo.jobhunter.service;

import com.cszjo.jobhunter.model.LoginResponse;

/**
 * Created by Han on 2017/3/5.
 */
public interface UserService {

    LoginResponse login(String userName, String password);
}
