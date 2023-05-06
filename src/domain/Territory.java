package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private User owner;
    private Continent continent;
    private List<Army> armies;
    private int armyNum;
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

    public Continent getContinent() {
        return continent;
    }

    public void setBelongTo(Continent belongTo) {
        this.continent = belongTo;
    }

    public int getArmyNum() {
        return armyNum;
    }

    public void setArmyNum(int armyNum) {
        this.armyNum = armyNum;
    }

    public void addArmy(int i) {
        armyNum += i;
    }

    public void removeArmy(int i) {
        armyNum -= i;
    }

    public List<Territory> getNeighbours() {
        return neighbours;
    }

    // TODO: implement isconneccted method

}