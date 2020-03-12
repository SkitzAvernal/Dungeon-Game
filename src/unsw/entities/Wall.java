package unsw.entities;

public class Wall extends Entity {

    public Wall(int id, int x, int y) {
        super(id, x, y, false, false, "/brick_brown_0.png");
    }
    
    @Override
    public boolean onMove(Player player) {
    	return false;
    }
}
