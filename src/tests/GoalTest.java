package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Boulder;
import unsw.entities.Enemy;
import unsw.entities.Exit;
import unsw.entities.GoalAnd;
import unsw.entities.GoalEnemy;
import unsw.entities.GoalExit;
import unsw.entities.GoalOr;
import unsw.entities.GoalSwitch;
import unsw.entities.GoalTreasure;
import unsw.entities.Player;
import unsw.entities.Switch;
import unsw.entities.Items.Potion;
import unsw.entities.Items.Treasure;

class GoalTest {

	@Test
	public void enemy() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 2, 3, 1);
		Potion potion = new Potion(3, 2, 1);
		GoalEnemy goal = new GoalEnemy(dungeon);
		dungeon.addGoal(goal);
		dungeon.addEntity(enemy);
		dungeon.addEntity(potion);
		player.moveRight();
		player.addItem(potion);
		player.moveRight();
		
		assertTrue(dungeon.checkGoals() == true);
		
		
	}
	
	@Test
	public void treasure() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		Treasure treasure = new Treasure(2, 1, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(treasure);
		GoalTreasure goal = new GoalTreasure(dungeon);
		dungeon.addGoal(goal);
		
		assertTrue(dungeon.checkGoals() == false);
		player.moveDown();
		assertTrue(dungeon.checkGoals() == true);
	}
	
	@Test
	public void floorSwitch() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Player player = new Player(dungeon, 1, 1, 1);
		Boulder boulder = new Boulder(2, 1, 2);
		Switch s = new Switch(dungeon, 3, 1, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(boulder);
		dungeon.addEntity(s);
		GoalSwitch s1 = new GoalSwitch(dungeon);
		dungeon.addGoal(s1);
		
		
		assertTrue(dungeon.checkGoals() == false);
		player.moveDown();
		assertTrue(dungeon.checkGoals() == true);
		
	}
	
	@Test
	public void exit() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Exit exit = new Exit(1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		dungeon.addEntity(exit);
		dungeon.setPlayer(player);
		
		GoalExit e = new GoalExit(dungeon);
		dungeon.addGoal(e);
		
		assertTrue(dungeon.checkGoals() == false);
		player.moveRight();
		assertTrue(dungeon.checkGoals() == true);
		player.moveLeft();
		assertTrue(dungeon.checkGoals() == false);
	}
	
	@Test
	public void goalOr() {
		Dungeon dungeon1 = new Dungeon(5, 5, false);
		Player player = new Player(dungeon1, 1, 1, 1);
		Treasure treasure = new Treasure(2, 1, 0);
		Boulder boulder = new Boulder(3, 1, 2);
		Switch s = new Switch(dungeon1, 4, 1, 3);
		dungeon1.addEntity(treasure);
		dungeon1.addEntity(boulder);
		dungeon1.addEntity(s);
		dungeon1.setPlayer(player);
		
		
		GoalOr o = new GoalOr();
		GoalSwitch s2 = new GoalSwitch(dungeon1);
		GoalTreasure goal1 = new GoalTreasure(dungeon1);
		
		o.addGoal(s2);
		o.addGoal(goal1);
		dungeon1.addGoal(o);
		
		assertTrue(dungeon1.checkGoals() == false);
		player.moveUp();
		assertTrue(dungeon1.checkGoals() == true);
		
	}
	
	@Test
	public void goalAnd() {
		Dungeon dungeon1 = new Dungeon(5, 5, false);
		Player player = new Player(dungeon1, 1, 1, 1);
		Treasure treasure = new Treasure(2, 1, 0);
		Boulder boulder = new Boulder(3, 1, 2);
		Switch s = new Switch(dungeon1, 4, 1, 3);
		dungeon1.addEntity(treasure);
		dungeon1.addEntity(boulder);
		dungeon1.addEntity(s);
		dungeon1.setPlayer(player);
		
		
		GoalAnd o = new GoalAnd();
		GoalSwitch s2 = new GoalSwitch(dungeon1);
		GoalTreasure goal1 = new GoalTreasure(dungeon1);
		
		o.addGoal(s2);
		o.addGoal(goal1);
		dungeon1.addGoal(o);
		
		assertTrue(dungeon1.checkGoals() == false);
		player.moveUp();
		assertTrue(dungeon1.checkGoals() == false);
		
		assertTrue(dungeon1.checkGoals() == false);
		player.moveDown();
		player.moveDown();
		assertTrue(dungeon1.checkGoals() == true);
		
	}
	
	
	
	
	
	
	
}
