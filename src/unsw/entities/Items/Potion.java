package unsw.entities.Items;

import unsw.entities.Player;

/**
 * A potion in the dungeon
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */

public class Potion extends Item{
	
	/**
	 * Creates a potion in the dungeon
	 * @param id
	 * @param x
	 * @param y
	 */
	public Potion(int id, int x, int y) {
		super(id, x, y, "bubbly.png");
	}
	
	@Override
	/**
	 * Makes the player invincible
	 */
	public boolean onMove(Player player) {
		player.setInvincible();
		this.useItem();
		return true;
	}
}
