package domain.builders;

import java.util.Date;

import domain.model.Grouper;

public class GrouperBuilder {
	
	private String description;
	private int id;
	private Date expiryDate;
	
	public static GrouperBuilder newGrouper(){
		return new GrouperBuilder();		
	}

	public GrouperBuilder withDescription(String description){
		this.description = description;
		return this;
	}
	
	public GrouperBuilder withId(int id){
		this.id = id;
		return this;
	}
	
	public GrouperBuilder withExpiryDate(Date expiryDate){
		this.expiryDate = expiryDate;
		return this;
	}
	
	public Grouper build(){
		return new Grouper(id, description, expiryDate);
	}
}
