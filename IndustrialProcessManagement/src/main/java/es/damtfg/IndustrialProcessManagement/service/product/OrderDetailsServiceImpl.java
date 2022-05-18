package es.damtfg.IndustrialProcessManagement.service.product;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.product.OrderDetailsRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;

	@Override
	@Transactional
	public ApiResponse create(OrderDetails orderDetails) {
		orderDetails.setUnit(orderDetails.getUnit());
		
		return new ApiResponse(true, AppMessages.SUCCESS_ORDER_CREATION);
	}

	@Override
	@Transactional
	public OrderDetails save(OrderDetails orderDetails) {
		return orderDetailsRepository.save(orderDetails);
	}

	@Transactional
	public Optional<OrderDetails> findById(Long id) {
		return orderDetailsRepository.findById(id);
	}

	@Transactional
	public List<OrderDetails> findAll() {
		return orderDetailsRepository.findAll();
	}

}
