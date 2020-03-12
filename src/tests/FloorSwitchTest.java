package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Boulder;
import unsw.entities.Player;
import unsw.entities.Switch;

class FloorSwitchTest {

	@Test
	public void floorSwitchTrigger() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		Boulder boulder = new Boulder(2, 1, 2);
		Switch s = new Switch(dungeon, 3, 1, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(s);
		
		
		assertTrue(s.checkTrigger() == false);
		player.moveDown();
		assertTrue(s.checkTrigger() == true);
		
	}
	
	
	@Test
	public void floorSwitchUnTrigger() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		Boulder boulder = new Boulder(2, 1, 2);
		Switch s = new Switch(dungeon, 3, 1, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(s);
		
		assertTrue(s.checkTrigger() == false);
		player.moveDown();
		assertTrue(s.checkTrigger() == true);
		player.moveDown();
		assertTrue(s.checkTrigger() == false);
		
	}
	
}
