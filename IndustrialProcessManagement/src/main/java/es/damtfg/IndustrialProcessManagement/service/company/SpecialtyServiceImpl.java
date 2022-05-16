package es.damtfg.IndustrialProcessManagement.service.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.company.Specialty;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.company.SpecialtyRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class SpecialtyServiceImpl implements SpecialtyService {
	
	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	@Transactional
	public ApiResponse create(Specialty specialty) {

		specialty.setName(specialty.getName().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return specialtyRepository.findByName(name);
	}

	@Override
	@Transactional
	public Specialty save(Specialty specialty) {
		return specialtyRepository.save(specialty);
	}


}
