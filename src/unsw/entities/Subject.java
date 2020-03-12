package unsw.entities;
/**
 * Subject Interface
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public interface Subject {
	
	public void notifyObservers();
	
	public void attach(Observer observer);
	
	public void detach(Observer observer);
	
}
