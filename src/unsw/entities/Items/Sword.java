package unsw.entities.Items;

import unsw.entities.Player;

/**
 * The Sword entity
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 */
public class Sword extends Item {
	
	private int uses = 5; //Value is hardcoded as that is what is said in the specs

	/**
	 * Creates a sword in the dungeon
	 * @param id
	 * @param x
	 * @param y
	 */
	public Sword(int id, int x, int y) {
		super(id, x, y, "greatsword_1_new.png");
	}
	
	@Override
	public void useItem() {
		uses--;
		if (uses == 0) {
			super.useItem();
		}
	}
	
	@Override
	public boolean onMove(Player player) {
		if (player.getSword() == null) {
			player.setSword(this);
			this.intoInventory();
		}
		return true;
	}
}
