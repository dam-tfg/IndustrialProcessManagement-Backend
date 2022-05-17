/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

/**
 * @author Alberto González
 *
 */
/**
 * 
 */
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String usernameOrEmail;
	
	@NotBlank
	private String password;
	
}
