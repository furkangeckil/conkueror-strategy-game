package domain;

public class Territory {
    private Continent continent;

    public Territory(Continent continent) {
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
}