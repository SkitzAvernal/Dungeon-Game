package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Player;
import unsw.entities.Portal;

class PortalTest {

	@Test
	void portalMovement() {
		Dungeon dungeon = new Dungeon(10, 10, false);
		Player player = new Player(dungeon, 0, 0, 1);
		Portal p1 = new Portal(dungeon, 1, 1, 1, 1);
		dungeon.addEntity(p1);
		Portal p2 = new Portal(dungeon, 2, 3, 3, 1);
		dungeon.addEntity(p2);
		dungeon.setPlayer(player);
		
		player.moveRight();
		assertTrue(player.getX() == 3 && player.getY() == 3);
		player.moveLeft();
		player.moveRight();
		assertTrue(player.getX() == 1 && player.getY() == 1);
	}
	
	@Test
	void portalSets() {
		Dungeon dungeon = new Dungeon(10, 10, false);
		Player player = new Player(dungeon, 0, 0, 1);
		Portal p1 = new Portal(dungeon, 1, 1, 1, 1);
		dungeon.addEntity(p1);
		Portal p2 = new Portal(dungeon, 2, 3, 3, 1);
		dungeon.addEntity(p2);
		Portal p3 = new Portal(dungeon, 3, 2, 1, 2);
		dungeon.addEntity(p3);
		Portal p4 = new Portal(dungeon, 4, 5, 5, 2);
		dungeon.addEntity(p4);
		dungeon.setPlayer(player);
		
		player.moveRight();
		assertTrue(player.getX() == 3 && player.getY() == 3);
		player.moveLeft();
		player.moveRight();
		assertTrue(player.getX() == 1 && player.getY() == 1);
		
		player.moveRight();
		assertTrue(player.getX() == 5 && player.getY() == 5);
		player.moveLeft();
		player.moveRight();
		assertTrue(player.getX() == 2 && player.getY() == 1);
	}

}
