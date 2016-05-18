package com.basf.newtp.services.api;

import com.basf.newtp.jackson.beans.CustomersListBean;
import com.basf.newtp.jackson.beans.ProductsListBean;
import com.basf.newtp.jackson.beans.TreeOutput;

public interface AjaxCallsService {
	
	public CustomersListBean getCustomerResults(String inputJson);
	public ProductsListBean getPartsResults(String inputParameter);
	public ProductsListBean getPartsFilterResults(String inputParameter);
	public TreeOutput getFilterTree(String inputParameter);
	public ProductsListBean getProductAttributes(String inputParameter);
}
