package es.damtfg.IndustrialProcessManagement.service.production;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.production.Process;
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
	public ApiResponse create(Process proceso) {

		proceso.setName(proceso.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	public Boolean existsByName(String name) {
		return processRepository.findByName(name);
	}

	@Override
	public Process save(Process process) {
		return processRepository.save(process);
	}

	@Transactional(readOnly = true)
	public Optional<Process> findById(Long id) {
		return processRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Process> findAll() {
		return processRepository.findAll();
	}

	
}
