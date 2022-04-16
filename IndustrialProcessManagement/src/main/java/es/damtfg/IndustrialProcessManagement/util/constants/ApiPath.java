/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.util.constants;

/**
 * @author Alberto González
 *
 */
public abstract class ApiPath {

	/**************************************************************************************
	 * API Paths
	 **************************************************************************************/
	public static final String BASE = "/api/v1";
	
	// Auth
	public static final String AUTH = BASE + "/auth/";
	
	// User
	public static final String USER = BASE + "/user/";
	public static final String PERSON = BASE + "/person/";
	public static final String ROLE = USER + "role/";
	
}
