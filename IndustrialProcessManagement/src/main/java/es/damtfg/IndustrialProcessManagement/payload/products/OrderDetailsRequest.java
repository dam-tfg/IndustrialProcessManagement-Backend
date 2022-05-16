package es.damtfg.IndustrialProcessManagement.payload.products;

import javax.validation.constraints.NotBlank;

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
public class OrderDetailsRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String unit;
	
	@NotBlank
	private Product product;
	
}
