package es.damtfg.IndustrialProcessManagement.repository.product;

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

	OrderDetails save(OrderDetails orderDetails);

}
