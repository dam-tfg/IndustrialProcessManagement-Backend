package es.damtfg.IndustrialProcessManagement.payload.production;

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
public class LineRequest {
	
	@NotBlank
	private String name;

}
