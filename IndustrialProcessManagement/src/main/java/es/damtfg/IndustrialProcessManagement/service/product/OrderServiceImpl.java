package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.product.Order;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.product.OrderRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional
	public ApiResponse create(Order order) {

		order.setDate(order.getDate());
		
		return new ApiResponse(true, AppMessages.SUCCESS_ORDER_CREATION);
		
	}
	@Override
	@Transactional
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

}
