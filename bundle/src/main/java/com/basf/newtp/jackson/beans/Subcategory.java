package com.basf.newtp.jackson.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subcategory {

	private String id;
	private String name;
	private String url;
	private List<SubcategoryLevel1> subcategories;
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
	public List<SubcategoryLevel1> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<SubcategoryLevel1> subcategories) {
		this.subcategories = subcategories;
	}
}
