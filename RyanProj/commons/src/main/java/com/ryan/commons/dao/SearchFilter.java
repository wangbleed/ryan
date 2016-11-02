package com.ryan.commons.dao;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 实现查询的扩展类
 * @author Ryan
 */
public class SearchFilter {

	//TODO 操作符
	//TODO 当类型为IN时，所对应的value为：('x','x','x')，各种变量值
	public enum Operator {
		EQ, GT, LT, GTE, LTE, LEFT_LIKE, RIGHT_LIKE, LIKE
//		, IN
	}

	//TODO 条件连接符
	public enum Link {
		AND, OR
	}

	public String fieldName;
	public Object value;
	public Operator operator;
	public Link link = Link.AND;
	public boolean bLBracket = false;
	public boolean bRBracket = false;

	/**
	 * 默认连接符为 and
	 * @param fieldName
	 * @param operator
	 * @param value
	 */
	public SearchFilter(String fieldName, Operator operator, Object value) {
		this(fieldName, operator, value, Link.AND);
		this.link = Link.AND;
	}

	public SearchFilter(String fieldName, Operator operator, Object value, Link link){
		this.fieldName = fieldName;
		this.operator = operator;
		this.value = value;
		this.link = link;
	}

	/**
	 * 拼接条件
	 * @param fieldName 字段名
	 * @param operator 	操作符
	 * @param value		值
	 * @param link     	And/Or 	   条件1与条件2之前的连接符取决于条件2的link
	 * @param bLBracket 是否 左括号（位置在条件之前）
	 * @param bRBracket	是否 右括号（位置在条件之后）
	 */
	public SearchFilter(String fieldName, Operator operator, Object value,
						Link link, boolean bLBracket, boolean bRBracket){
		this(fieldName, operator, value, link);
		this.bLBracket = bLBracket;
		this.bRBracket = bRBracket;
	}

	public static Map<String, SearchFilter> parse(Map<String, Object> filterParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : filterParams.entrySet()) {
			String[] names = StringUtils.split(entry.getKey(), "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(entry.getKey() + " is not a valid search filter name");
			}
			SearchFilter filter = new SearchFilter(names[1], Operator.valueOf(names[0]), entry.getValue());
			filters.put(filter.fieldName, filter);
		}

		return filters;
	}
}
