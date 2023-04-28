package domain;

public class Artillery extends Army {

    private int color;
    private Territory placedIn;
    private Player belongTo;
    
	public Artillery(int color, Territory placedIn, Player belongTo) {
		super();
		this.color = color;
		this.placedIn = placedIn;
		this.belongTo = belongTo;
	}
    
	

      


}