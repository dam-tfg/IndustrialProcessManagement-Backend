package es.damtfg.IndustrialProcessManagement.payload.component;

import javax.validation.constraints.NotBlank;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public class ComponentRequest {

	@NotBlank
	private String name;

}
