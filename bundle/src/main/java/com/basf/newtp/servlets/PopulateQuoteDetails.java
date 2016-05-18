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

//import com.aos.portal.AOSPortal.connection.util.HttpConnection;

@SlingServlet(paths = { "/apps/basf/order-quote/PopulateQuoteDetails" })
@Properties({
		@Property(name = "service.pid", value = "com.basf.newtp.servlet.PopulateQuoteDetails", propertyPrivate = false),
		@Property(name = "service.description", value = "QuoteDetails  servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "BASF", propertyPrivate = false) })

public class PopulateQuoteDetails extends SlingAllMethodsServlet {
	// HttpConnection connectService=new HttpConnection();

	/**
	 * 
	 */
	@Reference
	GetServiceConnection getServiceConnection;	
	private static final long serialVersionUID = 2L;
	static final Logger log = LoggerFactory.getLogger("PopulateQuoteDetails");

	@Override
	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// log.info("quotenumber "+request.getParameter("quotenumber"));
		// request.getSession().setAttribute(request.getParameter("quotenumber"),
		// ouputObj);
		// response.sendRedirect("/content/aosmith/order-quote/en/home/newquote.html?mode=edit&quotenumber="+request.getParameter("quoternumber"));
		log.info("entering PopulatequoteDetails Get method");
		String serverUrl = CastIronServiceProperties.castironServer;
		String endPoint = CastIronServiceProperties.castironQuoteDetailsEndPoint;
		String urlString = serverUrl + "/" + endPoint;

		StringBuilder input = new StringBuilder();
		input.append("{\"quotenumber\":\"");
		input.append(request.getParameter("quotenumber"));
		input.append("\"}");
		String inputJson = input.toString();
		ObjectMapper objectMapper = new ObjectMapper();

		//String outputJson =	"{\n    \"quotenumber\": \"22233323\",\n    \"quotetype\": \"old\",\n    \"competitivemfr\": \"MFR\",\n    \"location\": \"US\",\n    \"validfrom\": \"3/3/2014\",\n    \"validto\": \"4/3/2014\",\n    \"notes\": \"added data for a quote\",\n    \"reasonforquote\": \"price is too high\",\n    \"soldto\": {\n        \"company\": \"Company\",\n        \"city\": \"City\",\n        \"addressone\": \"Addr1\",\n        \"addresstwo\": \"Addr2\",\n        \"state\": \"State\",\n        \"zipcode\": \"Code\"\n    },\n    \"shipto\": {\n        \"company\": \"Company\",\n        \"city\": \"City\",\n        \"addressone\": \"Addr1\",\n        \"addresstwo\": \"Addr2\",\n        \"state\": \"State\",\n        \"zipcode\": \"Code\"\n    },\n    \"products\": [\n        {\n            \"material\": \"1001\",\n            \"model\": \"N\",\n            \"quantity\": \"2\",\n            \"price\": \"200\",\n            \"loadfactor\": \"2.5\",\n            \"requestedprice\": \"150\",\n            \"competitiveprice\": \"160\",\n            \"netvalue\": \"$190\",\n            \"competitivemodel\": \"new\"\n        },\n        {\n            \"material\": \"1002\",\n            \"model\": \"O\",\n            \"quantity\": \"3\",\n            \"price\": \"870\",\n            \"loadfactor\": \"0.05\",\n            \"requestedprice\": \"670\",\n            \"competitiveprice\": \"770\",\n            \"netvalue\": \"$800\",\n            \"competitivemodel\": \"old\"\n        }\n    ]\n}";	
		log.info("Inside GET method");
		QuoteBean ouputObj = new QuoteBean();
		ErrorBean errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		try {
			getServiceConnection.invokePost(urlString, inputJson);
			String errorResponseString = getServiceConnection
					.getConnectionError();
			log.info("errorResponseString = " + errorResponseString);
			if (StringUtils.isEmpty(errorResponseString)) {
				
			String responseString = getServiceConnection.getResponseString();
			log.info("response string : "+responseString);
			ouputObj = objectMapper.readValue(responseString,QuoteBean.class);
			log.info("Converted json to Output bean"+ouputObj);
			request.getSession().setAttribute("beanObj", ouputObj);
			
			if (null != ouputObj.getError()
					&& ouputObj.getError().size() > 0) {
				for (ErrorBean error : ouputObj.getError()) {
					if (!StringUtils.isEmpty(error.getFaultmessage())
							|| !StringUtils.isEmpty(error.getFaultname())) {
						error.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
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
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			//Setting custom error message		
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			//Setting custom error message		
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		}catch (Exception exp) {
			log.error("Error :" + exp);		
			errorBean.setErrormessage(ErrorConstants.ERROR_QUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);	
			request.getSession().setAttribute("orderdata", ouputObj);
			}

		finally{
			response.sendRedirect("/content/aosmith/order-quote/en/home/newquote/quotedetails.html?mode=edit&quotenumber="
					+ request.getParameter("quotenumber")
					+ "&status="
					+ request.getParameter("status")
					+ "&quotetype="
					+ request.getParameter("quotetype"));
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
