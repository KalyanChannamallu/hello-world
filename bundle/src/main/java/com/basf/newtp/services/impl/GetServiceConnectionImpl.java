package com.basf.newtp.services.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.basf.newtp.services.api.GetServiceConnection;
import com.basf.newtp.utils.ErrorConstants;



@Component
@Service
@Properties({
	@Property(name="service.pid", value="com.aosmith.portal.orderquote.util.GetServiceConnectionImpl"),
	@Property(name="service.description", value="AOS GetServiceConnectionImpl"),
	@Property(name="service.vendor", value="AO Smith")
})

public class GetServiceConnectionImpl implements GetServiceConnection
{

    static final Logger log = LoggerFactory.getLogger(GetServiceConnectionImpl.class);

	private int statusCode=0;
	private String responseString=null;
	private String connectionError=null;
	private Header[] responseHeader=null;
	private HttpClient httpClient=null;
	MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();


    private HttpClient createHttpClient() {
		HttpClient http = new HttpClient(this.connectionManager);
		log.info("httpClient in createHttpClient "+http );
		return http;
	}

    
    /**
	 * Used for invoking GET requests
	*/
	public void invokeGet(String urlString,String inputJson) {
		
		httpClient = createHttpClient();
		final GetMethod aHttpGet = new GetMethod(urlString);
		try {
			if (log.isDebugEnabled()) {
				log.debug(" About to make a GET request for the URL " + urlString);
			}
			aHttpGet.addRequestHeader("Content-Type", "application/jsonl;charset=UTF-8");
			aHttpGet.addRequestHeader("Accept-Charset", "UTF-8");
			aHttpGet.addRequestHeader("Accept", "application/json");
			try {
				statusCode = httpClient.executeMethod(aHttpGet);
				if (log.isDebugEnabled()) {
					log.debug(" Made a GET request for the URL " + urlString + " and the HTTP Status Code is " + statusCode);
				}
				if (statusCode == HttpStatus.SC_OK && aHttpGet.getResponseBodyAsStream() != null) {
					responseString = aHttpGet.getResponseBodyAsString();
					responseHeader = aHttpGet.getResponseHeaders();
					if (log.isDebugEnabled()) {
						log.debug(" Made a GET request for the URL " + urlString + " and the response is " + responseString);
					}
					if(statusCode != 200){
						connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
					}
				}
			} catch (final IOException anIOException) {
				log.error(" IO Exception occured while invoking GET ", anIOException);
				connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
			}
			if (log.isDebugEnabled()) {
				log.debug(" Response Recieved from the remote server is " + responseString);
			}
		} catch (final IllegalStateException anIllegalStateException) {
			log.error(" Illegal State Exception " + anIllegalStateException);
			connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
		} finally {
			aHttpGet.releaseConnection();
		}
	}
    
    /**
	 * Used for invoking POST method
	 * 
	 * @return
	 */
	public void invokePost(String urlString,String inputJson) {

		log.info("Inside GetURLConnection invoke post");
		httpClient = createHttpClient();
		final PostMethod postMethod = new PostMethod(urlString);

		try {
			if (log.isDebugEnabled()) {
				log.debug(" About to make a POST request for the URL " + urlString);
			}
			try {
				//log.info("Input Json.."+inputJson);
				responseString=null;
                StringRequestEntity requestEntity = new StringRequestEntity(inputJson,"application/json","UTF-8");                  
        		postMethod.setRequestEntity(requestEntity);
        		log.info("url String...."+urlString);
				statusCode = httpClient.executeMethod(postMethod);
				log.info("Status code.."+statusCode);
				if (log.isDebugEnabled()) {
					log.debug(" Made a POST request for the URL " + urlString + " and the HTTP Status Code is " + statusCode);
				}
				if (statusCode == HttpStatus.SC_OK && postMethod.getResponseBodyAsStream() != null) {
					responseString = postMethod.getResponseBodyAsString();
					responseHeader = postMethod.getResponseHeaders();
					for(org.apache.commons.httpclient.Header header : postMethod.getRequestHeaders()){
						log.info(header.getName() +"  =  " + header.getValue());
					}
					if (log.isDebugEnabled()) {
						log.debug(" Made a POST request for the URL " + urlString + " and the response is " + responseString);
					}
					if(statusCode != 200){
						connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
					}
				}
			
			}catch(SocketTimeoutException socketTimeOutException){
				log.error("Request timed out..");
				connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
				
			}catch (final IOException anIOException) {
				log.error(" IO Exception occured while invoking GET ", anIOException);
				connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
			}catch (Exception exception) {
				log.error("Exception occured :" + exception.getMessage());
				connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
			} 
			
			if (log.isDebugEnabled()) {
				log.debug(" Response Recieved from the remote server is " + responseString);
			}
		} catch (final IllegalStateException anIllegalStateException) {
			log.error(" Illegal State Exception " + anIllegalStateException);
			connectionError = ErrorConstants.SERVICE_CONNECTION_ERROR_MESSAGE;
		} finally {
			//responseString=null;
			postMethod.releaseConnection();
		}
	}


    public String getResponseString() {
		return this.responseString;
	}
	
    public String getConnectionError() {
		return this.connectionError;
	}
    
	public Header[] getResponseHeader() {
		return this.responseHeader;
	}
	
	public void setResponseHeader(String key, String value) {
		log.info("httpClient = "+httpClient);
		this.httpClient.getParams().setParameter(key,value);
	}

	public void setResponseString(String iResponseString) {
		this.responseString = iResponseString;
	}
	
	public void setConnectionError(String connectionError) {
		this.connectionError = connectionError;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(int iStatusCode) {
		this.statusCode = iStatusCode;
	}

}