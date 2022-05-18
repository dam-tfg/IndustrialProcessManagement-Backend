package es.damtfg.IndustrialProcessManagement.controller.company;

import java.net.URI;
import java.util.Collections;
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

import es.damtfg.IndustrialProcessManagement.exception.AppException;
import es.damtfg.IndustrialProcessManagement.exception.ResourceNotFoundException;
import es.damtfg.IndustrialProcessManagement.model.company.Company;
import es.damtfg.IndustrialProcessManagement.model.user.User;
import es.damtfg.IndustrialProcessManagement.model.user.security.Role;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.payload.company.CompanyRequest;
import es.damtfg.IndustrialProcessManagement.service.company.CompanyServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.UserServiceImpl;
import es.damtfg.IndustrialProcessManagement.service.user.security.RoleServiceImpl;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;
import es.damtfg.IndustrialProcessManagement.util.constants.ApiPath;

/**
 * @author  Carlos Munoz
 * @author Alberto González
 */
@RestController
@RequestMapping(ApiPath.COMPANY)
public class CompanyController {
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@PostMapping("new")
	public ResponseEntity<ApiResponse> create(@Valid @RequestBody CompanyRequest companyRequest) {
		
		User newUser = new User(companyRequest.getUsername(), companyRequest.getEmail(),
				companyRequest.getPassword(), new Date());
		
		ApiResponse apiResponse = userService.create(newUser);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Company newCompany = new Company(companyRequest.getName(), newUser);
		
		newUser.setCompany(newCompany);
		
		apiResponse = companyService.create(newCompany);
		
		if(!apiResponse.getSuccess()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
		
		Role userRole = roleService.findByName("ROLE_COMPANY")
				.orElseThrow(() -> new AppException(AppMessages.EXCEPTION_USER_ROLE_NOT_SET));
		
		newUser.setRoles(Collections.singleton(userRole));
		
		User result = userService.save(newUser);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath()
				.buildAndExpand(result.getUsername()).toUri();	
		
		return ResponseEntity.created(location).body(new ApiResponse(true, AppMessages.SUCCESS_COMPANY_CREATION));	
	}
	
	/**
	 * Devuelve dato company por ID
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity (OK + Company)
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<Company> read(@PathVariable Long id) {
		
		Company company = companyService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Company", "company ID", id));
		
		return ResponseEntity.ok(company);
	}

	/**
	 * Devuelve todos los datos company
	 * 
	 * @return Lista de Company
	 */
	@GetMapping("all")
	public List<Company> readAll() {
		
		List<Company> company = StreamSupport
				.stream(companyService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return company;
	}

}
