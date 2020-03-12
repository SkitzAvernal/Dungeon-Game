package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entities.Player;
import unsw.entities.Exit;

class ExitTest {

	@Test
	public void exitActive() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Exit exit = new Exit(1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		dungeon.addEntity(exit);
		dungeon.setPlayer(player);
		player.moveRight();
		
		assertTrue(exit.checkExit());
	}
	
	@Test
	public void exitMoveOnAndOff() {
		Dungeon dungeon = new Dungeon(5, 5, false);
		Exit exit = new Exit(1, 1, 1);
		Player player = new Player(dungeon, 2, 0, 1);
		dungeon.addEntity(exit);
		dungeon.setPlayer(player);
		player.moveRight();
		
		assertTrue(exit.checkExit());
		
		player.moveLeft();
		
		assertFalse(exit.checkExit());
		
		player.moveRight();
		
		assertTrue(exit.checkExit());
	}

}
