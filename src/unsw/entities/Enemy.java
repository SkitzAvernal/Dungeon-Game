package unsw.entities;

import unsw.dungeon.Dungeon;

/**
 * The enemy entity
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */

import unsw.entities.Entity;

public class Enemy extends Entity implements Observer{

	private EnemyState state;
	
	/**
	 * Creates an enemy entitiy
	 * @param dungeon
	 * @param id
	 * @param x
	 * @param y
	 */
	public Enemy(Dungeon dungeon, int id, int x, int y) {
		super(id, x ,y, true, false,"/gnome.png");
		this.state = new EnemyNormal();
		dungeon.getPlayer().attach(this);
		
	}

	/**
	 * A helper function to help the enemy move towards the player
	 * @param player
	 */
	public void nextMove(Player player) {
		state.moveEnemy(player, this);
	}
	
	/**
	 * function to kill the player
	 * @param player
	 */
	public void kill(Player player) {
		int x1 = this.getX();
    	int y1 = this.getY();
    	int x2 = player.getX();
    	int y2 = player.getY();
    	Dungeon dungeon = player.getDungeon();
		
    	if (dungeon.compareCoord(x1, y1, x2, y2) && !player.isInvincible()) {
    		dungeon.removeEntity(player);
    		
    	}
    	
	}
	/**
	 * Function to kill this enemy
	 * @param player
	 */
	public void die(Player player) {
		int x1 = this.getX();
    	int y1 = this.getY();
    	int x2 = player.getX();
    	int y2 = player.getY();
    	Dungeon dungeon = player.getDungeon();
    	/*
    	System.out.println(x1);
    	System.out.println(y1);
    	System.out.println(x2);
    	System.out.println(y2);
    	dungeon.compareCoord(x1, y1, x2, y2) &&
    	*/
    	if (dungeon.compareCoord(x1, y1, x2, y2) && player.isInvincible()) {
    		System.out.println("we here");
    		dungeon.checkGoals();
    		player.detach(this);
    		dungeon.removeEntity(this);
    		

    	}
    	
	}
	
	/**
	 * Code to move this entity to another position
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Sets the enemy to the chasing state
	 */
	public void makeNormal() {
		this.state = state.makeNormal();
	}
	
	/**
	 * Sets the enemy to the fleeing state
	 */
	public void makeScared() {
		this.state = state.makeScared();
	}
	
	
	/**
	 * Implements Observer
	 */
	@Override
	public void update(Subject player) {
		if (player instanceof Player) {
			if ((((Player) player).isInvincible())) {
				this.makeScared();
			} else {
				this.makeNormal();
			}
		}
	}
	
	/**
	 * Gets the state of the enemy
	 * @return
	 */
	public EnemyState getState() {
		return state;
	}

	/**
	 * OnMove Override - checks if the player is invincible, then 
	 * @param plauer
	 */
	@Override
	public boolean onMove(Player player) {
		if (player.isInvincible()) {
			this.die(player);
		}
		return true;
	}
	
}
