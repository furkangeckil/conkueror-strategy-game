package domain;

public class ArmyCard extends Card{
    
	private String type;

	public ArmyCard(String type, Player owner) {
		super();
		this.type = type;
		this.owner = owner;
		this.type = "Army";
	}
	
	@Override
	public void applyEffect() {
		// Implement applyEffect logic

	}

    
}
