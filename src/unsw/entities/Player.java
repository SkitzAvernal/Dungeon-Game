package unsw.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import unsw.dungeon.Dungeon;
import unsw.entities.Items.Item;
import unsw.entities.Items.Key;
import unsw.entities.Items.Potion;
import unsw.entities.Items.Sword;
import unsw.entities.Items.Treasure;

/**
 * The player entity
 * @author Robert Clifton-Everest
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class Player extends Entity implements Subject {

    private Dungeon dungeon;
    private boolean alive = true;
    private boolean invincible = false; //TODO: Add a timer to the invincibility
    private Sword sword;
    private Key key;
    private int treasureCount = 0;
    ArrayList<Observer> enemies = new ArrayList<Observer>();

    /**
     * Create a player positioned in square (x,y)
     * @param dungeon - dungeon that the player is in
     * @param id - id of the player entity
     * @param x - starting x position of the player
     * @param y - starting y position of the player
     */
    public Player(Dungeon dungeon, int id, int x, int y) {
        super(id, x, y, true, false, "human_new.png"); //Walkable to help with the killing code
        this.dungeon = dungeon;
    }

    /**
     * Moves a player up, after doing a series of checks
     */
    public void moveUp() {
        if (getY() > 0 && checkMove(this.getX(), this.getY() -1)) {
        	dungeon.offExit(this.getX(), this.getY());
            y().set(getY() - 1);
            afterMove(this.getX(), this.getY());
            dungeon.checkGoals();
        }
    }

    /**
     * Moves a player down, after doing a series of checks
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 &&  checkMove(this.getX(), this.getY() + 1)) {
        	dungeon.offExit(this.getX(), this.getY());
            y().set(getY() + 1);
            afterMove(this.getX(), this.getY());
            dungeon.checkGoals();
        }
    }
    
    /**
     * Moves a player to the left, after doing a series of checks
     */
    public void moveLeft() {
        if (getX() > 0 && checkMove(this.getX() -1, this.getY())) {
        	dungeon.offExit(this.getX(), this.getY());
            x().set(getX() - 1);
            afterMove(this.getX(), this.getY());
            dungeon.checkGoals();
        }
    }
    
    /**
     * Moves a player to the right, after doing a series of checks
     */
    public void moveRight() {
    	
        if (getX() < dungeon.getWidth() - 1 && checkMove(this.getX() + 1, this.getY())) {
        	dungeon.offExit(this.getX(), this.getY());
            x().set(getX() + 1);
            afterMove(this.getX(), this.getY());
            dungeon.checkGoals();
        }
    }
    
    /**
     * Checks if the player is alive
     * @return
     */
    public boolean isAlive() {
    	return alive;
    }
    
    /**
     * Kills the player
     */
    public void kill() {
    	alive = false;
    }
    
    /**
     * Checks if the player is invulnerable
     * @return
     */
    public boolean isInvincible() {
    	return invincible;
    }
    
    /**
     * Makes a player vulnerable and sets the enemy to chase
     */
    public void unsetInvincible() {
    	invincible = false;
    	notifyObservers();
    }
    
    /**
     * Makes a player invincible and sets the enemy to be scared
     */
    public void setInvincible() {
    	invincible = true;
    	notifyObservers();
    }
    
    /**
     * Gets a key from the player's inventory
     * @return
     */
    public Key getKey() {
    	return key;
    }
    
    /**
     * Puts a key in the player's inventory
     * @param key
     */
    public void setKey(Key key) {
    	this.key = key;
    }
    
    /**
     * Gets a sword in the player's inventory
     * @return
     */
    public Sword getSword() {
    	return sword;
    }
    
    /**
     * Puts a sword into the inventory
     * @param sword
     */
    public void setSword(Sword sword) {
    	this.sword = sword;
    }
    
    /**
     * Tester function to manually add items into the player inventory
     * @param item
     * @return boolean
     */
    public boolean addItem(Item item) {
    	if (item instanceof Sword) {
    		if (sword == null) {
    			sword = (Sword) item;
    			item.intoInventory();
    			return true;
    		}
    	}
    	
    	if (item instanceof Key) {
    		if (key == null) {
    			key = (Key) item;
    			item.intoInventory();
    			return true;
    		}
    	}
    	
    	if (item instanceof Treasure) {
    		//collectTreasure((Treasure) item);
    		item.useItem();
    		return true;
    	}
    	
    	if (item instanceof Potion) {
    		this.invincible = true;
    		item.useItem();
    		notifyObservers();
  
    		
    		return true;
    	}
    	// Can add extra items if situation arises
    	return false;
    }
    
    /**
     * Makes the player invincible and starts a time for 5 seconds (?)
     */
    public void usePotion() {
    	this.invincible = true;
    	notifyObservers();
    	
    	new Timer().schedule(new TimerTask() {
    		
    		@Override
    		public void run() {
    			unsetInvincible();
    		}
    		
    	}, 6000);
    }
    
    /**
     * Checks if the player has a sword, and uses it if the player does have a sword
     * @return
     */
    public boolean hasSword() {
    	if (sword == null)
    		return false;
    	else {
    		sword.useItem();
    		//TODO: Implement code to kill things with the sword (Should kill things in a + shape around the player)
    		if (sword.isUsed())
    			sword = null;
    		return true;
    	}
    	
    }
    
    /** 
     * Uses a key on the player on the door
     * @param door
     */
    public void useKey(Door door) {
    	if (key != null) {
    		key.useItem(door);
    		if (key.isUsed())
    			key = null;
    	}
    }
    
    /**
     * Collects treasure and checks if the victory condition has been established
     * @param treasure
     */
    public void collectTreasure(Treasure treasure) {
    	treasureCount++;
    	dungeon.checkGoals();
    }
    
    /**
     * Gets the amount of treasure that the player has collected
     * @return treasureCount
     */
    public int getTreasure() {
		return treasureCount;
	}
    
    /**
     * Get the dungeon that the player is assigned to
     * @return dungeon
     */
    public Dungeon getDungeon() {
    	return dungeon;
    }
    
 
    /**
     * 
     * @param x - x position of player
     * @param y - y position of player
     * @return boolean
     */
    //Note: Probably redundant
    private boolean checkMove(int x, int y) {
    	
    	if (!dungeon.checkLocation(x,y)) {
    		return false;
    	}
    	
    	return true;
    }
    
 // implementing observer pattern
    @Override
    public void notifyObservers() {
    	for (Observer enemy: enemies) {
    		if (enemy instanceof Enemy) {
    			((Enemy)enemy).update(this);
    		}
    		
    	}
    }
    @Override
    public void attach(Observer enemy) {
    	enemies.add(enemy);
    }
    @Override
    public void detach(Observer enemy) {
    	enemies.remove(enemy);
    }
    
    /**
     * This kills an enemy
     * @param enemy
     */
    public void killEnemy(Enemy enemy) {
    	enemy.die(this);
    }
    
    // this function checks if any entities need changes after
    // player makes a move
    // atm checks enemies
    // will add more entities if needed
    /**
     * This function checks if any entities need to have their position changed after the player moves
     * @param x - x position of player
     * @param y - y position of player
     */
    public void afterMove(int x, int y) {
    	List<Entity> entities = dungeon.getEntities();
    	List<Entity> removeEntities =  new ArrayList<>();
    	for (Entity entity : entities) {
    		int ex = entity.getX();
    		int ey = entity.getY();
    		if (dungeon.compareCoord(x, y, ex, ey)) {
    			if (entity instanceof Enemy && (this.isInvincible() || this.hasSword())) {
    				removeEntities.add(entity);
    			} else if (entity instanceof Potion) {
    				usePotion();
    				removeEntities.add(entity);
    			} else if (entity instanceof Item) {
    				removeEntities.add(entity);
    			} //else if (entity instanceof Sword) {
    			//	removeEntities.add(entity);
    			//}
    		}
    	}
    	if (removeEntities.size() != 0) 
    	for (Entity ent : removeEntities) {
    		dungeon.removeEntity(ent);
    	}
    }
    
    
}
