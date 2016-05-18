package com.basf.newtp.services.api;

import com.basf.newtp.jackson.beans.OrdersListBean;
import com.basf.newtp.jackson.beans.QuotesListBean;


public interface MyDashBoardService {
	
	public OrdersListBean fetchMyOrders (String userID,String userGroup);
	
	
	public QuotesListBean fetchMyQuotes (String userID,String userGroup);
	
	
}
