package es.damtfg.IndustrialProcessManagement.payload.component;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class ComponentRequest {

	@NotBlank
	private String name;

}
