package domain.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedQuery(name="Pagamento.findAll", query="SELECT p FROM Payment p")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Temporal(TemporalType.DATE)
	private Date payDay;

	private BigDecimal amountPaid;

	@ManyToOne
	@JoinColumn(name="commitmentId")
	private Commitment commitment;

	public Payment() {}
	
	public Payment(Date payDay, Commitment commitment, BigDecimal amountPaid)
	{
		this.payDay = payDay;
		this.commitment = commitment;
		this.amountPaid = amountPaid;
	}
	
	public Payment(int id, Date payDay, Commitment commitment, BigDecimal amountPaid){		
		this(payDay, commitment, amountPaid);
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public Date getPayDay() {
		return this.payDay;
	}

	public BigDecimal getAmountPaid() {
		return this.amountPaid;
	}

	public int getCommitmentId(){
		return this.commitment.getId();
	}

	public void setCommitment(Commitment commitment) {
		this.commitment = commitment;
	}
}