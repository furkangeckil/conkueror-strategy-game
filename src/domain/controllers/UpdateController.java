package domain.controllers;

import domain.territory.Territory;
import domain.util.Observable;
import domain.util.Observer;

public class UpdateController extends Observer {

	private static UpdateController instance;
	
	private void UpdateController() {
		
	}
	
	public static UpdateController getInstance() {
		if(instance == null) {
			instance = new UpdateController();
			return instance;
		}
		else {
			return instance;
		}
	}
	
	@Override
	public void update(Observable subject) {
		if(subject instanceof Territory) {
			Territory territory = (Territory) subject;
			Controller.getInstance().updateTerritoryOnMap(territory);
		}
	
	}
}
