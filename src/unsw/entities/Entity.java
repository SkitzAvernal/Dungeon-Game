package unsw.entities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 * @author Lin Thit Myat Hsu
 * @author Michale Tran
 *
 */
public class Entity {
    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
	private int id;
    private IntegerProperty x, y;
    private boolean walkable, moveable;
    private String imagelocation;

    /**
     * Create an entity positioned in square (x,y)
     * @param id
     * @param x
     * @param y
     * @param walkable
     * @param moveable
     * @param ImageLocation
     */
    public Entity(int id, int x, int y, boolean walkable, boolean moveable, String imagelocation) {
    	this.id = id;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.walkable = walkable;
        this.moveable = moveable;
        this.imagelocation = imagelocation;
    }

    //General Getters and Setters
    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void setX(int x) {
    	x().set(x);
    }
    
    public void setY(int y) {
    	y().set(y);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBlocking() {
		return !walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	public String getImagelocation() {
		return imagelocation;
	}

	public void setImagelocation(String imagelocation) {
		this.imagelocation = imagelocation;
	}
    
	public boolean onMove(Player player) {
    	return true;
    }
}
