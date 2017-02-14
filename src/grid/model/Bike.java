package grid.model;

public class Bike {
	
	private String bikeName;
	
	public Bike(String bikeName){
		this.bikeName = bikeName;
	}
	
	@Override
	public String toString() {
		return bikeName;
	}
	
	public String getBikeName() {
		return bikeName;
	}

	public void setBikeName(String bikeName) {
		this.bikeName = bikeName;
	}

}
