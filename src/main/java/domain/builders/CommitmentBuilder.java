package domain.builders;

import java.math.BigDecimal;
import java.util.Date;

import domain.model.Commitment;
import domain.model.Grouper;
import domain.model.Movement;
import domain.model.Type;

public class CommitmentBuilder {
	
	private String description;
	private Movement movement;
	private int plot;
	private int totalPlots;
	private Type type;
	private BigDecimal effectiveValue;
	private BigDecimal expectedValue;
	private Grouper grouper;
	private Date expiryDate;
	private int id;
	
	public static CommitmentBuilder newCommitment() {
		return new CommitmentBuilder();
	}
	
	public Commitment build(){
		return new Commitment(id, description, movement, type, plot,  
				totalPlots, effectiveValue, expectedValue, grouper, 
				expiryDate);
	}
	
	public CommitmentBuilder withDescription(String description){
		this.description = description;
		return this;
	}
	
	public CommitmentBuilder withMovement(Movement movement){
		this.movement = movement;
		return this;
	}
	
	public CommitmentBuilder withPlot(int plot){
		this.plot = plot;		
		return this;
	}
	
	public CommitmentBuilder withTotalPlots(int totalPlots){
		this.totalPlots = totalPlots;
		return this;
	}
	
	public CommitmentBuilder withType (Type type){
		this.type = type;
		return this;
	}
	
	public CommitmentBuilder withEffectiveValue(BigDecimal effectiveValue){
		this.effectiveValue = effectiveValue;
		return this;
	}	

	public CommitmentBuilder withExpectedValue(BigDecimal expectedValue){
		this.expectedValue = expectedValue;		
		return this;
	}
	
	public CommitmentBuilder withGrouper(Grouper grouper){
		this.grouper = grouper;
		return this;
	}
	
	public CommitmentBuilder withExpiryDate(Date expiryDate){
		this.expiryDate = expiryDate;
		return this;
	}

	public CommitmentBuilder withId(int id) {
		this.id = id; 
		return this;
	}
}
