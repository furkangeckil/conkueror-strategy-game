package domain;

public class Territory {
    private Continent continent;
    private int armies;

    public Territory(Continent continent) {
        this.continent = continent;
        this.armies = 0;
    }

    public Continent getContinent() {
        return continent;
    }

    public int getArmies() {
        return armies;
    }

    public void addArmies(int armies) {
        this.armies += armies;
    }

    public void removeArmies(int armies) {
        this.armies -= armies;
        if (this.armies < 0) {
            this.armies = 0;
        }
    }
}
