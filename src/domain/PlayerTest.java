package domain;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import domain.Army.armyTypes;

public class PlayerTest {

	Player player;
	ArrayList<Card> cards;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Jane");
		cards = new ArrayList<Card>();
		cards.add(new ArmyCard(armyTypes.INFANTRY.toString()));
		cards.add(new ArmyCard(armyTypes.ARTILLERY.toString()));
		cards.add(new ArmyCard(armyTypes.ARTILLERY.toString()));
		player.setCards(cards);
	}

	@Test
	public void removeZeroCardsFromPlayer() {
		// num = 0;
		player.removeCardFromPlayer(armyTypes.INFANTRY, 0);
		List<Card> remainingCards = player.getCards();
		assertEquals(3,remainingCards.size());
	

	}
	
	@Test
	public void removeTooManyCardsFromPlayer() {
		// num > # of cards;
		player.removeCardFromPlayer(armyTypes.INFANTRY, 2);
		List<Card> remainingCards = player.getCards();
		assertEquals(2,remainingCards.size());
	

	}
	
	@Test
	public void removeAllCardsFromPlayer() {
		// num = # of cards;
		player.removeCardFromPlayer(armyTypes.INFANTRY, 1);
		player.removeCardFromPlayer(armyTypes.ARTILLERY, 2);
		List<Card> remainingCards = player.getCards();
		assertEquals(0,remainingCards.size());
	

	}
	
	@Test
	public void removeWrongTypeOfCardFromPlayer() {
		// type != ARTILLERY; INFANTRY
		player.removeCardFromPlayer(armyTypes.CAVALRY, 1);
		List<Card> remainingCards = player.getCards();
		assertEquals(3,remainingCards.size());
	

	}
	
	@Test
	public void removeSomeCardsFromPlayer() {
		// 0 < num < cards.size()
		player.removeCardFromPlayer(armyTypes.ARTILLERY, 1);
		List<Card> remainingCards = player.getCards();
		assertEquals(2,remainingCards.size());
	

	}
	
	
	

}
