package unsw.entities;

public class Door extends Entity {
	/**
	 * The door entity
	 * @author Lin Thit Myat Hsu
	 * @author Michael Tran
	 *
	 */
	private int uid;
	private DoorState state;
	
	/**
	 * Creates a door entity
	 * @param id
	 * @param x
	 * @param y
	 * @param uid
	 */
	public Door(int id, int x, int y, int uid) {
		super(id, x, y, false, false, "closed_door.png");
		this.uid = uid;
		state = new ClosedDoor();
	}

	/**
	 * Sets the door image to be an open door
	 */
	public void openDoor() {
		state = new OpenDoor();
		this.setImagelocation("open_door.png");
	}
	
	/**
	 * Checks if the door is blocking the player or not
	 * @return boolean
	 */
	@Override
	public boolean isBlocking() {
		return !state.isWalkable();
	}
	
	/**
	 * Get the uid of the door to use with the key
	 * @return uid
	 */
	public int getUid() {
		return uid;
	}
	
	/**
	 * Code to open the door as the player moves into it with the correct key
	 * @return true if the door opens, false if remains closed
	 */
	@Override 
	public boolean onMove(Player player) {
		if (state.getClass().equals(ClosedDoor.class)) {
			player.useKey(this);
			return false;
		}
		else
			return true;
	}
}
