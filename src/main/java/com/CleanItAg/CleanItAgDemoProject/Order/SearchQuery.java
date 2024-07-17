package com.CleanItAg.CleanItAgDemoProject.Order;

public class SearchQuery {
	private String query;

	public SearchQuery() {
		super();
		// TODO Auto-generated constructor stub
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
