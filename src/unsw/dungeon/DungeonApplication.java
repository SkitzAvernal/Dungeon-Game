package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {
	
	private Stage primaryStage;
	
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
    	primaryStage.setTitle("Dungeon");

        //DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("boulders.json");

        //DungeonController controller = dungeonLoader.loadController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Start.fxml"));
        loader.setController(new StartController(this));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    
    
    public void changeLevel(String maze) throws IOException {
    	

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(maze);

        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.getScene().setRoot(root);
        primaryStage.show();
        
    }
    
    public void endScreen() throws IOException {
    	
    	
    	
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
