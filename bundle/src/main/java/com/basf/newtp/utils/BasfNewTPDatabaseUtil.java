package com.basf.newtp.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.felix.scr.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;


public class BasfNewTPDatabaseUtil {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String dataSourceName = "sqlDataSource";
	DataSource ds = null;
	@Reference
	DataSourcePool dbService;
	
	//@Reference
	//DatasourceUtil dataSourceUtil;
	
	static final Logger log = LoggerFactory.getLogger("AosDatabaseUtil");

	public BasfNewTPDatabaseUtil() {
		//this.ds = dataSourceUtil.getDataSource(dataSourceName);
//		try {
//			this.ds = (DataSource)dbService.getDataSource(dataSourceName);
//		} catch (DataSourceNotFoundException exp) {
//			log.error("DataSourceNotFoundException : " + exp);
//		}
	}

	/**
	 * Method for retrieving list of ShiptoAddresses from Staged DB
	 * 
	 */
	public List<String> getShipToAddress(String customerNumber) {
		List<String> shipToAddress = new ArrayList<String>();
		
		String query = "SELECT shiptoaddress FROM Customer_ShipTo where Customer_ Number=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, customerNumber);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(null != rs.getString("shiptoaddress")){
					shipToAddress.add(rs.getString("shiptoaddress"));
				}	

			}
		} catch (SQLException exp) {
			log.error("Exception while retrieving... " + exp);
		} finally {
			try {
				if (null != rs) {
					rs.close();
				}
				if (null != pstmt) {
					pstmt.close();
				}
				if (null != conn) {
					conn.close();
				}
			} catch (SQLException exp) {
				log.error("Error while releasing connection : " + exp);
			}
		}
		return shipToAddress;
	}

	/**
	 * Method for retrieving list of Brands from Staged DB
	 * 
	 */
	public List<String> getBrands() {
		List<String> brands = new ArrayList<String>();
		
		String query = "SELECT SalesRep_Authorisation_ZTable1.brand FROM SalesRep_Authorisation_ZTable1 join Customer_Master on SalesRep_Authorisation_ZTable1.Sales_Group=Customer_Master.Sales_Group";
		try {
//			conn = ds.getConnection();
//			rs = pstmt.executeQuery(query);
//			while (rs.next()) {
//				brands.add(rs.getString("brand"));
//
//			}
//		} catch (SQLException exp) {
//			log.error("Exception while retrieving... " + exp);
			brands.add("State");
			brands.add("Tagaki");
		} finally {
//			try {
//				if (null != rs) {
//					rs.close();
//				}
//				if (null != pstmt) {
//					pstmt.close();
//				}
//				if (null != conn) {
//					conn.close();
//				}
//			} catch (SQLException exp) {
//				log.error("Error while releasing connection : " + exp);
//			}
		}
		return brands;
	}
	
	public List<String> getCompetitiveMFR() {
		List<String> competitiveMFR = new ArrayList<String>();
		
//		String query = "SELECT SalesRep_Authorisation_ZTable1.brand FROM SalesRep_Authorisation_ZTable1 join Customer_Master on SalesRep_Authorisation_ZTable1.Sales_Group=Customer_Master.Sales_Group";
		try {
//			conn = ds.getConnection();
//			rs = pstmt.executeQuery(query);
//			while (rs.next()) {
//				brands.add(rs.getString("brand"));
//
//			}
//		} catch (SQLException exp) {
//			log.error("Exception while retrieving... " + exp);
			competitiveMFR.add("Credit Memo Request");
			competitiveMFR.add("Debit Memo Request");
			competitiveMFR.add("Export Order");
			competitiveMFR.add("Consignment Pick-Up");
			competitiveMFR.add("Consignment Fill-Up");
			competitiveMFR.add("Consignment Issue");
			competitiveMFR.add("Consignment Returns");
			competitiveMFR.add("Standard Quote");
		} finally {
//			try {
//				if (null != rs) {
//					rs.close();
//				}
//				if (null != pstmt) {
//					pstmt.close();
//				}
//				if (null != conn) {
//					conn.close();
//				}
//			} catch (SQLException exp) {
//				log.error("Error while releasing connection : " + exp);
//			}
		}
		return competitiveMFR;
	}

}
