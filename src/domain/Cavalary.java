package domain;

public class Cavalary extends Army {

    private int color;
    private Territory placedIn;
    private Player belongTo;
    
	public Cavalary(int color, Territory placedIn, Player belongTo) {
		super();
		this.color = color;
		this.placedIn = placedIn;
		this.belongTo = belongTo;
	}
    
	


}
