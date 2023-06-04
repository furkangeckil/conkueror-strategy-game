package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	
	// Test if the `placeArmies` method works correctly when the player owns the territory:
	@Test
	public void testPlaceArmies_OwnTerritory() {
	    Player player = new Player("Ahmet");
	    Territory territory = new Territory("Turkey", player, new Continent(), 0);
	    player.setUnassignedArmies(5);
	    int result = player.placeArmies(territory);
	    assertEquals(0, result);
	    assertEquals(4, player.getUnassignedArmies());
	    assertEquals(1, territory.getArmyNum());
	}

	// Test if the `placeArmies` method works correctly when the territory is not owned:
	@Test
	public void testPlaceArmies_UnownedTerritory() {
	    Player player = new Player("Ahmet");
	    Territory territory = new Territory("Turkey", null, new Continent(), 0);
	    player.setUnassignedArmies(5);
	    
	    int result = player.placeArmies(territory);
	    assertEquals(1, result);
	    assertEquals(4, player.getUnassignedArmies());
	    assertEquals(1, territory.getArmyNum());
	    assertEquals(player, territory.getOwner());
	}

	// Test if the `placeArmies` method works correctly when the territory is owned by another player:
	@Test
	public void testPlaceArmies_OtherPlayerTerritory() {
	    Player player = new Player("Ahmet");
	    Player otherPlayer = new Player("OtherPlayerName");
	    Territory territory = new Territory("Turkey", otherPlayer, new Continent(), 0);
	    player.setUnassignedArmies(5);

	    int result = player.placeArmies(territory);
	    assertEquals(-1, result);
	    assertEquals(5, player.getUnassignedArmies());
	    assertEquals(0, territory.getArmyNum());
	    assertEquals(otherPlayer, territory.getOwner());
	}

	// Test if the `placeArmies` method works correctly when the player has no unassigned armies:
	@Test
	public void testPlaceArmies_NoUnassignedArmies() {
	    Player player = new Player("Ahmet");
	    Territory territory = new Territory("Turkey", player, new Continent(), 0);
	    player.setUnassignedArmies(0);

	    int result = player.placeArmies(territory);
	    assertEquals(-1, result);
	    assertEquals(0, player.getUnassignedArmies());
	    assertEquals(0, territory.getArmyNum());
	}

	// Test if the `placeArmies` method works correctly when the player does multiple attempts to add armies:
	@Test
	public void testPlaceArmies__MultipleAttempts() {
	    Player player = new Player("Ahmet");
	    Territory territory = new Territory("Turkey", player, new Continent(), 0);
	    player.setUnassignedArmies(2);
	    
	    int result = player.placeArmies(territory);
	    assertEquals(0, result);
	    assertEquals(1, player.getUnassignedArmies());
	    assertEquals(1, territory.getArmyNum());
	    
	    result = player.placeArmies(territory);
	    assertEquals(0, result);
	    assertEquals(0, player.getUnassignedArmies());
	    assertEquals(2, territory.getArmyNum());
	    
	    result = player.placeArmies(territory);
	    assertEquals(-1, result);
	    assertEquals(0, player.getUnassignedArmies());
	    assertEquals(2, territory.getArmyNum());
	    
	}

}
