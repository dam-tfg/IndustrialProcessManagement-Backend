package es.damtfg.IndustrialProcessManagement.payload.company;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import es.damtfg.IndustrialProcessManagement.payload.user.UserSignUpRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * @author  Carlos Munoz
 * @author Alberto González
 */
@Getter
@Setter
public class CompanyRequest extends UserSignUpRequest {
	
	@NotBlank
	@Size(min = 4, max = 40)
	private String name;

}
