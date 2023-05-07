package domain;

public class Cavalary extends Army {
    private int color;
    private Territory placedIn;
    private Player belongTo;
    
    public Cavalary(Territory placedIn, Player owner) {
        super(placedIn, owner);
        //TODO Auto-generated constructor stub
    }
}