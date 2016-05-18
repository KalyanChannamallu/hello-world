package com.basf.newtp.utils;

/*
 * This class will be accessed to get values for configuring Cast Iron Service urls
 */

import java.util.Dictionary;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.OsgiUtil;
import org.osgi.service.component.ComponentContext;


@Service(value = CastIronServiceProperties.class)
@Component(label = "AOS CastIronServicesProperties Configuration", name = "com.aos.portal.AOSPortal.CastIronServiceProperties", description = "CastIronServiceProperties", metatype = true, immediate = true)
@Properties({
		@Property(name = "service.description", value = "AOS CastIronServiceProperties Configuration"),
		@Property(name = "service.vendor", value = "AO Smith") })
public class CastIronServiceProperties {

	
	public static String castironServer;
	public static String castironCustomerLookupEndPoint;
	public static String castironGetContractPriceEndPoint;
	public static String castironSubmitOrderEndPoint;
	public static String castironSubmitQuoteEndPoint;
	public static String castironSaveOrderEndPoint;
	public static String castironSaveQuoteEndPoint;
	public static String castironCancelOrderEndPoint;
	public static String castironMyOrdersEndPoint;
	public static String castironMyQuotesEndPoint;
	public static String castironOrderDeatilsEndPoint;
	public static String castironQuoteDetailsEndPoint;
	

	@Property(label = "CastIron Server Name", description = "CastIron Server Name", value = "http://172.20.105.203")
	protected static final String CASTIRON_SERVER_PROPERTY = "CASTIRON_SERVER";

	@Property(label = "CastIron CustomerLookup Service EndPoint", description = "CastIron CustomerLookup Service EndPoint", value = "customersearch")
	protected static final String CASTIRON_CUSTOMERLOOKUP_ENDPOINT_PROPERTY = "CASTIRON_CUSTOMERLOOKUP_ENDPOINT";

	@Property(label = "CastIron GetContractPrice Service EndPoint", description = "CastIron GetContractPrice Service EndPoint", value = "GetContractPrice")
	protected static final String CASTIRON_GETCONTRACTPRICE_ENDPOINT_PROPERTY = "CASTIRON_GETCONTRACTPRICE_ENDPOINT";
	
	@Property(label = "CastIron SubmitOrder Service EndPoint", description = "CastIron SubmitOrder Service EndPoint", value = "createorder")
	protected static final String CASTIRON_SUBMITORDER_ENDPOINT_PROPERTY = "CASTIRON_SUBMITORDER_ENDPOINT";
	
	@Property(label = "CastIron SubmitQuote Service EndPoint", description = "CastIron SubmitQuote Service EndPoint", value = "createquote")
	protected static final String CASTIRON_SUBMITQUOTE_ENDPOINT_PROPERTY = "CASTIRON_SUBMITQUOTE_ENDPOINT";
	
	@Property(label = "CastIron SaveOrder Service EndPoint", description = "CastIron SaveOrder Service EndPoint", value = "saveorder")
	protected static final String CASTIRON_SAVEORDER_ENDPOINT_PROPERTY = "CASTIRON_SAVEORDER_ENDPOINT";
	
	@Property(label = "CastIron SaveQuote Service EndPoint", description = "CastIron SaveQuote Service EndPoint", value = "savequote")
	protected static final String CASTIRON_SAVEQUOTE_ENDPOINT_PROPERTY = "CASTIRON_SAVEQUOTE_ENDPOINT";
	
	@Property(label = "CastIron CancelOrder Service EndPoint", description = "CastIron CancelOrder Service EndPoint", value = "cancelorder")
	protected static final String CASTIRON_CANCELORDER_ENDPOINT_PROPERTY = "CASTIRON_CANCELORDER_ENDPOINT";
	
	@Property(label = "CastIron MyOrders Service EndPoint", description = "CastIron MyOrders Service EndPoint", value = "orderheader")
	protected static final String CASTIRON_MYORDERS_ENDPOINT_PROPERTY = "CASTIRON_MYORDERS_ENDPOINT";
	
	@Property(label = "CastIron MyQuotes Service EndPoint", description = "CastIron MyQuotes Service EndPoint", value = "quoteheader")
	protected static final String CASTIRON_MYQUOTES_ENDPOINT_PROPERTY = "CASTIRON_MYQUOTES_ENDPOINT";
	
	@Property(label = "CastIron OrderDetails Service EndPoint", description = "CastIron OrderDetails Service EndPoint", value = "orderdetails")
	protected static final String CASTIRON_ORDERDETAILS_ENDPOINT_PROPERTY = "CASTIRON_ORDERDETAILS_ENDPOINT";
	
	@Property(label = "CastIron QuoteDetails Service EndPoint", description = "CastIron QuoteDetails Service EndPoint", value = "quotedetails")
	protected static final String CASTIRON_QUOTEDETAILS_ENDPOINT_PROPERTY = "CASTIRON_QUOTEDETAILS_ENDPOINT";
	
	
	/**
	 * Triggered when activating the OSGi bundle.<br/>
	 * 
	 * @param ComponentContext
	 *            The OSGi component context
	 * @throws Exception
	 */

	protected void activate(ComponentContext ctx) throws Exception {

		Dictionary<?, ?> props = ctx.getProperties();

		try {
			castironServer = OsgiUtil.toString(props.get("CASTIRON_SERVER"), "");
			castironCustomerLookupEndPoint = OsgiUtil.toString(	props.get("CASTIRON_CUSTOMERLOOKUP_ENDPOINT"), "");
			castironGetContractPriceEndPoint = OsgiUtil.toString(props.get("CASTIRON_GETCONTRACTPRICE_ENDPOINT"), "");
			castironSubmitOrderEndPoint = OsgiUtil.toString(props.get("CASTIRON_SUBMITORDER_ENDPOINT"), "");
			castironSubmitQuoteEndPoint = OsgiUtil.toString(props.get("CASTIRON_SUBMITQUOTE_ENDPOINT"), "");
			castironSaveOrderEndPoint = OsgiUtil.toString(props.get("CASTIRON_SAVEORDER_ENDPOINT"), "");
			castironSaveQuoteEndPoint = OsgiUtil.toString(props.get("CASTIRON_SAVEQUOTE_ENDPOINT"), "");
			castironCancelOrderEndPoint = OsgiUtil.toString(props.get("CASTIRON_CANCELORDER_ENDPOINT"), "");
			castironMyOrdersEndPoint = OsgiUtil.toString(props.get("CASTIRON_MYORDERS_ENDPOINT"), "");
			castironMyQuotesEndPoint = OsgiUtil.toString(props.get("CASTIRON_MYQUOTES_ENDPOINT"), "");
			castironOrderDeatilsEndPoint = OsgiUtil.toString(props.get("CASTIRON_ORDERDETAILS_ENDPOINT"), "");
			castironQuoteDetailsEndPoint = OsgiUtil.toString(props.get("CASTIRON_QUOTEDETAILS_ENDPOINT"), "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Triggered when bundle is stopped.
	 * 
	 * @param ComponentContext
	 *            The OSGi component context
	 * @throws Exception
	 */
	protected void deactivate(ComponentContext ctx) throws Exception {
		castironServer = null;
		castironCustomerLookupEndPoint = null;
		castironGetContractPriceEndPoint = null;
		castironSubmitOrderEndPoint = null;
		castironSubmitQuoteEndPoint = null;
		castironSaveOrderEndPoint = null;
		castironSaveQuoteEndPoint = null;
		castironCancelOrderEndPoint = null;
		castironMyOrdersEndPoint = null;
		castironMyQuotesEndPoint = null;
		castironOrderDeatilsEndPoint = null;
		castironQuoteDetailsEndPoint = null;
	}
}