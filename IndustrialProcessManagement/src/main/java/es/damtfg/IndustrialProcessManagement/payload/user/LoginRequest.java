/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto González
 *
 */
@Getter
@Setter
public class LoginRequest {

	@NotBlank
	private String usernameOrEmail;
	
	@NotBlank
	private String password;
	
}
