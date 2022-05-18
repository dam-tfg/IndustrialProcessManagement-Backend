package es.damtfg.IndustrialProcessManagement.controller.products;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.ProductRequest;
import es.damtfg.IndustrialProcessManagement.service.product.ProductServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.PRODUCT)
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	/**
	 * Crea producto
	 * 
	 * @param productRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody ProductRequest productRequest) {
				
		Product newProduct = new Product(productRequest.getName());
		
		ApiResponse apiResponse = productService.create(newProduct);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Product result = productService.save(newProduct);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION));	
	}

	/**
	 * Devuelve un product por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Product)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Product> read(@PathVariable Long id) {
		
		Product product = productService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product ID", id));
		
		return ResponseEntity.ok(product);
	}

	/**
	 * Devuelve todos los Product
	 * 
	 * @return Lista de Product
	 */
	@GetMapping("all")
	public List<Product> readAll() {
		
		List<Product> product = StreamSupport
				.stream(productService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return product;
	}
	
}
