package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.entities.Boulder;
import unsw.entities.Door;
import unsw.entities.Enemy;
import unsw.entities.Entity;
import unsw.entities.Exit;
import unsw.entities.Goal;
import unsw.entities.GoalAnd;
import unsw.entities.GoalEnemy;
import unsw.entities.GoalExit;
import unsw.entities.GoalOr;
import unsw.entities.GoalSwitch;
import unsw.entities.GoalTreasure;
import unsw.entities.Player;
import unsw.entities.Portal;
import unsw.entities.Switch;
import unsw.entities.Wall;
import unsw.entities.Items.Key;
import unsw.entities.Items.Potion;
import unsw.entities.Items.Sword;
import unsw.entities.Items.Treasure;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private int ent_id = 0;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        boolean versus = false; //TODO: have a way to read what the dungeon's type is, and a way so that the program doesn't crash if it can't read one

        Dungeon dungeon = new Dungeon(width, height, versus);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        
        loadGoals(dungeon, jsonGoals);
        
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        int id;

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, ent_id, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "player2":
            player = new Player(dungeon, ent_id, x, y);
            dungeon.setPlayer2(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(ent_id, x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, ent_id, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        case "exit":
        	Exit exit = new Exit(ent_id, x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(ent_id, x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(ent_id, x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch button = new Switch(dungeon, ent_id, x, y);
        	onLoad(button);
        	entity = button;
        	break;
        case "invincibility":
        	Potion potion = new Potion(ent_id, x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "sword":
        	Sword sword = new Sword(ent_id, x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "door":
        	id = json.getInt("id");
        	Door door = new Door(ent_id, x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "key":
        	id = json.getInt("id");
        	Key key = new Key(ent_id, x, y, id);
        	onLoad(key);
        	entity = key;
        	break;
        case "portal":
        	id = json.getInt("id");
        	Portal portal = new Portal(dungeon, ent_id, x, y, id);
        	onLoad(portal);
        	entity = portal;
        	break;
        }
        dungeon.addEntity(entity);
        ent_id++;
    }
    
    public void loadGoals(Dungeon dungeon, JSONObject json) {
    	String type = json.getString("goal");
    	Goal goal = null;
    	
    	switch (type) {
    	case "AND":
    		JSONArray jsonGoals = json.getJSONArray("subgoals");
    		goal = loadAndGoals(dungeon, jsonGoals);
    		break;
    	case "OR":
    		jsonGoals = json.getJSONArray("subgoals");
    		goal = loadOrGoals(dungeon, jsonGoals);
    		break;
    	case "exit":
    		goal = new GoalExit(dungeon);
    		break;
    	case "boulders":
    		goal = new GoalSwitch(dungeon);
    		break;
    	case "enemies":
    		goal = new GoalEnemy(dungeon);
    		break;
    	case "treasure":
    		goal = new GoalTreasure(dungeon);
    		break;
    	}
    	dungeon.addGoal(goal);
    }
    
    public Goal loadAndGoals(Dungeon dungeon, JSONArray json) {
    	GoalAnd goal = new GoalAnd();
    	for (int i = 0; i < json.length(); i++) {
    		String type = json.getJSONObject(i).getString("goal");
    		Goal subgoal = null;
    		switch (type) {
        	case "AND":
        		JSONArray jsonGoals = json.getJSONObject(i).getJSONArray("subgoals");
        		subgoal = loadAndGoals(dungeon, jsonGoals);
        		break;
        	case "OR":
        		jsonGoals = json.getJSONObject(i).getJSONArray("subgoals");
        		subgoal = loadOrGoals(dungeon, jsonGoals);
        		break;
        	case "exit":
        		subgoal = new GoalExit(dungeon);
        		break;
        	case "boulders":
        		subgoal = new GoalSwitch(dungeon);
        		break;
        	case "enemies":
        		subgoal = new GoalEnemy(dungeon);
        		break;
        	case "treasure":
        		subgoal = new GoalTreasure(dungeon);
        		break;
        	}
    		goal.addGoal(subgoal);
        }
    	return goal;
    }
    
    public Goal loadOrGoals(Dungeon dungeon, JSONArray json) {
    	GoalOr goal = new GoalOr();
    	for (int i = 0; i < json.length(); i++) {
    		String type = json.getJSONObject(i).getString("goal");
    		Goal subgoal = null;
    		switch (type) {
        	case "AND":
        		JSONArray jsonGoals = json.getJSONObject(i).getJSONArray("subgoals");
        		subgoal = loadAndGoals(dungeon, jsonGoals);
        		break;
        	case "OR":
        		jsonGoals = json.getJSONObject(i).getJSONArray("subgoals");
        		subgoal = loadOrGoals(dungeon, jsonGoals);
        		break;
        	case "exit":
        		subgoal = new GoalExit(dungeon);
        		break;
        	case "boulders":
        		subgoal = new GoalSwitch(dungeon);
        		break;
        	case "enemies":
        		subgoal = new GoalEnemy(dungeon);
        		break;
        	case "treasure":
        		subgoal = new GoalTreasure(dungeon);
        		break;
        	}
    		goal.addGoal(subgoal);
        }
    	return goal;
    }
    

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch button);
    
    public abstract void onLoad(Potion potion);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal);
    // TODO Create additional abstract methods for the other entities

}
