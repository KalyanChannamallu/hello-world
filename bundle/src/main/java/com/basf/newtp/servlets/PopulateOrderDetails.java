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
import com.basf.newtp.jackson.beans.OrderBean;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.utils.CastIronServiceProperties;
import com.basf.newtp.utils.ErrorConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.aos.portal.AOSPortal.connection.util.HttpConnection;

@SlingServlet(paths = { "/apps/basf/order-quote/populateOrderDetails" })
@Properties({
		@Property(name = "service.pid", value = "com.basf.newtp.servlet.populateOrderDetails", propertyPrivate = false),
		@Property(name = "service.description", value = "OrderDetails  servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "BASF", propertyPrivate = false) })
public class PopulateOrderDetails extends SlingAllMethodsServlet {
	// HttpConnection connectService=new HttpConnection();

	/**
	 * 
	 */
	@Reference
	GetServiceConnection getServiceConnection;
	private static final long serialVersionUID = 2L;
	static final Logger log = LoggerFactory.getLogger("populateOrderDetails");

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// log.info("ordernumber "+request.getParameter("ordernumber"));
		// request.getSession().setAttribute(request.getParameter("ordernumber"),
		// ouputObj);
		// response.sendRedirect("/content/aosmith/order-quote/en/home/neworder.html?mode=edit&ordernumber="+request.getParameter("ordernumber"));
		log.info("entering PopulateOrderDetails Get method");
		String serverUrl = CastIronServiceProperties.castironServer;
		String endPoint = CastIronServiceProperties.castironOrderDeatilsEndPoint;
		String urlString = serverUrl + "/" + endPoint;

		StringBuilder input = new StringBuilder();
		input.append("{\"ordernumber\":\"");
		input.append(request.getParameter("ordernumber"));
		input.append("\"}");
		String inputJson = input.toString();

		ObjectMapper objectMapper = new ObjectMapper();

		// String outputJson =
		// "{\n  \"ordernumber\": \"a\",\n  \"ordertype\": \"b\",\n  \"customerpo\": \"c\",\n  \"requesteddeliverydate\": \"d\",\n  \"estimateddeliverydate\": \"e\",\n  \"notes\": \"f\",\n  \"soldto\": {\n\t\"id\": \"g\",\n    \"company\": \"h\",\n    \"city\": \"i\",\n    \"addressone\": \"j\",\n    \"addresstwo\": \"k\",\n    \"state\": \"l\",\n    \"zipcode\":\"m\"\n  },\n \"shipto\": {\n\t\"id\": \"n\",\n    \"company\": \"o\",\n    \"city\": \"p\",\n    \"addressone\": \"q\",\n    \"addresstwo\": \"r\",\n    \"state\": \"s\",\n    \"zipcode\":\"t\"\n  },\n \"products\": [\n       {\n         \"materialNumber\": \"mat112\",\n         \"modelNumber\": \"v\",\n         \"quantity\": \"1\",\n         \"price\": \"$1200\",\n         \"loadfactor\": \"y\",\n         \"notes\": \"z\"\n       },\n       {\n         \"materialNumber\": \"u1\",\n         \"modelNumber\": \"v1\",\n         \"quantity\": \"2\",\n         \"price\": \"$200\",\n         \"loadfactor\": \"y1\",\n         \"notes\": \"z1\"\n       }\n    ]\n}";
		log.info("Inside GET method");
		OrderBean ouputObj = new OrderBean();
		ErrorBean errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		try {
			getServiceConnection.invokePost(urlString, inputJson);
			String responseString = getServiceConnection.getResponseString();
			String errorResponseString = getServiceConnection
					.getConnectionError();
			log.info("errorResponseString = " + errorResponseString);
			if (StringUtils.isEmpty(errorResponseString)) {
				responseString = getServiceConnection.getResponseString();
				log.info("response string : " + responseString);
				ouputObj = objectMapper.readValue(responseString,
						OrderBean.class);
				log.info("Converted json to Output bean" + ouputObj);
				request.getSession().setAttribute("beanObj", ouputObj);
				if (null != ouputObj.getError()
						&& ouputObj.getError().size() > 0) {
					for (ErrorBean error : ouputObj.getError()) {
						if (!StringUtils.isEmpty(error.getFaultmessage())
								|| !StringUtils.isEmpty(error.getFaultname())) {
							error.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
						}
					}
				}
			} else {
				errorBean.setErrormessage(errorResponseString);
				errorBeanList.add(errorBean);
				ouputObj.setError(errorBeanList);
				request.getSession().setAttribute("orderdata", ouputObj);
			}

		} catch (JsonParseException jpExp) {
			log.error("Error while parsing Json :" + jpExp);
			//Setting custom error message		
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			//Setting custom error message		
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			//Setting custom error message		
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		}catch (Exception exp) {
			log.error("Error :" + exp);		
			errorBean.setErrormessage(ErrorConstants.ERROR_ORDER_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);	
			request.getSession().setAttribute("orderdata", ouputObj);
			}

		finally {
			response.sendRedirect("/content/aosmith/order-quote/en/home/neworder/orderdetails.html?mode=edit&ordernumber="
					+ request.getParameter("ordernumber")
					+ "&status="
					+ request.getParameter("status")
					+ "&ordertype="
					+ request.getParameter("ordertype"));
		}
	}

	@Override
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}