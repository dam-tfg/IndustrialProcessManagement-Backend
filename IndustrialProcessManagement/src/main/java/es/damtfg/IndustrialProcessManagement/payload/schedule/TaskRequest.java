package es.damtfg.IndustrialProcessManagement.payload.schedule;

import java.util.Date;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
import es.damtfg.IndustrialProcessManagement.payload.products.OrderDetailsRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class TaskRequest extends OrderDetailsRequest{
	
	private Date date;

	private OrderDetails orderDetails;

}
