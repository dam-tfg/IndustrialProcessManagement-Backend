package es.damtfg.IndustrialProcessManagement.payload.schedule;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class TaskRequest {
	
	@NotBlank
	private Date date;

}
