package domain;

public class Infantry extends Army {
    private int color;
    private Territory placedIn;
    private Player belongTo;
    
    public Infantry(Territory placedIn, Player owner) {
        super(placedIn, owner);
        //TODO Auto-generated constructor stub
    }
}