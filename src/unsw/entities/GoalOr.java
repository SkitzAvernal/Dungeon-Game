package unsw.entities;

import java.util.ArrayList;

/**
 * Handler for 'or' Goals
 * @author Lin Thit Myat Hsu
 * @author Michael Tran
 *
 */

public class GoalOr implements Goal {

	private ArrayList<Goal> goals;
	
	public GoalOr() {
		this.goals = new ArrayList<Goal>();
	}
	
	@Override
	public boolean completed() {
		
		for (Goal goal : goals) {
			if (goal.completed()) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addGoal(Goal NewGoal) {
		this.goals.add(NewGoal);
	}
}
