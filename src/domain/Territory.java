package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private List<Army> armies;
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