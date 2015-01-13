package com.ryan.ssh.service.impl;

import com.ryan.ssh.dao.IUserDao;
import com.ryan.ssh.entity.User;
import com.ryan.ssh.service.IUserService;
import com.ryan.ssh.service.common.AbstractService;
import com.ryan.commons.dao.common.IOperations;
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