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

import es.damtfg.IndustrialProcessManagement.model.production.Log;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.service.production.LogServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.LOG)
public class LogController {

	@Autowired
	private LogServiceImpl logService;

	/**
	 * @param logRequest
	 * 
	 * @return ResponseEntity (HTTP Status created + Location)
	 */

	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody Log logRequest) {

		Log newLog = new Log(logRequest.getDate());

		ApiResponse apiResponse = logService.create(newLog);

		if (!apiResponse.getSuccess())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);

		Log result = logService.save(newLog);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getDate()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION));

	}

}
