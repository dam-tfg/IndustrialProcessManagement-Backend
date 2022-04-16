/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alberto González
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -397781125673949183L;

	public AppException(String message) {
		
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		
        super(message, cause);
    }
	
}
