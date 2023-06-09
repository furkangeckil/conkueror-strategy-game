package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyCardTest {

    private Player player;
    private List<Card> cards;

    @BeforeEach
    public void setup() {
        player = new Player("John");
        cards = new ArrayList<>();
    }

    @Test
    public void testCheckNumOfArmyCard_MultipleArmyTypes() {
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.ARTILLERY.toString()));
        player.setCards(cards);

        int result = ArmyCard.checkNumOfArmyCard(player.getCards(), Army.armyTypes.INFANTRY);
        assertEquals(2, result);
    }

    @Test
    public void testCheckNumOfArmyCard_SingleArmyType() {
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        player.setCards(cards);

        int result = ArmyCard.checkNumOfArmyCard(player.getCards(), Army.armyTypes.INFANTRY);
        assertEquals(3, result);
    }

    @Test
    public void testCheckNumOfArmyCard_NoSpecifiedArmyType() {
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        player.setCards(cards);

        int result = ArmyCard.checkNumOfArmyCard(player.getCards(), Army.armyTypes.ARTILLERY);
        assertEquals(0, result);
    }

    @Test
    public void testCheckNumOfArmyCard_EmptyList() {
        player.setCards(cards);

        int result = ArmyCard.checkNumOfArmyCard(player.getCards(), Army.armyTypes.INFANTRY);
        assertEquals(0, result);
    }

    @Test
    public void testCheckNumOfArmyCard_MixedList() {
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.CAVALRY.toString()));
        cards.add(new ArmyCard(Army.armyTypes.INFANTRY.toString()));
        cards.add(new TerritoryCard(null));
        player.setCards(cards);

        int result = ArmyCard.checkNumOfArmyCard(player.getCards(), Army.armyTypes.INFANTRY);
        assertEquals(2, result);
    }
}