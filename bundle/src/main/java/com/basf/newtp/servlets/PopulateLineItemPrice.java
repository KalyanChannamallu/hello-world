package com.basf.newtp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.basf.newtp.jackson.beans.CustomersListBean;
import com.basf.newtp.jackson.beans.ErrorBean;
import com.basf.newtp.jackson.beans.OrderBean;
import com.basf.newtp.jackson.beans.ProductsListBean;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.utils.CastIronServiceProperties;
import com.basf.newtp.utils.ErrorConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.aos.portal.AOSPortal.connection.util.HttpConnection;

@SlingServlet(paths = { "/apps/basf-order-quote/populateLineItemPrice" })
@Properties({
		@Property(name = "service.pid", value = "com.basf.newtp.servlet.PopulateLineItemPrice", propertyPrivate = false),
		@Property(name = "service.description", value = "LineItemPrice  servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "BASF", propertyPrivate = false) })
public class PopulateLineItemPrice extends SlingAllMethodsServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	static final Logger log = LoggerFactory.getLogger("PopulateLineItemPrice");
	@Reference
	GetServiceConnection getServiceConnection;
	ObjectMapper objectMapper = null;
	Error error = null;

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Hello World");

	}

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		log.info("Inside post method of lineitemPrice..");
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		String submitorderquotedata = request.getParameter("SubmitOrderData");
		String serverUrl = CastIronServiceProperties.castironServer;
		String endPoint = CastIronServiceProperties.castironGetContractPriceEndPoint;
		String urlString = serverUrl + "/" + endPoint;
		log.info("CastIron SubmitOrder Url.." + urlString);
		ProductsListBean productsListBean = new ProductsListBean();
		String responseString = null;
		String errorResponseString = null;
		try {
			objectMapper = new ObjectMapper();
//			getServiceConnection.invokePost(urlString, submitorderquotedata);
//			errorResponseString = getServiceConnection.getConnectionError();
			if (StringUtils.isEmpty(errorResponseString)) {
				int statusCode = getServiceConnection.getStatusCode();
				responseString = "{\n    \"products\": [\n        {\n            \"material\": \"mat155\",\n            \"price\": \"$200\"\n        },\n        {\n            \"material\": \"mat154\",\n            \"price\": \"$300\"\n        },\n        {\n            \"material\": \"mat114\",\n            \"price\": \"$400\"\n        }\n    ]\n}";
				productsListBean = objectMapper.readValue(responseString,ProductsListBean.class);
				if(null != productsListBean.getError() && productsListBean.getError().size() > 0){
					for(ErrorBean error: productsListBean.getError()){
						if(!StringUtils.isEmpty(error.getFaultmessage()) || !StringUtils.isEmpty(error.getFaultname())){
							responseString = "{\"error\":\""+ErrorConstants.ERROR_PRICE_MESSAGE+"\"}";
						}
					}
				}
			} else {
				responseString = "{\"error\":\"" + errorResponseString + "\"}";
			}
			log.info("responseString = " + responseString);
			// ouputObj =
			// objectMapper.readValue(responseString,ProductsListBean.class);
			// request.getSession().setAttribute("orderdata", ouputObj);
			// response.sendRedirect(redirectPath);
		} catch (Exception exception) {
			log.info("Exception : " + exception);
			responseString = "{\"error\":\""+ErrorConstants.ERROR_PRICE_MESSAGE+"\"}";
		}
		out.println(responseString);

	}

}
