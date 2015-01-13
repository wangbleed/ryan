package com.hyron.commons.dao.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ryan on 15/1/5.
 * 让所有的DAO都实现基本的操作接口IOperations
 * 除了实现IOperations中的基本操作之外
 * 可以供DAO,SERVICE来实现基本的操作。
 * 特殊的方法，如DAO的攻击查询可以放在特定的DAO的具体类中或指定dao的抽象类；service层的特殊业务逻辑方法放在各自的业务层中
 */
public interface IOperations<T extends Serializable> {

    T findOne(final long id);

    List<T> findAll();

    void create(final T entity);

    T update(final T entity);

    void delete(final T entity);

    void deleteById(final long entityId);

}
