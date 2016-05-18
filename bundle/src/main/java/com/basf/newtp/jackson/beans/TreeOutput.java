package com.basf.newtp.jackson.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TreeOutput {
	
	private String id;
	private String name;
	private String url;
	private List<Category> subcategories;
	private List<ErrorBean> error;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Category> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}
	public List<ErrorBean> getError() {
		return error;
	}
	public void setError(List<ErrorBean> error) {
		this.error = error;
	}
}
