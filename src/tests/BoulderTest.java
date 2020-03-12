package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.entities.Boulder;
import unsw.dungeon.Dungeon;
import unsw.entities.Player;

class BoulderTest {
	
	
	@Test
	public void boulderMoveRightTest() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Boulder boulder = new Boulder(1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		dungeon.addEntity(boulder);
		dungeon.setPlayer(player);
		player.moveRight();

		
		assertTrue(player.getX() == 1 && player.getY() == 1);
		assertTrue(boulder.getX() == 2 && boulder.getY() == 1);
	}
	
	@Test
	public void boulderMoveUpTest() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Boulder boulder = new Boulder(1, 2, 2);
		Player player = new Player(dungeon, 2, 2, 3);
		dungeon.addEntity(boulder);
		dungeon.setPlayer(player);
		player.moveUp();
	
		
		assertTrue(player.getX() == 2 && player.getY() == 2);
		assertTrue(boulder.getX() == 2 && boulder.getY() == 1);
	}
	
	@Test
	public void boulderMoveLeftTest() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Boulder boulder = new Boulder(1, 2, 2);
		Player player = new Player(dungeon, 2, 3, 2);
		dungeon.addEntity(boulder);
		dungeon.setPlayer(player);
		player.moveLeft();

		
		assertTrue(player.getX() == 2 && player.getY() == 2);
		assertTrue(boulder.getX() == 1 && boulder.getY() == 2);
	}
	
	@Test
	public void boulderMoveDownTest() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Boulder boulder = new Boulder(1, 2, 2);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.addEntity(boulder);
		dungeon.setPlayer(player);
		player.moveDown();
		
		assertTrue(player.getX() == 2 && player.getY() == 2);
		assertTrue(boulder.getX() == 2 && boulder.getY() == 3);
	}
	
	@Test
	public void boulderblocked() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Boulder boulder = new Boulder(1, 2, 2);
		Boulder boulder1 = new Boulder(2, 2, 3);
		Player player = new Player(dungeon, 3, 2, 1);
		dungeon.addEntity(boulder);
		dungeon.addEntity(boulder1);
		dungeon.setPlayer(player);
		player.moveDown();
		
		assertTrue(player.getX() == 2 && player.getY() == 1);
		assertTrue(boulder.getX() == 2 && boulder.getY() == 2);
		assertTrue(boulder1.getX() == 2 && boulder1.getY() == 3);
	}
	
	
	
	
}
