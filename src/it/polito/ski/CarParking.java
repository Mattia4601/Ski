package it.polito.ski;

public class CarParking {
	private String name;
	private int availableSlots;
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAvailableSlots() {
		return availableSlots;
	}
	public void setAvailableSlots(int availableSlots) {
		this.availableSlots = availableSlots;
	}
	
	// constructor
	public CarParking(String name, int availableSlots) {
		super();
		this.name = name;
		this.availableSlots = availableSlots;
	}
	
}
