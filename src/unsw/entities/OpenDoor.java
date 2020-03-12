package unsw.entities;

public class OpenDoor implements DoorState{
	
	/**
	 * Checks if the door is a walkable door or not
	 */
	@Override
	public boolean isWalkable() {
		return true;
	}
}
