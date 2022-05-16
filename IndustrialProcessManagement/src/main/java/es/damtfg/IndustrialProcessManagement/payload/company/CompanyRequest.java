package es.damtfg.IndustrialProcessManagement.payload.company;

import javax.validation.constraints.NotBlank;

import es.damtfg.IndustrialProcessManagement.model.user.User;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class CompanyRequest {
	
	@NotBlank
	private String name;

	@NotBlank
	private User user;

}
