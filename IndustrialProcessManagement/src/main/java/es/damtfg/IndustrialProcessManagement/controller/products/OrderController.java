package es.damtfg.IndustrialProcessManagement.controller.products;

import java.net.URI;
import java.util.Date;
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
import es.damtfg.IndustrialProcessManagement.model.product.Order;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.products.OrderRequest;
import es.damtfg.IndustrialProcessManagement.service.product.OrderServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.ORDER)
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderService;
	
	/**
	 * Crea orden
	 * 
	 * @param orderRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody OrderRequest orderRequest) {

		Order newOrder = new Order(new Date(), orderRequest.getOrderDetails());
		
		ApiResponse apiResponse = orderService.create(newOrder);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Order result = orderService.save(newOrder);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getDate()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_ORDER_CREATION));	
	}
	
	/**
	 * Devuelve un Order por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Order)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Order> read(@PathVariable Long id) {
		
		Order order = orderService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Order", "Order ID", id));
		
		return ResponseEntity.ok(order);
	}

	/**
	 * Devuelve todos los Order
	 * 
	 * @return Lista de Order
	 */
	@GetMapping("all")
	public List<Order> readAll() {
		
		List<Order> order = StreamSupport
				.stream(orderService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return order;
	}

}
