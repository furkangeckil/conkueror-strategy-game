package domain;

public class Army {
    
    private Player owner;
	private Territory placedIn;
    
	public Army(Territory placedIn, Player owner) {
		this.owner = owner;
		this.placedIn = placedIn;
	}

	public boolean moveValidator(Territory to) {
		// Return true if Territory to is a neighbour of placedIn
		return true;
	}

	public void moveArmy(Territory destination) {
		if (moveValidator(destination)) {
			placedIn = destination;
		}
	}


}