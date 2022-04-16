/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.util.constants;

/**
 * @author Alberto González
 *
 */
public abstract class ModelDataBase {

	/**************************************************************************************
	 * Structure
	 **************************************************************************************/
	public static final String BASE_PATH = "model";
	
	/**************************************************************************************
	 * Users
	 **************************************************************************************/
	// User
	public static final String USERS = BASE_PATH + ".user";

	// Person
	public static final String USERS_PERSON = USERS + ".person";
	
	// User's security
	public static final String USERS_ROLES = USERS + ".role";
	public static final String USERS_ROLES_RELATIONSHIP = USERS + ".role.relationship";
	
	/**************************************************************************************
	 * Companies
	 **************************************************************************************/
	// Company
	public static final String COMPANY = BASE_PATH + ".company";
	public static final String COMPANY_EMPLOYEE = COMPANY + ".employee";
	public static final String COMPANY_EMPLOYEE_SPECIALTY = COMPANY_EMPLOYEE + ".specialty";
	
	/**************************************************************************************
	 * Components
	 **************************************************************************************/
	// Component
	public static final String COMPONENT = BASE_PATH + ".component";
	
	// Stock component
	public static final String COMPONENT_STOCK = COMPONENT + ".stock";
	
	/**************************************************************************************
	 * Products
	 **************************************************************************************/
	// Product
	public static final String PRODUCT = BASE_PATH + ".product";
	
	// Order
	public static final String PRODUCT_ORDER = PRODUCT + ".order";
	
	// Order details
	public static final String PRODUCT_ORDER_DETAILS = PRODUCT_ORDER + ".details";
	
	// Recipe
	public static final String PRODUCT_RECIPE = PRODUCT + ".recipe";
	
	// Recipe components
	public static final String PRODUCT_RECIPE_COMPONENTS = PRODUCT_RECIPE + ".components";
	
	/**************************************************************************************
	 * Productions
	 **************************************************************************************/
	// Production base
	private static final String PRODUCTION = BASE_PATH + ".production";
	
	// Production line
	public static final String PRODUCTION_LINE = PRODUCTION + ".line";
		
	// Production section
	public static final String PRODUCTION_SECTION = PRODUCTION + ".section";
	
	// Production process
	public static final String PRODUCTION_PROCESS = PRODUCTION + ".process";
	
	// Production process
	public static final String PRODUCTION_LOG = PRODUCTION + ".log";
	
	// Production process
	public static final String PRODUCTION_LOG_EVENT = PRODUCTION_LOG + ".event";
	
	/**************************************************************************************
	 * Scheduled task
	 **************************************************************************************/
	// Schedule base
	private static final String SCHEDULE = BASE_PATH + ".schedule";
		
	// Task
	public static final String SCHEDULE_TASK = SCHEDULE + ".task";
		
}
