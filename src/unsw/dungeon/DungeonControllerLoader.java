package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.entities.Boulder;
import unsw.entities.Door;
import unsw.entities.Enemy;
import unsw.entities.Entity;
import unsw.entities.Exit;
import unsw.entities.Portal;
import unsw.entities.Switch;
import unsw.entities.Wall;
import unsw.entities.Items.Key;
import unsw.entities.Items.Potion;
import unsw.entities.Items.Sword;
import unsw.entities.Items.Treasure;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private boolean second = false;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/deep_elf_master_archer.png");
        wallImage = new Image("/brick_brown_0.png");
    }

    @Override
    public void onLoad(Entity player) {
    	ImageView view = null;
    	if (second) {
    		view = new ImageView(playerImage);
    	}
    	else {
    		view = new ImageView(player.getImagelocation());
    		second = true;
    	}
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wall.getImagelocation());
        addEntity(wall, view);
    }
    
    @Override
	public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemy.getImagelocation());
        addEntity(enemy, view);
    }
    
    @Override
	public void onLoad(Exit exit) {
    	ImageView view = new ImageView(exit.getImagelocation());
        addEntity(exit, view);
    }

    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasure.getImagelocation());
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulder.getImagelocation());
        addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Switch button) {
    	ImageView view = new ImageView(button.getImagelocation());
        addEntity(button, view);
    }
    
    @Override
    public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potion.getImagelocation());
        addEntity(potion, view);
    }
    
    @Override
	public void onLoad(Sword sword) {
    	ImageView view = new ImageView(sword.getImagelocation());
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(door.getImagelocation());
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(key.getImagelocation());
        addEntity(key, view);
    }
    
    @Override
	public void onLoad(Portal portal) {
    	ImageView view = new ImageView(portal.getImagelocation());
        addEntity(portal, view);
    }
	

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
