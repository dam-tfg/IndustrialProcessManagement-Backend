package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.ArrayList;
import java.util.List;

import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface RecipeComponentService {
	
	/**
	 * Crea un nuevo componente de receta sin persistencia
	 * 
	 * @param recipe
	 * @return
	 */
	public ApiResponse create(RecipeComponent recipeComponent);

	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto componente de la receta guardado
	 */
	public RecipeComponent save(RecipeComponent recipeComponent);

	public List<RecipeComponent> saveAll(ArrayList<RecipeComponent> recipeComponent);

}
