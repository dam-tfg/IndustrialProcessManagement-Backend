package es.damtfg.IndustrialProcessManagement.payload.company;

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
public class SpecialtyRequest {
	
	@NotBlank
	private String name;

}
