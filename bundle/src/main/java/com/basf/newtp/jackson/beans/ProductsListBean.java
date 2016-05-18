package com.basf.newtp.jackson.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductsListBean {
	private List<ProductBean> products;
	private List<ErrorBean> error;

	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
}