package es.damtfg.IndustrialProcessManagement.payload.products;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class ProductRequest {
	
	@NotBlank
	@Size(min = 4, max = 40)
	private String name;
	
	@NotBlank
	@Size(min = 4, max = 40)
	private String quantity;

}
