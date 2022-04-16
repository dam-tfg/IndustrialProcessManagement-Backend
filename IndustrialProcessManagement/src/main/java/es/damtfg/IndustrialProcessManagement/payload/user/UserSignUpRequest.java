/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto González
 *
 */
@Getter
@Setter
public abstract class UserSignUpRequest {

	@NotBlank
	@Size(min = 3, max = 15)
	private String username;
	
	@NotBlank
    @Size(max = 40)
    @Email
    private String email;
	
	@NotBlank
	@Size(min = 6, max = 25)
	private String password;
	
}
