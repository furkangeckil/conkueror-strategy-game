package test;

import domain.Player;
import domain.Territory;
import domain.Validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorTest {

//	Validator class is responsible for validating various actions in the game of Risk to ensure they are valid or possible.

//	belongsToPlayer: Checks if the territory belongs to the given Player.
	
//	validateAttack: Checks if the owner of the two territories are the same. If they are not then it checks that if the attack
//	is valid with the attacker territory to defender territory.
	
//	validateRecruit: Checks if the territory that the Player wants to recruit in belongs to the Player.
	
//	validateFortify: Checks if the territory the fortify is from's owner and the territory the fortify is made to's owner are
//	the same Player. Then if it checks if the territories are neighbors.
	 

	
	private Player player1;
	private Player player2;
	private Territory territory1;
	private Territory territory2;
	
	@BeforeEach
	public void setup() {
		player1 = new Player("Kaiser");
		player2 = new Player("Soze");
	}
		
	@Test
	public void testValidBelongsTo() {
		territory1 = new Territory("t1", null, null);
		territory1.setOwner(player1);
		assertTrue(Validator.belongsToPlayer(territory1, player1));
	}
	
	@Test
	public void testInvalidBelongsTo() {
		territory1 = new Territory("t1", null, null);
		territory1.setOwner(player2);
		assertFalse(Validator.belongsToPlayer(territory1, player1));
	}
	
	@Test
	public void testValidAttack() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player2);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		territory1.addNeighbor(territory2);
		assertTrue(Validator.validateAttack(territory1, territory2));
	}
	
	@Test
	public void testAttackOwnTerritory() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player1);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		territory1.addNeighbor(territory2);
		assertFalse(Validator.validateAttack(territory1, territory2));
	}
	
	@Test
	public void testAttackNonNeighbor(){
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player2);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		assertFalse(Validator.validateAttack(territory1, territory2));
	}
	
	@Test
	public void testNotEnoughArmyAttack() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player2);
		territory1.setArmyNum(1);
		territory2.setArmyNum(5);
		territory1.addNeighbor(territory2);
		assertFalse(Validator.validateAttack(territory1, territory2));
	}
	
	@Test
	public void testValidRecruit() {
		territory1 = new Territory("t1",null,null);
		territory1.setOwner(player1);
		assertTrue(Validator.validateRecruit(territory1, player1));
	}
	
	@Test
	public void testNotOwnerRecruit() {
		territory1 = new Territory("t1",null,null);
		territory1.setOwner(player2);
		assertFalse(Validator.validateRecruit(territory1, player1));
	}
	
	@Test
	public void testValidFortify() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player1);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		territory1.addNeighbor(territory2);
		assertTrue(Validator.validateFortify(territory1, territory2,4));
	}
	
	@Test
	public void testFortifyNonNeighbor() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player1);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		assertFalse(Validator.validateFortify(territory1, territory2,4));
	}
	
	@Test
	public void testNotOwnerFortify() {
		territory1 = new Territory("t1",null,null);
		territory2 = new Territory("t2",null, null);
		territory1.setOwner(player1);
		territory2.setOwner(player2);
		territory1.setArmyNum(5);
		territory2.setArmyNum(5);
		territory1.addNeighbor(territory2);
		assertFalse(Validator.validateFortify(territory1, territory2,4));
	}
	


}
