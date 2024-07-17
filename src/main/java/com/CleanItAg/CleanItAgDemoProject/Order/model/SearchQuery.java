package com.CleanItAg.CleanItAgDemoProject.Order.model;

public class SearchQuery {
	private String query;

	public SearchQuery() {
		super();
	}

	public SearchQuery(String query) {
		super();
		this.query = query.strip();
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query.strip();
	}
		
}
