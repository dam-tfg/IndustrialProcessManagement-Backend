/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alberto González
 *
 */
@Data
@AllArgsConstructor
public class ApiResponse {

	private Boolean success;
	private String message;
}
