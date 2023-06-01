package test;


import domain.Army;
import domain.ArmyCard;
import domain.Player;
import domain.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TradeArmyCardsTest {

	private Player player;
	private List<Card> cards;

	@BeforeEach
	public void setup() {
		player = new Player("John");
		cards = new ArrayList<>();
	}

	@Test
	public void testTradeArmyCards_Option1() {
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		player.setCards(cards);

		ArmyCard.tradeArmyCards(player, 1);
		assertEquals(5, player.getTotalArmy());
	}

	@Test
	public void testTradeArmyCards_Option2() {
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
		player.setCards(cards);

		ArmyCard.tradeArmyCards(player, 2);
		assertEquals(10, player.getTotalArmy());
	}

	@Test
	public void testTradeArmyCards_Option3() {
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(Army.armyTypes.ARTILLERY.toString()));
		player.setCards(cards);

		ArmyCard.tradeArmyCards(player, 3);
		assertEquals(20, player.getTotalArmy());
	}

    @Test
    public void testTradeArmyCards_Option4() {
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        player.setCards(cards);

        ArmyCard.tradeArmyCards(player, 4);
        assertEquals(15, player.getTotalArmy());
    }

    @Test
    public void testTradeArmyCards_Option5() {
        cards.add(new ArmyCard(Army.armyTypes.ARTILLERY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        player.setCards(cards);

        ArmyCard.tradeArmyCards(player, 5);
        assertEquals(30, player.getTotalArmy());
    }
    
    @Test
    public void testTradeArmyCards_noCards() {
    	ArmyCard.tradeArmyCards(player, 2);
    	assertEquals(0,player.getTotalArmy());
    }
    
    @Test
    public void testTradeArmyCards_notEnoughInfantry() {
        cards.add(new ArmyCard(Army.armyTypes.ARTILLERY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        
        ArmyCard.tradeArmyCards(player, 1);
    	assertEquals(0,player.getTotalArmy());
    }
    
}
