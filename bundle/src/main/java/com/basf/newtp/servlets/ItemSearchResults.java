
package com.basf.newtp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.aos.portal.AOSPortal.connection.util.HttpConnection;

	@SlingServlet(
	        paths={"/apps/basf/order-quote/itemsearch"}
	)
	@Properties({
	        @Property(name="service.pid", value="com.basf.newtp.servlet.ItemSearchResults",propertyPrivate=false),
	        @Property(name="service.description",value="ItemSearchResult  servlet", propertyPrivate=false),
	        @Property(name="service.vendor",value="BASF", propertyPrivate=false)
	})




	public class ItemSearchResults extends SlingAllMethodsServlet
	{ 
		private static final long serialVersionUID = 2L;
		  static final Logger log = LoggerFactory.getLogger("ItemSearchResults");
		  
		  protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
		    throws ServletException, IOException
		  {
		    /*response.setContentType("application/json;charset=utf-8");
		    PrintWriter out = response.getWriter();
		    String string = "{\n    \"products\":{\n        \"itemId\": \"1\",\n        \"modelNo\": \"GHD373\",\n        \"material\": \"pALAGS\",\n        \"loadFactor\": \"2.00\",\n        \"netValue\": \"$789\",\n        \"extendedInformation\": {\n            \"fuelType\": \"E\",\n            \"elementKW\": \"62\",\n            \"phase\": \"1Ã\",\n            \"anode\": \"AL\",\n            \"pressureRTG\": \"150PSI\",\n            \"totalKW\": \"5.5\",\n            \"elementMat'l\": \"AU\",\n            \"phaseHz\": \"60Hz\",\n            \"tpl\": \"TT&P\",\n            \"language\": \"EN\",\n            \"elements\": \"6\",\n            \"voltage\": \"280V\",\n            \"control\": \"SURFACE\",\n            \"Construction\": \"ASME\"\n        }\n}";
		    out.print(string);
		    out.flush();*/
		  }
		  
		  protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
		    throws ServletException, IOException
		  {
		    response.setContentType("application/json;charset=utf-8");
		    PrintWriter out = response.getWriter();
		    String search=request.getParameter("search");
		    String hiddenVal=request.getParameter("brandId");
		    log.info("Search Material ID :"+search);
		    log.info("Hidden Value Here :"+hiddenVal);
		  
		    try
		    {
		      log.info("in AOS ITEM Servlet");
		      String jsonItemData ="{\n    \"products\": [\n        {\n            \"materialNumber\": \"mat154\",\n            \"classifications\": [\n                {\n                    \"name\": \"electric\",\n                    \"features\": [\n                        {\n                            \"range\": false,\n                            \"name\": \"Total KW\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"2.5\"\n                                },\n                                {\n                                    \"value\": \"23.9\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.totalkw\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Element KW\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"2.5/5.5\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.elementkw\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Elements\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"2\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.elements\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Phase\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"1ph\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.phase\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Phase HZ\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"60HZ\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.phasehz\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Voltage\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"120V\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.voltage\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Element Mat'l\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"AL\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/electric.elementmatl\"\n                        }\n                    ],\n                    \"code\": \"electric\"\n                },\n                {\n                    \"name\": \"general\",\n                    \"features\": [\n                        {\n                            \"range\": false,\n                            \"name\": \"Anode Material\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"AL-1A\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.anodematerial\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Language\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"EN\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.language\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"T&P Location\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"ST&P\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.tplocation\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Construction\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"ASME\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.construction\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Pressure Rating\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"150PSI\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.pressurerating\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Fuel Type\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"electric\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.fueltype\"\n                        },\n                        {\n                            \"range\": false,\n                            \"name\": \"Control\",\n                            \"featureValues\": [\n                                {\n                                    \"value\": \"4-WIRE\"\n                                }\n                            ],\n                            \"comparable\": true,\n                            \"code\": \"HotWaterClassificationCatalog/1.0/general.controlcodes\"\n                        }\n                    ],\n                    \"code\": \"general\"\n                }\n            ],\n            \"stock\": {\n                \"stockLevelStatus\": {\n                    \"code\": \"inStock\",\n                    \"codeLowerCase\": \"instock\"\n                },\n                \"stockLevel\": 0\n            },\n            \"code\": \"839\",\n            \"modelNumber\": \"model154\"\n        }\n    ],\n    \"pagination\": {\n        \"pageSize\": 20,\n        \"currentPage\": 0,\n        \"totalResults\": 1,\n        \"totalPages\": 1\n    },\n    \"sorts\": [],\n    \"currentQuery\": {\n        \"query\": {\n            \"value\": \"839:\"\n        },\n        \"url\": \"/search?q=839%3A\"\n    },\n    \"facets\": [],\n    \"breadcrumbs\": [],\n    \"freeTextSearch\": \"839\",\n    \"spellingSuggestion\": {\n        \"query\": \"239:\",\n        \"suggestion\": \"239\"\n    }\n}";
		      out.print(jsonItemData);
		    }
		    catch (Exception e)
		    {
		      log.error(e.toString());
		    }
		  }
	
	}