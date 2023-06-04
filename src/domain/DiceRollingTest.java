package domain;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class DiceRollingTest {

    Player attacker, defender;
    Territory attackerTerritory, defenderTerritory;

    @Before
    public void setUp() {
        attacker = new Player("Attacker");
        defender = new Player("Defender");
    }

    @Test
    public void testRollSimpleDice_AttackerWins() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, 6, 3);

        assertEquals(5, attackerTerritory.getArmyNum());
        assertEquals(4, defenderTerritory.getArmyNum());
    }

    @Test
    public void testRollSimpleDice_DefenderWins() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, 3, 6);

        assertEquals(3, attackerTerritory.getArmyNum());
        assertEquals(5, defenderTerritory.getArmyNum());
    }

    @Test
    public void testRollSimpleDice_Draw() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, 3, 3);

        assertEquals(3, attackerTerritory.getArmyNum());
        assertEquals(5, defenderTerritory.getArmyNum());
    }

    @Test(expected = InsufficientUnitsException.class)
    public void testRollSimpleDice_NotSufficientUnitsToAttack() {
        attackerTerritory = new Territory("A", attacker, null, 1);
        defenderTerritory = new Territory("B", defender, null, 5);
        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, 3, 3);
    }

    @Test
    public void testRollSimpleDice_TerritoryIsOccupied() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 1);
        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, 6, 3);

        assertEquals(attacker, defenderTerritory.getOwner());
    }
}
