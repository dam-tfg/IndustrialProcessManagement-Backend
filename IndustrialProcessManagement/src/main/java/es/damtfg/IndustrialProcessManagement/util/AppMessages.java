/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.util;

/**
 * @author Alberto González
 *
 */
public abstract class AppMessages {

	public static final String ERROR_USERNAME_EXIST = "Username is already taken";
	public static final String ERROR_EMAIL_EXIST = "Email address is already in use";
	public static final String ERROR_ROLE_EXIST = "Role name is already in use";
	
	public static final String SUCCESS_USER_CREATION = "User registered successfully";
	public static final String SUCCESS_ROLE_CREATION = "Role registered successfully";
	
	public static final String EXCEPTION_RESOURCE_NOT_FOUND = "%s not found with %s: '%s'";
	public static final String EXCEPTION_USER_ROLE_NOT_SET = "User role not set";
	public static final String EXCEPTION_USERNAME_NOT_FOUND = "User not found with username or email: ";
	public static final String EXCEPTION_USERNAME_ID_NOT_FOUND = "User not found with ID: ";
	
	public static final String LOG_ERROR_UNAUTHORIZED_RESPONSE = "Responding with unauthorized error. Message - {}";
	public static final String LOG_ERROR_AUTHORIZATION_CONTEXT = "Could not set user authentication in security context";
	public static final String LOG_ERROR_JWT_INVALID_SIGNATURE = "Invalid JWT signature";
	public static final String LOG_ERROR_JWT_INVALID_TOKEN = "Invalid JWT token";
	public static final String LOG_ERROR_JWT_EXPIRED_TOKEN = "Expired JWT token";
	public static final String LOG_ERROR_JWT_UNSUPPORTED_TOKEN = "Unsupported JWT token";
	public static final String LOG_ERROR_JWT_CLAIMS_EMPTY = "JWT claims string is empty";
	
	public static final String SUCCESS_PRODUCT_CREATION = "prueba";
	public static final String SUCCESS_ORDER_CREATION = "prueba";
	
}
