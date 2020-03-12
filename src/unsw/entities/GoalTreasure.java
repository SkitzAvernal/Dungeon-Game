package unsw.entities;

import java.util.List;

import unsw.dungeon.Dungeon;
import unsw.entities.Items.Treasure;

public class GoalTreasure implements Goal{
	private Dungeon dungeon;
	private int treasurecount = 0;
	
	
	public GoalTreasure(Dungeon dungeon) {
		this.dungeon = dungeon;
		for (Entity entity : dungeon.getEntities())
			if (entity.getClass().equals(Treasure.class))
				treasurecount++;
	}
	
	
	@Override
	public boolean completed() {
		List<Entity> entities = dungeon.getEntities();
		if (entities.size() == 0) {
			return true;
		}
		
		if (dungeon.getPlayer().getTreasure() == treasurecount)
			return true;
		return false;
	}
	
	
}
