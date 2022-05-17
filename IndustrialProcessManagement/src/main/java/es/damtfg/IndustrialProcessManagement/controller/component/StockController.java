package es.damtfg.IndustrialProcessManagement.controller.component;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import es.damtfg.IndustrialProcessManagement.model.component.Stock;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.component.StockRequest;
import es.damtfg.IndustrialProcessManagement.service.component.StockServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.STOCK)
public class StockController {
	
	@Autowired
	private StockServiceImpl stockService;
	
	/**
	 * 
	 * @param stockRequest
	 * @return
	 */
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody StockRequest stockRequest) {
		
		Stock newStock = new Stock(stockRequest.getUnit(), stockRequest.getComponent());
		
		ApiResponse apiResponse = stockService.create(newStock);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Stock result = stockService.save(newStock);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getUnit()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION));	
	}
	

}
