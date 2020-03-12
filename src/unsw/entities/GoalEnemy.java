package unsw.entities;

import java.util.List;

import unsw.dungeon.Dungeon;

public class GoalEnemy implements Goal{

	private Dungeon dungeon;
	
	
	public GoalEnemy(Dungeon dungeon) {
		this.dungeon = dungeon;
		
	}
	
	@Override
	public boolean completed() {
		List<Entity> entities = dungeon.getEntities();
		if (entities.size() == 0) {
			return true;
		}
		
		for (Entity entity: entities) {
			if (entity.getClass().equals(Enemy.class)) {
				return false;
			}
		}
		return true;
	}
	
	
}
