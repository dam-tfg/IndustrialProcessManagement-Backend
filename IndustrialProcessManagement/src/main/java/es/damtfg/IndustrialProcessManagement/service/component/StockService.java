package es.damtfg.IndustrialProcessManagement.service.component;

import es.damtfg.IndustrialProcessManagement.model.component.Stock;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;

/**
 * 
 * @author  Carlos Munoz
 *
 */
public interface StockService {

	public ApiResponse create(Stock stock);

	public Stock save(Stock stock);

}
