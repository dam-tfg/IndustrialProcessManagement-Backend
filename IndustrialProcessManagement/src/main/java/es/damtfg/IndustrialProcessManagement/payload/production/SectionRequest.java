package es.damtfg.IndustrialProcessManagement.payload.production;

import javax.validation.constraints.NotBlank;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class SectionRequest {
	
	@NotBlank
	private String name;

	@NotBlank
	private Line line;

}
