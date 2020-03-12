package unsw.entities;

import unsw.dungeon.Dungeon;

public class EnemyNormal implements EnemyState {

	
	/**
	 * Sets the enemy to the chasing state
	 */
	public EnemyState makeNormal() {
		return this;
	}
	
	/**
	 * Sets the enemy to the fleeing state
	 */
	public EnemyState makeScared() {
		return new EnemyScared();
	}
	
	/**
	 * Moves the enemy towards the player
	 * @param Player
	 * @param Enemy
	 */
	public void moveEnemy(Player player, Enemy enemy) {
		int playerX = player.getX();
		int playerY = player.getY();
		Dungeon dungeon = player.getDungeon();
		
		
		if (playerX < enemy.getX() && !dungeon.isObstacle(enemy.getX()-1, enemy.getY())) {
			enemy.move(enemy.getX()-1, enemy.getY());
		} else if (playerX > enemy.getX() && !dungeon.isObstacle(enemy.getX()+1, enemy.getY())) {
			enemy.move(enemy.getX()+1, enemy.getY());
		} else if (playerY < enemy.getY() && !dungeon.isObstacle(enemy.getX(), enemy.getY()-1)) {
			enemy.move(enemy.getX(), enemy.getY()-1);
		} else if (playerY > enemy.getY() && !dungeon.isObstacle(enemy.getX(), enemy.getY()+1)) {
			enemy.move(enemy.getX(), enemy.getY()+1);
		}
	}
}
