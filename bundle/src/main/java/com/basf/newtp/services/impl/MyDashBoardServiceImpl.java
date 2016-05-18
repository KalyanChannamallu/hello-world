package com.basf.newtp.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basf.newtp.jackson.beans.ErrorBean;
import com.basf.newtp.jackson.beans.OrdersListBean;
import com.basf.newtp.jackson.beans.QuotesListBean;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.services.api.MyDashBoardService;
import com.basf.newtp.utils.CastIronServiceProperties;
import com.basf.newtp.utils.ErrorConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Service
@Properties({
		@Property(name = "service.pid", value = "com.aosmith.portal.orderquote.dashboard.MyDashBoardImpl"),
		@Property(name = "service.description", value = "MyDashBoardImpl"),
		@Property(name = "service.vendor", value = "AO Smith") })
public class MyDashBoardServiceImpl implements MyDashBoardService {

	@Reference
	GetServiceConnection getServiceConnection;
	static final Logger log = LoggerFactory.getLogger(MyDashBoardServiceImpl.class);
	ObjectMapper objectMapper = null;
	Error error = null;

	/* Method for fetching Order from SAP */
	public OrdersListBean fetchMyOrders(String userId, String userGroup) {
	
		String serverUrl = CastIronServiceProperties.castironServer; 
		String endPoint = CastIronServiceProperties.castironMyOrdersEndPoint;
		String urlString = serverUrl + "/" + endPoint;
		StringBuilder input=new StringBuilder();
		input.append("{\"userid\":\"");
		input.append(userId);
		input.append("\",\"usergroup\":\"");
		input.append(userGroup);
		input.append("\"}");
		String inputJson = input.toString();
		OrdersListBean orderOutput =new OrdersListBean();
		objectMapper = new ObjectMapper();
		ErrorBean errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		String errorResponseString = null;
		try {
			getServiceConnection.invokePost(urlString,inputJson); 
			errorResponseString = getServiceConnection.getConnectionError();
			if(StringUtils.isEmpty(errorResponseString)){
				String orderHeaderData = getServiceConnection.getResponseString();
	//			String orderHeaderData = "{\r\n    \"orders\": [\r\n        {\r\n            \"ordernumber\": \"10201201\",\r\n            \"customerpo\": \"ZZZO-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"001\",\r\n                \"company\": \"Great Company Inc.\",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"GA\",\r\n                \"zipcode\": \"30300\"\r\n            },\r\n            \"total\": \"$5,750.41\",\r\n            \"date\": \"09/02/14\",\r\n            \"status\": \"Rejected\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"2012012\",\r\n            \"customerpo\": \"ABV-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"002\",\r\n                \"company\": \"ACME Co.\",\r\n                \"city\": \"Alanta\",\r\n                \"addressone\": \"300\",\r\n                \"addresstwo\": \"Second Street\",\r\n                \"state\": \"NW\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,75034.41\",\r\n            \"date\": \"12/02/14\",\r\n            \"status\": \"Pending\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"123345\",\r\n            \"customerpo\": \"XYX-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"003\",\r\n                \"company\": \"PCO Inc. \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,7530.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Shipped\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"123345\",\r\n            \"customerpo\": \"00099-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"004\",\r\n                \"company\": \"Howdy Folks LLC (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,75450.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Approved\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"8768\",\r\n            \"customerpo\": \"CBDB-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"005\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,72350.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Shipped\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"435345\",\r\n            \"customerpo\": \"QQQQ00-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"006\",\r\n                \"company\": \"RowdyFolks \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"300\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,75340.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Approved\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"324768678\",\r\n            \"customerpo\": \"WEWQ-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"007\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,75340.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"213768678\",\r\n            \"customerpo\": \"DF-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"008\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,74450.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"213768678\",\r\n            \"customerpo\": \"SDF-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"009\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,7530.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"76867832434\",\r\n            \"customerpo\": \"SDF-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"010\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,75430.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\"\r\n        },\r\n        {\r\n            \"ordernumber\": \"768234678\",\r\n            \"customerpo\": \"FGH-1234\",\r\n            \"shipto\": {\r\n                \"id\": \"011\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,7530.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\"\r\n        }\r\n    ]\r\n}";
				orderOutput =  objectMapper.readValue(orderHeaderData, OrdersListBean.class);
				//Checking if error bean is set. This will be set if Cast Iron service fails
				if(null != orderOutput.getError() && orderOutput.getError().size() > 0){
					for(ErrorBean error: orderOutput.getError()){
						if(!StringUtils.isEmpty(error.getFaultmessage()) || !StringUtils.isEmpty(error.getFaultname())){
							//Setting custom error message in case Cast Iron service fails
							error.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
						}
					}
				}
			}else{
				//Setting generic error message in case of service connection failure
				errorBean.setErrormessage(errorResponseString);
				errorBeanList.add(errorBean);
				orderOutput.setError(errorBeanList);
			} 
		} catch (JsonParseException jpExp) {
			log.error("Error while parsing Json :" + jpExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			orderOutput.setError(errorBeanList);
		} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			orderOutput.setError(errorBeanList);
		} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			orderOutput.setError(errorBeanList);
		}catch (Exception exception) {
			log.error("Generic Exception while parsing Json:" + exception);
			///Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			orderOutput.setError(errorBeanList);
		} 
		return orderOutput;
	} 

	/* Method for fetching Quote from SAP */
	public QuotesListBean fetchMyQuotes(String userId, String userGroup) {
		
		String serverUrl = CastIronServiceProperties.castironServer; 
		String endPoint = CastIronServiceProperties.castironMyQuotesEndPoint;
		String urlString = serverUrl + "/" + endPoint;
		StringBuilder input=new StringBuilder();
		input.append("{\"userid\":\"");
		input.append(userId);
		input.append("\",\"usergroup\":\"");
		input.append(userGroup);
		input.append("\"}");
		String inputJson = input.toString();
		QuotesListBean quoteOutput = new QuotesListBean();
		objectMapper = new ObjectMapper();
		ErrorBean errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		String errorResponseString = null;
		try {
			//getServiceConnection.invokePost(urlString,inputJson); 
			errorResponseString = getServiceConnection.getConnectionError();
			if(StringUtils.isEmpty(errorResponseString)){
				//String quoteHeaderData = getServiceConnection.getResponseString();
				String quoteHeaderData = "{\r\n    \"quotes\": [\r\n        {\r\n            \"quotenumber\": \"10201201\",\r\n            \"shipto\": {\r\n                \"id\": \"001\",\r\n                \"company\": \"Great Company Inc.\",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"GA\",\r\n                \"zipcode\": \"30300\"\r\n            },\r\n            \"total\": \"$5,750.41\",\r\n            \"date\": \"09/02/14\",\r\n            \"status\": \"Rejected\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to one reason\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"2012012\",\r\n            \"shipto\": {\r\n                \"id\": \"002\",\r\n                \"company\": \"ACME Co.\",\r\n                \"city\": \"Alanta\",\r\n                \"addressone\": \"300\",\r\n                \"addresstwo\": \"Second Street\",\r\n                \"state\": \"NW\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"12/02/14\",\r\n            \"status\": \"Pending\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to incompatibility\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"123345\",\r\n            \"shipto\": {\r\n                \"id\": \"003\",\r\n                \"company\": \"PCO Inc. \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Shipped\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to incompatibility\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"123345\",\r\n            \"shipto\": {\r\n                \"id\": \"004\",\r\n                \"company\": \"Howdy Folks LLC (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Approved\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to incompatibility\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"8768\",\r\n            \"shipto\": {\r\n                \"id\": \"005\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Shipped\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to incompatibility\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"435345\",\r\n            \"shipto\": {\r\n                \"id\": \"006\",\r\n                \"company\": \"RowdyFolks \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"300\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Approved\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to first reason\"\r\n        },\r\n        {\r\n            \"quotenumber\": \"768678\",\r\n            \"shipto\": {\r\n                \"id\": \"007\",\r\n                \"company\": \"Ferguson (OTC) \",\r\n                \"city\": \"Atlanta\",\r\n                \"addressone\": \"200\",\r\n                \"addresstwo\": \"Main Street\",\r\n                \"state\": \"Atlanta\",\r\n                \"zipcode\": \"234200\"\r\n            },\r\n            \"total\": \"$,750.41\",\r\n            \"date\": \"09/01/14\",\r\n            \"status\": \"Rejected\",\r\n            \"quotestatusmessage\": \"Your order was rejected due to another reason\"\r\n        }\r\n    ]\r\n}";
				quoteOutput = objectMapper.readValue(quoteHeaderData,QuotesListBean.class);
				if(null != quoteOutput.getError()){
					for(ErrorBean error: quoteOutput.getError()){
						if(!StringUtils.isEmpty(error.getFaultmessage()) || !StringUtils.isEmpty(error.getFaultname())){
							error.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
						}
					}
				}
			}else{
				errorBean.setErrormessage(errorResponseString);
				errorBeanList.add(errorBean);
				quoteOutput.setError(errorBeanList);
			} 
			} catch (JsonParseException jpExp) {
			log.error("Error while parsing Json :" + jpExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			quoteOutput.setError(errorBeanList);
			} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			quoteOutput.setError(errorBeanList);
			} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			//Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			quoteOutput.setError(errorBeanList);
			} catch (Exception exception) {
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			quoteOutput.setError(errorBeanList);
		}
	return quoteOutput;
	} 
}