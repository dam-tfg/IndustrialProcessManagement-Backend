/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto González
 *
 */
@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1701024378077133246L;
	
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	/**
	 * Conventional constructor.
	 * 
	 * @param resourceName
	 * @param fieldName
	 * @param fieldValue
	 */
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		
		super(String.format(AppMessages.EXCEPTION_RESOURCE_NOT_FOUND, resourceName, fieldName, fieldValue));
		
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
