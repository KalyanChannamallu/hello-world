package com.basf.newtp.jackson.beans;

import java.util.List;

public class QuotesListBean {

	private List<QuoteBean> quotes;
	private List<ErrorBean> error;
	

	public List<QuoteBean> getQuotes() {
		return quotes;
	}

	public List<ErrorBean> getError() {
		return error;
	}

	public void setError(List<ErrorBean> error) {
		this.error = error;
	}

	public void setQuotes(List<QuoteBean> quotes) {
		this.quotes = quotes;
	}
}