package unsw.entities;

import unsw.dungeon.Dungeon;

/**
 * A Portal Entity in the Dungeon
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 */

public class Portal extends Entity {
	
	private int pairid;
	private Portal pair;
	
	/**
	 * Creates a portal in the Dungeon
	 * @param dungeon
	 * @param id
	 * @param x
	 * @param y
	 * @param pair
	 */
	public Portal (Dungeon dungeon, int id, int x, int y, int pair) {
		super(id, x, y, false, false, "portal.png");
		this.pairid = pair;
		for (Entity e : dungeon.getEntities()) {
			if (e.getClass().equals(this.getClass())){
				tryPair((Portal) e);
			}
		}
	}
	/**
	 * Tries to pair up a portal with its pair
	 * @param pair
	 */
	public void tryPair(Portal pair) {
		
		if (pair.getPairid() == pairid) {
			this.pair = pair;
			pair.setPair(this);
		}
	}
	
	/**
	 * Sets a pair for the portal
	 * @param pair
	 */
	public void setPair(Portal pair) {
		this.pair = pair;
	}
	
	@Override
	/**
	 * Teleports the player to the paired portal
	 * @param Player
	 */
	public boolean onMove(Player player) { //TODO: Check if issue of player bouncing between two portals exists
		if (pair != null) {	
			player.x().set(pair.getX());
			player.y().set(pair.getY());
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the pairid of the portal
	 * @return pairid
	 */
	public int getPairid() {
		return pairid;
	}
}
