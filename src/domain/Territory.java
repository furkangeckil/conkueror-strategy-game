package domain;

public class Territory {
    private Continent continent;
    private Player owner;

    public Territory(Player owner, Continent continent) {
        this.owner = owner;
        this.continent = continent;
    }

    public void chnageOwner(Player newOwner) {
        this.owner = newOwner;
    }

    public Player getOwner() {
        return this.owner;
    }

    public Continent getContinent() {
        return continent;
    }
}