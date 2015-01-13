package com.ryan.ssh.dao.impl;

import com.ryan.ssh.dao.IUserDao;
import com.ryan.ssh.entity.User;
import com.ryan.commons.dao.common.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by ryan on 15/1/5.
 */
@Repository("userDao")
public class UserDao extends AbstractHibernateDao<User> implements IUserDao{

    public UserDao() {
        super();

        setClazz(User.class);
    }
}

