package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private User owner;
    private Continent belongTo;
    private int armies;
    private List<Territory> neighbours;

    public Territory(User owner, Continent belongTo) {
        this.owner = owner;
        this.belongTo = belongTo;
        this.armies = 0;
        this.neighbours = new ArrayList<>();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Continent getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(Continent belongTo) {
        this.belongTo = belongTo;
    }

    public int getArmies() {
        return armies;
    }

    public void setArmies(int armies) {
        this.armies = armies;
    }

    public void addArmy(int i) {
        armies += i;
    }

    public void removeArmy(int i) {
        armies -= i;
    }

    public List<Territory> getNeighbours() {
        return neighbours;
    }


}
