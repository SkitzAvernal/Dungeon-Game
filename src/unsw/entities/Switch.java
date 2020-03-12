package unsw.entities;

import unsw.dungeon.Dungeon;

/**
 * The Switch Entitiy
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class Switch extends Entity { //TODO: Work on victory conditions on this
	
	private boolean trigger;
	
	/**
	 * Creates a new switch
	 * @param dungeon
	 * @param id
	 * @param x
	 * @param y
	 */
	public Switch (Dungeon dungeon, int id, int x, int y) {
		super(id, x, y, true, false, "pressure_plate.png");
		trigger = false;
	}
	
	/**
	 * Triggers a switch
	 */
	public void setTrigger() {
		trigger = true;
	}
	/**
	 * Unriggers a switch
	 */
	public void setUnTrigger() {
		trigger = false;
	}
	
	/**
	 * Checks if the switch is triggered or not
	 * @return boolean
	 */
	public boolean checkTrigger() {
		return trigger;
	}
}
