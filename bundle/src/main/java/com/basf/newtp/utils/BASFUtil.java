package com.basf.newtp.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.AccessDeniedException;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.Value;

import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.jcr.base.util.AccessControlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BASFUtil {

	static final Logger log = LoggerFactory.getLogger("AosUtil");

	@SuppressWarnings("unchecked")
	public static Map<String, String> getUserGroups(
			SlingHttpServletRequest request) throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {

		Session session = request.getResourceResolver().adaptTo(Session.class);

		// List<String> groups = new ArrayList<String>();

		UserManager um = AccessControlUtil.getUserManager(session);
		User currentUser = (User) um.getAuthorizable(session.getUserID());
		log.info("Current user.." + currentUser);
		Map<String, String> groupsForUser = null;
		if (request.getAttribute("groupsForUser") == null) {

			Iterator<Group> userGroups = currentUser.memberOf();

			groupsForUser = new HashMap<String, String>();
			while (userGroups.hasNext()) {

				Group userGrp = userGroups.next();
				String val = userGrp.getID();
				groupsForUser.put(val, val);
			} // end of while
			request.setAttribute("groupsForUser", groupsForUser);
		} else {
			groupsForUser = (Map<String, String>) request.getAttribute("groupsForUser");
		}

		return groupsForUser;
	}

	public static boolean isCcr(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		boolean isccr = false;
		Map<String, String> userGroups = getUserGroups(request);

		for (String val : userGroups.keySet()) {

			if (val.contains("call-center-reps")) {

				isccr = true;
			}
		}

		return isccr;
	}

	public static boolean isCsr(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		boolean iscsr = false;
		Map<String, String> userGroups = getUserGroups(request);

		for (String val : userGroups.keySet()) {
			if (val.contains("cust-serv-reps")) {

				iscsr = true;
			}
		}
		return iscsr;
	}

	public static boolean isExternalRep(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		boolean isexternalrep = false;
		Map<String, String> userGroups = getUserGroups(request);

		for (String val : userGroups.keySet()) {
			if (val.contains("ext-sales-reps")) {

				isexternalrep = true;
			}
		}
		return isexternalrep;
	}

	public static boolean isInternalRep(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		boolean isinternalrep = false;
		Map<String, String> userGroups = getUserGroups(request);

		for (String val : userGroups.keySet()) {
			if (val.contains("int-sales-reps")) {

				isinternalrep = true;
			}
		}
		return isinternalrep;
	}
	
	public static String getCurrentUserID(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		
		Session session = request.getResourceResolver().adaptTo(Session.class);

		UserManager um = AccessControlUtil.getUserManager(session);
		User currentUser = (User) um.getAuthorizable(session.getUserID());
		log.info("Current userID.." + currentUser.getID());
		return currentUser.getID();
		
	}
	
	public static String getUserGroupName(SlingHttpServletRequest request)
			throws AccessDeniedException,
			UnsupportedRepositoryOperationException, RepositoryException,
			Exception {
		String userGroup=null;
		Map<String, String> userGroups = getUserGroups(request);

		for (String val : userGroups.keySet()) {
			if (val.contains("int-sales-reps")) {

				userGroup="ISR";
			}
			else if (val.contains("ext-sales-reps")) {

				userGroup="ESR";
			}
			else if (val.contains("cust-serv-reps")) {

				userGroup="CSR";
			}
			else if (val.contains("call-center-reps")) {

				userGroup="CCR";
			}
		}
		return userGroup;
		
	}

}
