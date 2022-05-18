package es.damtfg.IndustrialProcessManagement.repository.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.product.Recipe;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface RecipeRepository extends JpaRepository <Recipe, Long> {
	
	/**
	 * Busca por nombre de receta.
	 * 
	 * @param name
	 * 
	 * @return Optional<recipe>
	 */
	Boolean findByName(String name);
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Recipe> findById(String id);

}
