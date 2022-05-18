package es.damtfg.IndustrialProcessManagement.repository.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.product.RecipeComponent;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface RecipeComponentRepository extends JpaRepository <RecipeComponent, Long> {
	
	/**
	 * Busca por nombre de componente de receta.
	 * 
	 * @param unit
	 * 
	 * @return Optional<recipe>
	 */
	Boolean findByUnit(String unit);
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<RecipeComponent> findById(String id);

}
