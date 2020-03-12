package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import unsw.entities.Enemy;
import unsw.entities.Entity;
import unsw.entities.Player;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;
    
    private Player player2;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.player2 = dungeon.getPlayer2();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        initEnemy();

    }
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case W:
        	if (player2 != null)
            	player2.moveUp();
            break;
        case S:
        	if (player2 != null)
        		player2.moveDown();
            break;
        case A:
        	if (player2 != null)
        		player2.moveLeft();
            break;
        case D:
        	if (player2 != null)
        		player2.moveRight();
            break;
        default:
            break;
        }
        dungeon.update();
    }
    //TODO
    public void initEnemy() {
    	List<Entity>  entities = dungeon.getEntities();
    	for (Entity entity : entities) {
    		if (entity instanceof Enemy) {
    			Timeline timeline = new Timeline(
    	    		    new KeyFrame(Duration.seconds(1), e -> {
    	    		        ((Enemy) entity).nextMove(player);
    	    		        ((Enemy) entity).kill(player);
    	    		        
    	    		    })
    	    		);
    	    	timeline.setCycleCount(Timeline.INDEFINITE);
    	    	timeline.play();	 
 
    		}
    	}
    	
    }
}

