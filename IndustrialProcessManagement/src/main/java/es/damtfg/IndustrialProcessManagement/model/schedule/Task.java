/**
 * 
 */
package es.damtfg.IndustrialProcessManagement.model.schedule;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import es.damtfg.IndustrialProcessManagement.model.product.OrderDetails;
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
@Table(name = ModelDataBase.SCHEDULE_TASK)
public class Task implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 3356418987371997410L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId")
	private Long id;
	
	@NonNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", 
			updatable = true)
	private Date date; 
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "orderDetailsId",
			nullable = false,
			unique = true)
	@JsonBackReference
	private OrderDetails orderDetails;
	
}
