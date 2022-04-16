/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload.user;

import javax.validation.constraints.NotNull;

import es.damtfg.IndustrialProcessManagement.model.user.Person;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alberto González
 *
 */
@Data
@AllArgsConstructor
public class PersonSummary {

	@NotNull
	private User user;
	
	@NotNull
	private Person person;
	
}
