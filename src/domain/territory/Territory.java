package domain.territory;

import java.awt.Shape;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

import domain.army.Army;
import domain.controllers.UpdateController;
import domain.player.Player;
import domain.util.Observable;

public class Territory extends Observable {
    private List<Army> armies = new ArrayList<Army>();
    private int armyNum;
    private List<Territory> neighbors = new ArrayList<Territory>();
    private Player owner;
    private Continent continent;
    private String name;
    
    
    public Territory(String name, Player owner, Continent continent, int armyNum) {
        this.owner = owner;
        this.continent = continent;
        this.name = name;
        this.armyNum = armyNum;
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

    public List<Territory> getNeighbors() {
        return neighbors;
    }
    
    public void addNeighbor(Territory territory) {
    	this.neighbors.add(territory);
    }

    public boolean isConnected(Territory territory) {
    	
    	boolean connected = false;
    	
    	for(Territory trtry : neighbors) {
    		if(trtry.getName() == territory.getName()) {
    			connected = true;
    		}
    	}
    	
    	return connected;
    }
    
}
