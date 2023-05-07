package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory extends Observable{
    private List<Army> armies;
    private List<Territory> neighbours;
    private Player owner;
    private Continent continent;
    private String name;
    private static Observer observer;
    
    public Territory(String name, Player owner, Continent continent) {
        this.owner = owner;
        this.continent = continent;
        this.name = name;
        observer = new Controller();
   
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

    public List<Army> getArmies() {
        return armies;
    }

    public void addArmy(Army army) {
        this.armies.add(army);
        notifyObservers();
    }

    public void removeArmy(Army army) {
        this.armies.remove(army);
        notifyObservers();
    }

    public List<Territory> getNeighbours() {
        return neighbours;
    }
    
    // TODO: implement isconneccted method

}