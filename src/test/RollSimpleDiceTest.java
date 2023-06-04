package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.Player;
import domain.Territory;
import domain.WarController;

public class RollSimpleDiceTest {

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
        attackerTerritory.addNeighbor(defenderTerritory);
        int[] result = WarController.getInstance().rollSimpleDice(attackerTerritory, defenderTerritory);
        
        if(result[0] > result[1]) {
        	assertEquals(5, attackerTerritory.getArmyNum());
            assertEquals(4, defenderTerritory.getArmyNum());
        }
           
    }

    @Test
    public void testRollSimpleDice_DefenderWins() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
        attackerTerritory.addNeighbor(defenderTerritory);
        int[] result = WarController.getInstance().rollSimpleDice(attackerTerritory, defenderTerritory);
        
        if(result[0] < result[1]) {
        	assertEquals(3, attackerTerritory.getArmyNum());
            assertEquals(5, defenderTerritory.getArmyNum());
        }
        
    }

    @Test
    public void testRollSimpleDice_Draw() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
        attackerTerritory.addNeighbor(defenderTerritory);
        int[] result = WarController.getInstance().rollSimpleDice(attackerTerritory, defenderTerritory);
        
        if(result[0] == result[1]) {
        	assertEquals(3, attackerTerritory.getArmyNum());
            assertEquals(5, defenderTerritory.getArmyNum());
        }
    }

    @Test
    public void testRollSimpleDice_NotSufficientUnitsToAttack() {
        attackerTerritory = new Territory("A", attacker, null, 1);
        defenderTerritory = new Territory("B", defender, null, 5);
        attackerTerritory.addNeighbor(defenderTerritory);
        int[] result = WarController.getInstance().rollSimpleDice(attackerTerritory, defenderTerritory);
  
        assertEquals(null, result);
    }

    @Test
    public void testRollSimpleDice_TerritoryIsOccupied() {
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 1);
        attackerTerritory.addNeighbor(defenderTerritory);
        int[] result = WarController.getInstance().rollSimpleDice(attackerTerritory, defenderTerritory);
        
        if(result[0] > result[1]) {
            assertEquals(attacker, defenderTerritory.getOwner());
        }
    }
}