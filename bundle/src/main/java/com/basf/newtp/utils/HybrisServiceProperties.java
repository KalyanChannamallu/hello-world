package com.basf.newtp.utils;

/*
 * This class will be accessed to get values for configuring Hybris Service urls
 */

import java.util.Dictionary;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.OsgiUtil;
import org.osgi.service.component.ComponentContext;

@Service(value = HybrisServiceProperties.class)
@Component(label = "AOS HybrisServiceProperties Configuration", name = "com.aos.portal.AOSPortal.HybrisServiceProperties", description = "HybrisServiceProperties", metatype = true, immediate = true)
@Properties({
		@Property(name = "service.description", value = "AOS HybrisServicesProperties Configuration"),
		@Property(name = "service.vendor", value = "AO Smith") })
public class HybrisServiceProperties {


	public static String hybrisServer;
	public static String hybrisPartsLookupEndPoint;
	public static String hybrisGetCategoriesEndPoint;
	public static String hybrisGetProductAttributesEndPoint;

	@Property(label = "Hybris Server Name", description = "Hybris Server Name", value = "http://172.20.105.29:9001")
	protected static final String HYBRIS_SERVER_PROPERTY = "HYBRIS_SERVER";

	@Property(label = "Hybris PartsLookup Service EndPoint", description = "Hybris PartsLookup Service EndPoint", value = "rest/v1/HotWaterSiteUID/products?")
	protected static final String HYBRIS_PARTSLOOKUP_ENDPOINT_PROPERTY = "HYBRIS_PARTSLOOKUP_ENDPOINT";

	@Property(label = "Hybris GetCategories Service EndPoint", description = "Hybris GetCategories Service EndPoint", value = "rest/v1/HotWaterSiteUID/catalogs/HotWaterProductCatalog/Online/categories/001?options=SUBCATEGORIES")
	protected static final String HYBRIS_GETCATEGORIES_ENDPOINT_PROPERTY = "HYBRIS_GETCATEGORIES_ENDPOINT";
	
	@Property(label = "Hybris GetProductAttributes Service EndPoint", description = "Hybris GetProductAttributes Service EndPoint", value = "rest/v1/HotWaterSiteUID/products?")
	protected static final String HYBRIS_GETPRODUCTATTRIBUTES_ENDPOINT_PROPERTY = "HYBRIS_GETPRODUCTATTRIBUTES_ENDPOINT";

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
			hybrisServer = OsgiUtil.toString(props.get("HYBRIS_SERVER"), "");
			hybrisPartsLookupEndPoint = OsgiUtil.toString(props.get("HYBRIS_PARTSLOOKUP_ENDPOINT"), "");
			hybrisGetCategoriesEndPoint = OsgiUtil.toString(props.get("HYBRIS_GETCATEGORIES_ENDPOINT"), "");
			hybrisGetProductAttributesEndPoint = OsgiUtil.toString(props.get("HYBRIS_GETPRODUCTATTRIBUTES_ENDPOINT"), "");
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
		hybrisServer = null;
		hybrisPartsLookupEndPoint = null;
		hybrisGetCategoriesEndPoint = null;
		hybrisGetProductAttributesEndPoint = null;
	}
}