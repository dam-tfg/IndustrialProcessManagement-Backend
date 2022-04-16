/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.product;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.data.annotation.CreatedDate;

import es.damtfg.IndustrialProcessManagement.model.production.Log;
import es.damtfg.IndustrialProcessManagement.util.constants.ModelDataBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Alberto González
 *
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ModelDataBase.PRODUCT_ORDER)
public class Order implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 2994552616010059169L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private Long id;
	
	@NonNull
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", 
			updatable = false)
	private Date date;
	
	@Min(value = 0)
	@Column(name = "status")
	private int status;
		
	@NonNull
	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@JoinColumn(name = "orderId")
	private Set<OrderDetails> orderDetails;

	@OneToMany(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL,
			orphanRemoval = false)
	@JoinColumn(name = "orderId")
	private Set<Log> log;
	
}
