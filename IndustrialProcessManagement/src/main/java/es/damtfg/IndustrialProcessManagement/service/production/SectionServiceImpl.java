package es.damtfg.IndustrialProcessManagement.service.production;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import es.damtfg.IndustrialProcessManagement.model.production.Section;
import es.damtfg.IndustrialProcessManagement.payload.ApiResponse;
import es.damtfg.IndustrialProcessManagement.repository.production.SectionRepository;
import es.damtfg.IndustrialProcessManagement.util.AppMessages;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Service
public class SectionServiceImpl implements SectionService {
	
	@Autowired
	private SectionRepository sectionRepository;

	@Override
	@Transactional
	public ApiResponse create(Section section) {

		section.setName(section.getName().trim().toLowerCase());

		return new ApiResponse(true, AppMessages.SUCCESS_PRODUCT_CREATION);
		
	}

	@Override
	@Transactional
	public Boolean existsByName(String name) {
		return sectionRepository.findByName(name);
	}

	@Override
	@Transactional
	public Section save(Section section) {
		return sectionRepository.save(section);
	}

	@Transactional(readOnly = true)
	public Optional<Section> findById(Long id) {
		return sectionRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public List<Section> findAll() {
		return sectionRepository.findAll();
	}

	@Transactional
	public Line saveline(Line line) {
		return sectionRepository.save(line);
	}

}
