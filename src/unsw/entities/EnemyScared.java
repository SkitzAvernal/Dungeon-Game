package unsw.entities;

import unsw.dungeon.Dungeon;

public class EnemyScared implements EnemyState {

	/**
	 * Sets the enemy to the chasing state
	 */
	public EnemyState makeNormal() {
		return new EnemyNormal();
	}
	
	/**
	 * Sets the enemy to the fleeing state
	 */
	public EnemyState makeScared() {
		return this;
	}
	
	/**
	 * Moves the enemy away from the player
	 * @param Player
	 * @param Enemy
	 */
	public void moveEnemy(Player player, Enemy enemy) {
		Dungeon dungeon = player.getDungeon();
		
		if (player.getX() < enemy.getX() && !dungeon.isObstacle(enemy.getX()+1, enemy.getY())) {
			enemy.move(enemy.getX()+1, enemy.getY());
		} else if (player.getX() > enemy.getX() && !dungeon.isObstacle(enemy.getX()-1, enemy.getY())) {
			enemy.move(enemy.getX()-1, enemy.getY());
		} else if (player.getY() < enemy.getY() && !dungeon.isObstacle(enemy.getX(), enemy.getY()+1)) {
			enemy.move(enemy.getX(), enemy.getY()+1);
		} else if (player.getY() > enemy.getY() && !dungeon.isObstacle(enemy.getX(), enemy.getY()-1)) {
			enemy.move(enemy.getX(), enemy.getY()-1);
		}		
	}
}
