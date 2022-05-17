package es.damtfg.IndustrialProcessManagement.payload.products;

import es.damtfg.IndustrialProcessManagement.model.product.Product;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class RecipeRequest {
	
	private String name;
	
	private Product product;

}
