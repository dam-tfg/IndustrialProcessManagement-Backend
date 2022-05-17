package es.damtfg.IndustrialProcessManagement.service.product;

import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface ProductService {
	
	/**
	 * Crea un nuevo producto sin persistencia
	 * 
	 * @param product
	 * @return
	 */
	public ApiResponse create(Product product);
	
	/**
	 * Búsqueda de todos los productos.
	 * 
	 * @return
	 */
	public Iterable<Product> findAll();

	/**
	 * Crea la persistencia del objeto.
	 * 
	 * @param user
	 * 
	 * @return Objeto producto guardado
	 */
	public Product save(Product product);

}
