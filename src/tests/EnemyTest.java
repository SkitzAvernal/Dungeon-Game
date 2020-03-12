package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Enemy;
import unsw.entities.EnemyNormal;
import unsw.entities.EnemyScared;
import unsw.entities.Player;
import unsw.entities.Items.Potion;

class EnemyTest {

	@Test
	public void killEnemy() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 1);
		Potion potion = new Potion(3, 2, 1);
		//player.attach(enemy);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		player.moveRight();
		player.addItem(potion);
		player.moveRight();
		assertTrue(dungeon.entitySize() == 2);
		player.killEnemy(enemy);
		assertTrue(dungeon.entitySize() == 2);
		
		
	}
	
	@Test
	public void killPlayer() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 1);
		//player.attach(enemy);
		dungeon.addEntity(enemy);
		player.moveRight();
		player.moveRight();
		assertTrue(dungeon.entitySize() == 2);
		enemy.kill(player);
		assertTrue(dungeon.entitySize() == 1);
		
	}
	
	@Test
	public void chasePlayer() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 5, 5);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 1, 1);
		//player.attach(enemy);
		dungeon.addEntity(enemy);
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 2);
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 3);
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 4);
		
	}
	
	@Test
	public void runPlayer() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 5, 5);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 5);
		Potion potion = new Potion(3, 4, 5);
		//player.attach(enemy);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		player.moveLeft();
		player.addItem(potion);
		
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 2);
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 1);
		
	}
	
	@Test
	public void EnemyState() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 5, 5);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 5);
		Potion potion = new Potion(3, 4, 5);
		//player.attach(enemy);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		assertTrue(enemy.getState() instanceof EnemyNormal);
		player.moveLeft();
		assertTrue(enemy.getState() instanceof EnemyScared);

		
	}
	
	@Test
	public void MultipleEnemy() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 5, 5);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 5);
		Enemy enemy1 = new Enemy(dungeon, 2, 2, 5);
		Potion potion = new Potion(3, 4, 5);
		//player.attach(enemy);
		//player.attach(enemy1);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		player.moveLeft();
		player.addItem(potion);
		enemy1.nextMove(player);
		assertTrue(enemy1.getX() == 1);
		enemy.nextMove(player);
		assertTrue(enemy.getX() == 2);
		
	}

}
