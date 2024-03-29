package it.polito.ski;

public class Lift {
	
	private String name;
	private String typeCode;
	private boolean servedByParking=false;
	
	public void setFlagParking() {
		this.servedByParking=true;
	}
	
	public boolean getFlagParking() {
		return this.servedByParking;
	}
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	// contructor
	public Lift(String name, String typeCode) {
		super();
		this.name = name;
		this.typeCode = typeCode;
	}
	
}
