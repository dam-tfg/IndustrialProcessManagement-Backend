package es.damtfg.IndustrialProcessManagement.service.company;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.company.Company;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.company.CompanyRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return companyRepository.findByName(name);
	}

	@Override
	@Transactional
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	public ApiResponse create(Company company) {

		company.setName(company.getName().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Transactional(readOnly = true)
	public Optional<Company> findById(Long id) {
		return companyRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

}
