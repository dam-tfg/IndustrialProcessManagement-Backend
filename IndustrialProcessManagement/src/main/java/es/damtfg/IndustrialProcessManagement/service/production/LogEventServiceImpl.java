package es.damtfg.IndustrialProcessManagement.service.production;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.production.LogEventRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * @author Alberto González
 * @author Carlos Munoz
 *
 */
@Service
public class LogEventServiceImpl implements LogEventService {

	@Autowired
	private LogEventRepository logEventRepository;

	@Override
	public ApiResponse create(LogEvent logEvent) {

		logEvent.setName(logEvent.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);

	}

	@Override
	public Optional<LogEvent> findByName(String name) {
		return logEventRepository.findByName(name);
	}

	@Override
	public Boolean existsByName(String name) {
		return logEventRepository.existsByName(name);
	}
	
	@Override
	public LogEvent save(LogEvent logEvent) {
		return logEventRepository.save(logEvent);
	}

	@Transactional(readOnly = true)
	public Optional<LogEvent> findById(Long id) {
		return logEventRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<LogEvent> findAll() {
		return logEventRepository.findAll();
	}
	
	@Override
	@Transactional
	public List<LogEvent> saveAll(ArrayList<LogEvent> event) {
				
		int index = 0;
		
		while(index < event.size()) {
			
			if(existsByName(event.get(index).getName())) {
				
				event.remove(index);
				
			} else {
				
				index++;
			}
		}
				
		return logEventRepository.saveAll(event);
	}

}
