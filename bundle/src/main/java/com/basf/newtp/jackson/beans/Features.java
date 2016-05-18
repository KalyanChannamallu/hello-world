package com.basf.newtp.jackson.beans;

import java.util.List;

public class Features {
	public Boolean getRange() {
		return range;
	}
	public void setRange(Boolean range) {
		this.range = range;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<FeatureValues> getFeatureValues() {
		return featureValues;
	}
	public void setFeatureValues(List<FeatureValues> featureValues) {
		this.featureValues = featureValues;
	}
	public Boolean getComparable() {
		return comparable;
	}
	public void setComparable(Boolean comparable) {
		this.comparable = comparable;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private Boolean range;
	private String name;
	private List<FeatureValues>featureValues;
	private Boolean comparable;
	private String code;


}
