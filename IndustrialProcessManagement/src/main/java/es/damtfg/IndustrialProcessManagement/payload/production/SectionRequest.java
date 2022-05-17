package es.damtfg.IndustrialProcessManagement.payload.production;

import es.damtfg.IndustrialProcessManagement.model.production.Line;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class SectionRequest {
	
	private String name;

	private Line line;

}
