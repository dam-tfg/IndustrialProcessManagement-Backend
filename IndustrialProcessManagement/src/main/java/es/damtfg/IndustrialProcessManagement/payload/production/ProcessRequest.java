package es.damtfg.IndustrialProcessManagement.payload.production;


import es.damtfg.IndustrialProcessManagement.model.production.Section;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class ProcessRequest extends SectionRequest{

	private String name;
	
	private Section section;
	
}
