package com.basf.newtp.jackson.beans;

import java.util.List;

public class CustomersListBean {
	
	private List<CustomerBean> customers;
	private List<ErrorBean> error;

	public List<CustomerBean> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerBean> customers) {
		this.customers = customers;
	}
	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}
}