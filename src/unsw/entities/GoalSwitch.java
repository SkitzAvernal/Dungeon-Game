package unsw.entities;

import java.util.List;

import unsw.dungeon.Dungeon;


public class GoalSwitch implements Goal{

	private Dungeon dungeon;
	
	public GoalSwitch(Dungeon dungeon) {
		this.dungeon = dungeon;
		
	}
	
	@Override
	public boolean completed() {
		List<Entity> entities = dungeon.getEntities();
		if (entities.size() == 0) {
			return true;
		}
		
		for (Entity entity: entities) {
			if (entity.getClass().equals(Switch.class)) {
				Switch s = (Switch) entity;
				if (!s.checkTrigger()) {
					return false;
				}
			}
		}
		return true;
	}
	
	
}
