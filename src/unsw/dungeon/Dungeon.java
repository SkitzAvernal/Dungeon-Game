/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.entities.Entity;
import unsw.entities.Goal;
import unsw.entities.Player;
import unsw.entities.Switch;
import unsw.entities.Exit;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Player player2;
    private ArrayList<Goal> goals;
    private ArrayList<Goal> goals2;
    private boolean versus = false;

    public Dungeon(int width, int height, boolean versus) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = new ArrayList<Goal>();
        this.versus = versus;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer(Player player) {
    	addEntity(player);
        this.player = player;
    }
    
    public void setPlayer2(Player player) {
    	addEntity(player);
        this.player2 = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    public List<Entity> getEntities() {
    	return entities;
    }
    
    public void update() {
    	//TODO Add system to update all of the observers in the dungeon. Observers control victory conditions and switches
    }
    
    // calls onMove for every entity at location (x,y)
    public boolean checkLocation(int x, int y) {
    	if (entities.isEmpty() == false) { 
    		for (Entity entity: entities) {
    			if (entity != null) {
    				if (compareCoord(entity.getX(), entity.getY(), x, y)) {
    					if (!entity.onMove(player) && this.isObstacle(x, y)) {
    						return false;
    					}
    				}
    			}
    		}
    	}
    	return true;
    }
    
    public boolean isObstacle(int x, int y) {
    	
    	// check for valid coordinates
    	
    	for (Entity obstacle : entities) {

    		// check same coordinates as entity and isBlocking
    		if (compareCoord(obstacle.getX(), obstacle.getY() , x, y)) {
	    		if (obstacle.isBlocking()) { //TODO: Check here, not sure what happens here
	    			
	    			return true;
	    		}
    		}
    	}
    	return false;
    }
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return true if coordinates the same
     */
    public boolean compareCoord(int x1, int y1, int x2, int y2) {
    	
    	if (x1 == x2 && y1 == y2) {
    		return true;
    	}
    	
    	return false;
    }
    
    public int entitySize() {
    	return entities.size();
    }
    
    
    public void removeEntity(Entity entity) {
    	entity.setX(100);
    	entity.setY(100);
    	entities.remove(entity);
    	checkGoals();
    }
    
    // goal stuff
    public void addGoal(Goal goal) {
    	goals.add(goal);
    }
    
    public List<Goal> getGoals() {
    	return goals;
    }
    
    // checks all goals satisfied
    public boolean checkGoals() {
    	for (Goal goal: goals) {
    		if (!goal.completed()) {
    			return false;
    		}
    	}
    	System.out.println("Game won");
    	return true;
    }
    
    // trigger switch when boulder moves on
    public void checkSwitch(int x, int y) {
    	for (Entity entity : entities) {
    		if (compareCoord(entity.getX(), entity.getY() , x, y)) {
    			if (entity.getClass().equals(Switch.class)) {
    				Switch s = (Switch) entity;
    				s.setTrigger();
    				checkGoals();
    			}
    		}
    	}
    }
    
    // untrigger switch when boulder moved off
    public void returnSwitch(int x, int y) {
    	for (Entity entity : entities) {
    		if (compareCoord(entity.getX(), entity.getY() , x, y)) {
    			if (entity.getClass().equals(Switch.class)) {
    				Switch s = (Switch) entity;
    				s.setUnTrigger();
    				checkGoals();
    			}
    		}
    	}
    }
    
    // untrigger exit when player moves off the exit
    public void offExit(int x, int y) {
    	for (Entity entity : entities) {
    		if (compareCoord(entity.getX(), entity.getY() , x, y)) {
    			if (entity.getClass().equals(Exit.class)) {
    				Exit e = (Exit) entity;
    				e.moveOffExit();
    				checkGoals();
    			}
    		}
    	}
    }
    
}
