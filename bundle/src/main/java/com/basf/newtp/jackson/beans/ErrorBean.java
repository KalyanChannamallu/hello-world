package com.basf.newtp.jackson.beans;

public class ErrorBean {

	private String service;
	private String faultname;
	private String faultmessage;
	private String faulttime;
	private String errormessage;
	
	public String getFaultname() {
		return faultname;
	}
	public void setFaultname(String faultname) {
		this.faultname = faultname;
	}
	public String getFaultmessage() {
		return faultmessage;
	}
	public void setFaultmessage(String faultmessage) {
		this.faultmessage = faultmessage;
	}
	public String getFaulttime() {
		return faulttime;
	}
	public void setFaulttime(String faulttime) {
		this.faulttime = faulttime;
	}
	public String getErrormessage() {
		return errormessage;
	}
	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
}
