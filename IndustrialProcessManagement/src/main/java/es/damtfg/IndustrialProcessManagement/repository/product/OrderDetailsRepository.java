package es.damtfg.IndustrialProcessManagement.repository.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository <OrderDetails, Long> {
	
	/**
	 * Búsqueda por ID.
	 * 
	 * @param id
	 * @return Optional
	 */
	Optional<OrderDetails> findById(String id);

}
