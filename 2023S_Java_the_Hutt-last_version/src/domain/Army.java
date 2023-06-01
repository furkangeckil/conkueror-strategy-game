package domain;

public class Army {
    
	private armyTypes type;
    private Player owner;
    private Territory territory;
    
    //constructor
	public Army(String type, Player owner) {
		this.type = transformType(type);
		this.owner = owner;
	}
	
	//constructor
	public Army(String type, Player owner, Territory territory) {
		this.type = transformType(type);
		this.owner = owner;
		this.territory = territory;
	}
    
	public Army(Territory placedIn, Player owner2) {
	}

	//sets the owner of the card as given player
    public void addToPlayer(Player player) {
		this.owner=player;
		player.addArmyToPlayer(this);
	}
    
    //sets the territory of the card as given territory
    public void placeToTerritory(Territory territory) {
		if(this.owner.getTerritories().contains(territory)) {
			this.territory = territory;
		}
	}
    
    //gives armyTypes for the given army type string 
    public static armyTypes transformType(String type) {
    	switch (type){
			case "Artillery":
			case "ARTILLERY":
            	return armyTypes.ARTILLERY;
			case "Cavalry":
			case "CAVALRY":
            	return armyTypes.CAVALRY;
			case "Infantry":
			case "INFANTRY":
            	return armyTypes.INFANTRY;
        }
		return null;
    	
    }
	 
    
    //army types
    public static enum armyTypes {
    	ARTILLERY, CAVALRY, INFANTRY
	}

	@Override
	public String toString() {
		return "Army [type=" + type + ", owner=" + owner + ", territory=" + territory + "]";
	}
    
    
}
