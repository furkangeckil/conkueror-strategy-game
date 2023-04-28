package domain;

public class Army {
    
	// TODO implement army crowd mechanics
    private Player owner;
	private Territory placedIn;
    
	public Army(Territory placedIn, Player owner) {
		this.owner = owner;
		this.placedIn = placedIn;
	}
    
    public void addToPlayer (Player player ) {
    	owner = player;
    }

	public boolean moveValidator(Territory to) {
		for ()
	}

	public void moveArmy(Territory destination) {
		if (moveValidator(destination)) {
			placedIn = destination;
		}
	}


}

