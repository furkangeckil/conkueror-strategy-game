package domain;

public class Territory {
    private Continent continent;
    private Player owner;
    
	public Territory(Continent continent) {
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
    
    //sets the owner of the territory as given player
    public void changeOwner(Player newOwner) {
    	this.owner = newOwner;
    }

	public Player getOwner() {
		return owner;
	}
    
}