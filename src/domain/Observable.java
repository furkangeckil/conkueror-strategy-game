package domain;

import java.util.*;

public abstract class Observable {
	private List<Observer> observers = new ArrayList<Observer>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);	
	}
	
	public void notifyObservers() {
		for(Observer observer : this.observers) {
			observer.update(this);
		}
	}
}
