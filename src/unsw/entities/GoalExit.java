package unsw.entities;

import java.util.List;

import unsw.dungeon.Dungeon;

public class GoalExit implements Goal{

	private Dungeon dungeon;
	
	
	public GoalExit (Dungeon dungeon) {
		this.dungeon = dungeon;
		
	}
	
	@Override
	public boolean completed() {
		List<Entity> entities = dungeon.getEntities();
		/*if (entities.size() == 0) {
			return true;
		}*/
		
		for (Entity entity: entities) {
			if (entity.getClass().equals(Exit.class)) {
				if (!((Exit) entity).checkExit())
					return false;
			}
		}
		return true;
	}
	
	
}
