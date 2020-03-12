package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Door;
import unsw.entities.Player;
import unsw.entities.Items.Key;

class DoorTest {

	@Test
	void BasicDoorandKeyTest() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Door door = new Door(1, 1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		Key key = new Key(3, 0, 0, 1);
		dungeon.addEntity(key);
		dungeon.addEntity(door);
		dungeon.setPlayer(player);
		player.moveRight();
		
		assertTrue(player.getX() == 0 && player.getY() == 1);
		
		player.moveUp();
		player.moveDown();
		player.moveRight();

		assertFalse(door.isBlocking());
		player.moveRight();
		assertTrue(player.getX() == 1 && player.getY() == 1);
	}
	
	@Test
	void MismatchedKeyInventory() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Door door = new Door(1, 1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		Key key1 = new Key(3, 0, 0, 2);
		Key key2 = new Key(4, 1, 2, 3);
		dungeon.addEntity(key1);
		dungeon.addEntity(door);
		dungeon.setPlayer(player);
		player.addItem(key2);
		player.moveRight();
		
		assertTrue(player.getX() == 0 && player.getY() == 1);
		
		player.moveUp();
		assertTrue(key1.isInInventory() == false);
	}

	@Test
	void PickupAfterUse() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Door door = new Door(1, 1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		Key key1 = new Key(3, 0, 0, 2);
		Key key2 = new Key(4, 1, 2, 1);
		dungeon.addEntity(key1);
		dungeon.addEntity(door);
		dungeon.setPlayer(player);
		player.addItem(key2);
		player.moveRight();
		player.moveUp();
		
		assertTrue(key1.isInInventory() == true);
	}
}
