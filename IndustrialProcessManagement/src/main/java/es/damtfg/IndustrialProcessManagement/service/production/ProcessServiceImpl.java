package es.damtfg.IndustrialProcessManagement.service.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.damtfg.IndustrialProcessManagement.model.production.Proceso;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.production.ProcessRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author Carlos Munoz
 *
 */
@Service
public class ProcessServiceImpl implements ProcessService{
	
	@Autowired
	private ProcessRepository processRepository;
	
	@Override
	public ApiResponse create(Proceso proceso) {

		proceso.setName(proceso.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	public Boolean existsByName(String name) {
		return processRepository.findByName(name);
	}

	@Override
	public Proceso save(Proceso process) {
		return processRepository.save(process);
	}

	
}
