package unsw.entities.Items;

import unsw.entities.Door;
import unsw.entities.Player;

/**
 * A Key in the Dungeon
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class Key extends Item{
	
	private int uid;

	/**
	 * Creates a key in the dungeon
	 * @param id
	 * @param x
	 * @param y
	 * @param uid
	 */
	public Key(int id, int x, int y, int uid) {
		super(id, x, y, "key.png");
		this.uid = uid;
	}
	
	/**
	 * Uses the key on a specific door - door will open if the key and the door's UID matches
	 * @param door
	 */
	public void useItem(Door door) {
		if (this.uid == door.getUid()) {
			door.openDoor();
			super.useItem();
		}
	}
	
	/**
	 * Picks up the key
	 * @param player
	 */
	@Override
	public boolean onMove(Player player) {
		if (player.getKey() == null) {
			player.setKey(this);
			this.intoInventory();
		}
		return true;
	}
	
	/**
	 * Gets the uid of the key
	 * @return
	 */
	public int getuid() {
		return uid;
	}
}
