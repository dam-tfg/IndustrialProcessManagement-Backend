package es.damtfg.IndustrialProcessManagement.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.damtfg.IndustrialProcessManagement.model.product.Product;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
	
	/**
	 * Busca por nombre de producto.
	 * 
	 * @param name
	 * 
	 * @return Optional<producto>
	 */
	Boolean findByName(String name);

}
