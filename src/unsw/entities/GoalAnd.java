package unsw.entities;

import java.util.ArrayList;
/**
 * Handler for 'and' Goals
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */
public class GoalAnd implements Goal{

	private ArrayList<Goal> goals; 
	
	public GoalAnd() {
		this.goals = new ArrayList<Goal>();
	}
	
	@Override
	public boolean completed() {
		
		for (Goal goal : goals) {
			if (!goal.completed()) {
				return false;
			}
		}
		
		return true;
	}
	
	public void addGoal(Goal NewGoal) {
		this.goals.add(NewGoal);
	}
	
	
}
