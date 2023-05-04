package domain;

public class Territory {
    private Continent continent;
    private Player owner;
    private String name;

    public Territory(String name, Player owner, Continent continent) {
        this.owner = owner;
        this.continent = continent;
        this.name = name;
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

    public String getName() {
        return name;
    }
}