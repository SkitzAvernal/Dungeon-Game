package unsw.entities;

import unsw.dungeon.Dungeon;

/**
 * The boulder entity
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */

public class Boulder extends Entity {
	
	/**
	 * Creates a boulder entity
	 * @param id
	 * @param x
	 * @param y
	 */
	public Boulder(int id, int x, int y) {
		super(id, x, y, false, true, "boulder.png");
	}

	/**
	 * Sets a boulder's position to a certain point
	 * @param x - x-position to move to
	 * @param y - y-position to move to
	 */
	public void move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Code to move the boulder as it collides with the player
	 * @param player
	 */
	@Override
	public boolean onMove(Player player) {
		
		int playerX = player.getX();
		int playerY = player.getY();
		
		int boulderX = this.getX();
		int boulderY = this.getY();

		
		// find out which direction player coming from 
		// direction : 1= up, 2=right, 3=down, 4=left
		// maybe move this into a new function
		int direction = 0;
		if (playerY == boulderY) {
			if (playerX == boulderX - 1) {
				direction = 2;
			} else {
				direction = 4;
			}
		} else if (playerX == boulderX){
			if (playerY == boulderY - 1) {
				direction = 1;
			} else {
				direction = 3;
			}
		}
		
		// now work out direction boulder must move
		int newBoulderX = getNewX(direction, boulderX);
		int newBoulderY = getNewY(direction, boulderY);
		

		
		// then check if it is possible to move here
		Dungeon dungeon = player.getDungeon();
		if (!dungeon.isObstacle(newBoulderX, newBoulderY)) {
			// no obstacles ok to move boulder
			dungeon.returnSwitch(boulderX, boulderY);
			dungeon.checkSwitch(newBoulderX, newBoulderY);
			this.move(newBoulderX, newBoulderY);
			
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Finds the new x-position of the boulder
	 * @param direction
	 * @param x
	 * @return int newX
	 */
	
	public int getNewX(int direction, int x) {
		if (direction == 1) {
			return x;
		} else if (direction == 2) {
			return x+1;
		} else if (direction == 3) {
			return x;
		} else {
			// direction = 4
			return x-1;
		}
	}
	
	/**
	 * Finds the new y-position of the boulder
	 * @param direction
	 * @param y
	 * @return int newY
	 */
	public int getNewY(int direction, int y) {
		if (direction == 1) {
			return y+1;
		} else if (direction == 2) {
			return y;
		} else if (direction == 3) {
			return y-1;
		} else {
			// direction = 4
			return y;
		}
	}

	
	
}
