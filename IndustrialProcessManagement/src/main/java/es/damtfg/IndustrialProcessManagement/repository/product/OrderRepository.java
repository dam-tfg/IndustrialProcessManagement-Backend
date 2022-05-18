package es.damtfg.IndustrialProcessManagement.repository.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.product.Order;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<Order> findById(String id);

}
