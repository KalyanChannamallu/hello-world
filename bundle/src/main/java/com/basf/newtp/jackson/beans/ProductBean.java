package com.basf.newtp.jackson.beans;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductBean {
	private String materialNumber;
	private String material;
	private List<Classifications>classifications;
	private String modelNumber;
	private String model;
	private String code;
	private String price;
	private String notes;
	private String quantity;
	private String loadfactor;
	private String requestedprice;
	private String competitiveprice;
	private String netvalue;
	private String competitivemodel;
	private List<ErrorBean> error;
	
	public String getMaterialNumber() {
		return materialNumber;
	}
	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	public List<Classifications> getClassifications() {
		return classifications;
	}
	public void setClassifications(List<Classifications> classifications) {
		this.classifications = classifications;
	}
	public String getModelNumber() {
		return modelNumber;
	}
	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getLoadfactor() {
		return loadfactor;
	}
	public void setLoadfactor(String loadfactor) {
		this.loadfactor = loadfactor;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRequestedprice() {
		return requestedprice;
	}
	public void setRequestedprice(String requestedprice) {
		this.requestedprice = requestedprice;
	}
	public String getCompetitiveprice() {
		return competitiveprice;
	}
	public void setCompetitiveprice(String competitiveprice) {
		this.competitiveprice = competitiveprice;
	}
	public String getNetvalue() {
		return netvalue;
	}
	public void setNetvalue(String netvalue) {
		this.netvalue = netvalue;
	}
	public String getCompetitivemodel() {
		return competitivemodel;
	}
	public void setCompetitivemodel(String competitivemodel) {
		this.competitivemodel = competitivemodel;
	}
	public List<ErrorBean> getError() {
		return error;
	}
	public void setError(List<ErrorBean> error) {
		this.error = error;
	}

}
