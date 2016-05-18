package com.basf.newtp.jackson.beans;

import java.util.List;

public class OrdersListBean {

	private List<OrderBean> orders;
	private List<ErrorBean> error;

	
	public List<OrderBean> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderBean> orders) {
		this.orders = orders;
	}

	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}
}