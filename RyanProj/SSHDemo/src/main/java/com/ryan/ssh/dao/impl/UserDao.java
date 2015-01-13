package com.hyron.alarmcenter.dao.impl;

import com.hyron.alarmcenter.dao.IUserDao;
import com.hyron.alarmcenter.entity.User;
import com.hyron.commons.dao.common.AbstractHibernateDao;
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

