package unsw.entities.Items;

import unsw.entities.Entity;
import unsw.entities.Player;

/**
 * An Item in the game
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class Item extends Entity{
	
	private boolean inInventory = false; // Used to remove item from the map when it is picked up by the player
	private boolean isUsed = false;

	public Item(int id, int x, int y, String imagelocation) {
		super(id, x, y, true, false, imagelocation);
	}

	public boolean isInInventory() {
		return inInventory;
	}

	public boolean isUsed() {
		return isUsed;
	}
	
	/**
	 * Uses the item in the inventory
	 */
	public void useItem() {
		if (isInInventory() == false)
			inInventory = true;
		isUsed = true;
	}
	
	public void intoInventory() {
		inInventory = true;
	}
	
	/**
	 * Superclass for all items
	 */
	@Override
	public boolean onMove(Player player) {
		//player.addItem(this);
		return true;
	}
	
}
