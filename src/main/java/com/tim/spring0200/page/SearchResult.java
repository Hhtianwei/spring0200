package com.tim.spring0200.page;

import java.util.List;

public class SearchResult<T> {
	private List<T> results;
	private Pagination pagination;
	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> results) {
		this.results = results;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	
}
