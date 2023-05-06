package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private List<Army> armies;
    private int armyNum;
    private List<Territory> neighbours;
    private Player owner;
    private Continent continent;
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
    
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Continent getContinent() {
        return continent;
    }

    public String getName() {
        return name;
    }
    
    public void setContinent(Continent belongTo) {
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
