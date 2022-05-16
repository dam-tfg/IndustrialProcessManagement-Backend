package es.damtfg.IndustrialProcessManagement.service.production;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.damtfg.IndustrialProcessManagement.model.production.LogEvent;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.production.LogEventRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
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
	public Boolean existsByName(String name) {
		// TODO Auto-generated method stub
		return logEventRepository.findByName(name);
	}

	@Override
	public LogEvent save(LogEvent logEvent) {
		// TODO Auto-generated method stub
		return logEventRepository.save(logEvent);
	}

}
