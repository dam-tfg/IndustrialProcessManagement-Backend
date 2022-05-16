package es.damtfg.IndustrialProcessManagement.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.component.ComponentRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class ComponentServiceImpl implements ComponentService {
	
	@Autowired
	private ComponentRepository componentRepository;

	@Override
	@Transactional
	public ApiResponse create(Component component) {

		component.setName(component.getName().trim().toLowerCase());
		
		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return componentRepository.findByName(name);
	}

	@Override
	@Transactional
	public Component save(Component component) {
		return componentRepository.save(component);
	}

}
