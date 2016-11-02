package com.ryan.commons.dao;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ryan on 15/6/5.
 * 所有的FieldName，都以orm映射的实体bean中的成员变量名
 * 该类实现排序、分组、内外联等
 * v1.0 实现排序
 */
public class ExtSqlProp {

    public enum FieldSortType{
        asc, desc
    }

    private LinkedHashSet<String> distinctField = Sets.newLinkedHashSet();
    //设置排序字段
    private LinkedHashMap<String, FieldSortType> orderBy = Maps.newLinkedHashMap();
    //设置分组字段
    private LinkedHashSet<String> groupBy = Sets.newLinkedHashSet();

    private void clearMap(Map map){
        map.clear();
    }

    private void clearMapByKey(Map map, String key){
        map.remove(key);
    }

    private void addMapAll(Map map, Map subMap){
        map.clear();
        map.putAll(subMap);
    }

    private void addMapBySingle(Map map, String key, Object value){
        map.put(key, value);
    }

    private void clearSet(Set set){
        set.clear();
    }

    private void clearSetByKey(Set set, String key){
        set.remove(key);
    }

    private void addSetAll(Set set, Set subSet){
        set.clear();
        set.addAll(subSet);
    }

    private void addSetBySingle(Set set, String key){
        set.add(key);
    }

    /**
     * 去重
     * @param fieldName
     */
    public void addDistinctField(String fieldName){
        addSetBySingle(distinctField, fieldName);
    }
    public void addAllDistinctField(LinkedHashSet<String> fieldName){
        addSetAll(distinctField, fieldName);
    }
    public void clearAllDistinctField(){
        clearSet(distinctField);
    }
    public void clearSomeDistinct(String fieldName){
        clearSetByKey(distinctField, fieldName);
    }
    public LinkedHashSet<String> getAllDistinctField(){
        return distinctField;
    }

    /**
     * 获取分组信息
     * @return
     */
    public String getGroupBy(){
        StringBuilder sb = new StringBuilder();
        if(groupBy != null && groupBy.size() > 0){
            for(String field : groupBy){
                if(StringUtils.isNotEmpty(sb.toString())){
                    sb.append(",");
                }
                sb.append(" " + field + " ");
            }
        }
        return sb.toString();
    }

    /**
     * 添加分组字段
     * @param fieldName
     */
    public void addGroupBy(String fieldName){
        addSetBySingle(groupBy, fieldName);
    }

    /**
     * 添加所有的分组字段
     * @param fieldName
     */
    public void addAllGroupBy(LinkedHashSet<String> fieldName){
        addSetAll(groupBy, fieldName);
    }

    /**
     * 移除某个分组
     * @param fieldName
     */
    public void clearSomeGroupBy(String fieldName){
        clearSetByKey(groupBy, fieldName);
    }

    /**
     * 移除所有的分组
     */
    public void clearAllGroupBy(){
        clearSet(groupBy);
    }

    /**
     * 获取排序信息.
     */
    public String getOrderBy() {
        StringBuilder sb = new StringBuilder();
        if(orderBy != null && orderBy.size() > 0){
            for(Map.Entry<String, FieldSortType> entry : orderBy.entrySet()){
                if(StringUtils.isNotEmpty(sb.toString())){
                    sb.append(",");
                }
                sb.append(" "+ entry.getKey()+" " +entry.getValue().toString());
            }
        }
        return sb.toString();
    }

    /**
     * 设置排序
     * @param fieldName		类的成员名
     * @param sortType      desc/asc/""
     */
    public void addOrderBy(String fieldName, FieldSortType sortType){
        addMapBySingle(orderBy, fieldName, sortType);
    }

    /**
     * 清光排序
     */
    public void clearAllOrderBy(){
        clearMap(orderBy);
    }

    /**
     * 移除某个排序
     * @param fieldName
     */
    public void clearSomeOrderBy(String fieldName){
        clearMapByKey(orderBy, fieldName);
    }

    /**
     * 增加所有的排序
     * @param orderByMap
     */
    public void addAllOrderBy(LinkedHashMap<String, FieldSortType> orderByMap){
        addMapAll(orderBy, orderByMap);
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted() {
        return !orderBy.isEmpty();
    }

    /**
     * 分组是否设值
     * @return
     */
    public boolean isGroupBySetted(){
        return !groupBy.isEmpty();
    }

    /**
     * 去重是否设值
     * @return
     */
    public boolean isDistinctBySetted(){
        return !distinctField.isEmpty();
    }
}
