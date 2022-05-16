package es.damtfg.IndustrialProcessManagement.payload.component;

import javax.validation.constraints.NotBlank;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class StockRequest {
	
	@NotBlank
	private String unit;

	@NotBlank
	private Component component;

}
