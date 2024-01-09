package it.polito.ski;

public class Lift {
	
	private String code;
	private String category;
	private int capacity;
	
	public Lift(String code, String category, int capacity) {
		super();
		this.code = code;
		this.category = category;
		this.capacity = capacity;
	}
	
	// getters and setters
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
