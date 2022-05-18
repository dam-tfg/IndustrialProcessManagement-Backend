package es.damtfg.IndustrialProcessManagement.service.production;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.production.LineRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class LineServiceImpl implements LineService {
	
	@Autowired
	private LineRepository lineRepository;

	@Override
	@Transactional
	public ApiResponse create(Line lineEvent) {

		lineEvent.setName(lineEvent.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return lineRepository.findByName(name);
	}

	@Override
	@Transactional
	public Line save(Line line) {
		return lineRepository.save(line);
	}

	@Transactional(readOnly = true)
	public Optional<Line> findById(Long id) {
		return lineRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Line> findAll() {
		return lineRepository.findAll();
	}

}
