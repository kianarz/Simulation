
public class Station {
	private int numOfBikes;
	private int numOfDocks;
	private int capacity;
	private String name;
	
	public String getName() {
		return this.name;
	}
	public Station(String nameIn) {
		this.name = nameIn;
	}
	public void setCapacity(int numIn) {
		this.capacity = numIn;
	}
	public void setNumBike(int numIn) {
		this.numOfBikes = numIn;
	}
	public void setNumDock(int numIn) {
		this.numOfDocks = numIn;
	}
	public void addBike(int numIn) {
		this.numOfBikes = this.numOfBikes + numIn;
		this.numOfDocks = this.numOfDocks - numIn;
	}
	public void removeBike(int numIn) {
		this.numOfBikes = this.numOfBikes - numIn;
		this.numOfDocks = this.numOfDocks + numIn;
	}
	public int getNumBike() {
		return numOfBikes;
	}
	public int getNumDock() {
		return numOfDocks;
	}
	public boolean isEmpty() {
		if (numOfBikes == 0) {
			return true;
		} 
		return false;
	}
	public boolean isFull() {
		if (numOfBikes == capacity) {
			return true;
		} 
		return false;
	}
	public void pickup() {
		numOfBikes--;
		numOfDocks++;
	}
	public void dropoff() {
		numOfBikes++;
		numOfDocks--;
	}
	public boolean equals(Object objectIn) {
		if(objectIn instanceof Station) {
			Station copy= (Station)objectIn;
			if(copy.name.equals(this.name)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}
