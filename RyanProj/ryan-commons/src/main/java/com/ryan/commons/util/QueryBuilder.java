package com.ryan.commons.util;

import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * date: 13-2-25 上午10:00
 * 
 * @author:clong
 */
public class QueryBuilder {
	private static final Pattern AND_PATTERN = Pattern.compile("&");

	private final String path;
	private Map<String, Object> queries = new LinkedHashMap<String, Object>();

	private QueryBuilder(String path) {
		if (path == null) {
			throw new RuntimeException("path can't be null");
		}
		this.path = path;
	}

    public void setQueryMap(Map<String,Object> map){
        this.queries = map;
    }

	public static QueryBuilder newInstance(String path) {
		return new QueryBuilder(path);
	}

	public QueryBuilder addQuery(String query, Object value) {
		if (query != null && value != null && hasContent(value)) {
			queries.put(query, value);
		}

		return this;
	}

	public String getPathWithQuery() {
		Set<Map.Entry<String, Object>> entrySet = queries.entrySet();

		StringBuilder pathAndQueries = new StringBuilder(path);

		for (Map.Entry<String, Object> entry : entrySet) {
			String queryName = entry.getKey();
			Object queryValue = entry.getValue();
            if(queryValue != null){
				queryValue = getValue(queryValue);
				pathAndQueries.append("&").append(queryName).append("=").append(queryValue);
            }

		}

		Matcher m = AND_PATTERN.matcher(pathAndQueries.toString());

		return m.replaceFirst("?");

	}



	public String paramsToString() {
		Set<Map.Entry<String, Object>> entrySet = queries.entrySet();
		StringBuilder pathAndQueries = new StringBuilder();

		for (Map.Entry<String, Object> entry : entrySet) {
			pathAndQueries.append("&").append(entry.getKey()).append("=").append(entry.getValue());
		}

		Matcher m = AND_PATTERN.matcher(pathAndQueries.toString());
		return m.replaceFirst("?");
	}

	private boolean hasContent(Object value){
		if(null == value){
			return false;
		}

		if(value instanceof String){
			String str = (String)value;

			return StringUtils.hasText(str);

		}

		return true;
	}

	private Object getValue(Object queryValue) {
		if(queryValue instanceof Byte[]){
			String temp = "";
			Byte[] array = (Byte[])queryValue;
			for(Byte bte : array)
				temp += bte + ",";
			queryValue = temp.substring(0, temp.length() - 1);
		}
		return queryValue;
	}

}
