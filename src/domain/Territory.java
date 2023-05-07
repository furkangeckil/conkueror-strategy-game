package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory extends Observable {
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
        this.attach(UpdateController.getInstance());
    }

    public Player getOwner() {
        return this.owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
        notifyObservers();
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
        notifyObservers();
    }

    public void addArmy(int i) {
        armyNum += i;
        notifyObservers();
    }

    public void removeArmy(int i) {
        armyNum -= i;
        notifyObservers();
    }

    public List<Territory> getNeighbours() {
        return neighbours;
    }

    // TODO: implement isconneccted method

}
