package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.product.ProductRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	/**
	 * Creación de un nuevo producto
	 * 
	 * @param Product
	 * 
	 * @return ApiResponse
	 */
	@Override
	@Transactional
	public ApiResponse create(Product product) {

		product.setName(product.getName().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Transactional(readOnly = true)
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return productRepository.findAll();
	}

}
