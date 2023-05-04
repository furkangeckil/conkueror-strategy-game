package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private User owner;
    private Continent continent;
    private List<Army> armies;
    private List<Territory> neighbours;

    public Territory(List<Territory> neighbours, Continent continent) {
        this.continent = continent;
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

    // TODO: implement isconneccted method

}