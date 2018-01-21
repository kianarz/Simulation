
public interface Visitor {
	public double getNextEvent();
	public void postProceed(double nextEvent);
	public boolean getAlive();
	public String getName();
	public double getEnterTime();

}
