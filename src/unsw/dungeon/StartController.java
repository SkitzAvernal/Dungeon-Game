package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button coopButton;

    @FXML
    private Button advancedButton;

    @FXML
    private Button boulderButton;

    @FXML
    private Button basicButton;
    
    private DungeonApplication d;
    
    public StartController(DungeonApplication d) {
    	this.d = d;
    }
    
    @FXML
    public void levelHandler(ActionEvent event) {
    	Button button = (Button) event.getSource();
    	try {
	    	switch(button.getText()) {
	    	
	    	case "Basic maze":
	    		d.changeLevel("maze.json");
	    		break;
	    	case "Boulder maze":
	    		d.changeLevel("boulders.json");
	    		break;
	    	case "Advanced maze":
	    		d.changeLevel("advanced.json");
	    		break;
	    	case "Co-op maze":
	    		d.changeLevel("multimaze.json");
	    		break;
	    	
	    	}
    	} catch(IOException error) {
    		System.out.println("Problem changing levels " + error);
    	}
    }
}
