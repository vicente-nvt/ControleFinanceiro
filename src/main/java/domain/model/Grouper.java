package domain.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Grouper implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date expiryDate;

	@OneToMany(mappedBy="grouper")
	private List<Commitment> commitment;

	public Grouper() {}

	public int getCodigo() { return this.id; }
	
	public void setId(int id) { this.id = id; }

	public String getDescription() { return this.description; }

	public void setDescription(String description) { this.description = description; }
	
	public Date getExpiryDate() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(this.expiryDate);
		Date returnDate;
		
		try {
			returnDate = sdf.parse(date);
		} catch (ParseException e) {			
			returnDate = null;
		}		
		return returnDate;		
	}

	public void setExpiryDate(Date expiryDate) { this.expiryDate = expiryDate; }
	
	private List<Commitment> getCommitments() { return this.commitment; }	

	public Commitment addCompromisso(Commitment commitment) {
		getCommitments().add(commitment);
		commitment.setGrouper(this);

		return commitment;
	}

	public Commitment removeCommitment(Commitment commitment) {
		getCommitments().remove(commitment);
		commitment.setGrouper(null);

		return commitment;
	}
	
	public boolean equals(Grouper grouper){
		return 	grouper.getDescription().equals(this.getDescription()) &&
				grouper.getExpiryDate().compareTo(this.getExpiryDate()) == 0;			
	}
}