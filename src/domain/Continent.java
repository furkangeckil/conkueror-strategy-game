package domain;

import java.util.ArrayList;
import java.util.List;

public class Continent {
    private List<Territory> territories;

    public Continent() {
        this.territories = new ArrayList<>();
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }
}