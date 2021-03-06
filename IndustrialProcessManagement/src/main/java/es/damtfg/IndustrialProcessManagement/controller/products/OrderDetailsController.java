package es.damtfg.IndustrialProcessManagement.controller.products;

import java.net.URI;
import java.util.List;
import java.util.Set;
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
import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
import es.damtfg.IndustrialProcessManagement.model.product.Product;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.OrderDetailsRequest;
import es.damtfg.IndustrialProcessManagement.service.product.OrderDetailsServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.product.ProductServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.ORDER_DETAILS)
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsServiceImpl orderDetailsService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	/**
	 * Crea detalles de la orden
	 * 
	 * @param orderDetailsRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	
	@SuppressWarnings("unchecked")
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody OrderDetailsRequest orderDetailsRequest) {
		
		Product newProduct = new Product(orderDetailsRequest.getName());
		
		ApiResponse apiResponse = productService.create(newProduct);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
				
		OrderDetails newOrderDetails = new OrderDetails(orderDetailsRequest.getUnit(), orderDetailsRequest.getProduct());
		
		newProduct.setOrderDetails((Set<OrderDetails>) newOrderDetails);
		
		apiResponse = orderDetailsService.create(newOrderDetails);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Product result = productService.save(newProduct);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_ORDERDETAILS_CREATION));	
	}
	
	/**
	 * Devuelve un orderDetails por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + OrderDetails)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<OrderDetails> read(@PathVariable Long id) {
		
		OrderDetails orderDetails = orderDetailsService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("OrderDetails", "orderDetails ID", id));
		
		return ResponseEntity.ok(orderDetails);
	}

	/**
	 * Devuelve todos los OrderDetails
	 * 
	 * @return Lista de OrderDetails
	 */
	@GetMapping("all")
	public List<OrderDetails> readAll() {
		
		List<OrderDetails> orderDetails = StreamSupport
				.stream(orderDetailsService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return orderDetails;
	}

}
