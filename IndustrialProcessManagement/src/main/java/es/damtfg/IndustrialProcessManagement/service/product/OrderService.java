package es.damtfg.IndustrialProcessManagement.service.product;

import es.damtfg.IndustrialProcessManagement.model.product.Order;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface OrderService {
	
	/**
	 * Crea una orden sin persistencia
	 * 
	 * @param orden
	 * @return
	 */
	public ApiResponse create(Order order);
	
	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param orden
	 * 
	 * @return Objeto producto guardado
	 */
	public Order save(Order order);


}
