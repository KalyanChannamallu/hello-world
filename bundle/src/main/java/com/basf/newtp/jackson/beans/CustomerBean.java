package com.basf.newtp.jackson.beans;

import java.util.List;

public class CustomerBean {
	
	private String brand;
	private String customernumber;
	private String customername;
	private SoldToBean soldto;
	private List<ShipToBean> shipto;
	private String salesorg;
	private String city;
	private String state;	
	private String zipcode;
	private String distributionchannel;
	private String producthierachy;
	private String division;
	private String userid;
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCustomernumber() {
		return customernumber;
	}
	public void setCustomernumber(String customernumber) {
		this.customernumber = customernumber;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public SoldToBean getSoldto() {
		return soldto;
	}
	public void setSoldto(SoldToBean soldto) {
		this.soldto = soldto;
	}
	public List<ShipToBean> getShipto() {
		return shipto;
	}
	public void setShipto(List<ShipToBean> shipto) {
		this.shipto = shipto;
	}
	public String getSalesorg() {
		return salesorg;
	}
	public void setSalesorg(String salesorg) {
		this.salesorg = salesorg;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
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
	
}