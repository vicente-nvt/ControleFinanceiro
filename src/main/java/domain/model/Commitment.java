package domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name="Compromisso.findAll", query="SELECT c FROM Commitment c")
public class Commitment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;
	
	private String description;
	
	private String movement;
	
	private int plot;
	
	private String type;
	
	private int totalPlots;
	
	private BigDecimal effectiveValue;
	
	private BigDecimal expectedValue;	
	
	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@ManyToOne
	@JoinColumn(name="grouperId")
	private Grouper grouper;

	@OneToMany(mappedBy="commitment")
	private List<Payment> payments;

	public Commitment() {
	}
	
	public Commitment(String description, Movement movement, int plot,
		Type type, int totalPlots, BigDecimal effectiveValue, 
		BigDecimal expectedValue, Grouper grouper, Date expiryDate)
	{
		this.movement = movement.toString();
		this.type = type.toString();
		this.plot = plot;
		this.totalPlots = totalPlots;
		this.effectiveValue = effectiveValue;
		this.expectedValue = expectedValue;
		this.grouper = grouper;
		this.expiryDate = expiryDate;
	}

	public int getId() { return this.id; }
	public String getDescription() {	return this.description;	}
	public Movement getMovement(){ return Movement.valueOf(this.movement);	}
	public int getPlot() { return this.plot; }
	public Type getType() {	return Type.valueOf(this.type);	}
	public int getTotalPlots() { return this.totalPlots; }
	public BigDecimal getEffectiveValue() { return this.effectiveValue; }
	public BigDecimal getExpectedValue() { return this.expectedValue; }
	public Grouper getGrouper() { return this.grouper; }
	public List<Payment> getPayments() { return this.payments; }
	public Date getExpiryDate() { return expiryDate; }

	public void setGrouper(Grouper grouper) { this.grouper = grouper; }
	
	public Payment addPagamento(Payment payment) {
		getPayments().add(payment);
		payment.setCommitment(this);

		return payment;
	}

	public Payment removePagamento(Payment payment) {
		getPayments().remove(payment);
		payment.setCommitment(null);

		return payment;
	}
	
	public boolean equals (Commitment commitment) {
		return this.getId() == commitment.getId() &&
			   this.getDescription().equals(commitment.getDescription()) &&
			   this.getMovement().compareTo(commitment.getMovement()) == 0 &&
			   this.getType().compareTo(commitment.getType()) == 0 &&
			   this.getPlot() == commitment.getPlot() &&
			   this.getTotalPlots() == commitment.getTotalPlots() &&
			   this.getEffectiveValue().equals(commitment.getEffectiveValue()) &&
			   this.getExpectedValue().equals(commitment.getExpectedValue());
			   
	}	
}