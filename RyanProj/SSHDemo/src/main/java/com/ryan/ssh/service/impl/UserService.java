package com.hyron.alarmcenter.service.impl;

import com.hyron.alarmcenter.dao.IUserDao;
import com.hyron.alarmcenter.entity.User;
import com.hyron.alarmcenter.service.IUserService;
import com.hyron.alarmcenter.service.common.AbstractService;
import com.hyron.commons.dao.common.IOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ryan on 15/1/5.
 */
@Service("userService")
public class UserService extends AbstractService<User> implements IUserService {

    @Autowired
    private IUserDao dao;

    public UserService() {
        super();
    }

    @Override
    protected IOperations<User> getDao() {
        return this.dao;
    }
}