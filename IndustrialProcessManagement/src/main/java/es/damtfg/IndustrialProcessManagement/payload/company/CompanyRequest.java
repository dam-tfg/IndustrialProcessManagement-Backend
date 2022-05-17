package es.damtfg.IndustrialProcessManagement.payload.company;

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
	
	private String name;

	private User user;

}
