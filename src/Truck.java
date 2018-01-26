import java.util.ArrayList;
import java.util.List;

public class Truck implements Visitor {
	private String name;
	private List<Double> enterTime;
	private List<Station> enterStation;
	private List<Boolean> pickOrDrop;  //True if pickup false if drop-off
	private List<Integer> numPickOrDrop;
	private List<Integer> success; //True if returned or picked
	private int currentNumBike;
	private int capacity;
	private World world;
	private boolean alive;
	public Truck(String nameIn, int capacityIn) {
		this.name = nameIn;
		this.capacity = capacityIn;
		this.enterTime = new ArrayList<Double>() ;
		this.enterStation = new ArrayList<Station>();
		this.pickOrDrop = new ArrayList<Boolean>();
		this.numPickOrDrop = new ArrayList<Integer>();
		this.success = new ArrayList<Integer>();
		this.currentNumBike = 0;
		this.alive = true;
	}
	public double getEnterTime() {
	    	return this.enterTime.get(0);	
	}
	public void setWorld(World world){
	    	this.world = world;
	}
	public String getName() {
		return this.name;
	}
	public  List<Integer> getNumPickOrDrop() {
		return this.numPickOrDrop;
	}
	public void addStation(Station stationIn) {
		this.enterStation.add(stationIn);
	}
	public void addPickOrDrop(boolean pickOrDropIn) {
		this.pickOrDrop.add(pickOrDropIn);
	}
	public void addNumPickOrDrop(int numPickOrDropIn) {
		this.numPickOrDrop.add(numPickOrDropIn);
	}
	public void addEnterTime(double enterTimeIn) {
		this.enterTime.add(enterTimeIn);
	}
	public void removeStation(Station stationIn) {
		this.enterStation.remove(stationIn);
	}
	public int getCurrentNumBike() {
		return this.currentNumBike;
	}
	public List<Integer> getSuccess(){
		return this.success;
	}
	public List<Station> getEnterStation(){
		return this.enterStation;
	}
	public void pickup(int numIn) {
		this.currentNumBike = this.currentNumBike + numIn;
	}
	public void dropoff(int numIn) {
		this.currentNumBike = this.currentNumBike - numIn;
	}
	public boolean getAlive() {
		if(this.enterStation.isEmpty()) {
			return false;
		}
		return true;
	}


	public double getNextEvent() {
		if (!this.enterStation.isEmpty()) {
			return this.enterTime.get(0);
		} 
		return Global.INF;
	}
	public void postProceed(double nextEvent) {
		if (this.getAlive() && this.enterTime.get(0) == nextEvent) {
			System.out.println(this.getCurrentNumBike() + "currennt");
			int indStation = world.getStations().indexOf(this.enterStation.get(0));
			if(this.pickOrDrop.get(0) == true) {
				System.out.println(this.numPickOrDrop.get(0) +"pick");
				if(world.getStations().get(indStation).getNumBike() >= this.numPickOrDrop.get(0) && this.capacity>= (this.currentNumBike+this.numPickOrDrop.get(0))){
					this.currentNumBike = this.currentNumBike+this.numPickOrDrop.get(0); 
					this.success.add(this.numPickOrDrop.get(0));
					world.getStations().get(indStation).removeBike(this.numPickOrDrop.get(0));
				} else {
					int min = world.getStations().get(indStation).getNumBike();
					if (min > (this.capacity -this.currentNumBike)) {
						min = this.capacity -this.currentNumBike;
					}
					this.currentNumBike = this.currentNumBike + min;
					this.success.add(min);
					world.getStations().get(indStation).removeBike(min);
				}
			} else {
				System.out.println(this.numPickOrDrop.get(0) +"Drop");
				if(world.getStations().get(indStation).getNumDock() >= this.numPickOrDrop.get(0) && this.currentNumBike >= this.numPickOrDrop.get(0)){
					this.currentNumBike = this.currentNumBike-this.numPickOrDrop.get(0); 
					this.success.add(this.numPickOrDrop.get(0));
					world.getStations().get(indStation).addBike(this.numPickOrDrop.get(0));
				} else {
					int min = world.getStations().get(indStation).getNumDock();
					if (min > this.currentNumBike) {
						min = this.currentNumBike;
					}
					this.currentNumBike = this.currentNumBike - min;
					this.success.add(min);
					world.getStations().get(indStation).addBike(min);
				}
			}
			this.enterStation.remove(0);
			this.enterTime.remove(0);
			this.numPickOrDrop.remove(0);
			this.pickOrDrop.remove(0);
		}
	}

}
	


