package es.damtfg.IndustrialProcessManagement.controller.production;

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

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.production.LogEventServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.LOG_EVENT)
public class LogEventController {
	
	@Autowired
	private LogEventServiceImpl logEventService;
	
	/**
	 * 
	 * @param logEventRequest
	 * @return
	 */

	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody LogEvent logEventRequest) {
		
		LogEvent newLogEvent = new LogEvent(logEventRequest.getName(), logEventRequest.getDescription());
		
		ApiResponse apiResponse = logEventService.create(newLogEvent);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		LogEvent result = logEventService.save(newLogEvent);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION));	
	}

}
