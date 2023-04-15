package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private User owner;
    private Continent belongTo;
    private List<Army> armies;
    private List<Territory> neighbours;

    public Territory(User owner, Continent belongTo) {
        this.owner = owner;
        this.belongTo = belongTo;
        this.armies = new ArrayList<>();
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

    public List<Army> getArmies() {
        return armies;
    }

    public void addArmy(Army army) {
        this.armies.add(army);
    }

    public void removeArmy(Army army) {
        this.armies.remove(army);
    }

    public List<Territory> getNeighbours() {
        return neighbours;
    }


}