package observer;

public interface IObserver {
	void addObserver(IListener listener);
	void removeObserver(IListener listener);
	void notifyObserver(Object event,Object obj);
}
