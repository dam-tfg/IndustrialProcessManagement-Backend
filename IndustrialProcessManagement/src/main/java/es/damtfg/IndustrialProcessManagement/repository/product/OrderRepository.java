package es.damtfg.IndustrialProcessManagement.repository.product;

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

	Order save(Order order);

}
