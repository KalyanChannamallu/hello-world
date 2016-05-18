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
import com.basf.newtp.jackson.beans.QuoteBean;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.utils.CastIronServiceProperties;
import com.basf.newtp.utils.ErrorConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SlingServlet(paths = { "/apps/basf/submitorder" })
@Properties({
		@Property(name = "service.pid", value = "com.basf.newtp.servlets.SubmitOrder", propertyPrivate = false),
		@Property(name = "service.description", value = "SubmitOrder  servlet", propertyPrivate = false),
		@Property(name = "service.vendor", value = "BASF", propertyPrivate = false) })
public class SubmitOrder extends SlingAllMethodsServlet {
	/**
	 * author : Kalyan Channamallu created on : 18/05/16 for : Submit or save the Order
	 * details to SAP or Cancel the Order and Fetching the Order Number and
	 * Order Status
	 */
	private static final long serialVersionUID = 2L;
	static final Logger log = LoggerFactory.getLogger(SubmitOrder.class);
	@Reference
	GetServiceConnection getServiceConnection;
	String endPoint = "";
	String responseString = "";
	String actionType = "";
	String submitorderquotedata = "";
	String redirectPath = "";
	String urlString = "";
	String errorResponseString = "";
	String serverUrl = CastIronServiceProperties.castironServer;
	Error error = null;
	ObjectMapper objectMapper = null;
	OrderBean ouputObj = null;
	ErrorBean errorBean = null;
	List<ErrorBean> errorBeanList = null;

	protected void doGet(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {
	}

	/*
	 * Method for Submit or Save the Order Details to SAP or Cancel the Order
	 * and retrieving Order Number and Order status
	 */
	protected void doPost(SlingHttpServletRequest request,
			SlingHttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("text/html");
		actionType = request.getParameter("actionType");
		submitorderquotedata = request.getParameter("jsonData");
		redirectPath = request.getParameter("redirect");
		if (actionType.equals("save")) {
			endPoint = CastIronServiceProperties.castironSaveOrderEndPoint;
		} else if (actionType.equals("submit")) {
			endPoint = CastIronServiceProperties.castironSubmitOrderEndPoint;
		} else if (actionType.equals("cancel")) {
			endPoint = CastIronServiceProperties.castironCancelOrderEndPoint;
		}
		urlString = serverUrl + "/" + endPoint;
		objectMapper = new ObjectMapper();
		ouputObj = new OrderBean();
		errorBean = new ErrorBean();
		List<ErrorBean> errorBeanList = new ArrayList<ErrorBean>();
		try {
			getServiceConnection.invokePost(urlString, submitorderquotedata);
			String errorResponseString = getServiceConnection
					.getConnectionError();
			log.debug("errorResponseString = " + errorResponseString);
			if (StringUtils.isEmpty(errorResponseString)) {
				responseString = getServiceConnection.getResponseString();
				log.debug("responseString = " + responseString);
				ouputObj = objectMapper.readValue(responseString,
						OrderBean.class);
				request.getSession().setAttribute("orderdata", ouputObj);
				if (null != ouputObj.getError()
						&& ouputObj.getError().size() > 0) {
					for (ErrorBean error : ouputObj.getError()) {
						if (!StringUtils.isEmpty(error.getFaultmessage())
								|| !StringUtils.isEmpty(error.getFaultname())) {
							error.setErrormessage(ErrorConstants.ERROR_SUBMITORDERQUOTE_MESSAGE);
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
			errorBean.setErrormessage(ErrorConstants.ERROR_SUBMITORDERQUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (JsonMappingException jmExp) {
			log.error("Error while mapping Json :" + jmExp);
			errorBean.setErrormessage(ErrorConstants.ERROR_SUBMITORDERQUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (IOException ioExp) {
			log.error("IOException while parsing Json :" + ioExp);
			errorBean.setErrormessage(ErrorConstants.ERROR_SUBMITORDERQUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} catch (Exception exp) {
			log.error("Error :" + exp);
			errorBean.setErrormessage(ErrorConstants.ERROR_SUBMITORDERQUOTE_MESSAGE);
			errorBeanList.add(errorBean);
			ouputObj.setError(errorBeanList);
			request.getSession().setAttribute("orderdata", ouputObj);
		} finally {
			response.sendRedirect(redirectPath);
		}

	}

}
