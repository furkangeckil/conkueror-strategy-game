package domain;

import java.util.ArrayList;
import java.util.List;

public class Territory extends Observable {
    private List<Army> armies = new ArrayList<Army>();
    private int armyNum;
    private List<Territory> neighbors = new ArrayList<Territory>();
    private Player owner;
    private Continent continent;
    private String name;
    
    
    public Territory(String name, Player owner, Continent continent) {
        this.owner = owner;
        this.continent = continent;
        this.name = name;
        this.armyNum = 0;
        //this.attach(UpdateController.getInstance());
    }

    public Player getOwner() {
        return this.owner;
    }
    
    public void setOwner(Player owner) {
        this.owner = owner;
        //notifyObservers();
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
        //notifyObservers();
    }

    public void addArmy(int i) {
        armyNum += i;
        //notifyObservers();
    }

    public void removeArmy(int i) {
        armyNum -= i;
        //notifyObservers();
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
