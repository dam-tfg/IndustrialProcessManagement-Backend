package es.damtfg.IndustrialProcessManagement.payload.products;

import java.util.Date;
import java.util.Set;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author  Carlos Munoz
 *
 */
@Getter
@Setter
public class OrderRequest extends OrderDetailsRequest{
	
	private Date date;
	
	private Set<OrderDetails> orderDetails;

}
