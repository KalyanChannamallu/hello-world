package com.basf.newtp.jackson.beans;

import java.util.List;

public class OrderBean {
	
	
	private String ordernumber;
	private String customerpo;
	private ShipToBean shipto;
	private String total;
	private String date;
	private String status;
	private String ordertype;
	private String estimateddeliverydate;
	private String requesteddeliverydate;
	private String notes;
	private SoldToBean soldto;
	private List<ProductBean> products;
	private String orderstatus;
	private String message;
	private List<ErrorBean> error;
	private String brand;
	private String distributionchannel;
	private String producthierachy;
	private String division;
	
	public String getDistributionchannel() {
		return distributionchannel;
	}

	public void setDistributionchannel(String distributionchannel) {
		this.distributionchannel = distributionchannel;
	}

	public String getProducthierachy() {
		return producthierachy;
	}

	public void setProducthierachy(String producthierachy) {
		this.producthierachy = producthierachy;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}


	
	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}	
	
	
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getCustomerpo() {
		return customerpo;
	}
	public void setCustomerpo(String customerpo) {
		this.customerpo = customerpo;
	}
	public ShipToBean getShipto() {
		return shipto;
	}
	public void setShipto(ShipToBean shipto) {
		this.shipto = shipto;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getEstimateddeliverydate() {
		return estimateddeliverydate;
	}
	public void setEstimateddeliverydate(String estimateddeliverydate) {
		this.estimateddeliverydate = estimateddeliverydate;
	}
	public String getRequesteddeliverydate() {
		return requesteddeliverydate;
	}
	public void setRequesteddeliverydate(String requesteddeliverydate) {
		this.requesteddeliverydate = requesteddeliverydate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public SoldToBean getSoldto() {
		return soldto;
	}
	public void setSoldto(SoldToBean soldto) {
		this.soldto = soldto;
	}
	public List<ProductBean> getProducts() {
		return products;
	}
	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}