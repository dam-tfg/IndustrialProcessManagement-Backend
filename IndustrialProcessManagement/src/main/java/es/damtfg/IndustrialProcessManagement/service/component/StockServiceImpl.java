package es.damtfg.IndustrialProcessManagement.service.component;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.component.Stock;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.component.StockRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	private StockRepository stockRepository;

	@Transactional
	public ApiResponse create(Stock stock) {

		stock.setUnit(stock.getUnit().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Transactional
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}

	@Transactional(readOnly = true)
	public Optional<Stock> findById(Long id) {
		return stockRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

}
