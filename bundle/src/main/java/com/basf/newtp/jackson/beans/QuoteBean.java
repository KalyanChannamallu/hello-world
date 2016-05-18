package com.basf.newtp.jackson.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteBean {
	
	
	private String quotenumber;
	private ShipToBean shipto;
	private SoldToBean soldto;
	private String competitivemfr;
	private String location;
	private String informationsource;
	private String total;
	private String date;
	private String status;
	private String quotetype;
	private String validfrom;
	private String validto;
	private String reasonforquote;
	private String notes;
	private String quotestatusmessage;
	private List<ProductBean> products;
	private String quotestatus;
	private List<ErrorBean> error;
	
	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}	
	
	
	public String getQuotenumber() {
		return quotenumber;
	}
	public void setQuotenumber(String quotenumber) {
		this.quotenumber = quotenumber;
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
	public String getQuotestatusmessage() {
		return quotestatusmessage;
	}
	public void setQuotestatusmessage(String quotestatusmessage) {
		this.quotestatusmessage = quotestatusmessage;
	}
	public String getQuotestatus() {
		return quotestatus;
	}
	public void setQuotestatus(String quotestatus) {
		this.quotestatus = quotestatus;
	}

	public SoldToBean getSoldto() {
		return soldto;
	}

	public void setSoldto(SoldToBean soldto) {
		this.soldto = soldto;
	}

	public String getCompetitivemfr() {
		return competitivemfr;
	}

	public void setCompetitivemfr(String competitivemfr) {
		this.competitivemfr = competitivemfr;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInformationsource() {
		return informationsource;
	}

	public void setInformationsource(String informationsource) {
		this.informationsource = informationsource;
	}

	public String getQuotetype() {
		return quotetype;
	}

	public void setQuotetype(String quotetype) {
		this.quotetype = quotetype;
	}

	public String getValidfrom() {
		return validfrom;
	}

	public void setValidfrom(String validfrom) {
		this.validfrom = validfrom;
	}

	public String getValidto() {
		return validto;
	}

	public void setValidto(String validto) {
		this.validto = validto;
	}

	public String getReasonforquote() {
		return reasonforquote;
	}

	public void setReasonforquote(String reasonforquote) {
		this.reasonforquote = reasonforquote;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
}