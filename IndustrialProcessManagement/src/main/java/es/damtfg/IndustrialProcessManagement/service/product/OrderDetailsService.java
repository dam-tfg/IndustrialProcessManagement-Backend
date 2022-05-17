package es.damtfg.IndustrialProcessManagement.service.product;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface OrderDetailsService {

	public OrderDetails save(OrderDetails orderDetails);

	ApiResponse create(OrderDetails order);

}
