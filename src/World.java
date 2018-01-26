import java.util.ArrayList;
import java.util.List;

public class World {
	private List<User> users;
	private List<Station> stations;
	private List<Truck> trucks;
	private List<Visitor> visitors;

	public World(){
		this.users = new ArrayList<User>();
		this.stations = new ArrayList<Station>();
		this.visitors = new ArrayList<Visitor>();
	}
	public void addUsers(User userIn) {
		users.add(userIn);
	}
	public void addStations(Station stationIn) {
		stations.add(stationIn);
	}
	public void addTruck(Truck truckIn) {
		trucks.add(truckIn);
	}
	public void addVisitor(Visitor visitorIn) {
		visitors.add(visitorIn);
	}
	public List<Visitor> getVisitors() {
		return visitors;
	}
	
	public List<User> getUsers() {
		return users;
	}
	public List<Station> getStations() {
		return stations;
	}

	public void removeUsers(User userIn) {
		users.remove(userIn);
	}
	private boolean finished() {
		for(Visitor visitor: visitors) {
			if (visitor.getAlive()) {
				return false;
			}
		}
		return true;
	}
	public void run() {
		while (!finished()) {
			double nextEvent = Global.INF;
			for(Visitor visitor:visitors) {
				
				nextEvent = Math.min(nextEvent, visitor.getNextEvent());
			}
			System.out.println("This is the nextEvent " + nextEvent);
			Global.proceed(nextEvent-Global.time);
		
			for(Visitor visitor:visitors) {
				visitor.postProceed(nextEvent);
			
				//System.out.println(user.getName() + user.getHasStarted());
				/*int a = stations.indexOf(user.getDestination());
				System.out.println(stations.get(a).getName() + stations.get(a).getNumBike());
				System.out.println(stations.get(a).getName() + stations.get(a).getNumDock());
				int b = stations.indexOf(user.getOrigin());
				System.out.println(stations.get(b).getName() + stations.get(b).getNumBike());
				System.out.println(stations.get(b).getName() + stations.get(b).getNumDock());*/
			}
			
			for(Station station: stations) {
				if(station.getName().equals("Jefferson Dr & 14th St SW")) {
					System.out.println(station.getNumBike()+ ":" +station.getName());
				}
			}
			System.out.println("I got this");
			
			
		}
		/*for(User user:users) {
			System.out.println(user.getName() + user.getHasStarted());
			int a = stations.indexOf(user.getDestination());
			System.out.println(stations.get(a).getName() + stations.get(a).getNumBike());
			System.out.println(stations.get(a).getName() + stations.get(a).getNumDock());
			int b = stations.indexOf(user.getOrigin());
			System.out.println(stations.get(b).getName() + stations.get(b).getNumBike());
			System.out.println(stations.get(b).getName() + stations.get(b).getNumDock());
		}*/
		System.out.println("here");
	}
}
