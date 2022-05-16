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
	
	// Company
	public static final String COMPANY = BASE + "/company/";
	public static final String SPECIALTY = COMPANY + "specialty/";
	
	// Component
	public static final String COMPONENT = BASE + "/component/";
	public static final String STOCK = COMPONENT + "stock/";
	
	// Component
	public static final String PRODUCT = BASE + "/product/";
	public static final String ORDER = PRODUCT + "order/";
	public static final String ORDER_DETAILS = PRODUCT + "orderdetails/";
	public static final String RECIPE = PRODUCT + "recipe/";
	public static final String RECIPE_COMPONENT = PRODUCT + "recipecomponent/";
	
	// Production
	public static final String PRODUCTION = BASE + "/production/";
	public static final String LINE = PRODUCTION + "line/";
	public static final String LOG = PRODUCTION + "log/";
	public static final String LOG_EVENT = PRODUCTION + "logevent/";
	public static final String PROCESS = PRODUCTION + "process/";
	public static final String SECTION = PRODUCTION + "section/";
	
	// Schedule
	public static final String SCHEDULE = BASE + "/schedule/";
	public static final String TASK = SCHEDULE + "task/";
	
	
}
