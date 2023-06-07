package domain.army;

import domain.player.Player;
import domain.territory.Territory;

public class Infantry extends Army {
    private int color;
    private Territory placedIn;
    private Player belongTo;
    
    public Infantry(Territory placedIn, Player owner) {
        super(placedIn, owner);
        //TODO Auto-generated constructor stub
    }
}