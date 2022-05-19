package es.damtfg.IndustrialProcessManagement.payload.products;

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
public class RecipeComponentRequest extends RecipeRequest {
	
	private String unit;

	private Recipe recipe;
	
	private Component component;

}
