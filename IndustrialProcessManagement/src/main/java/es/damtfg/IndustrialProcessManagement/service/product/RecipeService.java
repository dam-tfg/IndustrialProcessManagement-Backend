package es.damtfg.IndustrialProcessManagement.service.product;

import es.damtfg.IndustrialProcessManagement.model.product.Recipe;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface RecipeService {

	/**
	 * Crea una nueva receta sin persistencia
	 * 
	 * @param recipe
	 * @return
	 */
	public ApiResponse create(Recipe recipe);
	
	/**
	 * Comprueba la existencia de una nueva receta
	 * 
	 * @param name
	 * 
	 * @return
	 */
	public Boolean existsByName(String name);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto receta guardado
	 */
	public Recipe save(Recipe recipe);
	
}
