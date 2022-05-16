package es.damtfg.IndustrialProcessManagement.payload.products;

import javax.validation.constraints.NotBlank;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class RecipeComponentRequest {
	
	@NotBlank
	private String unit;

	@NotBlank
	private Recipe recipe;
	
	@NotBlank
	private Component component;

}
