import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DiceRollingTest {

    private Player attacker, defender;
    private Territory attackerTerritory, defenderTerritory;

    @Mock
    private Dice attackerDice, defenderDice;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        attacker = new Player();
        defender = new Player();
        attackerTerritory = new Territory("A", attacker, null, 5);
        defenderTerritory = new Territory("B", defender, null, 5);
    }

    @Test
    public void testRollSimpleDice_AttackerWins() {
        when(attackerDice.roll()).thenReturn(6);
        when(defenderDice.roll()).thenReturn(3);

        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, attackerDice, defenderDice);

        assertEquals(3, attackerTerritory.getArmyNum());
        assertEquals(5, defenderTerritory.getArmyNum());
    }

    @Test
    public void testRollSimpleDice_DefenderWins() {
        when(attackerDice.roll()).thenReturn(3);
        when(defenderDice.roll()).thenReturn(6);

        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, attackerDice, defenderDice);

        assertEquals(5, attackerTerritory.getArmyNum());
        assertEquals(4, defenderTerritory.getArmyNum());
    }

    @Test
    public void testRollSimpleDice_AttackerLosesAll() {
        when(attackerDice.roll()).thenReturn(6);
        when(defenderDice.roll()).thenReturn(3);
        attackerTerritory.setArmyNum(2);

        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, attackerDice, defenderDice);

        assertEquals(0, attackerTerritory.getArmyNum());
        assertEquals(5, defenderTerritory.getArmyNum());
    }

    @Test
    public void testRollSimpleDice_DefenderLosesAll() {
        when(attackerDice.roll()).thenReturn(3);
        when(defenderDice.roll()).thenReturn(6);
        defenderTerritory.setArmyNum(1);

        attacker.rollSimpleDice(attackerTerritory, defenderTerritory, attackerDice, defenderDice);

        assertEquals(5, attackerTerritory.getArmyNum());
        assertEquals(0, defenderTerritory.getArmyNum());
    }
    @Test
    public void testRollSimpleDice_Draw() {
        // Setting up territories and players.
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Territory attackerTerritory = new Territory("A", player1, null, 5);
        Territory defenderTerritory = new Territory("B", player2, null, 5);

        // Preset dice results for the test.
        int[] attackerResults = {3};
        int[] defenderResults = {3};

        when(attackerDice.getDieResults()).thenReturn(attackerResults);
        when(defenderDice.getDieResults()).thenReturn(defenderResults);

        game.rollSimpleDice(attackerTerritory, defenderTerritory);

        // attackerDice = defenderDice, so neither should lose any armies.
        assertEquals(4, attackerTerritory.getArmyNum());
        assertEquals(5, defenderTerritory.getArmyNum());
    }
}
