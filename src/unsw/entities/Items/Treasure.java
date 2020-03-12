package unsw.entities.Items;

import unsw.entities.Player;

/**
 * A treasure entity in the dungeon
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */

public class Treasure extends Item {
	
	/**
	 * Creates treasure in the dungeon
	 * @param id
	 * @param x
	 * @param y
	 */
	public Treasure(int id, int x, int y) {
		super(id, x, y, "gold_pile.png");
	}
	
	@Override
	public boolean onMove(Player player) {
		player.collectTreasure(this);
		return true;
	}
	
}
