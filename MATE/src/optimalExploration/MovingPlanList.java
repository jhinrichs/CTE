package optimalExploration;

import java.util.ArrayList;
import java.util.List;

import solutionData.IAgent;

public class MovingPlanList{
	public List<MovingPlan> plan = new ArrayList<MovingPlan>();
	

	public MovingPlanList() {
		// TODO Auto-generated constructor stub
	}

	public void calculateMoves(List<IAgent> agents){
		for (IAgent agent : agents) {
			plan.add(agent.calculateMove());
		}
	}
	
	public List<MovingPlan> getPlan(){
		return plan;
	}
	
	public void execute() {
		for (MovingPlan p : plan) {
			p.execute();
		}
		plan = new ArrayList<MovingPlan>();
		
	}
}