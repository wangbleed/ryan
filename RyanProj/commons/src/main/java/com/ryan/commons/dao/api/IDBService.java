package com.ryan.commons.dao.api;

import com.ryan.commons.dao.ExtSqlProp;
import com.ryan.commons.dao.SearchFilter;
import com.ryan.commons.entity.BaseEntity;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ryan on 15/10/30.
 * 数据层Api
 * cls为指定的用户pojo的class
 * code为服务提供者给消费者的一个code，用于数据层的权限、访问等一系列的认证
 * object为具体某个pojo的对象
 */
public interface IDBService<T extends BaseEntity> {

    /**
     * 获取
     * @param cls
     * @param code
     * @param id 主键
     * @return
     */
    public ApiResult<T> getObject(Class cls, @NotNull String code, @NotNull Long id);

    /**
     * 保存记录
     * @param cls
     * @param code
     * @param obj 对象
     * @return
     */
    public ApiResult<T> saveObject(Class cls, @NotNull String code, @NotNull Object obj);

    /**
     * 更新记录
     * @param cls
     * @param code
     * @param obj 对象
     * @return
     */
    public ApiResult<T> updateObject(Class cls, @NotNull String code, @NotNull Object obj);

    /**
     * 删除记录
     * @param cls
     * @param code
     * @param obj 对象
     * @return
     */
    public ApiResult<T> deleteObject(Class cls, @NotNull String code, @NotNull Object obj);

    /**
     * 获取列表
     * @param cls
     * @param searchFilters 搜索条件
     * @param sqlProp       排序、分组
     * @param pageNum       页数
     * @param pageCount     每页数
     * @return
     */
    public ApiResult<T> findBy(Class cls, @NotNull String code, List<SearchFilter> searchFilters,
                            ExtSqlProp sqlProp, int pageNum, int pageCount);


    /**
     * 获取所有列表
     * @param cls
     * @param searchFilters 搜索条件
     * @param sqlProp       排序、分组
     * @return
     */
    public ApiResult<T> findAll(Class cls, @NotNull String code, List<SearchFilter> searchFilters,
                             ExtSqlProp sqlProp);

    /**
     * 去重
     * @param cls
     * @param searchFilters 搜索条件
     * @param sqlProp       排序、分组
     * @param pageNum       页数
     * @param pageCount     每页数
     * @return
     */
    public ApiResult<T> distinctBy(Class cls, @NotNull String code, List<SearchFilter> searchFilters,
                                ExtSqlProp sqlProp, int pageNum, int pageCount);

    /**
     * 去重
     * @param cls
     * @param searchFilters 搜索条件
     * @param sqlProp       排序、分组
     * @return
     */
    public ApiResult<T> distinct(Class cls, @NotNull String code, List<SearchFilter> searchFilters,
                              ExtSqlProp sqlProp);
}
