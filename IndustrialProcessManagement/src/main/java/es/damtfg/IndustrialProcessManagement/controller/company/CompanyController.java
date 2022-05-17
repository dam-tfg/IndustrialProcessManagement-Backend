package es.damtfg.IndustrialProcessManagement.controller.company;

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

import es.damtfg.IndustrialProcessManagement.model.company.Company;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.company.CompanyRequest;
import es.damtfg.IndustrialProcessManagement.service.company.CompanyServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@RestController
@RequestMapping(ApiPath.COMPANY)
public class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody CompanyRequest companyRequest) {
		
		Company newCompany = new Company(companyRequest.getName(), companyRequest.getUser());
		
		ApiResponse apiResponse = companyService.create(newCompany);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Company result = companyService.save(newCompany);
		
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().buildAndExpand(result.getName()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION));	
	}

}
