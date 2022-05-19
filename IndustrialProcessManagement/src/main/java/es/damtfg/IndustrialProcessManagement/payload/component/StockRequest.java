package es.damtfg.IndustrialProcessManagement.payload.component;

import es.damtfg.IndustrialProcessManagement.model.component.Component;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class StockRequest{
	
	private String unit;

	private Component component;

}
