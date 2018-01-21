
public class User implements Visitor{
	private double enterTime;
	private Station enterStation;
	private boolean pickOrDrop;  //True if pickup false if drop-off
	private boolean success; //True if returned or picked
	private String name;
    private boolean alive; //True if not processed False if processed
    private World world;
	//private boolean usingBike = true;
    public User(String nameIn) {
    	this.name = nameIn;
    	this.success = true;
    	this.alive = true;
    }
    public void setWorld(World world){
    	this.world = world;
    }
    public boolean getAlive() {
    	return this.alive;
    }
    public String getName() {
    	return this.name;
    }
    public boolean getSuccess() {
    	return this.success;	
    }
    public boolean getPickOrDrop() {
    	return this.pickOrDrop;
    }
    public Station getEnterStation() {
    	return this.enterStation;	
    }
    public double getEnterTime() {
    	return this.enterTime;	
    }
	public void setEnterStation(Station enterStationIn) {
		this.enterStation = enterStationIn;
	}
	public void setEnterTime(double enterTimeIn) {
		this.enterTime = enterTimeIn;
	}
	public void setPickOrDrop(boolean pickOrDropIn) {
		this.pickOrDrop = pickOrDropIn;
	}
	
	/////////////////////changed///////////////////////////
	public double getNextEvent() {
		if (Global.time < this.enterTime) {
		return this.enterTime;
		}
		return Global.INF;
	}
	
	public void postProceed(double nextEvent) {
		if ((this.enterTime - nextEvent) <= Global.EPS && this.getAlive()) {
			int indStation = world.getStations().indexOf(this.enterStation);
			if (this.pickOrDrop) {
				if (!(world.getStations().get(indStation).isEmpty())) {
					world.getStations().get(indStation).pickup();
				} else {
					this.success = false;
				}
				this.alive = false;
			} else {
				if (!(world.getStations().get(indStation).isFull())) {
					world.getStations().get(indStation).dropoff();
				} else {
					this.success = false;

				}
				this.alive=false;	
			}
		}
	}
	
}

