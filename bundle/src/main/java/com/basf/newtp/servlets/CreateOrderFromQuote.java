
package com.basf.newtp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basf.newtp.jackson.beans.ErrorBean;
import com.basf.newtp.jackson.beans.QuoteBean;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.utils.CastIronServiceProperties;
import com.basf.newtp.utils.ErrorConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SlingServlet(paths = { "/apps/basf/order-quote/createOrderFromQuote" })
@Properties({
		@Property(name = "service.pid", value = "com.basf.newtp.servlet.createOrderFromQuote", propertyPrivate = false),
		@Property(name = "service.description", value = "Create Order from Approved Quotes servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "BASF", propertyPrivate = false) })
public class CreateOrderFromQuote extends SlingAllMethodsServlet {

	@Reference
	GetServiceConnection getServiceConnection;
	private static final long serialVersionUID = 2L;
	static final Logger log = LoggerFactory.getLogger("CreateOrderFromQuote");

	@Override
	protected void doGet(SlingHttpServletRequest request,SlingHttpServletResponse response) throws ServletException,IOException {

		
		String quoteNumber = request.getParameter("quotenumber");
		String createOrderPagePath = request.getParameter("orderpagepath"); 
		String serverUrl = CastIronServiceProperties.castironServer;
		String endPoint = CastIronServiceProperties.castironQuoteDetailsEndPoint;
		String urlString = serverUrl + "/" + endPoint;
		StringBuilder input = new StringBuilder();
		input.append("{\"quotenumber\":\"");
		input.append(quoteNumber);
		input.append("\"}");
		String inputJson = input.toString();
		log.info("Calling cast Iron to fetch quote details with input as "+inputJson);
		QuoteBean quoteBean = new QuoteBean();
		ObjectMapper objectMapper = new ObjectMapper();
		ErrorBean errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		String errorResponseString = null;
		try {
			getServiceConnection.invokePost(urlString,inputJson);
			errorResponseString = getServiceConnection.getConnectionError();
			if (StringUtils.isEmpty(errorResponseString)) {
				String orderHeaderData =getServiceConnection.getResponseString();
				log.info("Connection established and provided output response :" +orderHeaderData);
				//String orderHeaderData = "{\r\n    \"quotenumber\": \"12345\",\r\n    \"quotetype\": \"Export Quote\",\r\n    \"competitivemfr\": \"Credit Memo Request\",\r\n    \"location\": \"New Delhi\",\r\n    \"validfrom\": \"02/01/2014\",\r\n    \"validto\": \"04/01/2014\",\r\n    \"notes\": \"Notes Will Go here\",\r\n    \"reasonforquote\": \"Reason for Quote will go here\",\r\n    \"soldto\": {\r\n        \"company\": \"Ferguson (OTC)\",\r\n        \"city\": \"Atlanta\",\r\n        \"addressone\": \"200 Main Street\",\r\n        \"addresstwo\": \"Suite 4\",\r\n        \"state\": \"GA\",\r\n        \"zipcode\": \"30300\"\r\n    },\r\n    \"shipto\": {\r\n        \"company\": \"Ferguson (OTC)\",\r\n        \"city\": \"Atlanta\",\r\n        \"addressone\": \"700 Main Street\",\r\n        \"addresstwo\": \"Suite 6\",\r\n        \"state\": \"GA\",\r\n        \"zipcode\": \"30322\"\r\n    },\r\n    \"products\": [\r\n        {\r\n            \"material\": \"H12-OTC\",\r\n            \"model\": \"CSB8215IFEBNC 100\",\r\n            \"quantity\": \"4\",\r\n            \"price\": \"$200.00\",\r\n            \"loadfactor\": \"ed\",\r\n            \"requestedprice\": \"$190.00\",\r\n            \"competitiveprice\": \"$192.00\",\r\n            \"netvalue\": \"$200.00\",\r\n            \"competitivemodel\": \"ABY-K34\"\r\n        },\r\n        {\r\n            \"material\": \"P15-MTC\",\r\n            \"model\": \"CSB8215IFEBNC 200\",\r\n            \"quantity\": \"2\",\r\n            \"price\": \"$300.00\",\r\n            \"loadfactor\": \"ed\",\r\n            \"requestedprice\": \"$270.00\",\r\n            \"competitiveprice\": \"$282.00\",\r\n            \"netvalue\": \"$300.00\",\r\n            \"competitivemodel\": \"KANS-AK47\"\r\n        }\r\n    ]\r\n}";
				quoteBean = objectMapper.readValue(orderHeaderData,QuoteBean.class);
				request.getSession().setAttribute("quoteBean", quoteBean);
				response.sendRedirect(createOrderPagePath+".html?quotenumber="+ request.getParameter("quotenumber"));
				// Checking if error bean is set. This will be set if Cast Iron service fails
				if (null != quoteBean.getError()&& quoteBean.getError().size() > 0) {
					for (ErrorBean error : quoteBean.getError()) {
						if (!StringUtils.isEmpty(error.getFaultmessage()) || !StringUtils.isEmpty(error.getFaultname())) {
							// Setting custom error message in case Cast Iron service fails
							error.setErrormessage(ErrorConstants.ERROR_QUOTETOORDER_MESSAGE);
						}
					}
				}
			} else {
				// Setting generic error message in case of service connection failure
				log.error("connection not established");
				errorBean.setErrormessage(errorResponseString);
				errorBeanList.add(errorBean);
				quoteBean.setError(errorBeanList);
			}
		} catch (JsonParseException jpExp) {
			log.error("Error while parsing Json :" + jpExp);
			// Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTETOORDER_MESSAGE);
			errorBeanList.add(errorBean);
			quoteBean.setError(errorBeanList);
		} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			// Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTETOORDER_MESSAGE);
			errorBeanList.add(errorBean);
			quoteBean.setError(errorBeanList);
		} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			// Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTETOORDER_MESSAGE);
			errorBeanList.add(errorBean);
			quoteBean.setError(errorBeanList);
		} catch (Exception exception) {
			log.error("Generic Exception while parsing Json:" + exception);
			// /Setting custom error message
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTETOORDER_MESSAGE);
			errorBeanList.add(errorBean);
			quoteBean.setError(errorBeanList);
		}

	}

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

	}

}
