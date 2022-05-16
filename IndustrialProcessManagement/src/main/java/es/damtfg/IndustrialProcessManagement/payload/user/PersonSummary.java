/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

import javax.validation.constraints.NotNull;

import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alberto González
 *
 */
@Getter
@Setter
public class PersonSummary {

	@NotNull
	private User user;
	
	@NotNull
	private Person person;
	
}
