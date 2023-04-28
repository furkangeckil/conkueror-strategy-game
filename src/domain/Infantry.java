package domain;

public class Infantry extends Army {

    private int color;
    private Territory placedIn;
    private Player belongTo;
    
	public Infantry(int color, Territory placedIn, Player belongTo) {
		super();
		this.color = color;
		this.placedIn = placedIn;
		this.belongTo = belongTo;
	}

}
