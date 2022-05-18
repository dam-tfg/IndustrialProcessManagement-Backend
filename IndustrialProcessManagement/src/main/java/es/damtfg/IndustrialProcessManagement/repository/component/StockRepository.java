package es.damtfg.IndustrialProcessManagement.repository.component;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.component.Stock;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface StockRepository extends JpaRepository <Stock, Long> {

	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Stock> findById(String id);

}
