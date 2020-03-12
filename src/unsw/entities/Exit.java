package unsw.entities;

public class Exit extends Entity {
	/**
	 * Exit Gate Entity
	 * @author Lin Thit Myat Hsu
	 * @author Michael Tran
	 */
	
	private boolean exiting = false;
	
	/**
	 * Creates an Exit
	 * @param id
	 * @param x
	 * @param y
	 */
	public Exit(int id, int x, int y) {
		super(id, x, y, true, false, "/open_door.png");
	}
	
	@Override
	/**
	 * Triggers the exit when a player moves on it
	 * @param player
	 */
	public boolean onMove(Player player) {
		exiting = true;
		return true;
	}

	/**
	 * Detriggers the exit when the player moves off it
	 */
	public void moveOffExit() {
		exiting = false;
	}
	
	public boolean checkExit() {
		return exiting;
	}
	
}
