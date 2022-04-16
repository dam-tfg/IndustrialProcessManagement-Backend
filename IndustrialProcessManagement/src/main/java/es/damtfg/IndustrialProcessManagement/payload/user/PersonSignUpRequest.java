/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

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
public class PersonSignUpRequest extends UserSignUpRequest {

	@NotBlank
	@Size(min = 4, max = 40)
	private String name;
	
	@NotBlank
	@Size(min = 2, max = 60)
	private String surnames;
	
}
