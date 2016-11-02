package com.ryan.commons.entity;

import com.ryan.commons.constant.Global;

import java.util.List;

/**
 * 页面的工具类
 * @author Ryan
 * @param <T>
 */
public class Page<T> extends BaseEntity{
	
	private int pageIndex = 1;
	private int pageSize = Global.Default_Page_Size;
	private long total = 0;
	private List<T> result;
	private String forwordName;

	public Page() {
	}

	public Page(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
	}

	public Page(int pageIndex, int pageSize, long total, List<T> result) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
		this.result = result;
	}

	public Page(int pageIndex, int pageSize, long total, List<T> result, String forwordName) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.total = total;
		this.result = result;
		this.forwordName = forwordName;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public Page<T> setPageIndex(int pageIndex) {
		if (pageIndex > 0)
			this.pageIndex = pageIndex;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public Page<T> setPageSize(int pageSize) {
		if (pageSize > 0)
			this.pageSize = pageSize;
		return this;
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	public List<T> getResult() {
		return result;
	}

	public Page<T> setResult(List<T> result) {
		this.result = result;
		return this;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPageCount() {
		long pageCount = 0;
		if (total > 0) {
			if (total % pageSize == 0)
				pageCount = total % pageSize;
			else
				pageCount = total / pageSize + 1;
		}
		return pageCount;
	}

	public boolean isHasNext() {
		return getPageCount() > pageIndex;
	}

	public long getNextPage() {
		if (isHasNext()) {
			return pageIndex + 1;
		} else {
			return pageIndex;
		}
	}

	public boolean isHasPrevious() {
		return pageIndex - 1 > 0;
	}

	public long getPreviousPage() {
		if (isHasPrevious()) {
			return pageIndex - 1;
		} else {
			return pageIndex;
		}
	}

	public String getForwordName() {
		return forwordName;
	}

	public void setForwordName(String forwordName) {
		this.forwordName = forwordName;
	}
}
